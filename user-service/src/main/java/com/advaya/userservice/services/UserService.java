package com.advaya.userservice.services;

import com.advaya.userservice.exceptions.InvalidTokenValueException;
import com.advaya.userservice.exceptions.UserNotFoundException;
import com.advaya.userservice.models.Token;
import com.advaya.userservice.models.User;
import com.advaya.userservice.repositories.TokenRepository;
import com.advaya.userservice.repositories.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,TokenRepository tokenRepository)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public User signUp(String email, String password, String name)
    {

        User user = new User();
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        user.setName(name);

        user = userRepository.save(user);

        return user;
    }

    public Token login(String email, String password)
    {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty())
        {
            throw new UserNotFoundException(email);
        }

        if(!bCryptPasswordEncoder.matches(password, user.get().getHashedPassword()))
        {
            // throw some exception
            return null;
        }

        Token token = Token.builder().user(user.get()).
                      expiryAt(LocalDate.now().plusDays(10)).value(UUID.randomUUID().toString()).build();

        token = tokenRepository.save(token);

        return token;
    }

    public User validateToken(String token)
    {

        Token tokenObj = tokenRepository.findByValueAndIsDeletedAndExpiryAtGreaterThan(token, Boolean.FALSE, LocalDate.now());
        if(tokenObj == null)
        {
            return null;
        }

        return tokenObj.getUser();
    }

    public User logout(String token)
    {
        Token tokenObj = tokenRepository.findByValueAndIsDeleted(token, Boolean.FALSE);
        if(tokenObj == null)
        {
            throw new InvalidTokenValueException(token);
        }

        return tokenObj.getUser();
    }

}

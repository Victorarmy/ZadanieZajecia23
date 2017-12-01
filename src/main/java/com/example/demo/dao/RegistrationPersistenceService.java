package com.example.demo.dao;

import com.example.demo.exception.AccountExistsException;
import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationPersistenceService {

    private UserRepository userRepository;

    @Autowired
    public RegistrationPersistenceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUserAccount(UserDTO userDTO) throws AccountExistsException {
        if (areExistsInDB(userDTO)) {
            throw new AccountExistsException("Account with this address email already exists");
        }

        User user = new User.UserBuilder(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail()).build();

        return userRepository.save(user);
    }

    private boolean areExistsInDB(UserDTO userDTO) {
        return userRepository.isExisting(userDTO.getEmail()) > 0;
    }
}

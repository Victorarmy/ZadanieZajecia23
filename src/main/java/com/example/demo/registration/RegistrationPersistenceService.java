package com.example.demo.registration;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserRoleRepository;
import com.example.demo.exception.AccountExistsException;
import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationPersistenceService {

    private final static String DEFAULT_USER_ROLE = "ROLE_USER";

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public RegistrationPersistenceService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public User registerNewUserAccountWithDefaultRole(UserDTO userDTO) throws AccountExistsException {
        if (areExistsInDB(userDTO)) {
            throw new AccountExistsException("Account with this address email already exists");
        }
        UserRole userRole = userRoleRepository.findByRole(DEFAULT_USER_ROLE);
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);

        User user = new User.UserBuilder(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(),
                userRoles).build();

        return userRepository.save(user);
    }

    private boolean areExistsInDB(UserDTO userDTO) {
        return userRepository.isExisting(userDTO.getEmail()) > 0;
    }
}

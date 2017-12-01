package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();;

    private boolean enabled;

    public User() {
    }

    private User(UserBuilder userBuilder) {
        this.username = userBuilder.username;
        this.password = userBuilder.password;
        this.email = userBuilder.email;
        userBuilder.roles.forEach(userRole -> roles.add(userRole));
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static class UserBuilder {

        private String username;
        private String password;
        private String email;
        private Set<UserRole> roles;

        public UserBuilder(String username, String password, String email, Set<UserRole> userRoles) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.roles = userRoles;
        }

        public User build() {
            return new User(this);
        }
    }
}

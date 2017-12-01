package com.example.demo.dao;

import com.example.demo.model.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserRole findByRole(String role) {
        TypedQuery<UserRole> query = entityManager
                .createQuery("select ur from UserRole ur where role = :role", UserRole.class)
                .setParameter("role", role);
        return query.getSingleResult();
    }
}

package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public User findByEmail(String email) {
        TypedQuery<User> typedQuery = entityManager
                .createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email);
        return typedQuery.getSingleResult();
    }

    public int isExisting(String email) {
        Query query = entityManager
                .createQuery("select u from User u where u.email = :email")
                .setParameter("email", email);
        return query
                .getResultList()
                .size();
    }
}

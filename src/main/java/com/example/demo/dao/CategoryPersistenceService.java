package com.example.demo.dao;

import com.example.demo.model.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryPersistenceService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Category> findAll() {
        TypedQuery<Category> query = entityManager.createQuery("Select c from Category c", Category.class);
        return query.getResultList();
    }

    public void save(Category category) {
        entityManager.persist(category);
    }
}

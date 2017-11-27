package com.example.demo.dao;

import com.example.demo.model.Task;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class TaskPersistenceService {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Task task) {
        entityManager.persist(task);
    }

    public List<Task> findAll() {
        TypedQuery<Task> query = entityManager.createQuery("select t from Task t", Task.class);
        List<Task> result = query.getResultList();
        return result;
    }

    public void updateTaskAsDone(long[] ids) {
        int doneOperations = 0;
        Query query = entityManager.createQuery("update Task t set t.done = true, t.actualEndDate =:date where t.id = :id");
        for (long id : ids) {
            query.setParameter("date", Date.valueOf(LocalDate.now()));
            query.setParameter("id", id);
            query.executeUpdate();
            doneOperations++;

            if (doneOperations % 10 == 0) {
                entityManager.flush();
                entityManager.clear();
                System.out.println("FLUSH AND CLEAR");
            }
        }
    }

    public List<Task> findAllDone() {
        TypedQuery<Task> query = entityManager.createQuery("select t from Task t where t.done = true", Task.class);
        return query.getResultList();
    }

    public List<Task> findAllUnfinished() {
        TypedQuery<Task> query = entityManager.createQuery("select t from Task t where t.done = false", Task.class);
        return query.getResultList();
    }

    public Task find(Long id) {
        return entityManager.find(Task.class, id);
    }

    public void updateTask(Task task) {
        entityManager.merge(task);
    }
}

package com.example.demo.dao;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface SpringDataRepository extends JpaRepository<Task, Long> {
}

package com.ctt.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctt.studentservice.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}

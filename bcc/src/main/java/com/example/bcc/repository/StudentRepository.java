package com.example.bcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bcc.model.Student;

import dto.StudentDTO;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{
	List<Student> findByNameContainingIgnoreCase(String name);
	
	
    Student findByNim(String nim);
	
	
}

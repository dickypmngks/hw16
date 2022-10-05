package com.example.bcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bcc.model.Score;


@Repository
public interface ScoreRepository extends JpaRepository<Score,String> {
	
	 List<Score> findByNim(String nim);
}

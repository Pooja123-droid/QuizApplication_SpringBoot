package com.demo.quizapp.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.quizapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository <Question, Integer> {
	
	List<Question> findByCategory(String category);

	@Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND()", nativeQuery = true)
	List<Question> findRandomQuestionByCategory(@Param("category") String category);

}
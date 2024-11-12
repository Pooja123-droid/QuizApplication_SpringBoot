package com.demo.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.quizapp.dao.QuestionDao;
import com.demo.quizapp.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions(){
		try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

	

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<String> addQuestions(Question question) {
		 questionDao.save(question);
	        return new ResponseEntity<>("success",HttpStatus.CREATED);
	}



	public ResponseEntity<String> deleteQuestions(int id) {
		questionDao.deleteById(id);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}



	public ResponseEntity<String> updateQuestions(int id, Question question) {
		questionDao.save(question);
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}



	public ResponseEntity<Question> getDeletedQuestion(int id) {
		try {
	        Optional<Question> question = questionDao.findById(id);
	        if (question.isPresent()) {
	            return new ResponseEntity<>(question.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if no question is found
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }		
	}

	

	

}

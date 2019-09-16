package com.codingdojo.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojooverflow.models.Question;
import com.codingdojo.dojooverflow.repositories.QuestionRepository;

@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}
	
	// get all questions
	public List<Question> getAllQuestions(){
		return (List<Question>) questionRepository.findAll();
	}
	
	// get one question by id
	public Question findQuestion(Long id) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		if(optionalQuestion.isPresent()) {
			return optionalQuestion.get();
		} else {
			return null;
		}
	}
	
	// add a question
	public void addQuestion(Question q) {
		questionRepository.save(q);
	}
	
}

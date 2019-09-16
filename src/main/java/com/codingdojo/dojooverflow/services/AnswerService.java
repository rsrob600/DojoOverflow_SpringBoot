package com.codingdojo.dojooverflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingdojo.dojooverflow.models.Answer;
import com.codingdojo.dojooverflow.repositories.AnswerRepository;

@Service
public class AnswerService {
	private final AnswerRepository answerRepository;
	
	public AnswerService(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}
	
	// get all answers
	public List<Answer> getAllAnswers(){
		return (List<Answer>) answerRepository.findAll();
	}
	
	// add an answer
	public void addAnswer(Answer a) {
		answerRepository.save(a);
	}

	
}

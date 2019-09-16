package com.codingdojo.dojooverflow.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.dojooverflow.models.Answer;
import com.codingdojo.dojooverflow.models.Question;
import com.codingdojo.dojooverflow.services.AnswerService;
import com.codingdojo.dojooverflow.services.QuestionService;
import com.codingdojo.dojooverflow.services.TagService;

@Controller
public class QuestionController {
	private final QuestionService questionService;
	private final TagService tagService;
	private final AnswerService answerService;
	
	public QuestionController(QuestionService questionService, TagService tagService, AnswerService answerService) {
		this.questionService = questionService;
		this.tagService = tagService;
		this.answerService = answerService;
	}
	
	// display add new question and tag page
	@RequestMapping("/questions/new")
	public String newQuestion(@ModelAttribute("question") Question question) {
		return "view/newQuestion.jsp";
	}
	
	// save new question and tag page
	@PostMapping("/addQuestion")
	public String addQuestion(@Valid @ModelAttribute("question") Question question, BindingResult result, RedirectAttributes flash) {
		
		// need tag logic validation (comma separated, lowercase, and limited to 3)
		
		if(result.hasErrors()) {
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "view/newQuestion.jsp";
		} else {
			
			// need tag logic validation
			
			questionService.addQuestion(question);
			return "redirect:/questions/new";
		}
	}
	
	// show question, tag and answer by id
	@RequestMapping("/questions/{id}")
	public String showQuestion(Model model, @PathVariable("id") Long id, @ModelAttribute("answer") Answer answer) {
		Question gQuestions = questionService.findQuestion(id);
		model.addAttribute("gQuestions", gQuestions);
		model.addAttribute("gTags", gQuestions.getTags());
		model.addAttribute("answers", gQuestions.getAnswers());
		return "view/newAnswer.jsp";
	}
	
	// save question and answer by id
	@PostMapping("/questions/{id}")
	public String addAnswerQuestion(@Valid @PathVariable("id") Long Id, @ModelAttribute("answer") Answer answer, @RequestParam("question") Long qID, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "view/newAnswer.jsp";
		} else {
			answerService.addAnswer(answer);
			return "redirect:/questions/" + qID;
		}
	}
	
	
	// show question dashboard
	@RequestMapping("/questions")
	public String dashboard(Model model) {
		List<Question> allQuestions = questionService.getAllQuestions();
		model.addAttribute("questions", allQuestions);
		return "view/dashboard.jsp";
	}
	
	
	
	
}

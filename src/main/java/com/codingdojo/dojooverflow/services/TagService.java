package com.codingdojo.dojooverflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingdojo.dojooverflow.models.Tag;
import com.codingdojo.dojooverflow.repositories.TagRepository;

@Service
public class TagService {
	private final TagRepository tagRepository;
	
	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
	// get all tags
	public List<Tag> getAllTags(){
		return (List<Tag>) tagRepository.findAll();
	}
	
	// add a tag
	public void addTag(Tag t) {
		tagRepository.save(t);
	}
	
	
}

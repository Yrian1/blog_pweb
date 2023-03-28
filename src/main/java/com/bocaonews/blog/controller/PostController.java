package com.bocaonews.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bocaonews.blog.dtos.PostDto;
import com.bocaonews.blog.services.PostService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService service;

	
	@GetMapping
	public List<PostDto> getAllPosts(){
		return  service.getAllPosts();  
	}
	
	@PostMapping
	public ResponseEntity<PostDto> cadastrar(@RequestBody @Valid PostDto postDto, UriComponentsBuilder uriBuilder) {
		return service.cadastrar(postDto, uriBuilder);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PostDto> atualizar(@PathVariable Long id, @RequestBody @Valid PostDto postDto){
		return service.atualizar(id,postDto);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<PostDto> apagar(@PathVariable Long id){
		return service.apagar(id);
	}
}

package com.bocaonews.blog.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.bocaonews.blog.dtos.PostDto;
import com.bocaonews.blog.entidades.Post;
import com.bocaonews.blog.repositorios.PostRepository;
import com.bocaonews.blog.repositorios.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class PostService {


	@Autowired
	private PostRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<PostDto> converteLista(List<Post> lista){
		return lista.stream().map(PostDto::new).collect(Collectors.toList());	
	}
	
	public Post getPost(PostDto postDto) {
		Post post =new Post();
		post.setTitulo(postDto.getTitulo());
		post.setTexto(postDto.getTexto());
		post.setCategoria(postDto.getCategoria());
		post.setUsuario(usuarioRepository.findByNome(postDto.getUsuario()));
		return post;
	}
	
	public List<PostDto> getAllPosts(){
		return this.converteLista(repository.findAll());
	}
	
	public ResponseEntity<PostDto> cadastrar(@RequestBody @Valid PostDto postDto, UriComponentsBuilder uriBuilder) {
		Post post=this.getPost(postDto);
		repository.save(post);
		URI uri=uriBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).body(new PostDto(post));
	}

	public ResponseEntity<PostDto> atualizar(Long id, @Valid PostDto postDto) {
		Optional<Post> postOptitional= repository.findById(id);
		Post post=null;
		if(postOptitional.isPresent()) {
			post=postOptitional.get();
			post.setTitulo(postDto.getTitulo());
			post.setTexto(postDto.getTexto());
			post.setCategoria(postDto.getCategoria());
			post.setUsuario(usuarioRepository.findByNome(postDto.getUsuario()));
			repository.save(post);
			return new ResponseEntity<PostDto>(new PostDto(post), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<PostDto> apagar(Long id) {
		Optional<Post> postOptitional= repository.findById(id);
		Post post=null;
		if(postOptitional.isPresent()) {
			post=postOptitional.get();
			ResponseEntity<PostDto>  ent=new ResponseEntity<PostDto>(new PostDto(post), HttpStatus.OK);
			repository.delete(post);
			return ent;
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}

package com.bocaonews.blog.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bocaonews.blog.entidades.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	
	public List<Post> findByTitulo(String titulo);
	
}

package com.bocaonews.blog.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bocaonews.blog.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Usuario findByNome(String nome);
}

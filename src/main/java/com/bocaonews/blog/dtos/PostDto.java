package com.bocaonews.blog.dtos;


import com.bocaonews.blog.entidades.Categoria;
import com.bocaonews.blog.entidades.Post;


import jakarta.validation.constraints.NotNull;

public class PostDto {
	private Long id;
	@NotNull(message = "O titulo não pode ser nulo")
	private String titulo;
	private String texto;
	private String usuario;
	private Categoria categoria;
	
	public PostDto(Post post) {	
		this.id = post.getId();
		this.titulo = post.getTitulo();
		this.texto = post.getTexto();
		this.usuario = post.getUsuario().getNome();
		this.categoria = post.getCategoria();
	}
	
	
	public PostDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}

package br.com.alura.spring.data.orm.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	private String logradouro;
	private Integer numero;
	private String bairro;
	
	
	public Endereco() {
	}

	@Override
	public String toString() {
		return this.getLogradouro() + " número " + this.getNumero() + 
				" | Bairro: " + this.getBairro();
	}

	public Endereco(String logradouro, Integer numero, String bairro) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}
}

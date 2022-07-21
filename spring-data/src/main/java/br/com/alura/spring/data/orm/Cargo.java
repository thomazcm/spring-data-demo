package br.com.alura.spring.data.orm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cargos")
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	@OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	public void adicionarFuncionario(Funcionario funcionario) {
		funcionarios.add(funcionario);
		funcionario.setCargo(this);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Cargo id=" + id + ", descricao=" + descricao;
	}
	
}

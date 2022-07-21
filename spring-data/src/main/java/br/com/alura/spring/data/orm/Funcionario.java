package br.com.alura.spring.data.orm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private BigDecimal salario;
	private LocalDate dataContratacao = LocalDate.now();
	@ManyToOne(fetch = FetchType.EAGER)
	private Cargo cargo;
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionarios_unidades", joinColumns = {
			@JoinColumn(name = "fk_funcionario")
	}, inverseJoinColumns = {
			@JoinColumn(name = "fk_unidade")
	})
	private List<UnidadeDeTrabalho> unidadesDeTrabalho = new ArrayList<UnidadeDeTrabalho>();

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Funcionario() {
	}

	public Funcionario(String nome, String cpf, BigDecimal salario) {
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
	}
	

	@Override
	public String toString() {
		String toString = "id=" + id + " - " + nome + ", cpf: " + cpf + ", salario: " + salario
				+ ", Data de contratação: " + dataContratacao;
		if (this.cargo != null)
			toString += "\nCargo: "+this.cargo.getDescricao();
		return toString;
	}

	public Integer getId() {
		return id;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void adicionarUnidade(UnidadeDeTrabalho unidadeDeTrabalho) {
		unidadesDeTrabalho.add(unidadeDeTrabalho);
		unidadeDeTrabalho.adicionarFuncionario(this);
	}

}

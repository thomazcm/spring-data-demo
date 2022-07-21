package br.com.alura.spring.data.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.alura.spring.data.orm.embedded.Endereco;

@Entity
@Table(name = "unidades_de_trabalho")
public class UnidadeDeTrabalho {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String descricao;
	@Embedded
	private Endereco endereco;
	public UnidadeDeTrabalho() {
	}
	@ManyToMany(mappedBy = "unidadesDeTrabalho", fetch = FetchType.EAGER)
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@Override
	public String toString() {
		String toString =  "id=" + id + ", Unidade :" + descricao + 
				"\nEndereço: " + this.getEndereco().toString() +
				"\n===Funcionarios Cadastrados===";
		for (Iterator<Funcionario> iterator = funcionarios.iterator(); iterator.hasNext();) {
			Funcionario funcionario = (Funcionario) iterator.next();
			toString += "\n" + funcionario.getNome();
		}
		return toString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void adicionarFuncionario(Funcionario funcionario) {
		funcionarios.add(funcionario);
	}
}

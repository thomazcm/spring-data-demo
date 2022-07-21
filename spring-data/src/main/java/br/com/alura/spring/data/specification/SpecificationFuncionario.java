package br.com.alura.spring.data.specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;
import br.com.alura.spring.data.orm.Funcionario;

public class SpecificationFuncionario {

    public static Specification<Funcionario> nomeSimilar(String nome) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"),
                "%" + nome + "%");
    }

    public static Specification<Funcionario> cpfIgual(String cpf) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpf"), cpf);
    }

    public static Specification<Funcionario> salarioMaior(BigDecimal salario) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("salario"),
                salario);
    }

    public static Specification<Funcionario> dataContratacaoMaior(LocalDate dataContratacao) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("dataContratacao"),
                dataContratacao);
    }

}

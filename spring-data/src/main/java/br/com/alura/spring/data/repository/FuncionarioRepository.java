package br.com.alura.spring.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

public interface FuncionarioRepository
        extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {
    List<Funcionario> findByNome(String nome);

    @Query("SELECT f FROM Funcionario f" + " WHERE f.salario >= :salario"
            + " AND f.dataContratacao >= :dataContratacao")
    List<Funcionario> findByDataSalarioMaior(BigDecimal salario, LocalDate dataContratacao);

    @Query(value = "SELECT * FROM funcionarios f " + "WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    @Query(value = "SELECT f.nome, f.salario " + "FROM funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioNomeSalario();
}

package br.com.alura.spring.data.service.relatorio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.service.helper.Console;
import br.com.alura.spring.data.service.helper.MenuServiceHelper;

@Service
public class RelatoriosService {
    private final FuncionarioRepository funcionarioRepository;
    private final MenuServiceHelper menu = new MenuServiceHelper();

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        Boolean querSair = false;
        while (!querSair) {
            Console.menuAcoesRelatorio();
            switch (scanner.nextInt()) {
            case 1:
                buscaFuncionarioPorNome(scanner);
                break;
            case 2:
                buscarFuncionariosPorNomeSalarioMaiorData(scanner);
                break;
            case 3:
                buscarFuncionariosPorDataMaior(scanner);
                break;
            case 4:
                mostrarFuncionariosNomeSalario();
                break;
            default:
                querSair = Boolean.TRUE;
                break;
            }
        }
    }

    private void mostrarFuncionariosNomeSalario() {
        List<FuncionarioProjecao> lista = funcionarioRepository.findFuncionarioNomeSalario();
        lista.forEach(
                f -> System.out.println("Nome: " + f.getNome() + " | Salário Atual: R$ " + f.getSalario()));
    }

    private void buscarFuncionariosPorDataMaior(Scanner scanner) {
        Console.titulo(" RELATÓRIOS ");
        LocalDate data = menu.perguntaData(scanner, " a partir da qual será feita a busca.");
        List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(data);
        funcionarios.forEach(System.out::println);
    }

    private void buscarFuncionariosPorNomeSalarioMaiorData(Scanner scanner) {
        Console.titulo(" RELATÓRIOS ");
        BigDecimal salario = menu.perguntaSalario(scanner, " que será o valor mínimo a buscar");
        LocalDate data = menu.perguntaData(scanner , " a partir da qual será feita a busca.");

        List<Funcionario> funcionarios = funcionarioRepository.findByDataSalarioMaior(salario, data);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioPorNome(Scanner scanner) {
        Console.titulo(" RELATÓRIOS ");
        String nome = menu.perguntaNome(scanner, " FUNCIONARIO ", " que deseja buscar");
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
        funcionarios.forEach(System.out::println);
    }

}

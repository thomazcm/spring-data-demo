package br.com.alura.spring.data.service.relatorio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.service.helper.Console;
import br.com.alura.spring.data.service.helper.MenuServiceHelper;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
    private final MenuServiceHelper menu;
    private final FuncionarioRepository funcionarioRepository;
    private final String PERGUNTA = " que deseja buscar ou digite 0 para continuar:";

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
        menu = new MenuServiceHelper("FUNCIONARIO", funcionarioRepository);
    }

    public void inicial(Scanner scanner) {
        String nome = stringDinamica(menu.perguntaNome(scanner, PERGUNTA));
        System.out.println(nome);
        String cpf = stringDinamica(menu.perguntaCPF(scanner, PERGUNTA));
        System.out.println(cpf);
        BigDecimal salario = bigDecimalDinamico(menu.perguntaSalario(scanner, " mínimo " + PERGUNTA));
        System.out.println(salario);
        LocalDate data = dataDinamica(scanner);
        System.out.println(data);

        List<Funcionario> funcionarios = funcionarioRepository.findAll(
                Specification.where(SpecificationFuncionario.nomeSimilar(nome))
                             .or(SpecificationFuncionario.salarioMaior(salario))
                             .or(SpecificationFuncionario.cpfIgual(cpf))
                             .or(SpecificationFuncionario.dataContratacaoMaior(data)));

        funcionarios.forEach(System.out::println);

    }

    private LocalDate dataDinamica(Scanner scanner) {
        Console.perguntaData(" a partir da qual " + PERGUNTA);
        String data = scanner.next();
        if (data.equalsIgnoreCase("0")){
            return null;
        }
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private BigDecimal bigDecimalDinamico(BigDecimal bigDecimal) {
        if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        return bigDecimal;
    }

    private String stringDinamica(String string) {
        if (string.equalsIgnoreCase("0")) {
            return null;
        }
        return string;
    }
}

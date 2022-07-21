package br.com.alura.spring.data.service.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.spring.data.service.crud.CrudService;

public class MenuServiceHelper {
    private final String ENTIDADE;
    private final CrudRepository<?, Integer> repository;

    public MenuServiceHelper() {
        this.ENTIDADE = "";
        this.repository = null;
    }

    public MenuServiceHelper(String entidade, CrudRepository<?, Integer> repository) {
        this.ENTIDADE = entidade;
        this.repository = repository;
    }

    public void inicialCrud(Scanner scanner, CrudService service) {
        Boolean querFicar = true;
        while (querFicar) {
            Console.menuAcoesCrud(ENTIDADE);
            switch (scanner.nextInt()) {
            case 1:
                service.salvar(scanner);
                break;
            case 2:
                service.atualizar(scanner);
                break;
            case 3:
                service.visualizar(scanner);
                break;
            case 4:
                service.deletar(scanner);
                break;
            default:
                System.out.println("Retornando ao menu Inicial");
                querFicar = false;
                break;
            }
        }
    }

    // Show the table of the entity that created this object
    public void mostrarTabela() {
        Console.titulo(ENTIDADE);
        repository.findAll().forEach(System.out::println);
        Console.separador();
    }

    // Show table with paging and sorting
    public void mostrarTabela(Pageable pageable, PagingAndSortingRepository<?, Integer> repository) {
        Console.titulo(ENTIDADE);
        Page<?> funcionarios = repository.findAll(pageable);
        System.out.println(funcionarios);
        funcionarios.forEach(System.out::println);
        System.out.println("Pagina atual : " + funcionarios.getNumber());
        System.out.println("Total de elementos: " + funcionarios.getTotalElements());
        Console.separador();
    }

    // Show tables from other entities through parameters
    public void mostrarTabela(String nomeEntidade, CrudRepository<?, Integer> repository) {
        Console.titulo(nomeEntidade);
        repository.findAll().forEach(System.out::println);
        Console.separador();
    }

    public void deletar(Scanner scanner) {
        Console.perguntaId(ENTIDADE, " que deseja deletar.");
        repository.deleteById(scanner.nextInt());
        Console.mensagemSucesso();
    }

    public void opcoesAtualizarFuncionario() {
        Console.menuAtualizarFuncionario();
    }

    public void opcoesAtualizarUnidade() {
        Console.menuAtualizarUnidadeDeTrabalho();
    }

    public void perguntaConfirma(String acao) {
        Console.perguntaConfirma();
    }

    public LocalDate perguntaData(Scanner scanner, String acao) {
        Console.perguntaData(acao);
        return LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Integer perguntaPagina(Scanner scanner) {
        Console.perguntaPagina();
        return scanner.nextInt();
    }

    public Integer perguntaId(Scanner scanner, String acao) {
        Console.perguntaId(ENTIDADE, acao);
        return scanner.nextInt();
    }

    public Integer perguntaId(Scanner scanner, String entidade, String acao) {
        Console.perguntaId(entidade, acao);
        return scanner.nextInt();
    }

    public String perguntaNome(Scanner scanner, String acao) {
        Console.perguntaNome(ENTIDADE, acao);
        return scanner.next();
    }

    public String perguntaNome(Scanner scanner, String entidade, String acao) {
        Console.perguntaNome(entidade, acao);
        return scanner.next();
    }

    public BigDecimal perguntaSalario(Scanner scanner, String acao) {
        Console.perguntaSalario(acao);
        return new BigDecimal(scanner.nextDouble());
    }

    public String perguntaCPF(Scanner scanner, String acao) {
        Console.perguntaCPF(acao);
        return scanner.next();
    }
}

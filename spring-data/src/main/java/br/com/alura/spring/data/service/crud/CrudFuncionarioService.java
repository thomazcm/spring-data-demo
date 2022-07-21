package br.com.alura.spring.data.service.crud;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;
import br.com.alura.spring.data.service.helper.Console;
import br.com.alura.spring.data.service.helper.MenuServiceHelper;

@Service
public class CrudFuncionarioService extends CrudService {
    private final FuncionarioRepository funcionarioRepository;
    private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
    private final CargoRepository cargoRepository;
    private final MenuServiceHelper menu;
    private final String nomeEntidade = "FUNCIONARIOS";

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CrudCargoService cargoService,
            CargoRepository cargoRepository, UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
        this.cargoRepository = cargoRepository;
        menu = new MenuServiceHelper(nomeEntidade, funcionarioRepository);
    }

    public void inicial(Scanner scanner) {
        menu.inicialCrud(scanner, this);
    }

    public void visualizar(Scanner scanner) {
        Integer page = menu.perguntaPagina(scanner);
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.ASC, "nome");
        menu.mostrarTabela(pageable, funcionarioRepository);
    }

    public void salvar(Scanner scanner) {
        Funcionario funcionario = new Funcionario();
        atualizarDadosCadastrais(scanner, funcionario);
    }

    public void atualizar(Scanner scanner) {
        menu.mostrarTabela();
        System.out.println("Informe o Id do funcionário que deseja atualizar");
        Funcionario funcionario = funcionarioRepository.findById(scanner.nextInt()).get();

        menu.opcoesAtualizarFuncionario();
        switch (scanner.nextInt()) {
        case 1:
            atualizarDadosCadastrais(scanner, funcionario);
            break;
        case 2:
            atribuirCargo(scanner, funcionario);
            break;
        case 3:
            adicionarUnidadeDeTrabalho(scanner, funcionario);
            break;
        default:
            break;
        }
    }

    public void deletar(Scanner scanner) {
        menu.deletar(scanner);
    }

    private void adicionarUnidadeDeTrabalho(Scanner scanner, Funcionario funcionario) {
        menu.mostrarTabela(" UNIDADES DE TRABALHO ", unidadeDeTrabalhoRepository);
        Integer unidadeDeTrabalhoId = menu.perguntaId(scanner, " UNIDADE DE TRABALHO ",
                " que deseja adicionar a este funcionário.");
        UnidadeDeTrabalho unidadeDeTrabalho = unidadeDeTrabalhoRepository.findById(unidadeDeTrabalhoId).get();
        menu.perguntaConfirma("Adicionar a unidade " + unidadeDeTrabalho.getDescricao()
                + " ao cadastro do funcionário " + funcionario.getNome());
        if (scanner.nextInt() == 1) {
            funcionario.adicionarUnidade(unidadeDeTrabalho);
            funcionarioRepository.save(funcionario);
            unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
            Console.mensagemSucesso();
        }
    }

    private void atribuirCargo(Scanner scanner, Funcionario funcionario) {
        menu.mostrarTabela(" CARGOS ", cargoRepository);
        Integer idCargo = menu.perguntaId(scanner, " CARGO ", " que sera atribuido a este funcionário:");
        Cargo cargo = cargoRepository.findById(idCargo).get();
        menu.perguntaConfirma(
                "Atribuir o cargo de " + cargo.getDescricao() + " para " + funcionario.getNome());
        if (scanner.nextInt() == 1) {
            cargo.adicionarFuncionario(funcionario);
            cargoRepository.save(cargo);
            funcionarioRepository.save(funcionario);
            Console.mensagemSucesso();
        }
    }

    private void atualizarDadosCadastrais(Scanner scanner, Funcionario funcionario) {
        String nome = menu.perguntaNome(scanner, " deste funcionário");
        String cpf = menu.perguntaCPF(scanner, " deste funcionário");
        LocalDate data = menu.perguntaData(scanner, "deste funcionário");
        BigDecimal salario = menu.perguntaSalario(scanner, " deste funcionário");
        menu.perguntaConfirma("Adicionar Funcionário: \nNome: " + nome + "\nCpf: " + cpf + "\nSalario: "
                + salario + " Data de contratação: " + data);
        if (scanner.nextInt() == 1) {
            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setDataContratacao(data);
            funcionario.setSalario(salario);
            funcionarioRepository.save(funcionario);
            Console.mensagemSucesso();
        }
    }
}

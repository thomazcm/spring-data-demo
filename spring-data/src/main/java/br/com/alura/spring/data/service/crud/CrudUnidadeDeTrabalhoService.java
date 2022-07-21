package br.com.alura.spring.data.service.crud;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.orm.embedded.Endereco;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;
import br.com.alura.spring.data.service.helper.Console;
import br.com.alura.spring.data.service.helper.MenuServiceHelper;

@Service
public class CrudUnidadeDeTrabalhoService extends CrudService {
    private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
    private final MenuServiceHelper menu;
    private final String nomeEntidade = "UNIDADES DE TRABALHO";

    public CrudUnidadeDeTrabalhoService(UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
        this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
        menu = new MenuServiceHelper(nomeEntidade, unidadeDeTrabalhoRepository);
    }

    public void inicial(Scanner scanner) {
        menu.inicialCrud(scanner, this);
    }

    public void salvar(Scanner scanner) {
        System.out.println("Informe o nome da Unidade de Trabalho");
        String nome = scanner.next();
        Endereco endereco = construirEndereco(scanner);
        Console.perguntaConfirma();
        if (scanner.nextInt() == 1) {
            UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
            unidadeDeTrabalho.setDescricao(nome);
            unidadeDeTrabalho.setEndereco(endereco);
            unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
        }
    }

    public void atualizar(Scanner scanner) {
        menu.mostrarTabela();
        Integer unidadeDeTrabalhoId = menu.perguntaId(scanner, " que deseja atualizar:");
        UnidadeDeTrabalho unidadeDeTrabalho = unidadeDeTrabalhoRepository.findById(unidadeDeTrabalhoId).get();
        menu.opcoesAtualizarUnidade();
        switch (scanner.nextInt()) {
        case 1:
            atualizarNome(scanner, unidadeDeTrabalho);
            break;
        case 2:
            atualizarEndereco(scanner, unidadeDeTrabalho);
            break;
        default:
            break;
        }
    }

    public void visualizar(Scanner scanner) {
        menu.mostrarTabela();
    }

    public void deletar(Scanner scanner) {
        menu.deletar(scanner);
    }

    private void atualizarEndereco(Scanner scanner, UnidadeDeTrabalho unidadeDeTrabalho) {
        Endereco endereco = construirEndereco(scanner);
        unidadeDeTrabalho.setEndereco(endereco);
        Console.mensagemSucesso();
        unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
    }

    private void atualizarNome(Scanner scanner, UnidadeDeTrabalho unidadeDeTrabalho) {
        String novoNome = menu.perguntaNome(scanner,
                " denominada " + unidadeDeTrabalho.getDescricao() + " que deseja atualizar.");
        unidadeDeTrabalho.setDescricao(novoNome);
        Console.mensagemSucesso();
        unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
    }

    private Endereco construirEndereco(Scanner scanner) {
        System.out.println("Informe a rua onde está localizada");
        String rua = scanner.next();
        System.out.println("Informe o numero da Unidade");
        Integer numero = scanner.nextInt();
        System.out.println("Informe o bairro onde está localizada");
        String bairro = scanner.next();
        return new Endereco(rua, numero, bairro);
    }

}

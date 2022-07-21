package br.com.alura.spring.data.service.crud;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.service.helper.Console;
import br.com.alura.spring.data.service.helper.MenuServiceHelper;

@Service
public class CrudCargoService extends CrudService{
	private final CargoRepository cargoRepository;
	private final MenuServiceHelper menu;
	private final String nomeEntidade = "CARGOS";

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
		menu = new MenuServiceHelper(nomeEntidade, cargoRepository);
	}

	public void inicial(Scanner scanner) {
		menu.inicialCrud(scanner, this);
	}

	public void salvar(Scanner scanner) {
	    menu.perguntaNome(scanner, " que deseja cadastrar.");
		String nome = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(nome);
		cargoRepository.save(cargo);
		Console.mensagemSucesso();
	}

	public void atualizar(Scanner scanner) {
		Integer id = menu.perguntaId(scanner, " que deseja atualizar:");
		String novoNome = menu.perguntaNome(scanner, " que deseja que substitua o atual:");
		Cargo cargo = cargoRepository.findById(id).get();
		cargo.setDescricao(novoNome);
		cargoRepository.save(cargo);
		Console.mensagemSucesso();
	}

	public void visualizar(Scanner scanner) {
		menu.mostrarTabela();
	}

    public void deletar(Scanner scanner) {
        menu.deletar(scanner);
    }
}

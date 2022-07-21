package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.service.crud.CrudCargoService;
import br.com.alura.spring.data.service.crud.CrudFuncionarioService;
import br.com.alura.spring.data.service.crud.CrudUnidadeDeTrabalhoService;
import br.com.alura.spring.data.service.helper.Console;
import br.com.alura.spring.data.service.relatorio.RelatorioFuncionarioDinamico;
import br.com.alura.spring.data.service.relatorio.RelatoriosService;

@Service
public class MenuTabelasService {
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService;
	private final RelatoriosService relatorioService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	public MenuTabelasService(CrudCargoService cargoService, CrudFuncionarioService funcionarioService,
			CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService, RelatoriosService relatorioService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeDeTrabalhoService = unidadeDeTrabalhoService;
		this.relatorioService = relatorioService;
        this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public void mostrarMenu(Scanner scanner) {
		Boolean system = true;
		while (system) {
			Console.menuInicial();
			system = true;
			
			switch (scanner.nextInt()) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2:
				funcionarioService.inicial(scanner);
				break;
			case 3:
				unidadeDeTrabalhoService.inicial(scanner);
				break;
			case 4:
				relatorioService.inicial(scanner);
				break;
			case 5:
			    relatorioFuncionarioDinamico.inicial(scanner);
			    break;
			default:
				system = false;
				System.out.println("Encerrando sessão...");
				break;
			}
		}
	}

}

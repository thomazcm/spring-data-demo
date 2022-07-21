package br.com.alura.spring.data.service.helper;

public abstract class Console {
	public static void menuInicial() {
		System.out.println("Selecione o que deseja acessar:" + 
				"\n0- Sair" +
				"\n1- Cargos" + 
				"\n2- Funcionarios" +
				"\n3- Unidades de Trabalho" +
				"\n4- Menu de Relatórios" + 
				"\n5- Busca dinâmica de Funcionários");
	}
	
	public static void menuAcoesCrud(String titulo) {
		titulo(titulo);
		System.out.println("Selecione a ação que deseja executar:"
				+ "\n0 - Retornar ao menu inicial"
				+ "\n1 - Cadastrar"
				+ "\n2 - Atualizar"
				+ "\n3 - Visualizar"
				+ "\n4 - Deletar");
		separador();
	}

	public static void menuAcoesRelatorio() {
		System.out.println("Escolha o relatório que deseja visualizar:"
				+ "\n0- Retornar ao menu inicial"
				+ "\n1- Buscar Funcionário por nome"
				+ "\n2- Verificar funcionários contratados após uma data e com salários maiores que um valor"
				+ "\n3- Buscar funcionários contratados após uma data informada"
				+ "\n4- Mostrar nomes e salários dos funcionários");
	}

	public static void menuAtualizarFuncionario() {
		System.out.println("Escolha o que deseja atualizar"
				+ "\n1- Atualizar Dados cadastrais"
				+ "\n2- Atribuir um Cargo a este funcionário"
				+ "\n3- Atribuir uma nova Unidade de Trabalho para este funcionário");
	}

	public static void menuAtualizarUnidadeDeTrabalho() {
		System.out.println("Escolha o que deseja atualizar"
				+ "\n1- Atualizar nome da Unidade"
				+ "\n2- Atualizar o endereço da Unidade");
	}

	public static void titulo(String titulo) {
		System.out.println("============= "+titulo+" =================");
	}
	
	public static void separador() {
		System.out.println("======================================");
	}
	
	public static void perguntaConfirma() {
		System.out.println("Confirma?\n(1)Sim (0)Cancelar");
	}

	public static void perguntaData(String acao) {
		System.out.println("Informe a data de contratação dd/mm/yyyy" + acao);
	}

	public static void perguntaPagina() {
		System.out.println("Informe a página que você deseja visualizar");
	}

	public static void mensagemSucesso() {
		separador();
		System.out.println("==== Alteração salva com sucesso ====");
		separador();
	}

    public static void perguntaNome(String entidade, String acao) {
        System.out.println("Informe o nome para " + entidade + acao);
    }

    public static void perguntaId(String entidade, String acao) {
        System.out.println("Informe o Id para " + entidade + acao);
    }

    public static void perguntaCPF(String acao){
        System.out.println("Infome o CPF " + acao);
    }

    public static void perguntaSalario(String acao) {
        System.out.println("Informe qual o valor do salário " + acao);
    }
}

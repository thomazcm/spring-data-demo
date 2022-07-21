package br.com.alura.spring.data.service.crud;

import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public abstract class CrudService {
	public abstract void salvar(Scanner scanner);
	public abstract void atualizar(Scanner scanner);
	public abstract void visualizar(Scanner scanner);
	public abstract void deletar(Scanner scanner);
}

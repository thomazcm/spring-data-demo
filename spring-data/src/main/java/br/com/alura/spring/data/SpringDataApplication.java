package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.MenuTabelasService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
    private final MenuTabelasService menuService;

    public SpringDataApplication(MenuTabelasService menuService) {
        this.menuService = menuService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        menuService.mostrarMenu(scanner);
    }
}

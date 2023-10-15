package br.com.claraferreira.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Essa anotação marca a classe como uma aplicação Spring Boot.
public class ToDoListApplication {

    public static void main(String[] args) {
        // Inicia a aplicação Spring Boot quando este método é chamado.
        SpringApplication.run(ToDoListApplication.class, args);
    }
}


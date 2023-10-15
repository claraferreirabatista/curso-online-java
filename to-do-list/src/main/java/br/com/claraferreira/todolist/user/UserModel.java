package br.com.claraferreira.todolist.user;

import java.util.UUID;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data  // Usando o Lombok para gerar automaticamente getters, setters, toString, etc.

@Entity(name = "tb_users")  // Esta classe é uma entidade JPA mapeada para a tabela "tb_users" no banco de dados.

public class UserModel {

    @Id  // A seguir, declaração do campo ID como a chave primária.
    @GeneratedValue(generator = "UUID")  // O valor do campo ID será gerado automaticamente, usando UUID.

    private UUID id;  // Campo de identificação exclusiva para cada usuário.
    @Column(unique= true)  // A coluna 'username' deve ser única no banco de dados.

    private String username;  // Nome de usuário do usuário.
    private String name;  // Nome real do usuário.
    private String password;  // Senha do usuário.

    @CreationTimestamp  // Esta anotação indica que o campo 'createdAt' será preenchido automaticamente com a data e hora de criação.
    private LocalDateTime createdAt;  // Data e hora em que o usuário foi criado.
}

package br.com.claraferreira.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data  // Usando o Lombok para gerar automaticamente getters, setters, toString, etc.

@Entity(name = "tb_tasks")  // Esta classe é uma entidade JPA mapeada para a tabela "tb_tasks" no banco de dados.

public class TaskModel {
    @Id  // A seguir, declaração do campo ID como a chave primária.
    @GeneratedValue(generator = "UUID")  // O valor do campo ID será gerado automaticamente, usando UUID.

    private UUID id;  // Campo de identificação exclusiva para cada tarefa.
    private String name;  // Nome da tarefa.
    private String description;  // Descrição da tarefa.

    @Column(length = 50)  // Especifica o comprimento máximo da coluna 'title' no banco de dados.
    private String title;  // Título da tarefa.
    private LocalDateTime startAt;  // Data e hora de início da tarefa.
    private LocalDateTime endAt;  // Data e hora de término da tarefa.
    private String priority;  // Prioridade da tarefa.

    private UUID idUser;  // ID do usuário associado a esta tarefa.

    @CreationTimestamp  // Esta anotação indica que o campo 'createdAt' será preenchido automaticamente com a data e hora de criação.
    private LocalDateTime createdAt;  // Data e hora em que a tarefa foi criada.
}

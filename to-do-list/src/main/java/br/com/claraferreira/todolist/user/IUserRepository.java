package br.com.claraferreira.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    // Esta interface é responsável por definir um repositório JPA para a entidade UserModel,
    // onde UserModel é a entidade que será gerenciada pelo repositório e UUID é o tipo de dado do ID.

    UserModel findByUsername(String username);
    // Este método define uma consulta personalizada para encontrar um usuário pelo nome de usuário (username).
    // O Spring Data JPA gera automaticamente a consulta SQL apropriada com base no nome do método.
}

package br.com.claraferreira.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController  // Define que esta classe é um controlador Spring MVC.
@RequestMapping("/users")  // Define o caminho base para todas as rotas deste controlador.

public class UserController {

    @Autowired  // Injeta a instância do IUserRepository para permitir o acesso ao banco de dados.
    private IUserRepository userRepository;

    @PostMapping("/")  // Mapeia a rota POST para a criação de um novo usuário.
    public ResponseEntity create(@RequestBody UserModel userModel) {  // Método que recebe uma solicitação POST com um objeto UserModel no corpo da solicitação.

        // Busca um usuário com o mesmo nome de usuário no banco de dados.
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário existe");  // Se o usuário já existe, retorna um status HTTP 400 (Bad Request).
        }

        // Hash da senha do usuário usando o algoritmo BCrypt.
        var passwordHashred = BCrypt.withDefaults()
            .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);  // Define a senha do usuário como o hash gerado.

        // Salva o usuário no banco de dados.
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);  // Retorna um status HTTP 201 (Created) com o usuário criado.
    }
}

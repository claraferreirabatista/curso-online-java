package br.com.claraferreira.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.claraferreira.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskControlller {

    @Autowired
    private ITaskRepository taskRepository;

    // Definição do endpoint POST para criar uma tarefa
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {

        // Obtém o ID do usuário a partir do HttpServletRequest
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        // Obtém a data e hora atual
        var currentDate = LocalDateTime.now();

        // Verifica se a data atual é posterior à data de início ou à data de término da
        // tarefa
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            // Retorna uma resposta de erro com status 400 (Bad Request) e uma mensagem
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data precisa ser maior");
        }

        // Verifica se a data de início da tarefa é posterior à data de término
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            // Retorna uma resposta de erro com status 400 (Bad Request) e uma mensagem
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data precisa ser menor");
        }

        // Salva a tarefa no repositório e retorna uma resposta com status 200 (OK)
        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    // Definição do endpoint GET para listar as tarefas
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        // Obtém o ID do usuário a partir do HttpServletRequest
        var idUser = request.getAttribute("idUser");
        // Obtém as tarefas associadas ao usuário
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    // Definição do endpoint PUT para atualizar uma tarefa com base no seu ID
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
        var task = this.taskRepository.findById(id).orElse(null);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Essa tarefa tem ID incorreto");
        }

        var idUser = request.getAttribute("idUser");
        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Você não é autorizado para isso");
        }
        Utils.copyNonNullProperties(taskModel, task);

        var taskUpdated = this.taskRepository.save(task);

        return ResponseEntity.ok().body(taskUpdated);
    }
}

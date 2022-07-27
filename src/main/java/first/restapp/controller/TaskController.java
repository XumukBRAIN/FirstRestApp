package first.restapp.controller;

import first.restapp.model.Person;
import first.restapp.model.Task;
import first.restapp.service.TaskService;
import first.restapp.util.*;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/one/{id}")
    public Task findOne(@PathVariable("id") int id){
        return taskService.findOne(id);
    }

    @GetMapping("/all")
    public List<Task> getAll(){
        return taskService.findAll();
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createTask(@RequestBody @Valid Task task, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List <FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors){
                errorMessage.
                        append(error.getField()).
                        append(" - ").
                        append(error.getDefaultMessage()).
                        append(";");
            }
            throw new PersonNotCreatedException(errorMessage.toString());
        }
        taskService.save(task);
        // Отправляем HTTP ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable("id") int id){
        taskService.delete(id);
    }

    @ExceptionHandler
    private ResponseEntity<TaskErrorResponse> handlerException(TaskNotFoundException e){
        TaskErrorResponse taskErrorResponse = new TaskErrorResponse(
                "Task with this id not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(taskErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<TaskErrorResponse> handleException(TaskNotCreatedException e){

        TaskErrorResponse taskErrorResponse = new TaskErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(taskErrorResponse, HttpStatus.BAD_REQUEST);
    }

}

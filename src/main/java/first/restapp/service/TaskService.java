package first.restapp.service;

import first.restapp.model.Task;
import first.restapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task save(Task task){
        return taskRepository.save(task);
    }

    @Transactional
    public void delete(int id){
        taskRepository.deleteById(id);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findOne(int id) {
        return taskRepository.findById(id).get();
    }

}

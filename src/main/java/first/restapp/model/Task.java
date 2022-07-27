package first.restapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int task_id;

    @Column(name = "task_name")
    @NotEmpty
    private String task_name;

    @Column(name = "description")
    private String description;

    @Column(name = "person_name")
    @JoinColumn(table = "person", name = "person_name")
    private String person_name;

    public Task(){

    }

    public Task(int task_id, String task_name, String description, String person_name) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.description = description;
        this.person_name = person_name;
    }

    public Task(String task_name, String description, String person_name) {
        this.task_name = task_name;
        this.description = description;
        this.person_name = person_name;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }
}

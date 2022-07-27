package first.restapp.util;

public class TaskNotCreatedException extends RuntimeException{
    public TaskNotCreatedException(String mssg){
        super(mssg);
    }
}

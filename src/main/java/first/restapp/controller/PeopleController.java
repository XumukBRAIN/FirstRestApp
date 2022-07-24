package first.restapp.controller;

import first.restapp.model.Person;
import first.restapp.service.PeopleService;
import first.restapp.util.PersonErrorResponse;
import first.restapp.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody над каждым методом. То есть метод будет возвращать не html-отображение, а какие-то данные.
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/all")
    public List<Person> getPeople(){
        return peopleService.findAll(); // Jackson конвертирует эти объекты в JSON
    }

    @GetMapping("/one/{id}")
    public Person getPerson(@PathVariable ("id") int id){
        // Статус - 200
        return peopleService.findOne(id); // Jackson конвертирует эти объекты в JSON
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                "Person with this id was not found",
                System.currentTimeMillis()
        );
        // Статус - 404 NOT_FOUND
        // В HTTP ответе тело ответа (personErrorResponse) и статус в заголовке
        return new ResponseEntity<>(personErrorResponse, HttpStatus.NOT_FOUND);
    }

}

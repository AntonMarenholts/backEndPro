package cohort5860.lesson_10_simple_web_service.controler;

import cohort5860.lesson_10_simple_web_service.dto.PersonDto;
import cohort5860.lesson_10_simple_web_service.dto.PersonFeedDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimpleServiceControler {

    @GetMapping("/hello")
    public String hello(@RequestParam("title") String name) {
        return "Hello " + name;
    }

    @PostMapping("/hello")
    public String hello(@RequestBody PersonDto person) {
        return "Hello " + person.getFirstName() + " " + person.getLastName();
    }

    @PostMapping("/feed")
    public PersonFeedDto personEating(@RequestBody PersonDto person) {
        return PersonFeedDto.builder()
                .fullName(person.getFirstName() + " " + person.getLastName())
                .foods(List.of("pizza", "burger", "Fries"))
                .build();
    }

    @PostMapping("/feed/{delicates}")
    public PersonFeedDto personEating(@RequestBody PersonDto person, @PathVariable("delicates") String food) {
        return PersonFeedDto.builder()
                .fullName(person.getFirstName() + " " + person.getLastName())
                .food(food)
                .build();
    }
}

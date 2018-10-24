package example;

import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot";
    }

    @GetMapping("/hello/employee")
    public Employee one() {
        return new Employee(1, "James");
    }

    @GetMapping("/hello/employee/{id}")
    public Employee oneSearchBy(@PathVariable Integer id) {
        return new Employee(id, "John");
    }

    @PostMapping("/hello/employee")
    public String newEmployee(@RequestBody Employee employee) {
        return "Employee " + employee.getName();
    }

    @PostMapping("/hello/employee/resource")
    public Resource<Employee> newResource(@RequestBody Employee employee) {
        return new Resource(employee,
                linkTo(methodOn(HelloController.class).oneSearchBy(employee.getId())).withSelfRel());
    }
}

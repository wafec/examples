package example;

import lombok.Data;

@Data
public class Employee {
    private Integer id;
    private String name;

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

package example;

import lombok.Data;

@Data
public class UserAddView {
    private String name;
    private String email;

    public UserAddView(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

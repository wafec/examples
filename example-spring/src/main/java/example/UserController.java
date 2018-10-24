package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public @ResponseBody String addNewUser(@RequestBody UserAddView newUser) {
        User user = new User(newUser.getName(), newUser.getEmail());
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}

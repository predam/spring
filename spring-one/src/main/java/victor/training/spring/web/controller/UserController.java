package victor.training.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import victor.training.spring.web.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("users/count")
    public long count() {
        return service.countUsers();
    }
    @GetMapping("users/create")
    public void create() {
        service.createUser();
    }
}

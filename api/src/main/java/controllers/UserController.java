package controllers;


import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> result = repository.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<User>> findAll(Pageable pageable){
        Page<User> result = repository.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/seach-salary")
    public ResponseEntity<Page<User>> searcBySalary(@RequestParam(defaultValue = "0") Double minSalary,
                                                    @RequestParam(defaultValue = "1000000000000") Double maxSalary,
                                                    Pageable pageable){
        Page<User> result = repository.findBySalaryBetween(minSalary, maxSalary, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/seach-name")
    public ResponseEntity<Page<User>> searchByName(
            @RequestParam(defaultValue = "") String name,
            Pageable pageable){
        Page<User> result = repository.findByNameContainingIgnoreCase(name, pageable);
        return ResponseEntity.ok(result);
    }
}

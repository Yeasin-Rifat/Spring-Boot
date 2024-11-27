package com.example.crud_on_json;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private DataService DataService;

    @GetMapping
    public String getStudent() {
        return "Get Method is working";
    }

    @PostMapping
    public String postStudent() {
        return "Post Method is working";
    }

    @PutMapping
    public String putStudent() {
        return "Put Method is working";
    }

    @DeleteMapping
    public String deleteStudent() {
        return "Delete Method is working";
    }

    @GetMapping("/fetchData")
    public String fetchData() {
        return DataService.fetchJsonData();
    }
}




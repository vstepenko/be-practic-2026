package ua.edu.duan.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class WarehouseController {
    @GetMapping("/hello-world")
    public String sayHelloWorld() {
        return "Hello World - advanced logic";
    }


    @GetMapping("/warehouse")
    public String getItems() {
        return "Warhouse - advanced logic";
    }
}

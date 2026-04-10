package ua.edu.duan.warehouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.duan.warehouse.dao.entity.CatalogEntity;
import ua.edu.duan.warehouse.dao.repository.CatalogRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class WarehouseController {

    private final CatalogRepository catalogRepository;
    ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("/hello-world")
    public String sayHelloWorld() {
        return "Hello World - advanced logic";
    }


    @GetMapping("/warehouse")
    public String getItems() throws JsonProcessingException {
        List<CatalogEntity> catalogEntityList=  catalogRepository.findAll();

        return objectMapper.writeValueAsString(catalogEntityList);


    }
}


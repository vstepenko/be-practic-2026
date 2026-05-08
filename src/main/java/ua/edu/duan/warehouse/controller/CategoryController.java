package ua.edu.duan.warehouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.duan.warehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/categories")
    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(categoryService.getAll());
    }

    @GetMapping("/categories/{id}")
    public String getById(@PathVariable String id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(categoryService.getById(id));
    }

    @PostMapping("/categories")
    public String create(@RequestBody CategoryDto dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(categoryService.create(dto));
    }

    @PutMapping("/categories/{id}")
    public String update(@PathVariable String id, @RequestBody CategoryDto dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(categoryService.update(id, dto));
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable String id) {
        categoryService.delete(id);
    }

}

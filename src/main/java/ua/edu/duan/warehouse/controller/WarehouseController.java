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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.duan.warehouse.service.CatalogService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WarehouseController {

    private final CatalogService catalogService;
    private final ObjectMapper objectMapper;

    @GetMapping("/hello-world")
    public String sayHelloWorld() {
        return "Hello World - advanced logic";
    }

    @GetMapping("/warehouse")
    public String getItems() throws JsonProcessingException {
        return objectMapper.writeValueAsString(catalogService.getAllItems());
    }

    @PostMapping("/item")
    public void addItem(@RequestBody ItemDto itemDto) {
        catalogService.addItem(itemDto);
    }

    @PutMapping("/item/{id}")
    public void updateItem(@PathVariable String id, @RequestBody ItemDto itemDto) {
        catalogService.updateItem(id, itemDto);
    }

    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable String id) {
        catalogService.deleteItem(id);
    }

    @GetMapping("/warehouse/search")
    public String searchItems(@RequestParam String prefix) throws JsonProcessingException {
        return objectMapper.writeValueAsString(catalogService.searchItemsByPrefix(prefix));
    }
}

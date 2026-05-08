package ua.edu.duan.warehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.edu.duan.warehouse.dao.entity.CatalogEntity;
import ua.edu.duan.warehouse.service.CatalogService;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WarehouseController.class)
class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CatalogService catalogService;

    private ItemDto itemDto;
    private CatalogEntity catalogEntity;

    @BeforeEach
    void setUp() {
        itemDto = new ItemDto();
        itemDto.setItemName("Widget");
        itemDto.setDescription("A useful widget");
        itemDto.setIcon("https://example.com/icon.png");
        itemDto.setAttributes("{\"color\": \"blue\"}");

        catalogEntity = new CatalogEntity();
        catalogEntity.setId(UUID.randomUUID().toString());
        catalogEntity.setItemName("Widget");
        catalogEntity.setDescription("A useful widget");
        catalogEntity.setIcon("https://example.com/icon.png");
        catalogEntity.setAttributes("{\"color\": \"blue\"}");
    }

    @Test
    void sayHelloWorld_shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/api/hello-world"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World - advanced logic"));
    }

    @Test
    void getItems_shouldReturnAllItemsAsJson() throws Exception {
        when(catalogService.getAllItems()).thenReturn(List.of(catalogEntity));

        mockMvc.perform(get("/api/warehouse"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(catalogEntity.getId()))
                .andExpect(jsonPath("$[0].itemName").value("Widget"));

        verify(catalogService).getAllItems();
    }

    @Test
    void getItems_shouldReturnEmptyArrayWhenNoItems() throws Exception {
        when(catalogService.getAllItems()).thenReturn(List.of());

        mockMvc.perform(get("/api/warehouse"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(catalogService).getAllItems();
    }

    @Test
    void addItem_shouldCallServiceAndReturnOk() throws Exception {
        doNothing().when(catalogService).addItem(any(ItemDto.class));

        mockMvc.perform(post("/api/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDto)))
                .andExpect(status().isOk());

        verify(catalogService).addItem(any(ItemDto.class));
    }

    @Test
    void updateItem_shouldCallServiceAndReturnOk() throws Exception {
        String id = "test-id";
        doNothing().when(catalogService).updateItem(anyString(), any(ItemDto.class));

        mockMvc.perform(put("/api/item/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDto)))
                .andExpect(status().isOk());

        verify(catalogService).updateItem(eq(id), any(ItemDto.class));
    }

    @Test
    void deleteItem_shouldCallServiceAndReturnOk() throws Exception {
        String id = "test-id";
        doNothing().when(catalogService).deleteItem(id);

        mockMvc.perform(delete("/api/item/{id}", id))
                .andExpect(status().isOk());

        verify(catalogService).deleteItem(id);
    }

    @Test
    void searchItems_shouldReturnMatchingItems() throws Exception {
        CatalogEntity searchResult = new CatalogEntity();
        searchResult.setId("1");
        searchResult.setItemName("Widget");

        when(catalogService.searchItemsByPrefix("wid")).thenReturn(List.of(searchResult));

        mockMvc.perform(get("/api/warehouse/search")
                        .param("prefix", "wid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].itemName").value("Widget"));

        verify(catalogService).searchItemsByPrefix("wid");
    }

    @Test
    void searchItems_shouldReturnEmptyArrayWhenNoMatch() throws Exception {
        when(catalogService.searchItemsByPrefix("xyz")).thenReturn(List.of());

        mockMvc.perform(get("/api/warehouse/search")
                        .param("prefix", "xyz"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(catalogService).searchItemsByPrefix("xyz");
    }
}

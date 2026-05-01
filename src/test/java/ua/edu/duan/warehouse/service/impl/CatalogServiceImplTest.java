package ua.edu.duan.warehouse.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.edu.duan.warehouse.controller.ItemDto;
import ua.edu.duan.warehouse.dao.entity.CatalogEntity;
import ua.edu.duan.warehouse.dao.repository.CatalogRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogServiceImplTest {

    @Mock
    private CatalogRepository catalogRepository;

    @InjectMocks
    private CatalogServiceImpl catalogService;

    private ItemDto itemDto;

    @BeforeEach
    void setUp() {
        itemDto = new ItemDto();
        itemDto.setItemName("Widget");
        itemDto.setDescription("A useful widget");
        itemDto.setIcon("https://example.com/icon.png");
        itemDto.setAttributes("{\"color\": \"blue\"}");
    }

    @Test
    void getAllItems_shouldReturnListOfEntities() {
        CatalogEntity entity1 = new CatalogEntity();
        entity1.setId(UUID.randomUUID().toString());
        entity1.setItemName("Widget");

        CatalogEntity entity2 = new CatalogEntity();
        entity2.setId(UUID.randomUUID().toString());
        entity2.setItemName("Gadget");

        when(catalogRepository.findAll()).thenReturn(List.of(entity1, entity2));

        List<CatalogEntity> result = catalogService.getAllItems();

        assertEquals(2, result.size());
        verify(catalogRepository).findAll();
    }

    @Test
    void getAllItems_shouldReturnEmptyListWhenNoItems() {
        when(catalogRepository.findAll()).thenReturn(List.of());

        List<CatalogEntity> result = catalogService.getAllItems();

        assertTrue(result.isEmpty());
        verify(catalogRepository).findAll();
    }

    @Test
    void addItem_shouldSaveEntityWithGeneratedId() {
        catalogService.addItem(itemDto);

        ArgumentCaptor<CatalogEntity> captor = ArgumentCaptor.forClass(CatalogEntity.class);
        verify(catalogRepository).save(captor.capture());

        CatalogEntity saved = captor.getValue();
        assertNotNull(saved.getId());
        assertEquals("Widget", saved.getItemName());
        assertEquals("A useful widget", saved.getDescription());
        assertEquals("https://example.com/icon.png", saved.getIcon());
        assertEquals("{\"color\": \"blue\"}", saved.getAttributes());
    }

    @Test
    void addItem_shouldGenerateUuidFormatId() {
        catalogService.addItem(itemDto);

        ArgumentCaptor<CatalogEntity> captor = ArgumentCaptor.forClass(CatalogEntity.class);
        verify(catalogRepository).save(captor.capture());

        String id = captor.getValue().getId();
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

    @Test
    void updateItem_shouldUpdateExistingEntity() {
        String id = "test-id";
        CatalogEntity existingEntity = new CatalogEntity();
        existingEntity.setId(id);
        existingEntity.setItemName("Old Name");

        when(catalogRepository.findById(id)).thenReturn(Optional.of(existingEntity));

        catalogService.updateItem(id, itemDto);

        assertEquals("Widget", existingEntity.getItemName());
        assertEquals("A useful widget", existingEntity.getDescription());
        assertEquals("https://example.com/icon.png", existingEntity.getIcon());
        assertEquals("{\"color\": \"blue\"}", existingEntity.getAttributes());
        verify(catalogRepository).save(existingEntity);
    }

    @Test
    void updateItem_shouldThrowWhenEntityNotFound() {
        String id = "non-existent-id";
        when(catalogRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> catalogService.updateItem(id, itemDto));

        assertEquals("Item not found", exception.getMessage());
        verify(catalogRepository, never()).save(any());
    }

    @Test
    void deleteItem_shouldDeleteById() {
        String id = "test-id";

        catalogService.deleteItem(id);

        verify(catalogRepository).deleteById(id);
    }

    @Test
    void searchItemsByPrefix_shouldReturnMatchingItems() {
        CatalogEntity entity = new CatalogEntity();
        entity.setId("1");
        entity.setItemName("Widget");

        when(catalogRepository.findByItemNameStartingWithIgnoreCase("wid"))
                .thenReturn(List.of(entity));

        List<CatalogEntity> result = catalogService.searchItemsByPrefix("wid");

        assertEquals(1, result.size());
        assertEquals("Widget", result.get(0).getItemName());
        verify(catalogRepository).findByItemNameStartingWithIgnoreCase("wid");
    }

    @Test
    void searchItemsByPrefix_shouldReturnEmptyWhenNoMatch() {
        when(catalogRepository.findByItemNameStartingWithIgnoreCase("xyz"))
                .thenReturn(List.of());

        List<CatalogEntity> result = catalogService.searchItemsByPrefix("xyz");

        assertTrue(result.isEmpty());
        verify(catalogRepository).findByItemNameStartingWithIgnoreCase("xyz");
    }
}

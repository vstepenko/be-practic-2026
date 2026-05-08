package ua.edu.duan.warehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.duan.warehouse.controller.ItemDto;
import ua.edu.duan.warehouse.dao.entity.CatalogEntity;
import ua.edu.duan.warehouse.dao.repository.CatalogRepository;
import ua.edu.duan.warehouse.service.CatalogService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Override
    public List<CatalogEntity> getAllItems() {
        return catalogRepository.findAll();
    }

    @Override
    public void addItem(ItemDto itemDto) {
        CatalogEntity entity = new CatalogEntity();
        entity.setId(UUID.randomUUID().toString());
        mapDtoToEntity(itemDto, entity);
        catalogRepository.save(entity);
    }

    @Override
    public void updateItem(String id, ItemDto itemDto) {
        CatalogEntity entity = catalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        mapDtoToEntity(itemDto, entity);
        catalogRepository.save(entity);
    }

    @Override
    public void deleteItem(String id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public List<CatalogEntity> searchItemsByPrefix(String prefix) {
        return catalogRepository.findByItemNameStartingWithIgnoreCase(prefix);
    }

    private void mapDtoToEntity(ItemDto itemDto, CatalogEntity entity) {
        entity.setItemName(itemDto.getItemName());
        entity.setDescription(itemDto.getDescription());
        entity.setIcon(itemDto.getIcon());
        entity.setAttributes(itemDto.getAttributes());
    }
}

package ua.edu.duan.warehouse.service;

import ua.edu.duan.warehouse.controller.ItemDto;
import ua.edu.duan.warehouse.dao.entity.CatalogEntity;

import java.util.List;

public interface CatalogService {
    List<CatalogEntity> getAllItems();
    void addItem(ItemDto itemDto);
    void updateItem(String id, ItemDto itemDto);
    void deleteItem(String id);
    List<CatalogEntity> searchItemsByPrefix(String prefix);
}

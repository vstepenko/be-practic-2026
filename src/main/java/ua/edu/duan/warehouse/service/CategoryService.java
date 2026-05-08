package ua.edu.duan.warehouse.service;

import ua.edu.duan.warehouse.controller.CategoryDto;
import ua.edu.duan.warehouse.dao.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> getAll();

    CategoryEntity getById(String id);

    CategoryEntity create(CategoryDto dto);

    CategoryEntity update(String id, CategoryDto dto);

    void delete(String id);

}

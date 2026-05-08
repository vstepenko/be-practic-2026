package ua.edu.duan.warehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.duan.warehouse.controller.CategoryDto;
import ua.edu.duan.warehouse.dao.entity.CategoryEntity;
import ua.edu.duan.warehouse.dao.repository.CategoryRepository;
import ua.edu.duan.warehouse.service.CategoryService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public CategoryEntity create(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(UUID.randomUUID().toString());
        mapDtoToEntity(dto, entity);
        return categoryRepository.save(entity);
    }

    @Override
    public CategoryEntity update(String id, CategoryDto dto) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        mapDtoToEntity(dto, entity);
        return categoryRepository.save(entity);
    }

    @Override
    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

    private void mapDtoToEntity(CategoryDto dto, CategoryEntity entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }

}

package ua.edu.duan.warehouse.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.duan.warehouse.dao.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
}

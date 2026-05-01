package ua.edu.duan.warehouse.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.duan.warehouse.dao.entity.CatalogEntity;

import java.util.List;

public interface CatalogRepository extends JpaRepository<CatalogEntity, String> {

    List<CatalogEntity> findByItemNameStartingWithIgnoreCase(String itemName);

}

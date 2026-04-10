package ua.edu.duan.warehouse.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.duan.warehouse.dao.entity.CatalogEntity;

public interface CatalogRepository extends JpaRepository <CatalogEntity,String>{

}

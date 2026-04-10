package ua.edu.duan.warehouse.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "catalog")

public class CatalogEntity {

    @Id
    private String id;

    @Column(name = "item_name")
    private String itemName;

    private String description;

    private String icon;

}

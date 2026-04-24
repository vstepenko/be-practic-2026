package ua.edu.duan.warehouse.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {


    private String itemName;

    private String description;

    private String icon;

    private String attributes;

}

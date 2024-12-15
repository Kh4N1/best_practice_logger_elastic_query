package com.example.mobileapp.dto;

import lombok.Data;
import java.util.List;

@Data
public class SearchCriteria {
    private List<String> manufacturers;
    private List<String> colors;
    private List<Integer> storageCapacities;
    private Boolean inStockOnly;
}
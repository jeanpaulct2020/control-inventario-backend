package com.mycompany.service;

import com.mycompany.model.Category;
import com.mycompany.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    ResponseEntity<CategoryResponseRest> search();

    ResponseEntity<CategoryResponseRest> searchById(Long idCategory);

    ResponseEntity<CategoryResponseRest> create(Category category); //debe ir un category request no el modelo
}

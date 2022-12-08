package com.mycompany.service;

import com.mycompany.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    ResponseEntity<CategoryResponseRest> search();

    ResponseEntity<CategoryResponseRest> searchById(Long idCategory);
}

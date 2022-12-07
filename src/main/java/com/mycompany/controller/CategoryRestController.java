package com.mycompany.controller;

import com.mycompany.response.CategoryResponseRest;
import com.mycompany.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CategoryRestController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("categories")
    public ResponseEntity<CategoryResponseRest> searchCategories(){
        ResponseEntity<CategoryResponseRest> response = categoryService.search();
        return response;
    }
}

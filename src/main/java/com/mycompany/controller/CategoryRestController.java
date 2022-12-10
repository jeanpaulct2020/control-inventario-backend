package com.mycompany.controller;

import com.mycompany.model.Category;
import com.mycompany.response.CategoryResponseRest;
import com.mycompany.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CategoryRestController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * get all categeories
     * @return
     */
    @GetMapping("categories")
    public ResponseEntity<CategoryResponseRest> searchCategories(){
        ResponseEntity<CategoryResponseRest> response = categoryService.search();
        return response;
    }

    /**
     * get categories by Id
     * @param idCategory
     * @return
     */
    @GetMapping("categories/{idCategory}")
    public ResponseEntity<CategoryResponseRest> searchCategoryById(@PathVariable("idCategory") Long idCategory){
        ResponseEntity<CategoryResponseRest> response = categoryService.searchById(idCategory);
        return response;
    }

    /**
     * Save a category
     * @param category
     * @return
     */
    @PostMapping("categories")
    public ResponseEntity<CategoryResponseRest> createCategory(@RequestBody Category category){
        ResponseEntity<CategoryResponseRest> response = categoryService.create(category);
        return response;
    }

    @PutMapping("categories/{idCategory}")
    public ResponseEntity<CategoryResponseRest> updateCategory(@RequestBody Category category, @PathVariable("idCategory") Long idCategory){
        ResponseEntity<CategoryResponseRest> response = categoryService.update(idCategory, category);
        return response;
    }
}

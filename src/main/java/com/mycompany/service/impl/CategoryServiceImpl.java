package com.mycompany.service.impl;


import com.mycompany.model.Category;
import com.mycompany.repository.ICategoryRepository;
import com.mycompany.response.CategoryResponseRest;
import com.mycompany.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        //declaramos la respuesta
        CategoryResponseRest response = new CategoryResponseRest();

        try{
            //obtenemos todas las categorias
            List<Category> categories = categoryRepository.findAll();

            //seteamos las categorias a la respuesta
            response.getCategoryResponse().setCategory(categories);
            response.setMetadata("Respuesta Ok", "00", "Respuesta exitosa");


        }catch (Exception e){
            response.setMetadata("Respuesta No Ok", "-1", "Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}

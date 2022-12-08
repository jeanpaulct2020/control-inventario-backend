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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public ResponseEntity<CategoryResponseRest> searchById(Long idCategory) {
        //declaramos la respuesta
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> listCategories = new ArrayList<>();
        try{
            //obtenemos la cateogira
            Optional<Category> category = categoryRepository.findById(idCategory);

            if(category.isPresent()){
                //agregamos a la lista de categorias para setearlo en la respuesta
                listCategories.add(category.get());
                //seteamos las categorias a la respuesta
                response.getCategoryResponse().setCategory(listCategories);
                response.setMetadata("Respuesta Ok", "00", "Categoria encontrada");
            }else{
                response.setMetadata("Respuesta No Ok", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            response.setMetadata("Respuesta No Ok", "-1", "Error al consultar por ID");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}

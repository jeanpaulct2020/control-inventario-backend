package com.mycompany.service.impl;


import antlr.StringUtils;
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
    @Transactional(readOnly = true)
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

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> create(Category category) {
        //declaramos la respuesta
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> listCategories = new ArrayList<>();
        try{
            //seteamos y guardamos la cateogira
            Category categoryToDB = categoryRepository.save(category);

            //validamos
            if(categoryToDB != null){
                listCategories.add(categoryToDB);
                response.getCategoryResponse().setCategory(listCategories);
                response.setMetadata("Respuesta Ok", "00", "Categoria grabada");
            }else{
                response.setMetadata("Respuesta No Ok", "-1", "Categoria no grabada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            response.setMetadata("Respuesta No Ok", "-1", "Error al grabar categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryResponseRest> update(Long idCategory, Category category) {
        //declaramos la respuesta
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> listCategories = new ArrayList<>();
        try{

            //validamos si tiene id la categoria

            Optional<Category> categoryOpt = categoryRepository.findById(idCategory);
            if(categoryOpt.isPresent()){
                //seteamos y guardamos la categoria encontrada
                categoryOpt.get().setName(category.getName());
                categoryOpt.get().setDescription(category.getDescription());
                Category categoryToUpdateDB = categoryRepository.save(categoryOpt.get());
                
                if(categoryToUpdateDB != null) {
                    listCategories.add(categoryToUpdateDB);
                    response.getCategoryResponse().setCategory(listCategories);
                    response.setMetadata("Respuesta Ok", "00", "Categoria actualizada");
                }else{
                    response.setMetadata("Respuesta No Ok", "-1", "Categoria no actualizada");
                    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
                
            }else{
                response.setMetadata("Respuesta No Ok", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            response.setMetadata("Respuesta No Ok", "-1", "Error al grabar categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> delete(Long idCategory) {
        //declaramos la respuesta
        CategoryResponseRest response = new CategoryResponseRest();
        try{
            //obtenemos la cateogira
            Optional<Category> category = categoryRepository.findById(idCategory);
            if(category.isPresent()){
               categoryRepository.deleteById(idCategory);
               response.setMetadata("Respuesta Ok", "00", "Categoria eliminada");
            }else{
                response.setMetadata("Respuesta No Ok", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            response.setMetadata("Respuesta No Ok", "-1", "Error al eliminar categoria por ID");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}

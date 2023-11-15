package com.innodev.shoppinglistproject.service;

import com.innodev.shoppinglistproject.entity.ShoppingItem;
import com.innodev.shoppinglistproject.repo.ShoppingListRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShoppingListService {


    private final ShoppingListRepo shoppingListRepo;

    @Autowired
    public ShoppingListService(ShoppingListRepo shoppingListRepo) {
        this.shoppingListRepo = shoppingListRepo;
    }

    public ResponseEntity<List<ShoppingItem>> getAllItems() throws Exception {
        try{
            return new ResponseEntity<>(shoppingListRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error while fetching all data from shopping list DB {} : ",e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ShoppingItem>> getItemsByCategoryName(String category) {
        try {
            List<ShoppingItem> itemList = shoppingListRepo.findByCategory(category);
            if(itemList.isEmpty()){
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(itemList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while fetching data from the database by category name: " + e.getMessage(), e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addItems(ShoppingItem shoppingItem) {
        try{
            shoppingListRepo.save(shoppingItem);
            log.info("new items save successfully to DB{} : ", shoppingItem);
            return new ResponseEntity<>("New items saved successfully to DB ",HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Error while saving items to db {} : ", e.getMessage());
            return new ResponseEntity<>("Error while saving data to DB ", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteItemsFromCart(long id) {
        try{
            boolean itemsPresent = shoppingListRepo.existsById(id);
            if(itemsPresent){
                shoppingListRepo.deleteById(id);
                return new ResponseEntity<>("Items is delete with id : "+id,HttpStatus.OK);
            }
            return new ResponseEntity<>("Items with id : " + id + " not found ",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error("Error while deleting items from cart {}  : ", e.getMessage());
            return new ResponseEntity<>("Error while deleting items from cart : ", HttpStatus.BAD_REQUEST);
        }
    }
}

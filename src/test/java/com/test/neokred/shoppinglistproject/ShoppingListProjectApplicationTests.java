package com.test.neokred.shoppinglistproject;

import com.test.neokred.shoppinglistproject.entity.ShoppingItem;
import com.test.neokred.shoppinglistproject.repo.ShoppingListRepo;
import com.test.neokred.shoppinglistproject.service.ShoppingListService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShoppingListProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @InjectMocks
    private ShoppingListService shoppingListService;

    @Mock
    private ShoppingListRepo shoppingListRepo;

    @Before("")
    public void setUp() {
        // Setup your mocks or test data here
    }

    @Test
    public void testGetAllItems() throws Exception {
        List<ShoppingItem> shoppingItemList = new ArrayList<>();
        when(shoppingListRepo.findAll()).thenReturn(shoppingItemList);
        ResponseEntity<List<ShoppingItem>> response = shoppingListService.getAllItems();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shoppingItemList, response.getBody());
    }

    @Test
    public void testGetItemsByCategoryName() {
        String category = "";
        List<ShoppingItem> shoppingItemList = new ArrayList<>();
        when(shoppingListRepo.findByCategory(category)).thenReturn(shoppingItemList);
        ResponseEntity<List<ShoppingItem>> response = shoppingListService.getItemsByCategoryName(category);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shoppingItemList, response.getBody());
    }

    @Test
    public void testAddItems() {
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setId(111);
        shoppingItem.setName("Iphone 15 pro max");
        shoppingItem.setPrice(159000.99);
        shoppingItem.setCategory("Mobile");
        when(shoppingListRepo.save(shoppingItem)).thenReturn(shoppingItem);
        ResponseEntity<String> response = shoppingListService.addItems(shoppingItem);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("New items saved successfully to DB ", response.getBody());
    }

    @Test
    public void testDeleteItemsFromCart() {
        long itemId = 1L;
        when(shoppingListRepo.existsById(itemId)).thenReturn(true);
        ResponseEntity<String> response = shoppingListService.deleteItemsFromCart(itemId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Items is delete with id : " + itemId, response.getBody());
    }

}

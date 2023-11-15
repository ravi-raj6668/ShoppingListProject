package com.innodev.shoppinglistproject.controller;

import com.innodev.shoppinglistproject.entity.ShoppingItem;
import com.innodev.shoppinglistproject.service.ShoppingListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shoppingList")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/home")
    public ResponseEntity<String> getHomePage() {
        return new ResponseEntity<>("Welcome to Shopping List App", HttpStatus.OK);
    }

    @GetMapping(value = "/getAllItems")
    @Operation(
            security = @SecurityRequirement(name = "basicAuth"),
            summary = "Showing all items of shopping list", description = "This API is used to show all items in the shopping list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success, Showing all items from shopping List"),
            @ApiResponse(responseCode = "401", description = "Not authorized !!"),
            @ApiResponse(responseCode = "500", description = "Error !!")

    })
    public ResponseEntity<List<ShoppingItem>> getAllItems() throws Exception {
        return shoppingListService.getAllItems();
    }

    @GetMapping("getItemsByCategory/{category}")
    @Operation(
            security = @SecurityRequirement(name = "basicAuth"),
            summary = "get items from shopping list by category!!", description = "This API is used to find items into the shopping list using category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success, Getting List Successfully !!"),
            @ApiResponse(responseCode = "401", description = "Not authorized !!"),
            @ApiResponse(responseCode = "500", description = "Error !!")

    })
    public ResponseEntity<List<ShoppingItem>> getItemsByCategory(@PathVariable(value = "category") String category) {
        return shoppingListService.getItemsByCategoryName(category);
    }

    @PostMapping(value = "/addItems", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            security = @SecurityRequirement(name = "basicAuth"),
            summary = "add items to shopping list.!!", description = "This API is used to add items into the shopping list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success, Items successfully added !!"),
            @ApiResponse(responseCode = "401", description = "Not authorized !!"),
            @ApiResponse(responseCode = "500", description = "Error !!")

    })
    public ResponseEntity<String> addItems(@RequestBody ShoppingItem shoppingItem) {
        return shoppingListService.addItems(shoppingItem);
    }

    @DeleteMapping(value = "/deleteItems/{id}")
    @Operation(  security = @SecurityRequirement(name = "basicAuth"),
            summary = "delete items from shopping list.!!", description = "This API is used to delete items from the shopping list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success, Items deleted successfully !!"),
            @ApiResponse(responseCode = "401", description = "Not authorized !!"),
            @ApiResponse(responseCode = "500", description = "Error !!")

    })
    public ResponseEntity<String> deleteItems(@PathVariable(value = "id") long id) {
        return shoppingListService.deleteItemsFromCart(id);
    }


}

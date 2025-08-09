/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.Arrays;
import java.util.List;
import models.Product;

/**
 *
 * @author seth
 */
public class ProductServiceImpl implements ProductService{

    @Override
    public List<Product> listar() {
        return Arrays.asList(
          new Product(1,"Monitor", "Monitor", 150000),
          new Product(2,"Laptop HP", "Laptop", 2500000),
          new Product(3,"Mouse Logitech", "Mouse", 85000)
        );
    }   
    
}

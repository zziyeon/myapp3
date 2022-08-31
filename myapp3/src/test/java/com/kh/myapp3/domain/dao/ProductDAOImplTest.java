package com.kh.myapp3.domain.dao;

import com.kh.myapp3.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class ProductDAOImplTest {
    @Autowired
    ProductDAO productDAO;

    @Test
    @DisplayName("상품등록")
    void save(){
        Product product = new Product();
        product.setPname("외장하드");
        product.setQuantity(1);
        product.setPrice(50000);

        Product saveProduct = productDAO.save(product);
        log.info("saveProduct = {}", saveProduct);
    }

    @Test
    @DisplayName("조회")
    void findById(){
        Long productId = 47l;
        Product findedProduct = productDAO.findById(productId);
        Assertions.assertThat(findedProduct.getPname())
                .isEqualTo("SSD");
        log.info("findedProduct = {}", findedProduct);
    }

    @Test
    @DisplayName("수정")
    void update(){
        Long productId = 47l;
        Product product = new Product();
        product.setProductId(productId);
        product.setPname("SSD");
        product.setQuantity(2);
        product.setPrice(35000);
        productDAO.update(productId,product);

        Product findedProduct = productDAO.findById(productId);
        Assertions.assertThat(findedProduct)
                .isEqualTo(product);
        log.info("findedProduct = {}", findedProduct);
    }

    @Test
    @DisplayName("삭제")
    void delete(){
        Long productId=47l;
        productDAO.delete(productId);
        Product findedProduct = productDAO.findById(productId);
        Assertions.assertThat(findedProduct)
                .isNull();
    }

    @Test
    @DisplayName("목록")
    void all(){
        List<Product> list = productDAO.findAll();
        Assertions.assertThat(list.size()).isEqualTo(11);
//        log.info("전체목록={}", list);
        //람다식
        list.stream().forEach(ele->{
            log.info("상품: {}", ele);
        });
        //향상된 for문
        for(Product p: list){
            log.info("상품: {}", p);
        }


    }
}

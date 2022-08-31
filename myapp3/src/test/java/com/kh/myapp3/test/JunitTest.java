package com.kh.myapp3.test;

import com.kh.myapp3.domain.Product;
import com.kh.myapp3.domain.dao.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//테스트 메소드의 실행 순서
//1) 문자와 숫자 소합으로 실행 순서를 결정
@Slf4j
@SpringBootTest
public class JunitTest {
    @Autowired
    private static ProductDAO productDAO;

    @BeforeAll
    static void beforeAll(){
//        for(int i=0; i<3; i++) {
//            Product product= new Product();
//            product.setPname("상품"+(i+1));
//            product.setQuantity(10+i);
//            product.setPrice(10000+(i*1000));
//            productDAO.save(product);
//        }
    }


    @Test
    void test1(){
        log.info("text1() called)");
    }
    @Test
    void test3(){
        log.info("text3() called");
        for(int i=0; i<10000;i++){}
    }
    @Test
    void test2(){
        log.info("text2() called");
    }
    @Test
    void test4(){
        log.info("text4() called");
    }

    @BeforeEach
    void beforeEach(){
        log.info("beforEach() called");
    }
    @AfterEach
    void afterEach(){
        log.info("afterEach() called");
    }

    @AfterAll
    static void afterAll(){
        log.info("afterAll() called");
    }
}

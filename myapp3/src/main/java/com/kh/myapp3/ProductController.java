package com.kh.myapp3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    //등록 양식
    @GetMapping
    public String saveForm(){

        return "product/saveForm";          //상품 등록 view
    }

    //등록 처리
    @PostMapping
    public String saver(){
        return "product/all.html";      //전체 목록 view
    }

}

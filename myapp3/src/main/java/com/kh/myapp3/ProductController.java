package com.kh.myapp3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "redirect:/product/1";      //상세 view
    }

    //상품 개별 조회
    @GetMapping("/{pid}")
    public String findByProductId(@PathVariable("pid") String pid){
        //db에서 상품조회
        return "product/detailForm";        //상세 view
    }

    //수정 양식
    @GetMapping("/{pid}/edit")
    public String updateForm(){
        return "product/updateForm";        //상품 수정 view
    }
    //수정 처리
    @PostMapping("/{pid}/edit")
    public String update(@PathVariable("pid") String pid){

        return "redifrect:/product/1";      //상품 상세 view
    }

}

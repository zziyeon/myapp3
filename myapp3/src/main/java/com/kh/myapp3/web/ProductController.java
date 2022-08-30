package com.kh.myapp3.web;

import com.kh.myapp3.domain.Product;
import com.kh.myapp3.domain.svc.ProductSVC;
import com.kh.myapp3.web.form.SaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductSVC productSVC;

    //등록 양식
    @GetMapping
    public String saveForm(){

        return "product/saveForm";          //상품 등록 view
    }

    //등록 처리
    @PostMapping
    public String save(SaveForm saveForm){
        log.info("saveForm:{}", saveForm);

        Product product = new Product();
        product.setPname(saveForm.getPname());
        product.setQuantity(saveForm.getQuantity());
        product.setPrice(saveForm.getPrice());

        Integer productId = productSVC.save(product);

        return "redirect:/product/"+productId;      //상세 view
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

    //삭제 처리
    @GetMapping("/{pid}/del")
    public String delete(){
        return "redirect:/product/all";         //전체 목록 view
    }

    // 목록 화면
    @GetMapping("/all")
    public String list(){

        return "product/all";               //전체 목록 view
    }

}

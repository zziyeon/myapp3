package com.kh.myapp3.web;

import antlr.StringUtils;
import com.kh.myapp3.domain.Product;
import com.kh.myapp3.domain.svc.ProductSVC;
import com.kh.myapp3.web.form.ItemForm;
import com.kh.myapp3.web.form.SaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductSVC productSVC;

    //등록 양식
    @GetMapping("/add")
    public String addForm(){

        return "product/addForm";          //상품 등록 view
    }

    //등록 처리
    @PostMapping("/add")
    public String save(SaveForm saveForm){
        log.info("saveForm:{}", saveForm);

        Product product = new Product();
        product.setPname(saveForm.getPname());
        product.setQuantity(saveForm.getQuantity());
        product.setPrice(saveForm.getPrice());

        Product savedProduct = productSVC.save(product);


        return "redirect:/products/"+savedProduct.getProductId();      //요청 url
    }

    //상품 개별 조회
    @GetMapping("/{pid}")
    public String findByProductId(
            @PathVariable("pid") Long pid,
            Model model
    ){
        //db에서 상품조회
        Product findedProduct = productSVC.findById(pid);

        //Product => ItemForm 복사
        ItemForm itemForm = new ItemForm();
        itemForm.setProductId(findedProduct.getProductId());
        itemForm.setPname((findedProduct.getPname()));
        itemForm.setQuantity(findedProduct.getQuantity());
        itemForm.setPrice(findedProduct.getPrice());

        //view에서 참조하기위해 model 객체에 저장
        model.addAttribute("itemForm",itemForm);

        return "product/itemForm";        //상세 view
    }

//    //수정 양식
//    @GetMapping("/{pid}/edit")
//    public String editForm(){
//
//
//
//        return "product/editForm";        //상품 수정 view
//    }

    //수정 양식
    @GetMapping("/{pid}/edit")
    public String editForm(
            @PathVariable("pid") Long pid,
            Model model
    ){
        //db에서 상품조회
        Product findedProduct = productSVC.findById(pid);

        //Product => ItemForm 복사
        ItemForm itemForm = new ItemForm();
        itemForm.setProductId(findedProduct.getProductId());
        itemForm.setPname((findedProduct.getPname()));
        itemForm.setQuantity(findedProduct.getQuantity());
        itemForm.setPrice(findedProduct.getPrice());

        //view에서 참조하기위해 model 객체에 저장
        model.addAttribute("itemForm",itemForm);


        return "product/editForm";        //상품 수정 view
    }

    //수정 처리
    @PostMapping("/{pid}/edit")
    public String update(@PathVariable("pid") String pid){

        return "redifrect:/products/1";      //상품 상세 url
    }

    //삭제 처리
    @GetMapping("/{pid}/del")
    public String delete(){
        return "redirect:/products";         //삭제 후 전체 목록 url
    }

    // 목록 화면
    @GetMapping
    public String list(){

        return "product/all";               //전체 목록 view
    }

}

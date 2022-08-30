package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.Product;
import com.kh.myapp3.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{
    private final ProductDAO productDAO;
//    ↑ 같다
//    public ProductSVCImpl(ProductDAO productDAO) {
//        this.productDAO = productDAO;
//    }

    //등록
    @Override
    public Integer save(Product product) {
        return productDAO.save(product);
    }
}

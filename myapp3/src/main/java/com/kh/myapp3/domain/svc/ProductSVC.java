package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.Product;

public interface ProductSVC {

    /**
     * 삼풍등록
     * @param product   상품정보
     * @return  상품ID
     */
    Integer save(Product product);
}

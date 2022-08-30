package com.kh.myapp3.domain.dao;

import com.kh.myapp3.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@Repository
@RequiredArgsConstructor            //final필드를 생성자의 매개변수로 해서 생성자를 만들어줌
public class ProductDAOImpl implements ProductDAO {
    private final JdbcTemplate jt;
//    ↑ 같다. 생성자 주입
//    private JdbcTemplate jt;
//    ProductDAOImpl(JdbcTemplate jt){
//        this.jt = jt;
//    }

//    //등록 방법 1
//    @Override
//    public Integer save(Product product) {
//        StringBuffer sql = new StringBuffer();
//        sql.append("insert into product values(product_product_id_seq.nextval, ?, ?,?)");
////        sql.append("insert into product(product_id, pname, quantity, price) ");
////        sql.append("values(product_product_id_seq.nextval, '컴퓨터', 20,90000) ");
//
//        class PreparedStatementCreatorImpl implements PreparedStatementCreator {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[]{"product_id"});
//                pstmt.setString(1, product.getPname());
//                pstmt.setInt(2, product.getQuantity());
//                pstmt.setInt(3, product.getPrice());
//
//                return pstmt;
//            }
//        };
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jt.update(new PreparedStatementCreatorImpl(),keyHolder);
//
//        Integer product_id=Integer.valueOf(keyHolder.getKeys().get("product_id").toString());
//        return product_id;
//    }

//    //등록  방법2
    @Override
    public Integer save(Product product) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into product values(product_product_id_seq.nextval, ?, ?,?)");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jt.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[]{"product_id"});
                pstmt.setString(1, product.getPname());
                pstmt.setInt(2, product.getQuantity());
                pstmt.setInt(3, product.getPrice());

                return pstmt;
            }
        },keyHolder);

        Integer product_id=Integer.valueOf(keyHolder.getKeys().get("product_id").toString());
        return product_id;
    }

    //등록    방법3) 화살표 함수 사용
//    @Override
//    public Integer save(Product product) {
//        StringBuffer sql = new StringBuffer();
//        sql.append("insert into product values(product_product_id_seq.nextval, ?, ?,?)");
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jt.update(
//                con -> {
//                    PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[]{"product_id"});
//                    pstmt.setString(1, product.getPname());
//                    pstmt.setInt(2, product.getQuantity());
//                    pstmt.setInt(3, product.getPrice());
//                    return pstmt;
//                }, keyHolder);
//
//        Integer product_id = Integer.valueOf(keyHolder.getKeys().get("product_id").toString());
//        return product_id;
//    }
}

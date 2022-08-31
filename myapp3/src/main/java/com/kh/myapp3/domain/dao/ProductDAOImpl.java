package com.kh.myapp3.domain.dao;

import com.kh.myapp3.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
//        product.setProductId(product_id);
//        return product;
//    }

//    //등록  방법2
    @Override
    public Product save(Product product) {
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

        Long product_id=Long.valueOf(keyHolder.getKeys().get("product_id").toString());

        product.setProductId(product_id);
        return product;
    }

    //조회
    @Override
    public Product findById(Long productId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select product_id, pname, quantity, price ");
        sql.append("from product ");
        sql.append("where product_id= ? ");

        Product product = null;     //queryForObject: 메소드 하나
        try {
            product = jt.queryForObject(
                    sql.toString(), new BeanPropertyRowMapper<>(Product.class) , productId);
        } catch (EmptyResultDataAccessException e) {
            log.info("삭제대상 상품이 없습니다 상품아이디={}", productId);
        }
        return product;
    }

    //수정
    @Override
    public void update(Long productId, Product product) {
        StringBuffer sql = new StringBuffer();

        sql.append("update product ");
        sql.append("set pname = ?, ");
        sql.append("        quantity = ?, ");
        sql.append("        price = ? ");
        sql.append("where product_id = ? ");


        jt.update(sql.toString(), product.getPname(), product.getQuantity(),product.getPrice(), productId );
    }

    //삭제
    @Override
    public void delete(Long productId) {
        String sql = ("delete FRom product where product_id = ? ");
        jt.update(sql, productId);
    }

    //목록
    @Override
    public List<Product> findAll() {
        StringBuffer sql = new StringBuffer();

        sql.append("select product_id, pname, quantity, price ");
        sql.append("    from product ");

        //case1) 자동 매핑 sql결과 레코드와 bean(컨테이너가 관리하는 자바 객체) 동일한 구조의 자바 객체가 존재할 경우
        List<Product> result =jt.query(sql.toString(), new BeanPropertyRowMapper<>(Product.class));              //결과가 여러개 나올 때 query 사용

        //case2) 수동 매핑 sql결과 레코드와 컬럼명과 java 객체의 멤버이름이 다른경우 or 타입이 다른 경우
//        List<Product> result =
//            jt.query(sql.toString(), new RowMapper<Product>() {
//
//            @Override
//            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Product product = new Product();
//                product.setProductId(rs.getLong("product_id"));
//                product.setQuantity(rs.getInt("quantity"));
//                product.setPrice(rs.getInt("price"));
//                return product;
//            }
//        });
       return result;
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
//        product.setProductId(product_id);
//        return product;
//    }
}

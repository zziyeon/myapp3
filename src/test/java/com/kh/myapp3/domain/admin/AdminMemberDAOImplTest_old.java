package com.kh.myapp3.domain.admin;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AdminMemberDAOImplTest_old {

    @Autowired
    private AdminMemberDAO adminMemberDAO;

    @Test
    @DisplayName("이메일 중복 체크")
    void dubChkOfMemberEmail() {
        Boolean isExist = adminMemberDAO.dubChkOfMemberEmail("test6@com");
        Assertions.assertThat(isExist).isTrue();

        isExist = adminMemberDAO.dubChkOfMemberEmail("test6@co1m");
        Assertions.assertThat(isExist).isFalse();
    }
}
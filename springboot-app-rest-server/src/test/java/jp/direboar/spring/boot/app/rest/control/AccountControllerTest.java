package jp.direboar.spring.boot.app.rest.control;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.direboar.spring.boot.app.rest.data.Account;

// see https://www.mkyong.com/spring-boot/spring-rest-validation-example/

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AccountControllerTest {

    // FIXME このクラスはコントローラのテストなので、サービスはモックにする。
    // FIXME MyBATISのテストはサービスを単体テストする。
    // FIXME 全体の結合はRestTestTemplateで実装する。

    @Autowired
    private MockMvc mvc;

    @Test
    void testPutAccountOK() throws Exception {
        Account account = new Account();
        account.setId("1234");
        account.setName("みのくば");
        account.setProfile("みのくばのプロファイル");
        account.setBirthday("2010-10-10");
        account.setMailAddress("minokuba@dummy.co.jp");

        String json = new ObjectMapper().writeValueAsString(account);

        this.mvc.perform(put("/api/v1/account/{id}", account.getId()).content(json)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNoContent())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

    }

    @Test
    void testPutAccountError() throws Exception {
        Account account = new Account();
        String json = new ObjectMapper().writeValueAsString(account);

        this.mvc.perform(put("/api/v1/account/{id}", 1).content(json)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isBadRequest())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.status", is(400)))
                        .andExpect(jsonPath("$.reason", is("Bad Request")))
                        .andExpect(jsonPath("$.messages", hasSize(4)))
                        .andExpect(jsonPath("$.messages", hasItem("birthday:must not be null")))
                        .andExpect(jsonPath("$.messages", hasItem("name:must not be null")))
                        .andExpect(jsonPath("$.messages", hasItem("id:must not be null")))
                        .andExpect(jsonPath("$.messages", hasItem("mailAddress:must not be null")));
    }

}

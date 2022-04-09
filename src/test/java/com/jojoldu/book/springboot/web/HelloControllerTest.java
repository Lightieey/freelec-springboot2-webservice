package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)    // JUnit에 내장된 실행자 외의 다른 실행자를 실행시킴(SpringRunner)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired  // 스프링이 관리하는 빈을 주입받음
    private MockMvc mvc;    // 스프링 MVC 테스트의 시작 (웹 API 테스트)

    @Test
    public void return_Hello() throws Exception {
        String hello = "hello";

        // chaining 지원
        mvc.perform(get("/hello"))  // /hello 주소로 GET 요청
                .andExpect(status().isOk()) // OK(200)인지 상태 검증
                .andExpect(content().string(hello));    // 응답 결과 검증
    }

    @Test
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)                        // API 테스트할 때 사용될 요청 파라미터 설정
                .param("amount", String.valueOf(amount)))   // 값은 String만 허용되므로 문자열로 변경
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    // 응답값을 필드별로 검증
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}

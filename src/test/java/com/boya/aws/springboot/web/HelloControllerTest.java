package com.boya.aws.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HelloController.class)  // web에 집중된 테스트 할 수 있음. @Controller 와 관련된 것만 사용 가능
@ExtendWith(SpringExtension.class) // @RunWith이 바뀐 것. JUnit 내장 실행자 외 다른 실행자 실행. 여기서는 SpringRunner 라는 스프링 실행자 사용하기 위함
public class HelloControllerTest {

        @Autowired // 스프링 빈 주입
        private MockMvc mvc; // web API 테스트 시 사용, HTTP get, post API 테스트 가능

        @Test
        public void hello가_리턴된다() throws Exception {
            String hello = "hello";

            mvc.perform(get("/hello")) // MockMVC를 통해 /hello 주소로 HTTP Get 요청. 체이닝 지원돼 여러 검증 이어서 선언 가능
                    .andExpect(status().isOk()) // mvc.perform 결과 검증, HTTP Header의 status 검증
                    .andExpect(content().string(hello)); // mvc.perform 결과 검증, 응답 본문 내용 검증, Controller에서 "hello"를 반환하는지 검증
        }

        @Test
        public void helloDto가_리턴된다() throws Exception {
            String name = "hello";
            int amount = 1000;

            mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount))) // param : API 테스트 할 때 사용될 요청 파라미터 설정
                                                                                                                            // 값은 String만 허용
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(name))) // Json 응답값을 필드별로 검증할 수 있는 메소드, $ 기준으로 필드명 명시
                    .andExpect(jsonPath("$.amount", is(amount)));
        }


}
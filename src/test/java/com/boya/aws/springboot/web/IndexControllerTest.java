package com.boya.aws.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

        @Autowired
        private TestRestTemplate restTemplate;

        @Test
        public void 메인페이지_로딩() { // URL 호출 시 페이지 내용이 제대로 호출되는 지 테스트
            //when
            String body = this.restTemplate.getForObject("/", String.class);

            //then
            assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
            // "/"로 호출했을 때 index.mustache에 포함된 코드들이 있는지 확인하는 것
            // 이 코드에는 스프링 부트로 시작하는 웹 서비스 문자열이 포함되어 있는지 확인한다.
        }

}
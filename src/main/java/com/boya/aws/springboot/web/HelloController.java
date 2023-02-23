package com.boya.aws.springboot.web;

import com.boya.aws.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON으로 반환하는 컨트롤러로 만들어 줌
public class HelloController {

    @GetMapping("/hello") // HTTP Method인 Get 요청 받을 수 있게 해 줌
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount); //@RequestParam 외부에서 API로 넘긴 파라미터 가져오는 어노테이션, 외부에서 name이란 이름으로 넘긴 파라미터를 메소드 파라미터 name에 저장

    }
}

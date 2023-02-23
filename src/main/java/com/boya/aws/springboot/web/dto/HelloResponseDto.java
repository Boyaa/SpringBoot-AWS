package com.boya.aws.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final이 포함된 필드 생성자를 생성해줌
public class HelloResponseDto {

    private final String name;
    private final int amount;

}

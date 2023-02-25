package com.boya.aws.springboot.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) // 클래스에 Auditing 기능 포함
public abstract class BaseTimeEntity { // 모든 클래스의 상위 클래스가 되어 entity의 생성시간과 수정시간을 관리하는 역할

    @CreatedDate // 저장될 때 시간 자동 저장
    private LocalDateTime createDate;

    @LastModifiedDate // 값 변경될 때 시간 자동 저장
    private LocalDateTime modifiedDate;
}

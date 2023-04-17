package com.codestates.member.dto;

import lombok.Builder;
import lombok.Getter;

// TODO 변경: Builder 패턴 적용
@Builder
@Getter
public class MemberResponseDto {
    private long memberId;
    private String email;
    private String name;
    private String phone;
}

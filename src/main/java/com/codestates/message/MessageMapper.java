package com.codestates.message;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {    // DTO 클래스와 Entity 클래스를 매핑해주는 Mapper 인터페이스
    Message messageDtoToMessage(MessagePostDto messagePostDto);
    MessageResponseDto messageToMessageResponseDto(Message message);
}

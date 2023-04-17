package com.codestates.member.controller;

import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.response.ErrorResponse;
import com.codestates.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;


/**
 * - DI 적용
 * - Mapstruct Mapper 적용
 */
@RestController
@RequestMapping("/v5/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
        Member member = mapper.memberPostDtoToMember(memberDto);

        Member response = memberService.createMember(member);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        Member response =
                memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),
                HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member response = memberService.findMember(memberId);
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> members = memberService.findMembers();
        List<MemberResponseDto> response = mapper.membersToMemberResponseDtos(members);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        System.out.println("# delete member");
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // @ExceptionHandler의 예외 처리
    /*
    @ExceptionHandler
    public ResponseEntity handleException(MethodArgumentNotValidException e) {  // 메서드 인수가 유효하지 않은 예외 처리
        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors(); // 에러 정보 확인

        List<ErrorResponse.FieldError> errors =
                fieldErrors
                        .stream()
                        .map(error ->
                                new ErrorResponse.FieldError(
                                        error.getField(),
                                        error.getRejectedValue(),
                                        error.getDefaultMessage()))
                        .collect(Collectors.toList());  // ErrorResponse 클래스의 필요한 정보들을 선택하여 ResponseEntity 클래스로 전달

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);   // 에러 정보를 Request Body로 전달
    }

    @ExceptionHandler
    public ResponseEntity handleException(ConstraintViolationException e) { // 제약 조건 위반 예외 처리

        // 메서드 오버로딩 -> 매개변수 개수 또는 타입이 달라야 한다 -> @ExceptionHandler 사용 단점

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    */
}

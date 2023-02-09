package com.codestates.message;

import org.springframework.data.repository.CrudRepository;

// Data Acess Layer에서 데이터베이스와의 연동을 담당하는 Repository 역할을 하는 MessageRepository 인터페이스
// CrudRepository 인터페이스 : 데이터베이스에 CRUD(데이터 생성, 조회, 수정, 삭제) 작업을 진행하기 위해 Spring에서 지원해주는 인터페이스
public interface MessageRepository extends CrudRepository<Message, Long> {
}

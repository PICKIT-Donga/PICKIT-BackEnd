package com.example.pickitbackend.repository;

import com.example.pickitbackend.domain.Popup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PopupRepository extends JpaRepository<Popup, Long> {
    List<Popup> findByAddressContaining(String address); // 주소에 특정 문자열이 포함된 팝업을 조회하는 메서드
    // JpaRepository를 상속받아 기본적인 CRUD 메서드를 사용할 수 있습니다.
    // 추가적인 쿼리 메서드가 필요하다면 여기에 정의할 수 있습니다.
}
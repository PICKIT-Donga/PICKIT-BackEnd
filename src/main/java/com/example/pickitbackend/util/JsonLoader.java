package com.example.pickitbackend.util;

import com.example.pickitbackend.dto.PopupRequestDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonLoader {
    // JSON 파일을 로드하는 유틸리티 클래스
    // 이 클래스는 JSON 파일을 읽고, 필요한 데이터를 파싱하여 반환하는 메서드를 포함할 수 있음
    // JSON 파일에서 팝업 정보를 로드하는 메서드를 구현할 수 있음

    public static List<PopupRequestDto> loadPopups() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = JsonLoader.class.getResourceAsStream("/data/output1.json")) {
            return mapper.readValue(is, new TypeReference<List<PopupRequestDto>>() {});
        } catch (Exception e) {
            throw new RuntimeException("JSON 파일을 읽을 수 없습니다", e);
        }
    }


}

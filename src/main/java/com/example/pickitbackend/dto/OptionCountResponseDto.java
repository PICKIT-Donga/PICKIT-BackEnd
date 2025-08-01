package com.example.pickitbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OptionCountResponseDto {
    private String optionValue;
    private int count;
}

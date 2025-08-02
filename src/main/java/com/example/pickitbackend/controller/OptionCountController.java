package com.example.pickitbackend.controller;

import com.example.pickitbackend.dto.OptionCountResponseDto;
import com.example.pickitbackend.service.OptionCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.pickitbackend.repository.PopupRepository;
import com.example.pickitbackend.domain.Popup;

import java.util.List;

@RestController
@RequestMapping("/popups/{popupId}/option-counts")
@RequiredArgsConstructor
public class OptionCountController {

    private final OptionCountService optionCountService;
    private final PopupRepository popupRepository;

    @GetMapping("/{category}")
    public List<OptionCountResponseDto> getOptionCounts(@PathVariable Long popupId, @PathVariable String category) {
        Popup popup = popupRepository.findById(popupId)
                .orElseThrow(() -> new RuntimeException("Popup not found"));
        return optionCountService.getOptionCountsByCategory(popup, category)
                .stream()
                .map(oc -> new OptionCountResponseDto(oc.getOptionValue(), oc.getCount()))
                .toList();
    }
}

package com.example.pickitbackend.service;

import com.example.pickitbackend.domain.OptionCount;
import com.example.pickitbackend.domain.Popup;
import com.example.pickitbackend.repository.OptionCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionCountService {

    private final OptionCountRepository optionCountRepository;

    @Transactional
    public void increaseCount(Popup popup, String category, String optionValue) {
        OptionCount optionCount = optionCountRepository
                .findByPopupAndCategoryAndOptionValue(popup, category, optionValue)
                .orElseGet(() -> OptionCount.builder()
                        .popup(popup)
                        .category(category)
                        .optionValue(optionValue)
                        .count(0)
                        .build());

        optionCount.setCount(optionCount.getCount() + 1);
        optionCountRepository.save(optionCount);
    }

    @Transactional
    public List<OptionCount> getOptionCountsByCategory(Popup popup, String category) {
        // 구현 내용
        return optionCountRepository.findByPopupAndCategory(popup, category);
    }
}

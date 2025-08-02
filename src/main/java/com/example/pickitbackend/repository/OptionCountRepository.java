package com.example.pickitbackend.repository;

import com.example.pickitbackend.domain.OptionCount;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pickitbackend.domain.Popup;

import java.util.List;
import java.util.Optional;

public interface OptionCountRepository extends JpaRepository<OptionCount, Long> {
    Optional<OptionCount> findByPopupAndCategoryAndOptionValue(Popup popup, String category, String optionValue);
    List<OptionCount> findByPopupAndCategory(Popup popup, String category);
}

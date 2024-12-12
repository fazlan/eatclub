package au.com.eatclub.digitalmenu.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public record MenuSection(
        Integer id,
        String title,
        String description,
        Integer menuId,
        @JsonIgnore LocalDate effectiveStartDate,
        @JsonIgnore LocalDate effectiveEndDate) {

    public MenuSection(Integer id, String title, String description, Integer menuId) {
        this(id, title, description, menuId, LocalDate.now(), LocalDate.now().plusYears(1000));
    }
}

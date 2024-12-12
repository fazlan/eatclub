package au.com.eatclub.digitalmenu.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MenuItem(
        Integer id,
        String title,
        String description,
        String imageURL,
        BigDecimal price,
        Integer menuSectionId,
        @JsonIgnore LocalDate effectiveStartDate,
        @JsonIgnore LocalDate effectiveEndDate) {

    public MenuItem(Integer id, String title, String description, String imageURL, BigDecimal price, Integer menuSectionId) {
        this(id, title, description, imageURL, price, menuSectionId, LocalDate.now(), LocalDate.now().plusYears(1000));
    }
}

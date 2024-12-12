package au.com.eatclub.digitalmenu.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public record Menu(
        Integer id,
        String title,
        Integer branchId,
        @JsonIgnore LocalDate effectiveStartDate,
        @JsonIgnore LocalDate effectiveEndDate) {

    public Menu(Integer id, String title, Integer branchId) {
        this(id, title, branchId, LocalDate.now(), LocalDate.now().plusYears(1000));
    }
}

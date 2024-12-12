package au.com.eatclub.digitalmenu.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public record Restaurant(Integer id,
                         String name,
                         @JsonIgnore LocalDate effectiveStartDate,
                         @JsonIgnore LocalDate effectiveEndDate) {

    public Restaurant(Integer id, String name) {
        this(id, name, LocalDate.now(), LocalDate.now().plusYears(1000));
    }
}

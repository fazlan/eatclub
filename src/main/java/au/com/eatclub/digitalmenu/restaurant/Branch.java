package au.com.eatclub.digitalmenu.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public record Branch(Integer id,
                     String name,
                     String location,
                     Integer restaurantId,
                     @JsonIgnore LocalDate effectiveStartDate,
                     @JsonIgnore LocalDate effectiveEndDate) {

    public Branch(Integer id, String name, String location, Integer restaurantId) {
        this(id, name, location, restaurantId, LocalDate.now(), LocalDate.now().plusYears(1000));
    }
}

package au.com.eatclub.digitalmenu.restaurant;

import java.time.LocalDate;
import java.util.Collection;

public interface RestaurantRepository {
    Restaurant addRestaurant(Restaurant restaurant);
    Collection<Restaurant> getRestaurants(LocalDate relativeDate);
}

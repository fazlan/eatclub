package au.com.eatclub.digitalmenu.restaurant;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static au.com.eatclub.digitalmenu.common.DateUtils.isFirstDateBetweenOtherTwoDates;

@Repository
public class RestaurantStubRepository implements RestaurantRepository {

    private static final AtomicInteger RECORD_SEQ = new AtomicInteger();
    private static final Map<Integer, Restaurant> RECORDS = new TreeMap<>();

    static {
        RECORD_SEQ.incrementAndGet();
        RECORDS.put(RECORD_SEQ.get(), new Restaurant(RECORD_SEQ.get(), "Kebab Joint"));

        RECORD_SEQ.incrementAndGet();
        RECORDS.put(RECORD_SEQ.get(), new Restaurant(RECORD_SEQ.get(), "Pizza Boys"));
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        RECORD_SEQ.incrementAndGet();
        return RECORDS.put(RECORD_SEQ.get(), new Restaurant(RECORD_SEQ.get(), restaurant.name()));
    }

    /**
     * SELECT *
     * FROM
     * RESTAURANT R
     * WHERE
     * :effectiveDate BETWEEN R.EFFECTIVE_STARTDATE AND R.EFFECTIVE_ENDDATE
     * ORDER BY
     * R.NAME;
     *
     * @param  effectiveDate the relative date to find the records based on effective stand and end dates.
     * @return Currently active restaurants.
     */
    @Override
    public Collection<Restaurant> getRestaurants(LocalDate effectiveDate) {
        return RECORDS.values()
                .stream()
                .filter(r -> isFirstDateBetweenOtherTwoDates(effectiveDate, r.effectiveStartDate(), r.effectiveEndDate()))
                .sorted(Comparator.comparing(Restaurant::name))
                .collect(Collectors.toList());
    }
}

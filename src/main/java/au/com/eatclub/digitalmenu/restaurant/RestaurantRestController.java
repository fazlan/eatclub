package au.com.eatclub.digitalmenu.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * This restful controller is defined for simple Restaurant CRUD operations (except for search - use GraphQL controller).
 */
@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantRestController {

    private final BranchRepository branchRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantRestController(BranchRepository branchRepository, RestaurantRepository restaurantRepository) {
        this.branchRepository = branchRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public ResponseEntity<?> getRestaurants() {
        return ResponseEntity.ok(restaurantRepository.getRestaurants(LocalDate.now()));
    }

    /**
     * This endpoint adds a new restaurant to the system.
     *
     * @param restaurant the new restaurant to be registered.
     * @return the newly created restaurant.
     * <p>
     * TODO: This endpoint needs to be secured via JWT.
     */
    @PostMapping
    public ResponseEntity<?>
    addRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(restaurantRepository.addRestaurant(restaurant));
    }


    @GetMapping("/{restaurantId}/branches")
    public ResponseEntity<?> getBranches(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(branchRepository.getBranches(restaurantId, LocalDate.now()));
    }

    /**
     * This endpoint adds a new branch to an existing restaurant.
     *
     * @param branch the new branch to be registered.
     * @return the newly created branch.
     * <p>
     * TODO: This endpoint needs to be secured via JWT.
     */
    @PostMapping("/{restaurantId}/branches")
    public ResponseEntity<?>
    addBranch(@PathVariable Integer restaurantId, @RequestBody Branch branch) {
        return ResponseEntity.ok(branchRepository.addBranch(restaurantId, branch));
    }
}

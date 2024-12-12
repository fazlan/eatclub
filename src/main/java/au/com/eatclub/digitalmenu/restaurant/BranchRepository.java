package au.com.eatclub.digitalmenu.restaurant;

import java.time.LocalDate;
import java.util.Collection;

public interface BranchRepository {
    Branch addBranch(Integer restaurantId, Branch branch);
    Collection<Branch> getBranches(Integer restaurantId, LocalDate effectiveDate);
}

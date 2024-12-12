package au.com.eatclub.digitalmenu.restaurant;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static au.com.eatclub.digitalmenu.common.DateUtils.isFirstDateBetweenOtherTwoDates;

@Repository
public class BranchStubRepository implements BranchRepository {

    private static final AtomicInteger RECORD_SEQ = new AtomicInteger();
    private static final Map<Integer, Branch> RECORDS = new TreeMap<>();

    static {
        RECORD_SEQ.incrementAndGet();
        RECORDS.put(RECORD_SEQ.get(), new Branch(RECORD_SEQ.get(), "Kebab Joint", "Box Hill - NSW", 1));
        RECORD_SEQ.incrementAndGet();
        RECORDS.put(RECORD_SEQ.get(), new Branch(RECORD_SEQ.get(), "Kebab Joint", "Brunswick - VIC", 1));

        RECORD_SEQ.incrementAndGet();
        RECORDS.put(RECORD_SEQ.get(), new Branch(RECORD_SEQ.get(), "Pizza Boys", "Sydney - NSW", 2));
        RECORD_SEQ.incrementAndGet();
        RECORDS.put(RECORD_SEQ.get(), new Branch(RECORD_SEQ.get(), "Pizza Boys", "Geelong - VIC", 2));
    }

    @Override
    public Branch addBranch(Integer restaurantId, Branch branch) {
        RECORD_SEQ.incrementAndGet();
        return RECORDS.put(RECORD_SEQ.get(), new Branch(RECORD_SEQ.get(), branch.name(), branch.location(), restaurantId));
    }

    /**
     * SELECT B.*
     * FROM
     * BRANCH B
     * WHERE
     * B.RESTAURANT_ID = :restaurantId
     * AND
     * :effectiveDate BETWEEN B.EFFECTIVE_STARTDATE AND B.EFFECTIVE_ENDDATE
     * ORDER BY
     * B.NAME;
     *
     * @param restaurantId the restaurant selected by the customer.
     * @param  effectiveDate the relative date to find the records based on effective stand and end dates.
     * @return list of branches associated.
     */

    @Override
    public Collection<Branch> getBranches(Integer restaurantId, LocalDate effectiveDate) {
        return RECORDS.values()
                .stream()
                .filter(b -> Objects.equals(b.restaurantId(), restaurantId))
                .filter(b -> isFirstDateBetweenOtherTwoDates(effectiveDate, b.effectiveStartDate(), b.effectiveEndDate()))
                .sorted(Comparator.comparing(Branch::name))
                .collect(Collectors.toList());
    }
}

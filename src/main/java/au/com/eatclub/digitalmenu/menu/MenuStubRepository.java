package au.com.eatclub.digitalmenu.menu;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static au.com.eatclub.digitalmenu.common.DateUtils.isFirstDateBetweenOtherTwoDates;

@Repository
public class MenuStubRepository implements MenuRepository {

    private static final AtomicInteger MENU_RECORD_SEQ = new AtomicInteger();
    private static final AtomicInteger MENU_SECTION_RECORD_SEQ = new AtomicInteger();
    private static final AtomicInteger MENU_ITEM_RECORD_SEQ = new AtomicInteger();

    private static final Map<Integer, Menu> MENU_RECORDS = new HashMap<>();
    private static final Map<Integer, MenuSection> MENU_SECTION_RECORDS = new HashMap<>();
    private static final Map<Integer, MenuItem> MENU_ITEM_RECORDS = new HashMap<>();

    static {
        // Kebab Joint - NSW
        MENU_RECORD_SEQ.incrementAndGet();
        MENU_RECORDS.put(MENU_RECORD_SEQ.get(), new Menu(MENU_RECORD_SEQ.get(), "Kebab Joint Menu", 1));

        // Kebab Joint - NSW - Mix Platters Menu Section
        MENU_SECTION_RECORD_SEQ.incrementAndGet();
        MENU_SECTION_RECORDS.put(MENU_SECTION_RECORD_SEQ.get(), new MenuSection(MENU_SECTION_RECORD_SEQ.get(), "Mix Platters", "All mixed platters", MENU_RECORD_SEQ.get()));

        // Kebab Joint - NSW - Mix Platters Menu Section - Chicken Bowl
        MENU_ITEM_RECORD_SEQ.incrementAndGet();
        MENU_ITEM_RECORDS.put(MENU_ITEM_RECORD_SEQ.get(), new MenuItem(MENU_ITEM_RECORD_SEQ.get(), "Chicken Bowl", "Greek salad served with chicken doner meat.", "", new BigDecimal("16.90"), MENU_SECTION_RECORD_SEQ.get()));
        // Kebab Joint - NSW - Mix Platters Menu Section - Schnitzel Cold Pack
        MENU_ITEM_RECORD_SEQ.incrementAndGet();
        MENU_ITEM_RECORDS.put(MENU_ITEM_RECORD_SEQ.get(), new MenuItem(MENU_ITEM_RECORD_SEQ.get(), "Schnitzel Cold Pack", "Served with bowl of Greek salad and schnitzel with two choices of sauces.", "", new BigDecimal("18.90"), MENU_SECTION_RECORD_SEQ.get()));

        // Kebab Joint - NSW - HSP Menu Section
        MENU_SECTION_RECORD_SEQ.incrementAndGet();
        MENU_SECTION_RECORDS.put(MENU_SECTION_RECORD_SEQ.get(), new MenuSection(MENU_SECTION_RECORD_SEQ.get(), "HSP", "All HSP platters", MENU_RECORD_SEQ.get()));

        // Kebab Joint - NSW - HSP Menu Section - HSP
        MENU_ITEM_RECORD_SEQ.incrementAndGet();
        MENU_ITEM_RECORDS.put(MENU_ITEM_RECORD_SEQ.get(), new MenuItem(MENU_ITEM_RECORD_SEQ.get(), "HSP", "", "", new BigDecimal("12.90"), MENU_SECTION_RECORD_SEQ.get()));
        // Kebab Joint - NSW - HSP Menu Section - Meat Only
        MENU_ITEM_RECORD_SEQ.incrementAndGet();
        MENU_ITEM_RECORDS.put(MENU_ITEM_RECORD_SEQ.get(), new MenuItem(MENU_ITEM_RECORD_SEQ.get(), "Meat Only Pack", "", "", new BigDecimal("19.90"), MENU_SECTION_RECORD_SEQ.get()));

        // Pizza Boys - NSW
        MENU_RECORD_SEQ.incrementAndGet();
        MENU_RECORDS.put(MENU_RECORD_SEQ.get(), new Menu(MENU_RECORD_SEQ.get(), "Pizza Boys Menu", 3));

        MENU_SECTION_RECORD_SEQ.incrementAndGet();
        MENU_SECTION_RECORDS.put(MENU_SECTION_RECORD_SEQ.get(), new MenuSection(MENU_SECTION_RECORD_SEQ.get(), "Pizzas", "Tasty pizzas", MENU_RECORD_SEQ.get()));

        MENU_SECTION_RECORD_SEQ.incrementAndGet();
        MENU_SECTION_RECORDS.put(MENU_SECTION_RECORD_SEQ.get(), new MenuSection(MENU_SECTION_RECORD_SEQ.get(), "Pastas", "Tasty pastas", MENU_RECORD_SEQ.get()));
    }

    /**
     * @param branchId the branch the menu belongs to.
     * @param menu     the menu details.
     * @return added menu.
     */
    @Override
    public Menu addMenu(Integer branchId, Menu menu) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * SELECT M.*
     * FROM
     * MENU M
     * WHERE
     * :effective_date BETWEEN M.EFFECTIVE_STARTDATE AND M.EFFECTIVE_ENDDATE
     * AND
     * M.BRANCH_ID = :branchId
     *
     * @param branchId      the branch id the menu belongs to.
     * @param effectiveDate the relative date to find the records based on effective stand and end dates.
     * @return Currently active menu associated with the branch.
     */
    @Override
    public Menu getRecentMenu(Integer branchId, LocalDate effectiveDate) {
        return MENU_RECORDS
                .values()
                .stream()
                .filter(m -> Objects.equals(m.branchId(), branchId))
                .filter(m -> isFirstDateBetweenOtherTwoDates(effectiveDate, m.effectiveStartDate(), m.effectiveEndDate()))
                .findFirst()
                .orElse(null);
    }

    /**
     * SELECT MS.*
     * FROM
     * MENU_SECTION MS
     * WHERE
     * :effective_date BETWEEN MS.EFFECTIVE_STARTDATE AND MS.EFFECTIVE_ENDDATE
     * AND
     * MS.MENU_ID = :menuId
     *
     * @param menuId        the menu these sections belongs to.
     * @param effectiveDate the relative date to find the records based on effective stand and end dates.
     * @return All active menu sections associated with the menu.
     */
    @Override
    public Collection<MenuSection> getMenuSections(Integer menuId, LocalDate effectiveDate) {
        return MENU_SECTION_RECORDS
                .values()
                .stream()
                .filter(ms -> Objects.equals(ms.menuId(), menuId))
                .filter(ms -> isFirstDateBetweenOtherTwoDates(effectiveDate, ms.effectiveStartDate(), ms.effectiveEndDate()))
                .sorted(Comparator.comparing(MenuSection::title))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * SELECT MI.*
     * FROM
     * MENU_ITEM MI
     * WHERE
     * :effective_date BETWEEN MI.EFFECTIVE_STARTDATE AND MI.EFFECTIVE_ENDDATE
     * AND
     * MS.MENU_SECTION_ID = :menuSectionId
     *
     * @param menuSectionId        the menu section these items belongs to.
     * @param effectiveDate the relative date to find the records based on effective stand and end dates.
     * @return All active menu items associated with the menu section.
     */
    @Override
    public Collection<MenuItem> getMenuItems(Integer menuSectionId, LocalDate effectiveDate) {
        return MENU_ITEM_RECORDS
                .values()
                .stream()
                .filter(mi -> Objects.equals(mi.menuSectionId(), menuSectionId))
                .filter(mi -> isFirstDateBetweenOtherTwoDates(effectiveDate, mi.effectiveStartDate(), mi.effectiveEndDate()))
                .sorted(Comparator.comparing(MenuItem::title))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

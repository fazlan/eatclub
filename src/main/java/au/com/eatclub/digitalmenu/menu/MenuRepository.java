package au.com.eatclub.digitalmenu.menu;

import java.time.LocalDate;
import java.util.Collection;

public interface MenuRepository {
    Menu addMenu(Integer branchId, Menu menu);
    Menu getRecentMenu(Integer branchId, LocalDate effectiveDate);
    Collection<MenuSection> getMenuSections(Integer menuId, LocalDate effectiveDate);
    Collection<MenuItem> getMenuItems(Integer menuSectionId, LocalDate effectiveDate);
}

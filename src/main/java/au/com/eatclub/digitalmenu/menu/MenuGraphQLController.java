package au.com.eatclub.digitalmenu.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Collection;

@Controller
public class MenuGraphQLController {

    private final MenuRepository repository;

    public MenuGraphQLController(MenuRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public Menu getRecentMenu(@Argument Integer branchId) {
        return repository.getRecentMenu(branchId, LocalDate.now());
    }

    @SchemaMapping(typeName = "Menu", field = "sections")
    public Collection<MenuSection> getMenuSections(Menu menu) {
        return repository.getMenuSections(menu.id(), LocalDate.now());
    }

    @SchemaMapping(typeName = "MenuSection", field = "items")
    public Collection<MenuItem> getMenuSections(MenuSection menuSection) {
        return repository.getMenuItems(menuSection.id(), LocalDate.now());
    }
}

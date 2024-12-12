For the purpose of this exercise, we'll assume the following echo system and set of requirements designing the Restaurant Menu Management System:

# Echo system
* There will be restaurants.
* The restaurant will have one or more locations - branches (yes, this breakdown will allow different branches to serve different menus).
* Each branch will have an active menu at any given time period (soft delete based on effective_startdate and effective_enddate).
* A menu will have different menu sections, containing different menu items.
* There will be customers looking up for restaurants and their menus.

# System Requirements

## Restaurant Administration:

### Restaurant/branch
* Register restaurant (out of scope in this exercise).
* Add a branch. (out of scope in this exercise).
* Edit a branch (limited, e.i: branch name, operating days/hours).
* Activate/deactivate a branch (soft delete - effective_startdate <= current_date <= effective_enddate)

### Menu
* Add menu.
* Add/update/activate/deactivate menu section and menu item (soft delete - effective_startdate <= current_date <= effective_enddate).
* Add/update/activate/deactivate menu level (flat rate / percentage) discount (soft delete - effective_startdate <= current_date <= effective_enddate).
* Add/update/activate/deactivate menu item level (flat rate / percentage) discount (overrides menu level discount, soft delete - effective_startdate <= current_date <= effective_enddate).
* Add/update/remove a known slug (Highlights/Popular/Discounted) to a menu item (the slug can be later used in the UI to emphasise when showing to customers/diners).
* Add/update/activate/deactivate date and time based menu item availability.
* Add/update/activate/deactivate menu item variations (options - gluten free, add-ons - extra salad, drinks, sizes - regular/large/family).

## Restaurant Manager

### Menu
* Add menu.
* Add/update/activate/deactivate menu section and menu item (soft delete - effective_startdate <= current_date <= effective_enddate).
* Add/update/activate/deactivate menu level (flat rate / percentage) discount (soft delete - effective_startdate <= current_date <= effective_enddate).
* Add/update/activate/deactivate menu item level (flat rate / percentage) discount (overrides menu level discount, soft delete - effective_startdate <= current_date <= effective_enddate).
* Add/update/remove a known slug (Highlights/Popular/Discounted) to a menu item (the slug can be later used in the UI to emphasise when showing to customers/diners).
* Add/update/activate/deactivate date and time based menu item availability.
* Add/update/activate/deactivate menu item variations (options - gluten free, add-ons - extra salad, drinks, sizes - regular/large/family).

**NOTE*: Any changes to menu or pricing should reflect immediately.

## Diners/Customers

### Restaurant/branch/menu
* Search for restaurants and their branches (REST).
* Search for the currently offered menu in the restaurant/branch (GraphQL).

### Scope of the exercise (Basic search results)
* The data is ingested/seeded.
* Search for restaurants and their branches (REST).
* Basic search of the currently offered menu in the restaurant/branch (GraphQL).

### Next set of enhancements
* Search/show associated menu item variations (options - gluten free, add-ons - extra salad, drinks, sizes - regular/large/family).
* Search/show date and time based menu item availability.
* Search/show associated known slug (Highlights/Popular/Discounted) for a menu item.

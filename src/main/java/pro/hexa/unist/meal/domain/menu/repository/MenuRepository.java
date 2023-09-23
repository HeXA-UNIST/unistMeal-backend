package pro.hexa.unist.meal.domain.menu.repository;

import pro.hexa.unist.meal.domain.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> , MenuRepositoryCustom{
}

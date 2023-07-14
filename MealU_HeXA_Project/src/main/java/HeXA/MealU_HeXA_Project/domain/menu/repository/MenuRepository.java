package HeXA.MealU_HeXA_Project.domain.menu.repository;

import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> , MenuRepositoryCustom{
}

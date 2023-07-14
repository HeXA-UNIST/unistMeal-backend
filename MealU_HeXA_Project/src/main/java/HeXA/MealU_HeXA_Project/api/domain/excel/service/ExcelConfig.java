package HeXA.MealU_HeXA_Project.api.domain.excel.service;

import HeXA.MealU_HeXA_Project.api.common.parseClass.ExcelParser;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelConfig {
    private final MealTableRepository mealTableRepository;
    private final MenuRepository menuRepository;


    @Autowired
    public ExcelConfig(MealTableRepository mealTableRepository, MenuRepository menuRepository, ExcelParser excelParser) {
        this.menuRepository = menuRepository;
        this.mealTableRepository = mealTableRepository;

    }

    @Bean
    public ExcelService excelService() {
        return new ExcelService(this.mealTableRepository, this.menuRepository);
    }
}

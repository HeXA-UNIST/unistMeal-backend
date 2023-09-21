package HeXA.MealU_HeXA_Project.domain.allergy.model;

public enum AllergyType {
    EGG("난류 알러지"),
    MILK("우유 알러지"),
    FAGOPYRUM("메밀"),
    NUTS("땅콩"),
    BEAN("대두"),
    TRITICUM("밀"),
    SCOMBER("고등어"),
    CRAB("게"),
    SHRIMP("새우"),
    PORK("돼지고기"),
    PEACH("복숭아"),
    TOMATO("토마토"),
    SO2_3("아황산염"),
    WALNUT("호두"),
    CHICKEN("닭고기"),
    BEAF("소고기"),
    SQUID("오징어"),
    CLAM("조개류"),
    PINENUT("잣");
    private final String value;


    AllergyType(String value) {
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}

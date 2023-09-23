package pro.hexa.unist.meal.domain.ImageUrls.model;

public enum RestaurantType {
    DORMITORY("??? ??"),
    STUDENT("?? ??"),
    PROFESSOR("??? ??");

    private final String value;


    RestaurantType(String value) {
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }

}

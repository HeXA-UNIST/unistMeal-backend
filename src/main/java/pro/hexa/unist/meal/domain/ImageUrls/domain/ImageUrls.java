package pro.hexa.unist.meal.domain.ImageUrls.domain;

import pro.hexa.unist.meal.domain.AbstractEntity;
import pro.hexa.unist.meal.domain.ImageUrls.model.RestaurantType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class ImageUrls extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageUrls_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    RestaurantType restaurantType;

    @Column
    String url;
    public ImageUrls(RestaurantType restaurantType, String url){
        this.restaurantType = restaurantType;
        this.url = url;
    }
}

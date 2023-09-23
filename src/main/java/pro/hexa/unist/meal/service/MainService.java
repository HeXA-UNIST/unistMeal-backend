package pro.hexa.unist.meal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.hexa.unist.meal.service.Exceptions.BadRequestException;
import pro.hexa.unist.meal.service.Exceptions.BadRequestType;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MainService {

    @Value("${secretKey}")
    String secretKey;

    public void verifySecretKey(String key) {
        if (!secretKey.equals(key)) {
            throw new BadRequestException(BadRequestType.WRONG_SECRET_KEY);
        }
    }
}

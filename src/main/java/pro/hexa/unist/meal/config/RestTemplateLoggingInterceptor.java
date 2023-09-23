package pro.hexa.unist.meal.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@Slf4j
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        loggingRequest(request, body);

        ClientHttpResponse response = execution.execute(request, body);

        loggingResponse(request, response);

        return response;

    }

    private void loggingRequest(HttpRequest request, byte[] body) throws IOException {
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        log.info("Headers     : {}", request.getHeaders());
        log.info("Request body: {}", IOUtils.toString(body, StandardCharsets.UTF_8.name()));
    }


    private void loggingResponse(HttpRequest request, ClientHttpResponse response) throws IOException {
        log.info("URI / Method : {} / {}", request.getURI(), request.getMethod());
        log.info("Headers      : {}", response.getHeaders());
        log.info("Response body: {}", IOUtils.toString(response.getBody(), StandardCharsets.UTF_8));
    }

}

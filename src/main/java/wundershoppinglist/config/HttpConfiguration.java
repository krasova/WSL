package wundershoppinglist.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import wundershoppinglist.service.WunderlistRestService;
import wundershoppinglist.service.WunderlistRestServiceImpl;

/**
 * Created by osamo on 6/23/2017.
 */
@Configuration
public class HttpConfiguration {

    private static final String WUNDERLIST_API_URL = "a.wunderlist.com/api/v1/";

    @Bean
    public HttpClient httpClient() {
        return HttpClientBuilder.create()
                .setConnectionManager(newClientConnectionManager())
                .build();
    }

    private PoolingHttpClientConnectionManager newClientConnectionManager() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 20 - MultiThreadedHttpConnectionManager
        cm.setMaxTotal(20);
        // Increase default max connection per route to 2 - MultiThreadedHttpConnectionManager
        cm.setDefaultMaxPerRoute(2);
        return cm;
    }

    @Bean
    public ClientHttpRequestFactory httpClientFactory() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        return httpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(httpClientFactory());
    }

    @Bean
    public WunderlistRestService wunderlistRestService() {
        return new WunderlistRestServiceImpl(restTemplate(),
                WUNDERLIST_API_URL);
    }

}

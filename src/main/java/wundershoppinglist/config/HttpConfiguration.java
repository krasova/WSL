package wundershoppinglist.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by osamo on 6/23/2017.
 */
@Configuration
@ComponentScan(basePackages = {"wundershoppinglist"})
public class HttpConfiguration {

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
    public RestTemplate wunderlistRestTemplate() {
        return new RestTemplate(httpClientFactory());
    }

}

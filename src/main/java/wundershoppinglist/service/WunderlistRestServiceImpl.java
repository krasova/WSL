package wundershoppinglist.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wundershoppinglist.dto.Task;

/**
 * Created by osamo on 6/23/2017.
 */
@Slf4j
public class WunderlistRestServiceImpl implements WunderlistRestService {

    private static final String TASK_URL = "tasks";
    private static final String LIST_URL = "lists";

    private String baseUrl;
    private RestTemplate restTemplate;

    public WunderlistRestServiceImpl(RestTemplate restTemplate,
                                     String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public void addTaskToWunderlist(Task task) {
        try {
            addTask(baseUrl + TASK_URL, task, HttpMethod.POST);
        } catch (Exception e) {
            log.error("Error creating task in List - URL:" + baseUrl + TASK_URL + " Task: " + task.getTitle(), e);
        }
    }

//    @Override
//    public java.util.List<List> getListsFromWunderlist() {
//        return null;
//    }


    private void addTask(String url,
                         Task task,
                         HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Client-ID", "31a7366935eb7d609e3e");
        headers.set("X-Access-Token", "e87636ae512874029114af69eb5d5a7e870efbd60f92c769e7a94bc20c52");
        HttpEntity<Task> httpEntity = new HttpEntity<>(task, headers);
        restTemplate.exchange(url,
                method,
                httpEntity,
                String.class);
    }
}

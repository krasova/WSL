package wundershoppinglist.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Service
public class WunderlistRestServiceImpl implements WunderlistRestService {

    private static final String WUNDERLIST_API_URL = "a.wunderlist.com/api/v1/";
    private static final String TASK_URL = "tasks";
    private static final String LIST_URL = "lists";

    @Autowired
    @Qualifier("wunderlistRestTemplate")
    private RestTemplate restTemplate;

    @Override
    public void addTaskToWunderlist(Task task) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-Client-ID", "31a7366935eb7d609e3e");
            headers.set("X-Access-Token", "e87636ae512874029114af69eb5d5a7e870efbd60f92c769e7a94bc20c52");
            HttpEntity<Task> taskHttpEntity = new HttpEntity<>(task, headers);
            restTemplate.postForEntity(WUNDERLIST_API_URL + TASK_URL, taskHttpEntity, String.class);
        } catch (Exception e) {
            log.error("Error creating task in List - URL:" + WUNDERLIST_API_URL + TASK_URL + " Task: " + task.getTitle(), e);
        }
    }

//    @Override
//    public java.util.List<List> getListsFromWunderlist() {
//        return null;
//    }

}

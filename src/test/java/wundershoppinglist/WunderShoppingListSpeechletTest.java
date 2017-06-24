package wundershoppinglist;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wundershoppinglist.dto.Task;
import wundershoppinglist.service.WunderlistRestService;
import wundershoppinglist.service.WunderlistRestServiceImpl;

/**
 * Created by osamo on 6/22/2017.
 */
public class WunderShoppingListSpeechletTest {


    @Autowired
    WunderlistRestServiceImpl wunderlistRestService;

    @Test
    public void testApi(){
        wunderlistRestService.addTaskToWunderlist(Task.builder().list_id(142353947).title("test").build());
    }

}
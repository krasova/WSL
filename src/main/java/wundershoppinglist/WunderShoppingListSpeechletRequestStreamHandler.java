package wundershoppinglist;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by osamo on 6/13/2017.
 */
public class WunderShoppingListSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;

    static {
        supportedApplicationIds = new HashSet<>();
        supportedApplicationIds.add("amzn1.ask.skill.98f76baf-ac37-40b3-9528-3028830f0504");
    }

    public WunderShoppingListSpeechletRequestStreamHandler() {
        super(new WunderShoppingListSpeechlet(), supportedApplicationIds);
    }
}

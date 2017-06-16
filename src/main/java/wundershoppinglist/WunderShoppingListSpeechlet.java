package wundershoppinglist;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;

/**
 * Created by osamo on 6/13/2017.
 */
public class WunderShoppingListSpeechlet implements Speechlet {
    @Override
    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {

    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        return null;
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        Intent intent = intentRequest.getIntent();
        String intentName = intent.getName();

        if ("AddIntent".equals(intentName)) {
            return handleAddIntentRequest(intent, session);
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

    }

    private SpeechletResponse handleAddIntentRequest(Intent intent, Session session) {
        Slot itemSlot = intent.getSlot("item");
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Tide Pooler");
        card.setContent("test");
        // Create the plain text output
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("test");
        return SpeechletResponse.newTellResponse(outputSpeech, card);
    }
}

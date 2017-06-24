package wundershoppinglist;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import wundershoppinglist.dto.Task;
import wundershoppinglist.service.WunderlistRestService;

/**
 * Created by osamo on 6/13/2017.
 */
@Slf4j
public class WunderShoppingListSpeechlet implements Speechlet {

    @Autowired
    WunderlistRestService wunderlistRestService;

    @Override
    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", sessionStartedRequest.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", launchRequest.getRequestId(),
                session.getSessionId());
        return null;
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", intentRequest.getRequestId(),
                session.getSessionId());

        Intent intent = intentRequest.getIntent();
        String intentName = intent.getName();

        if ("AddIntent".equals(intentName)) {
            return handleAddIntentRequest(intent, session);
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return handleHelpRequest();
        } else if ("AMAZON.StopIntent".equals(intentName)) {
            return handleStopIntent();
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            return handleCancelIntent();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    private SpeechletResponse handleHelpRequest() {
        String repromptText = "What do you want to add to List?";
        String speechOutput = repromptText;

        return newAskResponse(speechOutput, repromptText);
    }

    private SpeechletResponse handleStopIntent() {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("Goodbye");
        return SpeechletResponse.newTellResponse(outputSpeech);
    }

    private SpeechletResponse handleCancelIntent() {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("Goodbye");
        return SpeechletResponse.newTellResponse(outputSpeech);
    }

    private SpeechletResponse handleAddIntentRequest(Intent intent, Session session) {

        Slot itemSlot = intent.getSlot("item");
        wunderlistRestService.addTaskToWunderlist(Task.builder().list_id(142353947).title(itemSlot.getValue()).build());
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("List");
        card.setContent(itemSlot.getValue());
        // Create the plain text output
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(itemSlot.getValue());
        return SpeechletResponse.newTellResponse(outputSpeech, card);
    }

    @Override
    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

    }

    /**
     * Wrapper for creating the Ask response from the input strings with
     * plain text output and reprompt speeches.
     *
     * @param stringOutput the output to be spoken
     * @param repromptText the reprompt for if the user doesn't reply or is misunderstood.
     * @return SpeechletResponse the speechlet response
     */
    private SpeechletResponse newAskResponse(String stringOutput, String repromptText) {
        return newAskResponse(stringOutput, false, repromptText, false);
    }

    /**
     * Wrapper for creating the Ask response from the input strings.
     *
     * @param stringOutput   the output to be spoken
     * @param isOutputSsml   whether the output text is of type SSML
     * @param repromptText   the reprompt for if the user doesn't reply or is misunderstood.
     * @param isRepromptSsml whether the reprompt text is of type SSML
     * @return SpeechletResponse the speechlet response
     */
    private SpeechletResponse newAskResponse(String stringOutput, boolean isOutputSsml,
                                             String repromptText, boolean isRepromptSsml) {
        OutputSpeech outputSpeech, repromptOutputSpeech;
        if (isOutputSsml) {
            outputSpeech = new SsmlOutputSpeech();
            ((SsmlOutputSpeech) outputSpeech).setSsml(stringOutput);
        } else {
            outputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) outputSpeech).setText(stringOutput);
        }

        if (isRepromptSsml) {
            repromptOutputSpeech = new SsmlOutputSpeech();
            ((SsmlOutputSpeech) repromptOutputSpeech).setSsml(stringOutput);
        } else {
            repromptOutputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) repromptOutputSpeech).setText(repromptText);
        }

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);
        return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
    }

}

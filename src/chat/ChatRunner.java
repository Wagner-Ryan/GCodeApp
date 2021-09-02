package src.chat;

import java.util.ArrayList;

import src.chat.words.ObjectWords;
import src.chat.words.QuestionWords;
import src.chat.words.Responses;

public class ChatRunner {
    private String inputText;
    private String outputText;
    private ArrayList<String> notableWords;

    public ChatRunner() {
        inputText = "";
    }

    public void setInput(String inputText) {
        this.inputText = inputText.toLowerCase();
        figureOutput();
    }

    // TODO
    public String getOutput() {
        return outputText;
    }

    private void figureOutput() {
        boolean active = true;

        if(inputText.contains("help")){
            outputText = "Ask any question you have in regards to the program in here. The AI bot will try to assist you as best as possible. Questions and responses are monitored by the creators of this app in order to better fine tune the AI.";
            active = false;
        }
        if (inputText.contains("bye")) {
            outputText = "Have a good day!";
            active = false;
        }
        if (inputText.contains("hello")) {
            outputText = "Hey there! Type 'help' if you need assistance.";
            active = false;
        }
        if(inputText.contains("g30")){
            outputText = "G30 is the starting position of all GCode programs. This value is manually set by the user before running the code using the dial next to the computer.";
            active = false;
        }
        if(inputText.contains("end") || inputText.contains("finish")){
            outputText = "Under misc., click end program. Remember to set G30 on computer before running.";
            active = false;
        }
        if(inputText.contains("save")){
            outputText = "Click 'file' then 'save'";
            active = false;
        }
        if(inputText.contains("line")){
            outputText = "Click misc., then click new line";
            active = false;
        }

        while (active) {
            notableWords = Parser.parse(inputText);

            if (notableWords.size() == 0) {
                outputText = "I didn't quite get that. Please try again. Type \"help\" if you need assistance.";
                active = false;
            }

            if (notableWords.contains(QuestionWords.how.getWord())) {
                if (notableWords.contains(ObjectWords.circle.getWord())) {
                    outputText = Responses.makeCircle.getResponse();
                    active = false;
                }
            }

            else {
                outputText = "I didn't quite get that. Please try again. Type \"help\" if you need assistance.";
                active = false;
            }

        }
    }

}

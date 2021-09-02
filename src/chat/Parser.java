package src.chat;

import java.util.ArrayList;

import src.chat.words.WordChecker;

public class Parser {
    public static ArrayList<String> parse(String phrase) {
        ArrayList<String> notableWords = new ArrayList<String>();

        String[] phraseArr = phrase.split(" ");

        for(int i = 0; i < phraseArr.length; i++){
            if(WordChecker.wordCheck(phraseArr[i])){
                notableWords.add(phraseArr[i]);
            }
        }

        return notableWords;
    }
}

package src.chat.words;

public class WordChecker {
    public static boolean wordCheck(String word) {
        for(Words w : ConnectWords.values()){
            if(w.getWord().equals(word)){
                return true;
            };
        }
        
        for(Words w : QuestionWords.values()){
            if(w.getWord().equals(word)){
                return true;
            };
        }

        for(Words w : ObjectWords.values()){
            if(w.getWord().equals(word)){
                return true;
            };
        } 

        return false;
    }
}

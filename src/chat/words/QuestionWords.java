package src.chat.words;

public enum QuestionWords implements Words{
   how("how"), what("what"), where("where"), why("why");

    private String word;

    private QuestionWords(String word){
        this.word = word;
    }

    @Override
    public String getWord(){
        return word;
    }
}

package src.chat.words;

public enum ObjectWords implements Words{
    line("line"), arc("arc"), circle("circle"), rectangle("rectangle");

    private String word;

    private ObjectWords(String word){
        this.word = word;
    }

    public String getWord(){
        return word;
    }

}

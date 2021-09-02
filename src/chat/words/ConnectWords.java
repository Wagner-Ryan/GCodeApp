package src.chat.words;

public enum ConnectWords implements Words{
    c1("make"), c2("create"), c3("drill"), c4("put");

    private String word;

    private ConnectWords(String word){
        this.word = word;
    }

    @Override
    public String getWord(){
        return word;
    }

}

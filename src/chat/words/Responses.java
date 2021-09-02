package src.chat.words;

public enum Responses {
    makeCircle("click make circle and input radius"), drawLine("what"), drillPoint("where"), q4("why");

    private String response;

    private Responses(String response){
        this.response = response;
    }

    public String getResponse(){
        return response;
    }
}

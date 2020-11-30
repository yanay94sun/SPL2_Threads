package bgu.spl.mics;

public class ExampleEvent implements Event<String>{

    private String senderName;

    public ExampleEvent(String sName) {
        senderName = sName;
    }

    public String getSenderName() {
        return senderName;
    }

}

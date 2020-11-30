package bgu.spl.mics;

public class ExampleBroadcast implements Broadcast{
    private final String senderName;

    public ExampleBroadcast(String sName) {
        senderName = sName;
    }

    public String getSenderName() {
        return senderName;
    }


}

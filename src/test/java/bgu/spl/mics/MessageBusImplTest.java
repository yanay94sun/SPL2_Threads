package bgu.spl.mics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageBusImplTest { // why not extends testCase????????????????????
     private MessageBus messageBus;
    // maybe need to use annonimus class of micro service for the tests
    private SimpleMicroService simpleMicroServiceA;
    private SimpleMicroService simpleMicroServiceB;
    private ExampleBroadcast exampleBroadcast;
    private ExampleEvent exampleEventA;
    private ExampleEvent exampleEventB;



    @BeforeEach
    void setUp() {
            messageBus = MessageBusImpl.getInstance();  // read online that should be getInstance in singelton /\/\
            simpleMicroServiceA = new SimpleMicroService();
            simpleMicroServiceB = new SimpleMicroService();
            exampleBroadcast = new ExampleBroadcast("A");
            exampleEventA = new ExampleEvent("A");
            exampleEventB = new ExampleEvent("B");



            simpleMicroServiceB.initialize();
            Future<String> future = simpleMicroServiceA.sendEvent(exampleEventA);
            ExampleEvent event = null;
            try {
                event = (ExampleEvent) messageBus.awaitMessage(simpleMicroServiceB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void complete() { // need to add "test" in the start of the name of the function????? ALL
    }

    @Test
    void sendBroadcast() {
    }

    @Test
    void sendEvent() {
    }

    @Test
    void awaitMessage() { // check if we can check only if its null ?? OR possible not to check this because we use this in the other test every time
    }
}
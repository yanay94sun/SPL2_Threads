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
    private ExampleEvent exampleEvent;



    @BeforeEach
    void setUp() {
            messageBus = MessageBusImpl.getInstance();  // read online that should be getInstance in singelton /\/\
            simpleMicroServiceA = new SimpleMicroService();
            simpleMicroServiceB = new SimpleMicroService();
            exampleBroadcast = new ExampleBroadcast("A");
            exampleEvent = new ExampleEvent("A");


        messageBus.register(simpleMicroServiceA);
        messageBus.register(simpleMicroServiceB);
            simpleMicroServiceA.initialize();
            simpleMicroServiceB.initialize();
            Future<String> future = simpleMicroServiceA.sendEvent(exampleEvent);
            ExampleEvent event = null;
            try {
                event = (ExampleEvent) messageBus.awaitMessage(simpleMicroServiceB);
            } catch (InterruptedException e) {
                e.printStackTrace(); // return null ?????????????
            }
            simpleMicroServiceB.complete(event, "OK");

    }

    @AfterEach
    void tearDown() {
    }
    /**
     * we test all the other methods here:
     * register/unregister - we register the microservices and check the finale result
        then we check when unregistered if the results change
     * subscribes - Tested in the initialize method
     * send E/BC - Tested as part of running the program when {@code simpleMicroServiceA} send
        a E/BC we check if {@code simpleMicroServiceB} got the message
     * awaitMessage - we check if  we get message from the queue if there is one available,
        if there isn't we ????????? return null  ??????
      */
    @Test
    void complete() { // need to add "test" in the start of the name of the function????? ALL
        messageBus.register(simpleMicroServiceA);
        messageBus.register(simpleMicroServiceB);
        simpleMicroServiceA.initialize();
        simpleMicroServiceB.initialize();


        Future<String> future = simpleMicroServiceA.sendEvent(exampleEvent);
        ExampleEvent event = null;
        try {
            event = (ExampleEvent) messageBus.awaitMessage(simpleMicroServiceB);
        } catch (InterruptedException e) {
            e.printStackTrace(); // return null ?????????????
        }
        simpleMicroServiceB.complete(event, "OK");

        assertSame("OK", future.get());

        messageBus.unregister(simpleMicroServiceA);
        assertNotSame("OK", future.get());
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
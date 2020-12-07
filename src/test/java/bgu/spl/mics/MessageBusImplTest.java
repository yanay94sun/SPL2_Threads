package bgu.spl.mics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageBusImplTest {
    private MessageBus messageBus;
    private SimpleMicroService simpleMicroServiceA;
    private SimpleMicroService simpleMicroServiceB;
    private ExampleBroadcast exampleBroadcast;
    private ExampleEvent exampleEvent;



    @BeforeEach
    void setUp() {
            messageBus = MessageBusImpl.getInstance();
            simpleMicroServiceA = new SimpleMicroService();
            simpleMicroServiceB = new SimpleMicroService();
            exampleBroadcast = new ExampleBroadcast("A");
            exampleEvent = new ExampleEvent("A");

    }

    @AfterEach
    void tearDown() {
    }
    /**
     * we test all the other methods here, except the broadcasts:
     * register/unregister - we register the microservices and check the finale result
        then we check when unregistered if the results change
     * subscribes - Tested in the initialize method
     * send Event - Tested as part of running the program when {@code simpleMicroServiceA} send
        a Event we check if {@code simpleMicroServiceB} got the message
     * awaitMessage - we check if  we get message from the queue if there is one available,
        if there isn't we ????????? return null  ??????
      */
    @Test
    void complete() {
        messageBus.register(simpleMicroServiceA);
        messageBus.register(simpleMicroServiceB);
        simpleMicroServiceA.initialize();
        simpleMicroServiceB.initialize();


        Future<String> future = simpleMicroServiceA.sendEvent(exampleEvent);
        ExampleEvent event = null;
        try {
            event = (ExampleEvent) messageBus.awaitMessage(simpleMicroServiceB);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simpleMicroServiceB.complete(event, "OK");

        assertSame("OK", future.get());

        messageBus.unregister(simpleMicroServiceA);
        assertNotSame("OK", future.get());

        messageBus.unregister(simpleMicroServiceB);

    }

    /**
     * we test here the broadcasts methods:
        the other methods are also check in the TestComplete method.
     * we test if {@code simpleMicroServiceA} send a broadcast, {@code simpleMicroServiceB}
        will get the broadcast by checking the name of the broadcast are the same.
     */
    @Test
    void sendBroadcast(){
        messageBus.register(simpleMicroServiceA);
        messageBus.register(simpleMicroServiceB);
        simpleMicroServiceA.initialize();
        simpleMicroServiceB.initialize();

        simpleMicroServiceA.sendBroadcast(exampleBroadcast);
        ExampleBroadcast broadcast = null;
        try {
            broadcast = (ExampleBroadcast) messageBus.awaitMessage(simpleMicroServiceB);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert broadcast != null;
        assertSame("A", broadcast.getSenderName());

        messageBus.unregister(simpleMicroServiceA);
        messageBus.unregister(simpleMicroServiceB);
    }

}
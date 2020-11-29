package bgu.spl.mics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageBusImplTest { // why not extends testCase????????????????????
     private MessageBus messageBus;
    // maybe need to use annonimus class of micro service for the tests

    @BeforeEach
    void setUp() {
        messageBus = new MessageBusImpl();
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
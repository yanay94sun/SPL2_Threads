package bgu.spl.mics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageBusImplTest {
    MessageBus messageBus;

    @BeforeEach
    void setUp() {
        messageBus = new MessageBusImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void complete() {
    }

    @Test
    void sendBroadcast() {
    }

    @Test
    void sendEvent() {
    }

    @Test
    void awaitMessage() {
    }
}
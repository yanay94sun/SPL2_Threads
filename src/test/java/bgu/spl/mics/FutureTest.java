package bgu.spl.mics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;


import static org.junit.jupiter.api.Assertions.*;

// need to check the get that get time, but all the other test use the regular get
public class FutureTest {

    private Future<String> future;

    @BeforeEach
    public void setUp(){
        future = new Future<>();
    }

    @Test
    public void testResolve(){
        assertFalse(future.isDone()); // rafael add because not yet resolved
        String str = "someResult";
        future.resolve(str);
        assertTrue(future.isDone());
        assertTrue(str.equals(future.get()));
    }

    @Test
    public void getTime(){
        future.resolve("someResult");
        long curr = System.currentTimeMillis();
        String s = future.get(1000, TimeUnit.MILLISECONDS);
        long after = System.currentTimeMillis();
        long res = after - curr;
        assert (res <= 1000); //??????????????????
    }
}

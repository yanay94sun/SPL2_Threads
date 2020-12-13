package bgu.spl.mics.application.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.passiveObjects.*; // rafael add
import bgu.spl.mics.application.messages.*;
import bgu.spl.mics.application.ThreadCounter;


/**
 * LeiaMicroservices Initialized with Attack objects, and sends them as  {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class LeiaMicroservice extends MicroService {
	private Attack[] attacks;
	private LinkedList<Future<Boolean>> futureQueue; // ?? check others futures ?????????????????????
    private ThreadCounter threadCounter;

    public LeiaMicroservice(Attack[] attacks) {
        super("Leia");

		this.attacks = attacks;
		this.futureQueue = new LinkedList<>();
        threadCounter = ThreadCounter.getInstance();
    }


    public void makeFutureQueue(){
        // making attacks events
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Attack attack : attacks) {
             futureQueue.add(this.sendEvent(new AttackEvent(attack))); // WHAT ABOUT THE FUTURES !!??????
        }
    }

    public void checkFutures(){
        while (!futureQueue.isEmpty()){
            System.out.println(futureQueue.peek().get());
            futureQueue.pop().get(); // TODO: this get or getTime????
        }
        // after all the attack events are completed, we send event to R2D2
        this.sendEvent(new DeactivationEvent()).get();
        // after R2D2 completed is task, we send event to Lando
        this.sendEvent(new BombDestroyerEvent()).get();
        // after the battle is done, we send broadcast to terminate
        this.sendBroadcast(new TerminateBroadCast());

    }





    @Override
    protected void initialize() {
//        System.out.println(getName());
        this.subscribeBroadcast(TerminateBroadCast.class, message -> {
            System.out.println(this.getName() + " Is terminate");
            this.terminate();

        });
        makeFutureQueue();

        checkFutures();
        System.out.println(this.getName() + " initialize successfully!");




    }

}

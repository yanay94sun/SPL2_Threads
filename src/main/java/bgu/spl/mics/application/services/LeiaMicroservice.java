package bgu.spl.mics.application.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.passiveObjects.*; // rafael add
import bgu.spl.mics.application.messages.*;


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

    public LeiaMicroservice(Attack[] attacks) {
        super("Leia");
		this.attacks = attacks;
		this.futureQueue = new LinkedList<>();
    }


    public void makeFutureQueue(){
        // making attacks events
        for (Attack attack : attacks) {
             futureQueue.add(this.sendEvent(new AttackEvent(attack))); // WHAT ABOUT THE FUTURES !!??????
        }
    }

    public void checkFutures(){
        while (!futureQueue.isEmpty()){
            futureQueue.pop().get(); // TODO: this get or getTime????
        }

        this.sendEvent(new DeactivationEvent()).get();

        this.sendEvent(new BombDestroyerEvent()).get();

        this.sendBroadcast(new TerminateBroadCast());

    }


    public void


    @Override
    protected void initialize() {
        this.subscribeBroadcast(TerminateBroadCast.class, message -> {
            this.terminate();
        });











    	
    }
}

package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.messages.BombDestroyerEvent;
import bgu.spl.mics.application.messages.TerminateBroadCast;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Ewoks;
import com.google.gson.JsonObject;


/**
 * C3POMicroservices is in charge of the handling {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class C3POMicroservice extends MicroService {
    private Ewoks ewoks;
    private Diary diary;

    public C3POMicroservice() {
        super("C3PO");
        this.ewoks = Ewoks.getInstance();
        this.diary = Diary.getInstance();
    }

    @Override
    protected void initialize() {

//        System.out.println(getName() + " starting initialize");
        subscribeBroadcast(TerminateBroadCast.class, message->{
//            System.out.println(this.getName() + " Is terminate");
            this.diary.setC3POTerminate(System.currentTimeMillis());
            this.terminate();
        });

//        System.out.println(this.getName() + " start acquire ewoks: ");
        this.subscribeEvent(AttackEvent.class, message->{
//            System.out.println(message.getSerial() + " Ewoks for  " + this.getName());
            boolean available = ewoks.getEwoks(message.getSerial()); // wait to be true
            if (available){
                try {
                    Thread.sleep(message.getDuration());
//                    System.out.println("ZZZZZZZZZ... " + this.getName() + " was sleeping for " + message.getDuration() + " ms");
                    this.diary.setC3POFinish(System.currentTimeMillis());
                    this.diary.incrementTotalAttacks();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ewoks.releaseEwoks(message.getSerial());
                complete(message, true);
            }
            else {
                complete(message, false);
            }
        }
        );
//        System.out.println(this.getName() + " initialize successfully!");

    }


}

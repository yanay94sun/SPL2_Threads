package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.DeactivationEvent;
import bgu.spl.mics.application.messages.TerminateBroadCast;
import bgu.spl.mics.application.messages.BombDestroyerEvent;
import bgu.spl.mics.application.passiveObjects.Diary;

/**
 * LandoMicroservice
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class LandoMicroservice  extends MicroService {
    long duration;
    private Diary diary;

    public LandoMicroservice(long duration) {
        super("Lando");
        this.duration = duration;
        this.diary = Diary.getInstance();
    }

    @Override
    protected void initialize() {
        System.out.println(getName() + " starting initialize");
        this.subscribeBroadcast(TerminateBroadCast.class, message -> {
            System.out.println(this.getName() + " Is terminate");
            this.diary.setLandoTerminate(System.currentTimeMillis());
            this.terminate();
        });

        this.subscribeEvent(BombDestroyerEvent.class, message -> {
            try {
                Thread.sleep(duration);
                System.out.println("ZZZZZZZZZ " + this.getName() + " was sleeping for " + duration + " ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.complete(message, true); // check if the result is type boolean ?????????????????
        });

        System.out.println(this.getName() + " initialize successfully!");

       
    }
}


//he sending the brodacst to terminate all Threads
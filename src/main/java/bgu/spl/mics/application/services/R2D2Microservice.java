package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.messages.DeactivationEvent;
import bgu.spl.mics.application.messages.TerminateBroadCast;
import bgu.spl.mics.application.passiveObjects.Diary;

/**
 * R2D2Microservices is in charge of the handling {@link DeactivationEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link DeactivationEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class R2D2Microservice extends MicroService {
    long duration;
    private Diary diary;

    public R2D2Microservice(long duration) {
        super("R2D2");
        this.duration = duration;
        this.diary = Diary.getInstance();
    }

    @Override
    protected void initialize() {
//        System.out.println(getName() + " starting initialize");
        this.subscribeBroadcast(TerminateBroadCast.class, message -> {
//            System.out.println(this.getName() + " Is terminate");
            this.diary.setR2D2Terminate(System.currentTimeMillis());
            this.terminate();
        });
        this.subscribeEvent(DeactivationEvent.class, message -> {
            try {
                Thread.sleep(duration);
//                System.out.println("ZZZZZZZZZ " + this.getName() + " was sleeping for " + duration + " ms");
                this.diary.setR2D2Deactivate(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.complete(message, true); // check if the result is type boolean ?????????????????
        });

//        System.out.println(this.getName() + " initialize successfully!");

    }

}

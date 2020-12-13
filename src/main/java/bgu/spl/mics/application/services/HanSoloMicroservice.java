package bgu.spl.mics.application.services;


import bgu.spl.mics.Broadcast;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.messages.TerminateBroadCast;
import bgu.spl.mics.application.passiveObjects.Ewoks;

/**
 * HanSoloMicroservices is in charge of the handling {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class HanSoloMicroservice extends MicroService {
    private Ewoks ewoks;

    public HanSoloMicroservice() {
        super("Han");
        this.ewoks = Ewoks.getInstance();
    }


    @Override
    protected void initialize() {
        System.out.println(getName());
        this.subscribeBroadcast(TerminateBroadCast.class, message -> {
            System.out.println(this.getName() + " Is terminate");
            this.terminate();
        });
        System.out.println(this.getName() + " start acquire ewoks: ");
        this.subscribeEvent(AttackEvent.class, message ->{
            boolean available = ewoks.getEwoks(message.getSerial()); // wait to be true
            if (available){
                try {
                    Thread.sleep(message.getDuration());
                    System.out.println("ZZZZZZZZZ " + this.getName() + " was sleeping for " + message.getDuration() + " ms");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(message.getSerial());
                ewoks.releaseEwoks(message.getSerial());
                complete(message, true);
                System.out.println(this.getName() + " Complete is mission and released the ewoks!");
            }
            else {
                System.out.println("NOT AVAILABLE");
                complete(message, false);
            }

        });


        System.out.println(this.getName() + " initialize successfully!");

    }
}

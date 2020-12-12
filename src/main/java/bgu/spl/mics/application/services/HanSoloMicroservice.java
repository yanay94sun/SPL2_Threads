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
        this.subscribeBroadcast(TerminateBroadCast.class, message -> {
            this.terminate();
        });

        this.subscribeEvent(AttackEvent.class, message ->{
            boolean available = ewoks.getEwoks(message.getSerial()); // wait to be true

            if (available){
                try {
                    Thread.sleep(message.getDuration());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                complete(message, true);
            }
            else {
                complete(message, false);
            }
        });



    }
}

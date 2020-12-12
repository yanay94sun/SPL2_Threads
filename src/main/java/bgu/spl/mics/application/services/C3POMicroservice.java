package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.messages.BombDestroyerEvent;
import bgu.spl.mics.application.messages.TerminateBroadCast;
import bgu.spl.mics.application.passiveObjects.Ewoks;


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

    public C3POMicroservice() {
        super("C3PO");
        this.ewoks = Ewoks.getInstance();
    }

    @Override
    protected void initialize() {
        subscribeBroadcast(TerminateBroadCast.class, message->{
            this.terminate();
        });


        subscribeEvent(AttackEvent.class, message->{

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
        }
        );
    }

}

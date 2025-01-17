package bgu.spl.mics.application.messages;
import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.Attack;

import java.util.List;

public class AttackEvent implements Event<Boolean> {
    Attack attack;


    public AttackEvent (Attack attack){
        this.attack = attack;


    }

    public List<Integer> getSerial(){
        return attack.getSerials();
    }

    public int getDuration(){
        return attack.getDuration();
    }
}

package bgu.spl.mics.application.passiveObjects;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Passive object representing the resource manager.
 * <p>
 * This class must be implemented as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private methods and fields to this class.
 */


public class Ewoks {
//NEED TO BE SINGELTON!!
    private Map<Integer, Ewok> ewoks;

    private Ewoks(){
        ewoks = new HashMap<Integer, Ewok>();
    }

    private static class SingletonHolder{
        private static Ewoks ewoks = new Ewoks();
    }

    public static Ewoks getInstance() {
        return SingletonHolder.ewoks;
    }

    public void load (Ewok[] ewoks) { //maybe no need for load?? maybe load directly while prase Json??? ASK AVITAL @TODO
        //loads the ewoks
        for (Ewok ewok : ewoks) {
            int serialNumber = ewok.getSerialNumber();
            this.ewoks.put(serialNumber, ewok);
        }
    }

    public boolean getEwoks(List<Integer> serialNumbers){
        //checks if the ewoks are in Ewoks
        for (Integer serialNumber : serialNumbers) {
            if (!ewoks.containsKey(serialNumber)) {
                return false;
            }
        }
        //after all the ewoks are in Ewoks he acquires them
        for (Integer serialNumber : serialNumbers) {
            Ewok a = ewoks.get(serialNumber);
            a.acquire();
        }
        return true;
    }

    public synchronized void releaseEwoks(List<Integer> serialNumbers){ // I dont think it should be synchronized if we sort ewoks.
        //releases the ewoks
        for (Integer serialNumber : serialNumbers) {
            if (ewoks.containsKey(serialNumber)) {
                ewoks.get(serialNumber).release();
            }
        }
    }

    /**
     * 	public void sendAgents(List<String> serials, int time){
     * 		//sends the agents
     * 		try{
     * 			//simulates the time that the mission takes place
     * 			Thread.sleep(time*100);
     *                }
     * 		catch (Exception e){
     * 			Thread.currentThread().interrupt();
     *        }
     * 		//sends the agents to be released
     * 		releaseAgents(serials);* 	}
     */





}


// Tal said she did it synchronize, but becuase she had a deadLock, for example; if for attack T1 need
// ewak1 AND ewak2,  and T2 need ewak2 and ewak1 (like in the exmaple input),
// if T1 will get ewak1, and than content switch will happend, and then T2 whill get ewak2........WE GOT DEAD LOCK.
//I think that better soultion for this problem will be to SORT the ewoks needes.
// like in the example before, if the ewoks needed will be in sorted orders, we wouldnt get DeadLock. (I think)....
//soultion : we will try BOTH, and see which one is better (I think mine is much better ;)   )

//YEP. IN OFFICE HOUR2 HE SAID THAT ITS NOT NEED TO BE SYNCHRONIZE.
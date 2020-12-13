package bgu.spl.mics.application.passiveObjects;

import java.util.concurrent.Semaphore;

/**
 * Passive data-object representing a forest creature summoned when HanSolo and C3PO receive AttackEvents.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You may add fields and methods to this class as you see fit (including public methods).
 */
public class Ewok {
    static int number = 1; // yanay add
    int serialNumber;
	boolean available;
	private Semaphore sem;

	public  Ewok(int sNumber){
        available = true;
        serialNumber = sNumber;
        sem = new Semaphore(1, true);
        number++;
    }
	
  
    /**
     * Acquires an Ewok
     */
    public void acquire() {

        try {
            sem.acquire();
//            System.out.println("Ewok number: " + this.getSerialNumber() + " is acquire! by " + Thread.currentThread().getName());
        }
        catch (InterruptedException e1){
//            System.out.println("ewok interrupted");
        }
        available = false;

		
    }

    /**
     * release an Ewok
     */
    public void release() {
    	available = true;
    	sem.release();
    }

    public int getSerialNumber(){
        return serialNumber;
    }

}

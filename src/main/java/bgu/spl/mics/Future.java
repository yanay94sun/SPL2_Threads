package bgu.spl.mics;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * A Future object represents a promised result - an object that will
 * eventually be resolved to hold a result of some operation. The class allows
 * Retrieving the result once it is available.
 * 
 * Only private methods may be added to this class.
 * No public constructor is allowed except for the empty constructor.
 */
public class Future<T> {
	private boolean isDone;
    private T result;
	
	/**
	 * This should be the the only public constructor in this class.
	 */
	public Future() {
		isDone=false;
		result=null;
	}
	
	/**
     * retrieves the result the Future object holds if it has been resolved.
     * This is a blocking method! It waits for the computation in case it has
     * not been completed.
     * <p>
     * @return return the result of type T if it is available, if not wait until it is available.
     * 	       
     */
	public T get() {
		synchronized (this) {
			while (!isDone) {
				try {
					wait();
				}
				catch (Exception e) { // maybe here InterruptedException.. see next practice

				}
			}
//			System.out.println("The result of the futures is: " + result);
			return result; // what is the results ????????????????????????????????
		}
	}
	
	/**
     * Resolves the result of this Future object.
     */
	public void resolve (T result) {
		this.result=result;
		isDone=true;
		synchronized (this) { // why synchronized before notifyAll?? mybe because we need evrybody to wakeup now
			notifyAll();
		}
		
	}
	
	/**
     * @return true if this object has been resolved, false otherwise
     */
	public boolean isDone() {
		return isDone;
	}
	
	/**
     * retrieves the result the Future object holds if it has been resolved,
     * This method is non-blocking, it has a limited amount of time determined
     * by {@code timeout}
     * <p>
     * @param timeout 	the maximal amount of time units to wait for the result.
     * @param unit		the {@link TimeUnit} time units to wait.
     * @return return the result of type T if it is available, if not, 
     * 	       wait for {@code timeout} TimeUnits {@code unit}. If time has
     *         elapsed, return null.
     */
	public T get(long timeout, TimeUnit unit) { //As part of the work one does not have to implement this get that gets time
		synchronized (this) {
			if (!isDone()) {
				try {
					unit.timedWait(this,timeout);
				} // ASK AVITAL ABOUT HANDELING InterruptedException, maybe just signture of throw InterruptedException Or ignore??
				catch (InterruptedException e) {

				}
			}
			return result;
		}

	}

}

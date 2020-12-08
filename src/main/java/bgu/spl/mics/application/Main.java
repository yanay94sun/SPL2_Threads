package bgu.spl.mics.application;


import bgu.spl.mics.Message;
import bgu.spl.mics.MessageBus;
import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.application.services.C3POMicroservice;
import bgu.spl.mics.application.services.HanSoloMicroservice;
import bgu.spl.mics.application.services.R2D2Microservice;

/** This is the Main class of the application. You should parse the input file,
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		/**ThreadCounter tc = ThreadCounter.getInstance();
		HanSoloMicroservice hanSolo = new HanSoloMicroservice();
		C3POMicroservice c3PO = new C3POMicroservice();
		Thread t1 = new Thread(hanSolo);
		Thread t2 = new Thread(c3PO);
		t1.start();
		t2.start();
		for (int i = 0 ; i < 100000 ; i++){
			System.out.println(tc.getCount());
		}
*/

	}
}
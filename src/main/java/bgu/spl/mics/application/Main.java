package bgu.spl.mics.application;


import bgu.spl.mics.application.passiveObjects.InitializeBattle;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;

/** This is the Main class of the application. You should parse the input file,
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {

		if (args.length!=0){
//			InitializeBattle a = new InitializeBattle();
			//prase json
			BufferedReader buffer;
			Gson g;
			JsonObject battle = new JsonObject();
			try {
				buffer = new BufferedReader(new FileReader(args[0]));
				g = new Gson();
				battle = g.fromJson(buffer, JsonObject.class);
			} catch (Exception e) {
				System.out.println("file not found");
			}
//        //builds the matrix of all the subscribers
//        Subscriber[][] subs = new Subscriber[4][];
			//reads from the json
			JsonArray attacksMicroservice = battle.getAsJsonArray("attacksMicroservice");
			JsonObject R2D2Microservice = battle.getAsJsonObject("R2D2Microservice");
			JsonObject LandoMicroservice = battle.getAsJsonObject("LandoMicroservice");
//			JsonObject Ewoks = battle.getAsJsonObject("Ewoks");

			System.out.println(attacksMicroservice);
			System.out.println(R2D2Microservice);
			System.out.println(LandoMicroservice);
			System.out.println(battle);


			//			a.run(args[0]);
//			a.outputFile(args[1]);
		}

		//PRASE JSON NEED TO BE HERE IN MAIN.

		//NEED HELP WITH PRASING...ASK AVITAL  @TODO

		//SHOULD PRASE INITIALIZE THE data we need? or should it be premtive objects, and then Load them as we want?


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
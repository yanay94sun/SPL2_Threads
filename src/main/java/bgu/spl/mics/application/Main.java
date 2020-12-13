package bgu.spl.mics.application;

import bgu.spl.mics.application.passiveObjects.Attack;
import bgu.spl.mics.application.passiveObjects.Ewok;
import bgu.spl.mics.application.passiveObjects.Ewoks;
import bgu.spl.mics.application.services.*;

import bgu.spl.mics.application.passiveObjects.InitializeBattle;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/** This is the Main class of the application. You should parse the input file,
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		if (args.length!=0){
			InitializeBattle m = new InitializeBattle();
			m.run(args[0]);
//			m.outputFiles(args[1]);

////			InitializeBattle a = new InitializeBattle();
//			//prase json
//			BufferedReader buffer;
//			Gson g;
//			JsonObject battle = new JsonObject();
//			try {
//				buffer = new BufferedReader(new FileReader(args[0]));
//				g = new Gson();
//				battle = g.fromJson(buffer, JsonObject.class);
//			} catch (Exception e) {
//				System.out.println("file not found");
			}
//        //builds the matrix of all the subscribers
//        Subscriber[][] subs = new Subscriber[4][];
			//reads from the json
//			JsonArray attacksMicroservice = battle.getAsJsonArray("attacksMicroservice");
//			JsonObject R2D2Microservice = battle.getAsJsonObject("R2D2Microservice");
//			JsonObject LandoMicroservice = battle.getAsJsonObject("LandoMicroservice");
//			JsonObject Ewoks = battle.getAsJsonObject("Ewoks");

//			System.out.println(attacksMicroservice);
//			System.out.println(R2D2Microservice);
//			System.out.println(LandoMicroservice);
//			System.out.println(battle);


			//			a.run(args[0]);
//			a.outputFile(args[1]);
//		}

		//PRASE JSON NEED TO BE HERE IN MAIN.

		//NEED HELP WITH PRASING...ASK AVITAL  @TODO

		//SHOULD PRASE INITIALIZE THE data we need? or should it be premtive objects, and then Load them as we want?

//		List<Integer> serials1 = new ArrayList<>();
//		serials1.add(1);
//		serials1.add(2);
//
//		List<Integer> serials2 = new ArrayList<>();
//		serials2.add(1);
//		serials2.add(2);
//
//		Attack attack1_test = new Attack(serials1, 1000);
//		Attack attack2_test = new Attack(serials2, 1000);
//
//		Ewoks ewoks = Ewoks.getInstance();
//		ewoks.load(new Ewok[]{new Ewok(1), new Ewok(2)});
//
//		ThreadCounter tc = ThreadCounter.getInstance();
//		HanSoloMicroservice hanSolo = new HanSoloMicroservice();
//		C3POMicroservice c3PO = new C3POMicroservice();
//		LeiaMicroservice leia = new LeiaMicroservice(new Attack[]{attack1_test, attack2_test});
//		R2D2Microservice r2D2 = new R2D2Microservice(2000);
//		LandoMicroservice landoMicroservice = new LandoMicroservice(2000);
//
//
//		Thread t1 = new Thread(leia);
//		Thread t2 = new Thread(c3PO);
//		Thread t3 = new Thread(hanSolo);
//		Thread t4 = new Thread(r2D2);
//		Thread t5 = new Thread(landoMicroservice);
//
//
//		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
//		t5.start();



//		leia.makeFutureQueue();
//		leia.checkFutures();

//		for (int i = 0 ; i < 100000 ; i++){
//			System.out.println(tc.getCount());
//		}


	}
}
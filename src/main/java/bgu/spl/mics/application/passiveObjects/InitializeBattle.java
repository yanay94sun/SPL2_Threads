package bgu.spl.mics.application.passiveObjects;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.ThreadCounter;

import bgu.spl.mics.application.services.*;


import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;

public class InitializeBattle {


    public void run(String filePath) {
        //prase json
        BufferedReader buffer;
        Gson g;
        JsonObject battle = new JsonObject();
        try {
            buffer = new BufferedReader(new FileReader(filePath));
            g = new Gson();
            battle = g.fromJson(buffer, JsonObject.class);
        } catch (Exception e) {
            System.out.println("file not found");
        }

        //reads from the json
        JsonArray attacks = battle.getAsJsonArray("attacks");
        JsonPrimitive r2d2Dur = battle.getAsJsonPrimitive("R2D2");
        JsonPrimitive landoDur = battle.getAsJsonPrimitive("Lando");
        JsonPrimitive ewoksNumNe = battle.getAsJsonPrimitive("Ewoks");


        //This is the Data you going to use
        Attack[] attacksArr = generateAttacks(attacks);
        int r2d2Duration = generateDuration(r2d2Dur);
        int landoDuration = generateDuration(landoDur);
        int numEwoksNeed = generateNumEwoks(ewoksNumNe);

        // generate and load the Ewoks
        Ewok[] ewokArr = addToEwoks(numEwoksNeed);
        Ewoks.getInstance().load(ewokArr);

        //Creating the microServices.
        ThreadCounter tc = ThreadCounter.getInstance();
        HanSoloMicroservice hanSolo = new HanSoloMicroservice();
        C3POMicroservice c3PO = new C3POMicroservice();
        LeiaMicroservice leia = new LeiaMicroservice(attacksArr);
        R2D2Microservice r2D2 = new R2D2Microservice(r2d2Duration);
        LandoMicroservice landoMicroservice = new LandoMicroservice(landoDuration);

        //Creating the Threads.
        Thread t1 = new Thread(leia);
        Thread t2 = new Thread(c3PO);
        Thread t3 = new Thread(hanSolo);
        Thread t4 = new Thread(r2D2);
        Thread t5 = new Thread(landoMicroservice);

        //Starting the Threads.

        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t1.start();

//        while (ThreadCounter.getInstance().getCount().get() != 4){
//        }
//        synchronized (this){
//            notifyAll();
//        }

        // calling leia methods
        leia.makeFutureQueue();

        leia.checkFutures();

        }

    // this Function is creating the Attacks array that Leia get as a Parameter.
    public Attack[] generateAttacks (JsonArray attacks){

        Attack[] attacks_arr = new Attack[attacks.size()];
        for (int i = 0; i < attacks.size(); i++) {
            List<Integer> serials = new LinkedList<>();
            JsonObject attackm = attacks.get(i).getAsJsonObject();
            JsonArray ewoksSerials = attackm.getAsJsonArray("serials");
            JsonPrimitive duration = attackm.getAsJsonPrimitive("duration");
            int dur = duration.getAsInt();
            for (int j = 0; j < ewoksSerials.size(); j++) {
                serials.add(ewoksSerials.get(j).getAsInt());
            }
            Collections.sort(serials); // rafael add
            attacks_arr[i] = new Attack(serials, dur);
        }
        return attacks_arr;
    }

    // this Function is creating the duration time for R2D2 and Lando.
    public int generateDuration (JsonPrimitive dur){
        return dur.getAsInt();
    }

    // this Function is creating the number of ewoks need for the attack (we are not using it).
    public int generateNumEwoks (JsonPrimitive num){
        return num.getAsInt();
    }

    // this Function is adding ewoks to ewoks array (in order to load them to Ewoks)
    public Ewok[] addToEwoks(int numEwoksNeed) {
        Ewok[] ewokArr = new Ewok[numEwoksNeed];
        for (int i = 0; i < numEwoksNeed; i++){
            ewokArr[i] = new Ewok(i + 1);
        }
        return ewokArr;
    }


}


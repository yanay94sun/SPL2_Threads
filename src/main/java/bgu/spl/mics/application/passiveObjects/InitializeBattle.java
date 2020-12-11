package bgu.spl.mics.application.passiveObjects;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.ThreadCounter;

import bgu.spl.mics.application.services.*;


import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
//        //builds the matrix of all the subscribers
//        Subscriber[][] subs = new Subscriber[4][];
        //reads from the json
        JsonArray attacks = battle.getAsJsonArray("attacks");
        JsonObject R2D2 = battle.getAsJsonObject("R2D2");
        JsonObject Lando = battle.getAsJsonObject("Lando");
        JsonObject Ewoks = battle.getAsJsonObject("Ewoks");




//
//        //creates the inventory
//        Inventory.getInstance().load(addToInventory(inventory));
//        //creates the squad
//        Squad.getInstance().load(addToSquad(squad));
//        int numOfM = services.get("M").getAsInt();
//        int numOfMoneyPenny = services.get("Moneypenny").getAsInt();
//        JsonArray intelligence = services.getAsJsonArray("intelligence");
//        int time = services.get("time").getAsInt();
//        //creates the ms
//        M[] msubs = setMs(numOfM);
//        //creates the moneypennys
//        Moneypenny[] moneypennnySubs = setMoneypennys(numOfMoneyPenny);
//        //creates the intelligences
//        Intelligence[] intelligenceSubs = setIntelligence(intelligence);
//        //creates the q
//        Q[] q = {new Q()};
//        subs[0] = intelligenceSubs;
//        subs[2] = q;
//        subs[1] = msubs;
//        subs[3] = moneypennnySubs;
//        //calculates the number of subscribers
//        int numOfSubsAndPubs = numOfM + numOfMoneyPenny + intelligenceSubs.length + q.length;
//        //creates the threads and starts them
//        Thread[] threads = new Thread[numOfSubsAndPubs+1];
//        int index = 0;
//        for (int i = 0; i <subs.length;i++){
//            for (int j = 0; j<subs[i].length;j++){
//                Thread thread = new Thread(subs[i][j]);
//                threads[index] = thread;
//                thread.setName(subs[i][j].getName());
//                thread.start();
//                index++;
//            }
//        }
//        //makes sure to not start the time thread before all threads are initiliaze
//        ThreadCounter threadCounter = ThreadCounter.GetInstance();
//
//        while (numOfSubsAndPubs != threadCounter.getCount().get()){
//
//        }
//        //starts time thread
//        TimeService timeService = new TimeService(time);
//        Thread timeThread = new Thread(timeService);
//        threads[index] = timeThread;
//        timeThread.setName(timeService.getName());
//        timeThread.start();
//
//        //kills the threads
//        for (int i =0; i<threads.length;i++){
//            try {
//                threads[i].join();
//            }
//            catch (Exception e){
//            }
        }
    }






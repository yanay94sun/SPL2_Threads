package bgu.spl.mics.application.passiveObjects;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Passive data-object representing a Diary - in which the flow of the battle is recorded.
 * We are going to compare your recordings with the expected recordings, and make sure that your output makes sense.
 * <p>
 * Do not add to this class nothing but a single constructor, getters and setters.
 */
public class Diary {
//NEED TO BE SINGELTON!!

    //field TotalAttack should be atomic!

    private static Diary instance=new Diary();
    private AtomicInteger totalAttacks;

    private Diary(){
        totalAttacks = new AtomicInteger(0);

    }

    public static Diary getInstance() {
        return instance;
    }

    public int getTotalAttacks(){
        return totalAttacks.get();
    }

    public void incrementTotalAttacks(){
        totalAttacks.incrementAndGet();
    }



    public void printToFile(String filename) {
        Gson g = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setPrettyPrinting().create();
        String diary=g.toJson(Diary.getInstance());
        try(Writer write=new FileWriter(filename)){
            write.write(diary);
        } catch (Exception e) {

        } //empty block???
    }

}

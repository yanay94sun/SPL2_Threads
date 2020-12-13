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
//    int totalAttacks;
    long HanSoloFinish;
    long C3POFinish;
    long R2D2Deactivate;
    long LeiaTerminate;
    long HanSoloTerminate;
    long C3POTerminate;
    long R2D2Terminate;
    long LandoTerminate;


    private Diary(){
        totalAttacks = new AtomicInteger(0);

    }

    public static Diary getInstance() {
        return instance;
    }

    // TotalAttacks
    public int getTotalAttacks(){
        return totalAttacks.get();
    }

    public void setTotalAttacks(AtomicInteger curAttacks){
        this.totalAttacks = curAttacks;
    }
    public void incrementTotalAttacks(){
        totalAttacks.incrementAndGet();
    }

    // HanSoloFinish
    public long getHanSoloFinish(){
        return this.HanSoloFinish;
    }
    public void setHanSoloFinish(long hanSoloFinishTime){
        this.HanSoloFinish = hanSoloFinishTime;
    }

    // C3POFinish
    public long getC3POFinish(){
        return this.C3POFinish;
    }
    public void setC3POFinish(long c3POFinishTime){
        this.C3POFinish = c3POFinishTime;
    }

    // R2D2Deactivate
    public long getR2D2Deactivate(){
        return this.R2D2Deactivate;
    }
    public void setR2D2Deactivate(long r2D2DeactivateTime){
        this.R2D2Deactivate = r2D2DeactivateTime;
    }

    // LeiaTerminate
    public long getLeiaTerminate(){
        return this.LeiaTerminate;
    }
    public void setLeiaTerminate(long leiaTerminate){
        this.LeiaTerminate = leiaTerminate;
    }

    // HanSoloTerminate
    public long getHanSoloTerminate(){
        return this.HanSoloTerminate;
    }
    public void setHanSoloTerminate(long hanSoloTerminate){
        this.HanSoloTerminate = hanSoloTerminate;
    }

    // C3POTerminate
    public long getC3POTerminate(){
        return this.C3POTerminate;
    }
    public void setC3POTerminate(long c3POTerminate){
        this.C3POTerminate = c3POTerminate;
    }

    // R2D2Terminate
    public long getR2D2Terminate(){
        return this.R2D2Terminate;
    }
    public void setR2D2Terminate(long r2D2Terminate){
        this.R2D2Terminate = r2D2Terminate;
    }

    // LandoTerminate
    public long getLandoTerminate(){
        return this.LandoTerminate;
    }
    public void setLandoTerminate(long leiaTerminate){
        this.LeiaTerminate = leiaTerminate;
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

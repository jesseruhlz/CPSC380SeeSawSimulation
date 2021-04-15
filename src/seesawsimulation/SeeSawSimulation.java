package seesawsimulation;
import java.util.concurrent.*; //this is needed to use semaphore in java
import java.io.*;
import java.util.*;

/**
 * Name: Jesse Ruhl
 * Chapman ID: 
 * Course: CPSC 380
 */

public class SeeSawSimulation {
    
    static double fredHeight = 1.0; // assuming at time 0 person A at low end
    static double wilmaHeight = 7.0; // assuming at time 0 person B at high end
    static boolean fredUp = true;
    static int time = -0;
    
    // try to place in main later to see if it still works this way
    static Semaphore sem = new Semaphore(1); 

    //main function
    public static void main(String[] args) {
        // TODO code application logic here
        //Thread 1 for Fread created here
        Thread t1 = new Thread(new FredSee(SeeSawSimulation.sem));
        // Thread 2 for Wilma created here
        
        //try to start each thread and join each thread
        try{
            t1.start();
            t1.join();
        }
        catch(InterruptedException ie){
            ie.printStackTrace();
        }
       
    }
    
    // calculate the height at each interval
    public static void calculateHeight(Semaphore sem){
        try{
            sem.acquire();
            
            if(wilmaHeight == 1){
                fredUp = false; // meaning he is ground level
                System.out.println();
                System.out.println("Wilma is rising.");
            }
            else if (fredHeight == 1){
                fredUp = true;
                System.out.println();
                System.out.println("Fred is rising.");
            }
            try{
                if(fredUp){
                    wilmaHeight = wilmaHeight - 1;
                    fredHeight = fredHeight +1;
                }
                else{
                    wilmaHeight = wilmaHeight +  1;
                    fredHeight = fredHeight - 1;
                }
                time++;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            sem.release();
        }
        catch(InterruptedException ie){
            System.out.println("Interrupted Exception");
        }
    }
        
    }
    
    // will ouput the updates on each interval
    /*
    public static void outputSema(Semaphore outSem){
        
    }
    */
    
    class FredSee extends Thread implements Runnable{
        Semaphore sem;
        
        public FredSee(Semaphore sem){
            this.sem = sem;
        }
        public void run(){
            try{
                for(int x = -0; x < 50; x++){
                    SeeSawSimulation.calculateHeight(sem);
                    Thread.sleep(1000);
                }
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
    }
    
    class WilmaSaw extends Thread implements Runnable{
        Semaphore sem;
        
        public WilmaSaw(Semaphore sem){
            this.sem = sem;
        }
        public void run(){
            try{
                for(int y = -0; y < 50; y++){
                    SeeSawSimulation.calculateHeight(sem);
                    Thread.sleep(1000);
                }
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
    
}

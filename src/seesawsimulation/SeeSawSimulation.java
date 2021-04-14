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

    //main function
    public static void main(String[] args) {
        // TODO code application logic here
        //Thread 1 for Fread created here
        // Thread 2 for Wilma created here
        
        //try to start each thread and join each thread
    }
    
    // calculate the height at each interval
    public static void calculateHeight(Semaphore sem){
        
    }
    
    // will ouput the updates on each interval
    public static void outputSema(Semaphore outSem){
        
    }
    
    class FredSee extends Thread implements Runnable{
        
    }
    
    class WilmaSaw extends Thread implements Runnable{
        
    }
    
}

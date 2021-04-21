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
    static Semaphore outSem = new Semaphore(1);

    //main function
    public static void main(String[] args) {
        // TODO code application logic here
        //Thread 1 for Fread created here
        Thread t1 = new Thread(new FredSee(SeeSawSimulation.sem, SeeSawSimulation.outSem));
        Thread t2 = new Thread(new WilmaSaw(SeeSawSimulation.sem, SeeSawSimulation.outSem));
        // Thread 2 for Wilma created here
        
        //try to start each thread and join each thread
        try{
            t1.start();
            t2.start();
            
            t1.join();
            t2.join();
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
                //if fred is rising he should move 1 foot up per second
                //likewise wilma will fall 1 foot per second
                if(fredUp){
                    wilmaHeight = wilmaHeight - 1;
                    fredHeight = fredHeight +1;
                }
                //if wilma is rising she should move 1.5 feet up per second
                //and fred should fall 1.5 feet per second
                //becuase of this there will be less print statements since it will go
                //7->5.5->4->2.5->1 so should expect only 4 changing positions (exluding start position 1)
                //likewise for the person going up
                else{
                    wilmaHeight = wilmaHeight +  1.5;
                    fredHeight = fredHeight - 1.5;
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
            // will ouput the updates on each interval
    
    public static void outSemaphore(Semaphore outSem){
        try{
            /*
            for (int i = 0; i < 10; i++){
                outSem.acquire();
                if(time == 1){
                    System.out.println("Intial Positions");
                }
                System.out.println("Time " + time + "\t\tFred's Height: " + fredHeight + "\t\tWilma's Height: " + wilmaHeight);
                outSem.release();
            }*/
            
            outSem.acquire();
            if(time == 1){
                System.out.println("Intial Positions");
            }
            System.out.println("Time " + time + "\t\tFred's Height: " + fredHeight + "\t\tWilma's Height: " + wilmaHeight);
            outSem.release();
        }
        catch(InterruptedException ie){
            System.out.println("Interrupted Exception");
        }
    }
}
    
    class FredSee extends Thread implements Runnable{
        Semaphore sem;
        Semaphore outSem;
        
        public FredSee(Semaphore sem, Semaphore outSem){
            this.sem = sem;
            this.outSem = outSem;
        }
        
        public void run(){
            try{
                for(int x = -0; x < 50; x++){ //using 50 since that will produce 10 cycles of seesaw going up and down
                    SeeSawSimulation.calculateHeight(sem);
                    SeeSawSimulation.outSemaphore(outSem);
                    Thread.sleep(1000); // in milliseconds so 1000 would be 1 sec wait time between each thread
                }
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
    }
}
    
    class WilmaSaw extends Thread implements Runnable{
        Semaphore sem;
        Semaphore outSem; 
        
        public WilmaSaw(Semaphore sem, Semaphore outSem){
            this.sem = sem;
            this.outSem = outSem;
        }

        public void run(){
            try{
                for(int y = -0; y < 50; y++){ //using 50 since that will produce 10 cycles of seesaw going up and down
                    SeeSawSimulation.calculateHeight(sem);
                    SeeSawSimulation.outSemaphore(outSem);
                    Thread.sleep(1000); // in milliseconds so 1000 would be 1 sec wait time between each thread
                }
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
    

<<<<<<< HEAD
import synthesizer.ArrayRingBuffer;

import java.io.IOError;
import java.io.IOException;

import static java.lang.Math.pow;
=======
import synthesizer.GuitarString;
>>>>>>> temp_hw1

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */

public class GuitarHeroLite {
    private double[] freqList;
    private static final int SR = 44100;
    private static final char[] keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ".toCharArray();
    private static final double CONCERT_A = 440.0;
<<<<<<< HEAD
    private static final double CONCERT_C = CONCERT_A * pow(2, 3.0 / 12.0);
    private ArrayRingBuffer<Double>[] arr;
    public static int findCharOrder(char key){
        for (int i = 0; i < keyboard.length; i ++) {
            if (keyboard[i] == key) {
                return i;
            }
        }
        return -1;
    }
    public void makeFreqList(){
        freqList = new double[keyboard.length];
        for (int i = 0; i < keyboard.length; i++) {
            char key = keyboard[i];
            int n = findCharOrder(key);
            double freq = 440 * java.lang.Math.pow(2.0, (n - 24.0) / 12);
            freqList[i] = freq;
        }
    }
    public void printFreqList(){
        for (int i = 0; i < keyboard.length; i++) {
            System.out.print(freqList[i] + ", ");
        }
    }

    public void makeArray(double[] frequencyLst){
        arr = (ArrayRingBuffer<Double>[])new Object[keyboard.length];
        for (int i = 0; i < keyboard.length; i ++){
            int lngth = (int) java.lang.Math.round(SR/frequencyLst[i]);
            arr[i] = new ArrayRingBuffer<Double>(lngth);
        }
    }
=======
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final char[] keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ".toCharArray();

    public GuitarString[] guitarArray;

    public int getOrder(char k){
        for (int i = 0; i < keyboard.length; i++) {
            if (keyboard[i] == k){
                return i;
            }
        }
        return 0;
    }

    public void makeStringList(){
        guitarArray = new GuitarString[keyboard.length];
        for (int i = 0; i < keyboard.length; i++) {
            double freq = 440 * java.lang.Math.pow(2.0, (i-24)/12.0);
            guitarArray[i] = new GuitarString(freq);
        }
    }

    public void pluckString(char k){
        int ord = getOrder(k);
        guitarArray[ord].pluck();
    }

    //return the sum of first item of array of every string
    public double sampleAllString(){
        double ret = 0.0;
        for (int i = 0; i < keyboard.length; i++) {
            ret += guitarArray[i].sample();
        }
        return ret;
    }

    //make every guitar string in the list tick
    public void tickAll(){
        for (int i = 0; i < keyboard.length; i++){
            guitarArray[i].tic();
        }
    }





>>>>>>> temp_hw1

    public static void main(String[] args) {
        GuitarHeroLite ghl = new GuitarHeroLite();
        ghl.makeStringList();
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                ghl.pluckString(key);
            }

            /* compute the superposition of samples */
            //double sample = stringA.sample() + stringC.sample();
            double sample = ghl.sampleAllString();


            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            //stringA.tic();
            //stringC.tic();
            ghl.tickAll();
        }
    }




    public static void main1(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                }
            }

        /* compute the superposition of samples */
            double sample = stringA.sample() + stringC.sample();

        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            stringA.tic();
            stringC.tic();
        }
    }
}


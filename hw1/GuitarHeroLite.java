import java.io.IOError;
import java.io.IOException;

import static java.lang.Math.pow;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */

public class GuitarHeroLite {
    private static final int SR = 44100;
    private static final char[] keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ".toCharArray();
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * pow(2, 3.0 / 12.0);
    private double[][] arr;
    public static int findCharOrder(char key){
        for (int i = 0; i < keyboard.length; i ++) {
            if (keyboard[i] == key) {
                return i;
            }
        }
        return -1;
    }
    private static void makeNoise(char key){

        int i = findCharOrder(key);
        double freq = 440 * java.lang.Math.pow(2.0, (i-24.0)/12);
        synthesizer.GuitarString string = new synthesizer.GuitarString(freq, arr[i]);
    }

    public void makeArray(double[] frequencyLst){
        arr = new double[keyboard.length][];
        for (int i = 0; i < keyboard.length; i ++){
            int lngth = (int) java.lang.Math.round(SR/frequencyLst[i]);
            arr[i] = new double[lngth];
        }

    }

    public static void main(String[] args) {
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


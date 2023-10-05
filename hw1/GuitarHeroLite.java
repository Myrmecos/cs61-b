import synthesizer.GuitarString;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHeroLite {
    private static final double CONCERT_A = 440.0;
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


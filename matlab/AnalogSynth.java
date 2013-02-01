public class AnalogSynth extends Thread {
    public int max_val = 0;
    public int threshold = 200;
    // take weighted sum of two arrays
    public static double[] sum(double[] a, double[] b, double awt, double bwt) {

        // precondition: arrays have the same length
        assert (a.length == b.length);

        // compute the weighted sum
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i]*awt + b[i]*bwt;
        }
        return c;
    } 

    public void run() {
        hz = 0;
        while(true) {
        int N = (int) (StdAudio.SAMPLE_RATE * 0.01);
        double[] a = new double[N+1];
        double[] b = new double[N+1];
        double[] c = new double[N+1];
        for (int i = 0; i <= N; i++) {
            a[i] = tone2(1, i);
            b[i] = tone2(2, i);
            c[i] = tone2(0.5, i);

        }
        double[] h  = sum(b, c, .5, .5);
        double[] f  = sum(a, h, .5, .5);

        //System.out.println(hz);
        if(max_val > threshold) 
        StdAudio.play(f);
        }
    }
    public double tone2(double m, int t) { 
                return Math.sin(2 * Math.PI * m * t * hz / StdAudio.SAMPLE_RATE);
    }
    public double hz;
    // create a pure tone of the given frequency for the given duration
    public static double[] tone(double hz, double duration) { 
        int N = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[N+1];
        for (int i = 0; i <= N; i++) {
            a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        }
        return a; 
    } 

    // create a note with harmonics of of the given pitch, where 0 = concert A
    public static double[] note(int pitch, double t) {
        double hz = 440.0 * Math.pow(2, pitch / 12.0);
        double[] a  = tone(hz, t);
        double[] hi = tone(2*hz, t);
        double[] lo = tone(hz/2, t);
        double[] h  = sum(hi, lo, .5, .5);
        return sum(a, h, .5, .5);
    }


//    // read in notes from standard input and play them on standard audio
//    public static void main(String[] args) {
//
//        // read in pitch-duration pairs from standard input
//        while (!StdIn.isEmpty()) {
//            int pitch = StdIn.readInt();
//            double duration = StdIn.readDouble();
//            double[] a = note(pitch, duration);
//            StdAudio.play(a);
//        }
//
//        // needed to terminate program - known Java bug
//        System.exit(0);
//    } 
    private int[][] data;
    public void updateData(int[][] newdata, int th) {
        try {
            threshold = th;
            data = newdata;      
            int max = 0;
            int x = 0;
            int y = 0;
            for(int i = 0; i < 30; i++) {
                for(int j = 0; j < 30; j++) {
                    if (data[i][j] > max) {
                        max = data[i][j];
                        x = j;
                        y = i;
                    }
                }
            }
            hz = (double)x / 30 * 800;
            System.out.println(hz);
            max_val = max;
        } catch (Exception e) {
            e.printStackTrace();
        } 
    } 
}

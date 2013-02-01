//package music;

import javax.sound.midi.*;


public class Synth extends Thread {
    public int OFFSET = 60;
    public int instrument;
    Synthesizer synthesizer;

    public int threshold = 200; 
private int notes[][] = {

    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23},
    {14, 14, 14, 14, 14, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 23, 23, 23, 23, 23}

};
    private int[][] data;
    public enum Notes {
        C(60), D(62), E(64), F(65), G(67), A(69), B(71), HC(72);
        private int noteVal;
        Notes(int noteVal){
            this.noteVal = noteVal;
        }
        public int noteVal() { return noteVal; }
    };
             public MidiChannel[] channel; 
             public boolean curr;
    public void run() {
        try {
            instrument = 0;
            curr = false;
            int x = 0;
            Notes[] notelist = Notes.values();   
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channel = synthesizer.getChannels();     
            boolean noteOn[] = {
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false
            };

            //boolean[][] on = new boolean[55][67];
            boolean[][] on = new boolean[30][30];

          Instrument[] ins = synthesizer.getAvailableInstruments();
        //int notes[] = {60, 62, 64, 65, 67, 69, 71, 72, 74};
              synthesizer.loadInstrument(ins[instrument]);
              channel[0].programChange(ins[instrument].getPatch().getProgram());

            //data = new int[55][67];
            data = new int[30][30];
            int chan[] = { -1, -1, -1, -1, -1, -1, -1, -1 };

            boolean old = curr;
            while(true) {

                //Thread.sleep(50);
                //if (curr == old) {
                    //channel[0].allSoundOff();
                for(int i = 0; i < 30; i++) {
                    for(int j = 0; j < 30; j++) {
                        //System.out.println(notelist[x].noteVal() + ", " + (data[i][j] - 300)/8 + ", " + x);  
                //Thread.sleep(10);
                        if (data[i][j] > 200 && !noteOn[notes[i][j]] && !on[i][j]) {
                            System.out.println(i + "," + j);
                            System.out.println("Note: " + (notes[i][j]+OFFSET));
                            System.out.println("Pressure: " + (data[i][j] - 100));
                            System.out.println("Instrument: " + instrument);
                            channel[x%7].noteOn(notes[i][j]+OFFSET, (data[i][j] - 100));
                            chan[x] = notes[i][j];
                            noteOn[notes[i][j]] = true;
                            on[i][j] = true;

                        } else if (data[i][j] < 200 && noteOn[notes[i][j]] && on[i][j]) {
                            System.out.println("!" + i + "," + j);
                            channel[x%7].noteOff(notes[i][j]+OFFSET);
                            noteOn[notes[i][j]] = false;
                            chan[x] = -1;
                            on[i][j] = false;
                        } else {
                            //on[i][j] = false;
                        }
                        //x++;
                        if(x > 7) x = 0;
                    }
                //}
                old = curr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData(int[][] newdata, int inst) {
        data = newdata;      
        curr = !curr;
        instrument = inst;
          Instrument[] ins = synthesizer.getAvailableInstruments();
        //int notes[] = {60, 62, 64, 65, 67, 69, 71, 72, 74};
              synthesizer.loadInstrument(ins[instrument]);
              channel[0].programChange(ins[instrument].getPatch().getProgram());
    }

  public void updateThreshold(int th) {
      threshold = th;
  }

  public static int[][] add1(int[][] i){
      return i;
  }
}    

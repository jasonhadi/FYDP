#define SPEED 1
#define ROWS 30
#define COLS 30
int mux_io[9] = {31, 38, 39, 40, 41, 42, 43, 44, 45};
int mux_addr[9][4] ={
    {31, 31, 31, 31},
    {27, 0, 1, 2},
    {7, 8, 9, 10},
    {15, 16, 17, 26},
    {20, 19, 18, 25},
    {3, 4, 5, 6},
    {11, 12, 13, 14},
    {22, 21, 24, 23},
    {31, 31, 31, 31}
};
int math[16][4] = {
    {0, 0, 0, 0},
    {0, 0, 0, 1},
    {0, 0, 1, 0},                              
    {0, 0, 1, 1},
    {0, 1, 0, 0},
    {0, 1, 0, 1},
    {0, 1, 1, 0},                                                  
    {0, 1, 1, 1},
    {1, 0, 0, 0},
    {1, 0, 0, 1},
    {1, 0, 1, 0},
    {1, 0, 1, 1},
    {1, 1, 0, 0},
    {1, 1, 0, 1},
    {1, 1, 1, 0},
    {1, 1, 1, 1}
};

int columns[64][2] = {
    {1, 8},
    {1, 9},
    {1, 10},
    {1, 11},
    {1, 12},
    {1, 7},
    {1, 6},
    {1, 5},
    {1, 4},
    {1, 3},
    {1, 13},
    {1, 14},
    {1, 15},
    {2, 8},
    {2, 9},
    {1, 2},
    {1, 1},
    {1, 0},
    {2, 7},
    {2, 6},
    {2, 10},
    {2, 11},
    {2, 12},
    {2, 13},
    {2, 14},
    {2, 5},
    {2, 4},
    {2, 3},
    {2, 2},
    {2, 1},
    {2, 15},
    {4, 1},
    {4, 0},
    {3, 7},
    {3, 6},
    {2, 0},
    {3, 5},
    {3, 4},
    {3, 3},
    {3, 2},
    {3, 1},
    {3, 0},
    {3, 8},
    {3, 9},
    {3, 10},
    {3, 11},
    {3, 12},
    {3, 13},
    {3, 14},
    {3, 15},
    {4, 11},
    {4, 12},
    {4, 13},
    {4, 14},
    {4, 15},
    {4, 6},
    {4, 5},
    {4, 4},
    {4, 3},
    {4, 2},
    {4, 8},
    {4, 9},
    {4, 7},
    {4, 10}
};

int rows[55][2] = {
    {5, 6},
    {5, 5},
    {5, 7},
    {5, 8},
    {5, 9},
    {5, 0},
    {5, 1},
    {5, 2},
    {5, 3},
    {5, 4},
    {5, 11},
    {5, 10},
    {6, 8},
    {6, 9},
    {6, 10},
    {5, 12},
    {5, 13},
    {5, 14},
    {5, 15},
    {6, 7},
    {6, 11},
    {6, 12},
    {6, 13},
    {6, 14},
    {6, 15},
    {6, 5},
    {6, 4},
    {6, 3},
    {6, 2},
    {6, 1},
    {7, 8},
    {7, 9},
    {7, 10},
    {7, 11},
    {7, 12},
    {7, 7},
    {7, 6},
    {7, 5},
    {7, 4},
    {7, 3},
    {7, 15},
    {8, 7},
    {8, 6},
    {8, 5},
    {8, 4},
    {7, 2},
    {7, 1},
    {7, 0},
    {7, 13},
    {7, 14},
    {8, 9},
    {8, 10},
    {8, 1},
    {8, 3},
    {8, 2}
};

void setup(){
    Serial.begin(9600);
    for(int i = 1; i < 9; i++){
        for(int j =0; j < 4; j++){
            pinMode(mux_addr[i][j], OUTPUT);
        }
    }
}

int count = 0;
void loop(){
    Serial.println(-1);
    int resultC[ROWS][COLS];
    int resultR[ROWS][COLS];
    bool mode = true;
    set_io(mode);
    //for(int i = 0; i < COLS; i++){
    //    set_address(columns[i][0], columns[i][1]);
    //    digitalWrite(mux_io[columns[i][0]], HIGH);
    //    for(int j = 0; j < ROWS; j++){
    //        set_address(rows[j][0], rows[j][1]);
    //        //delay(SPEED);
    //        resultC[i][j] = analogRead(mux_io[rows[j][0]]);
    //    }
    //    digitalWrite(mux_io[columns[i][0]], LOW);
    //}
    //print_result(resultC);

    //delay(SPEED);

   mode = false;
   set_io(mode);
   for(int i = 0; i < ROWS; i++){
       set_address(rows[i][0], rows[i][1]);
       digitalWrite(mux_io[rows[i][0]], HIGH);
       for(int j = 0; j < COLS; j++){
           set_address(columns[j][0], columns[j][1]);
           //delay(SPEED);
           resultR[j][i] = analogRead(mux_io[columns[j][0]]);
       }
       digitalWrite(mux_io[rows[i][0]], LOW);
   }
   print_result(resultR);
    


    //if(count % 1000 == 0){
    //    Serial.print(F("COUNT!"));
    //    Serial.println(count);
    //} 

 
    //delay(500);
    //set_io(true);
    //set_address(1, 7);
    //digitalWrite(mux_io[1], HIGH);
    //delay(500);
    //digitalWrite(mux_io[1], LOW);
}

void set_pin(int pin, int mode){
    if (mode == 0) digitalWrite(pin, LOW);
    else digitalWrite(pin, HIGH);
}

void set_address(int mux, int address){
    set_pin(mux_addr[mux][0], math[address][3]);
    set_pin(mux_addr[mux][1], math[address][2]);
    set_pin(mux_addr[mux][2], math[address][1]);
    set_pin(mux_addr[mux][3], math[address][0]);
}
 
void set_io(bool mode){ //mode true: columns OUT/rows IN 
     pinMode(mux_io[1], (mode) ? OUTPUT : INPUT);
     pinMode(mux_io[2], (mode) ? OUTPUT : INPUT);
     pinMode(mux_io[3], (mode) ? OUTPUT : INPUT);
     pinMode(mux_io[4], (mode) ? OUTPUT : INPUT);
     
     pinMode(mux_io[5], (mode) ? INPUT : OUTPUT);
     pinMode(mux_io[6], (mode) ? INPUT : OUTPUT);
     pinMode(mux_io[7], (mode) ? INPUT : OUTPUT);
     pinMode(mux_io[8], (mode) ? INPUT : OUTPUT);

     for(int i = 1; i < 9; i++){
         digitalWrite(mux_io[i], LOW);
     }
}

void print_result(int result[ROWS][COLS]){
    for(int i = 0; i < COLS; i++){
        for(int j = 0; j < ROWS; j++){
            Serial.print(result[i][j]);
            Serial.print(" ");
    //        Serial.print("\t");
        }
        Serial.print("\n");
    }
    //Serial.println("================================");
}
  

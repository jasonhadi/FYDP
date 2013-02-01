import java.awt.*;
import java.awt.event.InputEvent;

public class Touchpad extends Thread{
    private int[][] data;
    public boolean curr;
    public int threshold = 500;


    boolean clicking = false;
    int Xmax = 0;
    int Ymax = 0;
    int prevDeltaX = 0;
    int prevDeltaY = 0;
    static Point movement = new Point(); 

    static Point previousMax = new Point();
    static Point currentMax = new Point();

    public Touchpad()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenResolution = toolkit.getScreenSize();

        Xmax = 5;
        Ymax = 5;
//        Xmax = screenResolution.width / 60;
//        Ymax = screenResolution.height / 60;
        data = new int[30][30];
    }

    public void run() {
        while (true) {
            try {
                if(detectMovement()) {  
                    PointerInfo currentPosition = MouseInfo.getPointerInfo();

                    int currentX = currentPosition.getLocation().x;
                    int currentY = currentPosition.getLocation().y;

                    try {
                        //joystick mode
                        //if (movement.X > 20) movement.X = 20;
                        //if (movement.Y > 20) movement.Y = 20;
                        //if (currentMax.X < 20) movement.X = -10;
                        //else movement.X = 10;
                        //if (currentMax.Y < 20) movement.Y = -10;
                        //else movement.Y = 10;
                        System.out.println(movement.X + " " + movement.Y);
                        //end joystick mode
                        if(movement.X < 6 || movement.Y < 6) {
                            moveTo(currentX+movement.X, currentY+movement.Y);
                        }
                        //click if pressure is hard enough
//                        if(data[currentMax.X][currentMax.Y]> 400)
//                        {
//                            Robot cursor = new Robot();
//                            cursor.mousePress(InputEvent.BUTTON1_MASK);
//                            cursor.mouseRelease(InputEvent.BUTTON1_MASK);
//                        }
                    } catch (Exception e1) {}
                }
            } catch (Exception e) {}
            //old = curr;
        }
    }


    public boolean detectMovement()
    {		
        findMaxPoint();
        if (previousMax.X == currentMax.X && previousMax.Y == currentMax.Y)
            return false;
        else {
            int offsetX = currentMax.X - previousMax.X;
            int offsetY = currentMax.Y - previousMax.Y;

            movement.setX(Xmax*offsetX);
            movement.setY(Ymax*offsetY);

            //update previous max point
            previousMax.X = currentMax.X;
            previousMax.Y = currentMax.Y;
            return true;
        }
    }

    public void moveTo(int end_x, int end_y) {
        PointerInfo currentPosition = MouseInfo.getPointerInfo();

        int start_x = currentPosition.getLocation().x;
        int start_y = currentPosition.getLocation().y;
        try {
            Robot robot = new Robot();
            int count = 100;
            for (int i=0; i<count; i++){  
                int mov_x = ((end_x * i)/count) + (start_x*(count-i)/count);
                int mov_y = ((end_y * i)/count) + (start_y*(count-i)/count);
                robot.mouseMove(mov_x,mov_y);
                robot.delay(5);
            }
        } catch (Exception e) {}
    }

    public void findMaxPoint() {
        int max = 150;
        try {
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    if(data[i][j] > max ) { //&& data[i][j] < 350)  {
                        max = data[i][j];
                        currentMax.X = i;
                        currentMax.Y = j;
                    }

                }
            }

            if(max == 150)
            {
                currentMax.X = 0;
                currentMax.Y = 0;
                previousMax.X = 0;
                previousMax.Y = 0;
            }

            Robot cursor = new Robot();
            if (max > threshold && !clicking) {
                cursor.mousePress(InputEvent.BUTTON1_MASK);
                clicking = true;
            }
            if (max < threshold && clicking) {
                cursor.mouseRelease(InputEvent.BUTTON1_MASK);
                clicking = false;
            } 
            } catch (Exception e) {}
    }

    public void updateData(int[][] newdata, int th) {
        data = newdata;      
        curr = !curr;
        threshold = th;
    }                 
}

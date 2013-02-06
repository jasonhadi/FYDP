package projectex;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;
import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.MouseAdapter;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.newt.event.awt.AWTMouseAdapter;

public class Heatmap implements GLEventListener {

  private float view_rotx = 20.0f, view_roty = 30.0f, view_rotz = 0.0f;
    private double theta = 0;
    private double s = 0;
    private double c = 0;

  private boolean mouseRButtonDown = false;
  private int prevMouseX, prevMouseY;

    public static void main(String[] args) {
     java.awt.Frame frame = new java.awt.Frame("Heatmap Test");
    frame.setSize(1000, 1000);
    frame.setLayout(new java.awt.BorderLayout());
 
        final Animator animator = new Animator();
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent e) {
          // Run this on another thread than the AWT event queue to
          // make sure the call to Animator.stop() completes before
          // exiting
          new Thread(new Runnable() {
              public void run() {
                animator.stop();
                System.exit(0);
              }
            }).start();
        }
      });

    GLCanvas canvas = new GLCanvas();
    animator.add(canvas);
    // GLCapabilities caps = new GLCapabilities(GLProfile.getDefault());
    // GLCanvas canvas = new GLCanvas(caps);

    final Heatmap gears = new Heatmap();
    canvas.addGLEventListener(gears);

    frame.add(canvas, java.awt.BorderLayout.CENTER);
    frame.validate();

    frame.setVisible(true);
    animator.start(); 
//        GLProfile glp = GLProfile.getDefault();
//        GLCapabilities caps = new GLCapabilities(glp);
//        GLCanvas canvas = new GLCanvas(caps);
//
//        Frame frame = new Frame("AWT Window Test");
//        frame.setSize(300, 300);
//        frame.add(canvas);
//        frame.setVisible(true);
//
        //frame.addWindowListener(new WindowAdapter() {
        //    public void windowClosing(WindowEvent e) {
        //        System.exit(0);
        //    }
        //});

        //canvas.addGLEventListener(new Heatmap());

        //Animator animator = new Animator(canvas);
        //animator.add(canvas);
        //animator.start();
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        update();
        render(drawable);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        MouseListener gearsMouse = new SimpleMouseAdapter();    
        KeyListener gearsKeys = new SimpleKeyAdapter();

        if (drawable instanceof Window) {
            Window window = (Window) drawable;
            window.addMouseListener(gearsMouse);
            window.addKeyListener(gearsKeys);
        } else if (GLProfile.isAWTAvailable() && drawable instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) drawable;
            new AWTMouseAdapter(gearsMouse).addTo(comp);
            new AWTKeyAdapter(gearsKeys).addTo(comp);
        }
    }
 

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

    private void update() {
        theta += 0.01;
        s = Math.sin(theta);
        c = Math.cos(theta);
    }

    private void render(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.setSwapInterval(1);

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glPushMatrix();
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(view_rotz, 0.0f, 0.0f, 1.0f);
        gl.glScalef(0.05f, 0.05f, 0.05f);
 
        double x = 0;
        double y = 0;
        double z_old = 0;
        for (x = -15; x < 15; x++) {
            for (y = -15; y < 15; y++) {
                double z = Math.sin(x*theta) + Math.cos(y*theta);

                gl.glBegin(GL.GL_TRIANGLES);
                gl.glColor3d(1, 0, Math.sin(x*theta) + Math.cos(y*theta));
                gl.glVertex3d(x, y, Math.sin(x*theta) + Math.cos(y*theta));
                gl.glColor3d(1, 0, Math.sin((x+1)*theta) + Math.cos(y*theta));
                gl.glVertex3d(x+1, y, Math.sin((x+1)*theta) + Math.cos(y*theta));
                gl.glColor3d(1, 0, Math.sin((x)*theta) + Math.cos((y+1)*theta));
                gl.glVertex3d(x, y+1, Math.sin((x)*theta) + Math.cos((y+1)*theta));
                gl.glColor3d(1, 0, Math.sin((x+1)*theta) + Math.cos(y*theta));
                gl.glVertex3d(x+1, y, Math.sin((x+1)*theta) + Math.cos(y*theta));
                gl.glColor3d(1, 0, Math.sin((x)*theta) + Math.cos((y+1)*theta));
                gl.glVertex3d(x, y+1, Math.sin((x)*theta) + Math.cos((y+1)*theta));
                gl.glColor3d(1, 0, Math.sin((x+1)*theta) + Math.cos((y+1)*theta));
                gl.glVertex3d(x+1, y+1, Math.sin((x+1)*theta) + Math.cos((y+1)*theta));
                gl.glEnd();

            }
        }
        // draw a triangle filling the window
        //gl.glBegin(GL.GL_TRIANGLES);
        //gl.glColor3f(1, 0, 0);
        //gl.glVertex2d(-c, -c);
        //gl.glColor3f(0, 1, 0);
        //gl.glVertex2d(0, c);
        //gl.glColor3f(0, 0, 1);
        //gl.glVertex2d(s, -s);
        //gl.glEnd();

        gl.glPopMatrix();

    }

   class SimpleMouseAdapter extends MouseAdapter {
      public void mousePressed(MouseEvent e) {
        prevMouseX = e.getX();
        prevMouseY = e.getY();
        if ((e.getModifiers() & e.BUTTON3_MASK) != 0) {
          mouseRButtonDown = true;
        }
      }
        
      public void mouseReleased(MouseEvent e) {
        if ((e.getModifiers() & e.BUTTON3_MASK) != 0) {
          mouseRButtonDown = false;
        }
      }
        
      public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int width=0, height=0;
        Object source = e.getSource();
        if(source instanceof Window) {
            Window window = (Window) source;
            width=window.getWidth();
            height=window.getHeight();
        } else if (GLProfile.isAWTAvailable() && source instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) source;
            width=comp.getWidth();
            height=comp.getHeight();
        } else {
            throw new RuntimeException("Event source neither Window nor Component: "+source);
        }
        float thetaY = 360.0f * ( (float)(x-prevMouseX)/(float)width);
        float thetaX = 360.0f * ( (float)(prevMouseY-y)/(float)height);
        
        prevMouseX = x;
        prevMouseY = y;

        view_rotx += thetaX;
        view_roty += thetaY;
      }
  } 

   class SimpleKeyAdapter extends KeyAdapter {      
    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();
        if(KeyEvent.VK_LEFT == kc) {
            view_roty -= 1;
        } else if(KeyEvent.VK_RIGHT == kc) {
            view_roty += 1;
        } else if(KeyEvent.VK_UP == kc) {
            view_rotx -= 1;
        } else if(KeyEvent.VK_DOWN == kc) {
            view_rotx += 1;
        }
    }
  }
 
}

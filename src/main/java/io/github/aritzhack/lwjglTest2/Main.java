package io.github.aritzhack.lwjglTest2;

import io.github.aritzhack.aritzh.logging.ILogger;
import io.github.aritzhack.aritzh.logging.SLF4JLogger;
import io.github.aritzhack.aritzh.timing.GameTimer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Canvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Aritz Lopez
 */
public class Main {

    public static boolean DEBUG = false;
    public static ILogger LOG = new SLF4JLogger("LWJGLTest");

    public static GameTimer timer = new GameTimer();

    private static JFrame frame = new JFrame("Game");
    private static Canvas canvas = new Canvas();
    private static boolean running = false;

    public static void main(String[] args) throws LWJGLException {
        setupDebug(args);

        initFrame();

        createDisplay();

        frame.setVisible(true);

        initOGL();

        timer.init();

        running = true;

        while (!Display.isCloseRequested() && running) {
            Display.setTitle("Test! - " + timer.getAverageUPS());
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);


            glBegin(GL_TRIANGLES);
            glColor3f(1, 0.5f, 1);
            glVertex2f(50, 50);
            glVertex2f(100, 50);
            glVertex2f(100, 100);
            glEnd();

            Display.update();

            timer.update();
            Display.sync(60);
        }
        Display.destroy();
        frame.dispose();
    }

    private static void initFrame() {
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(true);
        frame.getContentPane().setSize(800, 600);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                running = false;
            }
        });

        canvas.setSize(800, 600);
        frame.add(canvas);
        frame.pack();
    }

    private static void initOGL() throws LWJGLException {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        glLoadIdentity();
        glMatrixMode(GL_MODELVIEW);
    }

    private static void createDisplay() throws LWJGLException {
        //Display.setIcon(IconLoader.loadIcons("icon", IconLoader.getOSIconSizes()));
        Display.setParent(canvas);
        Display.setDisplayMode(new DisplayMode(800, 600));
        Display.create();
    }

    private static void setupDebug(String[] args) {

        for (String arg : args) {
            if ("debug".equalsIgnoreCase(arg)) {
                DEBUG = true;
                break;
            }
        }

        if (DEBUG)
            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + File.separator + "build" + File.separator + "natives");
    }
}

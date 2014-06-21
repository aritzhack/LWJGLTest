import io.github.aritzhack.aritzh.logging.ILogger;
import io.github.aritzhack.aritzh.logging.SLF4JLogger;
import io.github.aritzhack.aritzh.timing.GameTimer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.io.File;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Aritz Lopez
 */
public class Main {

    public static boolean DEBUG = false;
    public static ILogger LOG = new SLF4JLogger("LWJGLTest");

    public static GameTimer timer = new GameTimer();

    public static void main(String[] args) throws LWJGLException {
        setupDebug(args);
        System.out.println("Holaa!");
        Display.setDisplayMode(Display.getDesktopDisplayMode());
        Display.setTitle("Test!");
        Display.create();

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        glLoadIdentity();
        GL11.glOrtho(-2, 2, 2, -2, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        timer.init();

        while (!Display.isCloseRequested()) {
            Display.setTitle("Test! - " + timer.getAverageFPS());
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            glBegin(GL_TRIANGLES);
            glColor3f(1, 0.5f, 1);
            glVertex2f(-1, -1);
            glVertex2f(1, -1);
            glVertex2f(1, 1);
            glEnd();

            Display.update();
            timer.update();
            timer.render();
            Display.sync(60);
        }
        Display.destroy();
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

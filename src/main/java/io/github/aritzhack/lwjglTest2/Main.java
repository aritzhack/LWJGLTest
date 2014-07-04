package io.github.aritzhack.lwjglTest2;

import io.github.aritzhack.aritzh.logging.ILogger;
import io.github.aritzhack.aritzh.logging.SLF4JLogger;
import io.github.aritzhack.aritzh.timing.GameTimer;
import io.github.aritzhack.lwjglTest2.graphics.IconLoader;
import io.github.aritzhack.lwjglTest2.graphics.Materials;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.glu.GLU;

import java.io.File;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Aritz Lopez
 */
public class Main {

    public static boolean DEBUG = false;
    public static ILogger LOG = new SLF4JLogger("LWJGLTest");

    public static GameTimer timer = new GameTimer();

    private static int colorHandle;
    private static int vertexHandle;

    public static void main(String[] args) throws LWJGLException {
        setupDebug(args);

        createDisplay();

        initOGL();

        timer.init();

        CreateVBO();

        GL11.glTranslatef(0, 0, -5);

        while (!Display.isCloseRequested()) {
            Display.setTitle("Test! - " + timer.getAverageUPS());


            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT
                | GL11.GL_DEPTH_BUFFER_BIT);

            GL11.glTranslatef(0.0001f, 0f, -0.0001f); // Move Right 1.5 Units And Into
            // The
            GL11.glRotatef(0.025f, 1.0f, .5f, .25f);
            DrawVBO();
            // Render();
            Display.update();
            timer.update();
            //Display.sync(60);
        }
        Display.destroy();
    }

    private static void initOGL() throws LWJGLException {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClearDepth(1.0);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

        glEnable(GL_DEPTH_TEST);

        FloatBuffer lightPos = Util.bufferOf(0.5f, 0.5f, 3.0f, 0.0f);

        glLight(GL_LIGHT0, GL_POSITION, lightPos);

        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        Materials.setMaterial();

        glEnable(GL_FOG);
        {
            FloatBuffer fogColor = Util.bufferOf(0.5f, 0.5f, 0.5f, 1.0f);
            glFogi(GL_FOG_MODE, GL_EXP);
            glFog(GL_FOG_COLOR, fogColor);
            glFogf(GL_FOG_DENSITY, 0.35f);
            glHint(GL_FOG_HINT, GL_DONT_CARE);
            glFogf(GL_FOG_START, 1.0f);
            glFogf(GL_FOG_END, 5.0f);
        }
        glClearColor(0.5f, 0.5f, 0.5f, 1.0f);  /* fog color */

        glEnableClientState(GL11.GL_COLOR_ARRAY);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(45.0f, (float) 800 / (float) 600, 0.1f, 300.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }

    private static void CreateVBO() {
        colorHandle = GL15.glGenBuffers();
        vertexHandle = GL15.glGenBuffers();
        FloatBuffer vertexData = BufferUtils.createFloatBuffer(6 * 4 * 3); // FacesPerCube * VerticesPerFace * CoordinatesPerVertex
        vertexData.put(new float[]{
            1.0f, 1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,
            -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,

            1.0f, -1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,

            1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,

            1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,
            1.0f, 1.0f, -1.0f,

            -1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, 1.0f,

            1.0f, 1.0f, -1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, -1.0f
        });
        vertexData.flip();

        FloatBuffer colorData = BufferUtils.createFloatBuffer(6 * 4 * 3); // FacesPerCube * VerticesPerFace * ColorComponentPerVertex
        colorData.put(new float[]{
            1, 1, 0,
            1, 0, 1,
            0, 0, 1,
            0, 1, 1,

            1, 1, 0,
            1, 0, 1,
            0, 0, 1,
            0, 1, 1,

            1, 1, 0,
            1, 0, 1,
            0, 0, 1,
            0, 1, 1,

            1, 1, 0,
            1, 0, 1,
            0, 0, 1,
            0, 1, 1,

            1, 1, 0,
            1, 0, 1,
            0, 0, 1,
            0, 1, 1,

            1, 1, 0,
            1, 0, 1,
            0, 0, 1,
            0, 1, 1
        });
        colorData.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexData, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorData, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private static void DrawVBO() {
        GL11.glPushMatrix();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexHandle);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0L);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorHandle);
        GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0L);
        GL11.glDrawArrays(GL11.GL_QUADS, 0, 6 * 4); // FacesPerCube * VerticesPerFace
        GL11.glPopMatrix();
    }

    private static void createDisplay() throws LWJGLException {
        Display.setIcon(IconLoader.loadIcons("icon", IconLoader.getOSIconSizes()));
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

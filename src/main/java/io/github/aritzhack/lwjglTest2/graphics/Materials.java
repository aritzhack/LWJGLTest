package io.github.aritzhack.lwjglTest2.graphics;

import io.github.aritzhack.lwjglTest2.Util;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Aritz Lopez
 */
public class Materials {

    public static final FloatBuffer ambient, diffuse, specular;

    static {
        ambient = Util.bufferOf(0.1745f, 0.01175f, 0.01175f, 1.0f);
        diffuse = Util.bufferOf(0.61424f, 0.04136f, 0.04136f, 1.0f);
        specular = Util.bufferOf(0.727811f, 0.626959f, 0.626959f, 1.0f);
    }

    public static void setMaterial() {

        glMaterial(GL_FRONT, GL_AMBIENT, ambient);
        glMaterial(GL_FRONT, GL_DIFFUSE, diffuse);
        glMaterial(GL_FRONT, GL_SPECULAR, specular);
        glMaterialf(GL_FRONT, GL_SHININESS, 0.6f * 128.0f);

    }
}

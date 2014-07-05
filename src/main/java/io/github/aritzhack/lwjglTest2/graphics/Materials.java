package io.github.aritzhack.lwjglTest2.graphics;

import io.github.aritzhack.lwjglTest2.Main;
import io.github.aritzhack.lwjglTest2.Util;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Aritz Lopez
 */
public class Materials {

    public static final FloatBuffer ambient, diffuse, specular, emission;
    public static final FloatBuffer ambientD, diffuseD, specularD, emissionD;
    public static boolean ambientE, diffuseE, specularE, emissionE, shininessE;

    static {
        ambient = Util.bufferOf(0.1745f, 0.01175f, 0.01175f, 1.0f);
        diffuse = Util.bufferOf(0.61424f, 0.04136f, 0.04136f, 1.0f);
        specular = Util.bufferOf(0.727811f, 0.626959f, 0.626959f, 1.0f);

        // ambient = Util.bufferOf(1.0f, 0f, 0f, 1.0f);
        // diffuse = Util.bufferOf(0.0f, 0f, 0f, 1.0f);
        // specular = Util.bufferOf(1.0f, 1.0f, 1.0f, 1.0f);

        emission = Util.bufferOf(0.0f, 1.0f, 0.0f, 1.0f);

        ambientD = Util.bufferOf(0.2f, 0.2f, 0.2f, 1.0f);
        diffuseD = Util.bufferOf(0.8f, 0.8f, 0.8f, 1.0f);
        specularD = Util.bufferOf(0.0f, 0.0f, 0.0f, 1.0f);
        emissionD = Util.bufferOf(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public static void toggleAll() {
        if (!ambientE) toggleAmbient();
        if (!diffuseE) toggleDiffuse();
        if (!specularE) toggleSpecular();
        if (!shininessE) toggleShininess();
        if (!emissionE) toggleEmissiom();
    }

    public static void toggleDiffuse() {
        if (diffuseE) glMaterial(GL_FRONT, GL_DIFFUSE, diffuseD);
        else glMaterial(GL_FRONT, GL_DIFFUSE, diffuse);
        diffuseE = !diffuseE;
        Main.LOG.d("Diffuse: {}", diffuseE);
    }

    public static void toggleSpecular() {
        if (specularE) glMaterial(GL_FRONT, GL_SPECULAR, specularD);
        else glMaterial(GL_FRONT, GL_SPECULAR, specular);
        specularE = !specularE;
        Main.LOG.d("Specular: {}", specularE);
    }

    public static void toggleAmbient() {
        if (ambientE) glMaterial(GL_FRONT, GL_AMBIENT, ambientD);
        else glMaterial(GL_FRONT, GL_AMBIENT, ambient);
        ambientE = !ambientE;
        Main.LOG.d("Ambient: {}", ambientE);
    }

    public static void toggleShininess() {
        if (shininessE) glMaterialf(GL_FRONT, GL_SHININESS, 0f);
        else glMaterialf(GL_FRONT, GL_SHININESS, 0.6f * 128.0f);
        shininessE = !shininessE;
        Main.LOG.d("Shininess: {}", shininessE);
    }

    public static void toggleEmissiom() {
        if (emissionE) glMaterial(GL_FRONT, GL_EMISSION, emissionD);
        else glMaterial(GL_FRONT, GL_EMISSION, emission);
        emissionE = !emissionE;
        Main.LOG.d("Emission: {}", emissionE);
    }
}

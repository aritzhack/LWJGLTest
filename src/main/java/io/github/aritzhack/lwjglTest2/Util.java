package io.github.aritzhack.lwjglTest2;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * @author Aritz Lopez
 */
public class Util {

    public static FloatBuffer bufferOf(float... floats) {
        FloatBuffer ret = BufferUtils.createFloatBuffer(floats.length).put(floats);
        ret.flip();
        return ret;
    }
}

package io.github.aritzhack.lwjglTest2.graphics;

import com.google.common.collect.Sets;
import io.github.aritzhack.lwjglTest2.Main;
import org.newdawn.slick.opengl.PNGDecoder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Set;

/**
 * @author Aritz Lopez
 */
public class IconLoader {

    public static ByteBuffer[] loadIcons(String baseName, Size... sizes) {
        Set<ByteBuffer> buffers = Sets.newHashSet();

        for (Size s : sizes) {
            PNGDecoder decoder = null;
            try {
                decoder = new PNGDecoder(IconLoader.class.getResourceAsStream("/" + baseName + s.getName() + ".png"));
                ByteBuffer bBuffer = ByteBuffer.allocateDirect(decoder.getWidth() * decoder.getHeight() * PNGDecoder.RGBA.getNumComponents());
                decoder.decode(bBuffer, decoder.getWidth() * PNGDecoder.RGBA.getNumComponents(), PNGDecoder.RGBA);
                bBuffer.flip();
                buffers.add(bBuffer);
            } catch (IOException | NullPointerException e) {
                Main.LOG.e("Could not load icon {}", "/" + baseName + s.getName() + ".png", e);
            }
        }
        return buffers.toArray(new ByteBuffer[buffers.size()]);
    }

    public static Size[] getOSIconSizes() {
        String OS = System.getProperty("os.name").toUpperCase();
        return OS.contains("WIN") ? new Size[]{Size.x16, Size.x32} : OS.contains("MAC") ? new Size[]{Size.x128} : new Size[]{Size.x32};
    }

    public static enum Size {
        x16, x32, x128;

        public String getName() {
            return this.toString().substring(1);
        }
    }
}

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class i {
    private static boolean c = true;
    public static int a;
    public static int b;
    private static int d;
    private static int e;
    private static Image f;

    public static void a(int n2) {
        c = false;
        if (n2 == 2 && g.bc == 1 && !g.h && !g.bf) {
            c = true;
            try {
                f = Image.createImage((String)"/cp/girlbath.png");
            }
            catch (IOException iOException) {}
            a = 336;
            b = 552;
        }
    }

    public static void a(Graphics graphics) {
        if (!c) {
            return;
        }
        graphics.drawRegion(f, 0, d << 5, 24, 32, 0, a, b, 33);
    }

    public static void a() {
        if (!c) {
            return;
        }
        if (++e > 10) {
            e = 0;
        }
        d = e > 5 ? 0 : 1;
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class p {
    public static int a;
    public static int[] b;
    public static int[] c;
    public static int[] d;
    public static int[] e;
    public static int[] f;
    private static Image[] h;
    public static int g;

    static {
        g = -1;
    }

    public static final void a(Graphics graphics) {
        int n2 = 0;
        while (n2 < a) {
            if (q.ay <= b[n2] + 12 && q.ay + q.ab >= b[n2] - 12 && q.az <= c[n2] && q.az + q.ac >= c[n2] - 24) {
                if (d[n2] == 1) {
                    graphics.drawImage(h[e[n2]], b[n2], c[n2], 33);
                } else {
                    graphics.drawRegion(h[e[n2]], 0, 0, h[e[n2]].getWidth(), h[e[n2]].getHeight(), 2, b[n2], c[n2], 33);
                }
            }
            ++n2;
        }
    }

    public static final void a(int n2) {
        int n3;
        if (!g.m) {
            return;
        }
        a = 0;
        int n4 = n3 = g == -1 ? 0 : 1;
        if (n2 == 6) {
            n2 = 4;
            n2 = 33;
            n2 = n3;
            a = n2 + 1;
            p.a();
            p.a(0, 33, 4);
            if (n2 == 1) {
                p.a(1, g.at, g.au);
                g = 1;
                p.d[p.g] = g.aF;
            }
            return;
        }
        if (n3 == 1) {
            a = 1;
            p.a();
            p.a(0, g.at, g.au);
            g = 0;
            p.d[p.g] = g.aF;
        }
    }

    private static void a() {
        if (h == null) {
            h = new Image[3];
            try {
                int n2 = 0;
                while (n2 < 3) {
                    p.h[n2] = Image.createImage((String)("/i/k" + n2 + ".png"));
                    ++n2;
                }
            }
            catch (Exception exception) {}
        }
        b = new int[a];
        c = new int[a];
        d = new int[a];
        e = new int[a];
        f = new int[a];
    }

    private static void a(int n2, int n3, int n4) {
        p.b[n2] = n3 * j.f + 12;
        p.c[n2] = n4 * j.f;
        p.e[n2] = 0;
        p.d[n2] = 1;
    }
}


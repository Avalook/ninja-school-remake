/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.media.Manager
 *  javax.microedition.media.MediaException
 *  javax.microedition.media.Player
 *  javax.microedition.media.control.VolumeControl
 *  javax.microedition.midlet.MIDlet
 *  javax.microedition.rms.RecordStore
 */
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;

public class Midlet
extends MIDlet
implements Runnable {
    static boolean a;
    static a b;
    static Midlet c;
    private static boolean i;
    public static int d;
    public static boolean e;
    public static boolean f;
    private static Image[] j;
    private static final int[] k;
    private static int l;
    private static Image m;
    private static final int[] n;
    private static int o;
    private static Image p;
    private static final int[] q;
    private static int r;
    public static int g;
    private static Player s;
    private static int t;
    public static boolean h;

    static {
        i = true;
        k = new int[]{5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5};
        l = 8;
        n = new int[]{2, 6, 3, 5, 6, 6, 6, 5, 6, 6, 6, 3, 3, 3, 2, 6, 4, 4, 5, 3, 6, 8, 8, 8, 8, 8, 8, 8, 8, 3, 8, 8, 8, 10, 8, 8, 8, 8, 8, 7, 8, 8, 8, 10, 8, 8, 8, 6, 6, 6, 6, 6, 5, 6, 6, 3, 5, 6, 3, 8, 6, 6, 6, 6, 6, 6, 5, 6, 6, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3, 3, 3, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
        o = 12;
        q = new int[]{3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 4, 3, 3, 3, 6, 7, 7, 8, 5, 11, 11, 7, 6, 10, 6, 7, 5, 7, 6, 5, 7, 7, 3, 3, 6, 3, 9, 7, 7, 7, 7, 4, 5, 4, 8, 6, 9, 7, 6, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 8, 7, 8, 8, 8, 7, 7, 8, 7, 6, 7, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 7, 8, 7, 7, 8, 6, 6, 8, 8, 3, 6, 7, 6, 11, 9, 8, 7, 8, 7, 5, 6, 8, 7, 11, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 7, 6, 6, 6, 6, 5, 5, 5, 6, 5, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 7, 7, 7, 7, 7, 9};
        r = 13;
        g = 0;
        try {
            j = new Image[3];
            int n = 0;
            while (n < 3) {
                Midlet.j[n] = Image.createImage((String)("/fs" + n + ".png"));
                ++n;
            }
        }
        catch (Exception exception) {}
        try {
            m = Image.createImage((String)"/fr.png");
        }
        catch (Exception exception) {}
        try {
            p = Image.createImage((String)"/fb.png");
        }
        catch (Exception exception) {}
        t = -1;
    }

    private static void b(int n) {
        if (n == t) {
            return;
        }
        try {
            if (s != null) {
                s.stop();
                s.deallocate();
                s = null;
                System.gc();
            }
            s = Manager.createPlayer((InputStream)"".getClass().getResourceAsStream("/s/" + n + ".mid"), (String)"audio/midi");
            s.realize();
            ((VolumeControl)s.getControl("VolumeControl")).setLevel(100);
            s.prefetch();
            s.setLoopCount(1000);
            t = n;
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            System.gc();
            h = true;
            return;
        }
    }

    public static void a(Graphics graphics, String string, int n, int n2, int n3) {
        int n4 = string.length();
        n3 = n3 == 0 ? n : (n3 == 1 ? n - Midlet.a(string) : n - (Midlet.a(string) >> 1));
        int n5 = 0;
        while (n5 < n4) {
            n = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(string.charAt(n5));
            if (n == -1) {
                n = 0;
            }
            if (n > -1) {
                graphics.drawRegion(p, 0, n * r + 1, q[n], r - 1, 0, n3, n2, 20);
            }
            n3 += q[n];
            ++n5;
        }
    }

    public static void b(Graphics graphics, String string, int n, int n2, int n3) {
        int n4 = string.length();
        n3 = n - (Midlet.c(string) >> 1);
        int n5 = 0;
        while (n5 < n4) {
            n = " 0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111\u0110".indexOf(string.charAt(n5));
            if (n == -1) {
                n = 0;
            }
            if (n > -1) {
                graphics.drawRegion(m, 0, n * o + 1, Midlet.n[n], o - 1, 0, n3, n2, 20);
            }
            n3 += Midlet.n[n];
            ++n5;
        }
    }

    public static void a(Graphics graphics, String string, int n, int n2, int n3, int n4) {
        int n5 = string.length();
        n4 = n4 == 0 ? n2 : (n4 == 1 ? n2 - Midlet.d(string) : n2 - (Midlet.d(string) >> 1));
        int n6 = 0;
        while (n6 < n5) {
            n2 = "0123456789+-%$:abcdefghijklmnopqrstuvwxyz".indexOf(string.charAt(n6));
            if (n2 == -1) {
                n2 = 0;
            }
            if (n2 > -1) {
                graphics.drawRegion(j[n], 0, n2 * l, k[n2], l, 0, n4, n3, 20);
            }
            n4 += k[n2] - 1;
            ++n6;
        }
    }

    public static int a(String string) {
        int n = 0;
        int n2 = 0;
        while (n2 < string.length()) {
            int n3 = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(string.charAt(n2));
            if (n3 == -1) {
                n3 = 0;
            }
            n += q[n3];
            ++n2;
        }
        return n;
    }

    private static int c(String string) {
        int n = 0;
        int n2 = 0;
        while (n2 < string.length()) {
            int n3 = " 0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111\u0110".indexOf(string.charAt(n2));
            if (n3 == -1) {
                n3 = 0;
            }
            n += Midlet.n[n3];
            ++n2;
        }
        return n;
    }

    private static int d(String string) {
        int n = 0;
        int n2 = 0;
        while (n2 < string.length()) {
            int n3 = "0123456789+-%$:abcdefghijklmnopqrstuvwxyz".indexOf(string.charAt(n2));
            if (n3 == -1) {
                n3 = 0;
            }
            n += k[n3] + 1;
            ++n2;
        }
        return n;
    }

    public static byte[] b(String string) {
        byte[] byArray;
        try {
            string = RecordStore.openRecordStore((String)string, (boolean)false);
            byArray = string.getRecord(1);
            string.closeRecordStore();
        }
        catch (Exception exception) {
            return null;
        }
        return byArray;
    }

    public static int a() {
        byte[] byArray = Midlet.b("bg");
        if (byArray == null) {
            return 0;
        }
        return byArray[0];
    }

    public static void a(String string, byte[] byArray) {
        try {
            string = RecordStore.openRecordStore((String)string, (boolean)true);
            if (string.getNumRecords() > 0) {
                string.setRecord(1, byArray, 0, byArray.length);
            } else {
                string.addRecord(byArray, 0, byArray.length);
            }
            string.closeRecordStore();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static void a(boolean bl) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = 1;
        Midlet.a("saved", byArray2);
    }

    public static boolean b() {
        byte[] byArray = Midlet.b("saved");
        if (byArray == null) {
            return false;
        }
        return byArray[0] == 1;
    }

    public static void a(int n) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n;
        Midlet.a("bg", byArray2);
    }

    public static String[] a(String string, int n) {
        Vector<String> vector = new Vector<String>();
        int n2 = string.length();
        if (n2 <= 1) {
            return new String[]{string};
        }
        String string2 = "";
        int n3 = 0;
        int n4 = 0;
        while (true) {
            if (Midlet.a(string2) < n) {
                string2 = String.valueOf(string2) + string.charAt(n4);
                if (string.charAt(++n4) != '\n') {
                    if (n4 < n2 - 1) continue;
                    n4 = n2 - 1;
                }
            }
            if (n4 != n2 - 1 && string.charAt(n4 + 1) != ' ') {
                int n5 = n4;
                while (string.charAt(n4 + 1) != '\n' && (string.charAt(n4 + 1) != ' ' || string.charAt(n4) == ' ') && n4 != n3) {
                    --n4;
                }
                if (n4 == n3) {
                    n4 = n5;
                }
            }
            vector.addElement(string.substring(n3, n4 + 1));
            if (n4 == n2 - 1) break;
            n3 = n4 + 1;
            while (n3 != n2 - 1 && string.charAt(n3) == ' ') {
                ++n3;
            }
            if (n3 == n2 - 1) break;
            n4 = n3;
            string2 = "";
        }
        String[] stringArray = new String[vector.size()];
        int n6 = 0;
        while (n6 < vector.size()) {
            stringArray[n6] = (String)vector.elementAt(n6);
            ++n6;
        }
        return stringArray;
    }

    public static String[] b(String string, int n) {
        Vector<String> vector = new Vector<String>();
        int n2 = string.length();
        String string2 = "";
        int n3 = 0;
        int n4 = 0;
        while (true) {
            if (Midlet.c(string2) < n) {
                string2 = String.valueOf(string2) + string.charAt(n4);
                if (++n4 != n2 - 1) {
                    continue;
                }
            }
            if (n4 != n2 - 1 && string.charAt(n4 + 1) != ' ') {
                int n5 = n4;
                while ((string.charAt(n4 + 1) != ' ' || string.charAt(n4) == ' ') && n4 != n3) {
                    --n4;
                }
                if (n4 == n3) {
                    n4 = n5;
                }
            }
            vector.addElement(string.substring(n3, n4 + 1));
            if (n4 == n2 - 1) break;
            n3 = n4 + 1;
            while (n3 != n2 - 1 && string.charAt(n3) == ' ') {
                ++n3;
            }
            if (n3 == n2 - 1) break;
            n4 = n3;
            string2 = "";
        }
        String[] stringArray = new String[vector.size()];
        int n6 = 0;
        while (n6 < vector.size()) {
            stringArray[n6] = (String)vector.elementAt(n6);
            ++n6;
        }
        return stringArray;
    }

    public Midlet() {
        c = this;
        a.s();
        Display.getDisplay((MIDlet)this).setCurrent((Displayable)b);
    }

    protected void destroyApp(boolean bl) {
        if (s != null) {
            try {
                s.stop();
            }
            catch (MediaException mediaException) {}
            s.deallocate();
        }
    }

    public final void c() {
        System.gc();
        this.notifyDestroyed();
    }

    protected void pauseApp() {
    }

    public void run() {
        block25: {
            Object object;
            try {
                int n;
                Thread.sleep(200L);
                Thread.yield();
                a.w = a.a((int)System.currentTimeMillis() % 1000);
                a.R = 30;
                Thread.yield();
                a.m(a.v);
                a.q(a.v);
                a.p(a.v);
                a.o(a.v);
                a.R = 50;
                Thread.yield();
                a.t(a.v);
                a.i();
                a.f(a.v);
                a.g(a.v);
                a.e(a.v);
                a.R = 65;
                Thread.yield();
                a.r(a.v);
                a.R = 70;
                Thread.yield();
                a.h(a.v);
                a.i(a.v);
                a.j(a.v);
                a.k(a.v);
                a.l(a.v);
                a.R = 75;
                a.k();
                a.g();
                a.p();
                a.R = 78;
                Thread.yield();
                a.d(a.v);
                a.c(a.v);
                a.R = 80;
                a.l();
                a.r();
                a.R = 82;
                Thread.yield();
                a.q();
                a.n();
                a.m();
                a.R = 85;
                a.b(a.v);
                a.f();
                a.o();
                a.R = 87;
                a.e();
                a.h();
                a.j();
                a.R = 95;
                Thread.yield();
                int[] nArray = new int[]{0x55AAEE, 18};
                object = nArray;
                a.i = nArray[g];
                try {
                    if (!a.a) {
                        if (a.s == null) {
                            a.s = new Image[4];
                            int n2 = 0;
                            while (n2 < 4) {
                                a.s[n2] = Image.createImage((String)("/bg" + n2 + g + ".png"));
                                ++n2;
                            }
                        }
                        if (a.t == null) {
                            a.t = Image.createImage((String)("/cl" + g + ".png"));
                        }
                        if (a.u == null) {
                            a.u = Image.createImage((String)("/sun" + g + ".png"));
                        }
                    }
                }
                catch (Exception exception) {}
                a.b = false;
                if (a.v != 0 && !a.a) {
                    a.b = true;
                    a.p = a.r - (a.s[0].getHeight() + a.s[1].getHeight() + a.s[2].getHeight()) + 11;
                    a.j = a.r - a.s[0].getHeight();
                    a.k = a.j - a.s[1].getHeight();
                    a.l = a.k - a.s[2].getHeight();
                    a.m = a.k - a.s[3].getHeight() - 10;
                }
                if ((n = 2 * a.r / 3 - a.k) < 0) {
                    n = 0;
                }
                if (a.v < 19) {
                    a.W[a.v] = true;
                }
                a.p += n;
                a.j += n;
                a.k += n;
                a.l += n;
                a.m += n;
                a.g = 3 * a.q / 4;
                a.h = a.p / 3;
                a.e = new int[2];
                a.f = new int[2];
                a.e[0] = a.q / 3;
                a.f[0] = a.p / 2 - 8;
                a.e[1] = 2 * a.q / 3;
                a.f[1] = a.p / 2 + 8;
                a.c = true;
                a.n = "Menu";
                a.o = " ";
                a.T = false;
                a.n(a.v);
                a.R = 100;
                if (i) {
                    i = false;
                    a.E = 20;
                    a.F = 10;
                    a.H = 200;
                    a.G = 200;
                    a.J = 150;
                    a.I = 150;
                    a.O = -1;
                    a.P = 5;
                    a.K = 6;
                    a.L = 2;
                    a.Q = 8;
                    a.A = 4;
                    a.y = 1;
                    a.z = 1;
                    a.B = 0;
                    a.D = 0;
                    a.C = 0;
                    a.M = 1;
                    a.N = 0;
                }
                a.d();
                a.c();
                if (e) {
                    a.b();
                    e = false;
                } else if (f) {
                    a.a();
                    f = false;
                }
                switch (a.v) {
                    case 99: {
                        Midlet.b(0);
                        break;
                    }
                    case 95: 
                    case 96: 
                    case 97: {
                        Midlet.b(4);
                        break;
                    }
                    case 0: 
                    case 1: 
                    case 2: {
                        Midlet.b(1);
                        break;
                    }
                    case 3: 
                    case 4: 
                    case 5: 
                    case 6: 
                    case 7: {
                        Midlet.b(2);
                        break;
                    }
                    default: {
                        Midlet.b(3);
                    }
                }
                if (h) break block25;
                try {
                    s.start();
                }
                catch (Exception exception) {
                    Exception exception2 = exception;
                    exception.printStackTrace();
                }
            }
            catch (Exception exception) {
                object = exception;
                exception.printStackTrace();
                object = c;
                System.gc();
                object.notifyDestroyed();
            }
        }
        while (a.S < 90) {
            Thread.yield();
        }
        a.d = d;
    }

    protected void startApp() {
        System.out.println(a.b("2212"));
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.media.Manager
 *  javax.microedition.media.MediaException
 *  javax.microedition.media.Player
 *  javax.microedition.media.control.VolumeControl
 *  javax.microedition.midlet.MIDlet
 *  javax.microedition.rms.RecordStore
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
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
    static q a;
    static Midlet b;
    static boolean c;
    public static int d;
    public static boolean e;
    public static boolean f;
    private static Image[] i;
    private static final int[] j;
    private static int k;
    private static Image l;
    private static final int[] m;
    private static int n;
    private static Image o;
    private static final int[] p;
    private static int q;
    public static int g;
    private static Player r;
    private static int s;
    public static boolean h;

    static {
        c = true;
        j = new int[]{5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5};
        k = 8;
        m = new int[]{2, 6, 3, 5, 6, 6, 6, 5, 6, 6, 6, 3, 3, 3, 2, 6, 4, 4, 5, 3, 6, 8, 8, 8, 8, 8, 8, 8, 8, 3, 8, 8, 8, 10, 8, 8, 8, 8, 8, 7, 8, 8, 8, 10, 8, 8, 8, 6, 6, 6, 6, 6, 5, 6, 6, 3, 5, 6, 3, 8, 6, 6, 6, 6, 6, 6, 5, 6, 6, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3, 3, 3, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
        n = 12;
        p = new int[]{3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 4, 3, 3, 3, 6, 7, 7, 8, 5, 11, 11, 7, 6, 10, 6, 7, 5, 7, 6, 5, 7, 7, 3, 3, 6, 3, 9, 7, 7, 7, 7, 4, 5, 4, 8, 6, 9, 7, 6, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 8, 7, 8, 8, 8, 7, 7, 8, 7, 6, 7, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 7, 8, 7, 7, 8, 6, 6, 8, 8, 3, 6, 7, 6, 11, 9, 8, 7, 8, 7, 5, 6, 8, 7, 11, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 7, 6, 6, 6, 6, 5, 5, 5, 6, 5, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 7, 7, 7, 7, 7, 9};
        q = 13;
        g = 0;
        try {
            i = new Image[3];
            int n2 = 0;
            while (n2 < 3) {
                Midlet.i[n2] = Image.createImage((String)("/fs" + n2 + ".png"));
                ++n2;
            }
        }
        catch (Exception exception) {}
        try {
            l = Image.createImage((String)"/fr.png");
        }
        catch (Exception exception) {}
        try {
            o = Image.createImage((String)"/fb.png");
        }
        catch (Exception exception) {}
        s = -1;
        h = true;
    }

    private static void b(int n2) {
        if (n2 == s) {
            return;
        }
        try {
            if (r != null) {
                r.stop();
                r.deallocate();
                r = null;
                System.gc();
            }
            r = Manager.createPlayer((InputStream)"".getClass().getResourceAsStream("/s/" + n2 + ".mid"), (String)"audio/midi");
            r.realize();
            ((VolumeControl)r.getControl("VolumeControl")).setLevel(q.bu * 10);
            r.prefetch();
            r.setLoopCount(1000);
            s = n2;
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

    public static void a(Graphics graphics, String string, int n2, int n3, int n4) {
        int n5 = string.length();
        n4 = n4 == 0 ? n2 : (n4 == 1 ? n2 - Midlet.a(string) : n2 - (Midlet.a(string) >> 1));
        int n6 = 0;
        while (n6 < n5) {
            n2 = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(string.charAt(n6));
            if (n2 == -1) {
                n2 = 0;
            }
            if (n2 > -1) {
                graphics.drawRegion(o, 0, n2 * q + 1, p[n2], q - 1, 0, n4, n3, 20);
            }
            n4 += p[n2];
            ++n6;
        }
    }

    public static void b(Graphics graphics, String string, int n2, int n3, int n4) {
        int n5 = string.length();
        n4 = n2 - (Midlet.c(string) >> 1);
        int n6 = 0;
        while (n6 < n5) {
            n2 = " 0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111\u0110".indexOf(string.charAt(n6));
            if (n2 == -1) {
                n2 = 0;
            }
            if (n2 > -1) {
                graphics.drawRegion(l, 0, n2 * n + 1, m[n2], n - 1, 0, n4, n3, 20);
            }
            n4 += m[n2];
            ++n6;
        }
    }

    public static void a(Graphics graphics, String string, int n2, int n3, int n4, int n5) {
        int n6 = string.length();
        n5 = n5 == 0 ? n3 : (n5 == 1 ? n3 - Midlet.d(string) : n3 - (Midlet.d(string) >> 1));
        int n7 = 0;
        while (n7 < n6) {
            n3 = "0123456789+-%$:abcdefghijklmnopqrstuvwxyz".indexOf(string.charAt(n7));
            if (n3 == -1) {
                n3 = 0;
            }
            if (n3 > -1) {
                graphics.drawRegion(i[n2], 0, n3 * k, j[n3], k, 0, n5, n4, 20);
            }
            n5 += j[n3] - 1;
            ++n7;
        }
    }

    public static int a(String string) {
        int n2 = 0;
        int n3 = 0;
        while (n3 < string.length()) {
            int n4 = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(string.charAt(n3));
            if (n4 == -1) {
                n4 = 0;
            }
            n2 += p[n4];
            ++n3;
        }
        return n2;
    }

    private static int c(String string) {
        int n2 = 0;
        int n3 = 0;
        while (n3 < string.length()) {
            int n4 = " 0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111\u0110".indexOf(string.charAt(n3));
            if (n4 == -1) {
                n4 = 0;
            }
            n2 += m[n4];
            ++n3;
        }
        return n2;
    }

    private static int d(String string) {
        int n2 = 0;
        int n3 = 0;
        while (n3 < string.length()) {
            int n4 = "0123456789+-%$:abcdefghijklmnopqrstuvwxyz".indexOf(string.charAt(n3));
            if (n4 == -1) {
                n4 = 0;
            }
            n2 += j[n4] + 1;
            ++n3;
        }
        return n2;
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

    public static boolean a() {
        byte[] byArray = Midlet.b("saved");
        if (byArray == null) {
            return false;
        }
        return byArray[0] == 1;
    }

    public static void a(int n2) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n2;
        Midlet.a("bg", byArray2);
    }

    public static String[] a(String string, int n2) {
        Vector<String> vector = new Vector<String>();
        int n3 = string.length();
        if (n3 <= 1) {
            return new String[]{string};
        }
        String string2 = "";
        int n4 = 0;
        int n5 = 0;
        while (true) {
            if (Midlet.a(string2) < n2) {
                string2 = String.valueOf(string2) + string.charAt(n5);
                if (string.charAt(++n5) != '\n') {
                    if (n5 < n3 - 1) continue;
                    n5 = n3 - 1;
                }
            }
            if (n5 != n3 - 1 && string.charAt(n5 + 1) != ' ') {
                int n6 = n5;
                while (string.charAt(n5 + 1) != '\n' && (string.charAt(n5 + 1) != ' ' || string.charAt(n5) == ' ') && n5 != n4) {
                    --n5;
                }
                if (n5 == n4) {
                    n5 = n6;
                }
            }
            vector.addElement(string.substring(n4, n5 + 1));
            if (n5 == n3 - 1) break;
            n4 = n5 + 1;
            while (n4 != n3 - 1 && string.charAt(n4) == ' ') {
                ++n4;
            }
            if (n4 == n3 - 1) break;
            n5 = n4;
            string2 = "";
        }
        String[] stringArray = new String[vector.size()];
        int n7 = 0;
        while (n7 < vector.size()) {
            stringArray[n7] = (String)vector.elementAt(n7);
            ++n7;
        }
        return stringArray;
    }

    public static String[] b(String string, int n2) {
        Vector<String> vector = new Vector<String>();
        int n3 = string.length();
        String string2 = "";
        int n4 = 0;
        int n5 = 0;
        while (true) {
            if (Midlet.c(string2) < n2) {
                string2 = String.valueOf(string2) + string.charAt(n5);
                if (++n5 != n3 - 1) {
                    continue;
                }
            }
            if (n5 != n3 - 1 && string.charAt(n5 + 1) != ' ') {
                int n6 = n5;
                while ((string.charAt(n5 + 1) != ' ' || string.charAt(n5) == ' ') && n5 != n4) {
                    --n5;
                }
                if (n5 == n4) {
                    n5 = n6;
                }
            }
            vector.addElement(string.substring(n4, n5 + 1));
            if (n5 == n3 - 1) break;
            n4 = n5 + 1;
            while (n4 != n3 - 1 && string.charAt(n4) == ' ') {
                ++n4;
            }
            if (n4 == n3 - 1) break;
            n5 = n4;
            string2 = "";
        }
        String[] stringArray = new String[vector.size()];
        int n7 = 0;
        while (n7 < vector.size()) {
            stringArray[n7] = (String)vector.elementAt(n7);
            ++n7;
        }
        return stringArray;
    }

    public Midlet() {
        b = this;
        q.t();
    }

    protected void destroyApp(boolean bl) {
        if (r != null) {
            try {
                r.stop();
            }
            catch (MediaException mediaException) {}
            r.deallocate();
        }
    }

    public final void b() {
        q.y = false;
        System.gc();
        this.notifyDestroyed();
    }

    private static void c() {
        q.c = true;
        q.d = false;
        q.e = false;
        q.f = false;
        q.g = false;
        q.h = false;
        g.g = false;
        g.as = 1;
        g.aJ = 70;
        g.aK = 40;
        g.f = true;
        g.aM = 4000;
        g.aL = 4000;
        g.aO = 1500;
        g.aN = 1500;
        m.e = 4500;
        m.f = 4500;
        q.aL = 800;
        q.aK = 800;
        e.f = 8000;
        t.i = 1000;
        t.j = 1000;
        t.k = 2000;
        t.l = 2000;
        g.bc = -1;
        q.aC = 5;
        g.aP = 5;
        g.aQ = 2;
        q.aD = 8;
        g.ax = 4;
        g.av = 1;
        g.aw = 1;
        g.ay = 0;
        g.aH = 0;
        g.aG = 0;
        g.aW = 30;
        g.aX = 0;
        g.at = 24;
        g.au = 24;
        g.aF = 1;
        q.am = -1;
        q.bt = new boolean[21];
        g.bm = false;
        g.O = false;
        q.a = false;
        q.aj = 0;
        g.H = false;
        g.Q = -1;
        q.aQ = -1;
        g.i = false;
        g.h = false;
        q.au = false;
        g.ba = false;
        g.bb = false;
        q.i = false;
        q.j = false;
        q.k = false;
        q.l = false;
        q.m = false;
        q.n = false;
        q.o = false;
        q.p = false;
        q.q = false;
        q.r = false;
        q.s = false;
        q.t = false;
        g.j = false;
        g.k = false;
        g.l = false;
        t.m = false;
        g.m = false;
        g.n = false;
        g.o = false;
        g.p = true;
        g.r = false;
        g.s = false;
        g.t = false;
        g.u = false;
        g.q = true;
        g.v = false;
        g.w = false;
        g.x = false;
        g.y = false;
        g.z = false;
        g.A = false;
        g.B = false;
        g.C = false;
        m.g = false;
        g.D = -1;
        g.bL = 0;
        g.bn = -1;
        g.ap = false;
        g.a = -1;
        q.bi = -1;
        q.aA = false;
        q.aN = false;
        q.bf[0] = 0;
        q.bf[1] = 100;
        q.bf[2] = 1;
        q.bg[0] = "G\u01b0\u01a1m tre";
        q.bg[1] = "Phi ti\u00eau";
        q.bg[2] = "G\u01b0\u01a1m s\u00e9t";
        q.bs[0] = 0;
        q.bs[1] = 1;
        q.bs[2] = 2;
        q.bh = 3;
        int n2 = 3;
        while (n2 < 6) {
            q.bf[n2] = -1;
            q.bg[n2] = "";
            q.bs[n2] = -1;
            ++n2;
        }
    }

    protected void pauseApp() {
        q.b();
        q.c();
    }

    public void run() {
        block48: {
            try {
                int n2;
                int n3;
                int n4;
                block47: {
                    Thread.sleep(200L);
                    Thread.yield();
                    q.at = q.a((int)System.currentTimeMillis() % 1000);
                    q.bk = 30;
                    Thread.yield();
                    j.c(q.as);
                    j.b(q.as);
                    j.a(q.as);
                    q.bk = 50;
                    Thread.yield();
                    c.b(q.as);
                    g.d();
                    g.b(q.as);
                    g.a(q.as);
                    g.d(q.as);
                    q.bk = 65;
                    n.a(q.as);
                    Thread.yield();
                    a.a(q.as);
                    t.a(q.as);
                    m.a(q.as);
                    q.bk = 70;
                    Thread.yield();
                    q.e(q.as);
                    q.bk = 75;
                    q.j();
                    o.a();
                    q.p();
                    q.bk = 78;
                    Thread.yield();
                    q.d(q.as);
                    q.c(q.as);
                    p.a(q.as);
                    q.bk = 80;
                    q.k();
                    q.r();
                    q.bk = 82;
                    Thread.yield();
                    q.q();
                    q.m();
                    q.l();
                    q.bk = 85;
                    int n5 = q.as;
                    d.a = false;
                    if (n5 == 0 && q.c) {
                        d.a = true;
                        try {
                            d.b = Image.createImage((String)"/i/ftfong.png");
                        }
                        catch (IOException iOException) {
                            IOException iOException2 = iOException;
                            iOException.printStackTrace();
                        }
                        d.c = 0;
                        d.d = 1044;
                        d.e = 264;
                        d.f = -12;
                        d.g = 780;
                    }
                    e.a(q.as);
                    int n6 = q.as;
                    s.a = false;
                    if (n6 == 0 && q.c) {
                        s.a = true;
                        try {
                            s.b = Image.createImage((String)"/i/wind.png");
                        }
                        catch (IOException iOException) {
                            IOException iOException3 = iOException;
                            iOException.printStackTrace();
                        }
                        s.c = 0;
                        s.d = 780;
                        s.e = 276;
                        s.f = 0;
                    }
                    q.b(q.as);
                    q.e();
                    q.n();
                    q.o();
                    i.a(q.as);
                    q.bk = 87;
                    q.d();
                    q.g();
                    q.h();
                    q.bk = 95;
                    Thread.yield();
                    n4 = 0;
                    n3 = 0;
                    switch (q.as) {
                        case 0: 
                        case 1: 
                        case 2: 
                        case 3: 
                        case 4: 
                        case 20: 
                        case 103: 
                        case 105: 
                        case 107: 
                        case 108: {
                            g = 0;
                            q.be = new int[]{1, 2, 3, 4};
                            break;
                        }
                        case 5: 
                        case 6: 
                        case 18: 
                        case 19: 
                        case 101: {
                            g = 1;
                            q.be = new int[]{1, 2, 3, 4};
                            break;
                        }
                        case 7: 
                        case 8: 
                        case 9: 
                        case 10: 
                        case 100: 
                        case 106: 
                        case 109: {
                            g = 2;
                            n4 = 9;
                            n3 = 6;
                            q.be = new int[]{1, 2, 3, 4};
                            break;
                        }
                        case 11: 
                        case 17: {
                            g = 3;
                            q.be = new int[]{1, 1, 1, 1};
                            break;
                        }
                        case 12: 
                        case 13: 
                        case 14: 
                        case 15: 
                        case 16: 
                        case 99: 
                        case 102: {
                            g = 4;
                            n4 = 12;
                            q.be = new int[]{1, 2, 3, 4};
                        }
                    }
                    int[] nArray = new int[5];
                    nArray[0] = 0x55AAEE;
                    nArray[1] = 696558;
                    nArray[2] = 696558;
                    nArray[4] = 10541304;
                    int[] nArray2 = nArray;
                    q.P = nArray[g];
                    try {
                        if (q.b) break block47;
                        if (q.ao == null) {
                            q.ao = new Image[4];
                            q.ap = new int[4];
                            int n7 = 0;
                            while (n7 < 4) {
                                try {
                                    q.ao[n7] = Image.createImage((String)("/bg/bg" + n7 + g + ".png"));
                                }
                                catch (Exception exception) {}
                                if (q.ao[n7] != null) {
                                    q.ap[n7] = q.ao[n7].getWidth();
                                }
                                ++n7;
                            }
                        }
                        if (g != 3) {
                            if (q.aq == null) {
                                q.aq = Image.createImage((String)"/bg/cl0.png");
                            }
                            if (q.ar == null) {
                                q.ar = Image.createImage((String)"/bg/sun0.png");
                            }
                        } else {
                            q.aq = null;
                            q.ar = null;
                        }
                    }
                    catch (Exception exception) {
                        System.out.println("BG ERR");
                    }
                }
                q.I = false;
                if (!q.b) {
                    int n8;
                    q.I = true;
                    q.Q = n8 = q.ac - q.ao[0].getHeight();
                    if (q.ao[1] != null) {
                        n8 = n8 - q.ao[1].getHeight() + n4;
                    }
                    q.R = n8;
                    if (q.ao[3] != null) {
                        n8 -= q.ao[3].getHeight();
                    }
                    q.T = n8;
                    q.aa = n8;
                    if (q.ao[2] != null) {
                        q.S = q.R - q.ao[2].getHeight() + n3;
                    }
                }
                if ((n2 = 2 * q.ac / 3 - q.R) < 0) {
                    n2 = 0;
                }
                if (q.as < q.bt.length) {
                    q.bt[q.as] = true;
                }
                if (q.as == 106) {
                    q.bt[0] = false;
                }
                q.aa += n2;
                if (q.as != 11 && q.as != 17) {
                    q.Q += n2;
                }
                q.R += n2;
                q.S += n2;
                q.T += n2;
                q.N = 3 * q.ab / 4;
                q.O = q.aa / 3;
                q.L = new int[2];
                q.M = new int[2];
                q.L[0] = q.ab / 3;
                q.M[0] = q.aa / 2 - 8;
                q.L[1] = 2 * q.ab / 3;
                q.M[1] = q.aa / 2 + 8;
                q.J = true;
                q.U = "Menu";
                q.V = " ";
                a.b = false;
                q.f(q.as);
                q.f();
                q.bk = 100;
                if (c) {
                    c = false;
                    Midlet.c();
                }
                q.c();
                if (e) {
                    q.a(false);
                    e = false;
                } else if (f) {
                    q.a();
                    f = false;
                }
                switch (q.as) {
                    case 99: {
                        Midlet.b(0);
                        break;
                    }
                    case 100: 
                    case 101: 
                    case 102: 
                    case 103: 
                    case 108: {
                        Midlet.b(3);
                        break;
                    }
                    case 0: 
                    case 1: 
                    case 9: 
                    case 10: 
                    case 14: 
                    case 107: {
                        Midlet.b(1);
                        break;
                    }
                    case 2: 
                    case 3: 
                    case 4: 
                    case 20: 
                    case 105: 
                    case 106: {
                        Midlet.b(2);
                        break;
                    }
                    default: {
                        Midlet.b(0);
                    }
                }
                if (h) break block48;
                try {
                    r.start();
                }
                catch (Exception exception) {
                    Exception exception2 = exception;
                    exception.printStackTrace();
                }
            }
            catch (Exception exception) {
                Exception exception3 = exception;
                exception.printStackTrace();
                b.b();
            }
        }
        while (q.bl < 90) {
            Thread.yield();
        }
        q.b();
        q.c();
        q.K = d;
    }

    protected void startApp() {
        System.out.println(b.a("9100"));
    }
}


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
    private static Image r;
    private static final int[] s;
    private static int t;
    public static int g;
    private static Player u;
    private static int v;
    public static boolean h;

    static {
        c = true;
        j = new int[]{5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5};
        k = 8;
        m = new int[]{2, 6, 3, 5, 6, 6, 6, 5, 6, 6, 6, 3, 3, 3, 2, 6, 4, 4, 5, 3, 6, 8, 8, 8, 8, 8, 8, 8, 8, 3, 8, 8, 8, 10, 8, 8, 8, 8, 8, 7, 8, 8, 8, 10, 8, 8, 8, 6, 6, 6, 6, 6, 5, 6, 6, 3, 5, 6, 3, 8, 6, 6, 6, 6, 6, 6, 5, 6, 6, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3, 3, 3, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
        n = 12;
        p = new int[]{3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 4, 3, 3, 3, 6, 7, 7, 8, 5, 11, 11, 7, 6, 10, 6, 7, 5, 7, 6, 5, 7, 7, 3, 3, 6, 3, 9, 7, 7, 7, 7, 4, 5, 4, 8, 6, 9, 7, 6, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 8, 7, 8, 8, 8, 7, 7, 8, 7, 6, 7, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 7, 8, 7, 7, 8, 6, 6, 8, 8, 3, 6, 7, 6, 11, 9, 8, 7, 8, 7, 5, 6, 8, 7, 11, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 7, 6, 6, 6, 6, 5, 5, 5, 6, 5, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 7, 7, 7, 7, 7, 9, 8};
        q = 13;
        s = new int[]{7, 7, 7, 7, 7, 7, 7, 7, 7};
        t = 8;
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
        System.out.println(b.a("1069"));
        try {
            l = Image.createImage((String)"/fr.png");
        }
        catch (Exception exception) {}
        try {
            o = Image.createImage((String)"/fb.png");
        }
        catch (Exception exception) {}
        r = q.c("/number.png");
        v = -1;
        h = true;
    }

    private static void b(int n2) {
        if (n2 == v) {
            return;
        }
        try {
            if (u != null) {
                u.stop();
                u.deallocate();
                u = null;
                System.gc();
            }
            u = Manager.createPlayer((InputStream)"".getClass().getResourceAsStream("/s/" + n2 + ".mid"), (String)"audio/midi");
            u.realize();
            ((VolumeControl)u.getControl("VolumeControl")).setLevel(q.bs * 10);
            u.prefetch();
            u.setLoopCount(1000);
            v = n2;
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
            n2 = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111ABCDEFGHIJKLMNOPQRSTUVWXYZ\u0110".indexOf(string.charAt(n6));
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

    public static void c(Graphics graphics, String string, int n2, int n3, int n4) {
        int n5 = string.length();
        String string2 = string;
        int n6 = 0;
        int n7 = 0;
        while (n7 < string2.length()) {
            n4 = "123456789".indexOf(string2.charAt(n7));
            if (n4 == -1) {
                n4 = 0;
            }
            n6 += s[n4];
            ++n7;
        }
        n4 = n2 - (n6 >> 1);
        n6 = 0;
        while (n6 < n5) {
            int n8 = "123456789".indexOf(string.charAt(n6));
            if (n8 == -1) {
                n8 = 0;
            }
            if (n8 > -1) {
                graphics.drawRegion(r, 0, n8 * t, s[n8], t, 0, n4, n3, 20);
            }
            n4 += s[n8];
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
            int n4 = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111ABCDEFGHIJKLMNOPQRSTUVWXYZ\u0110".indexOf(string.charAt(n3));
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
            if (Midlet.a(string2) < 100) {
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
        q.w();
    }

    protected void destroyApp(boolean bl) {
        if (u != null) {
            try {
                u.stop();
            }
            catch (MediaException mediaException) {}
            u.deallocate();
        }
    }

    public final void b() {
        q.p = false;
        System.gc();
        this.notifyDestroyed();
    }

    private static void c() {
        q.b = true;
        h.af = 0;
        h.aw = 60;
        h.ax = 15;
        h.aH = 60;
        h.aI = 40;
        h.aJ = 13;
        h.e = true;
        h.az = 3500;
        h.ay = 3500;
        h.aB = 1500;
        h.aA = 1500;
        h.bj = 4000;
        h.bi = 4000;
        h.aR = -1;
        q.aC = 5;
        h.aC = 5;
        h.aD = 2;
        q.aD = 8;
        h.ak = 4;
        h.ai = 1;
        h.aj = 1;
        h.al = 0;
        h.au = 0;
        h.at = 0;
        h.aM = 24;
        h.aN = 0;
        h.ag = 24;
        h.ah = 24;
        h.as = 1;
        q.aT[0] = 10;
        q.aT[1] = 0;
        q.aU[0] = "Phi ti\u00eau";
        q.aU[1] = "Ki\u1ebfm tre";
        q.aS[0] = 0;
        q.aS[1] = 1;
        q.aV = 2;
        int n2 = 2;
        while (n2 < 7) {
            q.aT[n2] = -1;
            q.aU[n2] = "";
            q.aS[n2] = -1;
            ++n2;
        }
        h.n = new boolean[3];
        int[] nArray = new int[10];
        nArray[1] = 1;
        nArray[2] = 2;
        nArray[3] = 3;
        nArray[4] = 4;
        nArray[5] = 5;
        nArray[6] = 6;
        nArray[7] = 7;
        nArray[8] = 8;
        nArray[9] = 9;
        int[] nArray2 = nArray;
        int n3 = 0;
        while (n3 < 3) {
            int n4 = q.b(0, nArray2.length - n3);
            q.m[n3] = nArray2[n4];
            while (n4 < nArray2.length - (n3 + 1)) {
                nArray2[n4] = nArray2[n4 + 1];
                ++n4;
            }
            ++n3;
        }
        q.as = new int[10];
        q.at = new int[10];
        q.au = new int[10];
        q.am = new Image[10];
        q.ae = 0;
        p.h = 0;
        q.b = true;
        q.f = 0;
        q.g = 0;
        q.h = 0;
        q.c = 0;
        q.d = 0;
        q.e = 0;
        n3 = 0;
        while (n3 < q.i.length) {
            q.i[n3] = false;
            ++n3;
        }
        p.a = false;
        p.b = false;
        p.c = false;
        p.d = false;
        p.e = false;
        p.f = false;
        n3 = 0;
        while (n3 < q.br.length) {
            q.br[n3] = false;
            n3 = (byte)(n3 + 1);
        }
        q.ar = false;
        h.ad = 0;
        h.ae = 0;
        h.i = false;
        h.k = false;
        h.aR = 0;
        h.aW = 0;
        h.j = false;
        h.f = false;
        h.g = false;
        h.h = false;
        h.o = false;
        q.bo = 0;
        q.bn = 0;
        i.c = false;
        i.d = false;
        i.e = false;
        i.f = false;
        i.g = false;
        f.a = -1;
        i.h = false;
        i.i = false;
        i.k = false;
        d.g = false;
        q.bm = 0;
        c.h = 0;
        c.g = 0;
        f.f = 22;
        q.bz = 0;
        q.bF = 0;
        q.bB = false;
        q.bC = false;
        q.bD = false;
        c.l = 0;
        h.aV = false;
    }

    protected void pauseApp() {
    }

    public void run() {
        block49: {
            try {
                int n2;
                block48: {
                    Thread.sleep(200L);
                    Thread.yield();
                    q.aq = q.a((int)System.currentTimeMillis() % 1000);
                    q.be = 30;
                    Thread.yield();
                    k.c(q.ap);
                    k.b(q.ap);
                    k.a(q.ap);
                    q.be = 50;
                    Thread.yield();
                    c.b(q.ap);
                    c.c();
                    h.d();
                    h.c(q.ap);
                    h.b(q.ap);
                    h.e(q.ap);
                    n.b();
                    q.be = 65;
                    Thread.yield();
                    a.a(q.ap);
                    i.b(q.ap);
                    i.a(q.ap);
                    i.a();
                    f.a(q.ap);
                    d.a(q.ap);
                    q.be = 70;
                    Thread.yield();
                    q.be = 75;
                    q.k();
                    o.a();
                    q.q();
                    q.be = 78;
                    Thread.yield();
                    q.b(q.ap);
                    q.be = 80;
                    q.l();
                    q.t();
                    q.be = 82;
                    Thread.yield();
                    q.r();
                    q.s();
                    q.o();
                    q.m();
                    q.n();
                    q.be = 85;
                    q.d();
                    q.p();
                    q.e();
                    q.B();
                    q.C();
                    q.E();
                    q.be = 87;
                    q.f();
                    q.h();
                    q.i();
                    q.be = 95;
                    Thread.yield();
                    boolean bl = false;
                    switch (q.ap) {
                        case 3: 
                        case 11: 
                        case 12: 
                        case 13: {
                            g = 0;
                            q.aR = new int[]{1, 2, 3, 4};
                            break;
                        }
                        case 9: 
                        case 15: 
                        case 17: {
                            g = 1;
                            q.aR = new int[]{1, 2, 3, 4};
                            break;
                        }
                        case 8: 
                        case 14: {
                            g = 2;
                            q.aR = new int[]{1, 2, 3, 4};
                            break;
                        }
                        case 10: {
                            g = 3;
                            q.aR = new int[]{1, 1, 1, 1};
                            break;
                        }
                        case 0: 
                        case 1: 
                        case 7: {
                            g = 4;
                            q.aR = new int[]{1, 2, 3, 4};
                            break;
                        }
                        case 2: 
                        case 4: 
                        case 5: 
                        case 6: 
                        case 18: 
                        case 20: 
                        case 22: {
                            g = 5;
                            q.aR = new int[]{1, 2, 3, 4};
                        }
                    }
                    int[] nArray = new int[6];
                    nArray[0] = 2526662;
                    nArray[1] = 1513480;
                    nArray[2] = 0xFFBFBC;
                    nArray[4] = 1285290;
                    nArray[5] = 1285290;
                    int[] nArray2 = nArray;
                    q.E = nArray[g];
                    try {
                        if (q.a) break block48;
                        if (q.ak == null) {
                            q.ak = new Image[4];
                            q.al = new int[4];
                            int n3 = 0;
                            while (n3 < 4) {
                                try {
                                    q.ak[n3] = Image.createImage((String)("/bg/bg" + n3 + g + ".png"));
                                }
                                catch (Exception exception) {}
                                if (q.ak[n3] != null) {
                                    q.al[n3] = q.ak[n3].getWidth();
                                }
                                ++n3;
                            }
                            if (g == 3) {
                                q.ak[1] = Image.createImage((String)"/bg/bg03.png");
                                q.ak[2] = Image.createImage((String)"/bg/bg03.png");
                                q.al[1] = q.ak[1].getWidth();
                                q.al[2] = q.ak[2].getWidth();
                            }
                            if (g == 5) {
                                q.ak[3] = Image.createImage((String)"/bg/bg34.png");
                                q.al[3] = q.ak[3].getWidth();
                            }
                        }
                        if (g == 0 || g == 4 || g == 5) {
                            if (q.ap == 20) {
                                q.an = null;
                            } else if (q.an == null) {
                                q.an = Image.createImage((String)"/bg/cl0.png");
                            }
                        } else {
                            q.an = null;
                        }
                    }
                    catch (Exception exception) {
                        System.out.println("BG ERR");
                    }
                }
                q.z = false;
                if (!q.a) {
                    int n4;
                    q.z = true;
                    q.F = n4 = q.T - q.ak[0].getHeight();
                    if (q.ak[1] != null) {
                        n4 -= q.ak[1].getHeight();
                    }
                    q.G = n4;
                    if (q.ak[3] != null) {
                        n4 -= q.ak[3].getHeight();
                    }
                    q.I = n4;
                    q.R = n4;
                    if (q.ak[2] != null) {
                        q.H = q.G - q.ak[2].getHeight();
                    }
                }
                if ((n2 = 2 * q.T / 3 - q.G) < 0) {
                    n2 = 0;
                }
                if (q.ap < q.br.length) {
                    q.br[q.ap] = true;
                    System.out.println("AVAILABLE MAP " + q.ap);
                }
                q.R += n2;
                q.F += n2;
                q.G += n2;
                q.H += n2;
                q.I += n2;
                q.C = new int[2];
                q.D = new int[2];
                q.C[0] = q.S / 3;
                q.D[0] = q.a(q.R / 2 - 8);
                q.C[1] = 2 * q.S / 3;
                q.D[1] = q.a(q.R / 2 + 8);
                if (!q.a) {
                    if (g == 1) {
                        q.F = q.G = q.V - 50;
                    }
                    if (g == 2) {
                        q.F = q.V;
                        q.G = q.F + 5;
                        q.H = q.F - 15;
                        q.I = 0;
                    }
                    if (g == 3) {
                        q.I = q.H - q.ak[3].getHeight();
                    }
                    if (g == 4 || g == 5) {
                        q.I = 0;
                    }
                }
                a.b = false;
                q.d(q.ap);
                q.g();
                q.be = 100;
                if (c) {
                    c = false;
                    Midlet.c();
                }
                q.A = true;
                q.J = "Menu";
                q.K = h.n[1] && !q.br[13] ? "Ban Do" : " ";
                q.c();
                if (e) {
                    q.a(false);
                    e = false;
                } else if (f) {
                    q.aW = true;
                    q.a();
                    f = false;
                }
                switch (q.ap) {
                    case 16: 
                    case 18: {
                        Midlet.b(0);
                        break;
                    }
                    case 3: 
                    case 5: 
                    case 10: 
                    case 11: 
                    case 13: {
                        Midlet.b(3);
                        break;
                    }
                    case 8: 
                    case 14: 
                    case 15: 
                    case 17: {
                        Midlet.b(1);
                        break;
                    }
                    case 0: 
                    case 2: 
                    case 7: 
                    case 22: {
                        Midlet.b(2);
                        break;
                    }
                    default: {
                        Midlet.b(0);
                    }
                }
                if (h) break block49;
                try {
                    u.start();
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
        while (q.bf < 90) {
            Thread.yield();
        }
        q.b();
        q.c();
        q.B = d;
    }

    protected void startApp() {
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class j {
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    private static char[] w;
    private static int[] x;
    private static boolean[] y;
    public static Image e;
    private static Image z;
    private static Image A;
    private static Image B;
    private static Image C;
    private static Image[] D;
    private static int E;
    private static int[] F;
    private static int[] G;
    private static int[] H;
    private static int[] I;
    private static int[] J;
    private static Image K;
    public static int f;
    private static int L;
    private static int M;
    private static int N;
    private static int O;
    private static String[] P;
    private static int[] Q;
    private static int[] R;
    private static int[] S;
    private static int T;
    public static int g;
    public static int h;
    public static int i;
    public static int j;
    public static int k;
    public static int l;
    public static int m;
    public static int n;
    public static int o;
    public static int p;
    public static int q;
    public static int r;
    public static int s;
    public static int t;
    public static int u;
    public static int v;

    static {
        f = 24;
    }

    public static void a() {
        D = null;
    }

    public static void b() {
        e = null;
        A = null;
        C = null;
        D = null;
        w = null;
        x = null;
        System.gc();
    }

    public static void a(int n2) {
        T = 0;
        if (n2 == 0) {
            T = 8;
            j.d();
            j.a(0, "B\u1ed9 m\u00f4n Kh\u00ed", 1044, 336);
            j.a(1, "C\u00f4 Ayumi", 1044, 347);
            j.a(2, "B\u1ed9 m\u00f4n L\u1ef1c", 1044, 216);
            j.a(3, "Th\u1ea7y Hiroyaki", 1044, 227);
            j.a(4, "K\u00ed T\u00fac X\u00e1", 444, 341);
            j.a(5, "* Khu luy\u1ec7n t\u1eadp", 72, 341);
            j.a(6, "B\u00e1n HP-MP", 612, 341);
            j.a(7, "B\u00e1n \u0111\u1ed3 ti\u1ec1n th\u1eadt", 276, 341);
            return;
        }
        if (n2 == 1) {
            T = 2;
            j.d();
            j.a(0, "Tr\u01b0\u1eddng /", 1356, 173);
            j.a(1, "* Th\u00e1c n\u01b0\u1edbc", 48, 173);
            return;
        }
        if (n2 == 2) {
            T = 2;
            j.d();
            j.a(0, "Tr\u01b0\u1eddng /", 420, 77);
            j.a(1, "* Th\u00f4n Suzuya", 120, 53);
            return;
        }
        if (n2 == 4) {
            T = 3;
            j.d();
            j.a(0, "L\u00e0ng m\u1ef9 ngh\u1ec7", 1752, 173);
            j.a(1, "T O N E", 1752, 221);
            j.a(2, "* V\u00e1ch Ichidai", 432, 173);
            return;
        }
        if (n2 == 5) {
            T = 3;
            j.d();
            j.a(0, "L\u00e0ng Tone /", 624, 1805);
            j.a(1, "V\u00e1ch \u0111\u00e1 Ichidai", 336, 1757);
            j.a(2, "\u0111\u1ec9nh n\u00fai /", 600, 53);
            return;
        }
        if (n2 == 6) {
            T = 2;
            j.d();
            j.a(0, "N\u01a1i ph\u00f3ng di\u1ec1u l\u01b0\u1ee3n /", 576, 77);
            j.a(1, "\u0111\u1ed3i Kokoro /", 1776, 269);
            return;
        }
        if (n2 == 7) {
            T = 2;
            j.d();
            j.a(0, "C\u00e1nh \u0111\u1ed3ng Fuki /", 2088, 173);
            j.a(1, "Mua \u0111\u1ed3 ti\u1ec1n th\u1eadt", 1704, 149);
            return;
        }
        if (n2 == 8) {
            T = 2;
            j.d();
            j.a(0, "Tr\u01b0\u1eddng h\u1ecdc Haruna /", 2964, 149);
            j.a(1, "V\u1ec1 l\u00e0ng Suzuya", 3000, 245);
            return;
        }
        if (n2 == 9) {
            T = 3;
            j.d();
            j.a(0, "Tr\u01b0\u1eddng Ninja", 852, 298);
            j.a(1, "H A R U N A", 852, 317);
            j.a(2, "K\u00fd t\u00fac x\u00e1 /", 2412, 341);
            return;
        }
        if (n2 == 10) {
            T = 1;
            j.d();
            j.a(0, "K\u00fd t\u00fac x\u00e1", 480, 173);
            return;
        }
        if (n2 == 12) {
            T = 3;
            j.d();
            j.a(0, "Su\u1ed1i n\u01b0\u1edbc n\u00f3ng AKAGI", 552, 125);
            j.a(1, "B\u00e1n \u0111\u1ed3 ti\u1ec1n th\u1eadt", 360, 125);
            j.a(2, "B\u1edd bi\u1ec3n /", 2726, 221);
            return;
        }
        if (n2 == 14) {
            T = 2;
            j.d();
            j.a(0, "L\u00e0ng ch\u00e0i Kora", 336, 341);
            j.a(1, "B\u00e1n HP-MP", 876, 269);
            return;
        }
        if (n2 == 15) {
            T = 1;
            j.d();
            j.a(0, "\u0111\u1ea3o Sasayaku", 3408, 149);
            return;
        }
        if (n2 == 19) {
            T = 1;
            j.d();
            j.a(0, "Mi\u1ebfu Chakumi /", 2304, 557);
        }
    }

    private static void a(int n2, String string, int n3, int n4) {
        j.P[n2] = string;
        j.Q[n2] = n3;
        j.R[n2] = n4;
        int n5 = n2;
        Q[n5] = Q[n5] - (Midlet.a(string) >> 1);
        j.S[n2] = Midlet.a(string);
    }

    public static void a(Graphics graphics) {
        int n2 = 0;
        while (n2 < T) {
            if (Q[n2] + S[n2] >= q.ay && Q[n2] <= q.ay + q.ab && R[n2] >= q.az && R[n2] - 14 <= q.az + q.ac) {
                Midlet.a(graphics, P[n2], Q[n2], R[n2], 0);
            }
            ++n2;
        }
    }

    private static void d() {
        P = new String[T];
        Q = new int[T];
        R = new int[T];
        S = new int[T];
    }

    public static void b(int n2) {
        E = 0;
        if (n2 == 99) {
            n2 = 13;
        }
        if (n2 == 3) {
            if (D == null) {
                D = new Image[3];
                try {
                    j.D[0] = Image.createImage((String)"/t/h0.png");
                    j.D[1] = Image.createImage((String)"/t/h1.png");
                    j.D[2] = Image.createImage((String)"/t/h2.png");
                }
                catch (Exception exception) {}
            }
            E = 5;
            j.e();
            j.a(0, 0, 65, 19);
            j.a(1, 1, 52, 17);
            j.a(2, 2, 57, 17);
            j.a(3, 1, 43, 13);
            j.a(4, 2, 0, 0);
        }
        if (n2 == 13) {
            if (D == null) {
                D = new Image[1];
                try {
                    j.D[0] = Image.createImage((String)"/t/h3.png");
                }
                catch (Exception exception) {}
            }
            E = 11;
            j.e();
            j.a(0, 0, 9, 11);
            j.a(1, 0, 17, 11);
            j.a(2, 0, 24, 11);
            j.a(3, 0, 44, 9);
            j.a(4, 0, 46, 11);
            j.a(5, 0, 73, 6);
            j.a(6, 0, 50, 11);
            j.a(7, 0, 52, 11);
            j.a(8, 0, 63, 11);
            j.a(9, 0, 68, 11);
            j.a(10, 0, 95, 11);
        }
        if (n2 == 15) {
            if (D == null) {
                D = new Image[2];
                try {
                    j.D[0] = Image.createImage((String)"/t/h1.png");
                    j.D[1] = Image.createImage((String)"/t/h3.png");
                }
                catch (Exception exception) {}
            }
            E = 3;
            j.e();
            j.a(0, 0, 64, 8);
            j.a(1, 0, 67, 8);
            j.a(2, 1, 99, 8);
        }
        if (n2 == 16) {
            if (D == null) {
                D = new Image[1];
                try {
                    j.D[0] = Image.createImage((String)"/t/h3.png");
                }
                catch (Exception exception) {}
            }
            E = 1;
            j.e();
            j.a(0, 0, 43, 11);
        }
        if (n2 == 8 && D == null) {
            D = new Image[1];
            try {
                j.D[0] = Image.createImage((String)"/t/h4.png");
            }
            catch (Exception exception) {}
            E = 1;
            j.e();
            j.a(0, 0, 88, 7);
        }
    }

    private static void a(int n2, int n3, int n4, int n5) {
        j.F[n2] = n3;
        j.G[n2] = n4 * f + 12;
        j.H[n2] = n5 * f;
        j.I[n2] = D[n3].getWidth() >> 1;
        j.J[n2] = D[n3].getHeight();
    }

    private static void e() {
        F = new int[E];
        G = new int[E];
        H = new int[E];
        I = new int[E];
        J = new int[E];
    }

    public static void b(Graphics graphics) {
        int n2 = 0;
        while (n2 < E) {
            if (G[n2] + I[n2] >= q.ay && G[n2] - I[n2] <= q.ay + q.ab && H[n2] >= q.az && H[n2] - J[n2] <= q.az + q.ac) {
                L = G[n2] - q.ay;
                M = L - q.ad;
                O = (f - 2) * M / f;
                N = O + q.ad;
                if (q.as != 8) {
                    graphics.drawImage(D[F[n2]], q.ay + N, H[n2], 33);
                } else {
                    graphics.drawImage(D[F[n2]], G[n2], H[n2], 33);
                }
            }
            ++n2;
        }
    }

    static final void c() {
        try {
            if (e == null) {
                e = Image.createImage((String)"/t/t.png");
            }
            if (z == null) {
                z = !q.b ? Image.createImage((String)"/t/uwt_hi.png") : Image.createImage((String)"/t/uwt.png");
            }
            if (A == null) {
                A = Image.createImage((String)"/t/wtf.png");
            }
            if (B == null) {
                B = Image.createImage((String)"/t/twtf.png");
            }
            if (C == null) {
                C = !q.b ? Image.createImage((String)"/t/wts_hi.png") : Image.createImage((String)"/t/wts.png");
            }
            if (K == null && !q.b) {
                K = Image.createImage((String)"/t/tb.png");
            }
            System.gc();
            return;
        }
        catch (Exception exception) {
            Midlet.b.b();
            return;
        }
    }

    public static void c(int n2) {
        y = new boolean[200];
        if (n2 == 99) {
            n2 = 13;
        }
        if (n2 == 109) {
            a = 66;
            b = 16;
            g = -1;
            h = -1;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 108) {
            a = 120;
            b = 14;
            g = -1;
            h = 0;
            n = 864;
            o = 360;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 107) {
            a = 20;
            b = 14;
            g = -1;
            h = 0;
            n = 864;
            o = 360;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 106) {
            a = 50;
            b = 13;
            g = -1;
            h = 0;
            n = 864;
            o = 360;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 105) {
            a = 40;
            b = 12;
            g = -1;
            h = 0;
            n = 864;
            o = 360;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 0) {
            a = 46;
            b = 19;
            g = 1;
            l = 59 * f;
            m = 9 * f;
            h = 105;
            n = 36;
            o = 240;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 1) {
            a = 60;
            b = 14;
            g = 2;
            l = 19 * f;
            m = 5 * f;
            h = 0;
            n = 3;
            o = 16 * f;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 2) {
            a = 20;
            b = 40;
            g = 3;
            l = 68 * f;
            m = 19 * f;
            h = 1;
            n = 3;
            o = 9 * f;
            i = -1;
            j = -1;
            k = 103;
            t = 168;
            u = 3;
        }
        if (n2 == 103) {
            a = 20;
            b = 14;
            g = -1;
            h = -1;
            i = -1;
            j = 2;
            r = 228;
            s = 912;
            k = -1;
        }
        if (n2 == 3) {
            a = 70;
            b = 26;
            g = -1;
            h = 2;
            n = 3;
            o = 5 * f;
            i = -1;
            j = 8;
            r = 2928;
            s = 240;
            k = 4;
            t = 115 * f;
            u = 3;
        }
        if (n2 == 4) {
            a = 120;
            b = 14;
            g = 5;
            l = 29 * f;
            m = 77 * f;
            h = -1;
            i = -1;
            j = 3;
            r = 18 * f;
            s = 24 * f;
            k = -1;
        }
        if (n2 == 5) {
            a = 30;
            b = 80;
            g = -1;
            j = -1;
            k = -1;
            h = 101;
            i = 4;
            v = 288;
            n = 3;
            o = 144;
            p = 3;
            q = 168;
        }
        if (n2 == 101) {
            a = 20;
            b = 14;
            g = 5;
            l = 29 * f;
            m = 5 * f;
            j = -1;
            k = -1;
            h = 6;
            i = -1;
            n = 3;
            o = 432;
        }
        if (n2 == 6) {
            a = 80;
            b = 20;
            g = 5;
            l = 29 * f;
            m = 5 * f;
            h = 7;
            n = 3;
            o = 168;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 7) {
            a = 100;
            b = 14;
            g = 6;
            l = 1896;
            m = 288;
            h = 8;
            n = 3;
            o = 240;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 8) {
            a = 150;
            b = 13;
            g = 7;
            l = 2376;
            m = 264;
            h = 9;
            n = 3;
            o = 408;
            i = -1;
            j = -1;
            k = 3;
            t = 48;
            u = 3;
        }
        if (n2 == 9) {
            a = 120;
            b = 20;
            g = 8;
            l = 3576;
            m = 240;
            h = 10;
            n = 3;
            o = 288;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 10) {
            a = 50;
            b = 14;
            g = 9;
            l = 2856;
            m = 384;
            h = 100;
            n = 3;
            o = 120;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 100) {
            a = 20;
            b = 15;
            g = 10;
            l = 1176;
            m = 72;
            h = 11;
            n = 3;
            o = 912;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 11) {
            a = 64;
            b = 40;
            g = 100;
            l = 456;
            m = 264;
            h = 12;
            n = 3;
            o = 240;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 12) {
            a = 120;
            b = 15;
            g = 11;
            l = 1512;
            m = 888;
            h = 13;
            n = 3;
            o = 264;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 13) {
            a = 100;
            b = 13;
            g = 12;
            l = 2856;
            m = 288;
            h = 14;
            n = 3;
            o = 384;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 14) {
            a = 120;
            b = 20;
            g = 13;
            l = 2376;
            m = 264;
            h = 102;
            n = 3;
            o = 192;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 102) {
            a = 24;
            b = 14;
            g = 14;
            l = 2856;
            m = 432;
            h = 15;
            n = 3;
            o = 216;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 15) {
            a = 150;
            b = 11;
            g = 102;
            l = 528;
            m = 240;
            h = 16;
            n = 3;
            o = 192;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 16) {
            a = 100;
            b = 14;
            g = 15;
            l = 3552;
            m = 168;
            h = 17;
            n = 3;
            o = 672;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 17) {
            a = 80;
            b = 30;
            g = 16;
            l = 2376;
            m = 192;
            h = 18;
            n = 3;
            o = 240;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 18) {
            a = 100;
            b = 15;
            g = 17;
            l = 1896;
            m = 96;
            h = 19;
            n = 3;
            o = 648;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 19) {
            a = 100;
            b = 30;
            g = 18;
            l = 2376;
            m = 192;
            h = 20;
            n = 3;
            o = 288;
            i = -1;
            j = -1;
            k = -1;
        }
        if (n2 == 20) {
            a = 80;
            b = 16;
            g = 19;
            l = 2376;
            m = 648;
            h = -1;
            n = 3;
            o = 264;
            i = -1;
            j = -1;
            k = -1;
        }
        c = a * f;
        d = b * f;
        try {
            InputStream inputStream = "".getClass().getResourceAsStream("/m/" + n2);
            w = new char[a * b];
            x = new int[a * b];
            int n3 = 0;
            while (n3 < a * b) {
                j.w[n3] = (char)inputStream.read();
                if (w[n3] != '\u0000') {
                    j.y[j.w[n3] - '\u0001'] = true;
                }
                if (w[n3] == '\u0001' || w[n3] == '\u0002' || w[n3] == '\u0003' || w[n3] == '\u0004' || w[n3] == '\u0005' || w[n3] == '\u0006' || w[n3] == '\u0007' || w[n3] == '$' || w[n3] == '%' || w[n3] == '6' || w[n3] == '=' || w[n3] == 'I' || w[n3] == 'J' || w[n3] == 'L' || w[n3] == 'M' || w[n3] == 'N' || w[n3] == 'O' || w[n3] == 'R' || w[n3] == 'S' || w[n3] == 'b' || w[n3] == 'c' || w[n3] == 'd' || w[n3] == 'f' || w[n3] == 'g' || w[n3] == 'l' || w[n3] == 'm' || w[n3] == 'n' || w[n3] == 'p' || w[n3] == 'q' || w[n3] == 't' || w[n3] == 'u' || w[n3] == '}' || w[n3] == '~' || w[n3] == '\u007f' || w[n3] == '\u0081' || w[n3] == '\u0082') {
                    int n4 = n3;
                    x[n4] = x[n4] | 2;
                }
                if (w[n3] == '\u0001' || w[n3] == '\u0003' || w[n3] == '\u0004' || w[n3] == '\u0005' || w[n3] == '\u0006' || w[n3] == '\u0014' || w[n3] == '\u0015' || w[n3] == '\u0016' || w[n3] == '\u0017' || w[n3] == '$' || w[n3] == '%' || w[n3] == '&' || w[n3] == '\'' || w[n3] == '7' || w[n3] == 'm' || w[n3] == 'o' || w[n3] == 'p' || w[n3] == 'q' || w[n3] == 'r' || w[n3] == 's' || w[n3] == 't' || w[n3] == '\u007f' || w[n3] == '\u0081' || w[n3] == '\u0082') {
                    int n5 = n3;
                    x[n5] = x[n5] | 0x2000;
                }
                if (w[n3] == '\b' || w[n3] == '\t' || w[n3] == '\n' || w[n3] == '\f' || w[n3] == '\r' || w[n3] == '\u000e' || w[n3] == '\u001e') {
                    int n6 = n3;
                    x[n6] = x[n6] | 0x10;
                }
                if (w[n3] == '\u0011') {
                    int n7 = n3;
                    x[n7] = x[n7] | 0x20;
                }
                if (w[n3] == '\u0012') {
                    int n8 = n3;
                    x[n8] = x[n8] | 0x80;
                }
                if (w[n3] == '=' || w[n3] == '%' || w[n3] == '&' || w[n3] == '\u007f' || w[n3] == '\u0082' || w[n3] == '\u0083') {
                    int n9 = n3;
                    x[n9] = x[n9] | 4;
                }
                if (w[n3] == '=' || w[n3] == '$' || w[n3] == '\'' || w[n3] == '\u007f' || w[n3] == '\u0081' || w[n3] == '\u0084') {
                    int n10 = n3;
                    x[n10] = x[n10] | 8;
                }
                if (w[n3] == '\u0013') {
                    int n11 = n3;
                    x[n11] = x[n11] | 0x40;
                    if ((x[n3 - a] & 0x2000) == 8192) {
                        int n12 = n3;
                        x[n12] = x[n12] | 0x2000;
                    }
                }
                if (w[n3] == '\u0086') {
                    int n13 = n3;
                    x[n13] = x[n13] | 0x40;
                    if ((x[n3 - a] & 0x2000) == 8192) {
                        int n14 = n3;
                        x[n14] = x[n14] | 0x2000;
                    }
                }
                if (w[n3] == '#') {
                    int n15 = n3;
                    x[n15] = x[n15] | 0x1000;
                }
                if (w[n3] == ' ') {
                    int n16 = n3;
                    x[n16] = x[n16] | 0x100;
                }
                if (w[n3] == '\u0007') {
                    int n17 = n3;
                    x[n17] = x[n17] | 0x800;
                }
                if (w[n3] == '!' || w[n3] == '\"') {
                    int n18 = n3;
                    x[n18] = x[n18] | 0x200;
                }
                if (w[n3] == '=' || w[n3] == '\u007f') {
                    int n19 = n3;
                    x[n19] = x[n19] | 0x4000;
                }
                ++n3;
            }
        }
        catch (Exception exception) {
            System.out.println("Error Load Map");
            exception.printStackTrace();
            Midlet.b.b();
        }
        j.c();
        if (q.as == 14 && !q.o) {
            q.bi = 3;
            q.a(1848, 240);
        }
        if (q.as == 19 && !g.B) {
            q.bi = 7;
            q.a(624, 96);
            if (g.u && g.s && g.t) {
                int n20 = 1;
                while (n20 <= 8) {
                    o.b.addElement(new h(624, 82, n20 + 13));
                    ++n20;
                }
            }
        }
    }

    public static final void c(Graphics graphics) {
        int n2 = q.W;
        while (n2 < q.Y) {
            int n3 = q.X;
            while (n3 < q.Z) {
                int n4 = w[n3 * a + n2] - '\u0001';
                if (n4 != -1) {
                    graphics.drawRegion(e, 0, n4 * f, 24, 24, 0, n2 * f, n3 * f, 0);
                }
                if ((j.a(n2, n3) & 0x20) == 32) {
                    graphics.drawRegion(A, 0, 24 * (q.at % 4), 24, 24, 0, n2 * f, n3 * f, 0);
                } else if ((j.a(n2, n3) & 0x40) == 64) {
                    if ((j.a(n2, n3 - 1) & 0x20) == 32) {
                        graphics.drawRegion(A, 0, 24 * (q.at % 4), 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if ((j.a(n2, n3 - 1) & 0x2000) == 8192) {
                        graphics.drawRegion(e, 0, 504, 24, 24, 0, n2 * f, n3 * f, 0);
                    }
                    graphics.drawRegion(C, 0, (q.at % 8 >> 2) * 24, 24, 24, 0, n2 * f, n3 * f, 0);
                }
                if ((j.a(n2, n3) & 0x1000) == 4096) {
                    if ((j.a(n2, n3 - 1) & 0x20) == 32) {
                        graphics.drawRegion(A, 0, 24 * (q.at % 4), 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if ((j.a(n2, n3 - 1) & 0x2000) == 8192) {
                        graphics.drawRegion(e, 0, 504, 24, 24, 0, n2 * f, n3 * f, 0);
                    }
                    graphics.drawRegion(e, 0, (w[n3 * a + n2] - '\u0001') * f, 24, 24, 0, n2 * f, n3 * f, 0);
                }
                ++n3;
            }
            ++n2;
        }
    }

    public static final void d(Graphics graphics) {
        int n2 = q.W;
        while (n2 < q.Y) {
            int n3 = q.X;
            while (n3 < q.Z) {
                int n4 = w[n3 * a + n2] - '\u0001';
                if ((j.a(n2, n3) & 0x10) == 16) {
                    L = n2 * f - q.ay;
                    M = L - q.ad;
                    O = (f - 2) * M / f;
                    N = O + q.ad;
                    graphics.drawRegion(e, 0, n4 * f, 24, 24, 0, N + q.ay, n3 * f, 0);
                } else if ((j.a(n2, n3) & 0x100) != 256 && (j.a(n2, n3) & 0x200) != 512) {
                    if ((j.a(n2, n3) & 0x20) == 32) {
                        graphics.drawRegion(A, 0, 24 * (q.at % 8 >> 1), 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if (n4 == 17) {
                        graphics.drawRegion(B, 0, 24 * (q.at % 8 >> 1), 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if (n4 == 133) {
                        graphics.drawRegion(e, 0, 132 * f, 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if ((j.a(n2, n3) & 0x40) == 64 || (j.a(n2, n3) & 0x1000) == 4096) {
                        if ((j.a(n2, n3 - 1) & 0x20) == 32) {
                            graphics.drawRegion(A, 0, 24 * (q.at % 4), 24, 24, 0, n2 * f, n3 * f, 0);
                        } else if ((j.a(n2, n3 - 1) & 0x2000) == 8192) {
                            n4 = j.c(n2, n3 - 1);
                            n4 = n4 == 55 ? 54 : (n4 < 40 ? 21 : 110);
                            graphics.drawRegion(e, 0, n4 * 24, 24, 24, 0, n2 * f, n3 * f, 0);
                        }
                    } else if ((j.a(n2, n3) & 0x400) == 1024) {
                        graphics.drawRegion(e, 0, n4 * f, 24, 24, 0, n2 * f, n3 * f + 1, 0);
                    } else if (n4 != -1) {
                        graphics.drawRegion(e, 0, n4 * f, 24, 24, 0, n2 * f, n3 * f, 0);
                    }
                }
                ++n3;
            }
            ++n2;
        }
    }

    public static final void e(Graphics graphics) {
        if (q.b) {
            return;
        }
        int n2 = q.W;
        while (n2 < q.Y) {
            int n3 = q.X;
            while (n3 < q.Z) {
                if ((j.a(n2, n3) & 0x40) == 64) {
                    graphics.drawRegion(C, 0, (q.at % 8 >> 2) * 24, 24, 24, 0, n2 * f, n3 * f, 0);
                } else if ((j.a(n2, n3) & 0x100) == 256) {
                    L = n2 * f - q.ay;
                    M = L - q.ad;
                    O = (f - 1) * M / f;
                    N = O + q.ad;
                    graphics.drawImage(K, N + q.ay - 2 + (n2 + n3) % 5, n3 * f - 3 + n3 % 5, 0);
                }
                if ((j.a(n2, n3) & 0x200) == 512) {
                    graphics.drawRegion(e, 0, (w[n3 * a + n2] - '\u0001') * f, 24, 24, 0, n2 * f, n3 * f + 2, 0);
                }
                if ((j.a(n2, n3) & 0x1000) == 4096) {
                    graphics.drawImage(z, n2 * f, n3 * f, 0);
                }
                ++n3;
            }
            ++n2;
        }
    }

    private static int c(int n2, int n3) {
        try {
            return w[n3 * a + n2];
        }
        catch (Exception exception) {
            return 1000;
        }
    }

    public static final int a(int n2, int n3) {
        try {
            return x[n3 * a + n2];
        }
        catch (Exception exception) {
            return 1000;
        }
    }

    public static final int b(int n2, int n3) {
        try {
            return x[n3 / f * a + n2 / f];
        }
        catch (Exception exception) {
            return 1000;
        }
    }

    public static final boolean a(int n2, int n3, int n4) {
        try {
            return (x[n3 / f * a + n2 / f] & n4) == n4;
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static final void b(int n2, int n3, int n4) {
        int n5 = n3 / f * a + n2 / f;
        x[n5] = x[n5] | 0x400;
    }

    public static final void c(int n2, int n3, int n4) {
        int n5 = n3 / f * a + n2 / f;
        x[n5] = x[n5] & 0xFFFFFBFF;
    }

    public static final int d(int n2) {
        return n2 / f * f;
    }

    public static final int e(int n2) {
        return n2 / f * f;
    }
}


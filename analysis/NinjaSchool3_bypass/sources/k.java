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

public final class k {
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
    private static Image D;
    private static Image E;
    private static Image[] F;
    private static int G;
    private static int[] H;
    private static int[] I;
    private static int[] J;
    private static int[] K;
    private static int[] L;
    private static Image M;
    public static int f;
    private static int N;
    private static int O;
    private static int P;
    private static int Q;
    private static String[] R;
    private static int[] S;
    private static int[] T;
    private static int[] U;
    private static int V;
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
        F = null;
    }

    public static void b() {
        e = null;
        A = null;
        E = null;
        B = null;
        C = null;
        F = null;
        w = null;
        x = null;
        System.gc();
    }

    public static void a(int n2) {
        V = 0;
        if (n2 == 0) {
            V = 3;
            k.d();
            k.a(0, "L\u00e0ng Echigo /", 2333, 509);
            k.a(1, "C\u00e1nh \u0111\u1ed3ng Hiya", 672, 5);
            k.a(2, "* Khu \u0111\u00e1 \u0111\u1ecf Aiko", 72, 341);
        }
        if (n2 == 1) {
            V = 1;
            k.d();
            k.a(0, "Khu \u0111\u00e1 \u0111\u1ecf Akai /", 795, 845);
        }
        if (n2 == 2) {
            V = 2;
            k.d();
            k.a(0, "* D\u00f2ng s\u00f4ng b\u0103ng", 60, 197);
            k.a(1, "Khu \u0111\u00e1 \u0111\u1ecf Akai", 4560, 293);
        }
        if (n2 == 3) {
            V = 6;
            k.d();
            k.a(0, "* Khu \u0111\u00e1 \u0111\u1ecf Akai", 48, 389);
            k.a(1, "Mi\u1ec7ng n\u00fai l\u1eeda /", 3519, 197);
            k.a(2, "B\u00e1n HP-MP", 1344, 197);
            k.a(3, "B\u00e1n Items", 1992, 149);
            k.a(4, "Nh\u00e0 \u00f4ng Okamura", 1944, 341);
            k.a(5, "Nh\u00e0 b\u00e0 Katayama", 2664, 77);
        }
        if (n2 == 4) {
            V = 2;
            k.d();
            k.a(0, "D\u00f2ng s\u00f4ng b\u0103ng /", 5412, 365);
            k.a(1, "H\u1ed3 tr\u0103ng", 132, 365);
        }
        if (n2 == 5) {
            V = 2;
            k.d();
            k.a(0, "D\u00f2ng s\u00f4ng b\u0103ng", 144, 5);
            k.a(1, "H\u1ebbm n\u00fai Takana /", 1308, 197);
        }
        if (n2 == 6) {
            V = 3;
            k.d();
            k.a(0, "* H\u1ed3 tr\u0103ng", 156, 413);
            k.a(1, "L\u00e0ng Oshin", 1332, 725);
            k.a(2, "R\u1eebng ch\u1ebft /", 2246, 221);
        }
        if (n2 == 7) {
            V = 2;
            k.d();
            k.a(0, "* L\u00e0ng Echigo", 120, 605);
            k.a(1, "Hang n\u00fai Kurai /", 3000, 389);
        }
        if (n2 == 9) {
            V = 1;
            k.d();
            k.a(0, "* H\u1ebbm n\u00fai Takana", 120, 461);
        }
        if (n2 == 11) {
            V = 9;
            k.d();
            k.a(0, "H\u1ebbm n\u00fai Takana", 4704, 29);
            k.a(1, "* \u0110\u1ec1n Amaterasu", 96, 437);
            k.a(2, "L\u00e0ng Oshin", 3888, 341);
            k.a(3, "K\u00ednh ch\u00e0o Qu\u00fd kh\u00e1ch", 3888, 360);
            k.a(4, "Nh\u00e0 khoa h\u1ecdc Maikubi", 3552, 389);
            k.a(5, "B\u00e1n HP-MP", 1344, 269);
            k.a(6, "B\u00e1n Items", 1968, 245);
            k.a(7, "Nh\u00e0 h\u00e0ng chay", 1968, 365);
            k.a(8, "N\u01a1i th\u00ed nghi\u1ec7m", 3240, 365);
        }
        if (n2 == 12) {
            V = 1;
            k.d();
            k.a(0, "L\u00e0ng Oshin /", 3480, 413);
        }
        if (n2 == 13) {
            V = 2;
            k.d();
            k.a(0, "\u0110\u1ed9ng Tamatamo", 3480, 29);
            k.a(1, "* Phong \u1ea5n Ounio", 84, 461);
        }
        if (n2 == 17) {
            V = 1;
            k.d();
            k.a(0, "R\u1eebng t\u1ed1i /", 1560, 221);
        }
    }

    private static void a(int n2, String string, int n3, int n4) {
        k.R[n2] = string;
        k.S[n2] = n3;
        k.T[n2] = n4;
        int n5 = n2;
        S[n5] = S[n5] - (Midlet.a(string) >> 1);
        k.U[n2] = Midlet.a(string);
    }

    public static void a(Graphics graphics) {
        int n2 = 0;
        while (n2 < V) {
            if (S[n2] + U[n2] >= q.ax && S[n2] <= q.ax + q.S && T[n2] >= q.ay && T[n2] - 14 <= q.ay + q.T) {
                Midlet.a(graphics, R[n2], S[n2], T[n2], 0);
            }
            ++n2;
        }
    }

    private static void d() {
        R = new String[V];
        S = new int[V];
        T = new int[V];
        U = new int[V];
    }

    public static void b(int n2) {
        G = 0;
        if (n2 == 0) {
            if (F == null) {
                F = new Image[1];
                try {
                    k.F[0] = Image.createImage((String)"/t/h0.png");
                }
                catch (Exception exception) {}
            }
            G = 7;
            k.e();
            k.a(0, 0, 67, 26);
            k.a(1, 0, 88, 6);
            k.a(2, 0, 72, 17);
            k.a(3, 0, 50, 16);
            k.a(4, 0, 15, 9);
            k.a(5, 0, 22, 25);
            k.a(6, 0, 55, 28);
        }
        if (n2 == 2) {
            Image[] imageArray = new Image[1];
            F = imageArray;
            imageArray[0] = q.c("/i/n0.png");
            G = 1;
            k.e();
            k.a(0, 0, 93, 14);
        }
        if (n2 == 6) {
            if (F == null) {
                F = new Image[1];
                try {
                    k.F[0] = Image.createImage((String)"/t/h0.png");
                }
                catch (Exception exception) {}
            }
            G = 14;
            k.e();
            k.a(0, 0, 73, 21);
            k.a(1, 0, 49, 17);
            k.a(2, 0, 65, 27);
            k.a(3, 0, 97, 13);
            k.a(4, 0, 39, 7);
            k.a(5, 0, 19, 9);
            k.a(6, 0, 2, 20);
            k.a(7, 0, 22, 29);
            k.a(8, 0, 1, 32);
            k.a(9, 0, 11, 15);
            k.a(10, 0, 45, 27);
            k.a(11, 0, 47, 32);
            k.a(12, 0, 80, 12);
            k.a(13, 0, 76, 6);
        }
        if (n2 == 13) {
            if (F == null) {
                F = new Image[1];
                try {
                    k.F[0] = Image.createImage((String)"/t/h0.png");
                }
                catch (Exception exception) {}
            }
            G = 11;
            k.e();
            k.a(0, 0, 116, 20);
            k.a(1, 0, 102, 9);
            k.a(2, 0, 98, 19);
            k.a(3, 0, 69, 15);
            k.a(4, 0, 47, 20);
            k.a(5, 0, 41, 15);
            k.a(6, 0, 56, 18);
            k.a(7, 0, 67, 7);
            k.a(8, 0, 103, 4);
            k.a(9, 0, 111, 7);
            k.a(10, 0, 144, 6);
        }
        if (n2 == 17) {
            Image[] imageArray = new Image[1];
            F = imageArray;
            imageArray[0] = q.c("/i/n1.png");
            G = 1;
            k.e();
            k.a(0, 0, 12, 12);
        }
    }

    private static void a(int n2, int n3, int n4, int n5) {
        k.H[n2] = 0;
        k.I[n2] = n4 * f + 12;
        k.J[n2] = n5 * f;
        k.K[n2] = F[0].getWidth() >> 1;
        k.L[n2] = F[0].getHeight();
    }

    private static void e() {
        H = new int[G];
        I = new int[G];
        J = new int[G];
        K = new int[G];
        L = new int[G];
    }

    public static void b(Graphics graphics) {
        int n2 = 0;
        while (n2 < G) {
            if (I[n2] + K[n2] >= q.ax && I[n2] - K[n2] <= q.ax + q.S && J[n2] >= q.ay && J[n2] - L[n2] <= q.ay + q.T) {
                graphics.drawImage(F[H[n2]], I[n2], J[n2], 33);
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
                z = !q.a ? Image.createImage((String)"/t/uwt_hi.png") : Image.createImage((String)"/t/uwt.png");
            }
            if (A == null) {
                A = Image.createImage((String)"/t/wtf.png");
            }
            if (D == null) {
                D = Image.createImage((String)"/t/twtf.png");
            }
            if (E == null) {
                E = !q.a ? Image.createImage((String)"/t/wts_hi.png") : Image.createImage((String)"/t/wts.png");
            }
            if (M == null && !q.a) {
                M = Image.createImage((String)"/t/tb.png");
            }
            if (B == null) {
                B = q.c("/t/nt0.png");
            }
            if (C == null) {
                C = q.c("/t/nt1.png");
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
        i = -1;
        l = -1;
        k = -1;
        h = -1;
        g = -1;
        if (n2 == 18) {
            n2 = 6;
        }
        if (n2 == 0) {
            a = 100;
            b = 30;
            g = 1;
            m = 912;
            n = 912;
            h = 3;
            o = 24;
            p = 456;
            k = 2;
            s = 4680;
            t = 336;
        }
        if (n2 == 1) {
            a = 40;
            b = 40;
            h = 0;
            o = 24;
            p = 408;
            g = 19;
            m = 432;
            n = 336;
        }
        if (n2 == 2) {
            a = 200;
            b = 16;
            g = 4;
            m = 5472;
            n = 408;
            l = 0;
            u = 672;
            v = 48;
            h = 22;
            o = 24;
            p = 312;
        }
        if (n2 == 3) {
            a = 150;
            b = 21;
            g = 0;
            m = 2352;
            n = 576;
            h = 7;
            o = 24;
            p = 672;
        }
        if (n2 == 4) {
            a = 230;
            b = 20;
            h = 2;
            o = 24;
            p = 264;
            l = 5;
            u = 24;
            v = 72;
        }
        if (n2 == 5) {
            a = 61;
            b = 30;
            j = 624;
            h = 6;
            o = 24;
            p = 480;
            i = 20;
            q = 0;
            r = 48;
            k = 4;
            s = 96;
            t = 432;
        }
        if (n2 == 6) {
            a = 100;
            b = 34;
            g = 5;
            m = 1416;
            n = 264;
            h = 9;
            o = 24;
            p = 552;
            l = 11;
            u = 4656;
            v = 48;
        }
        if (n2 == 7) {
            a = 130;
            b = 30;
            g = 3;
            m = 3552;
            n = 240;
            h = 8;
            o = 24;
            p = 72;
        }
        if (n2 == 8) {
            a = 51;
            b = 41;
            g = 7;
            m = 3072;
            n = 480;
        }
        if (n2 == 9) {
            a = 150;
            b = 26;
            g = 6;
            m = 2352;
            n = 312;
            h = 17;
            o = 24;
            p = 288;
        }
        if (n2 == 10) {
            a = 100;
            b = 60;
            g = 15;
            m = 3600;
            n = 288;
            l = 13;
            u = 3528;
            v = 72;
        }
        if (n2 == 11) {
            a = 200;
            b = 24;
            g = 12;
            m = 3552;
            n = 480;
            k = 6;
            s = 1344;
            t = 768;
        }
        if (n2 == 12) {
            a = 150;
            b = 25;
            h = 11;
            o = 24;
            p = 504;
        }
        if (n2 == 13) {
            a = 150;
            b = 25;
            g = 14;
            m = 672;
            n = 408;
            k = 10;
            s = 600;
            t = 1392;
        }
        if (n2 == 14) {
            a = 30;
            b = 20;
            h = 13;
            o = 24;
            p = 528;
        }
        if (n2 == 16) {
            a = 20;
            b = 15;
        }
        if (n2 == 17) {
            a = 70;
            b = 14;
            g = 9;
            m = 3552;
            n = 432;
            h = 15;
            o = 24;
            p = 384;
        }
        if (n2 == 15) {
            a = 151;
            b = 20;
            g = 17;
            m = 1632;
            n = 288;
            h = 10;
            o = 24;
            p = 144;
            k = 21;
            s = 360;
            t = 432;
        }
        if (n2 == 19) {
            a = 20;
            b = 16;
            h = 1;
            o = 24;
            p = 120;
        }
        if (n2 == 20) {
            a = 20;
            b = 12;
            g = 5;
            m = 1416;
            n = 696;
        }
        if (n2 == 21) {
            a = 20;
            b = 20;
            l = 15;
            u = 624;
            v = 48;
        }
        if (n2 == 22) {
            a = 23;
            b = 15;
            g = 2;
            m = 4752;
            n = 120;
        }
        c = a * f;
        d = b * f;
        try {
            InputStream inputStream = "".getClass().getResourceAsStream("/m/" + n2);
            w = new char[a * b];
            x = new int[a * b];
            int n3 = 0;
            while (n3 < a * b) {
                k.w[n3] = (char)inputStream.read();
                if (w[n3] != '\u0000') {
                    k.y[k.w[n3] - '\u0001'] = true;
                }
                if (w[n3] == '\u0001' || w[n3] == '\u0002' || w[n3] == '\u0003' || w[n3] == '\u0004' || w[n3] == '\u0005' || w[n3] == '\u0006' || w[n3] == '\u0007' || w[n3] == '\u000b' || w[n3] == '\u000e' || w[n3] == '\u0011' || w[n3] == '+' || w[n3] == '3' || w[n3] == '?' || w[n3] == 'A' || w[n3] == 'C' || w[n3] == 'D' || w[n3] == 'G' || w[n3] == 'H' || w[n3] == 'S' || w[n3] == 'T' || w[n3] == 'U' || w[n3] == 'W' || w[n3] == '[' || w[n3] == '^' || w[n3] == 'a' || w[n3] == 'b' || w[n3] == 'j' || w[n3] == 'k' || w[n3] == 'o' || w[n3] == 'u' || w[n3] == 'v' || w[n3] == 'w' || w[n3] == '}' || w[n3] == '~' || w[n3] == '\u0081' || w[n3] == '\u0082' || w[n3] == '\u0083' || w[n3] == '\u0085' || w[n3] == '\u0088' || w[n3] == '\u008a' || w[n3] == '\u008b' || w[n3] == '\u008e') {
                    int n4 = n3;
                    x[n4] = x[n4] | 2;
                }
                if (w[n3] == '|' || w[n3] == 't' || w[n3] == '{' || w[n3] == ',' || w[n3] == '\f' || w[n3] == '\u000f' || w[n3] == '\u0010' || w[n3] == '-' || w[n3] == '\n' || w[n3] == '\t') {
                    int n5 = n3;
                    x[n5] = x[n5] | 0x2000;
                }
                if (w[n3] == '\u0017') {
                    int n6 = n3;
                    x[n6] = x[n6] | 0x20;
                }
                if (w[n3] == '\u0018') {
                    int n7 = n3;
                    x[n7] = x[n7] | 0x80;
                }
                if (w[n3] == '\u0006' || w[n3] == '\u000f' || w[n3] == '3' || w[n3] == '_' || w[n3] == 'a' || w[n3] == 'j' || w[n3] == 'o' || w[n3] == '{' || w[n3] == '}' || w[n3] == '\u008a' || w[n3] == '\u008c') {
                    int n8 = n3;
                    x[n8] = x[n8] | 4;
                }
                if (w[n3] == '\u0007' || w[n3] == '\u0010' || w[n3] == '3' || w[n3] == '`' || w[n3] == 'b' || w[n3] == 'k' || w[n3] == 'o' || w[n3] == '|' || w[n3] == '~' || w[n3] == '\u008b' || w[n3] == '\u008d') {
                    int n9 = n3;
                    x[n9] = x[n9] | 8;
                }
                if (w[n3] == '\u0019') {
                    int n10 = n3;
                    x[n10] = x[n10] | 0x40;
                    if ((x[n3 - a] & 0x2000) == 8192) {
                        int n11 = n3;
                        x[n11] = x[n11] | 0x2000;
                    }
                }
                if (w[n3] == '\"') {
                    int n12 = n3;
                    x[n12] = x[n12] | 0x1000;
                }
                if (w[n3] == '!') {
                    int n13 = n3;
                    x[n13] = x[n13] | 0x100;
                }
                if (w[n3] == '\u0011') {
                    int n14 = n3;
                    x[n14] = x[n14] | 0x800;
                }
                if (w[n3] == 'g' || w[n3] == 'h' || w[n3] == 'i' || w[n3] == '\u001a') {
                    int n15 = n3;
                    x[n15] = x[n15] | 0x200;
                }
                if (w[n3] == '3' || w[n3] == 'o' || w[n3] == 'D') {
                    int n16 = n3;
                    x[n16] = x[n16] | 0x4000;
                }
                if (w[n3] == 'R' || w[n3] == 'n' || w[n3] == '\u008f') {
                    int n17 = n3;
                    x[n17] = x[n17] | 0x8000;
                }
                if (w[n3] == 'q') {
                    int n18 = n3;
                    x[n18] = x[n18] | 0x20000;
                }
                if (w[n3] == '\u008e') {
                    int n19 = n3;
                    x[n19] = x[n19] | 0x10000;
                }
                if (w[n3] == '(' || w[n3] == ')') {
                    int n20 = n3;
                    x[n20] = x[n20] | 0x40000;
                }
                if (w[n3] == 'n') {
                    int n21 = n3;
                    x[n21] = x[n21] | 0x80000;
                }
                if (w[n3] == '\u008f') {
                    int n22 = n3;
                    x[n22] = x[n22] | 0x100000;
                }
                ++n3;
            }
        }
        catch (Exception exception) {
            System.out.println("Error Load Map");
            exception.printStackTrace();
            Midlet.b.b();
        }
        k.c();
    }

    public static final void c(Graphics graphics) {
        System.out.println(q.bc);
        int n2 = q.N;
        while (n2 < q.P) {
            int n3 = q.O;
            while (n3 < q.Q) {
                int n4 = w[n3 * a + n2] - '\u0001';
                if (n4 != -1) {
                    if (q.ap == 15 && q.bc % 3 != 0) {
                        graphics.setColor(1513480);
                        graphics.fillRect(n2 * f, n3 * f, 24, 24);
                    } else {
                        graphics.drawRegion(e, 0, n4 * f, 24, 24, 0, n2 * f, n3 * f, 0);
                    }
                }
                if ((k.b(n2, n3) & 0x20) == 32) {
                    graphics.drawRegion(A, 0, 24 * (q.aq % 4), 24, 24, 0, n2 * f, n3 * f, 0);
                } else if ((k.b(n2, n3) & 0x40) == 64) {
                    if ((k.b(n2, n3 - 1) & 0x20) == 32) {
                        graphics.drawRegion(A, 0, 24 * (q.aq % 4), 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if ((k.b(n2, n3 - 1) & 0x2000) == 8192) {
                        graphics.drawRegion(e, 0, 504, 24, 24, 0, n2 * f, n3 * f, 0);
                    }
                    graphics.drawRegion(E, 0, (q.aq % 8 >> 2) * 24, 24, 24, 0, n2 * f, n3 * f, 0);
                }
                if ((k.b(n2, n3) & 0x1000) == 4096) {
                    if ((k.b(n2, n3 - 1) & 0x20) == 32) {
                        graphics.drawRegion(A, 0, 24 * (q.aq % 4), 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if ((k.b(n2, n3 - 1) & 0x2000) == 8192) {
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
        int n2 = q.N;
        while (n2 < q.P) {
            int n3 = q.O;
            while (n3 < q.Q) {
                int n4 = w[n3 * a + n2] - '\u0001';
                if ((k.b(n2, n3) & 0x10) == 16) {
                    N = n2 * f - q.ax;
                    O = N - q.U;
                    Q = (f - 2) * O / f;
                    P = Q + q.U;
                    graphics.drawRegion(e, 0, n4 * f, 24, 24, 0, P + q.ax, n3 * f, 0);
                } else if ((k.b(n2, n3) & 0x100) != 256) {
                    if ((k.b(n2, n3) & 0x20) == 32) {
                        graphics.drawRegion(A, 0, 24 * (q.aq % 8 >> 1), 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if (n4 == 23) {
                        graphics.drawRegion(D, 0, 24 * (q.aq % 8 >> 1), 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if ((k.b(n2, n3) & 0x80000) == 524288) {
                        graphics.drawRegion(B, 0, 24 * (q.aq % 8 >> 2), 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if ((k.b(n2, n3) & 0x100000) == 0x100000) {
                        graphics.drawRegion(C, 0, 24 * (q.aq % 8 >> 2), 24, 24, 0, n2 * f, n3 * f, 0);
                    } else if ((k.b(n2, n3) & 0x40) == 64 || (k.b(n2, n3) & 0x1000) == 4096) {
                        if ((k.b(n2, n3 - 1) & 0x20) == 32) {
                            graphics.drawRegion(A, 0, 24 * (q.aq % 4), 24, 24, 0, n2 * f, n3 * f, 0);
                        } else if ((k.b(n2, n3 - 1) & 0x2000) == 8192) {
                            n4 = k.a(n2, n3 - 1);
                            if (n4 == 25) {
                                n4 = k.a(n2, n3 - 2);
                            }
                            if (n4 == 45) {
                                n4 = 44;
                            }
                            graphics.drawRegion(e, 0, --n4 * 24, 24, 24, 0, n2 * f, n3 * f, 0);
                        }
                    } else if ((k.b(n2, n3) & 0x400) == 1024) {
                        graphics.drawRegion(e, 0, n4 * f, 24, 24, 0, n2 * f, n3 * f + 1, 0);
                    } else if (n4 != -1) {
                        if (q.ap == 15 && q.bc % 3 != 0) {
                            graphics.setColor(1513480);
                            graphics.fillRect(n2 * f, n3 * f, 24, 24);
                        } else {
                            graphics.drawRegion(e, 0, n4 * f, 24, 24, 0, n2 * f, n3 * f, 0);
                        }
                    }
                }
                ++n3;
            }
            ++n2;
        }
    }

    public static final void e(Graphics graphics) {
        int n2;
        int n3;
        if (q.ap == 20) {
            n3 = q.N;
            while (n3 < q.P) {
                n2 = q.O;
                while (n2 < q.Q) {
                    graphics.drawImage(z, n3 * f, n2 * f, 0);
                    ++n2;
                }
                ++n3;
            }
        }
        if (q.a) {
            return;
        }
        n3 = q.N;
        while (n3 < q.P) {
            n2 = q.O;
            while (n2 < q.Q) {
                if ((k.b(n3, n2) & 0x40) == 64) {
                    graphics.drawRegion(E, 0, (q.aq % 8 >> 2) * 24, 24, 24, 0, n3 * f, n2 * f, 0);
                } else if ((k.b(n3, n2) & 0x100) == 256) {
                    N = n3 * f - q.ax;
                    O = N - q.U;
                    Q = (f - 1) * O / f;
                    P = Q + q.U;
                    graphics.drawImage(M, P + q.ax - 2 + (n3 + n2) % 5, n2 * f - 3 + n2 % 5, 0);
                }
                if ((k.b(n3, n2) & 0x200) == 512) {
                    graphics.drawRegion(e, 0, (w[n2 * a + n3] - '\u0001') * f, 24, 24, 0, n3 * f, n2 * f + 2, 0);
                }
                if ((k.b(n3, n2) & 0x1000) == 4096) {
                    graphics.drawImage(z, n3 * f, n2 * f, 0);
                }
                ++n2;
            }
            ++n3;
        }
    }

    public static final int a(int n2, int n3) {
        try {
            return w[n3 * a + n2];
        }
        catch (Exception exception) {
            return 1000;
        }
    }

    public static final int b(int n2, int n3) {
        try {
            return x[n3 * a + n2];
        }
        catch (Exception exception) {
            return 1000;
        }
    }

    public static final int c(int n2, int n3) {
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


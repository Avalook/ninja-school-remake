/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class c {
    private static Image[][] n;
    public static int a;
    public static int[] b;
    public static int[] c;
    private static int[] o;
    public static final int[] d;
    public static final int[] e;
    private static int[] p;
    private static Image q;
    public static boolean f;
    public static int g;
    public static int h;
    public static int i;
    private static int r;
    private static int s;
    private static Image[] t;
    public static int j;
    public static int k;
    private static int u;
    private static int[][] v;
    private static int[][] w;
    public static int l;
    public static boolean m;
    private static int x;
    private static int y;
    private static int z;
    private static String[][] A;
    private static int B;
    private static int C;
    private static int D;
    private static int E;
    private static int F;
    private static int G;
    private static int H;

    static {
        d = new int[]{2, -1, -1};
        int[] nArray = new int[3];
        nArray[0] = 20;
        e = nArray;
        int[] nArray2 = new int[8];
        nArray2[0] = 5;
        nArray2[1] = 7;
        nArray2[2] = 5;
        nArray2[3] = 5;
        nArray2[4] = 9;
        nArray2[5] = 3;
        nArray2[7] = 15;
        p = nArray2;
        i = 1;
        m = false;
    }

    public static void a() {
        n = null;
        q = null;
        System.gc();
    }

    public static void a(int n2) {
        switch (o[n2]) {
            case 0: {
                if (!i.i) {
                    if (!p.e) {
                        q.a(m.h[0], -1, b[n2], c[n2] - 40);
                        break;
                    }
                    if (!p.f) {
                        q.a(m.h[1], -1, b[n2], c[n2] - 40);
                        break;
                    }
                    if (p.f) {
                        q.a(m.h[2], -1, b[n2], c[n2] - 40);
                    }
                }
                return;
            }
            case 1: {
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                if (q.ap == 3 && n2 == 0 || q.ap == 11 && n2 == 1 && !q.br[14] || q.ap == 11 && n2 == 0 && q.br[14]) {
                    q.B = 9;
                    q.bj = 0;
                    q.y();
                }
                return;
            }
            case 4: {
                q.a(m.h[q.b(3, 5)], -1, b[n2], c[n2] - 40);
                return;
            }
            case 5: {
                if (!(q.ap == 3 && n2 == 1 || q.ap == 11 && n2 == 2 && !q.br[14]) && (q.ap != 11 || n2 != 1 || !q.br[14])) break;
                q.B = 16;
                q.bj = 0;
                q.y();
            }
        }
    }

    public static void b(int n2) {
        a = 0;
        if (n2 == 16) {
            if (q.aX) {
                n = new Image[1][2];
                try {
                    c.n[0][0] = Image.createImage((String)"/npc/00.png");
                    c.n[0][1] = Image.createImage((String)"/npc/01.png");
                }
                catch (Exception exception) {}
                a = 1;
                c.e();
                c.a(0, 0, 11, 9);
                return;
            }
            if (!q.b) {
                n = new Image[2][2];
                try {
                    c.n[1][0] = Image.createImage((String)"/npc/10.png");
                    c.n[1][1] = Image.createImage((String)"/npc/11.png");
                }
                catch (Exception exception) {}
                a = 1;
                c.e();
                c.a(0, 1, 9, 9);
                return;
            }
            n = new Image[3][2];
            try {
                c.n[0][0] = Image.createImage((String)"/npc/00.png");
                c.n[0][1] = Image.createImage((String)"/npc/01.png");
                c.n[1][0] = Image.createImage((String)"/npc/10.png");
                c.n[1][1] = Image.createImage((String)"/npc/11.png");
                c.n[2][0] = Image.createImage((String)"/npc/20.png");
                c.n[2][1] = Image.createImage((String)"/npc/21.png");
            }
            catch (Exception exception) {}
            a = 3;
            c.e();
            c.a(0, 0, 11, 9);
            c.a(1, 1, 9, 9);
            c.a(2, 2, 12, 9);
        }
        if (n2 == 0) {
            n = new Image[2][2];
            c.n[1][0] = q.c("/npc/10.png");
            c.n[1][1] = q.c("/npc/11.png");
            a = 1;
            c.e();
            c.a(0, 1, 63, 26);
        }
        if (n2 == 3) {
            n = new Image[6][2];
            try {
                c.n[3][0] = Image.createImage((String)"/npc/30.png");
                c.n[3][1] = Image.createImage((String)"/npc/31.png");
                c.n[5][0] = Image.createImage((String)"/npc/50.png");
                c.n[5][1] = Image.createImage((String)"/npc/51.png");
                c.n[4][0] = Image.createImage((String)"/npc/40.png");
                c.n[4][1] = Image.createImage((String)"/npc/41.png");
                q = Image.createImage((String)"/npc/l.png");
            }
            catch (Exception exception) {}
            a = 3;
            c.e();
            c.a(0, 3, 55, 11);
            c.a(1, 5, 83, 9);
            c.a(2, 4, 138, 18);
            r = 1983;
            s = 216;
        }
        if (n2 == 11) {
            n = new Image[6][2];
            c.n[0][0] = q.c("/npc/00.png");
            c.n[0][1] = q.c("/npc/01.png");
            c.n[3][0] = q.c("/npc/30.png");
            c.n[3][1] = q.c("/npc/31.png");
            c.n[5][0] = q.c("/npc/50.png");
            c.n[5][1] = q.c("/npc/51.png");
            if (!q.br[14]) {
                a = 3;
                c.e();
                c.a(0, 0, 29, 14);
                c.a(1, 3, 55, 14);
                c.a(2, 5, 81, 13);
            } else {
                a = 2;
                c.e();
                c.a(0, 3, 55, 14);
                c.a(1, 5, 81, 13);
            }
        }
        if (n2 == 14) {
            n = new Image[8][2];
            c.n[7][0] = q.c("/npc/70.png");
            c.n[7][1] = q.c("/npc/71.png");
            a = 1;
            c.e();
            c.a(0, 7, 15, 17);
            a = 0;
        }
    }

    private static void a(int n2, int n3, int n4, int n5) {
        c.o[n2] = n3;
        c.b[n2] = n4 * k.f + 12;
        c.c[n2] = n5 * k.f;
    }

    private static void e() {
        b = new int[a];
        c = new int[a];
        o = new int[a];
    }

    public static void a(Graphics graphics) {
        int n2 = 0;
        while (n2 < a) {
            int n3 = n2;
            Graphics graphics2 = graphics;
            if (!(q.ap == 16 && n3 == 2 && !f || q.ap == 0 && h == 0 && g == 50 || q.ap == 14 && h.bm > 0 && h.bm % 5 > 2 || b[n3] + 10 < q.ax || b[n3] - 10 > q.ax + q.S || c[n3] < q.ay || c[n3] - 32 > q.ay + q.T)) {
                if (h != 0) {
                    graphics2.setClip(b[n3] - 25, c[n3] - 50, 50, 50 - g);
                }
                graphics2.drawImage(n[o[n3]][1], b[n3], c[n3], 33);
                graphics2.drawImage(n[o[n3]][0], b[n3], c[n3] - p[o[n3]] + (q.aq % (15 + o[n3]) > 4 ? 0 : 1), 33);
            }
            ++n2;
        }
        if (q != null) {
            graphics.drawImage(q, r, s, 33);
        }
    }

    public static void b() {
        if (h == 0) {
            return;
        }
        if (g == 0) {
            o.b.addElement(new j(b[0], c[0] - 17, 40));
            o.b.addElement(new j(i.F, i.G - 17, 40));
        }
        if (h == 1 && g < 50) {
            g += 7;
        }
        if (h == -1 && g > 0) {
            g -= 7;
        }
        if (g <= 0) {
            g = 0;
            h = 0;
        }
        if (g >= 50) {
            g = 50;
            h = 0;
            a = 0;
            i.D = false;
        }
    }

    public static void c() {
        if (q.ap != 11) {
            return;
        }
        t = new Image[5];
        int n2 = 0;
        while (n2 < 5) {
            c.t[n2] = q.c("/npc/" + n2 + ".png");
            ++n2;
        }
        int[][] nArrayArray = new int[3][];
        int[] nArray = new int[4];
        nArray[0] = 5;
        nArray[2] = -10;
        nArrayArray[0] = nArray;
        int[] nArray2 = new int[4];
        nArray2[0] = 5;
        nArray2[2] = -10;
        nArrayArray[1] = nArray2;
        int[] nArray3 = new int[4];
        nArray3[0] = 5;
        nArray3[2] = -10;
        nArrayArray[2] = nArray3;
        v = nArrayArray;
        int[][] nArrayArray2 = new int[3][];
        int[] nArray4 = new int[2];
        nArray4[1] = 2;
        nArrayArray2[0] = nArray4;
        nArrayArray2[1] = new int[]{1, 2};
        nArrayArray2[2] = new int[]{4, 2};
        w = nArrayArray2;
        j = 3468;
        k = 456;
    }

    /*
     * Unable to fully structure code
     */
    public static void d() {
        block7: {
            block8: {
                if (q.ap != 11) {
                    return;
                }
                if (!c.m) break block7;
                if (c.E >= c.A[0].length) break block8;
                if (++c.D >= c.A[0][c.E].length()) {
                    c.D = 0;
                    ++c.E;
                }
                ** GOTO lbl-1000
            }
            if (c.E == c.A[0].length && ++c.H == 10) {
                c.m = false;
            } else if (0 >= c.y) {
                c.m = false;
            }
        }
        if (c.l == 1) {
            return;
        }
        v0 = c.u = q.aq % 8 > 5 ? 0 : 1;
        if (q.aq % q.b(10, 200) == 0 && !c.m) {
            c.a(m.f[q.b(0, 14)], c.j, c.k - 40);
        }
    }

    public static void a(String[] stringArray, int n2, int n3) {
        B = n2;
        C = n3;
        y = stringArray.length;
        z = 0;
        A = new String[y][];
        F = q.S >> 1;
        if (F < 120) {
            F = 120;
        }
        n2 = 0;
        while (n2 < y) {
            c.A[n2] = Midlet.b(stringArray[n2], F - 20);
            ++n2;
        }
        G = B - (F >> 1) - q.ax;
        if (G < 2) {
            G = 2;
        }
        if (G + F > q.S - 2) {
            G = q.S - 2 - F;
        }
        m = true;
        D = 0;
        E = 0;
        H = 0;
    }

    public static void b(Graphics graphics) {
        if (q.ap != 11) {
            return;
        }
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(-1, -1, q.v + 2, q.w + 2);
        x = A[0].length;
        int n2 = C - q.ay - (x << 4);
        boolean bl = true;
        if (n2 < 10) {
            bl = false;
            n2 = 10;
        }
        graphics.setColor(0xFFFFFF);
        graphics.fillRect(B - 60 - q.ax, n2 - 5, F, (x << 4) + 7);
        graphics.setColor(17408);
        graphics.drawRect(B - 60 - 1 - q.ax, n2 - 5, F + 1, (x << 4) + 7);
        int n3 = 0;
        while (n3 < E) {
            Midlet.b(graphics, A[0][n3], B - 60 + (F >> 1) - q.ax, n2 + (n3 << 4), 2);
            ++n3;
        }
        if (E < A[0].length) {
            Midlet.b(graphics, A[0][E].substring(0, D), B - 60 + (F >> 1) - q.ax, n2 + (E << 4), 2);
        }
        if (!q.l && bl && B > q.ax + 10 && B < q.ax + q.S - 10) {
            graphics.drawImage(q.ao, B - q.ax + 3, C - q.ay + 2, 17);
        }
    }

    public static void c(Graphics graphics) {
        if (q.ap != 11) {
            return;
        }
        if (l == 0) {
            graphics.drawImage(t[w[u][0]], j + v[u][0], k + v[u][2], 33);
            graphics.drawImage(t[w[u][1]], j + v[u][1], k + v[u][3], 33);
        }
        if (l == 1) {
            graphics.drawImage(t[4], j + v[0][0], k + v[0][2], 33);
            graphics.drawImage(t[w[0][1]], j + v[0][1], k + v[0][3], 33);
            graphics.drawImage(t[3], j - 6, k, 33);
        }
        if (l == 2) {
            graphics.drawImage(t[w[u][0]], j + v[u][0], k + v[u][2], 33);
            graphics.drawImage(t[w[u][1]], j + v[u][1], k + v[u][3], 33);
            graphics.drawImage(t[3], j - 6, k, 33);
        }
    }
}


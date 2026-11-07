/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class t {
    public static boolean a;
    public static Image[] b;
    public static int c;
    public static int d;
    private static int p;
    public static int e;
    public static int f;
    private static int q;
    private static int r;
    private static int s;
    private static int t;
    public static int g;
    public static int h;
    private static int u;
    private static int v;
    private static int[][] w;
    private static int[][] x;
    private static int[][] y;
    public static int i;
    public static int j;
    public static int k;
    public static int l;
    private static int z;
    public static boolean m;
    public static int n;
    public static Vector o;

    static {
        o = new Vector();
    }

    private static void c(int n2) {
        if (b == null) {
            switch (n2) {
                case 0: {
                    b = new Image[6];
                    n2 = 0;
                    while (n2 < 6) {
                        try {
                            t.b[n2] = Image.createImage((String)("/mob/b2" + n2 + ".png"));
                        }
                        catch (IOException iOException) {
                            System.out.println("Bos ERR");
                        }
                        ++n2;
                    }
                    return;
                }
                case 1: {
                    b = new Image[9];
                    try {
                        n2 = 0;
                        while (n2 < 8) {
                            t.b[n2] = Image.createImage((String)("/mob/s" + n2 + ".png"));
                            ++n2;
                        }
                        t.b[8] = Image.createImage((String)"/cp/cb0.png");
                        return;
                    }
                    catch (IOException iOException) {
                        System.out.println("Bos ERR");
                    }
                }
            }
        }
    }

    public static void a() {
        b = null;
        System.gc();
    }

    public static void a(int n2) {
        a = false;
        if (n2 == 101 && !g.k && g.j) {
            n = 0;
            t.c(0);
            a = true;
            c = 216;
            d = 264;
            q = 72;
            r = 432;
            e = -1;
            f = 0;
            h = 32;
            u = 50;
            s = 0;
            p = 3;
            g = 40;
            int[][] nArrayArray = new int[5][];
            int[] nArray = new int[7];
            nArray[1] = -1;
            nArray[2] = 2;
            nArray[3] = -11;
            nArrayArray[0] = nArray;
            int[] nArray2 = new int[7];
            nArray2[0] = 1;
            nArray2[1] = -1;
            nArray2[2] = 2;
            nArray2[3] = -9;
            nArrayArray[1] = nArray2;
            int[] nArray3 = new int[7];
            nArray3[0] = 1;
            nArray3[1] = -1;
            nArray3[2] = 3;
            nArray3[3] = -12;
            nArrayArray[2] = nArray3;
            int[] nArray4 = new int[7];
            nArray4[0] = 1;
            nArray4[1] = -1;
            nArray4[2] = 4;
            nArray4[3] = -24;
            nArrayArray[3] = nArray4;
            int[] nArray5 = new int[7];
            nArray5[0] = 1;
            nArray5[1] = 5;
            nArray5[2] = -1;
            nArray5[5] = -10;
            nArrayArray[4] = nArray5;
            w = nArrayArray;
        }
        if (n2 == 100 && !m) {
            n = 1;
            t.c(1);
            a = true;
            c = 312;
            d = 264;
            q = 192;
            r = 384;
            e = -1;
            f = 0;
            h = 32;
            u = 50;
            s = 0;
            p = 3;
            g = 55;
            int[][] nArrayArray = new int[6][];
            int[] nArray = new int[6];
            nArray[0] = 2;
            nArray[1] = -66;
            nArray[2] = -1;
            nArray[4] = -18;
            nArrayArray[0] = nArray;
            int[] nArray6 = new int[6];
            nArray6[0] = 2;
            nArray6[1] = -55;
            nArray6[2] = -1;
            nArray6[4] = -18;
            nArrayArray[1] = nArray6;
            int[] nArray7 = new int[6];
            nArray7[0] = 2;
            nArray7[1] = -66;
            nArray7[2] = -1;
            nArray7[4] = -15;
            nArrayArray[2] = nArray7;
            int[] nArray8 = new int[6];
            nArray8[0] = 2;
            nArray8[1] = -19;
            nArray8[3] = -1;
            nArrayArray[3] = nArray8;
            int[] nArray9 = new int[6];
            nArray9[3] = 4;
            nArrayArray[4] = nArray9;
            nArrayArray[5] = new int[]{2, -52, -1, -2, -7, -2};
            w = nArrayArray;
            int[][] nArrayArray2 = new int[6][];
            nArrayArray2[0] = new int[]{6, 2, 1};
            nArrayArray2[1] = new int[]{6, 2, 3};
            int[] nArray10 = new int[3];
            nArray10[0] = 6;
            nArray10[2] = 1;
            nArrayArray2[2] = nArray10;
            nArrayArray2[3] = new int[]{6, 5, 8};
            nArrayArray2[4] = new int[]{6, 8, 8};
            nArrayArray2[5] = new int[]{6, 4, 3};
            x = nArrayArray2;
            int[][] nArrayArray3 = new int[3][];
            int[] nArray11 = new int[4];
            nArray11[2] = 1;
            nArray11[3] = 1;
            nArrayArray3[0] = nArray11;
            nArrayArray3[1] = new int[]{3, 4, 4};
            nArrayArray3[2] = new int[]{3, 3, 4, 4, 1, 1, 3, 3};
            y = nArrayArray3;
        }
    }

    /*
     * Unable to fully structure code
     */
    public static void b() {
        block90: {
            if (!t.a) {
                return;
            }
            var0 = 0;
            while (var0 < t.o.size()) {
                var1_1 = (k)t.o.elementAt(var0);
                var1_1.a += 4 * t.e;
                var1_1.b += var1_1.f;
                if (q.at % 10 == 0) {
                    ++var1_1.c;
                }
                if (var1_1.c >= 4) {
                    t.o.removeElement(var1_1);
                }
                if (q.a(g.at - var1_1.a) < 20 && q.a(g.au - var1_1.b) < 10) {
                    if (g.ax != 5) {
                        g.a(t.g, t.e);
                        t.o.removeElement(var1_1);
                    } else {
                        t.o.removeAllElements();
                    }
                }
                ++var0;
            }
            block0 : switch (t.p) {
                case 1: {
                    switch (t.n) {
                        case 0: {
                            t.c -= t.e;
                            t.f = 4;
                            t.d += t.v;
                            ++t.v;
                            if (t.d <= 360) break block0;
                            g.k = true;
                            q.a("Quay v\u1ec1 g\u1eb7p gi\u00e0 l\u00e0ng");
                            t.p = 5;
                            return;
                        }
                        case 1: {
                            t.f = 5;
                            v0 = t.w[t.f];
                            v0[3] = v0[3] + t.t;
                            v1 = t.w[t.f];
                            v1[4] = v1[4] + t.s;
                            v2 = t.w[t.f];
                            v2[5] = v2[5] + t.v;
                            ++t.s;
                            ++t.t;
                            ++t.v;
                            if (t.w[t.f][5] > 360) {
                                t.a = false;
                                g.bm = true;
                                g.O = true;
                                g.b(q.as);
                                g.d(q.as);
                                g.bL = 0;
                                g.bp = 120;
                                g.S = 120;
                                g.R = 0;
                                g.bo = 0;
                                q.g(52);
                                return;
                            }
                            if (t.w[t.f][5] < 168 || t.m) break block0;
                            q.a("C\u1eeda hang \u0111\u00e3 \u0111\u01b0\u1ee3c m\u1edf");
                            ++q.aO;
                            t.m = true;
                        }
                    }
                    return;
                }
                case 6: {
                    switch (t.n) {
                        case 0: {
                            t.f = 4;
                            t.c -= t.e;
                            if (++t.s <= 5) break block0;
                            if (t.d == g.au && q.at % 10 > 7) {
                                t.p = 4;
                                t.s = 0;
                                t.t = 0;
                                t.e = t.c > g.at ? -1 : 1;
                                return;
                            }
                            t.t = t.q + q.at % (t.r - t.q - 40);
                            t.e = t.t > t.c ? 1 : -1;
                            t.s = 0;
                            t.p = 7;
                            return;
                        }
                        case 1: {
                            t.f = 5;
                            t.c += g.aF << 1;
                            if (++t.s > 5) {
                                t.p = 4;
                                t.s = 0;
                                t.t = 10;
                            }
                            t.d();
                        }
                    }
                    return;
                }
                case 12: {
                    switch (t.n) {
                        case 0: {
                            if (++t.s > 20) {
                                t.t = t.q + q.at % (t.r - t.q - 40);
                                t.e = t.t > t.c ? 1 : -1;
                                t.s = 0;
                                t.p = 7;
                                return;
                            }
                            t.f = t.s % 10 > 7 ? 3 : 0;
                            if ((t.s >> 1) % 10 != 0) break block0;
                            q.b(t.c + (t.e << 4), t.d - 16, t.e << 3, t.g >> 1, 2);
                            return;
                        }
                        case 1: {
                            ++t.s;
                            if (q.at % 5 == 0) {
                                v3 = t.f = t.f == 0 ? 1 : 0;
                            }
                            if (t.s < 8) {
                                t.c += 4 * t.e;
                            } else if (t.s < 16) {
                                t.c -= 4 * t.e;
                            } else {
                                t.s = 0;
                                t.t = 0;
                                t.p = 3;
                            }
                            if (q.a(t.c - g.at - 50 * t.e) >= 130 || q.a(g.au - t.d) >= 10 || q.a(t.c - g.at - 50 * t.e) <= 70) break block0;
                            g.a(t.g / 2, t.e);
                        }
                    }
                    return;
                }
                case 4: {
                    switch (t.n) {
                        case 0: {
                            if (++t.s > 10) {
                                t.p = 3;
                                t.s = 0;
                                t.t = 0;
                            }
                            if ((t.s == 5 || t.s == 9) && t.d - g.au < 30 && q.a(g.at - (t.c + (t.e << 4))) < 40) {
                                g.a(t.g, -t.e);
                            }
                            if (t.s == 4 || t.s == 5 || t.s == 8 || t.s == 9) {
                                t.c += t.e << 1;
                                t.f = 3;
                                return;
                            }
                            t.c -= t.e;
                            t.f = 0;
                            return;
                        }
                        case 1: {
                            if (t.t == 10) {
                                if (q.at % 2 == 0) {
                                    t.f = t.y[2][t.s];
                                    t.c += t.e << 2;
                                    if (++t.s >= t.y[2].length) {
                                        t.t = 0;
                                        t.s = 0;
                                        t.p = 3;
                                    }
                                    if (t.c < t.q) {
                                        t.c = t.q;
                                        t.e = 1;
                                    }
                                    if (t.c > t.r) {
                                        t.c = t.r;
                                        t.e = -1;
                                    }
                                }
                                t.t = 0;
                                t.s = 0;
                                t.p = 12;
                            }
                            if (t.t != 15) break block0;
                            t.f = 2;
                            t.s = 0;
                            t.p = 3;
                            t.o.addElement(new k(t.c - t.w[t.f][1] * t.e, t.d + t.w[t.f][4] - 7, 0));
                        }
                    }
                    return;
                }
                case 3: {
                    switch (t.n) {
                        case 0: {
                            ++t.s;
                            if (++t.t == 30) {
                                t.s = 0;
                                t.t = 0;
                                t.d();
                                return;
                            }
                            if (t.s > 10) {
                                t.s = 0;
                            }
                            if (t.s > 7) {
                                t.f = 0;
                                return;
                            }
                            t.f = 1;
                            return;
                        }
                        case 1: {
                            ++t.s;
                            if (t.t != 15) ** GOTO lbl182
                            if (t.s <= 40) ** GOTO lbl190
                            t.t = 0;
                            t.s = 0;
                            ** GOTO lbl-1000
lbl182:
                            // 1 sources

                            if (t.s > 30) {
                                t.s = 0;
                                t.t = 0;
                                ** if (q.e((int)0, (int)10) != 0) goto lbl-1000
lbl-1000:
                                // 1 sources

                                {
                                    t.p = 7;
                                    ** GOTO lbl190
                                }
                            }
                            ** GOTO lbl190
lbl-1000:
                            // 2 sources

                            {
                                t.p = 2;
                            }
lbl190:
                            // 4 sources

                            if (q.at % 7 == 0) {
                                t.f = t.f == 0 ? 1 : 0;
                            }
                            t.d();
                        }
                    }
                    return;
                }
                case 2: {
                    switch (t.n) {
                        case 0: {
                            if (++t.s > 5) {
                                t.s = 0;
                            }
                            if (t.s > 3) {
                                t.f = 0;
                                t.c += t.e << 2;
                            } else {
                                t.f = 2;
                            }
                            if (q.a(t.t - t.c) < 10) {
                                t.t = 0;
                                t.s = 0;
                                t.p = 3;
                                return;
                            }
                            if (t.c < t.q) {
                                t.c = t.q;
                                t.e = 1;
                            }
                            if (t.c > t.r) {
                                t.c = t.r;
                                t.e = -1;
                            }
                            if (q.at % 5 != 0) break block0;
                            t.c();
                            return;
                        }
                        case 1: {
                            if (q.at % 10 == 0) {
                                v4 = t.f = t.f == 0 ? 1 : 0;
                            }
                            if (q.at % 3 == 0) {
                                t.c += t.e << 1;
                            }
                            if (++t.s >= 20) {
                                t.s = 0;
                                if (q.e(0, 3) == 0) {
                                    t.p = 3;
                                }
                            }
                            if (t.c >= t.q && t.c <= t.r) {
                                t.c();
                                return;
                            }
                            if (t.c < t.q) {
                                t.c = t.q;
                                t.e = 1;
                                return;
                            }
                            if (t.c <= t.r) break block0;
                            t.c = t.r;
                            t.e = -1;
                        }
                    }
                    return;
                }
                case 7: {
                    switch (t.n) {
                        case 0: {
                            if (++t.s > 3) {
                                t.s = 0;
                            }
                            if (t.s > 1) {
                                t.f = 0;
                                t.c += t.e << 3;
                            } else {
                                t.f = 2;
                                t.c += t.e << 2;
                            }
                            if (q.a(t.t - t.c) < 10) {
                                t.t = 0;
                                t.s = 0;
                                t.e = t.c > g.at ? -1 : 1;
                                t.p = 12;
                                return;
                            }
                            if (t.c < t.q) {
                                t.c = t.q + 10;
                                t.e = 1;
                                t.s = 0;
                                t.t = 0;
                                t.p = 12;
                            }
                            if (t.c > t.r) {
                                t.c = t.r - 10;
                                t.e = -1;
                                t.s = 0;
                                t.t = 0;
                                t.p = 12;
                            }
                            if (q.at % 5 == 0) {
                                t.c();
                                return;
                            }
                            break block90;
                        }
                        case 1: {
                            if (q.at % 2 == 0) {
                                t.f = t.y[1][t.s];
                                ++t.t;
                                if (t.s < t.y[1].length - 1) {
                                    ++t.s;
                                }
                                if (t.t >= 20) {
                                    t.s = 0;
                                    t.t = 0;
                                    t.p = 3;
                                } else {
                                    ** GOTO lbl281
                                }
                            } else {
                                ** GOTO lbl281
                            }
                        }
                    }
                }
            }
        }
    }

    private static void c() {
        switch (n) {
            case 0: {
                if (d - g.au < 20 && q.a(g.at - (c + (e << 4))) < 40) {
                    p = 4;
                    t = 0;
                    s = 0;
                    return;
                }
                if (q.at % 10 != 1 || q.a(d - g.au) <= 20 || q.a(d - g.au) >= 40) break;
                p = 12;
                t = 0;
                s = 0;
                return;
            }
            case 1: {
                if (q.a(c - g.at - 5 * e) < 100 && q.a(d - g.au) < 30) {
                    if (q.e(0, 3) == 0) {
                        p = 7;
                        t = 0;
                    } else {
                        p = 4;
                        t = 10;
                        e = c > g.at ? -1 : 1;
                    }
                    s = 0;
                    return;
                }
                if (q.a(c - g.at - 5 * e) >= 200 || q.a(d - g.au) >= 30) break;
                p = 4;
                s = 0;
                t = 15;
                e = c > g.at ? -1 : 1;
            }
        }
    }

    private static void d() {
        switch (n) {
            case 0: {
                if (c - q < 20) {
                    e = 1;
                    s = 0;
                    t = q + q.at % (r - q - 40);
                    p = 2;
                    return;
                }
                if (r - c < 20) {
                    e = -1;
                    s = 0;
                    t = q + q.at % (r - q - 40);
                    p = 2;
                    return;
                }
                if (e == -1 && c > g.at || e == 1 && c < g.at) {
                    p = 12;
                    t = 0;
                    s = 0;
                    return;
                }
                if (d - g.au < 24 && q.a(c - g.at) > 24) {
                    t = g.at;
                    if (t < q + 5) {
                        t = q + 5;
                    }
                    if (t > r - 5) {
                        t = r - 5;
                    }
                    e = t > c ? 1 : -1;
                    s = 0;
                    p = 7;
                    return;
                }
                t = q + q.at % (r - q - 40);
                e = t > c ? 1 : -1;
                s = 0;
                p = 2;
                return;
            }
            case 1: {
                if (c < q) {
                    e = 1;
                    c = q + 10;
                    return;
                }
                if (c <= r) break;
                e = -1;
                c = r - 10;
            }
        }
    }

    public static void a(Graphics graphics) {
        int n2;
        block18: {
            block17: {
                if (!a) {
                    return;
                }
                if (e != 1) break block17;
                switch (n) {
                    case 0: {
                        graphics.drawImage(b[w[f][0]], c, d, 33);
                        if (w[f][1] != -1) {
                            graphics.drawImage(b[w[f][1]], c - w[f][5], d - w[f][6], 33);
                        }
                        if (w[f][2] != -1) {
                            graphics.drawImage(b[w[f][2]], c - w[f][3], d - w[f][4], 33);
                            break;
                        }
                        break block18;
                    }
                    case 1: {
                        n2 = 0;
                        while (n2 < 3) {
                            graphics.drawRegion(b[x[f][n2]], 0, 0, b[x[f][n2]].getWidth(), b[x[f][n2]].getHeight(), 2, -w[f][n2] + c, w[f][n2 + 3] + d, 33);
                            ++n2;
                        }
                        break block0;
                    }
                }
                break block18;
            }
            switch (n) {
                case 0: {
                    graphics.drawRegion(b[w[f][0]], 0, 0, b[w[f][0]].getWidth(), b[w[f][0]].getHeight(), 2, c, d, 33);
                    if (w[f][1] != -1) {
                        graphics.drawRegion(b[w[f][1]], 0, 0, b[w[f][1]].getWidth(), b[w[f][1]].getHeight(), 2, c + w[f][5], d - w[f][6], 33);
                    }
                    if (w[f][2] == -1) break;
                    graphics.drawRegion(b[w[f][2]], 0, 0, b[w[f][2]].getWidth(), b[w[f][2]].getHeight(), 2, c + w[f][3], d - w[f][4], 33);
                    break;
                }
                case 1: {
                    n2 = 0;
                    while (n2 < 3) {
                        graphics.drawImage(b[x[f][n2]], w[f][n2] + c, w[f][n2 + 3] + d, 33);
                        ++n2;
                    }
                    break;
                }
            }
        }
        if (z != 0) {
            graphics.setColor(0x330011);
            graphics.drawRect(c - (u >> 1) - 1, d - h - 7, u + 2, 4);
            n2 = n == 0 ? i * u / j : k * u / l;
            graphics.setColor(0xEE0000);
            graphics.fillRect(c - (u >> 1), d - h - 6, n2, 3);
            graphics.setColor(0x440022);
            graphics.fillRect(c - (u >> 1) + n2, d - h - 6, u - n2 + 1, 3);
        }
        n2 = 0;
        while (n2 < o.size()) {
            Graphics graphics2 = graphics;
            k k2 = (k)o.elementAt(n2);
            if (b[7] != null) {
                graphics2.drawRegion(b[7], 0, k2.c * k2.e, k2.d, k2.e, 0, k2.a, k2.b, 0);
            }
            ++n2;
        }
    }

    public static boolean b(int n2) {
        if (p == 3 || p == 2 || p == 7) {
            switch (n) {
                case 0: {
                    if ((i -= n2) <= 0) {
                        i = 0;
                        t = -(e << 3);
                        z = 0;
                        p = 1;
                        q.a(c, d, 4, -6, 50);
                        q.a(c, d, -5, -7, 50);
                        q.a(c, d, -4, -8, 50);
                        q.a(c, d, 5, -9, 50);
                        q.a(c, d, 0, -10, 50);
                        s = -5;
                        v = -10;
                        return true;
                    }
                    s = 0;
                    t = 0;
                    p = 6;
                    z = 10;
                    break;
                }
                case 1: {
                    if ((k -= n2) <= 0) {
                        k = 0;
                        t = -(e << 3);
                        z = 0;
                        p = 1;
                        q.a(c, d, 4, -6, 50);
                        q.a(c, d, -5, -7, 50);
                        q.a(c, d, -4, -8, 50);
                        q.a(c, d, 5, -9, 50);
                        q.a(c, d, 0, -10, 50);
                        s = -5;
                        v = -9;
                        t = -14;
                        return true;
                    }
                    s = 0;
                    t = 0;
                    p = 6;
                    z = 10;
                }
            }
        }
        return false;
    }
}


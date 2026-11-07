/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class n {
    static int a;
    static int b;
    private static int q;
    private static int r;
    static int c;
    static int d;
    private static int s;
    private static int t;
    private static int u;
    private static int v;
    private static int w;
    private static int x;
    private static int y;
    private static int z;
    static int[] e;
    static int[] f;
    static int[] g;
    static int[] h;
    static int[] i;
    static int[] j;
    static int[] k;
    private static int[] A;
    private static int[][] B;
    private static int[] C;
    private static Image[] D;
    private static Image[] E;
    private static Image[] F;
    static boolean l;
    static boolean m;
    static boolean n;
    static boolean o;
    static boolean p;

    static {
        e = new int[10];
        f = new int[10];
        g = new int[10];
        h = new int[10];
        i = new int[10];
        j = new int[10];
        k = new int[10];
        A = new int[10];
        C = new int[10];
    }

    public static void a(int n2, int n3, int n4) {
        int n5;
        p = false;
        if (n2 == 1) {
            l = true;
            D = new Image[3];
            n5 = 0;
            while (n5 < 3) {
                n.D[n5] = q.c("/mob/chim" + n5 + ".png");
                ++n5;
            }
            a = n3;
            b = n4;
            w = 1;
        }
        if (n2 == 0) {
            m = true;
            E = new Image[4];
            n5 = 0;
            while (n5 < 4) {
                n.E[n5] = q.c("/mob/cu" + n5 + ".png");
                ++n5;
            }
            c = n3;
            d = n4;
            x = 1;
        }
        if (n2 >= 2) {
            n.k[n2 - 2] = 2;
            p = true;
            F = new Image[3];
            n5 = 0;
            while (n5 < 3) {
                n.F[n5] = q.c("/mob/qua" + n5 + ".png");
                ++n5;
            }
            n.e[n2 - 2] = n3;
            n.f[n2 - 2] = n4;
            if (q.ap == 8) {
                n.i[n2 - 2] = 1;
            } else {
                n.i[n2 - 2] = -1;
                n.g[n2 - 2] = -q.b(3, 6);
                n.A[n2 - 2] = q.b(0, 3);
            }
            int[][] nArrayArray = new int[3][];
            nArrayArray[0] = new int[2];
            int[] nArray = new int[2];
            nArray[1] = -1;
            nArrayArray[1] = nArray;
            nArrayArray[2] = new int[]{4, 10};
            B = nArrayArray;
            n.j[n2 - 2] = 1;
        }
    }

    public static void a() {
        D = null;
        E = null;
        F = null;
    }

    public static void b() {
        int n2;
        p = false;
        if (l) {
            if (D == null) {
                D = new Image[3];
                n2 = 0;
                while (n2 < 3) {
                    n.D[n2] = q.c("/mob/chim" + n2 + ".png");
                    ++n2;
                }
            }
            a = h.ag - 20 * h.as;
            b = h.ah - 40;
        }
        if (m) {
            if (E == null) {
                E = new Image[4];
                n2 = 0;
                while (n2 < 4) {
                    n.E[n2] = q.c("/mob/cu" + n2 + ".png");
                    ++n2;
                }
            }
            c = h.ag - 28 * h.as;
            d = h.ah - 50;
        }
        if (q.ap == 14 && d.g && !i.i) {
            F = new Image[3];
            n2 = 0;
            while (n2 < 3) {
                n.F[n2] = q.c("/mob/qua" + n2 + ".png");
                ++n2;
            }
            int[][] nArrayArray = new int[3][];
            nArrayArray[0] = new int[2];
            int[] nArray = new int[2];
            nArray[1] = -1;
            nArrayArray[1] = nArray;
            nArrayArray[2] = new int[]{4, 10};
            B = nArrayArray;
            n2 = 0;
            while (n2 < 10) {
                n.k[n2] = 2;
                p = true;
                n.e[n2] = q.b(48, 648);
                n.f[n2] = q.b(240, 360);
                n.i[n2] = -1;
                n.g[n2] = -q.b(3, 6);
                n.A[n2] = q.b(0, 3);
                n.j[n2] = 1;
                n2 = (byte)(n2 + 1);
            }
        }
    }

    public static void c() {
        int n2;
        if (q.ap == 16) {
            return;
        }
        if (l) {
            if (n) {
                if (q.af == 0 && q.Y == 0) {
                    n = false;
                } else {
                    r = 0;
                    q = 0;
                    n2 = 0;
                    while (n2 < q.Y) {
                        if (q.ab[n2] != 0) {
                            if (q.a(a - q.Z[n2]) < 4) {
                                q = 0;
                            } else {
                                if (a < q.Z[n2]) {
                                    w = 1;
                                    q = 6;
                                }
                                if (a > q.Z[n2]) {
                                    w = -1;
                                    q = -6;
                                }
                            }
                            if (b < q.aa[n2]) {
                                r = 5;
                            }
                            if (b <= q.aa[n2]) break;
                            r = -5;
                            break;
                        }
                        ++n2;
                    }
                    if (q == 0 && r == 0) {
                        n2 = 0;
                        while (n2 < q.af) {
                            if (q.ai[n2] != 0) {
                                if (q.a(a - q.ag[n2]) < 4) {
                                    q = 0;
                                } else {
                                    if (a < q.ag[n2]) {
                                        w = 1;
                                        q = 6;
                                    }
                                    if (a > q.ag[n2]) {
                                        w = -1;
                                        q = -6;
                                    }
                                }
                                if (b < q.ah[n2]) {
                                    r = 5;
                                }
                                if (b <= q.ah[n2]) break;
                                r = -5;
                                break;
                            }
                            ++n2;
                        }
                    }
                    if (q == 0 && r == 0) {
                        n = false;
                        y = 0;
                    }
                    a += q;
                    b += r;
                    if (q.aq % 2 == 0) {
                        u = u == 0 ? 1 : 0;
                    }
                }
            } else {
                if (q.a(a - h.ag) < 45 && q.a(h.ah - b) < 50) {
                    if (k.a(a, b + 6, 2)) {
                        b = k.d(b + 6);
                        y = 1;
                    } else {
                        y = 0;
                    }
                } else {
                    if (a < h.ag - 45) {
                        w = 1;
                        q = 5;
                    }
                    if (a > h.ag + 45) {
                        w = -1;
                        q = -5;
                    }
                    if (b < h.ah) {
                        r = 3;
                    }
                    if (b > h.ah) {
                        r = -3;
                    }
                    y = 0;
                }
                switch (y) {
                    case 0: {
                        a += q;
                        b += r;
                        if (q.aq % 3 != 0) break;
                        u = u == 0 ? 1 : 0;
                        break;
                    }
                    case 1: {
                        u = 2;
                    }
                }
            }
        }
        if (m) {
            if (o) {
                if (c - q.ax < q.U + 40) {
                    c += 3;
                    x = 1;
                } else {
                    x = -1;
                }
                if (d > q.bg + 13 + q.bE.length * 15 + m.d[q.m[q.bF]] * 17) {
                    d -= 3;
                }
                if (d < q.bg + 13 + q.bE.length * 15 + m.d[q.m[q.bF]] * 17) {
                    d += 3;
                }
                if (q.aq % 4 == 0) {
                    v = v == 0 ? 1 : 0;
                }
            } else {
                if (q.a(c - h.ag) < 60 && q.a(h.ah - d) < 60) {
                    if (k.a(c, d + 6, 2)) {
                        d = k.d(d + 6);
                        z = 1;
                    } else {
                        z = 0;
                    }
                } else {
                    if (c < h.ag - 40) {
                        x = 1;
                        s = 4;
                    }
                    if (c > h.ag + 40) {
                        x = -1;
                        s = -4;
                    }
                    if (d < h.ah - 24) {
                        t = 3;
                    }
                    if (d > h.ah - 20) {
                        t = -3;
                    }
                    z = 0;
                }
                switch (z) {
                    case 0: {
                        c += s;
                        d += t;
                        if (q.aq % 4 != 0) break;
                        v = v == 0 ? 1 : 0;
                        break;
                    }
                    case 1: {
                        v = q.aq % 30 > 5 ? 2 : 3;
                    }
                }
            }
        }
        if (p && p) {
            n2 = 0;
            while (n2 < 10) {
                if (i.i) {
                    o.b.addElement(new j(e[n2], f[n2], 25));
                    n.j[n2] = 0;
                }
                if (j[n2] != 0) {
                    switch (k[n2]) {
                        case 1: {
                            q.n = 4;
                            o.a.addElement(new j(e[n2], f[n2], 25));
                            int n3 = 0;
                            while (n3 < q.b(7, 10)) {
                                o.a.addElement(new j(q.b(e[n2] - 40, e[n2] + 40), q.b(f[n2] - 40, f[n2] + 10), 27));
                                n3 = (byte)(n3 + 1);
                            }
                            n.k[n2] = 2;
                            n.e[n2] = q.b(q.P * 24, q.P * 24 + 50);
                            n.f[n2] = q.b(240, 336);
                            n.h[n2] = 0;
                            n.i[n2] = -1;
                            n.g[n2] = q.b(2, 5) * i[n2];
                            break;
                        }
                        case 3: {
                            break;
                        }
                        case 2: {
                            int n4;
                            if (q.aq % 3 == 0) {
                                if (A[n2] == 0) {
                                    n.A[n2] = 1;
                                } else if (A[n2] == 1) {
                                    n.A[n2] = 2;
                                } else if (A[n2] == 2) {
                                    n.A[n2] = 0;
                                }
                            }
                            int n5 = n2;
                            e[n5] = e[n5] + g[n2];
                            int n6 = n2;
                            f[n6] = f[n6] + h[n2];
                            if (e[n2] >= q.P * 24 || e[n2] <= q.N * 24) {
                                if (q.ap == 8) {
                                    p = false;
                                }
                                if (e[n2] >= q.P * 24) {
                                    n.g[n2] = q.b(-4, -2);
                                    n.i[n2] = -1;
                                }
                                if (e[n2] <= q.N * 24) {
                                    n.g[n2] = q.b(2, 5);
                                    n.i[n2] = 1;
                                }
                            }
                            int n3 = n2;
                            if (q.ap == 8 || i.b || q.aq % ((n3 + 2) * 50) != 0) break;
                            n.k[n3] = 4;
                            n.i[n3] = h.ag > e[n3] ? 1 : -1;
                            int n7 = q.a(e[n3] - h.ag) / 10;
                            if (n7 < 0) {
                                n7 = 4;
                            }
                            if ((n4 = q.a(f[n3] - h.ah) / 10) < 0) {
                                n4 = 4;
                            }
                            n.g[n3] = i[n3] * n7;
                            n.h[n3] = n4;
                            n.C[n3] = 0;
                            break;
                        }
                        case 4: {
                            int n8 = n2;
                            C[n8] = C[n8] + 1;
                            if (q.aq % 2 == 0) {
                                if (A[n2] == 0) {
                                    n.A[n2] = 1;
                                } else if (A[n2] == 1) {
                                    n.A[n2] = 2;
                                } else if (A[n2] == 2) {
                                    n.A[n2] = 0;
                                }
                            }
                            int n9 = n2;
                            e[n9] = e[n9] + g[n2];
                            int n10 = n2;
                            f[n10] = f[n10] + h[n2];
                            if (C[n2] < 12 && q.a(h.ag - e[n2]) < 15 && q.a(h.ah - f[n2]) < 15) {
                                h.a(i.M / 5, i[n2]);
                                n.C[n2] = 12;
                                n.h[n2] = q.b(-7, -4);
                                n.g[n2] = q.b(2, 5) * i[n2];
                            }
                            if (f[n2] >= h.ah) {
                                n.C[n2] = 12;
                                n.h[n2] = q.b(-7, -4);
                                n.g[n2] = q.b(2, 5) * i[n2];
                            }
                            if (C[n2] >= 12) {
                                int n11 = n2;
                                e[n11] = e[n11] + g[n2];
                                int n12 = n2;
                                f[n12] = f[n12] + h[n2];
                            }
                            if (C[n2] < 18) break;
                            n.C[n2] = 0;
                            n.k[n2] = 2;
                            n.h[n2] = 0;
                        }
                    }
                }
                n2 = (byte)(n2 + 1);
            }
            if (i.i) {
                p = false;
            }
        }
    }

    public static void a(Graphics graphics) {
        if (q.ap == 16) {
            return;
        }
        if (l && D != null) {
            graphics.drawRegion(D[u], 0, 0, D[u].getWidth(), D[u].getHeight(), w == 1 ? 0 : 2, a, b, 33);
        }
        if (m && E != null) {
            graphics.drawRegion(E[v], 0, 0, E[v].getWidth(), E[v].getHeight(), x == 1 ? 0 : 2, c - (o ? q.ax : 0), d - (o ? q.ay : 0), 33);
        }
        if (!p || F == null) {
            return;
        }
        int n2 = 0;
        while (n2 < 10) {
            if (j[n2] != 0) {
                graphics.drawRegion(F[A[n2]], 0, 0, F[A[n2]].getWidth(), F[A[n2]].getHeight(), i[n2] == 1 ? 2 : 0, e[n2] + B[A[n2]][0] * i[n2], f[n2] + B[A[n2]][1], 33);
            }
            n2 = (byte)(n2 + 1);
        }
    }
}


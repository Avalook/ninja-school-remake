/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class d {
    static int a;
    static int b;
    private static int[][] s;
    private static int t;
    private static int[][] u;
    static int c;
    static int[] d;
    private static int[] v;
    private static int[] w;
    private static int[] x;
    private static int[] y;
    private static int[] z;
    static int e;
    static int f;
    private static Image[] A;
    static boolean g;
    static boolean h;
    static int i;
    static int j;
    static int k;
    static int[] l;
    private static int[] B;
    private static int[] C;
    private static int[] D;
    static int[] m;
    private static int[] E;
    static int[] n;
    static int[] o;
    private static int[] F;
    private static int[] G;
    static int[] p;
    private static int[] H;
    private static int[] I;
    private static int[] J;
    private static int[] K;
    private static int[] L;
    private static int[] M;
    private static int[][] N;
    private static int[][] O;
    private static int[] P;
    private static int[] Q;
    private static int[] R;
    private static int[] S;
    static int[] q;
    static int[] r;
    private static int[] T;
    private static int U;

    static {
        v = new int[4];
        w = new int[4];
        e = 32;
        f = 21;
        i = 7;
        j = 6000;
        int[][] nArrayArray = new int[3][];
        nArrayArray[0] = new int[]{4, 5};
        int[] nArray = new int[3];
        nArray[1] = 2;
        nArray[2] = 3;
        nArrayArray[1] = nArray;
        nArrayArray[2] = new int[]{1, 2, 3};
        new Vector();
    }

    public static void a(int n2) {
        h = false;
        if (n2 != 14 || g) {
            return;
        }
        t = 0;
        h = true;
        A = new Image[12];
        n2 = 0;
        while (n2 < 12) {
            d.A[n2] = q.c("/mob/rong" + n2 + ".png");
            ++n2;
        }
        u = new int[][]{{9, 10, 7, 8}};
        int[][] nArrayArray = new int[1][];
        int[] nArray = new int[8];
        nArray[1] = 55;
        nArray[2] = -35;
        nArray[3] = 39;
        nArray[7] = 8;
        nArrayArray[0] = nArray;
        s = nArrayArray;
        a = 264;
        b = 408;
        c = 350;
        d = new int[i];
        B = new int[i];
        q = new int[i];
        C = new int[i];
        D = new int[i];
        m = new int[i];
        n = new int[i];
        o = new int[i];
        F = new int[i];
        G = new int[i];
        E = new int[i];
        l = new int[i];
        P = new int[i];
        M = new int[i];
        L = new int[i];
        p = new int[i];
        H = new int[i];
        J = new int[i];
        K = new int[i];
        I = new int[i];
        N = new int[i][];
        O = new int[i][];
        T = new int[i];
        S = new int[i];
        x = new int[i];
        y = new int[i];
        z = new int[i];
        r = new int[i];
        U = 0;
        n2 = 0;
        while (n2 < i) {
            d.d[n2] = 6000;
            d.l[n2] = q.b(0, 6);
            d.L[n2] = l[n2] <= 3 ? 0 : 1;
            d.E[n2] = 10 + (n2 == i - 1 ? i - 2 : n2) * 12;
            d.z[n2] = q.b(0, 2);
            if (n2 % 2 == 0) {
                d.n[n2] = q.b(a - 40, a - 5);
                d.F[n2] = q.b(a - 40, a - 5);
                d.m[n2] = -1;
                d.T[n2] = -1;
            } else {
                d.n[n2] = q.b(a + 5, a + 40);
                d.F[n2] = q.b(a + 5, a + 40);
                d.m[n2] = 1;
                d.T[n2] = 1;
            }
            d.o[n2] = b - 26 - E[n2];
            d.G[n2] = b - 26 - E[n2];
            d.C[n2] = q.b(1, 3);
            d.D[n2] = q.b(0, 3);
            d.p[n2] = 3;
            d.H[n2] = 0;
            d.L[n2] = q.b(0, 3);
            d.K[n2] = n[n2] + 10;
            d.J[n2] = n[n2] - 10;
            d.I[n2] = d.D[n2] = q.b(-3, -1);
            d.M[n2] = q.b(4, 8);
            d.x[n2] = q.b(10, 130);
            d.K[n2] = x[n2] + q.b(10, 25);
            d.J[n2] = x[n2] - q.b(10, 25);
            int n3 = q.b(a - 3, a + 4);
            int n4 = n2;
            d.P[n4] = n3;
            d.B[n4] = (b - o[n4]) / 5;
            d.N[n4] = new int[B[n4]];
            d.O[n4] = new int[B[n4]];
            d.N[n4][0] = n[n4];
            d.O[n4][0] = o[n4];
            d.N[n4][d.B[n4] - 1] = n3;
            d.O[n4][d.B[n4] - 1] = b - 15;
            n3 = q.a(O[n4][0] - O[n4][B[n4] - 1]) / B[n4];
            d.y[n4] = n3 >= 10 ? 10 : n3 + 1;
            ++n2;
        }
    }

    /*
     * Unable to fully structure code
     */
    public static void a() {
        if (!d.h) {
            return;
        }
        if (d.U == d.i) {
            var0 = 0;
            while (var0 < q.b(3, 6)) {
                o.b.addElement(new j(q.b(d.a - 50, d.a + 50), q.b(d.b - 50, d.b + 10), 0));
                var0 = (byte)(var0 + 1);
            }
            var0 = 0;
            while (var0 < 4) {
                d.v[var0] = q.b(-5, 6);
                d.w[var0] = q.b(-10, 0);
                ++var0;
            }
            d.U = 100;
        }
        if (d.U >= 100) {
            ++d.U;
            var0 = 0;
            while (var0 < 4) {
                v0 = var0++;
                d.w[v0] = d.w[v0] + 1;
            }
            if (d.U == 120) {
                d.g = true;
                d.h = false;
                i.b = false;
                i.j = false;
                q.f(32);
            }
        }
        var0 = 0;
        while (var0 < d.i) {
            block54: {
                if (d.r[var0] == -1) break block54;
                switch (d.p[var0]) {
                    case 3: {
                        v1 = var1_1 = var0;
                        d.H[v1] = d.H[v1] + 1;
                        if (d.H[var1_1] < 20) break;
                        d.H[var1_1] = 0;
                        d.p[var1_1] = 2;
                        break;
                    }
                    case 2: {
                        var1_1 = var0;
                        if (d.H[var1_1] < 0) {
                            v2 = var1_1;
                            d.H[v2] = d.H[v2] + 1;
                        }
                        if (d.z[var1_1] == 1) {
                            v3 = var1_1;
                            d.x[v3] = d.x[v3] + q.b(0, 5);
                        }
                        if (d.x[var1_1] >= d.K[var1_1]) {
                            d.z[var1_1] = 0;
                            d.m[var1_1] = -1;
                        }
                        if (d.z[var1_1] == 0) {
                            v4 = var1_1;
                            d.x[v4] = d.x[v4] - q.b(0, 5);
                        }
                        if (d.x[var1_1] <= d.J[var1_1]) {
                            d.z[var1_1] = 1;
                            d.m[var1_1] = 1;
                        }
                        if (d.y[var1_1] < d.I[var1_1]) {
                            v5 = var1_1;
                            d.y[v5] = d.y[v5] + 1;
                        }
                        d.n[var1_1] = d.F[var1_1] + l.b(d.x[var1_1], d.y[var1_1]);
                        d.o[var1_1] = d.G[var1_1] + l.c(d.x[var1_1], d.y[var1_1]);
                        if (q.aq % q.b(5, 100) == 0) {
                            if (d.L[var1_1] == 0) {
                                v6 = d.l;
                                v7 = var1_1;
                                v8 = q.b(0, 3);
                            } else {
                                v6 = d.l;
                                v7 = var1_1;
                                v8 = v6[v7] = d.l[var1_1] == 4 ? 5 : 4;
                            }
                        }
                        if (d.H[var1_1] != 0 || d.p[var2_2 = var1_1] == 4 || i.b) break;
                        var3_3 = var2_2;
                        var4_4 = 0;
                        var5_5 = 0;
                        while (var5_5 < d.i) {
                            if (var5_5 == var3_3 || d.p[var5_5] != 4 || ++var4_4 != 2) ** GOTO lbl79
                            v9 = false;
                            ** GOTO lbl82
lbl79:
                            // 1 sources

                            ++var5_5;
                        }
                        v9 = true;
lbl82:
                        // 2 sources

                        if (v9 && q.a(d.n[var2_2] - (h.ag + 5)) <= 40 && q.a(h.ah - d.o[var2_2]) < 80) {
                            d.I[var2_2] = q.a(d.O[var2_2][0] - d.O[var2_2][d.B[var2_2] - 1]) / d.B[var2_2];
                            d.M[var2_2] = l.a(h.ag + 5 - d.n[var2_2], h.ah - d.o[var2_2]);
                            d.S[var2_2] = 0;
                            d.p[var2_2] = 4;
                            d.m[var2_2] = d.n[var2_2] >= h.ag ? -1 : 1;
                            break;
                        }
                        if (q.a(d.n[var2_2] - h.ag) > 130 || q.a(h.ah - d.o[var2_2]) >= 100) break;
                        d.S[var2_2] = 1;
                        d.p[var2_2] = 4;
                        d.m[var2_2] = d.n[var2_2] >= h.ag ? -1 : 1;
                        break;
                    }
                    case 6: {
                        var1_1 = var0;
                        d.l[var1_1] = 6;
                        v10 = var1_1;
                        d.H[v10] = d.H[v10] + 1;
                        v11 = var1_1;
                        d.n[v11] = d.n[v11] - d.H[var1_1] * d.m[var1_1];
                        v12 = var1_1;
                        d.o[v12] = d.o[v12] - d.H[var1_1];
                        if (d.H[var1_1] != 10) break;
                        d.p[var1_1] = 2;
                        d.H[var1_1] = 0;
                        if (d.L[var1_1] == 0) {
                            d.l[var1_1] = q.b(0, 4);
                            break;
                        }
                        d.l[var1_1] = q.b(4, 6);
                        break;
                    }
                    case 4: {
                        var1_1 = var0;
                        if (h.ak == 6) {
                            d.l[var1_1] = 0;
                            d.H[var1_1] = -20;
                            d.p[var1_1] = 2;
                            d.I[var1_1] = q.b(3, 7);
                            break;
                        }
                        d.m[var1_1] = d.n[var1_1] >= h.ag + 5 ? -1 : 1;
                        v13 = var1_1;
                        d.H[v13] = d.H[v13] + 1;
                        d.l[var1_1] = 3;
                        if (d.y[var1_1] < d.I[var1_1]) {
                            v14 = var1_1;
                            d.y[v14] = d.y[v14] + 1;
                        }
                        if (d.y[var1_1] > d.I[var1_1]) {
                            v15 = var1_1;
                            d.y[v15] = d.y[v15] - 1;
                        }
                        if (d.x[var1_1] < d.M[var1_1]) {
                            v16 = var1_1;
                            d.x[v16] = d.x[v16] + 1;
                        }
                        if (d.x[var1_1] > d.M[var1_1]) {
                            v17 = var1_1;
                            d.x[v17] = d.x[v17] - 1;
                        }
                        if (d.H[var1_1] >= 10) {
                            d.l[var1_1] = 0;
                            d.H[var1_1] = -10;
                            d.I[var1_1] = q.b(3, 7);
                            d.p[var1_1] = 2;
                            d.y[var1_1] = q.b(3, 6);
                            if (d.L[var1_1] == 0) {
                                v18 = d.l;
                                v19 = var1_1;
                                v20 = q.b(0, 3);
                            } else {
                                v18 = d.l;
                                v19 = var1_1;
                                v20 = d.l[var1_1] == 4 ? 5 : 4;
                            }
                            v18[v19] = v20;
                            break;
                        }
                        if (d.S[var1_1] == 0 && q.a(d.n[var1_1] - (h.ag + 5)) < 15 && q.a(d.o[var1_1] - h.ah) < 20) {
                            h.a(d.c, d.m[var1_1]);
                            d.l[var1_1] = 0;
                            d.H[var1_1] = -20;
                            d.p[var1_1] = 2;
                            d.I[var1_1] = q.b(3, 7);
                            if (d.L[var1_1] == 0) {
                                v21 = d.l;
                                v22 = var1_1;
                                v23 = q.b(0, 3);
                            } else {
                                v21 = d.l;
                                v22 = var1_1;
                                v23 = v21[v22] = d.l[var1_1] == 4 ? 5 : 4;
                            }
                        }
                        if (d.S[var1_1] != 1 || d.H[var1_1] < 5) break;
                        d.l[var1_1] = 0;
                        d.H[var1_1] = -10;
                        d.p[var1_1] = 2;
                        q.c(var1_1, d.n[var1_1], d.o[var1_1], q.a(d.n[var1_1] - h.ag) / 10 * (d.n[var1_1] >= h.ag ? -1 : 1), q.a(d.o[var1_1] - h.ah) / 10 * (d.o[var1_1] >= h.ah ? -1 : 1));
                        break;
                    }
                    case 1: {
                        var1_1 = var0;
                        if (d.r[var1_1] == 1) break;
                        ++d.U;
                        d.r[var1_1] = 1;
                        d.Q = new int[d.B[var1_1]];
                        d.R = new int[d.B[var1_1]];
                        var2_2 = 0;
                        while (var2_2 < d.Q.length) {
                            d.Q[var2_2] = q.b(-5, 6);
                            d.R[var2_2] = q.b(-15, 5);
                            ++var2_2;
                        }
                        h.d(100);
                    }
                }
                var3_3 = d.y[var0];
                var2_2 = d.x[var0];
                var1_1 = var0;
                if (d.r[var1_1] == 1) {
                    var4_4 = 0;
                    var5_5 = 0;
                    while (var5_5 < d.B[var1_1]) {
                        v24 = d.N[var1_1];
                        v25 = var5_5;
                        v24[v25] = v24[v25] + d.Q[var5_5];
                        v26 = d.O[var1_1];
                        v27 = var5_5;
                        v26[v27] = v26[v27] + d.R[var5_5];
                        v28 = var5_5;
                        d.R[v28] = d.R[v28] + 1;
                        if (d.N[var1_1][var5_5] < q.N * 24 || d.N[var1_1][var5_5] > q.P * 24 || d.O[var1_1][var5_5] > q.Q * 24 || d.O[var1_1][var5_5] < q.N * 24) {
                            var4_4 = (byte)(var4_4 + 1);
                        }
                        ++var5_5;
                    }
                    d.n[var1_1] = d.N[var1_1][0];
                    d.o[var1_1] = d.O[var1_1][0];
                    if (var4_4 == d.B[var1_1]) {
                        d.r[var1_1] = -1;
                        i.b = false;
                        i.j = false;
                        i.B = 0;
                    }
                } else {
                    d.N[var1_1][0] = d.n[var1_1];
                    d.O[var1_1][0] = d.o[var1_1];
                    var4_4 = 1;
                    while (var4_4 < d.B[var1_1] - 1) {
                        d.N[var1_1][var4_4] = d.N[var1_1][var4_4 - 1] + l.b(var2_2 + var4_4 * 5, var3_3) * d.T[var1_1];
                        d.O[var1_1][var4_4] = d.O[var1_1][var4_4 - 1] + l.c(var2_2 + var4_4 * 5, var3_3);
                        ++var4_4;
                    }
                    var4_4 = d.B[var1_1] - 2;
                    while (var4_4 >= 0) {
                        d.N[var1_1][var4_4] = d.N[var1_1][var4_4 + 1] + l.b(var2_2 + var4_4 * 5, var3_3) * d.T[var1_1];
                        d.O[var1_1][var4_4] = d.O[var1_1][var4_4 + 1] - l.c(var2_2 + var4_4 * 5, var3_3);
                        --var4_4;
                    }
                    d.n[var1_1] = d.N[var1_1][0];
                    d.o[var1_1] = d.O[var1_1][0];
                }
            }
            ++var0;
        }
        if (q.aq % q.b(20, 30) == 0) {
            d.v[1] = d.v[1] == 0 ? d.v[1] - 2 : d.v[1] + 2;
        }
        if (q.aq % q.b(5, 25) == 0) {
            if (d.w[3] == 0) {
                d.w[3] = d.w[3] - 2;
                d.w[2] = d.w[2] + 2;
            } else {
                d.w[3] = d.w[3] + 2;
                d.w[2] = d.w[2] - 2;
            }
        }
        if (q.aq % q.b(9, 15) == 0) {
            d.v[0] = d.v[0] == 0 ? d.v[0] + 2 : d.v[0] - 2;
        }
        if (!i.b && q.aq % q.b(50, 200) == 0 && d.w[2] == 0) {
            d.w[2] = d.w[2] + 15;
            q.n = 0;
            q.D();
        }
        if (d.w[2] > 0) {
            d.w[2] = d.w[2] - 1;
        }
        if (d.w[2] < 0) {
            d.w[2] = d.w[2] + 1;
        }
    }

    public static void a(Graphics graphics) {
        if (!h) {
            return;
        }
        Graphics graphics2 = graphics;
        int n2 = 0;
        while (n2 < i) {
            if (r[n2] != -1) {
                int n3 = 1;
                while (n3 < B[n2]) {
                    graphics2.drawImage(A[11], N[n2][n3], O[n2][n3], 33);
                    ++n3;
                }
            }
            ++n2;
        }
        int n4 = 0;
        while (n4 < i) {
            if (r[n4] != -1) {
                if (m[n4] == -1) {
                    graphics.drawImage(A[l[n4]], n[n4], o[n4], 33);
                }
                if (m[n4] == 1) {
                    graphics.drawRegion(A[l[n4]], 0, 0, A[l[n4]].getWidth(), A[l[n4]].getHeight(), 2, n[n4], o[n4], 33);
                }
                if (q[n4] != 0 && !i.j) {
                    graphics.setColor(13073);
                    graphics.drawRect(n[n4] - 13 - 1, o[n4] - 20 - 7, 28, 4);
                    n2 = d[n4] * 26 / j;
                    graphics.setColor(60928);
                    graphics.fillRect(n[n4] - 13, o[n4] - 20 - 6, n2, 3);
                    graphics.setColor(17442);
                    graphics.fillRect(n[n4] - 13 + n2, o[n4] - 20 - 6, 26 - n2 + 1, 3);
                }
            }
            ++n4;
        }
        n4 = 0;
        while (n4 < 4) {
            graphics.drawImage(A[u[0][n4]], a + s[0][n4] + v[n4], b + s[0][n4 + 4] + w[n4], 33);
            ++n4;
        }
    }

    public static void b(Graphics graphics) {
        if (g) {
            return;
        }
        if (i.j && i.B <= i.C && i.B % 5 < 4) {
            Midlet.c(graphics, "" + i.l, a, b - 83, 2);
        }
    }

    public static boolean a(int n2, int n3) {
        if (p[n3] == 3 || p[n3] == 2 || p[n3] == 7) {
            if (d[n3] >= j / 5) {
                int n4 = n3;
                d[n4] = d[n4] - n2;
                d.q[n3] = 10;
            } else {
                d.q[n3] = 0;
            }
            d.H[n3] = 0;
            d.I[n3] = 0;
            d.p[n3] = 6;
            d.q[n3] = 10;
            if (!i.j && d[n3] <= j / 5) {
                i.C = 26;
                k = n3;
                i.j = true;
                i.l = q.b(1, 10);
                i.B = 0;
                q.b();
                q.c();
                o.a.addElement(new j(i.m, i.n - 67, 20));
            }
        }
        return false;
    }
}


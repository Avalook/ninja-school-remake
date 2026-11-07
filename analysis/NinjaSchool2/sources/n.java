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
    public static boolean a;
    private static int[] l;
    public static final int[] b;
    private static int[] m;
    private static int[] n;
    private static int[][] o;
    private static int[][] p;
    private static int[] q;
    private static Image[] r;
    private static Image[][] s;
    public static int c;
    private static int[] t;
    public static int[] d;
    private static int[] u;
    private static int[] v;
    public static int[] e;
    public static int[] f;
    public static int[] g;
    public static int[] h;
    private static int[] w;
    private static int[] x;
    private static int[] y;
    private static int[] z;
    private static int[] A;
    private static int[] B;
    public static int[] i;
    private static int[] C;
    private static int[] D;
    public static int[] j;
    private static int[] E;
    private static int[] F;
    private static int[] G;
    private static int[] H;
    public static int[] k;
    private static boolean[] I;
    private static boolean[] J;

    static {
        l = new int[]{900, 900, 3320, 2000, 3200, 3300, 3500};
        b = new int[]{60, 70, 90, 300, 400, 500, 600};
        m = new int[]{95, 90, 80, 135, 140, 145, 150};
        n = new int[]{150, 140, 120, 240, 280, 290, 300};
        o = new int[][]{{5, 8}, {3, 3}, new int[2], {4, 4}, {5, 8}, {3, 3}, new int[2]};
        int[][] nArrayArray = new int[7][];
        nArrayArray[0] = new int[]{-5, -2};
        nArrayArray[1] = new int[]{-2, -4};
        int[] nArray = new int[2];
        nArray[1] = -5;
        nArrayArray[2] = nArray;
        nArrayArray[3] = new int[]{-6, -6};
        nArrayArray[4] = new int[]{-5, -2};
        nArrayArray[5] = new int[]{-2, -4};
        int[] nArray2 = new int[2];
        nArray2[1] = -5;
        nArrayArray[6] = nArray2;
        p = nArrayArray;
        q = new int[]{1, -2, 1, 1, 1, -2, 1};
    }

    public static void a() {
        r = null;
        s = null;
        System.gc();
    }

    public static void a(int n2) {
        if (g.C) {
            return;
        }
        c = 0;
        a = false;
        if (n2 == 106) {
            c = 7;
            boolean[] blArray = new boolean[7];
            blArray[3] = true;
            blArray[4] = true;
            blArray[5] = true;
            blArray[6] = true;
            J = blArray;
            n.c();
            n.a(0, 42, 7, 3);
            n.a(1, 43, 7, 4);
            n.a(2, 45, 7, 3);
            n.a(3, 46, 7, 4);
            n.a(4, 44, 7, 6);
            n.a(5, 45, 7, 6);
            n.a(6, 42, 7, 6);
        }
        if (n2 == 20) {
            c = 6;
            boolean[] blArray = new boolean[7];
            blArray[3] = true;
            blArray[4] = true;
            blArray[5] = true;
            blArray[6] = true;
            J = blArray;
            n.c();
            n.a(0, 3, 11, 3);
            n.a(1, 13, 11, 4);
            n.a(2, 22, 14, 3);
            n.a(3, 29, 13, 4);
            n.a(4, 50, 10, 3);
            n.a(5, 52, 10, 4);
        }
        if (n2 == 13) {
            c = 10;
            boolean[] blArray = new boolean[7];
            blArray[3] = true;
            blArray[4] = true;
            blArray[5] = true;
            blArray[6] = true;
            J = blArray;
            n.c();
            n.a(0, 51, 11, 4);
            n.a(2, 44, 9, 4);
            n.a(3, 16, 11, 4);
            n.a(4, 53, 11, 4);
            n.a(5, 75, 6, 4);
            n.a(6, 85, 4, 4);
            n.a(7, 19, 11, 4);
            n.a(8, 79, 11, 4);
            n.a(9, 88, 11, 3);
            n.a(1, 69, 9, 4);
        }
    }

    private static void a(int n2, int n3, int n4, int n5) {
        n.u[n2] = n3 * 24 + 12;
        n.v[n2] = n4 * 24;
        n.e[n2] = u[n2];
        n.f[n2] = v[n2];
        n.g[n2] = e[n2];
        n.h[n2] = f[n2] - 9;
        n.B[n2] = n5;
        n.i[n2] = 3;
        n.C[n2] = n2 << 1;
        n.D[n2] = 0;
        n.I[n2] = false;
        n.j[n2] = -1;
        n.E[n2] = 0;
        n.F[n2] = 0;
        n.A[n2] = 0;
        n.G[n2] = n.a(u[n2], v[n2] + 10);
        n.H[n2] = n.b(u[n2], v[n2] + 10);
        n.d[n2] = l[B[n2]];
        n.k[n2] = -1;
    }

    private static int a(int n2, int n3) {
        block1: {
            n2 /= 24;
            n3 /= 24;
            int n4 = 0;
            do {
                ++n4;
                if (--n2 > 0) continue;
                n2 = 0;
                break block1;
            } while (n4 <= 8 && (j.a(n2, n3) & 2) == 2 && (j.a(n2, n3 - 1) & 8) != 8);
            ++n2;
        }
        return n2 * 24;
    }

    private static int b(int n2, int n3) {
        block1: {
            n2 /= 24;
            n3 /= 24;
            int n4 = 0;
            do {
                ++n4;
                if (++n2 < j.a - 1) continue;
                n2 = j.a - 1;
                break block1;
            } while (n4 <= 8 && (j.a(n2, n3) & 2) == 2 && (j.a(n2, n3 - 1) & 4) != 4);
            --n2;
        }
        return n2 * 24;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void b() {
        if (g.C) {
            return;
        }
        int n2 = 0;
        while (n2 < c) {
            block65: {
                if (k[n2] == 1 && k[n2] > 1 && g.as == 2) {
                    if (d[n2] > 1) {
                        int n3 = n2;
                        d[n3] = d[n3] - 3;
                    }
                    if (d[n2] < 1) {
                        n.d[n2] = 1;
                    }
                }
                if (k[n2] == 1 && g.as == 2) {
                    n.t[n2] = 100;
                }
                if (t[n2] > 0) {
                    int n4 = n2;
                    t[n4] = t[n4] - 1;
                }
                if (q.aA) {
                    n.i[n2] = 3;
                }
                block0 : switch (i[n2]) {
                    case 11: {
                        int n5 = n2;
                        f[n5] = f[n5] + x[n2];
                        int n6 = n2;
                        e[n6] = e[n6] + w[n2];
                        int n7 = n2;
                        C[n7] = C[n7] + 1;
                        n.E[n2] = 4;
                        if (x[n2] < 12) {
                            int n8 = n2;
                            x[n8] = x[n8] + 1;
                        }
                        if (!j.a(e[n2], f[n2], 2) || C[n2] <= 4) break;
                        n.f[n2] = j.e(f[n2]);
                        n.x[n2] = 0;
                        n.w[n2] = 0;
                        n.G[n2] = n.a(e[n2], f[n2] + 10);
                        n.H[n2] = n.b(e[n2], f[n2] + 10);
                        n.i[n2] = 3;
                        break;
                    }
                    case 3: {
                        int n9;
                        if (k[n2] == 1 && g.as == 3) {
                            n.k[n2] = -1;
                        }
                        if (!j.a(e[n2], f[n2], 2)) {
                            n.w[n2] = 10;
                            n.C[n2] = 0;
                            n.i[n2] = 12;
                        }
                        n.A[n2] = (q.at + n2) % 10 > 5 ? 1 : 0;
                        int n10 = n2;
                        C[n10] = C[n10] + 1;
                        n.E[n2] = 0;
                        n.F[n2] = 0;
                        if (a) break;
                        if (C[n2] > 50) {
                            n9 = (q.at - n2) % (n2 + 80) - 40;
                            n9 = n9 > 0 ? (n9 += 20) : (n9 -= 20);
                            n.D[n2] = e[n2] + n9;
                            int n11 = n.j[n2] = n9 > 0 ? 1 : -1;
                            if (q.at % 4 > 1) {
                                n.i[n2] = 2;
                                n.w[n2] = j[n2] << 1;
                            } else {
                                n.i[n2] = 7;
                                n.w[n2] = j[n2] << 2;
                            }
                            if (D[n2] < G[n2]) {
                                n.D[n2] = G[n2];
                            }
                            if (D[n2] > H[n2]) {
                                n.D[n2] = H[n2];
                            }
                        }
                        if (g.as == 3 && k[n2] == 1) {
                            return;
                        }
                        if (I[n2] && q.at % 7 == 0) {
                            n.b(n2);
                            break;
                        }
                        if (q.at % 10 != 0) break;
                        n.c(n2);
                        n.d(n2);
                        break;
                    }
                    case 2: {
                        if (!j.a(e[n2], f[n2], 2)) {
                            n.w[n2] = 10;
                            n.C[n2] = 0;
                            n.i[n2] = 12;
                        }
                        if (q.at % 12 > 5) {
                            n.A[n2] = 1;
                            n.E[n2] = 1;
                            if (q.at % 12 < 8) {
                                if (q.a(e[n2] - D[n2]) < 4) {
                                    n.b(n2);
                                }
                                int n12 = n2;
                                e[n12] = e[n12] + w[n2];
                            }
                            n.c(n2);
                            break;
                        }
                        n.E[n2] = 0;
                        n.A[n2] = 0;
                        break;
                    }
                    case 12: {
                        int n13 = n2;
                        x[n13] = x[n13] + 1;
                        int n14 = n2;
                        e[n14] = e[n14] + w[n2] * j[n2];
                        int n15 = n2;
                        f[n15] = f[n15] + x[n2];
                        if (w[n2] > 0) {
                            int n16 = n2;
                            w[n16] = w[n16] - 1;
                        }
                        n.E[n2] = 2;
                        if (f[n2] + 4 >= j.d) {
                            return;
                        }
                        if (x[n2] <= 4 || (j.b(e[n2], f[n2] + 3) & 2) != 2) break;
                        n.i[n2] = 2;
                        n.w[n2] = 1;
                        n.x[n2] = 0;
                        n.D[n2] = 0;
                        n.C[n2] = 0;
                        n.f[n2] = j.e(f[n2] + 3);
                        return;
                    }
                    case 7: {
                        if (q.a(e[n2] - D[n2]) < 12) {
                            n.b(n2);
                            break;
                        }
                        int n17 = n2;
                        e[n17] = e[n17] + w[n2];
                        if (q.at % 2 == 1) {
                            n.A[n2] = 1;
                            n.E[n2] = 3;
                        } else {
                            n.E[n2] = 2;
                            n.A[n2] = 0;
                        }
                        if (q.at % 4 != 0) break;
                        n.c(n2);
                        n.d(n2);
                        break;
                    }
                    case 4: {
                        int n9;
                        if (!j.a(e[n2], f[n2], 2)) {
                            n.w[n2] = 10;
                            n.C[n2] = 0;
                            n.i[n2] = 12;
                        }
                        int n18 = n2;
                        C[n18] = C[n18] + 1;
                        if (C[n2] < 3) {
                            n.A[n2] = 0;
                            n.E[n2] = 0;
                            n.F[n2] = 0;
                        } else if (C[n2] < 14) {
                            n.A[n2] = 1;
                            n.E[n2] = 1;
                            n.F[n2] = 1;
                        } else {
                            n.i[n2] = 3;
                            n.C[n2] = 0;
                        }
                        if (C[n2] != 3) break;
                        switch (B[n2]) {
                            case 2: 
                            case 3: 
                            case 6: {
                                n9 = n2;
                                if (q.a(g.au - f[n9]) >= 5) break block0;
                                if (q.as == 13) {
                                    g.a(g.aM / 2, j[n9]);
                                    break;
                                }
                                g.a(b[B[n9]], j[n9]);
                                break;
                            }
                            case 0: 
                            case 4: {
                                if (q.as == 13) {
                                    q.b(e[n2] + (j[n2] << 3), f[n2] - 15, j[n2] * 12, g.aM / 2, 0);
                                    break;
                                }
                                q.b(e[n2] + (j[n2] << 3), f[n2] - 15, j[n2] * 12, b[B[n2]], 0);
                                break;
                            }
                            case 1: 
                            case 5: {
                                q.b(e[n2] + (j[n2] << 3), f[n2] - 15, j[n2] << 3, b[B[n2]], 1);
                                break;
                            }
                        }
                        break;
                    }
                    case 6: {
                        if (g.as == 3 && k[n2] == 1) {
                            n.i[n2] = 3;
                            n.C[n2] = 0;
                        }
                        n.A[n2] = (q.at + n2) % 10 > 5 ? 1 : 0;
                        n.E[n2] = 0;
                        n.F[n2] = 0;
                        int n19 = n2;
                        C[n19] = C[n19] + 1;
                        if (C[n2] == 1) {
                            int n20 = n2;
                            e[n20] = e[n20] - (j[n2] << 3);
                            if (e[n2] < G[n2]) {
                                n.e[n2] = G[n2];
                                n.i[n2] = 4;
                            } else if (e[n2] > H[n2]) {
                                n.e[n2] = H[n2];
                                n.i[n2] = 4;
                            }
                        }
                        if (C[n2] < 5) break;
                        n.D[n2] = g.at;
                        if (e[n2] < g.at) {
                            int n21 = n2;
                            D[n21] = D[n21] - 80;
                        } else {
                            int n22 = n2;
                            D[n22] = D[n22] + 80;
                        }
                        n.C[n2] = 0;
                        n.j[n2] = D[n2] < g.at ? -1 : 1;
                        n.i[n2] = 7;
                        n.w[n2] = j[n2] << 3;
                        if (D[n2] < G[n2]) {
                            n.D[n2] = G[n2];
                        }
                        if (D[n2] <= H[n2]) break;
                        n.D[n2] = H[n2];
                        break;
                    }
                    case 5: {
                        int n23 = n2;
                        C[n23] = C[n23] + 1;
                        if (C[n2] <= 100 + n2 * 3) break;
                        n.e[n2] = u[n2];
                        n.f[n2] = v[n2];
                        n.C[n2] = 0;
                        n.i[n2] = 3;
                        n.d[n2] = l[B[n2]];
                        n.I[n2] = false;
                        break;
                    }
                    case 1: {
                        n.k[n2] = -1;
                        n.F[n2] = 1;
                        int n24 = n2;
                        C[n24] = C[n24] + 1;
                        int n25 = n2;
                        e[n25] = e[n25] + w[n2];
                        int n26 = n2;
                        f[n26] = f[n26] + x[n2];
                        int n27 = n2;
                        g[n27] = g[n27] + y[n2];
                        int n28 = n2;
                        h[n28] = h[n28] + z[n2];
                        if (C[n2] > 14) {
                            if (w[n2] > 1) {
                                int n29 = n2;
                                w[n29] = w[n29] - 1;
                            } else if (w[n2] < -1) {
                                int n30 = n2;
                                w[n30] = w[n30] + 1;
                            }
                            int n31 = n2;
                            x[n31] = x[n31] + 1;
                        }
                        if (y[n2] > 1) {
                            int n32 = n2;
                            y[n32] = y[n32] - 1;
                        } else if (y[n2] < -1) {
                            int n33 = n2;
                            y[n33] = y[n33] + 1;
                        }
                        int n34 = n2;
                        z[n34] = z[n34] + 1;
                        if (f[n2] <= q.az + q.ac + 32 && f[n2] <= j.d) break block65;
                        n.C[n2] = 0;
                        n.D[n2] = 0;
                        n.i[n2] = 5;
                    }
                }
                n.g[n2] = e[n2];
                n.h[n2] = f[n2] - 9 + A[n2] + o[B[n2]][F[n2]];
            }
            ++n2;
        }
    }

    private static void b(int n2) {
        if (q.at % 2 == n2 % 2) {
            return;
        }
        if (q.at % 3 == n2 % 3) {
            return;
        }
        if (!I[n2]) {
            n.D[n2] = 0;
            n.C[n2] = 0;
            n.i[n2] = 3;
            n.d(n2);
            return;
        }
        if (g.at > e[n2]) {
            if (g.at - e[n2] < 24) {
                if (g.au < f[n2]) {
                    n.j[n2] = 1;
                    n.i[n2] = 11;
                    n.C[n2] = 0;
                    n.w[n2] = 3;
                    int n3 = f[n2] - g.au;
                    if (n3 < 30) {
                        n.x[n2] = -6;
                        return;
                    }
                    if (n3 < 60) {
                        n.x[n2] = -10;
                        return;
                    }
                    n.x[n2] = -14;
                    return;
                }
                if ((q.at + (n2 << 1)) % 10 > 3) {
                    n.D[n2] = 0;
                    n.C[n2] = 0;
                    n.i[n2] = 3;
                    return;
                }
                n.D[n2] = H[n2];
                n.j[n2] = 1;
                n.i[n2] = 7;
                n.w[n2] = j[n2] << 2;
                return;
            }
            if (j.a(e[n2] + 40, f[n2] + 10, 2) && !j.a(e[n2] + 48, f[n2] - 10, 4)) {
                n.H[n2] = n.b(e[n2], f[n2] + 10);
                n.D[n2] = H[n2] < g.at ? H[n2] : g.at;
                n.j[n2] = 1;
                n.i[n2] = 7;
                n.w[n2] = j[n2] << 2;
                return;
            }
            if (g.au < f[n2]) {
                n.w[n2] = 2;
                n.x[n2] = -10;
            } else {
                n.w[n2] = 5;
                n.x[n2] = -4;
            }
            n.j[n2] = 1;
            n.i[n2] = 11;
            n.C[n2] = 0;
            return;
        }
        if (g.at - e[n2] > -24) {
            if (g.au < f[n2]) {
                n.j[n2] = -1;
                n.i[n2] = 11;
                n.C[n2] = 0;
                n.w[n2] = -3;
                int n4 = f[n2] - g.au;
                if (n4 < 30) {
                    n.x[n2] = -6;
                    return;
                }
                if (n4 < 60) {
                    n.x[n2] = -10;
                    return;
                }
                n.x[n2] = -14;
                return;
            }
            if ((q.at + (n2 << 1)) % 10 > 3) {
                n.D[n2] = 0;
                n.C[n2] = 0;
                n.i[n2] = 3;
                return;
            }
            n.D[n2] = G[n2];
            n.j[n2] = -1;
            n.i[n2] = 7;
            n.w[n2] = j[n2] << 2;
            return;
        }
        if (j.a(e[n2] - 40, f[n2] + 10, 2) && !j.a(e[n2] - 48, f[n2] - 10, 8)) {
            n.G[n2] = n.a(e[n2], f[n2] + 10);
            n.D[n2] = G[n2] > g.at ? G[n2] : g.at;
            n.j[n2] = -1;
            n.i[n2] = 7;
            n.w[n2] = j[n2] << 2;
            return;
        }
        if (g.au < f[n2]) {
            n.w[n2] = -2;
            n.x[n2] = -10;
        } else {
            n.w[n2] = -5;
            n.x[n2] = -4;
        }
        n.j[n2] = -1;
        n.i[n2] = 11;
        n.C[n2] = 0;
    }

    private static void c(int n2) {
        if (g.ba) {
            return;
        }
        if (q.a(e[n2] - g.at) < 100) {
            n.I[n2] = true;
            if (q.a(f[n2] - g.au) < 20) {
                switch (B[n2]) {
                    case 2: 
                    case 3: 
                    case 6: {
                        n.D[n2] = g.at;
                        if (e[n2] < g.at) {
                            int n3 = n2;
                            D[n3] = D[n3] - 10;
                        } else {
                            int n4 = n2;
                            D[n4] = D[n4] + 10;
                        }
                        n.j[n2] = D[n2] < g.at ? 1 : -1;
                        n.i[n2] = 7;
                        n.w[n2] = j[n2] << 3;
                        if (D[n2] < G[n2]) {
                            n.D[n2] = G[n2];
                        }
                        if (D[n2] <= H[n2]) break;
                        n.D[n2] = H[n2];
                        return;
                    }
                    case 0: 
                    case 1: 
                    case 4: 
                    case 5: {
                        n.j[n2] = e[n2] < g.at ? 1 : -1;
                        n.C[n2] = 0;
                        n.D[n2] = 0;
                        n.i[n2] = 4;
                    }
                }
            }
        }
    }

    private static void d(int n2) {
        if (q.a(g.au - f[n2]) <= 20 && q.a(e[n2] + (j[n2] << 3) - g.at) < 25 && (B[n2] == 6 || B[n2] == 2 || B[n2] == 3)) {
            n.C[n2] = 0;
            n.D[n2] = 0;
            n.i[n2] = 4;
        }
    }

    public static void a(Graphics graphics) {
        if (g.C) {
            return;
        }
        int n2 = 0;
        while (n2 < c) {
            if (i[n2] != 5 && e[n2] + 15 >= q.ay && e[n2] - 15 <= q.ay + q.ab && f[n2] >= q.az && f[n2] - 30 <= q.az + q.ac) {
                if (j[n2] == -1) {
                    graphics.drawImage(r[E[n2]], e[n2] + q[B[n2]], f[n2], 33);
                    graphics.drawImage(s[B[n2]][F[n2]], g[n2] + p[B[n2]][F[n2]], h[n2], 33);
                } else {
                    graphics.drawRegion(r[E[n2]], 0, 0, r[E[n2]].getWidth(), r[E[n2]].getHeight(), 2, e[n2] - q[B[n2]], f[n2], 33);
                    graphics.drawRegion(s[B[n2]][F[n2]], 0, 0, s[B[n2]][F[n2]].getWidth(), s[B[n2]][F[n2]].getHeight(), 2, g[n2] - p[B[n2]][F[n2]], h[n2], 33);
                }
                if (t[n2] != 0) {
                    graphics.setColor(13073);
                    graphics.drawRect(e[n2] - 13, f[n2] - 40, 26, 4);
                    int n3 = d[n2] * 24 / l[B[n2]];
                    graphics.setColor(0xEE0000);
                    graphics.fillRect(e[n2] - 12, f[n2] - 39, n3, 3);
                    graphics.setColor(0x440022);
                    graphics.fillRect(e[n2] - 12 + n3, f[n2] - 39, 24 - n3 + 1, 3);
                }
            }
            ++n2;
        }
    }

    private static void c() {
        int n2 = 0;
        if (r == null) {
            try {
                r = new Image[5];
                n2 = 0;
                while (n2 < 5) {
                    n.r[n2] = Image.createImage((String)("/mob/x" + n2 + ".png"));
                    ++n2;
                }
                s = new Image[7][2];
                n2 = 0;
                while (n2 < 7) {
                    if (J[n2]) {
                        int n3 = 0;
                        while (n3 < 2) {
                            n.s[n2][n3] = Image.createImage((String)("/mob/y" + n2 + n3 + ".png"));
                            ++n3;
                        }
                    }
                    ++n2;
                }
            }
            catch (Exception exception) {
                System.out.println("NJ ERR " + n2);
            }
        }
        u = new int[c];
        v = new int[c];
        e = new int[c];
        f = new int[c];
        g = new int[c];
        h = new int[c];
        B = new int[c];
        i = new int[c];
        C = new int[c];
        D = new int[c];
        j = new int[c];
        E = new int[c];
        F = new int[c];
        A = new int[c];
        G = new int[c];
        H = new int[c];
        w = new int[c];
        x = new int[c];
        y = new int[c];
        z = new int[c];
        d = new int[c];
        k = new int[c];
        I = new boolean[c];
        t = new int[c];
    }

    public static boolean a(int n2, int n3, int n4) {
        int n5 = n2;
        d[n5] = d[n5] - n3;
        if (i[n2] == 1 || i[n2] == 5) {
            return true;
        }
        if (d[n2] <= 0) {
            n.d[n2] = 0;
            n.j[n2] = n4;
            n.w[n2] = 0;
            n.x[n2] = 0;
            n.y[n2] = -(n4 << 3);
            n.z[n2] = -6;
            n.C[n2] = 0;
            n.t[n2] = 0;
            n.i[n2] = 1;
            g.c(n[B[n2]]);
            q.a(e[n2], f[n2], -(j[n2] << 2), -4, m[B[n2]] + q.at % 10);
            if (q.at % 20 > 5) {
                q.a(e[n2], f[n2], -(j[n2] << 3), -5, b[B[n2]], (q.at + 1) % 2);
            }
            return true;
        }
        n.C[n2] = 0;
        n.j[n2] = n4;
        n.t[n2] = 100;
        n.i[n2] = 6;
        return false;
    }
}


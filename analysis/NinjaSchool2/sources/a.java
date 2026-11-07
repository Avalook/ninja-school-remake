/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class a {
    private static String[] n = new String[]{"Nh\u00edm n\u00e2u", "Th\u1ecf tr\u1eafng", "C\u00f3c v\u00e0ng", "B\u1ecd c\u00e1nh c\u1ee9ng", "L\u1eadt \u0111\u1eadt", "\u1ed1c ma \u0111en", "B\u00f2 c\u1ea1p", "B\u1ecd x\u00edt", "Kh\u1ec9 l\u00f4ng n\u00e2u", "B\u00f9 nh\u00ecn", "C\u00e0o c\u00e0o", "Ninja gi\u00f3", "\u1ed0c b\u01b0u v\u00e0ng", "D\u01a1i", "Ma x\u01b0\u01a1ng", "R\u00f9a", "Cua", "\u1ed0c m\u01b0\u1ee3n h\u1ed3n", "Ma d\u00f9", "M\u1ef1c nh\u1ea3y", "C\u00e1 nh\u00e1m xanh", "R\u1eafn c\u1ea1p nong", "Heo r\u1eebng", "H\u1ed3n ma", "H\u1ed3n ma", "Qu\u1ef7 \u0111\u1ea7u l\u00e2u", "R\u00f9a gi\u00e0", "B\u1ecd n\u00fai", "\u1ed0c ti\u1ec1n s\u1eed"};
    private static int[] o = new int[]{100, 100, 200, 200, 400, 150, 60, 500, 250, 400, 600, 500, 600, 800, 900, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 5000, 2000, 2000, 2000, 2000, 2000};
    public static final int[] a;
    private static int[] p;
    private static int[] q;
    public static boolean b;
    private static Image[][][] r;
    public static int c;
    private static int s;
    public static int[] d;
    public static int[] e;
    public static int[] f;
    public static int[] g;
    public static int[] h;
    private static int[] t;
    public static int[] i;
    private static int[] u;
    private static int[] v;
    private static int[] w;
    private static int[] x;
    public static int[] j;
    private static int[] y;
    private static int[] z;
    private static int[] A;
    private static int[] B;
    public static int[] k;
    public static int[] l;
    private static int[] C;
    private static int[] D;
    private static int[] E;
    private static int[] F;
    private static int[] G;
    private static int[] H;
    private static int[] I;
    private static int[] J;
    public static int[] m;
    private static boolean[] K;
    private static int[][] L;
    private static int[][] M;
    private static boolean[][] N;
    private static int[] O;

    static {
        int[] nArray = new int[34];
        nArray[0] = 15;
        nArray[2] = 30;
        nArray[3] = 20;
        nArray[4] = 50;
        nArray[5] = 25;
        nArray[6] = 5;
        nArray[7] = 10;
        nArray[8] = 20;
        nArray[9] = 40;
        nArray[10] = 30;
        nArray[11] = 40;
        nArray[12] = 130;
        nArray[13] = 30;
        nArray[14] = 40;
        nArray[15] = 100;
        nArray[16] = 60;
        nArray[17] = 160;
        nArray[18] = 120;
        nArray[19] = 200;
        nArray[20] = 200;
        nArray[21] = 200;
        nArray[22] = 200;
        nArray[23] = 200;
        nArray[24] = 200;
        nArray[25] = 200;
        nArray[26] = 200;
        nArray[27] = 200;
        nArray[28] = 200;
        nArray[29] = 200;
        nArray[30] = 200;
        nArray[31] = 200;
        nArray[32] = 200;
        nArray[33] = 200;
        a = nArray;
        p = new int[]{35, 5, 40, 40, 45, 50, 30, 130, 60, 65, 70, 75, 120, 85, 100, 120, 140, 160, 120, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200};
        q = new int[]{20, 1, 30, 25, 40, 40, 15, 50, 60, 70, 80, 100, 120, 130, 160, 170, 210, 190, 220, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200};
        O = null;
    }

    public static void a() {
        r = null;
        System.gc();
    }

    private static void b(int n2) {
        if (E[n2] > 30) {
            int n3 = n2;
            int n4 = k[n3] > 0 ? e[n3] : e[n3] - F[n3];
            int n5 = k[n3] >= 0 ? e[n3] + F[n3] : e[n3];
            int n6 = f[n3] - G[n3];
            n3 = f[n3] + G[n3];
            if (g.ax == 6 ? false : (g.ba ? false : (g.at < n4 ? false : (g.at > n5 ? false : (g.au < n6 ? false : g.au <= n3))))) {
                a.E[n2] = 0;
                a.i[n2] = 4;
                a.j[n2] = 0;
            }
        }
    }

    private static void c(int n2) {
        if (E[n2] > 30) {
            int n3 = n2;
            int n4 = k[n3] > 0 ? e[n3] : e[n3] - F[n3];
            int n5 = k[n3] >= 0 ? e[n3] + F[n3] : e[n3];
            int n6 = f[n3] - G[n3];
            n3 = f[n3] + G[n3];
            if (g.R < n4 ? false : (g.R > n5 ? false : (g.S < n6 ? false : g.S <= n3))) {
                a.E[n2] = 0;
                a.i[n2] = 4;
                a.j[n2] = 0;
                a.K[n2] = true;
            }
        }
    }

    public static final boolean a(int n2, int n3, int n4, int n5) {
        if (i[n2] == 2 || i[n2] == 3 || i[n2] == 4 || i[n2] == 9) {
            int n6 = n2;
            l[n6] = l[n6] - n3;
            if (l[n2] <= 0) {
                a.l[n2] = 0;
                a.j[n2] = -5;
                a.y[n2] = -(n4 << 3);
                a.z[n2] = 0;
                a.t[n2] = 0;
                a.i[n2] = 1;
                if (q.as == 12 && g.bc == 4) {
                    if (n5 == 0) {
                        a.d(n2);
                    }
                    if (n5 == 1 && g.bc != -1 && g.bc < 9 && c.g[g.bc] == d[n2]) {
                        if (g.bh > 0) {
                            --g.bh;
                        }
                        q.a(String.valueOf(n[c.g[g.bc]]) + ": " + g.bh + "#" + c.h[g.bc]);
                    }
                } else {
                    a.d(n2);
                    if (n5 == 0 && q.as == 14 && g.w && g.D == 2 && !g.x && d[n2] == 17) {
                        g.D = 3;
                        q.a("Gi\u1ea3i \u0111\u01b0\u1ee3c c\u00e2u \u0111\u1ed1 th\u1ee9 3");
                    }
                }
                return true;
            }
            if (i[n2] == 9) {
                int n7 = n2;
                j[n7] = j[n7] - 7;
                a.k[n2] = n4;
            } else {
                a.j[n2] = 0;
                a.k[n2] = n4;
                a.i[n2] = 6;
                a.B[n2] = 3;
            }
        }
        return false;
    }

    private static void d(int n2) {
        g.c(q[d[n2]]);
        int n3 = q.at % 20;
        if (n3 > 15) {
            q.a(e[n2], f[n2], -(k[n2] << 3), -5, D[n2] << 2, (q.at + 1) % 2);
        } else if (n3 == 15) {
            q.a(e[n2], f[n2], -(k[n2] << 2), -4, (I[n2] << 3) + q.at % 10);
        } else {
            n3 = ((d[n2] << 2) / 10 + 1) * 10;
            q.a(e[n2], f[n2], -(k[n2] << 1), -6, n3);
            q.a(e[n2], f[n2], -(k[n2] << 4), -3, n3);
        }
        if (g.bc != -1 && g.bc < 9 && c.g[g.bc] == d[n2]) {
            q.a(String.valueOf(n[c.g[g.bc]]) + ": " + ++g.bh + "#" + c.h[g.bc]);
            if (q.as == 12 && g.bh == c.h[g.bc] && g.bc == 4) {
                g.bc = -1;
                q.g(47);
            }
        }
        if (d[n2] == g.bd - 100 && q.at % 10 < g.be) {
            q.a(e[n2], f[n2]);
            q.b(e[n2], f[n2] - 28);
        }
    }

    public static final boolean a(int n2, int n3, int n4) {
        if (i[n2] == 2 || i[n2] == 3 || i[n2] == 4) {
            int n5 = n2;
            l[n5] = l[n5] - n3;
            if (l[n2] <= 0) {
                a.l[n2] = 0;
                a.j[n2] = -5;
                a.y[n2] = -(n4 << 3);
                a.z[n2] = 0;
                a.t[n2] = 0;
                a.i[n2] = 1;
                return true;
            }
            a.j[n2] = 0;
            a.k[n2] = n4;
            a.i[n2] = 6;
            a.B[n2] = 3;
        }
        return false;
    }

    public static final boolean b(int n2, int n3, int n4) {
        if (i[n2] == 2 || i[n2] == 3 || i[n2] == 4) {
            int n5 = n2;
            l[n5] = l[n5] - n4;
            if (l[n2] <= 0) {
                a.l[n2] = 0;
                a.j[n2] = -5;
                a.y[n2] = q.ai[n3];
                a.z[n2] = 0;
                a.t[n2] = 0;
                a.i[n2] = 1;
                a.d(n2);
                return true;
            }
            a.j[n2] = 0;
            a.k[n2] = q.ai[n3] < 0 ? 1 : -1;
            a.i[n2] = 6;
            a.B[n2] = 3;
            q.ah[n3] = -1;
        }
        return false;
    }

    public static final void a(int n2) {
        int n3;
        int n4;
        c = 0;
        switch (n2) {
            case 105: {
                O = new int[1];
                a.c();
                a.L[0] = new int[]{7, 11, 17, 12, 29, 31, 32, 33};
                a.M[0] = new int[]{10, 10, 10, 10, 10, 10, 10, 10};
                a.d();
                a.e(8);
                a.m(0, 8);
                break;
            }
            case 1: {
                int[] nArray = new int[2];
                nArray[1] = 1;
                O = nArray;
                a.c();
                a.L[0] = new int[]{44, 40, 42, 39, 41, 39, 34, 48, 31, 21, 8, 26, 25, 24};
                a.M[0] = new int[]{10, 11, 9, 9, 7, 5, 9, 9, 7, 10, 9, 6, 8, 10};
                a.L[1] = new int[]{8, 3};
                a.M[1] = new int[]{9, 9};
                a.d();
                a.e(13);
                a.m(0, 9);
                a.h(1, 4);
                break;
            }
            case 2: {
                int[] nArray = new int[3];
                nArray[1] = 1;
                nArray[2] = 2;
                O = nArray;
                a.c();
                a.L[0] = new int[]{14, 15, 14};
                a.M[0] = new int[]{5, 13, 33};
                a.L[1] = new int[]{12, 6, 1, 6, 7, 9, 7, 14, 7};
                a.M[1] = new int[]{5, 5, 5, 7, 9, 9, 11, 13, 18};
                a.L[2] = new int[]{7, 4, 7, 13, 9, 12, 4, 9};
                a.M[2] = new int[]{23, 26, 29, 28, 32, 33, 35, 36};
                a.d();
                a.e(16);
                a.m(0, 2);
                a.h(1, 8);
                a.b(2, 3);
                break;
            }
            case 103: {
                O = new int[1];
                a.c();
                a.L[0] = new int[]{5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 10, 11, 12};
                a.M[0] = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 7, 7, 7, 7};
                a.d();
                a.e(3);
                a.m(0, 3);
                break;
            }
            case 3: {
                O = new int[]{1, 2, 3};
                a.c();
                a.L[0] = new int[]{68, 64, 27};
                a.M[0] = new int[]{19, 19, 5};
                a.L[1] = new int[]{55, 48};
                a.M[1] = new int[]{20, 24};
                a.L[2] = new int[]{44, 33, 23, 2, 23, 12, 27};
                a.M[2] = new int[]{13, 10, 2, 1, 11, 17, 21};
                a.d();
                a.e(16);
                a.h(0, 3);
                a.b(1, 2);
                a.j(2, 7);
                break;
            }
            case 4: {
                int[] nArray = new int[3];
                nArray[1] = 3;
                nArray[2] = 4;
                O = nArray;
                a.c();
                a.L[0] = new int[]{1, 97, 69};
                a.M[0] = new int[]{7, 8, 11};
                a.L[1] = new int[]{110, 111, 109, 110, 109};
                a.M[1] = new int[]{6, 6, 6, 6, 6};
                a.L[2] = new int[]{104, 88, 74, 64, 34, 18, 9, 63, 65, 70};
                a.M[2] = new int[]{10, 12, 11, 12, 9, 9, 9, 12, 12, 11};
                a.d();
                a.e(17);
                a.m(0, 3);
                a.j(1, 5);
                n4 = 9;
                n2 = 2;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 == c) break;
                n2 = n3;
                while (n2 < n3 + 9) {
                    a.J[n2] = 2;
                    a.d[n2] = 4;
                    a.F[n2] = 20;
                    a.G[n2] = 10;
                    a.g[n2] = 32;
                    a.h[n2] = 22;
                    a.k[n2] = 1;
                    a.i[n2] = 5;
                    a.j[n2] = n2 << 3;
                    a.l[n2] = a.C[n2] = o[d[n2]];
                    a.D[n2] = a[d[n2]];
                    a.I[n2] = p[d[n2]];
                    a.E[n2] = 0;
                    ++n2;
                }
                break;
            }
            case 5: {
                O = new int[]{5, 7, 6};
                a.c();
                a.L[0] = new int[]{20, 12, 14, 9, 7, 8, 7, 9, 8, 8, 13, 10, 7, 27};
                a.M[0] = new int[]{77, 76, 72, 69, 65, 65, 60, 60, 56, 48, 44, 39, 30, 24};
                a.L[1] = new int[]{6, 7, 6, 4, 4};
                a.M[1] = new int[]{54, 50, 44, 33, 18};
                a.L[2] = new int[]{18, 15, 23, 12, 14, 4, 7, 13, 20, 25};
                a.M[2] = new int[]{54, 35, 29, 22, 22, 22, 9, 8, 7, 4};
                a.d();
                a.e(25);
                a.q(0, 12);
                n4 = 5;
                n2 = 1;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 != c) {
                    n2 = n3;
                    while (n2 < n3 + 5) {
                        a.J[n2] = 1;
                        a.d[n2] = 7;
                        a.F[n2] = 10;
                        a.G[n2] = 10;
                        a.g[n2] = 16;
                        a.h[n2] = 16;
                        a.k[n2] = -1;
                        a.i[n2] = 5;
                        a.j[n2] = n2 + 60;
                        a.l[n2] = a.C[n2] = o[d[n2]];
                        a.D[n2] = a[d[n2]];
                        a.I[n2] = p[d[n2]];
                        a.E[n2] = 100;
                        a.w[n2] = 2;
                        a.x[n2] = 3;
                        ++n2;
                    }
                }
                a.o(2, 8);
                break;
            }
            case 101: {
                O = new int[]{6, 5};
                a.c();
                a.L[0] = new int[]{4, 6, 8, 10, 12, 14, 16};
                a.M[0] = new int[]{11, 11, 11, 11, 11, 11, 11};
                a.L[1] = new int[]{5, 7, 9, 13, 15};
                a.M[1] = new int[]{11, 11, 11, 11, 11, 11};
                a.d();
                a.e(3);
                a.o(0, 1);
                a.q(1, 2);
                break;
            }
            case 6: {
                O = new int[]{6, 3};
                a.c();
                a.L[0] = new int[]{8, 10, 14, 15, 20, 19, 33};
                a.M[0] = new int[]{13, 13, 12, 10, 11, 8, 4};
                a.L[1] = new int[]{48, 50, 59, 61, 67, 69};
                a.M[1] = new int[]{8, 9, 11, 13, 14, 15};
                a.d();
                a.e(12);
                a.o(0, 7);
                a.j(1, 5);
                break;
            }
            case 7: {
                O = new int[]{8, 10};
                a.c();
                a.L[0] = new int[]{6, 7, 12, 14, 30, 33, 31, 37, 38, 36, 40, 42, 44, 73, 75, 77};
                a.M[0] = new int[]{10, 10, 9, 8, 10, 10, 9, 9, 9, 6, 8, 8, 7, 10, 10, 10};
                a.L[1] = new int[]{89, 90, 91, 97, 59, 62};
                a.M[1] = new int[]{9, 9, 9, 11, 4, 5};
                a.d();
                a.e(17);
                n4 = 13;
                n2 = 0;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 != c) {
                    n2 = n3;
                    while (n2 < n3 + 13) {
                        a.J[n2] = 0;
                        a.d[n2] = 8;
                        a.F[n2] = 24;
                        a.G[n2] = 10;
                        a.g[n2] = 24;
                        a.h[n2] = 24;
                        int n5 = n2;
                        f[n5] = f[n5] + 5;
                        a.k[n2] = 1;
                        a.i[n2] = 5;
                        a.j[n2] = n2 * 5;
                        a.l[n2] = a.C[n2] = o[d[n2]];
                        a.D[n2] = a[d[n2]];
                        a.I[n2] = p[d[n2]];
                        a.E[n2] = 60;
                        ++n2;
                    }
                }
                a.d(1, 4);
                break;
            }
            case 8: {
                O = new int[]{10, 9, 11};
                a.c();
                a.L[0] = new int[]{4, 5, 27, 28, 52, 54, 58, 73, 74, 120, 121, 122, 144};
                a.M[0] = new int[]{10, 10, 9, 9, 10, 11, 10, 9, 8, 9, 8, 9, 10};
                a.L[1] = new int[]{14, 18, 35, 40, 48, 64, 72, 98, 112, 129};
                a.M[1] = new int[]{10, 10, 11, 11, 11, 10, 9, 9, 10, 8};
                a.L[2] = new int[]{14, 18, 35, 40, 48, 64, 72, 98, 112, 129};
                a.M[2] = new int[]{10, 10, 11, 11, 11, 10, 9, 9, 10, 8};
                a.d();
                a.e(28);
                a.d(0, 12);
                a.c(1, 8);
                a.i(2, 8);
                break;
            }
            case 106: {
                O = new int[]{10, 9, 11};
                a.c();
                a.L[0] = new int[]{11, 13};
                a.M[0] = new int[]{10, 8};
                a.L[1] = new int[]{21, 27};
                a.M[1] = new int[]{10, 9};
                a.L[2] = new int[]{21, 27};
                a.M[2] = new int[]{10, 9};
                a.d();
                a.e(6);
                a.d(0, 2);
                a.c(1, 2);
                a.i(2, 2);
                break;
            }
            case 9: {
                O = new int[]{12};
                a.c();
                a.L[0] = new int[]{8, 10, 5, 8, 10, 12, 24, 113, 116};
                a.M[0] = new int[]{19, 19, 16, 14, 6, 6, 17, 16, 16};
                a.d();
                a.e(8);
                a.p(0, 8);
                break;
            }
            case 10: {
                O = new int[]{12};
                a.c();
                a.L[0] = new int[]{2, 3, 7, 8, 9, 10, 37};
                a.M[0] = new int[]{12, 12, 11, 11, 10, 10, 8};
                a.d();
                a.e(5);
                a.p(0, 5);
                break;
            }
            case 11: {
                O = new int[]{13, 14};
                a.c();
                a.L[0] = new int[]{9, 29, 21, 26, 9, 16, 29, 14, 45, 45, 56};
                a.M[0] = new int[]{29, 27, 24, 17, 18, 7, 8, 1, 18, 36, 2};
                a.L[1] = new int[]{16, 13, 9, 25, 30, 36, 19, 35, 34, 25, 8, 10, 16, 15, 40, 41, 44, 45, 46, 46, 54, 58};
                a.M[1] = new int[]{35, 31, 27, 27, 30, 38, 27, 23, 14, 20, 21, 14, 10, 3, 5, 5, 16, 21, 21, 39, 16, 12};
                a.d();
                a.e(30);
                n4 = 10;
                n2 = 0;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 != c) {
                    n2 = n3;
                    while (n2 < n3 + 10) {
                        a.J[n2] = 0;
                        a.d[n2] = 13;
                        a.F[n2] = 120;
                        a.G[n2] = 50;
                        a.g[n2] = 20;
                        a.h[n2] = 16;
                        a.k[n2] = -1;
                        a.i[n2] = 5;
                        a.j[n2] = n2 << 1;
                        a.l[n2] = a.C[n2] = o[d[n2]];
                        a.D[n2] = a[d[n2]];
                        a.I[n2] = p[d[n2]];
                        a.E[n2] = 100;
                        a.w[n2] = -3;
                        a.x[n2] = 3;
                        ++n2;
                    }
                }
                n4 = 20;
                n2 = 1;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 == c) break;
                n2 = n3;
                while (n2 < n3 + 20) {
                    a.J[n2] = 1;
                    a.d[n2] = 14;
                    a.F[n2] = 36;
                    a.G[n2] = 20;
                    a.g[n2] = 32;
                    a.h[n2] = 24;
                    a.k[n2] = 1;
                    a.i[n2] = 5;
                    a.j[n2] = n2 << 3;
                    a.l[n2] = a.C[n2] = o[d[n2]];
                    a.D[n2] = a[d[n2]];
                    a.I[n2] = p[d[n2]];
                    a.E[n2] = 500;
                    ++n2;
                }
                break;
            }
            case 12: {
                O = new int[]{15, 16, 10};
                a.c();
                a.L[0] = new int[]{12, 21, 26, 50, 54, 59, 64, 69, 74, 82, 84, 82, 91};
                a.M[0] = new int[]{9, 12, 12, 6, 8, 9, 10, 11, 12, 13, 13, 13, 11};
                a.L[1] = new int[]{107, 110};
                a.M[1] = new int[]{11, 11};
                a.L[2] = new int[]{8, 10, 23, 24, 33, 35, 96, 100};
                a.M[2] = new int[]{8, 6, 7, 7, 6, 5, 5, 8};
                a.d();
                a.e(20);
                a.l(0, 12);
                a.f(1, 2);
                a.d(2, 6);
                break;
            }
            case 13: {
                O = new int[]{16, 17};
                a.c();
                a.L[0] = new int[]{14, 15, 24, 28, 50, 53, 69, 70, 73, 75};
                a.M[0] = new int[]{11, 11, 11, 11, 11, 11, 11, 11, 6, 6};
                a.L[1] = new int[]{40, 84, 86, 89, 93, 83, 84};
                a.M[1] = new int[]{9, 11, 11, 11, 11, 4, 4};
                a.d();
                a.e(12);
                a.f(0, 8);
                a.e(1, 4);
                break;
            }
            case 14: {
                O = new int[]{18, 17, 19};
                a.c();
                a.L[0] = new int[]{26, 61, 65, 64, 74, 88, 102};
                a.M[0] = new int[]{13, 9, 9, 15, 12, 14, 16};
                a.L[1] = new int[]{7, 8, 10, 47, 50, 52, 74};
                a.M[1] = new int[]{16, 16, 16, 12, 12, 12, 16};
                a.L[2] = new int[]{13, 49, 55, 71, 77, 83, 98};
                a.M[2] = new int[]{19, 19, 19, 19, 19, 19, 19};
                a.d();
                a.e(18);
                n4 = 6;
                n2 = 0;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 != c) {
                    n2 = n3;
                    while (n2 < n3 + 6) {
                        a.J[n2] = 0;
                        a.d[n2] = 18;
                        a.F[n2] = 36;
                        a.G[n2] = 20;
                        a.g[n2] = 32;
                        a.h[n2] = 18;
                        a.k[n2] = 1;
                        a.i[n2] = 5;
                        a.j[n2] = 60;
                        a.l[n2] = a.C[n2] = o[d[n2]];
                        a.D[n2] = a[d[n2]];
                        a.I[n2] = p[d[n2]];
                        a.E[n2] = 100;
                        ++n2;
                    }
                }
                a.e(1, 6);
                a.n(2, 6);
                break;
            }
            case 102: {
                O = new int[]{20};
                a.c();
                a.L[0] = new int[]{7, 9, 11, 13, 15, 17};
                a.M[0] = new int[]{12, 12, 12, 12, 12, 12};
                a.d();
                a.e(2);
                a.a(0, 2);
                break;
            }
            case 15: {
                O = new int[]{15, 19, 20};
                a.c();
                a.L[0] = new int[]{7, 9, 15, 23, 26, 36, 50, 132, 135, 141, 143};
                a.M[0] = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 8, 8};
                a.L[1] = new int[]{17, 40, 42, 56, 62, 81, 83};
                a.M[1] = new int[]{10, 10, 10, 10, 10, 10, 10};
                a.L[2] = new int[]{24, 88, 90, 94, 98, 102, 104, 120, 125};
                a.M[2] = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10};
                a.d();
                a.e(23);
                a.l(0, 9);
                a.n(1, 6);
                a.a(2, 8);
                break;
            }
            case 16: {
                O = new int[]{21, 19, 20};
                a.c();
                a.L[0] = new int[]{4, 8, 9, 12, 14, 15, 16, 21, 19};
                a.M[0] = new int[]{10, 12, 12, 10, 11, 9, 8, 7, 2};
                a.L[1] = new int[]{27, 29, 33, 40, 46, 52, 66, 75, 83, 85, 90};
                a.M[1] = new int[]{13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13};
                a.L[2] = new int[]{28, 30, 32, 41, 47, 53, 68, 76, 82, 86, 91};
                a.M[2] = new int[]{13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13};
                a.d();
                a.e(25);
                a.l(0, 7);
                a.n(1, 9);
                a.a(2, 9);
                break;
            }
            case 17: {
                O = new int[]{23, 24, 25};
                a.c();
                a.L[0] = new int[]{13, 37, 61, 70, 54, 30, 7, 61, 21, 15, 37, 53, 67, 7, 4, 20, 36, 54, 68, 29, 45, 61};
                a.M[0] = new int[]{28, 28, 28, 23, 23, 23, 23, 18, 18, 18, 13, 13, 13, 13, 8, 8, 8, 8, 8, 4, 4, 4};
                a.L[1] = new int[]{13, 37, 61, 70, 54, 30, 7, 61, 21, 15, 37, 53, 67, 7, 4, 20, 36, 54, 68, 29, 45, 61};
                a.M[1] = new int[]{28, 28, 28, 23, 23, 23, 23, 18, 18, 18, 13, 13, 13, 13, 8, 8, 8, 8, 8, 4, 4, 4};
                a.L[2] = new int[]{48, 65, 23, 36, 67, 13, 57, 24, 6, 50, 68};
                a.M[2] = new int[]{25, 21, 25, 19, 15, 11, 6, 5, 2, 1, 1};
                a.d();
                a.e(50);
                n4 = 20;
                n2 = 0;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 != c) {
                    n2 = n3;
                    while (n2 < n3 + 20) {
                        a.J[n2] = 0;
                        a.d[n2] = 23;
                        a.F[n2] = 40;
                        a.G[n2] = 10;
                        a.g[n2] = 24;
                        a.h[n2] = 22;
                        a.k[n2] = -1;
                        a.i[n2] = 5;
                        a.j[n2] = n2 + 60;
                        a.A[n2] = n2 + 20;
                        a.l[n2] = a.C[n2] = o[d[n2]];
                        a.D[n2] = a[d[n2]];
                        a.I[n2] = p[d[n2]];
                        a.E[n2] = 100;
                        a.w[n2] = 2;
                        a.x[n2] = 3;
                        ++n2;
                    }
                }
                n4 = 20;
                n2 = 1;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 != c) {
                    n2 = n3;
                    while (n2 < n3 + 20) {
                        a.J[n2] = 1;
                        a.d[n2] = 24;
                        a.F[n2] = 30;
                        a.G[n2] = 10;
                        a.g[n2] = 16;
                        a.h[n2] = 16;
                        a.k[n2] = -1;
                        a.i[n2] = 5;
                        a.j[n2] = n2 + 60;
                        a.l[n2] = a.C[n2] = o[d[n2]];
                        a.D[n2] = a[d[n2]];
                        a.I[n2] = p[d[n2]];
                        a.E[n2] = 100;
                        a.w[n2] = 2;
                        a.x[n2] = 3;
                        ++n2;
                    }
                }
                n4 = 10;
                n2 = 2;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 == c) break;
                n2 = n3;
                while (n2 < n3 + 10) {
                    a.J[n2] = 2;
                    a.d[n2] = 25;
                    a.F[n2] = 150;
                    a.G[n2] = 80;
                    a.g[n2] = 24;
                    a.h[n2] = 16;
                    a.k[n2] = -1;
                    a.i[n2] = 5;
                    a.j[n2] = n2 + 60;
                    a.l[n2] = a.C[n2] = o[d[n2]];
                    a.D[n2] = a[d[n2]];
                    a.I[n2] = p[d[n2]];
                    a.E[n2] = 100;
                    a.w[n2] = 2;
                    a.x[n2] = 3;
                    ++n2;
                }
                break;
            }
            case 18: {
                O = new int[]{15, 22, 26};
                a.c();
                a.L[0] = new int[]{8, 11, 27, 20, 34, 38, 71, 76};
                a.M[0] = new int[]{13, 13, 13, 10, 13, 13, 12, 12};
                a.L[1] = new int[]{91};
                a.M[1] = new int[]{8};
                a.L[2] = new int[]{16, 45, 50, 47, 45, 56, 57, 58, 66, 84, 86};
                a.M[2] = new int[]{11, 11, 11, 8, 9, 9, 8, 5, 11, 8, 8};
                a.d();
                a.e(17);
                a.l(0, 6);
                a.g(1, 1);
                n4 = 10;
                n2 = 2;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 == c) break;
                n2 = n3;
                while (n2 < n3 + 10) {
                    a.J[n2] = 2;
                    a.d[n2] = 26;
                    a.F[n2] = 24;
                    a.G[n2] = 10;
                    a.g[n2] = 24;
                    a.h[n2] = 20;
                    a.k[n2] = -1;
                    a.i[n2] = 5;
                    a.j[n2] = n2 + 60;
                    a.l[n2] = a.C[n2] = o[d[n2]];
                    a.D[n2] = a[d[n2]];
                    a.I[n2] = p[d[n2]];
                    a.E[n2] = 100;
                    a.w[n2] = 2;
                    a.x[n2] = 3;
                    ++n2;
                }
                break;
            }
            case 19: {
                O = new int[]{22, 27, 28};
                a.c();
                a.L[0] = new int[]{5, 6, 7, 14, 17, 17, 26, 31, 19, 22, 23, 21, 26, 27, 31, 34};
                a.M[0] = new int[]{27, 27, 27, 26, 24, 19, 24, 22, 14, 14, 9, 8, 4, 5, 7, 7};
                a.L[1] = new int[]{20, 21, 16, 15, 23, 21, 29, 23, 45, 46, 47, 62, 63};
                a.M[1] = new int[]{22, 21, 16, 17, 12, 11, 9, 3, 5, 5, 5, 21, 20};
                a.L[2] = new int[]{38, 41, 44, 48, 48, 44, 46, 50, 58, 59, 63, 67};
                a.M[2] = new int[]{7, 7, 7, 8, 15, 18, 19, 22, 23, 23, 21, 20};
                a.d();
                a.e(28);
                a.g(0, 10);
                a.k(1, 8);
                n4 = 10;
                n2 = 2;
                n3 = 0;
                while (n3 < c) {
                    if (d[n3] == -1) break;
                    ++n3;
                }
                if (n3 == c) break;
                n2 = n3;
                while (n2 < n3 + 10) {
                    a.J[n2] = 2;
                    a.d[n2] = 28;
                    a.F[n2] = 24;
                    a.G[n2] = 10;
                    a.g[n2] = 24;
                    a.h[n2] = 20;
                    a.k[n2] = -1;
                    a.i[n2] = 5;
                    a.j[n2] = n2 + 60;
                    a.l[n2] = a.C[n2] = o[d[n2]];
                    a.D[n2] = a[d[n2]];
                    a.I[n2] = p[d[n2]];
                    a.E[n2] = 100;
                    a.w[n2] = 2;
                    a.x[n2] = 3;
                    ++n2;
                }
                break;
            }
            case 20: {
                O = new int[]{27};
                a.c();
                a.L[0] = new int[0];
                a.M[0] = new int[0];
                a.d();
                a.e(0);
                a.k(0, 0);
            }
        }
        if (c > 0) {
            r = new Image[O.length][2][4];
            n2 = 0;
            while (n2 < O.length) {
                n4 = 0;
                while (n4 < 2) {
                    n3 = 0;
                    while (n3 < 4) {
                        try {
                            a.r[n2][n4][n3] = Image.createImage((String)("/mob/m" + O[n2] + n4 + n3 + ".png"));
                        }
                        catch (Exception exception) {}
                        if (r[n2][n4][n3] == null && n4 == 1) {
                            a.r[n2][1][n3] = r[n2][0][n3];
                        }
                        ++n3;
                    }
                    if (r[n2][n4][2] == null) {
                        a.r[n2][n4][2] = r[n2][n4][1];
                    }
                    ++n4;
                }
                ++n2;
            }
        }
    }

    public static final void a(Graphics graphics) {
        int n2 = 0;
        while (n2 < c) {
            if (i[n2] != 5 && i[n2] != 8 && e[n2] >= q.ay - 10 && e[n2] <= q.ay + q.ab + 10 && f[n2] >= q.az && f[n2] <= q.az + q.ac + 20) {
                graphics.drawImage(r[J[n2]][k[n2] >= 0 ? 0 : 1][B[n2]], e[n2], f[n2], 33);
                if (t[n2] != 0) {
                    graphics.setColor(13073);
                    graphics.drawRect(e[n2] - (h[n2] >> 1) - 1, f[n2] - g[n2] - 7, h[n2] + 2, 4);
                    int n3 = l[n2] * h[n2] / C[n2];
                    graphics.setColor(60928);
                    graphics.fillRect(e[n2] - (h[n2] >> 1), f[n2] - g[n2] - 6, n3, 3);
                    graphics.setColor(17442);
                    graphics.fillRect(e[n2] - (h[n2] >> 1) + n3, f[n2] - g[n2] - 6, h[n2] - n3 + 1, 3);
                }
            }
            ++n2;
        }
    }

    /*
     * Unable to fully structure code
     */
    public static final void b() {
        if (q.at % 100 == 0) {
            var1 = 0;
            while (var1 < a.c) {
                if (a.i[var1] != 5 && q.a(g.at - a.e[var1]) + q.a(g.au - a.f[var1]) > q.ac + q.ab) {
                    a.i[var1] = 10;
                } else if (a.i[var1] == 10) {
                    a.i[var1] = 2;
                }
                ++var1;
            }
        }
        var0_1 = 0;
        while (var0_1 < a.c) {
            if (a.m[var0_1] == 1 && a.l[var0_1] > 1 && g.as == 2) {
                if (a.l[var0_1] > 1) {
                    v0 = var0_1;
                    a.l[v0] = a.l[v0] - 3;
                }
                if (a.l[var0_1] < 1) {
                    a.l[var0_1] = 1;
                }
            }
            if (a.m[var0_1] == 1 && g.as == 2) {
                a.t[var0_1] = 100;
            }
            if (a.E[var0_1] <= 30) {
                v1 = var0_1;
                a.E[v1] = a.E[v1] + 1;
            }
            if (a.t[var0_1] > 0) {
                v2 = var0_1;
                a.t[v2] = a.t[v2] - 1;
            }
            block0 : switch (a.i[var0_1]) {
                case 5: {
                    v3 = var1 = var0_1;
                    a.j[v3] = a.j[v3] + 1;
                    if (a.j[var1] >= a.s) {
                        a.j[var1] = 0;
                        var2_2 = q.at % a.L[a.J[var1]].length;
                        if (a.N[a.J[var1]][var2_2]) {
                            a.j[var1] = a.s;
                            break;
                        }
                        a.e[var1] = a.L[a.J[var1]][var2_2];
                        a.f[var1] = a.M[a.J[var1]][var2_2];
                        a.N[a.J[var1]][var2_2] = true;
                        a.H[var1] = var2_2;
                        a.i[var1] = 2;
                        a.l[var1] = a.C[var1];
                        switch (a.d[var1]) {
                            case 0: 
                            case 1: 
                            case 8: 
                            case 9: 
                            case 12: 
                            case 15: 
                            case 23: 
                            case 26: {
                                a.u[var1] = a.e[var1] - 20;
                                a.v[var1] = a.e[var1] + 20;
                                break;
                            }
                            case 2: 
                            case 21: {
                                a.u[var1] = a.e[var1] + 24;
                                a.v[var1] = a.e[var1] + 24;
                                break;
                            }
                            case 3: 
                            case 10: 
                            case 27: {
                                a.u[var1] = a.e[var1];
                                a.v[var1] = a.f[var1];
                                break;
                            }
                            case 4: 
                            case 18: {
                                a.u[var1] = a.e[var1] - 20;
                                a.v[var1] = a.e[var1] + 20;
                                a.y[var1] = -3;
                                break;
                            }
                            case 5: 
                            case 6: 
                            case 22: 
                            case 28: {
                                a.u[var1] = a.e[var1] - 20;
                                a.v[var1] = a.e[var1] + 20;
                                break;
                            }
                            case 7: {
                                a.u[var1] = a.e[var1];
                                a.v[var1] = a.f[var1];
                                break;
                            }
                            case 11: 
                            case 24: {
                                a.i[var1] = 5;
                                break;
                            }
                            case 13: 
                            case 25: {
                                a.u[var1] = a.e[var1] + 12;
                                a.v[var1] = a.f[var1] + 12;
                                break;
                            }
                            case 14: 
                            case 19: 
                            case 20: {
                                a.u[var1] = a.e[var1] - 30;
                                a.v[var1] = a.e[var1] + 30;
                                break;
                            }
                            case 16: {
                                a.u[var1] = a.e[var1] - 40;
                                a.v[var1] = a.e[var1] + 40;
                                break;
                            }
                            case 17: {
                                a.u[var1] = a.e[var1] - 60;
                                a.v[var1] = a.e[var1] + 60;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    var1 = var0_1;
                    switch (a.d[var1]) {
                        case 0: {
                            a.j(var1);
                            break;
                        }
                        case 1: {
                            v4 = var2_2 = var1;
                            a.e[v4] = a.e[v4] + (a.k[var2_2] << 2);
                            a.B[var2_2] = 1;
                            v5 = var2_2;
                            a.j[v5] = a.j[v5] + 1;
                            if (a.e[var2_2] > a.v[var2_2]) {
                                a.k[var2_2] = -1;
                            } else if (a.e[var2_2] < a.u[var2_2]) {
                                a.k[var2_2] = 1;
                            }
                            if (a.j[var2_2] <= 3) break block0;
                            a.j[var2_2] = 0;
                            a.i[var2_2] = 3;
                            break;
                        }
                        case 2: {
                            v6 = var2_2 = var1;
                            a.e[v6] = a.e[v6] + a.z[var2_2];
                            if (a.z[var2_2] > 1) {
                                v7 = var2_2;
                                a.z[v7] = a.z[v7] - 1;
                            } else if (a.z[var2_2] < -1) {
                                v8 = var2_2;
                                a.z[v8] = a.z[v8] + 1;
                            }
                            v9 = var2_2;
                            a.f[v9] = a.f[v9] + a.y[var2_2];
                            v10 = var2_2;
                            a.y[v10] = a.y[v10] + 1;
                            if (a.y[var2_2] > 3) {
                                a.y[var2_2] = -3;
                                a.z[var2_2] = a.k[var2_2] << 2;
                                a.j[var2_2] = 0;
                                a.f[var2_2] = a.M[a.J[var2_2]][a.H[var2_2]];
                                a.i[var2_2] = 3;
                            }
                            if (a.y[var2_2] == -2) {
                                if (a.e[var2_2] > a.v[var2_2]) {
                                    a.k[var2_2] = -1;
                                } else if (a.e[var2_2] < a.u[var2_2]) {
                                    a.k[var2_2] = 1;
                                }
                                a.z[var2_2] = a.k[var2_2] * 6;
                                a.B[var2_2] = 0;
                            } else {
                                a.B[var2_2] = 1;
                            }
                            a.b(var2_2);
                            if (q.at % 9 != 0) break block0;
                            a.c(var2_2);
                            break;
                        }
                        case 3: 
                        case 27: {
                            a.n(var1);
                            break;
                        }
                        case 4: 
                        case 18: {
                            v11 = var2_2 = var1;
                            a.e[v11] = a.e[v11] + a.z[var2_2];
                            if (a.z[var2_2] > 1) {
                                v12 = var2_2;
                                a.z[v12] = a.z[v12] - 1;
                            } else if (a.z[var2_2] < -1) {
                                v13 = var2_2;
                                a.z[v13] = a.z[v13] + 1;
                            }
                            v14 = var2_2;
                            a.f[v14] = a.f[v14] + a.y[var2_2];
                            v15 = var2_2;
                            a.y[v15] = a.y[v15] + 1;
                            if (a.y[var2_2] > 3) {
                                a.y[var2_2] = -3;
                                a.z[var2_2] = a.k[var2_2] << 2;
                                a.j[var2_2] = 0;
                                a.f[var2_2] = a.M[a.J[var2_2]][a.H[var2_2]];
                            }
                            if (a.y[var2_2] == -2) {
                                if (a.e[var2_2] > a.v[var2_2]) {
                                    a.k[var2_2] = -1;
                                } else if (a.e[var2_2] < a.u[var2_2]) {
                                    a.k[var2_2] = 1;
                                }
                                a.z[var2_2] = a.k[var2_2] * 6;
                                a.B[var2_2] = 0;
                            } else {
                                a.B[var2_2] = 1;
                            }
                            a.b(var2_2);
                            if (q.at % 9 != 0) break block0;
                            a.c(var2_2);
                            break;
                        }
                        case 5: 
                        case 28: {
                            a.j(var1);
                            break;
                        }
                        case 6: {
                            a.r(var1);
                            break;
                        }
                        case 7: {
                            v16 = var2_2 = var1;
                            a.e[v16] = a.e[v16] + a.w[var2_2];
                            v17 = var2_2;
                            a.f[v17] = a.f[v17] + a.x[var2_2];
                            if (a.e[var2_2] < 0) {
                                a.e[var2_2] = 0;
                            }
                            if (a.e[var2_2] > j.c - 10) {
                                a.e[var2_2] = j.c - 10;
                            }
                            if (a.f[var2_2] < 0) {
                                a.f[var2_2] = 0;
                            }
                            if (a.f[var2_2] > j.d - 20) {
                                a.f[var2_2] = j.d - 20;
                            }
                            if (q.at % 31 == 0) {
                                a.w[var2_2] = q.at % 3 - 1;
                                a.x[var2_2] = (q.at >> 1) % 3 - 1;
                                if (j.a(a.e[var2_2], a.f[var2_2] + 10, 8192) && q.at % var2_2 == 0) {
                                    a.i[var2_2] = 3;
                                    a.k[var2_2] = -a.k[var2_2];
                                    a.j[var2_2] = 0;
                                    break;
                                }
                            }
                            a.B[var2_2] = a.B[var2_2] == 1 ? 3 : 1;
                            a.b(var2_2);
                            break;
                        }
                        case 8: {
                            var2_2 = var1;
                            if ((q.at + var2_2) % 2 == 0) {
                                v18 = var2_2;
                                a.B[v18] = a.B[v18] + 1;
                                if (a.B[var2_2] > 1) {
                                    a.B[var2_2] = 0;
                                }
                                v19 = var2_2;
                                a.e[v19] = a.e[v19] + (a.k[var2_2] << 1);
                            }
                            if (q.at % (var2_2 + 1 << 3) == 0) {
                                a.i[var2_2] = 2;
                            }
                            if (a.e[var2_2] > a.v[var2_2]) {
                                a.k[var2_2] = -1;
                            } else if (a.e[var2_2] < a.u[var2_2]) {
                                a.k[var2_2] = 1;
                            }
                            a.b(var2_2);
                            break;
                        }
                        case 10: {
                            var2_2 = var1;
                            a.n(var2_2);
                            break;
                        }
                        case 9: {
                            a.i[var1] = 3;
                            a.k[var1] = g.at > a.e[var1] ? 1 : -1;
                            break;
                        }
                        case 11: {
                            a.k(var1);
                            break;
                        }
                        case 13: {
                            v20 = var2_2 = var1;
                            a.e[v20] = a.e[v20] + a.w[var2_2];
                            if (a.e[var2_2] - a.u[var2_2] > 100) {
                                a.k[var2_2] = -1;
                                a.w[var2_2] = a.k[var2_2] * (q.at % 4 + 1);
                                v21 = var2_2;
                                a.e[v21] = a.e[v21] + (a.k[var2_2] << 2);
                            } else if (a.e[var2_2] - a.u[var2_2] < -100) {
                                a.k[var2_2] = 1;
                                a.w[var2_2] = a.k[var2_2] * (q.at % 4 + 1);
                                v22 = var2_2;
                                a.e[v22] = a.e[v22] + (a.k[var2_2] << 2);
                            }
                            a.x[var2_2] = a.f[var2_2] - a.v[var2_2] > 20 ? -(q.at % 4 + 1) : (a.f[var2_2] - a.v[var2_2] < -20 ? q.at % 4 + 1 : 0);
                            v23 = var2_2;
                            a.f[v23] = a.f[v23] + (a.x[var2_2] >> 1);
                            if (q.at % 2 == 0) {
                                v24 = var2_2;
                                a.B[v24] = a.B[v24] + 1;
                                if (a.B[var2_2] > 1) {
                                    a.B[var2_2] = 0;
                                    v25 = var2_2;
                                    a.f[v25] = a.f[v25] - 12;
                                } else {
                                    v26 = var2_2;
                                    a.f[v26] = a.f[v26] + 12;
                                }
                            }
                            a.b(var2_2);
                            break;
                        }
                        case 25: {
                            v27 = var2_2 = var1;
                            a.e[v27] = a.e[v27] + a.w[var2_2];
                            if (a.e[var2_2] - a.u[var2_2] > 100) {
                                a.k[var2_2] = -1;
                                a.w[var2_2] = a.k[var2_2] * (q.at % 4 + 1);
                                v28 = var2_2;
                                a.e[v28] = a.e[v28] + (a.k[var2_2] << 2);
                            } else if (a.e[var2_2] - a.u[var2_2] < -100) {
                                a.k[var2_2] = 1;
                                a.w[var2_2] = a.k[var2_2] * (q.at % 4 + 1);
                                v29 = var2_2;
                                a.e[v29] = a.e[v29] + (a.k[var2_2] << 2);
                            }
                            a.x[var2_2] = a.f[var2_2] - a.v[var2_2] > 20 ? -(q.at % 4 + 1) : (a.f[var2_2] - a.v[var2_2] < -20 ? q.at % 4 + 1 : 0);
                            v30 = var2_2;
                            a.f[v30] = a.f[v30] + (a.x[var2_2] >> 1);
                            if (q.at % 2 == 0) {
                                v31 = var2_2;
                                a.B[v31] = a.B[v31] + 1;
                                if (a.B[var2_2] > 1) {
                                    a.B[var2_2] = 0;
                                }
                            }
                            a.b(var2_2);
                            break;
                        }
                        case 14: {
                            var2_2 = var1;
                            a.k(var2_2);
                            break;
                        }
                        case 15: 
                        case 26: {
                            var2_2 = var1;
                            a.r(var2_2);
                            break;
                        }
                        case 16: 
                        case 22: 
                        case 24: {
                            var2_2 = var1;
                            if ((q.at + (var2_2 << 5)) % 100 > 30) {
                                v32 = var2_2;
                                a.B[v32] = a.B[v32] + 1;
                                if (a.B[var2_2] > 1) {
                                    a.B[var2_2] = 0;
                                }
                                v33 = var2_2;
                                a.e[v33] = a.e[v33] + (a.k[var2_2] << 2);
                            }
                            if (a.e[var2_2] > a.v[var2_2]) {
                                a.k[var2_2] = -1;
                            } else if (a.e[var2_2] < a.u[var2_2]) {
                                a.k[var2_2] = 1;
                            }
                            a.b(var2_2);
                            if (q.at % 9 != 0) break block0;
                            a.c(var2_2);
                            break;
                        }
                        case 19: {
                            var2_2 = var1;
                            if (q.at % 2 == 0) {
                                v34 = var2_2;
                                a.B[v34] = a.B[v34] + 1;
                                if (a.B[var2_2] > 1) {
                                    a.B[var2_2] = 0;
                                }
                            }
                            v35 = var2_2;
                            a.e[v35] = a.e[v35] + (a.k[var2_2] << 1);
                            if (a.e[var2_2] > a.v[var2_2]) {
                                a.k[var2_2] = -1;
                            } else if (a.e[var2_2] < a.u[var2_2]) {
                                a.k[var2_2] = 1;
                            }
                            a.b(var2_2);
                            break;
                        }
                        case 17: {
                            var2_2 = var1;
                            if (q.at % 2 == 0) {
                                v36 = var2_2;
                                a.B[v36] = a.B[v36] + 1;
                                if (a.B[var2_2] > 1) {
                                    a.B[var2_2] = 0;
                                }
                                v37 = var2_2;
                                a.e[v37] = a.e[v37] + (a.k[var2_2] << 1);
                            }
                            if (a.e[var2_2] > a.v[var2_2]) {
                                a.k[var2_2] = -1;
                            } else if (a.e[var2_2] < a.u[var2_2]) {
                                a.k[var2_2] = 1;
                            }
                            a.b(var2_2);
                            if (q.at % 9 != 0) break block0;
                            a.c(var2_2);
                            break;
                        }
                        case 12: {
                            a.j(var1);
                            break;
                        }
                        case 20: {
                            v38 = var2_2 = var1;
                            a.B[v38] = a.B[v38] + 1;
                            if (a.B[var2_2] > 1) {
                                a.B[var2_2] = 0;
                            }
                            v39 = var2_2;
                            a.e[v39] = a.e[v39] + (a.k[var2_2] << 2);
                            if (a.e[var2_2] > a.v[var2_2]) {
                                a.k[var2_2] = -1;
                            } else if (a.e[var2_2] < a.u[var2_2]) {
                                a.k[var2_2] = 1;
                            }
                            a.b(var2_2);
                            break;
                        }
                        case 23: {
                            var2_2 = var1;
                            if (q.at % 4 == 0) {
                                v40 = var2_2;
                                a.B[v40] = a.B[v40] + 1;
                                a.j[var2_2] = 0;
                                if (a.B[var2_2] > 1) {
                                    a.B[var2_2] = 0;
                                    a.j[var2_2] = 1;
                                }
                            }
                            v41 = var2_2;
                            a.e[v41] = a.e[v41] + a.j[var2_2] * a.k[var2_2];
                            if (a.e[var2_2] > a.v[var2_2]) {
                                a.k[var2_2] = -1;
                            } else if (a.e[var2_2] < a.u[var2_2]) {
                                a.k[var2_2] = 1;
                            }
                            a.b(var2_2);
                            if (q.at % 9 != 0) break block0;
                            a.c(var2_2);
                            break;
                        }
                        case 21: {
                            v42 = var2_2 = var1;
                            a.B[v42] = a.B[v42] + 1;
                            if (a.B[var2_2] > 1) {
                                a.B[var2_2] = 0;
                            }
                            if (var2_2 % 2 == 0) {
                                v43 = var2_2;
                                a.e[v43] = a.e[v43] + a.k[var2_2];
                            } else {
                                v44 = var2_2;
                                a.e[v44] = a.e[v44] + (a.k[var2_2] << 1);
                            }
                            if (a.e[var2_2] > a.v[var2_2]) {
                                a.k[var2_2] = -1;
                            } else if (a.e[var2_2] < a.u[var2_2]) {
                                a.k[var2_2] = 1;
                            }
                            a.b(var2_2);
                        }
                    }
                    break;
                }
                case 6: {
                    var1 = var0_1;
                    a.t[var1] = 100;
                    a.B[var1] = 2;
                    if (g.as == 3 && a.m[var1] == 1) {
                        a.j[var1] = 0;
                        a.i[var1] = 3;
                    }
                    switch (a.d[var1]) {
                        case 0: {
                            var2_2 = var1;
                            if (a.E[var2_2] < 30) {
                                v45 = var2_2;
                                a.e[v45] = a.e[v45] - (a.k[var2_2] << 1);
                            } else {
                                v46 = var2_2;
                                a.e[v46] = a.e[v46] - a.k[var2_2];
                            }
                            v47 = var2_2;
                            a.j[v47] = a.j[v47] + 1;
                            if (a.j[var2_2] == 1) {
                                v48 = var2_2;
                                a.f[v48] = a.f[v48] - 2;
                                break;
                            }
                            if (a.j[var2_2] != 5) break block0;
                            v49 = var2_2;
                            a.f[v49] = a.f[v49] + 2;
                            a.i[var2_2] = 2;
                            break;
                        }
                        case 1: {
                            a.o(var1);
                            break;
                        }
                        case 2: {
                            a.f(var1);
                            break;
                        }
                        case 3: 
                        case 27: {
                            a.m(var1);
                            break;
                        }
                        case 4: 
                        case 18: {
                            v50 = var2_2 = var1;
                            a.e[v50] = a.e[v50] - a.k[var2_2];
                            a.B[var2_2] = 2;
                            v51 = var2_2;
                            a.j[v51] = a.j[v51] + 1;
                            if (a.j[var2_2] != 7) break block0;
                            a.f[var2_2] = a.M[a.J[var2_2]][a.H[var2_2]];
                            a.i[var2_2] = 2;
                            a.y[var2_2] = -4;
                            a.j[var2_2] = a.k[var2_2] * 3;
                            break;
                        }
                        case 5: 
                        case 28: {
                            a.q(var1);
                            break;
                        }
                        case 6: {
                            var2_2 = var1;
                            a.q(var2_2);
                            break;
                        }
                        case 7: {
                            var2_2 = var1;
                            a.m(var2_2);
                            break;
                        }
                        case 8: {
                            var2_2 = var1;
                            a.q(var2_2);
                            break;
                        }
                        case 9: {
                            var2_2 = var1;
                            a.k[var2_2] = -g.aF;
                            a.B[var2_2] = a.j[var2_2] % 4 > 1 ? 0 : 2;
                            a.i[var2_2] = 1;
                            a.j[var2_2] = -8;
                            a.y[var2_2] = g.aF << 3;
                            a.e[a.A[var2_2]] = a.e[var2_2];
                            a.f[a.A[var2_2]] = a.f[var2_2];
                            a.H[a.A[var2_2]] = a.H[var2_2];
                            a.u[a.A[var2_2]] = a.e[a.A[var2_2]] - 20;
                            a.v[a.A[var2_2]] = a.e[a.A[var2_2]] + 20;
                            a.l[a.A[var2_2]] = a.C[a.A[var2_2]];
                            a.k[a.A[var2_2]] = -g.aF;
                            a.i[a.A[var2_2]] = 2;
                            a.j[a.A[var2_2]] = 0;
                            a.k[var2_2] = 0;
                            a.x[var2_2] = 0;
                            a.w[var2_2] = 0;
                            a.E[var2_2] = 0;
                            break;
                        }
                        case 10: {
                            var2_2 = var1;
                            a.m(var2_2);
                            break;
                        }
                        case 11: 
                        case 22: {
                            a.g(var1);
                            break;
                        }
                        case 13: 
                        case 25: {
                            v52 = var2_2 = var1;
                            a.j[v52] = a.j[v52] + 1;
                            v53 = var2_2;
                            a.e[v53] = a.e[v53] - (a.w[var2_2] >> 3);
                            v54 = var2_2;
                            a.f[v54] = a.f[v54] - (a.x[var2_2] >> 3);
                            if (a.w[var2_2] == 0) {
                                a.w[var2_2] = 2;
                            }
                            if (a.x[var2_2] == 0) {
                                a.x[var2_2] = 2;
                            }
                            if (a.j[var2_2] != 5) break block0;
                            a.i[var2_2] = 2;
                            a.j[var2_2] = 0;
                            a.k[var2_2] = a.e[var2_2] > a.u[var2_2] ? -1 : 1;
                            a.w[var2_2] = a.k[var2_2] << 3;
                            a.E[var2_2] = 0;
                            break;
                        }
                        case 14: {
                            var2_2 = var1;
                            a.g(var2_2);
                            break;
                        }
                        case 15: 
                        case 24: 
                        case 26: {
                            var2_2 = var1;
                            a.q(var2_2);
                            break;
                        }
                        case 23: {
                            var2_2 = var1;
                            a.g(var2_2);
                            if (a.l[var2_2] >= a.C[var2_2] >> 1) break block0;
                            a.i[var2_2] = 1;
                            a.j[var2_2] = -8;
                            a.y[var2_2] = g.aF << 3;
                            break;
                        }
                        case 16: 
                        case 21: {
                            a.o(var1);
                            break;
                        }
                        case 19: {
                            var2_2 = var1;
                            a.i[var2_2] = 4;
                            break;
                        }
                        case 17: {
                            a.q(var1);
                            break;
                        }
                        case 12: {
                            a.o(var1);
                            break;
                        }
                        case 20: {
                            a.f(var1);
                        }
                    }
                    break;
                }
                case 4: {
                    if (a.d[var0_1] != 19 && (q.aA || q.bd)) {
                        a.i[var0_1] = 2;
                        break;
                    }
                    var1 = var0_1;
                    if (g.ba) {
                        a.B[var1] = 0;
                        a.i[var1] = 2;
                        a.j[var1] = 0;
                        a.y[var1] = 0;
                    }
                    switch (a.d[var1]) {
                        case 0: {
                            a.h(var1);
                            break;
                        }
                        case 1: {
                            var2_2 = var1;
                            a.f[var2_2] = a.M[a.J[var2_2]][a.H[var2_2]];
                            a.i[var2_2] = 3;
                            a.y[var2_2] = -4;
                            v55 = a.j;
                            v56 = var2_2;
                            v57 = 0;
                            ** GOTO lbl774
                        }
                        case 2: {
                            v58 = var2_2 = var1;
                            a.j[v58] = a.j[v58] + 1;
                            if (a.j[var2_2] == 3) {
                                if (a.K[var2_2]) {
                                    a.K[var2_2] = false;
                                    g.b(a.D[var2_2], a.k[var2_2]);
                                } else {
                                    g.a(a.D[var2_2], a.k[var2_2]);
                                }
                            }
                            if ((a.j[var2_2] >> 2) % 2 == 0) {
                                v59 = var2_2;
                                a.e[v59] = a.e[v59] + a.k[var2_2];
                                a.B[var2_2] = 0;
                            } else {
                                a.B[var2_2] = 1;
                                v60 = var2_2;
                                a.e[v60] = a.e[v60] - a.k[var2_2];
                            }
                            if (a.j[var2_2] != 12) break;
                            a.f[var2_2] = a.M[a.J[var2_2]][a.H[var2_2]];
                            a.i[var2_2] = 3;
                            a.y[var2_2] = -4;
                            a.j[var2_2] = 0;
                            break;
                        }
                        case 3: 
                        case 27: {
                            a.l(var1);
                            break;
                        }
                        case 4: 
                        case 18: {
                            v61 = var2_2 = var1;
                            a.j[v61] = a.j[v61] + 1;
                            if (a.j[var2_2] == 3) {
                                if (a.K[var2_2]) {
                                    a.K[var2_2] = false;
                                    g.b(a.D[var2_2], a.k[var2_2]);
                                } else {
                                    g.a(a.D[var2_2], a.k[var2_2]);
                                }
                            }
                            if ((a.j[var2_2] >> 2) % 2 == 0) {
                                v62 = var2_2;
                                a.e[v62] = a.e[v62] + a.k[var2_2];
                            } else {
                                v63 = var2_2;
                                a.e[v63] = a.e[v63] - a.k[var2_2];
                            }
                            a.B[var2_2] = 3;
                            if (a.j[var2_2] != 12) break;
                            a.f[var2_2] = a.M[a.J[var2_2]][a.H[var2_2]];
                            a.i[var2_2] = 3;
                            a.y[var2_2] = -4;
                            a.j[var2_2] = 0;
                            break;
                        }
                        case 5: 
                        case 21: 
                        case 22: 
                        case 23: 
                        case 24: 
                        case 28: {
                            a.p(var1);
                            break;
                        }
                        case 6: {
                            v64 = var2_2 = var1;
                            a.j[v64] = a.j[v64] + 1;
                            a.B[var2_2] = 3;
                            if (a.j[var2_2] == 3) {
                                q.b(a.e[var2_2] + (a.k[var2_2] << 3), a.f[var2_2] - 12, a.k[var2_2] << 3, a.D[var2_2], 2);
                                break;
                            }
                            if (a.j[var2_2] != 6) break;
                            a.i[var2_2] = 3;
                            a.j[var2_2] = 0;
                            break;
                        }
                        case 7: {
                            var2_2 = var1;
                            a.l(var2_2);
                            break;
                        }
                        case 8: {
                            var2_2 = var1;
                            a.h(var2_2);
                            break;
                        }
                        case 10: {
                            var2_2 = var1;
                            a.l(var2_2);
                            break;
                        }
                        case 9: {
                            v65 = var2_2 = var1;
                            a.j[v65] = a.j[v65] + 1;
                            if (a.j[var2_2] != 6) break;
                            a.j[var2_2] = 0;
                            a.i[var2_2] = 2;
                            q.a(a.e[var2_2] + (a.k[var2_2] << 4), a.f[var2_2] - 16, a.k[var2_2] << 3, 0, false);
                            break;
                        }
                        case 11: 
                        case 26: {
                            a.i(var1);
                            break;
                        }
                        case 13: 
                        case 25: {
                            var2_2 = var1;
                            if (a.e[var2_2] < g.at) {
                                a.w[var2_2] = 4 + q.at % 6;
                            } else if (a.e[var2_2] > g.at) {
                                a.w[var2_2] = -(4 + q.at % 6);
                            }
                            if (a.f[var2_2] < g.au) {
                                a.x[var2_2] = 2 + q.at % 4;
                            } else if (a.f[var2_2] > g.au) {
                                a.x[var2_2] = -(2 + q.at % 4);
                            }
                            v66 = var2_2;
                            a.e[v66] = a.e[v66] + a.w[var2_2];
                            v67 = var2_2;
                            a.f[v67] = a.f[v67] + a.x[var2_2];
                            if (q.at % 2 == 0) {
                                v68 = var2_2;
                                a.B[v68] = a.B[v68] + 1;
                                if (a.B[var2_2] > 1) {
                                    a.B[var2_2] = 0;
                                }
                            }
                            if (q.a(a.e[var2_2] - g.at) >= 5 || q.a(a.f[var2_2] - g.au) >= 5) break;
                            g.a(a.D[var2_2], -g.aF);
                            a.i[var2_2] = 2;
                            a.j[var2_2] = 0;
                            a.k[var2_2] = a.e[var2_2] > a.u[var2_2] ? -1 : 1;
                            a.w[var2_2] = a.k[var2_2] * (q.at % 4 + 1);
                            a.E[var2_2] = 10;
                            break;
                        }
                        case 14: {
                            var2_2 = var1;
                            a.i(var2_2);
                            break;
                        }
                        case 15: {
                            var2_2 = var1;
                            a.h(var2_2);
                            break;
                        }
                        case 16: {
                            a.h(var1);
                            break;
                        }
                        case 17: {
                            var2_2 = var1;
                            a.p(var2_2);
                            break;
                        }
                        case 19: {
                            v69 = var2_2 = var1;
                            a.j[v69] = a.j[v69] + 1;
                            a.B[var2_2] = 3;
                            if (a.j[var2_2] <= 15) {
                                v70 = var2_2;
                                a.f[v70] = a.f[v70] - 7;
                                if (a.e[var2_2] < g.at) {
                                    v71 = var2_2;
                                    a.e[v71] = a.e[v71] + 1;
                                } else if (a.e[var2_2] > g.at) {
                                    v72 = var2_2;
                                    a.e[v72] = a.e[v72] - 1;
                                }
                                if (!q.aA && !q.bd && q.a(a.e[var2_2] - g.at) < 16 && q.a(a.f[var2_2] - (g.au - 16)) < 20) {
                                    g.a(a.D[var2_2], -g.aF);
                                    a.j[var2_2] = 15;
                                }
                            }
                            if (a.j[var2_2] == 15) {
                                a.y[var2_2] = 1;
                            }
                            if (a.j[var2_2] <= 15) break;
                            a.B[var2_2] = 2;
                            v73 = var2_2;
                            a.f[v73] = a.f[v73] + a.y[var2_2];
                            v74 = var2_2;
                            a.y[v74] = a.y[v74] + 1;
                            if (!q.aA && !q.bd && q.a(a.e[var2_2] - g.at) < 16 && q.a(a.f[var2_2] - (g.au - 16)) < 20) {
                                g.a(a.D[var2_2], -g.aF);
                            }
                            if (a.f[var2_2] <= a.M[a.J[var2_2]][a.H[var2_2]]) break;
                            a.f[var2_2] = a.M[a.J[var2_2]][a.H[var2_2]];
                            a.y[var2_2] = 0;
                            a.j[var2_2] = 0;
                            a.i[var2_2] = 2;
                            break;
                        }
                        case 12: {
                            var2_2 = var1;
                            g.a(a.D[var2_2], -g.aF);
                            a.i[var2_2] = 3;
                            a.j[var2_2] = 0;
                            v55 = a.B;
                            v56 = var2_2;
                            v57 = 2;
                            ** GOTO lbl774
                        }
                        case 20: {
                            v75 = var2_2 = var1;
                            a.j[v75] = a.j[v75] + 1;
                            if (a.j[var2_2] <= 10) {
                                if (a.f[var2_2] > g.au) {
                                    v76 = var2_2;
                                    a.f[v76] = a.f[v76] - 4;
                                }
                                if (a.e[var2_2] < g.at) {
                                    v77 = var2_2;
                                    a.e[v77] = a.e[v77] + 2;
                                } else if (a.e[var2_2] > g.at) {
                                    v78 = var2_2;
                                    a.e[v78] = a.e[v78] - 2;
                                }
                                if (q.a(a.e[var2_2] - g.at) < 16 && q.a(a.f[var2_2] - (g.au - 16)) < 20) {
                                    g.a(a.D[var2_2], -g.aF);
                                    a.j[var2_2] = 10;
                                }
                            }
                            if (a.j[var2_2] == 10) {
                                a.y[var2_2] = 1;
                            }
                            if (a.j[var2_2] > 10) {
                                v79 = var2_2;
                                a.f[v79] = a.f[v79] + a.y[var2_2];
                                v80 = var2_2;
                                a.y[v80] = a.y[v80] + 1;
                                if (a.f[var2_2] > a.M[a.J[var2_2]][a.H[var2_2]]) {
                                    a.f[var2_2] = a.M[a.J[var2_2]][a.H[var2_2]];
                                    a.y[var2_2] = 0;
                                    a.j[var2_2] = 0;
                                    a.i[var2_2] = 2;
                                }
                            }
                            v55 = a.B;
                            v56 = var2_2;
                            v57 = a.j[var2_2] % 2 == 0 ? 0 : 1;
lbl774:
                            // 3 sources

                            v55[v56] = v57;
                        }
                    }
                    break;
                }
                case 3: {
                    v81 = var1 = var0_1;
                    a.j[v81] = a.j[v81] + 1;
                    if (a.j[var1] >= 35) {
                        a.j[var1] = 0;
                        a.i[var1] = 2;
                        if (a.m[var1] == 1 && g.as == 3) {
                            a.m[var1] = -1;
                        }
                    }
                    a.B[var1] = 0;
                    if (g.as == 3 && a.m[var1] == 1) break;
                    a.b(var1);
                    break;
                }
                case 1: {
                    var1 = var0_1;
                    a.m[var1] = -1;
                    v82 = var1;
                    a.j[v82] = a.j[v82] + 1;
                    v83 = var1;
                    a.f[v83] = a.f[v83] + a.j[var1];
                    if (q.at % 2 == 0) {
                        if (a.y[var1] > 1) {
                            v84 = var1;
                            a.y[v84] = a.y[v84] - 1;
                        } else if (a.y[var1] < -1) {
                            v85 = var1;
                            a.y[v85] = a.y[v85] + 1;
                        }
                    }
                    v86 = var1;
                    a.e[v86] = a.e[v86] + a.y[var1];
                    a.B[var1] = 2;
                    if (a.f[var1] > q.Z * 24 || a.e[var1] < q.W * 24 || a.e[var1] > q.Y * 24) {
                        a.j[var1] = -300;
                        a.i[var1] = 5;
                        a.N[a.J[var1]][a.H[var1]] = false;
                        break;
                    }
                    if ((a.z[var1] == 0 || a.z[var1] < 3 && a.j[var1] > 7) && (j.b(a.e[var1], a.f[var1]) & 2) == 2) {
                        if (a.d[var1] == 25 && var1 > 3 && !g.A && q.bi != 5) {
                            var2_2 = j.d(a.f[var1]);
                            q.bi = 5;
                            q.f();
                            q.a(a.e[var1], var2_2);
                            q.b(a.e[var1], var2_2 - 28);
                        }
                        if (a.d[var1] == 23 && a.i[a.A[var1]] == 5) {
                            a.e[a.A[var1]] = a.e[var1] - (a.k[var1] << 3);
                            a.f[a.A[var1]] = j.d(a.f[var1]);
                            a.H[a.A[var1]] = a.H[var1];
                            a.u[a.A[var1]] = a.e[a.A[var1]] - 20;
                            a.v[a.A[var1]] = a.e[a.A[var1]] + 20;
                            a.l[a.A[var1]] = a.C[a.A[var1]];
                            a.k[a.A[var1]] = -g.aF;
                            a.i[a.A[var1]] = 9;
                            a.j[a.A[var1]] = -5;
                        }
                        a.j[var1] = a.j[var1] > 4 ? -4 : -a.j[var1];
                        v87 = var1;
                        a.z[v87] = a.z[v87] + 1;
                    }
                    if (!j.a(a.e[var1], a.f[var1], 64) || a.f[var1] % j.f <= 8) break;
                    q.c(a.e[var1], j.d(a.f[var1]) + 8);
                    break;
                }
                case 9: {
                    v88 = var1 = var0_1;
                    a.f[v88] = a.f[v88] + a.j[var1];
                    v89 = var1;
                    a.j[v89] = a.j[v89] + 1;
                    a.B[var1] = 2;
                    switch (a.d[var1]) {
                        case 24: {
                            if (a.f[var1] <= a.M[a.J[var1]][a.H[var1]]) break block0;
                            if (g.as == 3 && a.m[var1] == 1) {
                                a.j[var1] = 0;
                                break;
                            }
                            a.i[var1] = 2;
                            break block0;
                        }
                        case 3: 
                        case 7: 
                        case 10: 
                        case 13: 
                        case 25: 
                        case 27: {
                            if (a.f[var1] <= a.M[a.J[var1]][a.H[var1]]) break block0;
                            if (g.as == 3 && a.m[var1] == 1) {
                                a.j[var1] = 0;
                                break;
                            }
                            a.i[var1] = 2;
                            break block0;
                        }
                        case 23: {
                            if (a.l[var1] < a.C[var1] >> 1) {
                                a.i[var1] = 1;
                                a.t[var1] = 1;
                                a.j[var1] = -8;
                                a.y[var1] = g.aF << 3;
                            }
                            if (a.f[var1] <= a.M[a.J[var1]][a.H[var1]]) break block0;
                            if (g.as == 3 && a.m[var1] == 1) {
                                a.j[var1] = 0;
                                break;
                            }
                            a.i[var1] = 2;
                            break block0;
                        }
                        default: {
                            if (a.f[var1] <= a.M[a.J[var1]][a.H[var1]]) break block0;
                            a.f[var1] = a.M[a.J[var1]][a.H[var1]];
                            if (g.as != 3 || a.m[var1] != 1) break;
                            a.j[var1] = 0;
                        }
                    }
                    a.i[var1] = 3;
                }
            }
            ++var0_1;
        }
    }

    private static void a(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 20;
            a.F[n5] = 40;
            a.G[n5] = 30;
            a.g[n5] = 16;
            a.h[n5] = 20;
            int n6 = n5;
            f[n6] = f[n6] + 5;
            a.k[n5] = 1;
            a.i[n5] = 5;
            a.j[n5] = n5 * 5;
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 60;
            ++n5;
        }
    }

    private static void b(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 2;
            a.F[n5] = 20;
            a.G[n5] = 10;
            a.g[n5] = 18;
            a.h[n5] = 18;
            a.k[n5] = 1;
            a.i[n5] = 5;
            a.j[n5] = n5 << 3;
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 0;
            ++n5;
        }
    }

    private static void c(int n2, int n3) {
        n2 = 0;
        while (n2 < c) {
            if (d[n2] == -1) break;
            ++n2;
        }
        if (n2 == c) {
            return;
        }
        int n4 = n2;
        while (n4 < n2 + n3) {
            a.J[n4] = 1;
            a.d[n4] = 9;
            a.F[n4] = 100;
            a.G[n4] = 10;
            a.g[n4] = 30;
            a.h[n4] = 20;
            a.k[n4] = 1;
            a.i[n4] = 5;
            a.j[n4] = n4 << 2;
            a.A[n4] = n4 + n3;
            a.l[n4] = a.C[n4] = o[d[n4]];
            a.D[n4] = a[d[n4]];
            a.I[n4] = p[d[n4]];
            a.E[n4] = 0;
            ++n4;
        }
    }

    private static void d(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 10;
            a.F[n5] = 80;
            a.G[n5] = 100;
            a.g[n5] = 24;
            a.h[n5] = 20;
            a.k[n5] = 1;
            a.i[n5] = 5;
            a.j[n5] = n5 * 5;
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 40;
            ++n5;
        }
    }

    private static void e(int n2, int n3) {
        n2 = 0;
        while (n2 < c) {
            if (d[n2] == -1) break;
            ++n2;
        }
        if (n2 == c) {
            return;
        }
        int n4 = n2;
        while (n4 < n2 + n3) {
            a.J[n4] = 1;
            a.d[n4] = 17;
            a.F[n4] = 36;
            a.G[n4] = 10;
            a.g[n4] = 20;
            a.h[n4] = 20;
            a.k[n4] = -1;
            a.i[n4] = 5;
            a.j[n4] = n4 + 60;
            a.l[n4] = a.C[n4] = o[d[n4]];
            a.D[n4] = a[d[n4]];
            a.I[n4] = p[d[n4]];
            a.E[n4] = 100;
            a.w[n4] = 2;
            a.x[n4] = 3;
            ++n4;
        }
    }

    private static void f(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 16;
            a.F[n5] = 24;
            a.G[n5] = 10;
            a.g[n5] = 20;
            a.h[n5] = 20;
            a.k[n5] = -1;
            a.i[n5] = 5;
            a.j[n5] = n5 + 60;
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 100;
            a.w[n5] = 2;
            a.x[n5] = 3;
            ++n5;
        }
    }

    private static void g(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 22;
            a.F[n5] = 32;
            a.G[n5] = 10;
            a.g[n5] = 24;
            a.h[n5] = 16;
            a.k[n5] = -1;
            a.i[n5] = 5;
            a.j[n5] = n5 + 60;
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 100;
            a.w[n5] = 2;
            a.x[n5] = 3;
            ++n5;
        }
    }

    private static void c() {
        L = new int[O.length][];
        M = new int[O.length][];
        N = new boolean[O.length][];
    }

    private static void e(int n2) {
        c = n2;
        s = 70;
        d = new int[c];
        J = new int[c];
        e = new int[c];
        f = new int[c];
        i = new int[c];
        u = new int[c];
        v = new int[c];
        w = new int[c];
        x = new int[c];
        j = new int[c];
        y = new int[c];
        z = new int[c];
        A = new int[c];
        B = new int[c];
        k = new int[c];
        l = new int[c];
        K = new boolean[c];
        C = new int[c];
        D = new int[c];
        E = new int[c];
        g = new int[c];
        h = new int[c];
        h = new int[c];
        t = new int[c];
        F = new int[c];
        G = new int[c];
        H = new int[c];
        I = new int[c];
        m = new int[c];
        n2 = 0;
        while (n2 < c) {
            a.d[n2] = -1;
            a.m[n2] = -1;
            ++n2;
        }
    }

    private static void h(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 1;
            a.F[n5] = 25;
            a.G[n5] = 10;
            a.g[n5] = 27;
            a.h[n5] = 15;
            a.k[n5] = 1;
            a.i[n5] = 5;
            a.j[n5] = n5 * 7;
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 0;
            ++n5;
        }
    }

    private static void i(int n2, int n3) {
        n2 = 0;
        while (n2 < c) {
            if (d[n2] == -1) break;
            ++n2;
        }
        if (n2 == c) {
            return;
        }
        int n4 = n2;
        while (n4 < n2 + n3) {
            a.J[n4] = 2;
            a.d[n4] = 11;
            a.F[n4] = 36;
            a.G[n4] = 20;
            a.g[n4] = 34;
            a.h[n4] = 32;
            a.k[n4] = 1;
            a.i[n4] = 5;
            a.j[n4] = n4 * 6;
            a.l[n4] = a.C[n4] = o[d[n4]];
            a.D[n4] = a[d[n4]];
            a.I[n4] = p[d[n4]];
            a.E[n4] = 100;
            ++n4;
        }
    }

    private static void j(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 3;
            a.F[n5] = 60;
            a.G[n5] = 60;
            a.g[n5] = 16;
            a.h[n5] = 16;
            a.k[n5] = 1;
            a.i[n5] = 5;
            a.j[n5] = s;
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 100;
            a.w[n5] = 2;
            a.x[n5] = 3;
            ++n5;
        }
    }

    private static void k(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 27;
            a.F[n5] = 60;
            a.G[n5] = 60;
            a.g[n5] = 16;
            a.h[n5] = 16;
            a.k[n5] = 1;
            a.i[n5] = 5;
            a.j[n5] = s;
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 100;
            a.w[n5] = 2;
            a.x[n5] = 3;
            ++n5;
        }
    }

    private static void l(int n2, int n3) {
        n2 = 0;
        while (n2 < c) {
            if (d[n2] == -1) break;
            ++n2;
        }
        if (n2 == c) {
            return;
        }
        int n4 = n2;
        while (n4 < n2 + n3) {
            a.J[n4] = 0;
            a.d[n4] = 15;
            a.F[n4] = 20;
            a.G[n4] = 20;
            a.g[n4] = 16;
            a.h[n4] = 15;
            a.k[n4] = 1;
            a.i[n4] = 5;
            a.j[n4] = n4 << 1;
            a.l[n4] = a.C[n4] = o[d[n4]];
            a.D[n4] = a[d[n4]];
            a.I[n4] = p[d[n4]];
            a.E[n4] = 0;
            ++n4;
        }
    }

    private static void m(int n2, int n3) {
        n2 = 0;
        while (n2 < c) {
            if (d[n2] == -1) break;
            ++n2;
        }
        if (n2 == c) {
            return;
        }
        int n4 = n2;
        while (n4 < n2 + n3) {
            a.J[n4] = 0;
            a.d[n4] = 0;
            a.F[n4] = 7;
            a.G[n4] = 10;
            a.g[n4] = 16;
            a.h[n4] = 15;
            a.k[n4] = 1;
            a.i[n4] = 5;
            a.j[n4] = n4 << 3;
            a.l[n4] = a.C[n4] = o[d[n4]];
            a.D[n4] = a[d[n4]];
            a.I[n4] = p[d[n4]];
            a.E[n4] = 100;
            ++n4;
        }
    }

    private static void n(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 19;
            a.F[n5] = 80;
            a.G[n5] = 100;
            a.g[n5] = 22;
            a.h[n5] = 16;
            a.k[n5] = 1;
            a.i[n5] = 5;
            a.j[n5] = 62;
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 70;
            ++n5;
        }
    }

    private static void o(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 6;
            a.F[n5] = 80;
            a.G[n5] = 10;
            a.g[n5] = 22;
            a.h[n5] = 24;
            a.k[n5] = 1;
            a.i[n5] = 5;
            a.j[n5] = 50 + (n5 << 1);
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 500;
            ++n5;
        }
    }

    private static void p(int n2, int n3) {
        n2 = 0;
        while (n2 < c) {
            if (d[n2] == -1) break;
            ++n2;
        }
        if (n2 == c) {
            return;
        }
        int n4 = n2;
        while (n4 < n2 + n3) {
            a.J[n4] = 0;
            a.d[n4] = 12;
            a.F[n4] = 7;
            a.G[n4] = 10;
            a.g[n4] = 16;
            a.h[n4] = 15;
            a.k[n4] = 1;
            a.i[n4] = 5;
            a.j[n4] = n4 << 3;
            a.l[n4] = a.C[n4] = o[d[n4]];
            a.D[n4] = a[d[n4]];
            a.I[n4] = p[d[n4]];
            a.E[n4] = 100;
            ++n4;
        }
    }

    private static void d() {
        int n2 = 0;
        while (n2 < O.length) {
            a.N[n2] = new boolean[L[n2].length];
            ++n2;
        }
        n2 = 0;
        while (n2 < L.length) {
            int n3 = 0;
            while (n3 < L[n2].length) {
                a.L[n2][n3] = L[n2][n3] * j.f + 12;
                a.M[n2][n3] = M[n2][n3] * j.f;
                if (n2 == 8) {
                    int[] nArray = M[n2];
                    int n4 = n3;
                    nArray[n4] = nArray[n4] - 12;
                }
                ++n3;
            }
            ++n2;
        }
    }

    private static void q(int n2, int n3) {
        int n4 = 0;
        while (n4 < c) {
            if (d[n4] == -1) break;
            ++n4;
        }
        if (n4 == c) {
            return;
        }
        int n5 = n4;
        while (n5 < n4 + n3) {
            a.J[n5] = n2;
            a.d[n5] = 5;
            a.F[n5] = 36;
            a.G[n5] = 20;
            a.g[n5] = 22;
            a.h[n5] = 24;
            a.k[n5] = 1;
            a.i[n5] = 5;
            a.j[n5] = n5 * 6;
            a.l[n5] = a.C[n5] = o[d[n5]];
            a.D[n5] = a[d[n5]];
            a.I[n5] = p[d[n5]];
            a.E[n5] = 100;
            ++n5;
        }
    }

    private static void f(int n2) {
        int n3 = n2;
        j[n3] = j[n3] + 1;
        int n4 = n2;
        e[n4] = e[n4] - (w[n2] >> 3);
        int n5 = n2;
        f[n5] = f[n5] - (x[n2] >> 3);
        if (w[n2] == 0) {
            a.w[n2] = 2;
        }
        if (x[n2] == 0) {
            a.x[n2] = 2;
        }
        if (j[n2] == 5) {
            a.i[n2] = 2;
            a.j[n2] = 0;
            a.x[n2] = 0;
            a.w[n2] = 0;
            a.E[n2] = 10;
        }
    }

    private static void g(int n2) {
        int n3 = n2;
        e[n3] = e[n3] - k[n2];
        a.B[n2] = 2;
        int n4 = n2;
        j[n4] = j[n4] + 1;
        if (j[n2] == 6) {
            a.f[n2] = M[J[n2]][H[n2]];
            a.i[n2] = 4;
            a.j[n2] = 4;
            int n5 = n2;
            e[n5] = e[n5] + (k[n2] << 3);
        }
    }

    private static void h(int n2) {
        int n3 = n2;
        j[n3] = j[n3] + 1;
        if (j[n2] == 2) {
            if (K[n2]) {
                a.K[n2] = false;
                g.b(D[n2], k[n2]);
            } else {
                g.a(D[n2], k[n2]);
            }
        }
        if ((j[n2] >> 2) % 2 == 0) {
            int n4 = n2;
            e[n4] = e[n4] + k[n2];
            a.B[n2] = 3;
        } else {
            a.B[n2] = 0;
            int n5 = n2;
            e[n5] = e[n5] - k[n2];
        }
        if (j[n2] == 12) {
            a.i[n2] = 3;
            a.j[n2] = 0;
        }
    }

    private static void i(int n2) {
        int n3 = n2;
        j[n3] = j[n3] + 1;
        if (j[n2] < 6) {
            int n4 = n2;
            e[n4] = e[n4] + (k[n2] << 2);
        } else if (j[n2] == 6) {
            if (q.a(e[n2] + (k[n2] << 3) - g.at) < 13 + g.aB) {
                g.a(D[n2], k[n2]);
            }
        } else if (j[n2] < 12) {
            int n5 = n2;
            e[n5] = e[n5] - (k[n2] << 1);
        } else if (j[n2] == 12) {
            a.f[n2] = M[J[n2]][H[n2]];
            a.i[n2] = 3;
            a.y[n2] = -4;
            a.j[n2] = 0;
        }
        a.B[n2] = 1;
        if (j[n2] > 3 && j[n2] < 9 && j[n2] % 2 == 0) {
            a.B[n2] = 3;
        }
    }

    private static void j(int n2) {
        if (q.at % 4 == 0) {
            int n3 = n2;
            B[n3] = B[n3] + 1;
            if (B[n2] > 1) {
                a.B[n2] = 0;
            }
            int n4 = n2;
            e[n4] = e[n4] + k[n2];
        }
        if (e[n2] > v[n2]) {
            a.k[n2] = -1;
        } else if (e[n2] < u[n2]) {
            a.k[n2] = 1;
        }
        a.b(n2);
        if (q.at % 9 == 0) {
            a.c(n2);
        }
    }

    private static void k(int n2) {
        if (q.at % 4 == 1) {
            int n3 = n2;
            B[n3] = B[n3] + 1;
            if (B[n2] > 1) {
                a.B[n2] = 0;
            }
            int n4 = n2;
            e[n4] = e[n4] + (k[n2] << 1);
            if (e[n2] > v[n2]) {
                a.k[n2] = -1;
            } else if (e[n2] < u[n2]) {
                a.k[n2] = 1;
            }
        }
        a.b(n2);
    }

    private static void l(int n2) {
        if (e[n2] < g.at) {
            a.w[n2] = (q.at + n2) % 3 + 3;
            if (q.a(e[n2] - g.at) > 20) {
                a.k[n2] = 1;
            }
        } else if (e[n2] > g.at) {
            a.w[n2] = -(q.at - n2) % 3 - 3;
            if (q.a(e[n2] - g.at) > 20) {
                a.k[n2] = -1;
            }
        }
        if ((q.at + n2) % 2 == 0) {
            if (f[n2] < g.au) {
                a.x[n2] = q.at % 3 + 3;
            } else if (f[n2] > g.au) {
                a.x[n2] = -q.at % 3 - 3;
            }
        }
        int n3 = n2;
        e[n3] = e[n3] + w[n2];
        int n4 = n2;
        f[n4] = f[n4] + x[n2];
        a.B[n2] = B[n2] == 1 ? 3 : 1;
        if (q.a(e[n2] - g.at) < 10 && q.a(f[n2] - g.au) < 10) {
            a.E[n2] = 0;
            if (K[n2]) {
                a.K[n2] = false;
                g.b(D[n2], k[n2]);
            } else {
                g.a(D[n2], k[n2]);
            }
            a.j[n2] = 0;
            a.i[n2] = 2;
        }
    }

    private static void m(int n2) {
        int n3 = n2;
        j[n3] = j[n3] + 1;
        int n4 = n2;
        e[n4] = e[n4] - w[n2];
        int n5 = n2;
        f[n5] = f[n5] - x[n2];
        if (w[n2] == 0) {
            a.w[n2] = 2;
        }
        if (x[n2] == 0) {
            a.x[n2] = 2;
        }
        if (j[n2] == 5) {
            a.i[n2] = 2;
            a.j[n2] = 0;
            a.x[n2] = 0;
            a.w[n2] = 0;
            a.E[n2] = 0;
        }
        a.B[n2] = 2;
    }

    private static void n(int n2) {
        int n3 = n2;
        e[n3] = e[n3] + w[n2];
        int n4 = n2;
        f[n4] = f[n4] + x[n2];
        if (e[n2] < 0) {
            a.e[n2] = 0;
        }
        if (e[n2] > j.c - 10) {
            a.e[n2] = j.c - 10;
        }
        if (f[n2] < 0) {
            a.f[n2] = 0;
        }
        if (f[n2] > j.d - 20) {
            a.f[n2] = j.d - 20;
        }
        if (e[n2] - u[n2] > 40) {
            a.w[n2] = -(q.at % 2 + 1);
        }
        if (e[n2] - u[n2] < -40) {
            a.w[n2] = q.at % 2 + 1;
        }
        if (f[n2] - v[n2] > 40) {
            a.x[n2] = -(q.at % 2 + 1);
        }
        if (f[n2] - v[n2] < -40) {
            a.x[n2] = q.at % 2 + 1;
        }
        if (q.at % (n2 + 31) == 0) {
            if (q.a(e[n2] - g.at) > 40 && j.a(e[n2], f[n2] + 6, 2) && q.at % 2 == 0) {
                a.i[n2] = 3;
                a.k[n2] = g.at > e[n2] ? 1 : -1;
                a.j[n2] = 0;
                a.f[n2] = j.d(f[n2] + 6);
                return;
            }
            a.w[n2] = (q.at + n2) % 3 - 1;
            a.x[n2] = (q.at >> 1) % 3 - 1;
        }
        a.B[n2] = B[n2] == 1 ? 3 : 1;
        a.b(n2);
        if (q.at % 5 == 0) {
            a.c(n2);
        }
    }

    private static void o(int n2) {
        int n3 = n2;
        j[n3] = j[n3] + 1;
        int n4 = n2;
        e[n4] = e[n4] - k[n2];
        if (j[n2] == 5) {
            a.i[n2] = 2;
        }
    }

    private static void p(int n2) {
        int n3 = n2;
        j[n3] = j[n3] + 1;
        if (j[n2] < 6) {
            int n4 = n2;
            e[n4] = e[n4] + (k[n2] << 2);
        } else if (j[n2] == 6) {
            if (q.a(e[n2] + (k[n2] << 3) - g.at) < 13 + g.aB) {
                g.a(D[n2], k[n2]);
            }
        } else if (j[n2] < 12) {
            int n5 = n2;
            e[n5] = e[n5] - (k[n2] << 1);
        } else if (j[n2] == 12) {
            a.f[n2] = M[J[n2]][H[n2]];
            a.i[n2] = 3;
            a.y[n2] = -4;
            a.j[n2] = 0;
        }
        a.B[n2] = 3;
    }

    private static void q(int n2) {
        int n3 = n2;
        j[n3] = j[n3] + 1;
        if (j[n2] == 3) {
            a.i[n2] = 2;
            a.j[n2] = 1;
        }
        a.B[n2] = 2;
    }

    private static void r(int n2) {
        if ((q.at + n2) % 4 == 0) {
            int n3 = n2;
            B[n3] = B[n3] + 1;
            if (B[n2] > 1) {
                a.B[n2] = 0;
            }
            int n4 = n2;
            e[n4] = e[n4] + k[n2];
        }
        if (e[n2] > v[n2]) {
            a.k[n2] = -1;
        } else if (e[n2] < u[n2]) {
            a.k[n2] = 1;
        }
        a.b(n2);
        if (q.at % 5 == 0) {
            a.c(n2);
        }
    }
}


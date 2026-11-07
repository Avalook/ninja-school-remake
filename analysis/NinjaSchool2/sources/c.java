/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class c {
    public static Image[][] a;
    public static Image b;
    public static int c;
    public static int[] d;
    public static int[] e;
    public static int[] f;
    public static final int[] g;
    public static final int[] h;
    public static final int[] i;
    public static int j;
    public static int k;
    public static int l;
    public static int m;

    static {
        int[] nArray = new int[5];
        nArray[1] = -1;
        nArray[2] = -1;
        nArray[3] = 5;
        nArray[4] = 15;
        g = nArray;
        int[] nArray2 = new int[5];
        nArray2[0] = 10;
        nArray2[3] = 20;
        nArray2[4] = 20;
        h = nArray2;
        int[] nArray3 = new int[14];
        nArray3[0] = 7;
        nArray3[1] = 6;
        nArray3[2] = 6;
        nArray3[3] = 5;
        nArray3[4] = 2;
        nArray3[5] = 4;
        nArray3[8] = 5;
        nArray3[9] = -36;
        nArray3[10] = 5;
        nArray3[12] = 9;
        nArray3[13] = 5;
        i = nArray3;
        j = -1;
    }

    public static void a() {
        a = null;
        b = null;
        System.gc();
    }

    public static void a(int n2) {
        switch (f[n2]) {
            case 0: {
                int n3 = n2;
                if (g.C && !q.t) {
                    q.a(l.l[14], -1, d[n3], e[n3] - 40);
                    break;
                }
                if (g.i) break;
                if (q.d) {
                    q.a(l.l[13], -1, d[n3], e[n3] - 40);
                    break;
                }
                if (g.bc == 2) {
                    if (g.bm && g.bn == 0) {
                        if (g.aW >= 6) {
                            q.a(l.l[11], 8, d[n3], e[n3] - 40);
                            break;
                        }
                        q.a(l.l[10], -1, d[n3], e[n3] - 40);
                        break;
                    }
                    q.a(l.l[12], -1, d[n3], e[n3] - 40);
                    break;
                }
                if (g.f && !g.g) {
                    if (g.bc == 1) {
                        if (g.O && g.Q == 0) {
                            q.a(l.l[9], 6, d[n3], e[n3] - 40);
                            break;
                        }
                        q.a(l.l[8], -1, d[n3], e[n3] - 40);
                        break;
                    }
                    if (g.bc == -1) {
                        q.a(l.l[7], 5, d[n3], e[n3] - 40);
                        break;
                    }
                    q.a(l.l[6], 1, d[n3], e[n3] - 40);
                    break;
                }
                if (!g.f) {
                    q.a(l.l[0], -1, d[n3], e[n3] - 40);
                    break;
                }
                q.a(l.n[1], -1, d[n3], e[n3] - 40);
                break;
            }
            case 1: {
                int n4 = n2;
                if (!g.f) {
                    if (g.bc == 0) {
                        if (g.bh < h[0]) {
                            q.a(l.l[4], -1, d[n4], e[n4] - 40);
                            break;
                        }
                        boolean bl = false;
                        j = q.as;
                        m = 0;
                        k = d[0];
                        l = e[0] - 44;
                        q.a(l.l[5], 4, d[n4], e[n4] - 40);
                        break;
                    }
                    if (g.bc == -1) {
                        q.a(l.l[3], 3, d[n4], e[n4] - 40);
                        break;
                    }
                    q.a(l.l[6], 1, d[n4], e[n4] - 40);
                    break;
                }
                q.a(l.n[0], -1, d[n4], e[n4] - 40);
                break;
            }
            case 2: {
                int n5 = n2;
                q.a(l.n[3], -1, d[n5], e[n5] - 40);
                break;
            }
            case 3: {
                int n6 = n2;
                if (!g.f) {
                    q.a(l.l[1], -1, d[n6], e[n6] - 40);
                    break;
                }
                q.a(l.n[4], -1, d[n6], e[n6] - 40);
                break;
            }
            case 4: {
                int n7 = n2;
                if (!g.f) {
                    q.a(l.l[2], -1, d[n7], e[n7] - 40);
                    break;
                }
                q.a(l.n[2], -1, d[n7], e[n7] - 40);
                break;
            }
            case 5: {
                int n8 = n2;
                if (q.as == 0 && n8 == 3) {
                    q.v();
                    q.K = 9;
                    q.bp = 0;
                }
                if (q.as == 7 && n8 == 0) {
                    q.v();
                    q.K = 9;
                    q.bp = 1;
                }
                if (q.as == 3 && n8 == 0) {
                    q.a(l.m[0], -1, d[n8], e[n8] - 40);
                }
                if (q.as != 14 || n8 != 1) break;
                q.v();
                q.K = 9;
                q.bp = 0;
                break;
            }
            case 6: {
                int n9 = n2;
                if (g.o) break;
                if (g.bh == 0) {
                    q.a(l.k[0], -1, d[n9], e[n9] - 40);
                    break;
                }
                if (!g.j && g.bh != -1 && g.bh < h[g.bc]) {
                    q.a(l.k[1], -1, d[n9], e[n9] - 40);
                    break;
                }
                if (!g.k && g.bh != -1 && g.bh >= h[g.bc]) {
                    g.j = true;
                    g.a = 0;
                    q.a(l.k[2], 9, d[n9], e[n9] - 40);
                    g.bh = -1;
                    g.bc = -1;
                    break;
                }
                if (g.j && !g.k) {
                    q.a(l.k[3], -1, d[n9], e[n9] - 40);
                    break;
                }
                if (!g.k || g.m) break;
                if (g.aW >= 10) {
                    q.a(l.k[4], 10, d[n9], e[n9] - 40);
                    g.m = true;
                    g.a = 1;
                    break;
                }
                q.a(l.k[5], -1, d[n9], e[n9] - 40);
                g.a = 8;
                break;
            }
            case 7: {
                int n10 = n2;
                if (q.as != 8 || n10 != 0) break;
                q.v();
                q.K = 9;
                q.bp = 0;
                break;
            }
            case 8: {
                int n11 = n2;
                if (q.as != 9 || n11 != 1 || !g.o) break;
                if (!g.r) {
                    q.a(l.h[0], -1, d[n11], e[n11] - 40);
                    q.av = 1;
                    q.bi = 2;
                    q.f();
                    q.a(2184, 120);
                    q.b(2184, 91);
                }
                if (!g.s || g.C) break;
                q.a(l.h[2], -1, d[n11], e[n11] - 40);
                break;
            }
            case 9: {
                int n12 = n2;
                if (g.D == 1 && !g.v) {
                    q.a(l.g[1], -1, d[n12], e[n12] - 40);
                    g.v = true;
                    g.a = 6;
                    break;
                }
                if (g.D == 2 && !g.w) {
                    q.a(l.g[2], -1, d[n12], e[n12] - 40);
                    g.w = true;
                    g.a = 7;
                    break;
                }
                if (g.D >= 1 && (g.D != 1 || g.w) && (g.D != 2 || g.x)) break;
                q.a(l.g[0], -1, d[n12], e[n12] - 40);
                break;
            }
            case 10: {
                int n13 = n2;
                if (g.C) break;
                if (!g.A) {
                    q.a(l.d[0], -1, d[n13], e[n13] - 40);
                }
                if (!g.u || g.C) break;
                q.a(l.d[1], -1, d[n13], e[n13] - 40);
                break;
            }
            case 11: {
                int n14 = n2;
                if (g.C) break;
                if (!g.z) {
                    q.a(l.e[0], -1, d[n14], e[n14] - 40);
                    break;
                }
                if (g.z && !g.A) {
                    q.a(l.e[1], 12, d[n14], e[n14] - 40);
                    break;
                }
                if (!g.A) break;
                q.a(l.e[2], -1, d[n14], e[n14] - 40);
                break;
            }
            case 12: {
                int n15 = n2;
                if (q.as == 0 && n15 == 4) {
                    if (g.aW == 30 && !g.j) {
                        q.a(l.l[15], -1, d[n15], e[n15] - 40);
                    } else {
                        q.v();
                        q.K = 9;
                        q.bp = 1;
                    }
                }
                if (q.as != 7 && q.as != 12 || n15 != 0) break;
                q.v();
                q.K = 9;
                q.bp = 1;
            }
        }
        if (q.as == q.aE && n2 == q.aF) {
            q.aE = -1;
        }
    }

    public static void b(int n2) {
        c = 0;
        if (n2 == 3) {
            a = new Image[6][2];
            try {
                c.a[5][0] = Image.createImage((String)"/npc/50.png");
                c.a[5][1] = Image.createImage((String)"/npc/51.png");
            }
            catch (Exception exception) {}
            c = 1;
            c.b();
            c.a(0, 5, 23, 5);
        }
        if (n2 == 9) {
            if (!g.C) {
                a = new Image[9][2];
                try {
                    c.a[2][0] = Image.createImage((String)"/npc/20.png");
                    c.a[2][1] = Image.createImage((String)"/npc/21.png");
                    c.a[8][0] = Image.createImage((String)"/npc/70.png");
                    c.a[8][1] = Image.createImage((String)"/npc/71.png");
                    if (!g.s) {
                        b = Image.createImage((String)"/i/t.png");
                    }
                }
                catch (Exception exception) {}
                c = 2;
                c.b();
                c.a(0, 2, 102, 16);
                c.a(1, 8, 71, 15);
            } else {
                a = new Image[14][2];
                try {
                    c.a[2][0] = Image.createImage((String)"/npc/20.png");
                    c.a[2][1] = Image.createImage((String)"/npc/21.png");
                    c.a[8][0] = Image.createImage((String)"/npc/70.png");
                    c.a[8][1] = Image.createImage((String)"/npc/71.png");
                    c.a[0][0] = Image.createImage((String)"/npc/00.png");
                    c.a[0][1] = Image.createImage((String)"/npc/01.png");
                    c.a[13][0] = Image.createImage((String)"/npc/120.png");
                    c.a[13][1] = Image.createImage((String)"/npc/121.png");
                }
                catch (Exception exception) {}
                c = 4;
                c.b();
                c.a(0, 2, 102, 16);
                c.a(1, 8, 75, 15);
                c.a(2, 0, 50, 15);
                c.a(3, 13, 51, 15);
            }
        }
        if (n2 == 14) {
            a = new Image[10][2];
            try {
                c.a[9][0] = Image.createImage((String)"/npc/80.png");
                c.a[9][1] = Image.createImage((String)"/npc/81.png");
                c.a[5][0] = Image.createImage((String)"/npc/50.png");
                c.a[5][1] = Image.createImage((String)"/npc/51.png");
            }
            catch (Exception exception) {}
            c = 2;
            c.b();
            c.a(0, 9, 108, 17);
            c.a(1, 5, 36, 14);
        }
        if (n2 == 15 && !g.C) {
            a = new Image[11][2];
            try {
                if (!g.u) {
                    c.a[10][0] = Image.createImage((String)"/npc/90.png");
                    c.a[10][1] = Image.createImage((String)"/npc/91.png");
                } else {
                    c.a[10][0] = Image.createImage((String)"/npc/100.png");
                    c.a[10][1] = Image.createImage((String)"/npc/101.png");
                    c.i[10] = 0;
                }
            }
            catch (Exception exception) {}
            c = 1;
            c.b();
            c.a(0, 10, 72, 8);
        }
        if (n2 == 16 && !g.C && !g.u) {
            a = new Image[12][2];
            try {
                c.a[11][0] = Image.createImage((String)"/npc/100.png");
                c.a[11][1] = Image.createImage((String)"/npc/101.png");
            }
            catch (Exception exception) {}
            c = 1;
            c.b();
            c.a(0, 11, 21, 9);
            q.bi = 6;
            q.f();
            q.a(527, 201);
        }
        if (n2 == 99) {
            a = new Image[6][2];
            try {
                c.a[4][0] = Image.createImage((String)"/npc/40.png");
                c.a[4][1] = Image.createImage((String)"/npc/41.png");
            }
            catch (Exception exception) {}
            c = 1;
            c.b();
            c.a(0, 4, 46, 10);
        }
        if (n2 == 4) {
            a = new Image[7][2];
            try {
                c.a[6][0] = Image.createImage((String)"/npc/60.png");
                c.a[6][1] = Image.createImage((String)"/npc/61.png");
            }
            catch (IOException iOException) {}
            c = 1;
            c.b();
            c.a(0, 6, 22, 9);
        }
        if (n2 == 8) {
            a = new Image[8][1];
            try {
                c.a[7][0] = Image.createImage((String)"/t/k0.png");
            }
            catch (IOException iOException) {}
            c = 1;
            c.b();
            c.a(0, 7, 87, 7);
        }
        if (n2 == 7) {
            a = new Image[6][2];
            try {
                c.a[5][0] = Image.createImage((String)"/npc/50.png");
                c.a[5][1] = Image.createImage((String)"/npc/51.png");
            }
            catch (IOException iOException) {}
            c = 1;
            c.b();
            c.a(0, 5, 70, 9);
        }
        if (n2 == 12) {
            a = new Image[13][2];
            try {
                c.a[12][0] = Image.createImage((String)"/npc/110.png");
                c.a[12][1] = Image.createImage((String)"/npc/111.png");
            }
            catch (IOException iOException) {}
            c = 1;
            c.b();
            c.a(0, 12, 15, 8);
        }
        if (n2 == 0) {
            if (q.f) {
                a = new Image[6][2];
                try {
                    c.a[0][0] = Image.createImage((String)"/npc/00.png");
                    c.a[0][1] = Image.createImage((String)"/npc/01.png");
                    c.a[4][0] = Image.createImage((String)"/npc/40.png");
                    c.a[4][1] = Image.createImage((String)"/npc/41.png");
                    c.a[1][0] = Image.createImage((String)"/npc/10.png");
                    c.a[1][1] = Image.createImage((String)"/npc/11.png");
                }
                catch (Exception exception) {}
                c = 3;
                c.b();
                c.a(0, 0, 19, 17);
                c.a(1, 4, 17, 17);
                c.a(2, 1, 41, 16);
                return;
            }
            a = new Image[13][2];
            try {
                if (!g.C) {
                    c.a[0][0] = Image.createImage((String)"/npc/00.png");
                    c.a[0][1] = Image.createImage((String)"/npc/01.png");
                }
                c.a[3][0] = Image.createImage((String)"/npc/30.png");
                c.a[3][1] = Image.createImage((String)"/npc/31.png");
                c.a[1][0] = Image.createImage((String)"/npc/10.png");
                c.a[1][1] = Image.createImage((String)"/npc/11.png");
                c.a[5][0] = Image.createImage((String)"/npc/50.png");
                c.a[5][1] = Image.createImage((String)"/npc/51.png");
                c.a[12][0] = Image.createImage((String)"/npc/110.png");
                c.a[12][1] = Image.createImage((String)"/npc/111.png");
            }
            catch (Exception exception) {}
            c = 5;
            c.b();
            if (!g.C) {
                c.a(0, 0, 34, 13);
            }
            c.a(1, 3, 35, 13);
            c.a(2, 1, 43, 17);
            c.a(3, 5, 25, 17);
            c.a(4, 12, 11, 17);
        }
    }

    private static void a(int n2, int n3, int n4, int n5) {
        c.f[n2] = n3;
        c.d[n2] = n4 * j.f + 12;
        c.e[n2] = n5 * j.f;
    }

    private static void b() {
        d = new int[c];
        e = new int[c];
        f = new int[c];
    }

    public static void a(Graphics graphics) {
        int n2 = 0;
        while (n2 < c) {
            int n3 = n2;
            Graphics graphics2 = graphics;
            if (d[n3] + 10 >= q.ay && d[n3] - 10 <= q.ay + q.ab && e[n3] >= q.az && e[n3] - 32 <= q.az + q.ac) {
                if (f[n3] != 7) {
                    graphics2.drawImage(a[f[n3]][1], d[n3], e[n3], 33);
                    graphics2.drawImage(a[f[n3]][0], d[n3], e[n3] - i[f[n3]] + (q.at % (15 + f[n3]) > 4 ? 0 : 1), 33);
                } else {
                    graphics2.drawImage(a[f[n3]][0], d[n3] + 6, e[n3] - 12 - i[f[n3]] + (q.at % (15 + f[n3]) > 4 ? 0 : 1), 33);
                }
            }
            ++n2;
        }
        if (q.as == 9 && b != null) {
            graphics.drawImage(b, d[1] - 2, e[1] - 6 + (q.at % (15 + f[1]) > 4 ? 0 : 1), 33);
        }
    }
}


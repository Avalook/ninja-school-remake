/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class f {
    public static int a = -1;
    static int b;
    static int c;
    static int d;
    static int e;
    private static int p;
    private static int q;
    static int f;
    private static int r;
    static int g;
    static int h;
    static int i;
    static int j;
    static int k;
    static int l;
    static int m;
    private static int s;
    private static Image[] t;
    private static int u;
    static int n;
    private static int v;
    private static int[][] w;
    private static int[][] x;
    private static int[] y;
    private static int[] z;
    public static boolean o;

    static {
        f = 22;
        int[] nArray = new int[9];
        nArray[0] = 2;
        nArray[1] = 2;
        nArray[2] = 2;
        nArray[6] = 3;
        nArray[7] = 3;
        nArray[8] = 3;
        y = nArray;
        z = new int[]{4, 4, 5, 5, 5, 5};
    }

    public static void a(int n2) {
        o = false;
        if (p.h < 6) {
            return;
        }
        if (a > -1 && (n2 == 2 || n2 == 22)) {
            f = n2;
        }
        if (f != n2) {
            return;
        }
        v = 0;
        r = 220;
        g = 5000;
        h = 5000;
        u = 36;
        n = 32;
        i = -1;
        p = 120;
        q = 408;
        t = new Image[14];
        n2 = 0;
        while (n2 < 13) {
            f.t[n2] = q.c("/mob/cho" + n2 + ".png");
            ++n2;
        }
        f.t[13] = q.c("/cp/cb0.png");
        b = 11 * k.f + 12;
        c = 13 * k.f;
        if (a > -1) {
            b = h.ag - h.as * 10;
            c = h.ah;
            i = h.as;
        }
        j = 3;
        o = true;
        int[][] nArrayArray = new int[9][];
        int[] nArray = new int[10];
        nArray[0] = 13;
        nArray[1] = -7;
        nArray[2] = -10;
        nArray[3] = 9;
        nArray[4] = -16;
        nArray[5] = -6;
        nArray[6] = -6;
        nArray[9] = -8;
        nArrayArray[0] = nArray;
        int[] nArray2 = new int[10];
        nArray2[0] = 13;
        nArray2[1] = -7;
        nArray2[2] = -10;
        nArray2[3] = 9;
        nArray2[4] = -16;
        nArray2[5] = -6;
        nArray2[6] = -6;
        nArray2[9] = -9;
        nArrayArray[1] = nArray2;
        int[] nArray3 = new int[10];
        nArray3[1] = -7;
        nArray3[4] = -25;
        nArray3[6] = -3;
        nArray3[9] = -10;
        nArrayArray[2] = nArray3;
        int[] nArray4 = new int[10];
        nArray4[1] = -7;
        nArray4[4] = -27;
        nArray4[6] = -1;
        nArray4[9] = -10;
        nArrayArray[3] = nArray4;
        int[] nArray5 = new int[10];
        nArray5[0] = 13;
        nArray5[1] = -7;
        nArray5[2] = -9;
        nArray5[3] = 10;
        nArray5[4] = -16;
        nArray5[5] = -6;
        nArray5[6] = -6;
        nArray5[9] = -9;
        nArrayArray[4] = nArray5;
        int[] nArray6 = new int[10];
        nArray6[3] = 5;
        nArray6[4] = -17;
        nArray6[6] = -6;
        nArray6[9] = -19;
        nArrayArray[5] = nArray6;
        int[] nArray7 = new int[10];
        nArray7[0] = 12;
        nArray7[3] = 10;
        nArray7[4] = -12;
        nArray7[5] = -6;
        nArrayArray[6] = nArray7;
        int[] nArray8 = new int[10];
        nArray8[3] = 5;
        nArray8[4] = -16;
        nArray8[6] = -6;
        nArray8[9] = -21;
        nArrayArray[7] = nArray8;
        int[] nArray9 = new int[10];
        nArray9[3] = 5;
        nArray9[4] = -19;
        nArray9[6] = -6;
        nArray9[9] = -5;
        nArrayArray[8] = nArray9;
        w = nArrayArray;
        int[][] nArrayArray2 = new int[9][];
        int[] nArray10 = new int[5];
        nArray10[0] = 5;
        nArray10[1] = 3;
        nArray10[2] = 8;
        nArray10[3] = 9;
        nArrayArray2[0] = nArray10;
        int[] nArray11 = new int[5];
        nArray11[0] = 5;
        nArray11[1] = 3;
        nArray11[2] = 8;
        nArray11[3] = 9;
        nArrayArray2[1] = nArray11;
        int[] nArray12 = new int[5];
        nArray12[0] = 13;
        nArray12[1] = 6;
        nArray12[2] = 13;
        nArray12[3] = 13;
        nArrayArray2[2] = nArray12;
        int[] nArray13 = new int[5];
        nArray13[0] = 13;
        nArray13[1] = 7;
        nArray13[2] = 13;
        nArray13[3] = 13;
        nArrayArray2[3] = nArray13;
        nArrayArray2[4] = new int[]{5, 3, 8, 9, 10};
        nArrayArray2[5] = new int[]{13, 4, 13, 9, 1};
        nArrayArray2[6] = new int[]{5, 13, 13, 9, 2};
        nArrayArray2[7] = new int[]{11, 13, 13, 13, 1};
        int[] nArray14 = new int[5];
        nArray14[0] = 12;
        nArray14[1] = 13;
        nArray14[2] = 13;
        nArray14[3] = 13;
        nArrayArray2[8] = nArray14;
        x = nArrayArray2;
    }

    public static void a() {
        if (!o) {
            return;
        }
        block0 : switch (j) {
            case 1: {
                v = 0;
                k = 6;
                if (l != 0) break;
                q.a("\u0110\u00e3 thu\u1ea7n ph\u1ee5c \u0111\u01b0\u1ee3c ch\u00f3 tuy\u1ebft");
                p.h = 6;
                p.g = true;
                l = 0;
                if (!k.a(b, c, 2)) {
                    j = 11;
                    d = 0;
                    e = 5;
                    return;
                }
                j = 3;
                return;
            }
            case 2: {
                switch (a) {
                    case -1: {
                        if (q.a(h.ag - b) > 20) {
                            int n2 = i = b > h.ag ? -1 : 1;
                        }
                        if (h.ag < p || h.ag > q) {
                            if (q.a(b - 240) < 10) {
                                k = 0;
                                l = 0;
                                j = 3;
                                return;
                            }
                            if (b > 240) {
                                i = -1;
                            }
                            if (b < 240) {
                                i = 1;
                            }
                        }
                        if (!k.a(b, c, 2)) {
                            j = 11;
                            d = 0;
                            e = 5;
                            return;
                        }
                        if (l < 0 || l > 8) {
                            l = 0;
                        }
                        k = y[l];
                        if (++l >= y.length) {
                            l = 0;
                        }
                        f.b();
                        if (q.a(h.ag - (b += i << 2)) < 50 && q.a(h.ah - c) < 40) {
                            j = 4;
                            l = 0;
                        }
                        if (q.a(h.ag - b) < 150 && (h.ak != 1 || q.a(h.ah - c) < 40)) break block0;
                        j = 3;
                        k = 0;
                        i = b > h.ag ? -1 : 1;
                        return;
                    }
                    case 0: {
                        if (f.b()) {
                            return;
                        }
                        k = y[l];
                        if (++l == y.length) {
                            l = 0;
                        }
                        b += d;
                        if (i == 1) {
                            if (k.a(b + u / 2, c - n / 2, 4)) {
                                j = 7;
                                e = -10;
                                return;
                            }
                        } else if (k.a(b - u / 2 - 1, c - n / 2, 8)) {
                            j = 7;
                            e = -10;
                            return;
                        }
                        if (b < 0) {
                            b = 0;
                        }
                        if (b >= k.c) {
                            b = k.c - 1;
                        }
                        if ((k.c(b + i * u, c) & 2) != 2) {
                            j = 7;
                            e = -5;
                            return;
                        }
                        if (q.a(b - h.ag) < 50 && q.a(c - h.ah) < 50 && h.ak == 1) {
                            j = 3;
                            k = 0;
                            int n3 = i = b > h.ag ? -1 : 1;
                        }
                        if (q.aq % 5 != 0) break block0;
                        f.c();
                        return;
                    }
                    case 1: {
                        k = y[l];
                        if (++l != y.length) break block0;
                        l = 0;
                    }
                }
                return;
            }
            case 3: {
                if (q.aq % 10 == 0) {
                    k = k == 0 ? 1 : 0;
                }
                switch (a) {
                    case -1: {
                        c = !k.a(b, c, 2) ? (c += 5) : k.d(c);
                        if (i.b) {
                            return;
                        }
                        f.b();
                        if (++l <= 20 || q.a(h.ag - b) >= 150 || q.a(h.ah - c) >= 40) break block0;
                        j = 2;
                        l = 0;
                        i = b > h.ag ? -1 : 1;
                        return;
                    }
                    case 0: {
                        if (k != 0 && k != 1) {
                            k = 0;
                        }
                        if (f.b()) {
                            return;
                        }
                        f.c();
                        return;
                    }
                    case 1: {
                        if (k == 0 || k == 1) break block0;
                        k = 0;
                    }
                }
                return;
            }
            case 4: {
                switch (a) {
                    case -1: {
                        if (i.b) {
                            j = 3;
                            return;
                        }
                        f.b();
                        k = z[l];
                        ++l;
                        if (k == 5 && h.ak != 7 && q.a(h.ag - ((b += i << 2) + i * 17)) < 16 && q.a(h.ah - c) < 40) {
                            h.a(r, i);
                        }
                        if (l != z.length) break block0;
                        l = 0;
                        j = 2;
                        k = 0;
                        return;
                    }
                    case 0: {
                        k = z[l];
                        b += d;
                        c += e;
                        if (++l == z.length) {
                            l = 0;
                        }
                        f.c();
                    }
                }
                return;
            }
            case 6: {
                switch (a) {
                    case -1: {
                        k = 6;
                        c = l <= 2 ? (c -= 3) : (c += 3);
                        if (!i.b) {
                            b += i << 3;
                        } else if (q.aB < 6) {
                            b += h.as << 3;
                        }
                        if (++l != 6) break block0;
                        j = 2;
                        l = 15;
                        i = b > h.ag ? -1 : 1;
                        return;
                    }
                }
                return;
            }
            case 7: {
                switch (a) {
                    case 0: {
                        if (f.b()) {
                            return;
                        }
                        if (d == 0) {
                            d = i << 2;
                        }
                        if (i == 1) {
                            if ((k.c(b + u / 2, c - n / 2) & 4) == 4) {
                                d = 0;
                            }
                        } else if ((k.c(b - u / 2, c - n / 2) & 8) == 8) {
                            d = 0;
                        }
                        if (h.ah < 0) {
                            h.ah = 0;
                            h.aj = -1;
                        }
                        if ((b += d) < 0) {
                            b = 0;
                        }
                        if (b >= k.c) {
                            b = k.c - 1;
                        }
                        c += e;
                        k = 7;
                        b += d;
                        if (d > 1) {
                            --d;
                        }
                        if (++e >= 0) {
                            j = 11;
                            l = 0;
                            m = 0;
                            e = 1;
                        }
                        if (s < 0) {
                            ++s;
                        }
                        if (c > n && k.a(b, c - n, 16384)) {
                            c = k.d(c);
                            j = 11;
                            l = 0;
                            m = 0;
                            e = 1;
                        }
                        if (b < q.N * 24 - 24) {
                            b = q.N * 24;
                            c = h.ah;
                            j = 2;
                            i = 1;
                            d = 7;
                            return;
                        }
                        if (b <= q.P * 24 + 24) break block0;
                        b = q.P * 24;
                        i = -1;
                        j = 2;
                        c = h.ah;
                        d = -7;
                        return;
                    }
                    case 1: {
                        k = 7;
                    }
                }
                return;
            }
            case 9: {
                if (m < 30) {
                    ++m;
                }
                if (m != 30) break;
                k = 5;
                c += l;
                ++l;
                if (!k.a(b, c, 2)) break;
                if (i.b) {
                    q.n = 0;
                }
                l = 0;
                c = k.d(c);
                j = 3;
                m = 0;
                return;
            }
            case 11: {
                switch (a) {
                    case -1: {
                        if (k.a(b, c, 2)) {
                            j = 2;
                            l = 0;
                            e = 0;
                            d = 0;
                            c = k.d(c);
                            return;
                        }
                        c += e;
                        return;
                    }
                    case 0: {
                        if (i == 1) {
                            if ((k.c(b + u, c) & 4) == 4) {
                                d = 0;
                            }
                        } else if ((k.c(b - u, c) & 8) == 8) {
                            d = 0;
                        }
                        if ((b += d) < 0) {
                            b = 0;
                        }
                        if (b >= k.c) {
                            b = k.c - 1;
                        }
                        c += e;
                        if (e < 18) {
                            ++e;
                        }
                        if (e > 5) {
                            d = 0;
                        }
                        if (d > 1) {
                            --d;
                        }
                        if (c >= k.d) {
                            c = k.d - 1;
                            j = 7;
                            e = -15;
                        }
                        if (k == 2) {
                            k = 8;
                        }
                        if (k != 8) {
                            k = 2;
                        }
                        if (e > 4) {
                            if ((k.c(b, c) & 2) == 2) {
                                k = 0;
                                j = 3;
                                e = 0;
                                d = 0;
                                m = 0;
                                l = 0;
                                c = k.e(c);
                            }
                            if ((k.c(b, c) & 0x20000) == 131072) {
                                k = 0;
                                j = 3;
                                e = 0;
                                d = 0;
                                m = 0;
                                l = 0;
                                c = k.e(c) + 8;
                            }
                        }
                        if (k.a(b, c, 64) && k.a(b, c + 24, 4096) && c % k.f > 8) {
                            q.d(b, k.d(c) + 8);
                            j = 7;
                            i = h.ag > b ? 1 : -1;
                            d = i << 2;
                            e = -11;
                        }
                        if (s >= 0) break block0;
                        ++s;
                        return;
                    }
                    case 1: {
                        if (b < 0) {
                            b = 0;
                        }
                        if (b >= k.c) {
                            b = k.c - 1;
                        }
                        k = 8;
                    }
                }
                return;
            }
            case 13: {
                c += ++l;
                if (q.aq % 2 == 0) {
                    if (m > 1) {
                        --m;
                    } else if (m < -1) {
                        ++m;
                    }
                }
                k = 6;
                if (s != 0 && (s >= 3 || l <= 7) || (k.c(b += m, c) & 2) != 2) break;
                j = 2;
                c = 312;
                s = 0;
                m = 0;
                l = 0;
            }
        }
    }

    private static boolean b() {
        if (a == -1) {
            if (b < p) {
                b = p + 5;
                j = 2;
                i = 1;
            }
            if (b > q) {
                b = q - 5;
                i = -1;
                j = 2;
            }
        } else {
            if (q.ap != 2) {
                return false;
            }
            if (h.ag < 120 || h.ag > 4656) {
                if (j == 11) {
                    return false;
                }
                if (!k.a(b, c, 2)) {
                    j = 11;
                    d = 0;
                    e = 5;
                } else {
                    j = 3;
                    e = 0;
                    d = 0;
                }
                return true;
            }
        }
        return false;
    }

    public static boolean a(int n2, int n3, int n4) {
        switch (n4) {
            case 0: {
                if (j == 1) break;
                if (g > h / 5) {
                    g -= n2;
                    v = 10;
                } else {
                    v = 0;
                }
                i = n3;
                l = 0;
                m = 0;
                j = 6;
                q.a("-" + n2, 0, b, c - n, 0, -2);
                if (i.j || g > h / 5) break;
                i.C = 36;
                q.b();
                q.c();
                i.j = true;
                i.l = q.b(1, 10);
                i.B = 0;
                o.a.addElement(new j(b, c - 67, 20));
                break;
            }
            case 2: {
                if (g > h / 5) {
                    g -= n2;
                    v = 10;
                } else {
                    v = 0;
                }
                l = -5;
                m = n3 << q.aq % 4;
                s = 0;
                v = 10;
                q.a("-" + n2, 0, b, c - n, 0, -2);
                j = 13;
                if (i.j || g > h / 5) break;
                i.C = 36;
                q.b();
                q.c();
                i.j = true;
                i.l = q.b(1, 10);
                i.B = 0;
                o.a.addElement(new j(b, c - 67, 20));
            }
        }
        return false;
    }

    public static void a(Graphics graphics) {
        int n2;
        if (!o) {
            return;
        }
        if (i == 1) {
            n2 = 0;
            while (n2 < 5) {
                graphics.drawRegion(t[x[k][n2]], 0, 0, t[x[k][n2]].getWidth(), t[x[k][n2]].getHeight(), 2, -w[k][n2] + b, w[k][n2 + 5] + c, 33);
                ++n2;
            }
        } else {
            n2 = 0;
            while (n2 < 5) {
                graphics.drawImage(t[x[k][n2]], w[k][n2] + b, w[k][n2 + 5] + c, 33);
                ++n2;
            }
        }
        if (v != 0 && !i.j) {
            graphics.setColor(0x330011);
            graphics.drawRect(b - (u >> 1) - 1, c - n - 7, u + 2, 4);
            n2 = g * u / h;
            graphics.setColor(0xEE0000);
            graphics.fillRect(b - (u >> 1), c - n - 6, n2, 3);
            graphics.setColor(0x440022);
            graphics.fillRect(b - (u >> 1) + n2, c - n - 6, u - n2 + 1, 3);
        }
        if (i.j && i.B <= i.C && i.B % 5 < 4) {
            Midlet.c(graphics, "" + i.l, b, c - 70, 2);
        }
    }

    private static void c() {
        if (b < q.N * 24 - 24 || b > q.P * 24 + 24) {
            if (b < q.N * 24 - 24) {
                b = q.N * 24;
                c = h.ah;
                j = 2;
                d = 7;
                i = 1;
                return;
            }
            b = q.P * 24;
            i = -1;
            j = 2;
            c = h.ah;
            d = -7;
            return;
        }
        if (q.a(b - h.ag) < 50 && q.a(c - h.ah) < 50) {
            return;
        }
        if (h.ah <= c - 24 && k.a(b, c - 1, 2)) {
            j = 7;
            d = 0;
            e = -7;
            return;
        }
        if (h.ah <= c - 24 && k.a(b, c - 25, 2)) {
            j = 7;
            d = 0;
            e = -10;
            return;
        }
        if (c < q.O * 24 - 30 || c > q.Q * 24 + 30) {
            i = h.ag + (h.as << 5) > b ? 1 : -1;
            int n2 = h.ag;
            int n3 = h.ah > c ? q.ay - 20 : q.ay + q.T;
            b = n2;
            c = n3;
            j = 7;
            d = i << 2;
            e = -4;
            return;
        }
        if (q.a(h.ag + (h.as << 5) - b) > (j == 4 ? 10 : 20)) {
            if (h.ah < c && q.aq % 10 <= 4) {
                if (k.a(b + i * 48, c - 24, 2)) {
                    j = 7;
                    d = i * 5;
                    e = -8;
                    return;
                }
                int n4 = 1;
                while (n4 <= 3) {
                    if (k.a(b + i * 24, c - n4 * 24, 2)) {
                        j = 7;
                        d = i << 1;
                        e = -5 - n4 * 3;
                        return;
                    }
                    ++n4;
                }
                if (!k.a(b + i * 48, c - 2, 64)) {
                    if (c + 24 >= k.d || k.a(b + i * 48, c + 24, 2)) {
                        j = 7;
                        d = i * 7;
                        e = -4;
                        return;
                    }
                    j = 7;
                    i = b < h.ag ? 1 : -1;
                    d = i << 2;
                    e = -11;
                    return;
                }
            } else {
                if (h.ah > c && q.aq % 10 <= 4) {
                    if (k.a(b + i * u, c, 2)) {
                        i = h.ag > b ? 1 : -1;
                        j = 2;
                        d = i * q.aC;
                        return;
                    }
                    if (k.a(b + i * 48, c + 24, 2)) {
                        j = 7;
                        d = i * 7;
                        e = -5;
                        return;
                    }
                    if (k.a(b + i * 24, c - 24, 2)) {
                        j = 7;
                        d = i * 5;
                        e = -7;
                    } else if (k.a(b + i * 48, c - 24, 2)) {
                        j = 7;
                        d = i * 6;
                        e = -8;
                    } else if (k.a(b + i * 24, c - 48, 2)) {
                        j = 7;
                        d = i * 5;
                        e = -11;
                    }
                    j = 7;
                    i = b < h.ag ? 1 : -1;
                    d = i << 2;
                    e = -4;
                    return;
                }
                if (q.a(h.ag - b) > 50 && k.a(b + i * u, c, 2)) {
                    i = h.ag + (h.as << 5) > b ? 1 : -1;
                    j = 2;
                    d = i * (q.aC + 2);
                    return;
                }
            }
        } else {
            j = 3;
        }
    }
}


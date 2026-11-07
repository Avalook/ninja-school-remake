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

public final class m {
    public static boolean a;
    private static Image[] h;
    private static int i;
    public static int b;
    private static int j;
    private static int k;
    public static int c;
    private static int l;
    private static int m;
    private static int n;
    private static int o;
    private static int p;
    private static int q;
    private static int r;
    public static int d;
    private static int s;
    private static int t;
    private static int u;
    private static int v;
    private static int[][] w;
    private static int[] x;
    public static int e;
    public static int f;
    private static int y;
    public static boolean g;

    static {
        int[][] nArrayArray = new int[5][];
        int[] nArray = new int[6];
        nArray[1] = 1;
        nArray[2] = 30;
        nArray[3] = 2;
        nArray[4] = -15;
        nArrayArray[0] = nArray;
        int[] nArray2 = new int[6];
        nArray2[0] = 2;
        nArray2[1] = 3;
        nArray2[2] = 28;
        nArray2[3] = 3;
        nArray2[4] = -12;
        nArrayArray[1] = nArray2;
        int[] nArray3 = new int[6];
        nArray3[0] = 4;
        nArray3[1] = 3;
        nArray3[2] = 25;
        nArray3[3] = 3;
        nArray3[4] = -12;
        nArrayArray[2] = nArray3;
        int[] nArray4 = new int[6];
        nArray4[0] = 5;
        nArray4[1] = 3;
        nArray4[2] = 26;
        nArray4[3] = 3;
        nArray4[4] = -12;
        nArrayArray[3] = nArray4;
        int[] nArray5 = new int[6];
        nArray5[0] = 5;
        nArray5[1] = 1;
        nArray5[2] = 31;
        nArray5[3] = 3;
        nArray5[4] = -12;
        nArrayArray[4] = nArray5;
        w = nArrayArray;
        int[] nArray6 = new int[4];
        nArray6[1] = 3;
        nArray6[2] = 1;
        nArray6[3] = 4;
        x = nArray6;
    }

    public static void a() {
        h = null;
        System.gc();
    }

    public static void a(int n2) {
        a = false;
        if (g) {
            return;
        }
        if (n2 == 102) {
            a = true;
            if (h == null) {
                h = new Image[6];
                n2 = 0;
                while (n2 < 6) {
                    try {
                        m.h[n2] = Image.createImage((String)("/mob/b3" + n2 + ".png"));
                    }
                    catch (IOException iOException) {
                        System.out.println("Shr ERR");
                    }
                    ++n2;
                }
            }
            b = 192;
            c = 312;
            i = 312;
            l = 96;
            m = 432;
            p = -1;
            q = 0;
            d = 50;
            r = 64;
            n = 0;
            s = 3;
            t = 80;
            v = 288;
        }
    }

    public static void b() {
        if (!a) {
            return;
        }
        switch (s) {
            case 1: {
                if (!g.y) {
                    g = true;
                    q.bi = 4;
                    q.f();
                    q.a(b, c);
                    g.y = true;
                }
                ++o;
                q = 2;
                if ((b += p << 3) < l) {
                    b = l + 10;
                    p = 1;
                } else if (b > m) {
                    b = m - 10;
                    p = -1;
                }
                c += u;
                ++u;
                if (c > 720) {
                    if (q.as == 102 && g.y) {
                        g.aL = g.aM += g.d[++g.aW];
                        g.aN = g.aO += g.e[g.aW];
                        g.aJ += g.b[g.aW];
                        g.aX = 0;
                        q.h(8);
                        q.K = 7;
                    }
                    s = 5;
                }
                if (c <= v) break;
                q.c(b, v - 12);
                return;
            }
            case 6: {
                ++o;
                q = 2;
                if ((b += p << 3) < l) {
                    b = l + 10;
                    p = 1;
                } else if (b > m) {
                    b = m - 10;
                    p = -1;
                }
                c += u;
                ++u;
                if (c > i) {
                    c = i;
                    m.d();
                    p = -p;
                }
                if (c <= v) break;
                q.c(b, v - 12);
                return;
            }
            case 12: {
                if (++n >= 8) {
                    n = 0;
                }
                q = x[n >> 1];
                b += p << 3;
                if (c > v) {
                    --c;
                }
                if (b < l) {
                    b = l + 10;
                    p = 1;
                    o = 0;
                    n = 0;
                    s = 3;
                    c = i;
                } else if (b > m) {
                    b = m - 10;
                    p = -1;
                    o = 0;
                    n = 0;
                    s = 2;
                    j = 20;
                    c = i;
                }
                q.c(b - (p << 4), v - 12);
                m.c();
                return;
            }
            case 4: {
                q = ++o > 4 ? 3 : 1;
                b += p << 1;
                if (j > 0) {
                    --j;
                    b += p << 2;
                }
                if (b < l + 10) {
                    b = l + 10;
                    p = 1;
                } else if (b > m - 10) {
                    b = m - 10;
                    p = -1;
                }
                c += u;
                ++u;
                if (c > i) {
                    c = i;
                    m.d();
                }
                if (c > v) {
                    q.c(b, v - 12);
                }
                m.c();
                return;
            }
            case 3: {
                q = 1;
                ++n;
                if (++o == 20) {
                    m.d();
                    return;
                }
                if (n > 8) {
                    n = 0;
                }
                if (n <= 3) break;
                q = 0;
                return;
            }
            case 2: {
                q = 1;
                if (++n > 8) {
                    n = 0;
                }
                if (n > 3) {
                    q = 0;
                }
                b += p << 3;
                if (j > 0) {
                    --j;
                    b += p << 1;
                }
                if (b < l) {
                    b = l + 10;
                    p = 1;
                } else if (b > m) {
                    b = m - 10;
                    p = -1;
                }
                if (q.at % 4 != 0) break;
                if (q.a(g.at - b - (p << 6)) < 20) {
                    ++k;
                    m.d();
                }
                return;
            }
            case 7: {
                q = 1;
                if (++n > 8) {
                    n = 0;
                }
                if (n > 3) {
                    q = 0;
                }
                if ((b -= p << 2) >= l && b <= m) break;
                o = 0;
                n = 0;
                s = 12;
            }
        }
    }

    private static void c() {
        if (q.a(g.au - c) < 30 && q.a(g.at - b - (p << 5)) < 20 && g.ax != 7) {
            g.a(t, p);
        }
    }

    private static void d() {
        if (k > 0) {
            int n2;
            s = 4;
            if ((n2 = --k) > 3) {
                n2 = 3;
            }
            u = -10 - n2;
            return;
        }
        if ((p == -1 && b > g.at || p == 1 && b < g.at) && q.at % 10 > 4) {
            o = 0;
            n = 0;
            s = 7;
            return;
        }
        o = 0;
        n = 0;
        s = 2;
    }

    public static void a(Graphics graphics) {
        if (!a) {
            return;
        }
        if (p == 1) {
            graphics.drawImage(h[w[q][0]], b + w[q][2], c - w[q][3], 33);
            graphics.drawImage(h[w[q][1]], b + w[q][4], c - w[q][5], 33);
        } else {
            graphics.drawRegion(h[w[q][0]], 0, 0, h[w[q][0]].getWidth(), h[w[q][0]].getHeight(), 2, b - w[q][2], c - w[q][3], 33);
            graphics.drawRegion(h[w[q][1]], 0, 0, h[w[q][1]].getWidth(), h[w[q][1]].getHeight(), 2, b - w[q][4], c - w[q][5], 33);
        }
        if (y != 0) {
            graphics.setColor(0x330011);
            graphics.drawRect(b - (r >> 1) - 1, c - d - 7, r + 2, 4);
            int n2 = e * r / f;
            graphics.setColor(0xEE0000);
            graphics.fillRect(b - (r >> 1), c - d - 6, n2, 3);
            graphics.setColor(0x440022);
            graphics.fillRect(b - (r >> 1) + n2, c - d - 6, r - n2 + 1, 3);
        }
    }

    public static boolean b(int n2) {
        if ((e -= n2) <= 0) {
            e = 0;
            n = -5;
            o = -(p << 3);
            y = 0;
            s = 1;
            u = -10;
            q.a(b, c, 4, -6, 50);
            q.a(b, c, -5, -7, 50);
            q.a(b, c, -4, -8, 50);
            q.a(b, c, 5, -9, 50);
            q.a(b, c, 0, -10, 50);
            return true;
        }
        ++k;
        j = 10;
        n = 0;
        o = 0;
        s = 6;
        y = 10;
        return false;
    }
}


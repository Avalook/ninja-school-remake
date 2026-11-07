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

public final class e {
    public static int a;
    public static int b;
    private static int g;
    private static int h;
    private static int i;
    public static int c;
    private static int j;
    private static int k;
    private static int l;
    private static int m;
    private static int n;
    public static boolean d;
    private static int o;
    private static Image[] p;
    public static int e;
    public static int f;
    private static int q;
    private static int[][] r;
    private static int[][] s;
    private static int[][] t;

    static {
        f = 8000;
        int[][] nArrayArray = new int[10][];
        int[] nArray = new int[4];
        nArray[0] = 2;
        nArray[1] = -5;
        nArrayArray[0] = nArray;
        int[] nArray2 = new int[4];
        nArray2[0] = 2;
        nArray2[1] = -6;
        nArrayArray[1] = nArray2;
        int[] nArray3 = new int[4];
        nArray3[0] = -7;
        nArray3[1] = -10;
        nArrayArray[2] = nArray3;
        int[] nArray4 = new int[4];
        nArray4[0] = -6;
        nArray4[1] = -8;
        nArrayArray[3] = nArray4;
        int[] nArray5 = new int[4];
        nArray5[0] = -10;
        nArray5[1] = -7;
        nArrayArray[4] = nArray5;
        int[] nArray6 = new int[4];
        nArray6[0] = -1;
        nArray6[1] = -5;
        nArrayArray[5] = nArray6;
        int[] nArray7 = new int[4];
        nArray7[0] = -13;
        nArray7[1] = -4;
        nArrayArray[6] = nArray7;
        int[] nArray8 = new int[4];
        nArray8[0] = -7;
        nArray8[1] = -10;
        nArrayArray[7] = nArray8;
        int[] nArray9 = new int[4];
        nArray9[0] = -13;
        nArray9[1] = -4;
        nArrayArray[8] = nArray9;
        int[] nArray10 = new int[4];
        nArray10[1] = -2;
        nArrayArray[9] = nArray10;
        r = nArrayArray;
        int[][] nArrayArray2 = new int[10][];
        nArrayArray2[0] = new int[]{6, 7};
        nArrayArray2[1] = new int[]{6, 7};
        nArrayArray2[2] = new int[]{3, 4};
        nArrayArray2[3] = new int[]{3, 1};
        int[] nArray11 = new int[2];
        nArray11[1] = 1;
        nArrayArray2[4] = nArray11;
        nArrayArray2[5] = new int[]{6, 1};
        nArrayArray2[6] = new int[]{2, 1};
        nArrayArray2[7] = new int[]{3, 4};
        nArrayArray2[8] = new int[]{2, 1};
        nArrayArray2[9] = new int[]{5, 8};
        s = nArrayArray2;
        t = new int[][]{{5, 4, 4, 4, 5, 6, 6, 6}, {5, 6, 6, 6}};
    }

    public static void a(int n2) {
        if (g.C) {
            return;
        }
        d = false;
        if (n2 == 106 || n2 == 20) {
            d = true;
            o = -1;
            c = 3;
            p = new Image[9];
            try {
                int n3 = 0;
                while (n3 < 8) {
                    e.p[n3] = Image.createImage((String)("/mob/nj6t" + n3 + ".png"));
                    ++n3;
                }
                e.p[8] = Image.createImage((String)"/cp/cb0.png");
            }
            catch (IOException iOException) {
                System.out.println("Image Error");
            }
            if (n2 == 106) {
                a = 1066;
                b = 168;
                return;
            }
            a = 1810;
            b = 288;
            l = 24;
            m = 1896;
            e = 200;
            f = 8000;
        }
    }

    public static void a() {
        block46: {
            block48: {
                block47: {
                    if (!d) {
                        return;
                    }
                    if (q.as == 106 || q.as != 20 || q.aN) break block46;
                    if (a < m) break block47;
                    o = -1;
                    break block48;
                }
                if (a > l) break block46;
                o = 1;
            }
            c = 11;
            k = 0;
            j = 0;
            h = -12;
            g = 5;
        }
        if (q.aA && c != 1) {
            c = 3;
        }
        switch (c) {
            case 3: {
                if (++j > 10) {
                    j = 0;
                }
                i = j > 6 ? 0 : 1;
                o = a > g.at ? -1 : 1;
                ++k;
                if (q.as == 106 || q.as != 20 || q.aN || q.aA) break;
                if (k > 20) {
                    k = 0;
                    g = 1;
                    c = 2;
                    o = a > g.at ? -1 : 1;
                }
                e.b();
                return;
            }
            case 2: {
                ++g;
                ++j;
                if (o == 1) {
                    if ((j.b(a + 46, b - 48) & 4) == 4) {
                        g = 0;
                        c = 11;
                        h = -12;
                        j = 0;
                        g = 5;
                    }
                } else if ((j.b(a - 46, b - 48) & 8) == 8) {
                    g = 0;
                    c = 11;
                    h = -12;
                    j = 0;
                    g = 5;
                }
                i = 3;
                if (!j.a(a += g * o, b, 2)) {
                    g = 10;
                    j = 0;
                    c = 12;
                }
                if (g > 10) {
                    g = 0;
                    j = 0;
                    c = 3;
                }
                if (g.au < b - 40 && g.ax == 1) {
                    c = 11;
                    h = -12;
                    j = 0;
                    g = 5;
                }
                e.b();
                return;
            }
            case 4: {
                if (o == 1) {
                    if ((j.b(a + 46, b - 48) & 4) == 4) {
                        g = 0;
                    }
                } else if ((j.b(a - 46, b - 48) & 8) == 8) {
                    g = 0;
                }
                if ((b += h) < 0) {
                    h = 0;
                    b = 0;
                }
                if (h < 0) {
                    if (++h == 0) {
                        h = 1;
                    }
                } else if (h > 0) {
                    if (h < 20 && j % 5 == 0) {
                        ++h;
                    }
                    if (h > 3) {
                        h = 3;
                    }
                    if (h > 2 && (j.b(a, b + 3) & 2) == 2) {
                        h = 0;
                        g = 0;
                        b = j.e(b + 3);
                    }
                }
                if (n == 0) {
                    if (q.at % 2 != 0) break;
                    if (j >= t[0].length) {
                        c = 2;
                        o = a > g.at ? -1 : 1;
                        j = 0;
                        g = 1;
                    }
                    i = t[0][j];
                    if (++j != 1 && j != 5) break;
                    q.a(a + o * 26, b - 29, 1, o);
                    return;
                }
                if (n == 1) {
                    if (q.at % 2 != 0) break;
                    if (j >= t[1].length) {
                        c = 2;
                        g = 1;
                        j = 0;
                    }
                    i = t[1][j];
                    if (++j != 2) break;
                    q.a(a + o * 26, b - 18, 2, o);
                    return;
                }
                a += g * o;
                return;
            }
            case 6: {
                i = 9;
                if (++j < 3) break;
                j = 0;
                h = q.e(-12, -5);
                g = q.e(4, 7);
                c = 11;
                return;
            }
            case 1: {
                i = 9;
                if (h < 20) {
                    ++h;
                }
                if ((b += h) <= 384) break;
                d = false;
                g.C = true;
                g.a = -1;
                q.g(43);
                return;
            }
            case 11: {
                if (b < q.X) {
                    c = 12;
                    g = 10;
                    j = 0;
                }
                ++j;
                i = 2;
                b += h;
                a += o * g;
                if (h < 12) {
                    ++h;
                }
                if (h == 0) {
                    g = 10;
                    c = 12;
                    j = 0;
                }
                if (!j.a(a, b, 2) || j <= 8) break;
                b = j.e(b);
                h = 0;
                g = 1;
                c = 2;
                return;
            }
            case 12: {
                a += g * o;
                b += ++h;
                if (g > 0) {
                    --g;
                }
                i = 2;
                if (b + 4 >= j.d) {
                    return;
                }
                if (h <= 4 || (j.b(a, b + 3) & 2) != 2) break;
                c = 2;
                g = 1;
                h = 0;
                k = 0;
                j = 0;
                b = j.e(b + 3);
                return;
            }
        }
    }

    public static boolean a(int n2, int n3) {
        f -= n2;
        if (c == 1) {
            return true;
        }
        if (f <= 0) {
            f = 0;
            o = n3;
            g = -(n3 << 3);
            h = -12;
            j = 0;
            c = 1;
            q.a(a, b, -(o << 2), -4, 95 + q.at % 10);
            if (q.at % 20 > 5) {
                q.a(a, b, -(o << 3), -5, e, (q.at + 1) % 2);
            }
            return true;
        }
        j = 0;
        o = n3;
        q = 100;
        c = 6;
        return false;
    }

    private static void b() {
        if (q.a(g.au - b) <= 30 && q.a(a + (o << 3) - g.at) < 45 && q.a(a + (o << 3) - g.at) > 30 && q.am == -1) {
            j = 0;
            k = 0;
            c = 4;
            o = g.at > a ? 1 : -1;
            n = 0;
            i = 5;
            return;
        }
        if (q.a(g.au - b) <= 30 && q.a(a + (o << 3) - g.at) < 100 && q.am == -1 && q.a(a + (o << 3) - g.at) >= 45) {
            j = 0;
            k = 0;
            c = 4;
            o = g.at > a ? 1 : -1;
            n = 1;
        }
    }

    public static void a(Graphics graphics) {
        if (!d) {
            return;
        }
        if (o == -1) {
            graphics.drawImage(p[s[i][0]], a + r[i][0], b + r[i][1], 33);
            graphics.drawImage(p[s[i][1]], a + r[i][2], b + r[i][3], 33);
        } else {
            graphics.drawRegion(p[s[i][0]], 0, 0, p[s[i][0]].getWidth(), p[s[i][0]].getHeight(), 2, a - r[i][0], b + r[i][1], 33);
            graphics.drawRegion(p[s[i][1]], 0, 0, p[s[i][1]].getWidth(), p[s[i][1]].getHeight(), 2, a + r[i][2], b + r[i][3], 33);
        }
        if (q != 0) {
            graphics.setColor(0x330011);
            graphics.drawRect(a - 23 - 1, b - 48 - 7, 48, 4);
            int n2 = f * 46 / 8000;
            graphics.setColor(0xEE0000);
            graphics.fillRect(a - 23, b - 48 - 6, n2, 3);
            graphics.setColor(0x440022);
            graphics.fillRect(a - 23 + n2, b - 48 - 6, 46 - n2 + 1, 3);
        }
    }
}


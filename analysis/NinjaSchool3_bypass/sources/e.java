/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class e {
    private static int[] b;
    private static int[] c;
    private static int[] d;
    private static int[] e;
    private static Image f;
    private static int[] g;
    static int a;
    private static int h;
    private static int i;
    private static int j;

    public static void a(int n2) {
        h = 100;
        q.aF = 0;
        q.aE = 0;
        if (n2 == 8 || n2 == 9 || n2 == 10 || n2 == 14 || n2 == 17 || n2 == 15 || n2 == 20 || n2 == 21) {
            return;
        }
        if (n2 == 3 || n2 == 11 || n2 == 12 || n2 == 0 || n2 == 18 || n2 == 16) {
            h = 0;
            i = 0;
        } else if (n2 == 6) {
            h = -1;
            i = -1;
            if (!h.f || h.f && h.aA == 0) {
                q.aF = -2;
            }
        } else if (n2 == 13 || n2 == 19 || n2 == 22) {
            h = 1;
            i = 1;
            if (!h.f || h.f && h.aA == 0) {
                q.aE = 2;
            }
        } else if (n2 == 1 || n2 == 2 || n2 == 4 || n2 == 5 || n2 == 7) {
            i = 2;
            h = q.b(0, 2);
            if (!h.f || h.f && h.aA == 0) {
                if (h == -1) {
                    q.aF = -2;
                }
                if (h == 1) {
                    q.aE = 2;
                }
            }
        }
        a = 200;
        if (f == null) {
            f = q.c("/tuyet.png");
        }
        b = new int[a];
        c = new int[a];
        d = new int[a];
        e = new int[a];
        g = new int[a];
        n2 = 0;
        while (n2 < a) {
            e.b[n2] = q.b(q.N * 24 - 50, q.P * 24 + 50);
            e.c[n2] = q.b(q.O * 24, q.Q * 24 + 50);
            e.e[n2] = -1;
            e.g[n2] = q.b(0, 5);
            if (h == 1) {
                e.d[n2] = q.b(7, 10);
                e.e[n2] = q.b(4, 7);
            }
            if (h == 0) {
                e.d[n2] = 0;
                e.e[n2] = q.b(1, 3);
            }
            if (h == -1) {
                e.d[n2] = q.b(-10, -5);
                e.e[n2] = q.b(4, 7);
            }
            ++n2;
        }
    }

    public static void a() {
        int n2;
        if (h == 100) {
            return;
        }
        if ((q.aE != 0 || q.aF != 0) && h.f && h.aA > 0) {
            q.aF = 0;
            q.aE = 0;
        }
        if (i == 2 && q.aq % q.b(90, 500) == 0) {
            h = h == -1 ? -2 : (h == 1 ? 2 : q.b(-1, 2));
            if (!h.f || h.f && h.aA == 0) {
                if (h == -1) {
                    q.aF = -2;
                    j = h.ak == 3 || h.ak == 4 ? -6 : (h.ak == 1 ? -4 : -2);
                    q.aE = 0;
                } else if (h == 1) {
                    q.aF = 0;
                    j = h.ak == 3 || h.ak == 4 ? 6 : (h.ak == 1 ? 4 : 2);
                    q.aE = 2;
                } else {
                    q.aF = 0;
                    q.aE = 0;
                }
                n2 = 0;
                while (n2 < a) {
                    if (h == 1) {
                        e.d[n2] = q.b(7, 10);
                        e.e[n2] = q.b(4, 7);
                    }
                    if (h == 0) {
                        e.d[n2] = 0;
                        e.e[n2] = q.b(1, 3);
                    }
                    if (h == -1) {
                        e.d[n2] = q.b(-10, -5);
                        e.e[n2] = q.b(4, 7);
                    }
                    ++n2;
                }
            }
        }
        if (j > 0) {
            --j;
        }
        if (j < 0) {
            ++j;
        }
        if (j != 0) {
            if (j < 0 && (k.c(h.ag + j - h.ao, h.ah - h.ap) & 8) == 8) {
                j = 0;
            }
            if (j > 0 && (k.c(h.ag + j + h.ao, h.ah - h.ap) & 4) == 4) {
                j = 0;
            }
            h.ag += j;
        }
        if (i == 1 && (!h.f || h.f && h.aA == 0)) {
            q.aE = 2;
        }
        if (i == -1 && (!h.f || h.f && h.aA == 0)) {
            q.aF = -2;
        }
        n2 = 0;
        while (n2 < a) {
            if (h == 0) {
                int n3 = n2;
                c[n3] = c[n3] + e[n2];
            }
            if (h == -1) {
                int n4 = n2;
                b[n4] = b[n4] + d[n2];
                int n5 = n2;
                c[n5] = c[n5] + e[n2];
            }
            if (h == -2) {
                int n6 = n2;
                b[n6] = b[n6] + d[n2];
                int n7 = n2;
                c[n7] = c[n7] + e[n2];
                if (q.aq % 5 == 0) {
                    if (d[n2] < -2) {
                        int n8 = n2;
                        d[n8] = d[n8] + 1;
                    }
                    if (e[n2] > 2) {
                        int n9 = n2;
                        e[n9] = e[n9] - 1;
                    }
                }
            }
            if (h == 1) {
                int n10 = n2;
                b[n10] = b[n10] + d[n2];
                int n11 = n2;
                c[n11] = c[n11] + e[n2];
            }
            if (h == 2) {
                int n12 = n2;
                b[n12] = b[n12] + d[n2];
                int n13 = n2;
                c[n13] = c[n13] + e[n2];
                if (q.aq % 5 == 0) {
                    if (d[n2] > 2) {
                        int n14 = n2;
                        d[n14] = d[n14] - 1;
                    }
                    if (e[n2] > 2) {
                        int n15 = n2;
                        e[n15] = e[n15] - 1;
                    }
                }
            }
            if (c[n2] < q.O * 24 - 50 || c[n2] > q.Q * 24 + 50 || b[n2] > q.P * 24 + 50 || b[n2] < q.N * 24 - 50) {
                e.b[n2] = q.b(q.N * 24 - 50, q.P * 24 + 50);
                e.c[n2] = q.b(q.O * 24 - 40, q.Q * 24);
            }
            ++n2;
        }
    }

    public static void a(Graphics graphics) {
        if (h == 100) {
            return;
        }
        int n2 = 0;
        while (n2 < a) {
            if (g[n2] == 2) {
                graphics.drawImage(f, b[n2], c[n2], 0);
            }
            ++n2;
        }
    }

    public static void b(Graphics graphics) {
        if (h == 100) {
            return;
        }
        graphics.setColor(10742731);
        int n2 = 0;
        while (n2 < a) {
            if (g[n2] != 2) {
                graphics.fillRect(b[n2], c[n2], 2, 2);
            }
            ++n2;
        }
    }
}


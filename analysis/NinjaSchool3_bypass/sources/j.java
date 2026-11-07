/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class j {
    private static int[][] c;
    private static int[] d;
    private static int[] e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    int a;
    private int l;
    boolean b;
    private static Image[] m;

    static {
        int[][] nArrayArray = new int[45][];
        nArrayArray[0] = new int[2];
        nArrayArray[1] = new int[1];
        int[] nArray = new int[8];
        nArray[1] = 1;
        nArray[2] = 2;
        nArray[3] = 3;
        nArray[4] = 4;
        nArray[5] = 5;
        nArray[6] = 6;
        nArray[7] = 7;
        nArrayArray[2] = nArray;
        nArrayArray[3] = new int[1];
        int[] nArray2 = new int[15];
        nArray2[1] = 1;
        nArray2[2] = 2;
        nArray2[3] = 3;
        nArray2[4] = 4;
        nArray2[7] = 1;
        nArray2[8] = 1;
        nArray2[9] = 2;
        nArray2[10] = 2;
        nArray2[11] = 3;
        nArray2[12] = 3;
        nArray2[13] = 4;
        nArray2[14] = 4;
        nArrayArray[4] = nArray2;
        int[] nArray3 = new int[2];
        nArray3[1] = 1;
        nArrayArray[5] = nArray3;
        int[] nArray4 = new int[9];
        nArray4[1] = 1;
        nArray4[2] = 2;
        nArray4[3] = 3;
        nArray4[4] = 4;
        nArray4[5] = 5;
        nArray4[6] = 6;
        nArray4[7] = 7;
        nArray4[8] = 8;
        nArrayArray[6] = nArray4;
        int[] nArray5 = new int[2];
        nArray5[1] = 1;
        nArrayArray[7] = nArray5;
        int[] nArray6 = new int[9];
        nArray6[0] = 8;
        nArray6[1] = 7;
        nArray6[2] = 6;
        nArray6[3] = 5;
        nArray6[4] = 4;
        nArray6[5] = 3;
        nArray6[6] = 2;
        nArray6[7] = 1;
        nArrayArray[8] = nArray6;
        nArrayArray[9] = new int[1];
        int[] nArray7 = new int[2];
        nArray7[1] = 1;
        nArrayArray[10] = nArray7;
        nArrayArray[11] = new int[1];
        nArrayArray[12] = new int[1];
        int[] nArray8 = new int[4];
        nArray8[1] = 1;
        nArray8[2] = 2;
        nArray8[3] = 3;
        nArrayArray[13] = nArray8;
        int[] nArray9 = new int[4];
        nArray9[1] = 1;
        nArray9[2] = 2;
        nArray9[3] = 3;
        nArrayArray[14] = nArray9;
        int[] nArray10 = new int[4];
        nArray10[1] = 1;
        nArray10[2] = 2;
        nArray10[3] = 3;
        nArrayArray[15] = nArray10;
        int[] nArray11 = new int[4];
        nArray11[1] = 1;
        nArray11[2] = 2;
        nArray11[3] = 3;
        nArrayArray[16] = nArray11;
        int[] nArray12 = new int[10];
        nArray12[2] = 1;
        nArray12[3] = 1;
        nArray12[6] = 1;
        nArray12[7] = 1;
        nArrayArray[17] = nArray12;
        nArrayArray[18] = new int[14];
        nArrayArray[19] = new int[52];
        int[] nArray13 = new int[42];
        nArray13[1] = 1;
        nArray13[3] = 1;
        nArray13[5] = 1;
        nArray13[7] = 1;
        nArray13[9] = 1;
        nArray13[11] = 1;
        nArray13[13] = 1;
        nArray13[15] = 1;
        nArray13[17] = 1;
        nArray13[19] = 1;
        nArray13[21] = 1;
        nArray13[23] = 1;
        nArray13[25] = 1;
        nArray13[27] = 1;
        nArray13[29] = 1;
        nArray13[31] = 1;
        nArray13[33] = 1;
        nArray13[35] = 1;
        nArray13[37] = 1;
        nArray13[39] = 1;
        nArray13[41] = 1;
        nArrayArray[20] = nArray13;
        int[] nArray14 = new int[10];
        nArray14[4] = 1;
        nArray14[5] = 1;
        nArray14[6] = 2;
        nArray14[7] = 2;
        nArray14[8] = 3;
        nArray14[9] = 3;
        nArrayArray[21] = nArray14;
        int[] nArray15 = new int[20];
        nArray15[2] = 1;
        nArray15[3] = 1;
        nArray15[6] = 1;
        nArray15[7] = 1;
        nArray15[10] = 1;
        nArray15[11] = 1;
        nArray15[14] = 1;
        nArray15[15] = 1;
        nArray15[18] = 1;
        nArray15[19] = 1;
        nArrayArray[22] = nArray15;
        nArrayArray[23] = new int[8];
        int[] nArray16 = new int[24];
        nArray16[1] = 1;
        nArray16[2] = 2;
        nArray16[3] = 3;
        nArray16[5] = 1;
        nArray16[6] = 2;
        nArray16[7] = 3;
        nArray16[9] = 1;
        nArray16[10] = 2;
        nArray16[11] = 3;
        nArray16[13] = 1;
        nArray16[14] = 2;
        nArray16[15] = 3;
        nArray16[17] = 1;
        nArray16[18] = 2;
        nArray16[19] = 3;
        nArray16[21] = 1;
        nArray16[22] = 2;
        nArray16[23] = 3;
        nArrayArray[24] = nArray16;
        int[] nArray17 = new int[15];
        nArray17[3] = 1;
        nArray17[4] = 1;
        nArray17[5] = 1;
        nArray17[6] = 2;
        nArray17[7] = 2;
        nArray17[8] = 2;
        nArray17[9] = 3;
        nArray17[10] = 3;
        nArray17[11] = 3;
        nArray17[12] = 4;
        nArray17[13] = 4;
        nArray17[14] = 4;
        nArrayArray[25] = nArray17;
        int[] nArray18 = new int[11];
        nArray18[2] = 1;
        nArray18[3] = 1;
        nArray18[4] = 1;
        nArray18[5] = 2;
        nArray18[6] = 2;
        nArray18[7] = 2;
        nArray18[8] = 3;
        nArray18[9] = 3;
        nArray18[10] = 3;
        nArrayArray[26] = nArray18;
        int[] nArray19 = new int[12];
        nArray19[3] = 1;
        nArray19[4] = 1;
        nArray19[5] = 1;
        nArray19[6] = 2;
        nArray19[7] = 2;
        nArray19[8] = 2;
        nArray19[9] = 3;
        nArray19[10] = 3;
        nArray19[11] = 3;
        nArrayArray[27] = nArray19;
        int[] nArray20 = new int[3];
        nArray20[1] = 1;
        nArray20[2] = 2;
        nArrayArray[28] = nArray20;
        int[] nArray21 = new int[4];
        nArray21[2] = 1;
        nArray21[3] = 1;
        nArrayArray[29] = nArray21;
        int[] nArray22 = new int[4];
        nArray22[1] = 1;
        nArray22[2] = 2;
        nArray22[3] = 3;
        nArrayArray[30] = nArray22;
        int[] nArray23 = new int[4];
        nArray23[1] = 1;
        nArray23[2] = 2;
        nArray23[3] = 3;
        nArrayArray[31] = nArray23;
        int[] nArray24 = new int[4];
        nArray24[1] = 1;
        nArray24[2] = 2;
        nArray24[3] = 3;
        nArrayArray[32] = nArray24;
        int[] nArray25 = new int[4];
        nArray25[1] = 1;
        nArray25[2] = 2;
        nArray25[3] = 3;
        nArrayArray[33] = nArray25;
        int[] nArray26 = new int[4];
        nArray26[1] = 1;
        nArray26[2] = 2;
        nArray26[3] = 3;
        nArrayArray[34] = nArray26;
        int[] nArray27 = new int[4];
        nArray27[1] = 1;
        nArray27[2] = 2;
        nArray27[3] = 3;
        nArrayArray[35] = nArray27;
        int[] nArray28 = new int[4];
        nArray28[1] = 1;
        nArray28[2] = 2;
        nArray28[3] = 3;
        nArrayArray[36] = nArray28;
        int[] nArray29 = new int[4];
        nArray29[1] = 1;
        nArray29[2] = 2;
        nArray29[3] = 3;
        nArrayArray[37] = nArray29;
        int[] nArray30 = new int[12];
        nArray30[4] = 1;
        nArray30[5] = 1;
        nArray30[6] = 1;
        nArray30[7] = 1;
        nArray30[8] = 2;
        nArray30[9] = 2;
        nArray30[10] = 2;
        nArray30[11] = 2;
        nArrayArray[38] = nArray30;
        int[] nArray31 = new int[8];
        nArray31[1] = 1;
        nArray31[2] = 2;
        nArray31[3] = 1;
        nArray31[4] = 2;
        nArray31[5] = 3;
        nArray31[6] = 4;
        nArray31[7] = 5;
        nArrayArray[39] = nArray31;
        int[] nArray32 = new int[12];
        nArray32[4] = 1;
        nArray32[5] = 1;
        nArray32[6] = 1;
        nArray32[7] = 1;
        nArray32[8] = 2;
        nArray32[9] = 2;
        nArray32[10] = 2;
        nArray32[11] = 2;
        nArrayArray[40] = nArray32;
        int[] nArray33 = new int[4];
        nArray33[2] = 1;
        nArray33[3] = 1;
        nArrayArray[41] = nArray33;
        int[] nArray34 = new int[24];
        nArray34[1] = 1;
        nArray34[2] = 2;
        nArray34[3] = 3;
        nArray34[5] = 1;
        nArray34[6] = 2;
        nArray34[7] = 3;
        nArray34[9] = 1;
        nArray34[10] = 2;
        nArray34[11] = 3;
        nArray34[13] = 1;
        nArray34[14] = 2;
        nArray34[15] = 3;
        nArray34[17] = 1;
        nArray34[18] = 2;
        nArray34[19] = 3;
        nArray34[21] = 1;
        nArray34[22] = 2;
        nArray34[23] = 3;
        nArrayArray[42] = nArray34;
        int[] nArray35 = new int[4];
        nArray35[1] = 1;
        nArray35[2] = 2;
        nArray35[3] = 3;
        nArrayArray[43] = nArray35;
        int[] nArray36 = new int[24];
        nArray36[1] = 1;
        nArray36[2] = 2;
        nArray36[3] = 3;
        nArray36[5] = 1;
        nArray36[6] = 2;
        nArray36[7] = 3;
        nArray36[9] = 1;
        nArray36[10] = 2;
        nArray36[11] = 3;
        nArray36[13] = 1;
        nArray36[14] = 2;
        nArray36[15] = 3;
        nArray36[17] = 1;
        nArray36[18] = 2;
        nArray36[19] = 3;
        nArray36[21] = 1;
        nArray36[22] = 2;
        nArray36[23] = 3;
        nArrayArray[44] = nArray36;
        c = nArrayArray;
        d = new int[]{52, 11, 12, 41, 14, 32, 12, 32, 10, 32, 16, 12, 14, 7, 7, 7, 7, 24, 28, 30, 13, 32, 3, 28, 3, 27, 26, 16, 31, 16, 7, 7, 7, 7, 7, 7, 7, 7, 31, 49, 31, 5, 7, 7, 7};
        e = new int[]{18, 11, 12, 38, 14, 32, 12, 32, 10, 32, 200, 12, 14, 7, 7, 7, 7, 35, 18, 41, 13, 32, 12, 37, 3, 27, 33, 10, 36, 15, 7, 7, 7, 7, 7, 7, 7, 7, 29, 29, 29, 11, 7, 7, 7};
        m = new Image[50];
    }

    public j(int n2, int n3, int n4) {
        this.f = n2;
        this.g = n3;
        this.j = n4;
        this.h = d[n4];
        this.i = e[n4];
        this.l = -1;
        this.k = 1;
        if (n4 == 27) {
            this.l = q.b(0, 4);
        }
        if (n4 == 43) {
            this.a = q.b(0, 2);
        }
    }

    public static void a(int n2) {
        if (n2 == 1 || n2 == 11 || n2 == 12) {
            return;
        }
        if (m[n2] == null) {
            if (n2 > 29 && n2 < 38) {
                j.m[n2] = q.c("/g14.png");
                return;
            }
            if (n2 == 40) {
                j.m[n2] = q.c("/g38.png");
                return;
            }
            if (n2 == 42) {
                j.m[n2] = q.c("/g16.png");
                return;
            }
            if (n2 == 44) {
                j.m[n2] = q.c("/g15.png");
                return;
            }
            j.m[n2] = q.c("/g" + n2 + ".png");
        }
    }

    public final void a(Graphics graphics) {
        if (this.l == -1 || this.j == 41) {
            return;
        }
        if (this.j == 19) {
            if (q.aq % 4 > 1) {
                return;
            }
            graphics.drawRegion(m[this.j], 0, c[this.j][this.l] * this.i, this.h, this.i, 0, this.f, this.g, 3);
            return;
        }
        if (this.j == 20) {
            if (q.ap == 14) {
                if (!d.g) {
                    graphics.drawRegion(m[this.j], 0, c[this.j][this.l] * this.i, this.h, this.i, 0, d.a, d.b - 80, 3);
                    return;
                }
                graphics.drawRegion(m[this.j], 0, c[this.j][this.l] * this.i, this.h, this.i, 0, h.ag, h.ah - 47, 3);
                return;
            }
            if (q.ap == 17) {
                graphics.drawRegion(m[this.j], 0, c[this.j][this.l] * this.i, this.h, this.i, 0, h.bo, h.bp - 67, 3);
                return;
            }
            if (q.ap == 22) {
                graphics.drawRegion(m[this.j], 0, c[this.j][this.l] * this.i, this.h, this.i, 0, f.b, f.c - 67, 3);
                return;
            }
            graphics.drawRegion(m[this.j], 0, c[this.j][this.l] * this.i, this.h, this.i, 0, q.ap == 12 || q.ap == 4 ? h.ag : i.m, q.ap == 12 || q.ap == 4 ? h.ah - 57 : i.n - 67, 3);
            return;
        }
        if (this.j != 39 || this.j == 39 && this.a == 1) {
            graphics.drawRegion(m[this.j], 0, c[this.j][this.l] * this.i, this.h, this.i, 0, this.f, this.g, 3);
            return;
        }
        graphics.drawRegion(m[this.j], 0, c[this.j][this.l] * this.i, this.h, this.i, 2, this.f, this.g, 3);
    }

    public final void b(Graphics graphics) {
        if (this.j != 41 || this.l == -1) {
            return;
        }
        graphics.drawRegion(m[this.j], 0, c[this.j][this.l] * this.i, this.h, this.i, 2, this.f, this.g, 3);
    }

    public final void a() {
        if (this.j == 42 || this.j == 44) {
            this.f += q.b(-1, 2);
            this.g -= q.b(1, 3);
            ++this.l;
            if (this.l >= c[this.j].length) {
                this.l = 0;
                this.b = true;
                this.k = 0;
            }
            return;
        }
        if (this.j == 39) {
            int n2;
            this.f += this.a * this.k;
            if (q.aq % 2 == 0) {
                ++this.l;
                ++this.k;
            }
            if (i.E && q.a(this.f - i.H) < 28 && q.a(this.g - (i.I - 27)) < 23) {
                i.a(h.aw, this.a);
                this.l = 0;
                this.b = true;
                this.k = 0;
            }
            if (f.o && f.a == -1 && q.a(this.f - f.b) < 28 && q.a(this.g - f.c) < 23) {
                f.a(h.aw, this.a, 0);
                this.l = 0;
                this.b = true;
                this.k = 0;
            }
            if (i.a && q.a(this.f - i.m) < 20 && q.a(this.g - i.n) < 20) {
                i.a(h.aw, this.a, 0);
                this.l = 0;
                this.b = true;
                this.k = 0;
            }
            if (q.ap == 17 && !i.k && q.a(this.f - h.bo) < 28 && q.a(this.g - h.bp) < 23) {
                h.b(h.aw, this.a);
                this.l = 0;
                this.b = true;
                this.k = 0;
            }
            if (!d.g && q.ap == 14) {
                n2 = 0;
                while (n2 < d.i) {
                    if (d.r[n2] != -1 && q.a(this.f - d.n[n2]) < 28 && q.a(this.g - d.o[n2]) < 23) {
                        d.a(h.aw, n2);
                        q.b(d.n[n2], d.o[n2], h.as);
                        q.a("-" + h.aw, 0, d.n[n2], d.o[n2] - 25, 0, -2);
                        this.l = 0;
                        this.b = true;
                        this.k = 0;
                        break;
                    }
                    n2 = (byte)(n2 + 1);
                }
            }
            n2 = 0;
            while (n2 < a.c) {
                if (a.i[n2] != 5 && a.i[n2] != 1 && a.i[n2] != 10 && q.a(this.f - a.e[n2]) < a.h[n2] && q.a(this.g - a.f[n2]) < a.g[n2] + 5) {
                    if (!a.a(n2, h.aw, this.a, 0)) {
                        q.b(a.e[n2], a.f[n2] - (a.g[n2] >> 1), a.k[n2]);
                        q.a("-" + h.aw, 0, a.e[n2], a.f[n2] - a.g[n2], 0, -2);
                    }
                    this.l = 0;
                    this.b = true;
                    this.k = 0;
                    break;
                }
                n2 = (byte)(n2 + 1);
            }
            if (this.l >= c[this.j].length) {
                this.l = 0;
                this.b = true;
                this.k = 0;
            }
            return;
        }
        if (this.j > 29 && this.j < 38) {
            ++this.l;
            if (this.l >= c[this.j].length) {
                this.l = 0;
            }
            switch (this.j) {
                case 30: {
                    this.g -= this.k;
                    break;
                }
                case 31: {
                    this.f += this.k;
                    this.g -= this.k;
                    break;
                }
                case 32: {
                    this.f += 2 * this.k;
                    break;
                }
                case 33: {
                    this.f += this.k;
                    this.g += this.k;
                    break;
                }
                case 34: {
                    this.g += 2 * this.k;
                    break;
                }
                case 35: {
                    this.f -= this.k;
                    this.g += this.k;
                    break;
                }
                case 36: {
                    this.f -= 2 * this.k;
                    break;
                }
                case 37: {
                    this.f -= this.k;
                    this.g -= this.k;
                }
            }
            ++this.k;
            if (this.f < q.N * 24 || this.f > q.P * 24 || this.g < q.O * 24 || this.g > q.Q * 24) {
                this.l = 0;
                this.b = true;
                this.k = 0;
            }
            int n3 = 0;
            while (n3 < a.c) {
                if (n3 != this.a && a.i[n3] != 10 && a.i[n3] != 8 && a.i[n3] != 1 && q.a(a.e[n3] - this.f) < 20 && q.a(a.f[n3] - this.g) < 20) {
                    a.m[n3] = 1;
                    o.b.addElement(new j(a.e[n3], a.f[n3] - 5, 29));
                    a.a(n3, h.aw / 6, -h.as, 0);
                    q.a("-" + h.aw / 6, 0, a.e[n3], a.f[n3] - a.g[n3], 0, -2);
                    this.l = 0;
                    this.b = true;
                    this.k = 1;
                    return;
                }
                ++n3;
            }
            return;
        }
        if (this.j > 12 && this.j < 17) {
            ++this.l;
            if (this.l >= c[this.j].length) {
                this.l = 0;
            }
            this.f += q.b(-5, 5);
            this.g += q.b(3, 6);
            if (q.a(this.f - h.ag) < 10 && q.a(this.g - h.ah) < 10) {
                h.a(a.a[22], -h.as);
                this.l = 0;
                this.b = true;
            }
            if (this.f < q.N * 24 || this.f > q.P * 24 || this.g > q.Q * 24) {
                this.l = 0;
                this.b = true;
            }
            return;
        }
        if (this.j == 24) {
            ++this.l;
            if (this.l >= c[this.j].length) {
                this.l = 0;
            }
            this.f += q.b(-1, 2);
            this.g -= q.b(0, 10);
            if (this.g < q.O * 24) {
                this.l = 0;
                this.f = q.b(744, 778);
                this.g = 816;
            }
            return;
        }
        if (this.j == 27) {
            ++this.l;
            if (this.l >= c[this.j].length) {
                this.l = 0;
            }
            this.g += q.b(1, 4);
            this.f += q.b(-3, 4);
            if (this.g > 432) {
                this.l = 0;
                this.b = true;
            }
            return;
        }
        if (this.j == 18) {
            this.f = h.ag;
            this.g = h.ah;
            if (!h.p) {
                this.l = 0;
                this.b = true;
            }
        }
        if (this.j == 20 && i.B >= i.C - 1) {
            this.l = 0;
            this.b = true;
        }
        if (this.j == 41) {
            q.A = true;
        }
        ++this.l;
        if (this.l >= c[this.j].length) {
            this.l = 0;
            this.b = true;
            if (this.j == 41) {
                if (this.a == 2 && q.bd[0] == 2) {
                    q.bd[0] = 0;
                }
                if (this.a == 3 && q.bd[1] == 3) {
                    q.bd[1] = 0;
                }
                if (this.a == 4 && q.bd[2] == 4) {
                    q.bd[2] = 0;
                }
            }
            if (this.j == 17 || this.j == 23) {
                h.m = false;
            }
            if (this.j == 21) {
                c.f = true;
                if (!(i.i || h.bn != 7 && q.ap != 14)) {
                    i.E = true;
                }
            }
            if (this.j == 26) {
                h.m = false;
            }
            if (this.j == 38) {
                h.q = h.ag;
                h.r = h.ah;
                h.ay = 1000;
                h.ah = q.O * 24;
                if (q.O * 24 < 30) {
                    h.ah = 30;
                }
                h.ak = 4;
                q.c();
                q.b();
                h.m = false;
            }
        }
        if (this.j == 19 && this.l >= 30 && q.aq % 4 == 1) {
            h.ag = h.aF;
            h.ah = h.aG;
            h.ak = 4;
            h.ay = h.az / 3;
            h.aA = h.aB / 3;
            h.aj = 0;
            h.ai = 0;
            h.au = 0;
            h.at = 0;
            h.aN >>= 1;
            q.A = true;
            q.z();
            q.g(5);
            q.B = 7;
            this.l = 0;
            this.b = true;
        }
    }
}


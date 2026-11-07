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

public class h {
    public static final int[][] a;
    private static int[] h;
    private static int[] i;
    int b;
    int c;
    private int j;
    private int k;
    int d;
    private int l;
    int e;
    int f;
    boolean g;
    private static Image[] m;

    static {
        int[][] nArrayArray = new int[33][];
        nArrayArray[0] = new int[2];
        int[] nArray = new int[18];
        nArray[0] = 17;
        nArray[1] = 16;
        nArray[2] = 15;
        nArray[3] = 14;
        nArray[4] = 13;
        nArray[5] = 12;
        nArray[6] = 11;
        nArray[7] = 10;
        nArray[8] = 9;
        nArray[9] = 8;
        nArray[10] = 7;
        nArray[11] = 6;
        nArray[12] = 5;
        nArray[13] = 4;
        nArray[14] = 3;
        nArray[15] = 2;
        nArray[16] = 1;
        nArrayArray[1] = nArray;
        int[] nArray2 = new int[8];
        nArray2[1] = 1;
        nArray2[2] = 2;
        nArray2[3] = 3;
        nArray2[4] = 4;
        nArray2[5] = 5;
        nArray2[6] = 6;
        nArray2[7] = 7;
        nArrayArray[2] = nArray2;
        nArrayArray[3] = new int[1];
        int[] nArray3 = new int[15];
        nArray3[1] = 1;
        nArray3[2] = 2;
        nArray3[3] = 3;
        nArray3[4] = 4;
        nArray3[7] = 1;
        nArray3[8] = 1;
        nArray3[9] = 2;
        nArray3[10] = 2;
        nArray3[11] = 3;
        nArray3[12] = 3;
        nArray3[13] = 4;
        nArray3[14] = 4;
        nArrayArray[4] = nArray3;
        int[] nArray4 = new int[2];
        nArray4[1] = 1;
        nArrayArray[5] = nArray4;
        int[] nArray5 = new int[9];
        nArray5[1] = 1;
        nArray5[2] = 2;
        nArray5[3] = 3;
        nArray5[4] = 4;
        nArray5[5] = 5;
        nArray5[6] = 6;
        nArray5[7] = 7;
        nArray5[8] = 8;
        nArrayArray[6] = nArray5;
        int[] nArray6 = new int[2];
        nArray6[1] = 1;
        nArrayArray[7] = nArray6;
        int[] nArray7 = new int[9];
        nArray7[0] = 8;
        nArray7[1] = 7;
        nArray7[2] = 6;
        nArray7[3] = 5;
        nArray7[4] = 4;
        nArray7[5] = 3;
        nArray7[6] = 2;
        nArray7[7] = 1;
        nArrayArray[8] = nArray7;
        nArrayArray[9] = new int[1];
        int[] nArray8 = new int[2];
        nArray8[1] = 1;
        nArrayArray[10] = nArray8;
        int[] nArray9 = new int[18];
        nArray9[5] = 1;
        nArray9[6] = 1;
        nArray9[7] = 1;
        nArray9[8] = 1;
        nArray9[9] = 2;
        nArray9[10] = 2;
        nArray9[11] = 2;
        nArray9[12] = 2;
        nArray9[13] = 2;
        nArray9[14] = 2;
        nArray9[15] = 3;
        nArray9[16] = 3;
        nArray9[17] = 3;
        nArrayArray[11] = nArray9;
        int[] nArray10 = new int[12];
        nArray10[3] = 1;
        nArray10[4] = 1;
        nArray10[5] = 1;
        nArray10[6] = 2;
        nArray10[7] = 2;
        nArray10[8] = 2;
        nArray10[9] = 3;
        nArray10[10] = 3;
        nArray10[11] = 3;
        nArrayArray[12] = nArray10;
        int[] nArray11 = new int[4];
        nArray11[1] = 1;
        nArray11[2] = 2;
        nArray11[3] = 3;
        nArrayArray[13] = nArray11;
        int[] nArray12 = new int[3];
        nArray12[1] = 1;
        nArray12[2] = 2;
        nArrayArray[14] = nArray12;
        int[] nArray13 = new int[3];
        nArray13[1] = 1;
        nArray13[2] = 2;
        nArrayArray[15] = nArray13;
        int[] nArray14 = new int[3];
        nArray14[1] = 1;
        nArray14[2] = 2;
        nArrayArray[16] = nArray14;
        int[] nArray15 = new int[3];
        nArray15[1] = 1;
        nArray15[2] = 2;
        nArrayArray[17] = nArray15;
        int[] nArray16 = new int[3];
        nArray16[1] = 1;
        nArray16[2] = 2;
        nArrayArray[18] = nArray16;
        int[] nArray17 = new int[3];
        nArray17[1] = 1;
        nArray17[2] = 2;
        nArrayArray[19] = nArray17;
        int[] nArray18 = new int[3];
        nArray18[1] = 1;
        nArray18[2] = 2;
        nArrayArray[20] = nArray18;
        int[] nArray19 = new int[3];
        nArray19[1] = 1;
        nArray19[2] = 2;
        nArrayArray[21] = nArray19;
        int[] nArray20 = new int[8];
        nArray20[1] = 1;
        nArray20[2] = 2;
        nArray20[3] = 1;
        nArray20[4] = 2;
        nArray20[5] = 3;
        nArray20[6] = 4;
        nArray20[7] = 5;
        nArrayArray[22] = nArray20;
        int[] nArray21 = new int[3];
        nArray21[1] = 1;
        nArray21[2] = 2;
        nArrayArray[23] = nArray21;
        int[] nArray22 = new int[4];
        nArray22[2] = 1;
        nArray22[3] = 1;
        nArrayArray[24] = nArray22;
        int[] nArray23 = new int[3];
        nArray23[1] = 1;
        nArray23[2] = 2;
        nArrayArray[25] = nArray23;
        int[] nArray24 = new int[3];
        nArray24[1] = 1;
        nArray24[2] = 2;
        nArrayArray[26] = nArray24;
        int[] nArray25 = new int[3];
        nArray25[1] = 1;
        nArray25[2] = 2;
        nArrayArray[27] = nArray25;
        int[] nArray26 = new int[3];
        nArray26[1] = 1;
        nArray26[2] = 2;
        nArrayArray[28] = nArray26;
        int[] nArray27 = new int[3];
        nArray27[1] = 1;
        nArray27[2] = 2;
        nArrayArray[29] = nArray27;
        int[] nArray28 = new int[3];
        nArray28[1] = 1;
        nArray28[2] = 2;
        nArrayArray[30] = nArray28;
        int[] nArray29 = new int[3];
        nArray29[1] = 1;
        nArray29[2] = 2;
        nArrayArray[31] = nArray29;
        int[] nArray30 = new int[3];
        nArray30[1] = 1;
        nArray30[2] = 2;
        nArrayArray[32] = nArray30;
        a = nArrayArray;
        h = new int[]{52, 11, 12, 41, 14, 32, 12, 32, 10, 32, 16, 12, 14, 32, 5, 5, 5, 5, 5, 5, 5, 5, 49, 31, 16, 7, 7, 7, 7, 7, 7, 7, 7};
        i = new int[]{18, 11, 12, 38, 14, 32, 12, 32, 10, 32, 200, 12, 14, 32, 5, 5, 5, 5, 5, 5, 5, 5, 29, 36, 15, 7, 7, 7, 7, 7, 7, 7, 7};
        m = new Image[50];
    }

    public h(int n2, int n3, int n4) {
        this.b = n2;
        this.c = n3;
        this.d = n4;
        this.j = h[n4];
        this.k = i[n4];
        this.f = -1;
        this.l = 1;
    }

    public static void a(int n2) {
        if (m[n2] == null) {
            try {
                if (n2 >= 15 && n2 <= 21) {
                    h.m[n2] = Image.createImage((String)"/g14.png");
                    return;
                }
                if (n2 == 22) {
                    h.m[n2] = Image.createImage((String)"/g15.png");
                    return;
                }
                if (n2 >= 25 && n2 <= 32) {
                    h.m[n2] = Image.createImage((String)"/g18.png");
                    return;
                }
                if (n2 >= 23) {
                    h.m[n2] = Image.createImage((String)("/g" + (n2 - 7) + ".png"));
                    return;
                }
                h.m[n2] = Image.createImage((String)("/g" + n2 + ".png"));
                return;
            }
            catch (Exception exception) {}
        }
    }

    public void a() {
        if (this.d >= 14 && this.d <= 21) {
            switch (this.d) {
                case 14: {
                    this.c -= 2;
                    break;
                }
                case 15: {
                    ++this.b;
                    --this.c;
                    break;
                }
                case 16: {
                    this.b += 2;
                    break;
                }
                case 17: {
                    ++this.b;
                    ++this.c;
                    break;
                }
                case 18: {
                    this.c += 2;
                    break;
                }
                case 19: {
                    --this.b;
                    ++this.c;
                    break;
                }
                case 20: {
                    this.b -= 2;
                    break;
                }
                case 21: {
                    --this.b;
                    --this.c;
                }
            }
            if (q.a(624 - this.b) >= 40 || q.a(82 - this.c) >= 40) {
                this.b = 624;
                this.c = 82;
            }
            if (q.at % 2 == 0) {
                ++this.f;
            }
            if (this.f >= a[this.d].length) {
                this.f = 0;
            }
            if (g.B || q.as != 19) {
                this.f = 0;
                this.g = true;
                return;
            }
        } else if (this.d >= 25 && this.d <= 32) {
            switch (this.d) {
                case 25: {
                    this.c -= this.l;
                    break;
                }
                case 26: {
                    this.b += this.l;
                    this.c -= this.l;
                    break;
                }
                case 27: {
                    this.b += 2 * this.l;
                    break;
                }
                case 28: {
                    this.b += this.l;
                    this.c += this.l;
                    break;
                }
                case 29: {
                    this.c += 2 * this.l;
                    break;
                }
                case 30: {
                    this.b -= this.l;
                    this.c += this.l;
                    break;
                }
                case 31: {
                    this.b -= 2 * this.l;
                    break;
                }
                case 32: {
                    this.b -= this.l;
                    this.c -= this.l;
                }
            }
            ++this.l;
            if (this.b < q.W * 24 || this.b > q.Y * 24 || this.c < q.X * 24 || this.c > q.Z * 24) {
                this.f = 0;
                this.g = true;
                this.l = 0;
            }
            if (q.at % 2 == 0) {
                ++this.f;
            }
            if (this.f >= a[this.d].length) {
                this.f = 0;
                return;
            }
        } else if (this.d == 22) {
            this.b += this.e * this.l;
            if (q.at % 2 == 0) {
                ++this.f;
                ++this.l;
            }
            if (q.a(this.b - e.a) < 28 && q.a(this.c - (e.b - 27)) < 23) {
                e.a += this.e * this.l;
                e.c = 6;
                e.f -= this.l;
            }
            if (q.a(this.b - q.ak) < 15 && q.a(this.c - q.al) < 15 && q.an == 2) {
                this.f = 0;
                this.g = true;
                this.l = 0;
                q.am = -1;
                o.b.addElement(new h(this.b, this.c, 33));
            }
            if (this.f >= a[this.d].length) {
                this.f = 0;
                this.g = true;
                this.l = 0;
                return;
            }
        } else {
            ++this.f;
            if (this.f >= a[this.d].length) {
                this.f = 0;
                this.g = true;
                if (this.d == 13) {
                    try {
                        c.a[10][0] = Image.createImage((String)"/npc/100.png");
                        c.a[10][1] = Image.createImage((String)"/npc/101.png");
                        c.i[10] = 0;
                        return;
                    }
                    catch (IOException iOException) {}
                }
            }
        }
    }

    public final void a(Graphics graphics) {
        if (m[this.d] == null || this.f == -1) {
            return;
        }
        if (this.d != 22 || this.d == 22 && this.e == 1) {
            graphics.drawRegion(m[this.d], 0, a[this.d][this.f] * this.k, this.j, this.k, 0, this.b, this.c, 3);
            return;
        }
        graphics.drawRegion(m[this.d], 0, a[this.d][this.f] * this.k, this.j, this.k, 2, this.b, this.c, 3);
    }
}


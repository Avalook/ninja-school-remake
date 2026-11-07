/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class i {
    private static int[][] T;
    private static int[][] U;
    public static boolean a;
    public static boolean b;
    private static int V;
    private static int W;
    public static boolean c;
    public static boolean d;
    public static boolean e;
    public static boolean f;
    public static boolean g;
    public static boolean h;
    public static boolean i;
    public static boolean j;
    public static boolean k;
    public static int l;
    static int m;
    static int n;
    private static int X;
    static int o;
    private static int Y;
    static int p;
    private static int Z;
    private static int aa;
    static int q;
    static int r;
    static int s;
    static int t;
    static int u;
    private static int ab;
    static int v;
    private static int ac;
    private static int ad;
    private static int ae;
    private static Image[] af;
    private static Image[] ag;
    private static Image[] ah;
    static int w;
    static int x;
    static int y;
    static int z;
    static int A;
    private static int ai;
    private static int aj;
    static int B;
    static int C;
    public static boolean D;
    public static boolean E;
    public static int F;
    public static int G;
    private static int[][] ak;
    private static int[][] al;
    private static int am;
    private static int an;
    public static int H;
    public static int I;
    private static int[][] ao;
    private static int[][] ap;
    public static int J;
    private static int aq;
    public static int K;
    public static int L;
    public static int M;
    public static int N;
    public static int O;
    private static int ar;
    private static int as;
    public static int P;
    public static int Q;
    private static int at;
    static int[] R;
    static int S;
    private static int[] au;
    private static int av;
    private static int aw;
    private static int ax;
    private static int ay;
    private static int az;
    private static Image[] aA;
    private static int[] aB;
    private static int[] aC;
    private static int[] aD;
    private static int[] aE;
    private static int[] aF;

    static {
        B = 0;
        an = 1;
        int[] nArray = new int[57];
        nArray[0] = 1;
        nArray[1] = 1;
        nArray[2] = 1;
        nArray[3] = 2;
        nArray[4] = 2;
        nArray[5] = 2;
        nArray[6] = 3;
        nArray[7] = 3;
        nArray[8] = 3;
        nArray[9] = 7;
        nArray[10] = 7;
        nArray[11] = 7;
        nArray[12] = 3;
        nArray[13] = 3;
        nArray[14] = 3;
        nArray[15] = 7;
        nArray[16] = 7;
        nArray[17] = 7;
        nArray[18] = 3;
        nArray[19] = 3;
        nArray[20] = 3;
        nArray[21] = 7;
        nArray[22] = 7;
        nArray[23] = 7;
        nArray[24] = 3;
        nArray[25] = 3;
        nArray[26] = 3;
        nArray[27] = 7;
        nArray[28] = 7;
        nArray[29] = 7;
        nArray[30] = 3;
        nArray[31] = 3;
        nArray[32] = 3;
        nArray[33] = 7;
        nArray[34] = 7;
        nArray[35] = 7;
        nArray[36] = 3;
        nArray[37] = 3;
        nArray[38] = 3;
        nArray[39] = 7;
        nArray[40] = 7;
        nArray[41] = 7;
        nArray[42] = 3;
        nArray[43] = 3;
        nArray[44] = 3;
        nArray[45] = 7;
        nArray[46] = 7;
        nArray[47] = 7;
        nArray[51] = 4;
        nArray[52] = 4;
        nArray[53] = 4;
        nArray[54] = 6;
        nArray[55] = 6;
        nArray[56] = 6;
        R = nArray;
        int[] nArray2 = new int[15];
        nArray2[3] = 4;
        nArray2[4] = 4;
        nArray2[5] = 4;
        nArray2[6] = 6;
        nArray2[7] = 6;
        nArray2[8] = 6;
        nArray2[9] = 6;
        nArray2[10] = 6;
        nArray2[11] = 6;
        nArray2[12] = 6;
        nArray2[13] = 6;
        nArray2[14] = 6;
        au = nArray2;
        av = -1;
        aA = new Image[2];
        aB = new int[5];
        aC = new int[5];
        aD = new int[5];
        aE = new int[5];
        aF = new int[5];
    }

    public static void a(int n2) {
        E = false;
        if (n2 == 7 && !q.i[7]) {
            E = true;
            H = 2760;
            I = 288;
            L = 1;
            J = 0;
        }
        if (n2 == 14 && !i && d.g) {
            H = 384;
            I = 336;
            L = -1;
            E = true;
            K = 2;
        }
        if (E || n2 == 14 && !i) {
            O = 9000;
            N = 9000;
            M = 500;
            int[][] nArrayArray = new int[8][];
            int[] nArray = new int[6];
            nArray[1] = 2;
            nArray[4] = -10;
            nArrayArray[0] = nArray;
            int[] nArray2 = new int[6];
            nArray2[1] = 4;
            nArray2[4] = -8;
            nArrayArray[1] = nArray2;
            int[] nArray3 = new int[6];
            nArray3[1] = 4;
            nArray3[4] = -8;
            nArrayArray[2] = nArray3;
            int[] nArray4 = new int[6];
            nArray4[1] = 6;
            nArray4[4] = -8;
            nArrayArray[3] = nArray4;
            int[] nArray5 = new int[6];
            nArray5[1] = 2;
            nArray5[4] = -16;
            nArrayArray[4] = nArray5;
            int[] nArray6 = new int[6];
            nArray6[1] = 2;
            nArray6[2] = 3;
            nArray6[4] = -16;
            nArray6[5] = -17;
            nArrayArray[5] = nArray6;
            int[] nArray7 = new int[6];
            nArray7[1] = 2;
            nArray7[4] = -16;
            nArrayArray[6] = nArray7;
            int[] nArray8 = new int[6];
            nArray8[1] = 6;
            nArray8[4] = -8;
            nArrayArray[7] = nArray8;
            ao = nArrayArray;
            int[][] nArrayArray2 = new int[8][];
            nArrayArray2[0] = new int[]{1, 3, 6};
            int[] nArray9 = new int[3];
            nArray9[1] = 3;
            nArray9[2] = 6;
            nArrayArray2[1] = nArray9;
            nArrayArray2[2] = new int[]{1, 3, 6};
            int[] nArray10 = new int[3];
            nArray10[1] = 4;
            nArray10[2] = 6;
            nArrayArray2[3] = nArray10;
            nArrayArray2[4] = new int[]{1, 2, 6};
            int[] nArray11 = new int[3];
            nArray11[1] = 2;
            nArray11[2] = 5;
            nArrayArray2[5] = nArray11;
            int[] nArray12 = new int[3];
            nArray12[1] = 2;
            nArray12[2] = 6;
            nArrayArray2[6] = nArray12;
            nArrayArray2[7] = new int[]{1, 4, 6};
            ap = nArrayArray2;
            ah = new Image[7];
            n2 = 0;
            while (n2 < 6) {
                i.ah[n2] = q.c("/mob/t" + n2 + ".png");
                ++n2;
            }
            i.ah[6] = q.c("/cp/cb0.png");
            i.aA[0] = q.c("/e/coc0.png");
            i.aA[1] = q.c("/e/coc1.png");
        }
    }

    public static void a() {
        D = false;
        if (q.ap != 16 && q.ap != 0) {
            return;
        }
        D = true;
        int[][] nArrayArray = new int[2][];
        int[] nArray = new int[6];
        nArray[0] = 6;
        nArray[2] = -22;
        nArray[3] = -9;
        nArray[5] = -16;
        nArrayArray[0] = nArray;
        int[] nArray2 = new int[6];
        nArray2[0] = 6;
        nArray2[2] = -21;
        nArray2[3] = -8;
        nArray2[5] = -15;
        nArrayArray[1] = nArray2;
        ak = nArrayArray;
        int[][] nArrayArray2 = new int[2][];
        int[] nArray3 = new int[3];
        nArray3[0] = 1;
        nArray3[2] = 2;
        nArrayArray2[0] = nArray3;
        int[] nArray4 = new int[3];
        nArray4[0] = 1;
        nArray4[2] = 2;
        nArrayArray2[1] = nArray4;
        al = nArrayArray2;
        ag = new Image[3];
        int n2 = 0;
        while (n2 < 3) {
            i.ag[n2] = q.c("/mob/s" + n2 + ".png");
            ++n2;
        }
        if (q.ap == 16) {
            F = 231;
            G = 216;
        }
        if (q.ap == 0) {
            F = 1479;
            G = 624;
        }
    }

    public static void b(int n2) {
        a = false;
        if (n2 == 7) {
            if (c) {
                return;
            }
            V = 0;
            a = true;
        }
        if (n2 == 4) {
            if (g) {
                return;
            }
            V = 4;
            a = true;
        }
        if (n2 == 6) {
            V = 2;
            a = true;
        }
        if (n2 == 0) {
            if (d) {
                return;
            }
            V = 1;
            a = true;
        }
        if (n2 == 11) {
            if (e) {
                return;
            }
            V = 3;
            a = true;
        }
        if (n2 == 12) {
            if (f || p.h != 8) {
                return;
            }
            V = 5;
            a = true;
        }
        if (a) {
            u = 0;
            ab = -1;
            s = -1;
            v = 0;
            t = 3;
            j = false;
            A = 0;
            switch (V) {
                case 0: {
                    W = 400;
                    af = new Image[12];
                    n2 = 0;
                    while (n2 < 11) {
                        i.af[n2] = q.c("/mob/nhen" + n2 + ".png");
                        ++n2;
                    }
                    i.af[11] = q.c("/cp/cb0.png");
                    w = 36;
                    x = 50;
                    y = 22;
                    z = 25;
                    m = 3024;
                    n = 600;
                    Y = 552;
                    p = 648;
                    aa = 150;
                    q = 4000;
                    r = 4000;
                    int[][] nArrayArray = new int[6][];
                    int[] nArray = new int[8];
                    nArray[1] = -11;
                    nArray[2] = -12;
                    nArray[3] = -13;
                    nArray[5] = -34;
                    nArray[6] = -18;
                    nArray[7] = -10;
                    nArrayArray[0] = nArray;
                    int[] nArray2 = new int[8];
                    nArray2[1] = -11;
                    nArray2[2] = -12;
                    nArray2[3] = -13;
                    nArray2[5] = -34;
                    nArray2[6] = -18;
                    nArray2[7] = -10;
                    nArrayArray[1] = nArray2;
                    int[] nArray3 = new int[8];
                    nArray3[0] = 1;
                    nArray3[1] = -11;
                    nArray3[2] = -12;
                    nArray3[3] = -13;
                    nArray3[5] = -34;
                    nArray3[6] = -18;
                    nArray3[7] = -10;
                    nArrayArray[2] = nArray3;
                    int[] nArray4 = new int[8];
                    nArray4[1] = -11;
                    nArray4[2] = -12;
                    nArray4[3] = -13;
                    nArray4[5] = -34;
                    nArray4[6] = -18;
                    nArray4[7] = -10;
                    nArrayArray[3] = nArray4;
                    int[] nArray5 = new int[8];
                    nArray5[0] = -4;
                    nArray5[1] = -11;
                    nArray5[2] = -12;
                    nArray5[3] = -13;
                    nArray5[5] = -34;
                    nArray5[6] = -18;
                    nArray5[7] = -10;
                    nArrayArray[4] = nArray5;
                    nArrayArray[5] = new int[]{-4, -11, -8, -13, -5, -34, -20, -3};
                    T = nArrayArray;
                    int[][] nArrayArray2 = new int[6][];
                    nArrayArray2[0] = new int[]{1, 5, 6, 8};
                    nArrayArray2[1] = new int[]{1, 4, 7, 9};
                    int[] nArray6 = new int[4];
                    nArray6[1] = 5;
                    nArray6[2] = 7;
                    nArray6[3] = 8;
                    nArrayArray2[2] = nArray6;
                    nArrayArray2[3] = new int[]{1, 5, 7, 8};
                    nArrayArray2[4] = new int[]{2, 4, 6, 9};
                    nArrayArray2[5] = new int[]{3, 11, 11, 10};
                    U = nArrayArray2;
                    return;
                }
                case 1: {
                    W = 300;
                    aa = 70;
                    q = 3000;
                    r = 3000;
                    w = 50;
                    x = 50;
                    y = 40;
                    z = 25;
                    Y = 144;
                    p = 384;
                    af = new Image[14];
                    n2 = 0;
                    while (n2 < 14) {
                        i.af[n2] = q.c("/mob/cao" + n2 + ".png");
                        ++n2;
                    }
                    m = 12 * k.f + 12;
                    n = 28 * k.f;
                    t = 3;
                    int[][] nArrayArray = new int[8][];
                    nArrayArray[0] = new int[]{-1, -6, -19, 2, -28, -18, -8, 1, 1, -7};
                    nArrayArray[1] = new int[]{-3, -6, -19, 3, -28, -19, -9, 1, 1, -8};
                    nArrayArray[2] = new int[]{-3, -6, -20, 2, -28, -19, -9, 1, 1, -9};
                    int[] nArray = new int[10];
                    nArray[0] = 1;
                    nArray[1] = -5;
                    nArray[2] = -20;
                    nArray[3] = 4;
                    nArray[4] = -27;
                    nArray[5] = -19;
                    nArray[6] = -10;
                    nArray[9] = -9;
                    nArrayArray[3] = nArray;
                    nArrayArray[4] = new int[]{2, -5, -18, 4, -23, -18, -9, -4, 1, -16};
                    nArrayArray[5] = new int[]{-1, -6, -19, 2, -28, -20, -11, -1, -1, -11};
                    nArrayArray[6] = new int[]{-1, -6, -19, 2, -28, -18, -8, 1, 1, -9};
                    int[] nArray7 = new int[10];
                    nArray7[1] = -5;
                    nArray7[2] = -19;
                    nArray7[3] = 3;
                    nArray7[4] = -28;
                    nArray7[5] = -19;
                    nArray7[6] = -8;
                    nArray7[7] = 1;
                    nArray7[8] = 1;
                    nArray7[9] = -7;
                    nArrayArray[7] = nArray7;
                    T = nArrayArray;
                    int[][] nArrayArray3 = new int[8][];
                    int[] nArray8 = new int[5];
                    nArray8[0] = 6;
                    nArray8[1] = 5;
                    nArray8[2] = 7;
                    nArray8[3] = 8;
                    nArrayArray3[0] = nArray8;
                    int[] nArray9 = new int[5];
                    nArray9[0] = 6;
                    nArray9[1] = 5;
                    nArray9[2] = 9;
                    nArray9[3] = 10;
                    nArrayArray3[1] = nArray9;
                    int[] nArray10 = new int[5];
                    nArray10[0] = 6;
                    nArray10[1] = 5;
                    nArray10[2] = 11;
                    nArray10[3] = 12;
                    nArrayArray3[2] = nArray10;
                    int[] nArray11 = new int[5];
                    nArray11[0] = 6;
                    nArray11[1] = 5;
                    nArray11[2] = 3;
                    nArray11[3] = 4;
                    nArrayArray3[3] = nArray11;
                    nArrayArray3[4] = new int[]{6, 13, 3, 4, 1};
                    nArrayArray3[5] = new int[]{6, 5, 3, 4, 2};
                    int[] nArray12 = new int[5];
                    nArray12[0] = 6;
                    nArray12[1] = 5;
                    nArray12[2] = 9;
                    nArray12[3] = 10;
                    nArrayArray3[6] = nArray12;
                    nArrayArray3[7] = new int[]{6, 5, 11, 12, 1};
                    U = nArrayArray3;
                    ai = 5;
                    return;
                }
                case 2: {
                    aa = 60;
                    q = 3500;
                    r = 3500;
                    w = 60;
                    x = 40;
                    y = 30;
                    z = 40;
                    af = new Image[5];
                    n2 = 0;
                    while (n2 < 5) {
                        i.af[n2] = q.c("/mob/s" + n2 + ".png");
                        ++n2;
                    }
                    t = 3;
                    int[][] nArrayArray = new int[2][];
                    int[] nArray = new int[10];
                    nArray[0] = 6;
                    nArray[2] = -23;
                    nArray[3] = -28;
                    nArray[4] = -12;
                    nArray[5] = -9;
                    nArray[7] = -16;
                    nArray[8] = -12;
                    nArray[9] = -10;
                    nArrayArray[0] = nArray;
                    int[] nArray13 = new int[10];
                    nArray13[0] = 6;
                    nArray13[2] = -24;
                    nArray13[3] = -28;
                    nArray13[4] = -13;
                    nArray13[5] = -8;
                    nArray13[7] = -15;
                    nArray13[8] = -13;
                    nArray13[9] = -9;
                    nArrayArray[1] = nArray13;
                    T = nArrayArray;
                    int[][] nArrayArray4 = new int[2][];
                    int[] nArray14 = new int[5];
                    nArray14[0] = 1;
                    nArray14[2] = 2;
                    nArray14[3] = 3;
                    nArray14[4] = 4;
                    nArrayArray4[0] = nArray14;
                    int[] nArray15 = new int[5];
                    nArray15[0] = 1;
                    nArray15[2] = 2;
                    nArray15[3] = 3;
                    nArray15[4] = 4;
                    nArrayArray4[1] = nArray15;
                    U = nArrayArray4;
                    m = 52 * k.f + 12;
                    n = 8 * k.f;
                    ai = 5;
                    return;
                }
                case 3: {
                    W = 400;
                    aa = 250;
                    q = 6000;
                    r = 6000;
                    w = 35;
                    x = 44;
                    y = 30;
                    z = 20;
                    Y = 3000;
                    p = 3264;
                    af = new Image[4];
                    n2 = 0;
                    while (n2 < 4) {
                        i.af[n2] = q.c("/mob/r" + n2 + ".png");
                        ++n2;
                    }
                    m = 129 * k.f + 12;
                    n = 18 * k.f;
                    t = 3;
                    T = new int[][]{new int[2], new int[2], new int[2], new int[2]};
                    U = new int[][]{new int[1], {1}, {2}, {3}};
                    ai = 1;
                    return;
                }
                case 4: {
                    W = 600;
                    aa = 300;
                    q = 6000;
                    r = 6000;
                    w = 50;
                    x = 35;
                    y = 30;
                    z = 40;
                    Y = 4776;
                    p = 5088;
                    af = new Image[5];
                    n2 = 0;
                    while (n2 < 5) {
                        i.af[n2] = q.c("/mob/ca" + n2 + ".png");
                        ++n2;
                    }
                    m = 206 * k.f + 12;
                    Z = n = 19 * k.f;
                    t = 3;
                    ad = 432;
                    int[][] nArrayArray = new int[4][];
                    int[] nArray = new int[4];
                    nArray[1] = 27;
                    nArray[3] = -5;
                    nArrayArray[0] = nArray;
                    nArrayArray[1] = new int[]{-1, 22, -1, -8};
                    nArrayArray[2] = new int[]{-6, 23, -5, -7};
                    int[] nArray16 = new int[4];
                    nArray16[0] = 3;
                    nArray16[1] = 27;
                    nArray16[3] = -5;
                    nArrayArray[3] = nArray16;
                    T = nArrayArray;
                    int[][] nArrayArray5 = new int[4][];
                    int[] nArray17 = new int[2];
                    nArray17[1] = 3;
                    nArrayArray5[0] = nArray17;
                    int[] nArray18 = new int[2];
                    nArray18[1] = 4;
                    nArrayArray5[1] = nArray18;
                    nArrayArray5[2] = new int[]{1, 3};
                    nArrayArray5[3] = new int[]{2, 4};
                    U = nArrayArray5;
                    ai = 2;
                    return;
                }
                case 5: {
                    W = 500;
                    aa = 350;
                    q = 6500;
                    r = 6500;
                    w = 36;
                    x = 32;
                    y = 30;
                    z = 20;
                    Y = 3000;
                    p = 3336;
                    af = new Image[12];
                    n2 = 0;
                    while (n2 < 11) {
                        i.af[n2] = q.c("/mob/p" + n2 + ".png");
                        ++n2;
                    }
                    i.af[11] = q.c("/cp/cb0.png");
                    m = 3 * k.f + 12;
                    n = 14 * k.f;
                    t = 3;
                    int[][] nArrayArray = new int[6][];
                    int[] nArray = new int[8];
                    nArray[2] = 3;
                    nArray[3] = -4;
                    nArray[5] = -5;
                    nArray[6] = 10;
                    nArray[7] = -8;
                    nArrayArray[0] = nArray;
                    nArrayArray[1] = new int[]{-1, -5, -1, -7, -1, -1, 9, -12};
                    int[] nArray19 = new int[8];
                    nArray19[0] = -1;
                    nArray19[2] = -1;
                    nArray19[3] = -7;
                    nArray19[4] = -1;
                    nArray19[5] = 2;
                    nArray19[6] = 9;
                    nArray19[7] = -12;
                    nArrayArray[2] = nArray19;
                    int[] nArray20 = new int[8];
                    nArray20[3] = -9;
                    nArray20[7] = 5;
                    nArrayArray[3] = nArray20;
                    nArrayArray[4] = new int[]{1, 1, 1, -8, 1, 1, 1, 6};
                    int[] nArray21 = new int[8];
                    nArray21[1] = 1;
                    nArray21[3] = -6;
                    nArray21[6] = 10;
                    nArray21[7] = -12;
                    nArrayArray[5] = nArray21;
                    T = nArrayArray;
                    int[][] nArrayArray6 = new int[6][];
                    int[] nArray22 = new int[4];
                    nArray22[0] = 9;
                    nArray22[2] = 5;
                    nArray22[3] = 7;
                    nArrayArray6[0] = nArray22;
                    nArrayArray6[1] = new int[]{9, 1, 6, 7};
                    nArrayArray6[2] = new int[]{9, 10, 6, 7};
                    nArrayArray6[3] = new int[]{11, 3, 11, 7};
                    nArrayArray6[4] = new int[]{11, 4, 11, 7};
                    nArrayArray6[5] = new int[]{9, 2, 6, 8};
                    U = nArrayArray6;
                    ai = 4;
                }
            }
        }
    }

    public static void b() {
        if (!D) {
            return;
        }
        if (q.aq % 8 == 0) {
            am = am == 0 ? 1 : 0;
        }
    }

    public static void c() {
        if (!E) {
            return;
        }
        switch (K) {
            case 1: {
                return;
            }
            case 2: {
                if (q.aq % 3 == 0) {
                    int n2 = J = J == 1 ? 2 : 1;
                }
                if (q.az) break;
                if (H < q.N * 24 || H < 48) {
                    L = 1;
                }
                if (H > q.P * 24 || H > 624) {
                    L = -1;
                }
                if (q.aq % 10 == 0) {
                    as = 5;
                }
                if (I < 360) {
                    I += 2;
                }
                if (I > h.ah) {
                    I -= 2;
                }
                H += as * L;
                if (q.az || K == 4 || K == 7) break;
                if (q.aq % 50 == 0) {
                    K = 4;
                    ar = 0;
                    aq = 1;
                }
                if (q.aq % 100 == 0) {
                    K = 4;
                    ar = 0;
                    aq = 0;
                    L = h.ag > H ? 1 : -1;
                    as = q.a(H - h.ag) / 7 * L;
                    P = q.a(I - h.ah) / 7;
                    if (I > h.ah) {
                        P = -P;
                    }
                }
                return;
            }
            case 3: {
                J = 0;
                return;
            }
            case 4: {
                if (b) {
                    K = 2;
                }
                ++ar;
                if (aq == 1) {
                    int n3;
                    J = 3;
                    if (ar != 15) break;
                    i.aB[2] = n3 = h.ag;
                    i.aE[2] = 1;
                    i.aD[2] = 4;
                    i.aC[2] = 408;
                    n3 = 0;
                    while (n3 < 2) {
                        i.aB[n3] = aB[2] - (2 - n3) * 20;
                        i.aB[n3 + 3] = aB[2] + (n3 + 1) * 20;
                        i.aE[n3 + 3] = 1;
                        i.aE[n3] = 1;
                        i.aD[n3] = n3;
                        i.aD[n3 + 3] = 4 - (n3 + 3);
                        i.aC[n3 + 3] = 408;
                        i.aC[n3] = 408;
                        n3 = (byte)(n3 + 1);
                    }
                    ar = 0;
                    K = 2;
                    return;
                }
                J = 0;
                H += as;
                I += P;
                if (ar < 8) {
                    if (q.a(H - h.ag) < 10 && q.a(I - h.ah) < 10) {
                        h.a(M, L);
                        ar = 7;
                        if (I > 0) {
                            P = -P;
                        }
                    }
                    if (ar == 7 && I > 0) {
                        P = -P;
                    }
                }
                if (ar <= 13) break;
                P = 0;
                as = q.b(2, 5);
                K = 2;
                ar = 0;
                return;
            }
            case 6: {
                J = 5;
                if (++ar < 10) {
                    H += ar * h.as;
                    I -= ar;
                    if (ar != 9) break;
                    as = L * 5;
                    return;
                }
                if (ar < 20) {
                    H += as;
                    return;
                }
                L = h.ag > H ? 1 : -1;
                as = q.a(h.ag - H) / 6 * L;
                P = q.a(h.ah - I) / 6;
                ar = 0;
                K = 4;
                aq = 0;
                return;
            }
            case 7: {
                J = au[S];
                --I;
                if (++S != au.length) break;
                int n4 = 0;
                while (n4 < 10) {
                    int n5 = q.b(q.P * 24, (q.P + 3) * 24);
                    int n6 = q.b(240, 360);
                    n.a(n4 + 2, n5, n6);
                    n4 = (byte)(n4 + 1);
                }
                K = 2;
                return;
            }
            case 9: {
                if (!b) {
                    K = 2;
                }
                J = 5;
                if (k.a(H, I, 2)) {
                    I = k.d(I);
                    return;
                }
                I += P;
                if (q.aq % 2 != 0) break;
                ++P;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void d() {
        int n2;
        if (!a) {
            return;
        }
        block0 : switch (t) {
            case 1: {
                A = 0;
                switch (V) {
                    case 0: {
                        ++at;
                        u = 2;
                        n += o;
                        ++o;
                        if (n <= q.Q * 24 + x) break;
                        a = false;
                        h.d(W);
                        break;
                    }
                    case 1: {
                        n += o;
                        ++o;
                        ++v;
                        m += X;
                        if (X > 1) {
                            --X;
                        }
                        if (X < -1) {
                            ++X;
                        }
                        if (n <= q.Q * 24 + x) break;
                        a = false;
                        h.d(W);
                        break;
                    }
                    case 3: {
                        n += v;
                        ++v;
                        m += X;
                        if (X > 1) {
                            --X;
                        }
                        if (X < -1) {
                            ++X;
                        }
                        if (n <= q.Q * 24 + x) break;
                        a = false;
                        q.f(24);
                        c.m = false;
                        h.d(W);
                        break;
                    }
                    case 4: {
                        ++ac;
                        u = 3;
                        if ((m += s << 3) < Y) {
                            m = Y + 10;
                            s = 1;
                        } else if (m > p) {
                            m = p - 10;
                            s = -1;
                        }
                        n += o;
                        ++o;
                        if (n > ad) {
                            q.d(m, ad - 12);
                        }
                        if (n <= q.Q * 24 + x) break;
                        a = false;
                        h.d(W);
                        break;
                    }
                    case 5: {
                        q = 0;
                        v = -5;
                        ac = -(s << 3);
                        o = -10;
                        f = true;
                        o.a.addElement(new j(m, n - 15, 25));
                        q.a(m, n, 4, -6, 50);
                        q.a(m, n, -5, -7, 50);
                        q.a(m, n, -4, -8, 50);
                        q.a(m, n, 5, -9, 50);
                        q.a(m, n, 0, -10, 50);
                        a = false;
                        h.d(W);
                        break;
                    }
                }
                break;
            }
            case 6: {
                switch (V) {
                    case 0: {
                        ++v;
                        u = 5;
                        if (!b) {
                            if (n > Y) {
                                n -= 2;
                            }
                            if (v == 12) {
                                i.g();
                            }
                            if (v <= 12) break;
                            t = 3;
                            v = 0;
                            ac = 15;
                            s = -1;
                            break;
                        }
                        t = 3;
                        v = 0;
                        s = -1;
                        break;
                    }
                    case 1: {
                        ab = -1;
                        u = 5;
                        ++v;
                        if (b) {
                            n += o;
                            m += h.as;
                            if (++o == 2) {
                                o = 0;
                                t = 3;
                            }
                            return;
                        }
                        n += o;
                        o += 2;
                        if (!k.a(m += h.as * 3, n, 2)) break;
                        n = k.d(n);
                        s = h.ag > m ? -1 : 1;
                        t = 4;
                        ab = 3;
                        v = 10;
                        X = 7;
                        if (m >= p) {
                            s = -1;
                        }
                        if (m > Y) break;
                        s = 1;
                        break;
                    }
                    case 3: {
                        if (m < Y) {
                            m += q.b(10, 15);
                            s = 1;
                        }
                        if (m > p) {
                            m -= q.b(10, 15);
                            s = -1;
                        }
                        m -= s << 2;
                        u = 3;
                        n = n < 432 ? (n -= ae) : 432;
                        if (++ae < 12) break;
                        t = 2;
                        ae = 0;
                        ad = 0;
                        break;
                    }
                    case 4: {
                        if (b) {
                            u = 3;
                            n += o;
                            ++o;
                            break;
                        }
                        ++ac;
                        u = 3;
                        if ((m += s << 3) < Y) {
                            m = Y + 10;
                            s = 1;
                        } else if (m > p) {
                            m = p - 10;
                            s = -1;
                        }
                        n += o;
                        ++o;
                        if (n > Z) {
                            n = Z;
                            i.g();
                            s = -s;
                        }
                        if (n <= ad) break;
                        q.d(m, ad - 12);
                        break;
                    }
                    case 5: {
                        ++v;
                        if (!b) {
                            m += X * h.as;
                            n -= o;
                        }
                        if (v == 8) {
                            t = 2;
                            v = 0;
                            ae = 0;
                            ad = 0;
                        }
                        u = 5;
                        break;
                    }
                }
                break;
            }
            case 11: {
                if (b) {
                    return;
                }
                switch (V) {
                    case 4: {
                        if (++v >= 8) {
                            v = 0;
                        }
                        if (++u > 2) {
                            u = 0;
                        }
                        n = 432;
                        if ((m += s << 3) < Y) {
                            m = Y + 10;
                            s = 1;
                            ac = 0;
                            v = 0;
                            t = 3;
                            n = Z;
                        } else if (m > p) {
                            m = p - 10;
                            s = -1;
                            ac = 0;
                            v = 0;
                            t = 2;
                            aj = 20;
                            n = Z;
                        }
                        q.d(m - (s << 4), ad - 12);
                        i.h();
                        break block0;
                    }
                }
                break;
            }
            case 4: {
                if (b) {
                    return;
                }
                switch (V) {
                    case 0: {
                        u = 4;
                        ++v;
                        if (h.ak == 5) {
                            s = h.ah < 600 ? 1 : -1;
                        }
                        if (v > 5) {
                            t = 3;
                            v = 0;
                            ac = 0;
                            n += s * 3;
                        }
                        if (v != 4 || h.ak == 7 || q.a(m - h.ag - 11) >= 40 || q.a(n - h.ah) >= 30) break;
                        v = 0;
                        ac = 0;
                        h.a(aa, s);
                        t = 2;
                        s = -1;
                        break;
                    }
                    case 1: {
                        if (ab == 1) {
                            if (++v == 15) {
                                if (m < Y && h.ag < m) {
                                    s = 1;
                                    X = 0;
                                    v = 0;
                                    ab = 2;
                                }
                                if (m > p && h.ag > m) {
                                    s = -1;
                                    ab = 2;
                                    X = 0;
                                    v = 0;
                                }
                                t = 2;
                                ab = -1;
                                v = 0;
                            }
                            if (u == 4 && h.ak != 7 && h.ah <= n && q.a(h.ah - n) < 18 && q.a(m + (s << 5) - h.ag) < 15) {
                                h.a(aa, s);
                                v = 0;
                                if (m > Y + 40 && m < p - 20) {
                                    ab = 3;
                                    X = 6;
                                } else {
                                    t = 2;
                                }
                            }
                            if (v % 3 == 0) {
                                u = u == 3 ? 4 : 3;
                            }
                            m += X;
                        }
                        if (ab == 3) {
                            if (++v % 3 == 0) {
                                u = u == 3 ? 4 : 3;
                            }
                            m += s * X;
                            if (v == 20) {
                                ab = 2;
                                v = 0;
                                int n3 = s = h.ag > m ? 1 : -1;
                            }
                        }
                        if (ab != 2) break;
                        u = 7;
                        if (++v == 3) {
                            q.b(m + s * 39, n - 11, s << 3, aa, 3);
                        }
                        if (v != 5) break;
                        v = 0;
                        t = 3;
                        ab = -1;
                        ad = 0;
                        break;
                    }
                    case 3: {
                        ++v;
                        if (ab == 1) {
                            u = 2;
                            if (q.a(h.ag - (m += X)) < 20 && q.a(h.ah - n) < 15) {
                                h.a(aa, s);
                                ab = -1;
                            }
                        }
                        if (ab == 2) {
                            u = 1;
                            n2 = s;
                            if (av <= 0) {
                                av = 1;
                                aw = n2;
                                az = q.a(n - 29 - (h.ah - 12)) >= 17 ? q.S + 30 : q.a(h.ag - ax) - 10 * aw;
                                ax = m + 2 * aw - (aw == -1 ? az : 0);
                                ay = n - 29;
                            }
                            ab = -1;
                            v = 0;
                            t = 3;
                        }
                        if (v == 15) {
                            v = 0;
                            ab = -1;
                            t = 2;
                        }
                        if (m < Y) {
                            m = Y + 10;
                            s = 1;
                            ac = 0;
                            v = 0;
                            t = 3;
                            break;
                        }
                        if (m <= p) break;
                        m = p - 10;
                        s = -1;
                        ac = 0;
                        v = 0;
                        t = 2;
                        break;
                    }
                    case 4: {
                        u = ++ac > 4 ? 2 : 0;
                        m += s << 1;
                        if (aj > 0) {
                            --aj;
                            m += s << 3;
                        }
                        if (m < Y + 10) {
                            m = Y + 10;
                            s = 1;
                        } else if (m > p - 10) {
                            m = p - 10;
                            s = -1;
                        }
                        n += o;
                        ++o;
                        if (n > Z) {
                            n = Z;
                            i.g();
                        }
                        if (n > ad) {
                            q.d(m, ad - 12);
                        }
                        i.h();
                        break;
                    }
                    case 5: {
                        if (ab == 1) {
                            u = 0;
                            n = n < h.ah ? (n += v) : (n -= ++v);
                            if (q.a(m - h.ag) < y && q.a(n - h.ah) < z) {
                                h.a(aa, s);
                                v = 0;
                                t = 7;
                            }
                            if (v >= 20) {
                                v = 0;
                                t = 7;
                            }
                        }
                        if (ab != 2) break;
                        ++v;
                        m += X * s;
                        n = n < h.ah ? (n += o) : (n -= o);
                        if (q.aq % 2 == 0) {
                            int n4 = u = u == 4 ? 3 : 4;
                        }
                        if (q.a(m - h.ag) < y && q.a(n - h.ah) < z) {
                            h.a(aa, s);
                            v = 0;
                            t = 7;
                        }
                        if (v < 6) break;
                        v = 0;
                        t = 7;
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (V) {
                    case 0: {
                        ++ac;
                        if (++v % 4 == 0) {
                            X = X == -1 ? 1 : -1;
                            m += X;
                            n -= X;
                        }
                        if (ac >= 20) {
                            v = 0;
                            ac = 0;
                            t = 2;
                        }
                        if (v > 10) {
                            v = 0;
                        }
                        if (v > 7) {
                            u = 0;
                            break;
                        }
                        u = 1;
                        break;
                    }
                    case 1: {
                        if (q.aq % 3 == 0) {
                            int n5 = u = u == 1 ? 6 : 1;
                        }
                        if (++v < 30) break;
                        v = 0;
                        t = 2;
                        s = h.ag > m ? 1 : -1;
                        X = s << 1;
                        break;
                    }
                    case 2: {
                        if (q.aq % 8 != 0) break;
                        u = u == 0 ? 1 : 0;
                        break;
                    }
                    case 3: {
                        if (q.aq % 5 == 0 && ++u > 1) {
                            u = 0;
                        }
                        if (!b && q.aq % 3 == 0) {
                            m += 3 * s;
                        }
                        if (++v == 15) {
                            v = 0;
                            t = 2;
                        }
                        if (m < Y) {
                            m += q.b(10, 15);
                            s = 1;
                        }
                        if (m <= p) break;
                        m -= q.b(10, 15);
                        s = -1;
                        break;
                    }
                    case 4: {
                        if (n < 432) {
                            n += o;
                            ++o;
                        }
                        u = 1;
                        ++v;
                        if (++ac == 20) {
                            i.g();
                            return;
                        }
                        if (v > 8) {
                            v = 0;
                        }
                        if (v <= 3) break;
                        u = 0;
                        break;
                    }
                    case 5: {
                        if (++v == 10) {
                            v = 0;
                            t = 2;
                        }
                        if (q.aq % 5 == 0) {
                            ++u;
                        }
                        if (u <= 1) break;
                        u = 0;
                        break;
                    }
                }
                break;
            }
            case 2: {
                if (b) {
                    t = 3;
                    return;
                }
                switch (V) {
                    case 0: {
                        if (++v > 5) {
                            v = 0;
                        }
                        if (v > 3) {
                            u = 2;
                            n += s << 2;
                        } else {
                            u = 3;
                        }
                        if (v > 3 && q.a(m - h.ag - 11) < 20 && q.a(n - h.ah) < 30) {
                            t = 4;
                        }
                        if (q.a(h.ag + 11 - m) < 50 && q.a(h.ah - n) < 70) {
                            ac = 0;
                            v = 0;
                            t = 7;
                        }
                        if (n < Y) {
                            t = 3;
                            n = Y;
                            s = 1;
                        }
                        if (n > p) {
                            t = 3;
                            n = p;
                            s = -1;
                            break;
                        }
                        break block0;
                    }
                    case 1: {
                        if (m < Y) {
                            s = 1;
                        }
                        if (m > p) {
                            s = -1;
                        }
                        if (q.aq % 5 == 0) {
                            i.g();
                        }
                        if (q.aq % q.b(10, 100) == 0 && q.a(m - h.ag) > 100) {
                            t = 3;
                            v = 0;
                            break;
                        }
                        if (q.aq % 3 == 0) {
                            if (++u > 2) {
                                u = 0;
                            }
                            m += s << 1;
                        }
                    }
                    case 3: {
                        if (q.aq % 5 == 0 && ++u > 1) {
                            u = 0;
                        }
                        if ((m += 2 * s) < Y) {
                            m += q.b(10, 15);
                            s = 1;
                        }
                        if (m > p) {
                            m -= q.b(10, 15);
                            s = -1;
                        }
                        i.g();
                        break;
                    }
                    case 4: {
                        if (n < 420) {
                            n += v;
                        }
                        u = 1;
                        if (++v > 8) {
                            v = 0;
                        }
                        if (v > 3) {
                            u = 0;
                        }
                        m += s << 3;
                        if (aj > 0) {
                            --aj;
                            m += s << 1;
                        }
                        if (m < Y) {
                            m = Y + 10;
                            s = 1;
                        } else if (m > p) {
                            m = p - 10;
                            s = -1;
                        }
                        if (q.aq % 4 == 0) {
                            if (q.a(h.ag - m - (s << 6)) >= 20) break;
                            ++ae;
                            i.g();
                            break;
                        }
                        break block0;
                    }
                    case 5: {
                        if (m < 50) {
                            m = 50;
                        }
                        if (m > k.c - 50) {
                            m = k.c - 50;
                        }
                        if (n < 50) {
                            n = 50;
                        }
                        if (n > k.d - 20) {
                            n = k.d - 20;
                        }
                        if (m < h.ag) {
                            X = q.aq % 3 + 3;
                            if (q.a(m - h.ag) > 20) {
                                s = 1;
                            }
                        } else if (m > h.ag) {
                            X = -q.aq % 3 - 3;
                            if (q.a(m - h.ag) > 20) {
                                s = -1;
                            }
                        }
                        if (q.aq % 2 == 0) {
                            if (n < h.ah) {
                                o = q.aq % 3 + 3;
                            } else if (n > h.ah) {
                                o = -q.aq % 3 - 3;
                            }
                        }
                        if (q.aq % 2 == 0) {
                            if (u == 0) {
                                u = 1;
                            } else if (u == 1) {
                                u = 2;
                            } else if (u == 2) {
                                u = 0;
                            }
                            if (q.a(m - h.ag) > 20) {
                                m += X;
                                n += o;
                            } else {
                                t = 4;
                                v = 0;
                                ab = 1;
                            }
                        }
                        if (q.aq % q.b(8, 50) == 0) {
                            v = 0;
                            t = 3;
                        }
                        if (q.aq % 8 == 0) {
                            i.g();
                            break;
                        }
                        break block0;
                    }
                }
                break;
            }
            case 7: {
                if (j) {
                    return;
                }
                switch (V) {
                    case 0: {
                        if (++v > 3) {
                            v = 0;
                        }
                        if (v > 1) {
                            u = 2;
                            n += s << 3;
                        } else {
                            u = 3;
                            n += s << 2;
                        }
                        if (n < Y) {
                            n = Y + 10;
                            s = 1;
                            v = 0;
                            ac = 0;
                            t = 2;
                        }
                        if (n > p) {
                            n = p - 10;
                            s = -1;
                            v = 0;
                            ac = 0;
                            t = 2;
                        }
                        if (q.a(h.ah - n) >= 40) break;
                        ac = 0;
                        v = 0;
                        s = n > h.ah ? -1 : 1;
                        ab = 1;
                        t = 4;
                        return;
                    }
                    case 3: {
                        int n6 = u = ++v % 2 == 0 ? 0 : 1;
                        if (v != 5) break;
                        t = 4;
                        v = 0;
                        u = 1;
                        break;
                    }
                    case 4: {
                        u = 1;
                        if (++v > 8) {
                            v = 0;
                        }
                        if (v > 3) {
                            u = 0;
                        }
                        if ((m -= s << 2) >= Y && m <= p) break;
                        ac = 0;
                        v = 0;
                        t = 11;
                        break;
                    }
                    case 5: {
                        if (q.aq % 2 == 0) {
                            if (u == 0) {
                                u = 1;
                            } else if (u == 1) {
                                u = 2;
                            } else if (u == 2) {
                                u = 0;
                            }
                        }
                        m += X * s;
                        n -= ++v << 1;
                        if (v < 10) break;
                        t = 3;
                        v = 0;
                        break;
                    }
                }
                break;
            }
            case 9: {
                switch (V) {
                    case 0: {
                        t = 2;
                        break;
                    }
                    case 1: {
                        u = 5;
                        n += v;
                        ++v;
                        if (!k.a(m, n, 2)) break;
                        if (b) {
                            q.n = 0;
                        }
                        v = 0;
                        n = k.d(n);
                        t = 3;
                        break;
                    }
                    case 3: {
                        t = 2;
                        break;
                    }
                    case 4: {
                        t = 2;
                        break;
                    }
                    case 5: {
                        t = 2;
                        break;
                    }
                }
                break;
            }
            case 13: {
                switch (V) {
                    case 3: {
                        n += ++v;
                        if (q.aq % 2 == 0) {
                            if (ac > 1) {
                                --ac;
                            } else if (ac < -1) {
                                ++ac;
                            }
                        }
                        u = 3;
                        if (ad != 0 && (ad >= 3 || v <= 7) || (k.c(m += ac, n) & 2) != 2) break;
                        t = 2;
                        ad = 0;
                        ac = 0;
                        v = 0;
                    }
                }
                break;
            }
        }
        if (av != -1) {
            n2 = h.ah - 12;
            if (++av == 4) {
                if (q.a(n - 29 - n2) < 20) {
                    h.a(aa, aw);
                }
                av = -1;
            }
        }
    }

    public static void a(Graphics graphics) {
        if (!D) {
            return;
        }
        if (q.ap != 16) {
            if (c.h == 0 && c.g == 50) {
                return;
            }
            if (c.h != 0) {
                graphics.setClip(F - 100, G - 50, 200, 50 - c.g);
            }
        }
        if (an == 1) {
            int n2 = 0;
            while (n2 < 3) {
                graphics.drawRegion(ag[al[am][n2]], 0, 0, ag[al[am][n2]].getWidth(), ag[al[am][n2]].getHeight(), 2, -ak[am][n2] + F, ak[am][n2 + 3] + G, 33);
                ++n2;
            }
            return;
        }
        int n3 = 0;
        while (n3 < 3) {
            graphics.drawImage(ag[al[am][n3]], ak[am][n3] + F, ak[am][n3 + 3] + G, 33);
            ++n3;
        }
    }

    public static void b(Graphics graphics) {
        int n2;
        if (!E) {
            return;
        }
        if (L == 1) {
            n2 = 0;
            while (n2 < 3) {
                graphics.drawImage(ah[ap[J][n2]], ao[J][n2] + H, ao[J][n2 + 3] + I, 33);
                ++n2;
            }
        }
        if (L == -1) {
            n2 = 0;
            while (n2 < 3) {
                graphics.drawRegion(ah[ap[J][n2]], 0, 0, ah[ap[J][n2]].getWidth(), ah[ap[J][n2]].getHeight(), 2, -ao[J][n2] + H, ao[J][n2 + 3] + I, 33);
                ++n2;
            }
        }
        if (Q != 0 && !j) {
            graphics.setColor(0x330011);
            graphics.drawRect(H - 11 - 1, I - 42 - 7, 24, 4);
            n2 = N * 22 / O;
            graphics.setColor(0xEE0000);
            graphics.fillRect(H - 11, I - 42 - 6, n2, 3);
            graphics.setColor(0x440022);
            graphics.fillRect(H - 11 + n2, I - 42 - 6, 22 - n2 + 1, 3);
        }
    }

    public static void c(Graphics graphics) {
        if (!E) {
            return;
        }
        if (j && B <= C) {
            if (!d.g) {
                return;
            }
            if (B % 5 < 4) {
                Midlet.c(graphics, "" + l, h.ag, h.ah - 50, 2);
            }
        }
    }

    public static void d(Graphics graphics) {
        int n2;
        if (!a) {
            return;
        }
        if (V == 0) {
            n2 = 0;
            while (true) {
                graphics.drawImage(af[U[u][n2]], T[u][n2] + m + at, T[u][n2 + 4] + n, 33);
                if (n2 != 3) {
                    graphics.drawRegion(af[U[u][n2]], 0, 0, af[U[u][n2]].getWidth(), af[U[u][n2]].getHeight(), 2, T[u][n2] + m - af[U[u][n2]].getWidth() - at, T[u][n2 + 4] + n, 33);
                    ++n2;
                    continue;
                }
                break;
            }
        } else if (s == 1) {
            n2 = 0;
            while (n2 < ai) {
                graphics.drawRegion(af[U[u][n2]], 0, 0, af[U[u][n2]].getWidth(), af[U[u][n2]].getHeight(), 2, -T[u][n2] + m, T[u][n2 + ai] + n, 33);
                ++n2;
            }
        } else {
            n2 = 0;
            while (n2 < ai) {
                graphics.drawImage(af[U[u][n2]], T[u][n2] + m, T[u][n2 + ai] + n, 33);
                ++n2;
            }
        }
        if (A != 0 && !j) {
            graphics.setColor(0x330011);
            graphics.drawRect(m - (w >> 1) - 1, n - x - 7, w + 2, 4);
            n2 = q * w / r;
            graphics.setColor(0xEE0000);
            graphics.fillRect(m - (w >> 1), n - x - 6, n2, 3);
            graphics.setColor(0x440022);
            graphics.fillRect(m - (w >> 1) + n2, n - x - 6, w - n2 + 1, 3);
        }
        if (j && B <= C && B % 5 < 4) {
            Midlet.c(graphics, "" + l, q.ap == 12 || q.ap == 4 ? h.ag : m, q.ap == 12 || q.ap == 4 ? h.ah - 60 : n - 70, 2);
        }
        if (av != -1) {
            graphics.setColor(0xFF9292);
            graphics.drawRect(ax, ay - 2, az, 4);
            graphics.setColor(0xFF4949);
            graphics.drawRect(ax, ay - 1, az, 2);
            graphics.setColor(0xFF0000);
            graphics.drawRect(ax, ay, az, 0);
        }
    }

    private static void g() {
        if (j) {
            return;
        }
        switch (V) {
            case 0: {
                if (q.a(h.ag - m) >= 50 || q.a(h.ah - n) >= 40) break;
                t = 4;
                v = 0;
                ac = 0;
                return;
            }
            case 1: {
                if (h.ag < p && h.ag > Y && q.a(h.ah - n) < 20 && q.a(h.ag - m) < 100) {
                    t = 4;
                    s = m < h.ag ? 1 : -1;
                    X = s * 7;
                    ab = 1;
                    v = 0;
                    u = 3;
                }
                if (q.a(h.ah - n) >= 10 || q.a(h.ag - m) >= 130 || !(h.ag > p && s == 1 || h.ag < Y && s == -1) && q.a(h.ag - m) < 90) break;
                t = 4;
                s = m < h.ag + 11 ? 1 : -1;
                v = 0;
                ab = 2;
                return;
            }
            case 3: {
                if (q.a(h.ah - n) < 20 && q.a(h.ag - m) < 70) {
                    if (h.ag < Y || h.ag > p) {
                        return;
                    }
                    t = 4;
                    s = m < h.ag + 11 ? 1 : -1;
                    X = s << 3;
                    ab = 1;
                    v = 0;
                    u = 2;
                    return;
                }
                if (q.a(h.ah - n) >= 20 || q.a(h.ag - m) >= 100) break;
                t = 7;
                s = m < h.ag + 11 ? 1 : -1;
                ab = 2;
                v = 0;
                u = 1;
                return;
            }
            case 4: {
                if (ae > 0) {
                    int n2;
                    t = 4;
                    if ((n2 = --ae) > 3) {
                        n2 = 3;
                    }
                    o = -10 - n2;
                    return;
                }
                if ((s == -1 && m > h.ag || s == 1 && m < h.ag) && q.aq % 10 > 4) {
                    ac = 0;
                    v = 0;
                    t = 7;
                    return;
                }
                ac = 0;
                v = 0;
                t = 2;
                return;
            }
            case 5: {
                if (q.a(h.ah - n) >= 150 || q.a(h.ag - m) >= 90) break;
                t = 4;
                X = q.a(m - h.ag) / 6;
                o = q.a(h.ah - n) / 6;
                s = m < h.ag ? 1 : -1;
                v = 0;
                ab = 2;
            }
        }
    }

    public static void e() {
        if (q.aA < 33 || q.aA > 41) {
            return;
        }
        b = false;
        j = false;
        if (q.ap == 14 && !d.g) {
            d.q[d.k] = 0;
            d.l[d.k] = 6;
            d.p[d.k] = 1;
            o.a.addElement(new j(d.n[d.k], d.o[d.k], 25));
            return;
        }
        if (q.ap == 14 && !i) {
            Q = 0;
            o.b.addElement(new j(H, I - 20, 21));
            E = false;
            i = true;
            c.a = 1;
            h.as = 1;
            c.b[0] = H;
            c.c[0] = k.d(I);
            int n2 = 0;
            while (n2 < 10) {
                o.a.addElement(new j(n.e[n2], n.f[n2], 25));
                int n3 = 0;
                while (n3 < q.b(7, 10)) {
                    o.a.addElement(new j(q.b(n.e[n2] - 40, n.e[n2] + 40), q.b(n.f[n2] - 40, n.f[n2] + 10), 27));
                    n3 = (byte)(n3 + 1);
                }
                n2 = (byte)(n2 + 1);
            }
            n.p = false;
            q.f(42);
            return;
        }
        if (q.ap == 17) {
            h.bk = 0;
            h.bt = 7;
            h.br = -7;
            h.bq = h.as << 2;
            h.bs = 6;
            o.a.addElement(new j(h.bo, h.bp -= 3, 25));
            k = true;
            h.ad = -1;
            return;
        }
        if (q.ap == 22) {
            f.l = 0;
            f.m = -(f.i << 3);
            o.b.addElement(new j(f.b, f.c, 3));
            f.j = 1;
            f.e = -10;
            f.a = 0;
            q.a(f.b, f.c, 4, -6, 50);
            q.a(f.b, f.c, -5, -7, 50);
            q.a(f.b, f.c, -4, -8, 50);
            q.a(f.b, f.c, 5, -9, 50);
            q.a(f.b, f.c, 0, -10, 50);
            return;
        }
        switch (V) {
            case 0: {
                o = -3;
                at = 1;
                q.n = 4;
                t = 1;
                o = -5;
                c = true;
                q.a(m, n);
                q.a(m, n, 4, -6, 50);
                q.a(m, n, -5, -7, 50);
                q.a(m, n, -4, -8, 50);
                q.a(m, n, 5, -9, 50);
                q.a(m, n, 0, -10, 50);
                return;
            }
            case 1: {
                o.a.addElement(new j(m, n, 0));
                o = -7;
                q.n = 4;
                t = 1;
                o = -10;
                d = true;
                q.a(m, n);
                q.a(m, n, 4, -6, 50);
                q.a(m, n, -5, -7, 50);
                q.a(m, n, -4, -8, 50);
                q.a(m, n, 5, -9, 50);
                q.a(m, n, 0, -10, 50);
                return;
            }
            case 3: {
                u = 3;
                A = 0;
                e = true;
                v = -5;
                X = h.as << 3;
                ad = 0;
                t = 1;
                o.b.addElement(new j(m - 5, n - 20, 25));
                if (c.l != 0) break;
                c.l = 1;
                return;
            }
            case 4: {
                v = -5;
                ac = -(s << 3);
                A = 0;
                t = 1;
                o = -10;
                g = true;
                q.a(m, 336);
                q.a(m, n, 4, -6, 50);
                q.a(m, n, -5, -7, 50);
                q.a(m, n, -4, -8, 50);
                q.a(m, n, 5, -9, 50);
                q.a(m, n, 0, -10, 50);
                return;
            }
            case 5: {
                v = -5;
                ac = -(s << 3);
                A = 0;
                t = 1;
                o = -10;
                f = true;
                q.a(m, n);
                o.a.addElement(new j(m, n - 15, 25));
                q.a(m, n, 4, -6, 50);
                q.a(m, n, -5, -7, 50);
                q.a(m, n, -4, -8, 50);
                q.a(m, n, 5, -9, 50);
                q.a(m, n, 0, -10, 50);
            }
        }
    }

    public static boolean a(int n2, int n3, int n4) {
        switch (V) {
            case 0: {
                if (t != 3 && t != 2 && t != 7) break;
                if (q > r / 5) {
                    q -= n2;
                    A = 10;
                } else {
                    A = 0;
                }
                v = 0;
                ac = 0;
                t = 6;
                if (j || q > r / 5) break;
                C = 38;
                q.b();
                q.c();
                j = true;
                l = q.b(1, 10);
                B = 0;
                o.a.addElement(new j(m, n - 67, 20));
                break;
            }
            case 1: {
                if (t != 2 && t != 4) break;
                if (q > r / 5) {
                    q -= n2;
                    A = 10;
                } else {
                    A = 0;
                }
                v = 0;
                ac = 0;
                t = 6;
                u = 4;
                o = -3;
                if (j || q > r / 5) break;
                C = 40;
                q.b();
                q.c();
                j = true;
                l = q.b(1, 10);
                B = 0;
                o.a.addElement(new j(m, n - 67, 20));
                break;
            }
            case 2: {
                return true;
            }
            case 3: {
                if (t != 3 && t != 2) break;
                if (q > r / 5) {
                    q -= n2;
                    A = 10;
                } else {
                    A = 0;
                }
                if (n4 == 0) {
                    v = 0;
                    ac = 0;
                    ae = 0;
                    t = 6;
                } else {
                    v = -5;
                    ac = n3 << q.aq % 4;
                    ad = 0;
                    A = 10;
                    q.a("-" + n2, 0, m, n - x, 0, -2);
                    t = 13;
                }
                if (j || q > r / 5 || b) break;
                C = 34;
                q.b();
                q.c();
                j = true;
                l = q.b(1, 10);
                B = 0;
                o.a.addElement(new j(m, n - 67, 20));
                break;
            }
            case 4: {
                if (t == 6) break;
                if (q > r / 5) {
                    q -= n2;
                    A = 10;
                } else {
                    A = 0;
                }
                v = 0;
                ac = 0;
                t = 6;
                if (j || q > r / 5) break;
                C = 32;
                q.b();
                q.c();
                j = true;
                l = q.b(1, 10);
                B = 0;
                o.a.addElement(new j(m, n - 67, 20));
                break;
            }
            case 5: {
                if (q > r / 5) {
                    q -= n2;
                    A = 10;
                } else {
                    A = 0;
                }
                ++ae;
                aj = 10;
                v = 0;
                ac = 0;
                t = 6;
                if (j || q > r / 5) break;
                C = 30;
                j = true;
                q.b();
                q.c();
                l = q.b(1, 10);
                B = 0;
                o.a.addElement(new j(m, n - 30, 20));
            }
        }
        return false;
    }

    public static void a(int n2, int n3) {
        K = 6;
        if (N > O / 5) {
            N -= h.aw;
            q.b(H, I, -n3);
            q.a("-" + n2, 0, H, I - 50, 0, -2);
            Q = 10;
        }
        if (!j && N <= O / 5) {
            C = 24;
            B = 0;
            Q = 0;
            j = true;
            q.b();
            q.c();
            l = q.b(1, 10);
            o.a.addElement(new j(m, n - 67, 20));
        }
    }

    private static void h() {
        if (q.a(h.ah - n) < 30 && q.a(h.ag - m - (s << 4)) < 20) {
            h.a(aa, s);
        }
    }

    public static void f() {
        int n2 = 0;
        while (n2 < 5) {
            if (aE[n2] != 0) {
                if (aD[n2] > 1 && h.ah > aC[n2] - aD[n2] && q.a(aB[n2] - h.ag) < 10) {
                    h.a(M / 2, h.as);
                }
                if (aE[n2] == 1) {
                    if (aD[n2] < 25) {
                        int n3 = n2;
                        aD[n3] = aD[n3] + n2;
                    }
                    if (aD[n2] > 25) {
                        i.aD[n2] = 25;
                    }
                    if (aD[n2] == 25) {
                        int n4 = n2;
                        aF[n4] = aF[n4] + 1;
                    }
                    if (aF[n2] == 5) {
                        i.aE[n2] = -1;
                        i.aF[n2] = 0;
                    }
                }
                if (aE[n2] == -1) {
                    if (aD[n2] > 0) {
                        int n5 = n2;
                        aD[n5] = aD[n5] - n2;
                    }
                    if (aD[n2] < 0) {
                        i.aD[n2] = 0;
                    }
                    if (aD[n2] == 0) {
                        i.aE[n2] = 0;
                    }
                }
            }
            n2 = (byte)(n2 + 1);
        }
    }

    public static void e(Graphics graphics) {
        int n2 = 0;
        while (n2 < 5) {
            if (aE[n2] != 0) {
                graphics.drawRegion(aA[q.aq % 2 == 0 ? 0 : 1], 0, 0, 10, aD[n2], 0, aB[n2], aC[n2], 33);
            }
            n2 = (byte)(n2 + 1);
        }
    }
}


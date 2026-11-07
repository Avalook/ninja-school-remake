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

public final class h {
    private static int bM = 0;
    public static final int[] a = new int[]{6, 6, 6, 8, 8, 8, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 14, 14, 14, 14, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26};
    public static final int[] b = new int[]{100, 500, 1000, 1800, 3000, 5000, 9000, 11000, 14000, 18000, 24000, 30000, 38000, 50000, 60000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000};
    public static final int[] c = new int[]{140, 140, 140, 140, 160, 160, 160, 180, 180, 200, 220, 240, 260, 260, 280, 280, 300, 340, 360, 400, 420, 440, 460, 480, 500, 520, 540, 600, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700};
    public static final int[] d = new int[]{30, 30, 30, 30, 40, 40, 40, 50, 50, 60, 70, 80, 90, 90, 100, 100, 110, 130, 140, 160, 170, 180, 190, 200, 210, 220, 230, 260, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310, 310};
    public static boolean e = true;
    public static boolean f;
    public static boolean g;
    public static boolean h;
    public static boolean i;
    public static boolean j;
    public static boolean k;
    public static boolean l;
    public static boolean m;
    public static boolean[] n;
    public static boolean o;
    public static boolean p;
    public static int q;
    public static int r;
    private static Image bN;
    private static int[][][] bO;
    private static int[][][] bP;
    private static int[] bQ;
    private static int[] bR;
    private static int[][][] bS;
    public static int s;
    public static String[][] t;
    public static boolean u;
    public static boolean[] v;
    public static int w;
    public static int[] x;
    public static int[] y;
    private static int[] bT;
    private static int[] bU;
    private static int[] bV;
    private static int[] bW;
    private static int[] bX;
    private static int[] bY;
    private static int[] bZ;
    private static int[] ca;
    public static int[] z;
    private static int[] cb;
    private static int[] cc;
    private static int[] cd;
    private static int[] ce;
    private static Image[] cf;
    private static Image[] cg;
    private static Image[] ch;
    private static Image[] ci;
    public static Image[] A;
    private static Image[] cj;
    public static Image[] B;
    private static Image[] ck;
    private static Image[] cl;
    private static Image[][] cm;
    public static boolean C;
    public static int D;
    public static int E;
    public static int F;
    public static int G;
    public static int H;
    public static int I;
    public static int J;
    public static int K;
    public static int L;
    public static int M;
    public static int N;
    public static int O;
    public static int P;
    public static int Q;
    public static int R;
    public static int S;
    public static int T;
    public static int U;
    public static int V;
    public static int W;
    public static int X;
    public static int Y;
    public static int Z;
    public static int aa;
    public static int ab;
    public static int ac;
    public static int ad;
    public static int ae;
    public static int af;
    public static int ag;
    public static int ah;
    public static int ai;
    public static int aj;
    public static int ak;
    public static int al;
    public static int am;
    public static int an;
    public static int ao;
    public static int ap;
    public static int aq;
    public static int ar;
    public static int as;
    public static int at;
    public static int au;
    public static int av;
    public static int aw;
    public static int ax;
    public static int ay;
    public static int az;
    public static int aA;
    public static int aB;
    public static int aC;
    public static int aD;
    public static int aE;
    public static int aF;
    public static int aG;
    public static int aH;
    public static int aI;
    public static int aJ;
    private static boolean cn;
    public static int aK;
    public static int aL;
    public static int aM;
    public static int aN;
    public static boolean aO;
    public static boolean aP;
    private static boolean co;
    public static boolean aQ;
    public static int aR;
    public static int aS;
    public static int aT;
    public static boolean aU;
    public static boolean aV;
    public static int aW;
    public static int aX;
    public static int aY;
    public static int aZ;
    public static int ba;
    static int bb;
    static int bc;
    private static int cp;
    private static int cq;
    static int bd;
    static int be;
    static int bf;
    static boolean bg;
    public static boolean bh;
    private static int cr;
    private static int cs;
    public static int bi;
    public static int bj;
    public static int bk;
    public static boolean bl;
    public static int bm;
    public static int bn;
    public static int bo;
    public static int bp;
    public static int bq;
    public static int br;
    public static int bs;
    public static int bt;
    public static int bu;
    public static int bv;
    public static int bw;
    public static int bx;
    public static int by;
    public static int bz;
    public static int bA;
    public static int bB;
    public static int bC;
    public static int bD;
    public static int bE;
    public static int bF;
    public static int bG;
    public static int bH;
    public static int bI;
    public static int bJ;
    public static int bK;
    public static int bL;

    static {
        int[][][] nArrayArray = new int[14][][];
        int[][] nArrayArray2 = new int[3][];
        int[] nArray = new int[3];
        nArray[1] = -13;
        nArray[2] = 31;
        nArrayArray2[0] = nArray;
        int[] nArray2 = new int[3];
        nArray2[1] = -7;
        nArray2[2] = 8;
        nArrayArray2[1] = nArray2;
        int[] nArray3 = new int[3];
        nArray3[1] = -11;
        nArray3[2] = 14;
        nArrayArray2[2] = nArray3;
        nArrayArray[0] = nArrayArray2;
        int[][] nArrayArray3 = new int[3][];
        int[] nArray4 = new int[3];
        nArray4[1] = -13;
        nArray4[2] = 32;
        nArrayArray3[0] = nArray4;
        int[] nArray5 = new int[3];
        nArray5[1] = -7;
        nArray5[2] = 8;
        nArrayArray3[1] = nArray5;
        int[] nArray6 = new int[3];
        nArray6[1] = -11;
        nArray6[2] = 15;
        nArrayArray3[2] = nArray6;
        nArrayArray[1] = nArrayArray3;
        int[][] nArrayArray4 = new int[3][];
        nArrayArray4[0] = new int[]{1, -11, 31};
        nArrayArray4[1] = new int[]{1, -9, 16};
        int[] nArray7 = new int[3];
        nArray7[0] = 6;
        nArray7[1] = -1000;
        nArrayArray4[2] = nArray7;
        nArrayArray[2] = nArrayArray4;
        int[][] nArrayArray5 = new int[3][];
        nArrayArray5[0] = new int[]{2, -11, 32};
        nArrayArray5[1] = new int[]{2, -10, 16};
        int[] nArray8 = new int[3];
        nArray8[0] = 6;
        nArray8[1] = -1000;
        nArrayArray5[2] = nArray8;
        nArrayArray[3] = nArrayArray5;
        int[][] nArrayArray6 = new int[3][];
        nArrayArray6[0] = new int[]{1, -11, 31};
        nArrayArray6[1] = new int[]{3, -8, 16};
        int[] nArray9 = new int[3];
        nArray9[0] = 6;
        nArray9[1] = -1000;
        nArrayArray6[2] = nArray9;
        nArrayArray[4] = nArrayArray6;
        int[][] nArrayArray7 = new int[3][];
        nArrayArray7[0] = new int[]{2, -10, 32};
        nArrayArray7[1] = new int[]{7, -9, 17};
        int[] nArray10 = new int[3];
        nArray10[0] = 6;
        nArray10[1] = -1000;
        nArrayArray7[2] = nArray10;
        nArrayArray[5] = nArrayArray7;
        int[][] nArrayArray8 = new int[3][];
        nArrayArray8[0] = new int[]{1, -10, 31};
        nArrayArray8[1] = new int[]{8, -8, 16};
        int[] nArray11 = new int[3];
        nArray11[0] = 6;
        nArray11[1] = -1000;
        nArrayArray8[2] = nArray11;
        nArrayArray[6] = nArrayArray8;
        int[][] nArrayArray9 = new int[3][];
        int[] nArray12 = new int[3];
        nArray12[1] = -8;
        nArray12[2] = 35;
        nArrayArray9[0] = nArray12;
        nArrayArray9[1] = new int[]{4, -5, 15};
        nArrayArray9[2] = new int[]{2, -10, 21};
        nArrayArray[7] = nArrayArray9;
        int[][] nArrayArray10 = new int[3][];
        int[] nArray13 = new int[3];
        nArray13[1] = -8;
        nArray13[2] = 34;
        nArrayArray10[0] = nArray13;
        nArrayArray10[1] = new int[]{4, -6, 13};
        nArrayArray10[2] = new int[]{3, -11, 22};
        nArrayArray[8] = nArrayArray10;
        int[][] nArrayArray11 = new int[3][];
        int[] nArray14 = new int[3];
        nArray14[1] = -8;
        nArray14[2] = 31;
        nArrayArray11[0] = nArray14;
        nArrayArray11[1] = new int[]{6, -13, 9};
        nArrayArray11[2] = new int[]{4, -10, 18};
        nArrayArray[9] = nArrayArray11;
        nArrayArray[10] = new int[][]{{1, -8, 30}, {6, -13, 9}, {5, -8, 17}};
        nArrayArray[11] = new int[][]{{2, -10, 30}, {6, -13, 9}, {6, -8, 16}};
        nArrayArray[12] = new int[][]{{2, -10, 29}, {6, -13, 9}, {7, -11, 17}};
        nArrayArray[13] = new int[][]{{3, -11, 33}, {8, -4, 13}, {8, -15, 25}};
        bO = nArrayArray;
        int[][][] nArrayArray12 = new int[52][][];
        int[][] nArrayArray13 = new int[3][];
        int[] nArray15 = new int[3];
        nArray15[1] = -10;
        nArray15[2] = 32;
        nArrayArray13[0] = nArray15;
        nArrayArray13[1] = new int[]{1, -7, 7};
        nArrayArray13[2] = new int[]{1, -11, 15};
        nArrayArray12[0] = nArrayArray13;
        int[][] nArrayArray14 = new int[3][];
        int[] nArray16 = new int[3];
        nArray16[1] = -10;
        nArray16[2] = 33;
        nArrayArray14[0] = nArray16;
        nArrayArray14[1] = new int[]{1, -7, 7};
        nArrayArray14[2] = new int[]{1, -11, 16};
        nArrayArray12[1] = nArrayArray14;
        nArrayArray12[2] = new int[][]{{1, -10, 33}, {2, -10, 11}, {2, -9, 16}};
        nArrayArray12[3] = new int[][]{{1, -10, 32}, {3, -11, 9}, {3, -11, 16}};
        nArrayArray12[4] = new int[][]{{1, -10, 34}, {4, -9, 9}, {4, -8, 16}};
        nArrayArray12[5] = new int[][]{{1, -10, 34}, {5, -11, 11}, {5, -10, 17}};
        nArrayArray12[6] = new int[][]{{1, -10, 33}, {6, -9, 9}, {6, -8, 16}};
        int[][] nArrayArray15 = new int[3][];
        int[] nArray17 = new int[3];
        nArray17[1] = -9;
        nArray17[2] = 36;
        nArrayArray15[0] = nArray17;
        nArrayArray15[1] = new int[]{7, -5, 15};
        nArrayArray15[2] = new int[]{7, -10, 21};
        nArrayArray12[7] = nArrayArray15;
        nArrayArray12[8] = new int[][]{{4, -13, 26}, new int[3], new int[3]};
        nArrayArray12[9] = new int[][]{{5, -13, 25}, new int[3], new int[3]};
        nArrayArray12[10] = new int[][]{{6, -12, 26}, new int[3], new int[3]};
        nArrayArray12[11] = new int[][]{{7, -13, 25}, new int[3], new int[3]};
        int[][] nArrayArray16 = new int[3][];
        int[] nArray18 = new int[3];
        nArray18[1] = -9;
        nArray18[2] = 35;
        nArrayArray16[0] = nArray18;
        nArrayArray16[1] = new int[]{8, -4, 13};
        nArrayArray16[2] = new int[]{8, -14, 27};
        nArrayArray12[12] = nArrayArray16;
        int[][] nArrayArray17 = new int[3][];
        int[] nArray19 = new int[3];
        nArray19[1] = -9;
        nArray19[2] = 32;
        nArrayArray17[0] = nArray19;
        nArrayArray17[1] = new int[]{9, -11, 9};
        nArrayArray17[2] = new int[]{10, -10, 18};
        nArrayArray12[13] = nArrayArray17;
        nArrayArray12[14] = new int[][]{{2, -7, 34}, {9, -11, 9}, {11, -8, 16}};
        nArrayArray12[15] = new int[][]{{2, -8, 33}, {9, -11, 9}, {12, -8, 15}};
        nArrayArray12[16] = new int[][]{{2, -7, 33}, {9, -11, 9}, {13, -12, 16}};
        int[][] nArrayArray18 = new int[3][];
        int[] nArray20 = new int[3];
        nArray20[1] = -11;
        nArray20[2] = 32;
        nArrayArray18[0] = nArray20;
        nArrayArray18[1] = new int[]{9, -11, 9};
        nArrayArray18[2] = new int[]{14, -15, 19};
        nArrayArray12[17] = nArrayArray18;
        nArrayArray12[18] = new int[][]{{2, -9, 33}, {9, -11, 9}, {15, -13, 20}};
        nArrayArray12[19] = new int[][]{{2, -9, 32}, {9, -11, 9}, {16, -7, 23}};
        nArrayArray12[20] = new int[][]{{2, -9, 33}, {9, -11, 9}, {17, -11, 19}};
        nArrayArray12[21] = new int[][]{{3, -12, 34}, {8, -4, 13}, {8, -15, 25}};
        int[][] nArrayArray19 = new int[3][];
        int[] nArray21 = new int[3];
        nArray21[1] = -10;
        nArray21[2] = 28;
        nArrayArray19[0] = nArray21;
        nArrayArray19[1] = new int[3];
        nArrayArray19[2] = new int[]{19, -8, 12};
        nArrayArray12[22] = nArrayArray19;
        int[][] nArrayArray20 = new int[3][];
        int[] nArray22 = new int[3];
        nArray22[1] = -9;
        nArray22[2] = 35;
        nArrayArray20[0] = nArray22;
        nArrayArray20[1] = new int[]{8, -4, 13};
        nArrayArray20[2] = new int[]{9, -9, 22};
        nArrayArray12[23] = nArrayArray20;
        int[][] nArrayArray21 = new int[3][];
        int[] nArray23 = new int[3];
        nArray23[1] = -10;
        nArray23[2] = 33;
        nArrayArray21[0] = nArray23;
        nArrayArray21[1] = new int[3];
        nArrayArray21[2] = new int[3];
        nArrayArray12[24] = nArrayArray21;
        nArrayArray12[25] = new int[][]{{9, -4, 24}, new int[3], new int[3]};
        nArrayArray12[26] = new int[][]{{10, -4, 22}, new int[3], new int[3]};
        int[][] nArrayArray22 = new int[3][];
        int[] nArray24 = new int[3];
        nArray24[1] = -9;
        nArray24[2] = 32;
        nArrayArray22[0] = nArray24;
        nArrayArray22[1] = new int[]{8, -4, 9};
        nArrayArray22[2] = new int[]{10, -10, 18};
        nArrayArray12[27] = nArrayArray22;
        nArrayArray12[28] = new int[][]{{2, -7, 34}, {8, -4, 9}, {11, -8, 16}};
        nArrayArray12[29] = new int[][]{{2, -8, 33}, {8, -4, 9}, {12, -8, 15}};
        nArrayArray12[30] = new int[][]{{2, -7, 33}, {8, -4, 9}, {13, -12, 16}};
        int[][] nArrayArray23 = new int[3][];
        int[] nArray25 = new int[3];
        nArray25[1] = -11;
        nArray25[2] = 32;
        nArrayArray23[0] = nArray25;
        nArrayArray23[1] = new int[]{7, -5, 9};
        nArrayArray23[2] = new int[]{14, -15, 19};
        nArrayArray12[31] = nArrayArray23;
        nArrayArray12[32] = new int[][]{{2, -9, 33}, {7, -5, 9}, {15, -13, 20}};
        nArrayArray12[33] = new int[][]{{2, -9, 32}, {7, -5, 9}, {16, -7, 23}};
        nArrayArray12[34] = new int[][]{{2, -9, 33}, {7, -5, 9}, {17, -11, 19}};
        int[][] nArrayArray24 = new int[3][];
        int[] nArray26 = new int[3];
        nArray26[1] = -9;
        nArray26[2] = 32;
        nArrayArray24[0] = nArray26;
        nArrayArray24[1] = new int[]{2, -10, 11};
        nArrayArray24[2] = new int[]{10, -10, 18};
        nArrayArray12[35] = nArrayArray24;
        nArrayArray12[36] = new int[][]{{2, -7, 34}, {3, -11, 9}, {11, -8, 16}};
        nArrayArray12[37] = new int[][]{{2, -8, 33}, {4, -9, 9}, {12, -8, 15}};
        nArrayArray12[38] = new int[][]{{2, -7, 33}, {5, -11, 11}, {13, -12, 16}};
        int[][] nArrayArray25 = new int[3][];
        int[] nArray27 = new int[3];
        nArray27[1] = -11;
        nArray27[2] = 32;
        nArrayArray25[0] = nArray27;
        nArrayArray25[1] = new int[]{2, -10, 11};
        nArrayArray25[2] = new int[]{14, -15, 19};
        nArrayArray12[39] = nArrayArray25;
        nArrayArray12[40] = new int[][]{{2, -9, 33}, {3, -11, 9}, {15, -13, 20}};
        nArrayArray12[41] = new int[][]{{2, -9, 32}, {4, -9, 9}, {16, -7, 23}};
        nArrayArray12[42] = new int[][]{{2, -9, 33}, {5, -11, 11}, {17, -11, 19}};
        nArrayArray12[43] = new int[][]{{1, -8, 38}, new int[3], {18, -8, 21}};
        nArrayArray12[44] = new int[][]{{1, -8, 39}, new int[3], {18, -8, 22}};
        nArrayArray12[45] = new int[][]{{1, -10, 43}, new int[3], {18, -11, 26}};
        nArrayArray12[46] = new int[][]{{2, -11, 36}, {9, -11, 9}, {20, -8, 19}};
        nArrayArray12[47] = new int[][]{{2, -10, 13}, {8, -4, -12}, {16, -7, 3}};
        nArrayArray12[48] = new int[][]{{2, -11, 13}, {8, -5, -12}, {16, -6, 3}};
        int[][] nArrayArray26 = new int[3][];
        int[] nArray28 = new int[3];
        nArray28[0] = 2;
        nArray28[2] = 34;
        nArrayArray26[0] = nArray28;
        int[] nArray29 = new int[3];
        nArray29[0] = 2;
        nArray29[2] = 9;
        nArrayArray26[1] = nArray29;
        nArrayArray26[2] = new int[]{15, -4, 20};
        nArrayArray12[49] = nArrayArray26;
        int[][] nArrayArray27 = new int[3][];
        nArrayArray27[0] = new int[]{2, -1, 34};
        int[] nArray30 = new int[3];
        nArray30[0] = 6;
        nArray30[2] = 9;
        nArrayArray27[1] = nArray30;
        nArrayArray27[2] = new int[]{15, -5, 20};
        nArrayArray12[50] = nArrayArray27;
        nArrayArray12[51] = new int[][]{{2, -9, 31}, {9, -11, 9}, {21, -7, 19}};
        bP = nArrayArray12;
        int[] nArray31 = new int[15];
        nArray31[0] = 12;
        nArray31[1] = 22;
        nArray31[2] = 23;
        nArray31[3] = -6;
        nArray31[4] = -6;
        nArray31[5] = -4;
        nArray31[6] = 27;
        nArray31[7] = 28;
        nArray31[8] = 13;
        nArray31[9] = 15;
        nArray31[11] = -10;
        nArray31[12] = 11;
        nArray31[13] = 1;
        nArray31[14] = 4;
        bQ = nArray31;
        bR = new int[]{-21, -26, -27, -28, -27, -19, -25, -39, -41, -37, -35, -20, -38, -43, -39};
        bS = new int[bP.length][3][3];
        int n2 = 0;
        while (n2 < bP.length) {
            int n3 = 0;
            while (n3 < 3) {
                int n4 = 0;
                while (n4 < 3) {
                    h.bS[n2][n3][n4] = bP[n2][n3][n4];
                    if (n3 == 0 && n4 == 0) {
                        switch (bP[n2][n3][n4]) {
                            case 1: 
                            case 2: {
                                h.bS[n2][n3][n4] = 1;
                                break;
                            }
                            default: {
                                h.bS[n2][n3][n4] = 0;
                            }
                        }
                    }
                    ++n4;
                }
                ++n3;
            }
            ++n2;
        }
        C = false;
        E = -1;
        aO = true;
        cp = 0;
        cq = 0;
        bl = false;
        bn = -1;
    }

    /*
     * Unable to fully structure code
     */
    public static final void a() {
        if (h.E != q.ap) {
            return;
        }
        try {
            switch (h.J) {
                case 1: {
                    if (++h.S > 30) {
                        h.S = 0;
                    }
                    h.K = h.S % 15 < 5 ? 0 : 1;
                    if (!h.C || q.aq % 6 != 0) ** GOTO lbl28
                    if (q.aq % 8 >= 3) ** GOTO lbl22
                    var0 = 0;
                    while (var0 < a.c) {
                        if (a.i[var0] == 5 || q.a(a.f[var0] - h.G) >= 20 || q.a(a.e[var0] - h.F) >= 40 || a.a[a.d[var0]] <= 0) ** GOTO lbl20
                        h.R = a.e[var0] > h.F ? 1 : -1;
                        h.J = 5;
                        h.ba = 0;
                        h.S = 0;
                        h.T = 0;
                        ** GOTO lbl28
lbl20:
                        // 1 sources

                        ++var0;
                    }
lbl22:
                    // 2 sources

                    if (q.a(h.ag - h.F) > 25 && h.ak == 5) {
                        h.R = h.ag > h.F ? 1 : -1;
                        h.J = 5;
                        h.ba = 1;
                        h.S = 0;
                        h.T = 0;
                    }
lbl28:
                    // 5 sources

                    if (q.aq % 15 == 0) {
                        h.i();
                    }
                    return;
                }
                case 2: {
                    if (++h.S >= 10) {
                        h.S = 0;
                    }
                    h.K = (h.S >> 1) + 2;
                    if (h.R == 1) {
                        if (k.a(h.F + h.N, h.G - h.O, 4)) {
                            h.H = 0;
                            h.F = k.e(h.F + h.N) - h.N;
                        }
                    } else if (k.a(h.F - h.N - 1, h.G - h.O, 8)) {
                        h.H = 0;
                        h.F = k.e(h.F - h.N - 1) + k.f + h.N;
                    }
                    if ((h.F += h.H) < 0) {
                        h.F = 0;
                    }
                    if (h.F >= k.c) {
                        h.F = k.c - 1;
                    }
                    if (q.aK != -1) {
                        if (q.a(h.F - q.aL[q.aK]) >= 20) {
                            q.aK = -1;
                            if (k.a(h.F, h.G, 2)) {
                                h.J = 1;
                            } else {
                                h.J = 4;
                                h.I = 2;
                                h.H = 3 * h.R;
                                h.T = 0;
                            }
                        }
                    } else if ((k.c(h.F + h.R * h.L, h.G) & 2) != 2) {
                        h.J = 1;
                        h.K = 0;
                        h.H = 0;
                        h.S = 0;
                        h.T = 0;
                    }
                    q.a(h.R, h.F - (h.R << 3), h.G);
                    if (h.ac != 0) {
                        if (q.a(h.ac - h.F) < 20) {
                            h.J = 1;
                            h.ac = 0;
                            System.out.println("STOPP");
                            break;
                        }
                    } else if (q.aq % 5 == 0) {
                        h.i();
                    }
                    return;
                }
                case 3: {
                    if (h.H == 0) {
                        h.H = h.R << 2;
                    }
                    if (h.R == 1) {
                        if ((k.c(h.F + h.N, h.G - h.O) & 4) == 4) {
                            h.H = 0;
                        }
                    } else if ((k.c(h.F - h.N, h.G - h.O) & 8) == 8) {
                        h.H = 0;
                    }
                    if (h.ah < 0) {
                        h.ah = 0;
                        h.aj = -1;
                    }
                    if ((h.F += h.H) < 0) {
                        h.F = 0;
                    }
                    if (h.F >= k.c) {
                        h.F = k.c - 1;
                    }
                    h.G += h.I;
                    h.K = h.S == 0 ? 7 : 8;
                    if (h.H > 1) {
                        --h.H;
                    }
                    if (++h.I == 0) {
                        h.J = 4;
                        h.S = 0;
                        h.T = 0;
                        h.I = 1;
                    }
                    if (h.U < 0) {
                        ++h.U;
                    }
                    if (h.G > h.M && k.a(h.F, h.G - h.M, 16384)) {
                        h.G = k.d(h.G);
                        h.J = 4;
                        h.S = 0;
                        h.T = 0;
                        h.I = 1;
                    }
                    return;
                }
                case 4: {
                    if (h.R == 1) {
                        if ((k.c(h.F + h.L, h.G) & 4) == 4) {
                            h.H = 0;
                        }
                    } else if ((k.c(h.F - h.L, h.G) & 8) == 8) {
                        h.H = 0;
                    }
                    h.aZ = -1;
                    if ((h.F += h.H) < 0) {
                        h.F = 0;
                    }
                    if (h.F >= k.c) {
                        h.F = k.c - 1;
                    }
                    h.G += h.I;
                    if (h.I < 18) {
                        ++h.I;
                    }
                    if (h.I > 5) {
                        h.H = 0;
                    }
                    if (h.H > 1) {
                        --h.H;
                    }
                    h.K = 8;
                    if (h.G >= k.d) {
                        h.G = k.d - 1;
                        h.J = 3;
                        h.I = -20;
                    }
                    if (h.I > 4 && (k.c(h.F, h.G) & 2) == 2) {
                        h.J = 1;
                        h.I = 0;
                        h.H = 0;
                        h.T = 0;
                        h.S = 0;
                        h.G = k.e(h.G);
                        q.a(-1, h.F - -8, h.G);
                        q.a(1, h.F - 8, h.G);
                    }
                    if (k.a(h.F, h.G, 64) && k.a(h.F, h.G + 24, 4096) && h.G % k.f > 8) {
                        q.d(h.F, k.d(h.G) + 8);
                        h.J = 3;
                        h.R = h.ag > h.F ? 1 : -1;
                        h.H = h.R << 2;
                        h.I = -11;
                    }
                    if (h.I > 6) {
                        h.K = 6;
                    }
                    if (h.U < 0) {
                        ++h.U;
                    }
                    return;
                }
                case 5: {
                    switch (++h.S) {
                        case 1: 
                        case 2: 
                        case 3: {
                            h.aZ = -1;
                            h.K = 9;
                            break;
                        }
                        case 4: 
                        case 5: {
                            h.aZ = 0;
                            h.K = 10;
                            break;
                        }
                        case 6: {
                            h.aZ = 1;
                            h.K = 11;
                            if (h.ba == 1) {
                                var0_1 = h.R << 3;
                                var1_3 = h.ah - h.G >> 3;
                                q.a(h.F + (h.R << 3), h.G - 12, var0_1, var1_3 + (var0_1 - 8), true);
                                break;
                            }
                            var0_2 = h.aw << 1;
                            var1_4 = h.R > 0 ? h.F - 20 : h.F - 60;
                            var2_5 = h.R > 0 ? h.F + 60 : h.F + 20;
                            var3_6 = h.G - h.M - 15;
                            var4_7 = h.G + 5;
                            var5_8 = 0;
                            while (var5_8 < a.c) {
                                if (a.i[var5_8] == 5 || a.e[var5_8] < var1_4 || a.e[var5_8] > var2_5 || a.f[var5_8] < var3_6 || a.f[var5_8] > var4_7) ** GOTO lbl185
                                v0 = var5_8;
                                ** GOTO lbl188
lbl185:
                                // 1 sources

                                ++var5_8;
                            }
                            v0 = var1_4 = -1;
lbl188:
                            // 2 sources

                            if (v0 == -1) break;
                            o.a.addElement(new j(a.e[var1_4], a.f[var1_4], 3));
                            o.b.addElement(new j(a.e[var1_4], a.f[var1_4] - 130, 10));
                            a.a(var1_4, var0_2, -h.R, 0);
                            q.b(a.e[var1_4], a.f[var1_4] - (a.g[var1_4] >> 1), h.R);
                            q.a("-" + var0_2, 0, a.e[var1_4], a.f[var1_4] - a.g[var1_4], 0, -2);
                            break;
                        }
                        case 7: {
                            h.aZ = 2;
                            h.K = 11;
                            break;
                        }
                        case 8: {
                            h.aZ = -1;
                            h.K = 12;
                            break;
                        }
                        case 9: {
                            h.aZ = -1;
                            h.K = 9;
                            h.J = 1;
                            h.S = 0;
                            h.T = 0;
                        }
                    }
                    if (h.ba == 1) {
                        h.aZ = -1;
                    }
                    return;
                }
                case 7: {
                    if ((k.c(h.F + h.N, h.G - h.O) & 4) == 4) {
                        h.H = -h.H;
                    }
                    if ((k.c(h.F - h.N, h.G - h.O) & 8) == 8) {
                        h.H = -h.H;
                    }
                    h.F += h.H;
                    h.G += h.I;
                    h.K = 7;
                    if (h.H > 1) {
                        --h.H;
                    }
                    if (h.H < -1) {
                        ++h.H;
                    }
                    if (++h.I > 0) {
                        h.I = 0;
                    }
                    if (++h.S >= 7) {
                        h.J = 4;
                        h.U = -20;
                        h.T = 0;
                        h.S = 0;
                        h.I = 1;
                    }
                }
                default: {
                    return;
                }
            }
        }
        catch (Exception v1) {
            h.F = h.ag;
            h.G = h.ah;
            h.H = 0;
            h.I = 0;
            h.J = 4;
        }
    }

    private static void i() {
        if (C) {
            if (ah <= G - 24 && k.a(F, G - 1, 2)) {
                J = 3;
                H = 0;
                I = -7;
                return;
            }
            if (ah <= G - 24 && k.a(F, G - 25, 2)) {
                J = 3;
                H = 0;
                I = -10;
                return;
            }
            if (F < q.ax - 24 || F > q.ax + q.S + 24) {
                int n2;
                R = ag + (as << 5) > F ? 1 : -1;
                int n3 = ag > F ? q.ax - 24 : q.ax + q.S + 24;
                if (!k.a(n3, n2 = ah - 20, 8192)) {
                    F = n3;
                    G = n2;
                    J = 4;
                    H = 4 * R;
                    I = 3;
                    return;
                }
                if (k.a(F + R * L, G, 2)) {
                    R = ag > F ? 1 : -1;
                    J = 2;
                    H = R * 12;
                    return;
                }
            } else {
                if (G < q.ay - 30 || G > q.ay + q.T + 30) {
                    R = ag + (as << 5) > F ? 1 : -1;
                    int n4 = ag;
                    int n5 = ah > G ? q.ay - 20 : q.ay + q.T;
                    F = n4;
                    G = n5;
                    J = 3;
                    H = R << 2;
                    I = -4;
                    return;
                }
                if (q.a(ag + (as << 5) - F) > (ak == 5 ? 40 : 70)) {
                    if (ah < G && q.aq % 10 > 4) {
                        if (k.a(F + R * 48, G - 24, 2)) {
                            J = 3;
                            H = R * 5;
                            I = -9;
                            return;
                        }
                        int n6 = 1;
                        while (n6 <= 3) {
                            if (k.a(F + R * 24, G - n6 * 24, 2)) {
                                J = 3;
                                H = R * 6;
                                I = -5 - n6 * 3;
                                return;
                            }
                            ++n6;
                        }
                        if (!k.a(F + R * 48, G - 2, 64)) {
                            if (G + 24 >= k.d || k.a(F + R * 48, G + 24, 2)) {
                                J = 3;
                                H = R * 7;
                                I = -4;
                                return;
                            }
                            J = 3;
                            R = F < ag ? 1 : -1;
                            H = R * 6;
                            I = -4;
                            return;
                        }
                    } else {
                        if (ah > G && q.aq % 10 > 4) {
                            if (k.a(F + R * L, G, 2)) {
                                R = ag > F ? 1 : -1;
                                J = 2;
                                H = R * q.aC;
                                return;
                            }
                            if (k.a(F + R * 48, G + 24, 2)) {
                                J = 3;
                                H = R * 7;
                                I = -5;
                                return;
                            }
                            if (k.a(F + R * 24, G - 24, 2)) {
                                J = 3;
                                H = R * 5;
                                I = -7;
                            } else if (k.a(F + R * 48, G - 24, 2)) {
                                J = 3;
                                H = R * 6;
                                I = -8;
                            } else if (k.a(F + R * 24, G - 48, 2)) {
                                J = 3;
                                H = R * 5;
                                I = -11;
                            }
                            J = 3;
                            R = F < ag ? 1 : -1;
                            H = R << 2;
                            I = -4;
                            return;
                        }
                        if (k.a(F + R * L, G, 2)) {
                            R = ag + (as << 5) > F ? 1 : -1;
                            J = 2;
                            H = R * (q.aC - 1);
                            return;
                        }
                    }
                } else {
                    J = 1;
                }
            }
        }
    }

    public static final void b() {
        int n2;
        if (k.a(ag, ah) == 111) {
            if ((ag - 24) / 24 >= 0 && k.a(ag - 24, ah) == 1000) {
                ag = k.b(ag - 24, ah);
            } else if ((ag + 24) / 24 <= k.a && k.a(ag + 24, ah) == 1000) {
                ag = k.b(ag + 24, ah);
            }
        }
        if (!aV && q.ap == 15 && ag >= 1608) {
            aV = true;
        }
        if (q.ap == 20 || k.a(ag, ah - 1, 4096)) {
            q.aC = 3;
        }
        if (q.aC == 3 && q.ap != 20 && !k.a(ag, ah - 1, 4096)) {
            q.aC = 5;
        }
        if (q.ap == 20 && !k) {
            ak = 6;
            ad = -1;
        }
        if (q.ap == 8) {
            if (k.a(ag, ah, 65536) && i) {
                i = false;
                q.bn = 0;
            }
            if (ag >= 1032 && ak != 4 && f && k.a(ag, ah, 262144)) {
                ak = 16;
                at = 0;
            }
        }
        if (aO) {
            q.av = ag - q.U + q.X * as;
            q.aw = ah - q.W;
        }
        if (aA < aB && ak == 8) {
            if (bM < 50) {
                ++bM;
            } else if (bM == 50) {
                q.A = true;
                if (aA <= aB) {
                    q.e(1, q.L + (aA += aB / 20) * q.M / aB);
                    n2 = 0;
                    while (n2 < 5) {
                        o.b.addElement(new j(ag - 14 + n2 * 7, ah - 12, 42));
                        n2 = (byte)(n2 + 1);
                    }
                    q.a("+" + aB / 20, 2, ag, ah - an, 0, -1);
                    if (aA > aB) {
                        aA = aB;
                    }
                }
                bM = 0;
            }
        } else {
            bM = 0;
        }
        switch (ak) {
            case 1: {
                ad = -1;
                if (++at > 30) {
                    at = 0;
                }
                al = at % 15 < 5 ? 0 : 1;
                h.l();
                n2 = 0;
                while (n2 < s) {
                    if (bV[n2] == 1 && ah == y[n2] && q.a(ag - x[n2]) < 30 && aY == -1 && q.K.equals("")) {
                        q.K = "Tr\u00f2 chuy\u1ec7n";
                        q.A = true;
                        aY = n2;
                        break;
                    }
                    ++n2;
                }
                n2 = 0;
                while (n2 < c.a) {
                    if (q.ap == 0) break;
                    if (ah == c.c[n2] && q.a(ag - c.b[n2]) < 30 && aX == -1) {
                        q.K = "Giao ti\u1ebfp";
                        q.A = true;
                        aX = n2;
                        break;
                    }
                    ++n2;
                }
                if (p.d || bn != 8 || bs != 1 || ah != bp || q.a(ag - bo) >= 30 || !q.K.equals(" ")) break;
                q.K = "N\u00f3i chuy\u1ec7n";
                q.A = true;
                break;
            }
            case 2: {
                if (++at >= 10) {
                    at = 0;
                    aE = 0;
                }
                al = (at >> 1) + 2;
                if (as == 1) {
                    if (k.a(ag + ao, ah - ap, 4)) {
                        ai = 0;
                        ag = k.e(ag + ao) - ao;
                    }
                } else if (k.a(ag - ao - 1, ah - ap, 8)) {
                    ai = 0;
                    ag = k.e(ag - ao - 1) + k.f + ao;
                }
                ag = (k.c(ag, ah - 1) & 0x40) == 64 ? (ag += ai >> 1) : (ag += ai);
                if (ai > 0) {
                    --ai;
                } else if (ai < 0) {
                    ++ai;
                } else {
                    ak = 1;
                    aE = 0;
                }
                if (q.aK != -1) {
                    if (q.a(ag - q.aL[q.aK]) >= 20) {
                        q.aK = -1;
                        if (k.a(ag, ah, 2)) {
                            ak = 1;
                        } else {
                            ak = 4;
                            aj = 2;
                            ai = 3 * as;
                            au = 0;
                        }
                    }
                } else if ((k.c(ag, ah) & 2) != 2) {
                    ak = 4;
                    ai = 3 * as;
                    au = 0;
                }
                if (f && q.aq % 8 == 0 && aA >= 2) {
                    if ((aA -= 2) <= 0) {
                        aA = 0;
                    }
                    q.e(1, q.L + aA * q.M / aB);
                    q.A = true;
                }
                h.s();
                q.a(as, ag - (as << 3), ah);
                h.l();
                break;
            }
            case 3: {
                ag += ai;
                if ((ah += aj) < 0) {
                    if (k.k != -1) break;
                    ah = 0;
                    aj = -1;
                }
                al = at == 0 ? 7 : 23;
                ++aj;
                if (e) {
                    if (aj == -3) {
                        al = 8;
                    } else if (aj == -2) {
                        al = 9;
                    } else if (aj == -1) {
                        al = 10;
                    } else if (aj == 0) {
                        al = 11;
                    }
                }
                if (aj == 0) {
                    ak = 4;
                    at = 0;
                    au = e ? 1 : 0;
                    aj = 1;
                    q.A = true;
                }
                if (av < 0) {
                    ++av;
                }
                if (as == 1) {
                    if ((k.c(ag + ao, ah) & 4) == 4) {
                        ag = k.e(ag + ao) - ao;
                        ai = 0;
                    }
                } else if ((k.c(ag - ao, ah) & 8) == 8) {
                    ag = k.e(ag + 24 - ao) + ao;
                    ai = 0;
                }
                if (ah <= an || !k.a(ag, ah - an, 16384)) break;
                ak = 4;
                at = 0;
                au = 0;
                aj = 1;
                break;
            }
            case 4: {
                if (q != 0 && r != 0) {
                    if (ah < r - 5) {
                        al = 7;
                        ah += aj;
                        if (aj < 20) {
                            ++aj;
                        }
                        if (q.a(ah - r) >= 11 && ah <= r) break;
                        ah = r;
                        break;
                    }
                    if (ah != r) break;
                    ah = k.e(ah + 3);
                    ak = aj > 12 ? 8 : 1;
                    aj = 0;
                    ai = 0;
                    au = 0;
                    at = 0;
                    r = 0;
                    q = 0;
                    j = false;
                    ah = k.d(ah);
                    break;
                }
                if (!bh && k.a(ag, ah, 131072)) {
                    bh = true;
                    o.a.addElement(new j(ag, k.d(ah) - 10, 19));
                    break;
                }
                if (bh) break;
                if ((k.a(ag, ah, 4096) || k.a(ag, ah, 32768)) && !k) {
                    ak = 6;
                }
                if (ah + 4 >= k.d) {
                    ak = 6;
                    ay = 0;
                    break;
                }
                ad = -1;
                ag += ai;
                ah += aj;
                if (aj < 20) {
                    ++aj;
                }
                if (ai > 0) {
                    --ai;
                }
                al = 7;
                if (ah + 4 >= k.d) break;
                if (aj > 4) {
                    if (p) {
                        h.j();
                    }
                    if ((k.c(ag, ah + 3) & 2) == 2) {
                        if (p) {
                            q.n = 0;
                            p = false;
                        }
                        ak = aj > 12 ? 8 : 1;
                        aj = 0;
                        ai = 0;
                        au = 0;
                        at = 0;
                        ah = k.e(ah + 3);
                        q.a(-1, ag - -8, ah);
                        q.a(1, ag - 8, ah);
                        break;
                    }
                }
                if (k.a(ag, ah, 64) && ah % k.f > 8) {
                    p = false;
                    q.d(ag, k.d(ah) + 8);
                    ah = k.d(ah) + 8;
                    ak = 11;
                    ai = as << 1;
                    aj >>= 2;
                    ah = k.d(ah) + 12;
                    if (aA == 0 || !e) {
                        ak = 12;
                    }
                }
                if (au == 1 && !aQ) {
                    if (aj == 3) {
                        al = 11;
                    } else if (aj == 2) {
                        al = 8;
                    } else if (aj == 1) {
                        al = 9;
                    } else if (aj == 0) {
                        al = 10;
                    }
                } else {
                    al = 23;
                }
                if (aj > 6) {
                    al = 12;
                }
                if (av < 0) {
                    ++av;
                }
                if (as == 1) {
                    if ((k.c(ag + ao, ah) & 4) == 4) {
                        ag = k.e(ag + ao) - ao;
                        ai = 0;
                    }
                } else if ((k.c(ag - ao, ah) & 8) == 8) {
                    ag = k.e(ag + 24 - ao) + ao;
                    ai = 0;
                }
                h.q();
                break;
            }
            case 5: {
                ++at;
                if (as == 1) {
                    if ((k.c(ag + ao, ah - ap) & 4) == 4) {
                        ai = 0;
                    }
                } else if ((k.c(ag - ao, ah - ap) & 8) == 8) {
                    ai = 0;
                }
                if (ah > an && k.a(ag, ah - an, 16384)) {
                    if (!k.a(ag, ah, 2)) {
                        ak = 4;
                        at = 0;
                        au = 0;
                        aj = 1;
                    } else {
                        ah = k.d(ah);
                    }
                }
                ag += ai;
                if ((ah += aj) < 0) {
                    aj = 0;
                    ah = 0;
                }
                if (aj == 0) {
                    if (q.aK == -1 && (k.c(ag, ah) & 2) != 2) {
                        ak = 4;
                        ad = -1;
                        ai = (q.aC >> 1) * as;
                        au = 0;
                        at = 0;
                    }
                    if (q.aK != -1 && q.a(ag - q.aL[q.aK]) >= 20) {
                        q.aK = -1;
                        if (k.a(ag, ah, 2)) {
                            ak = 1;
                        } else {
                            ak = 4;
                            aj = 2;
                            ai = 3 * as;
                            ad = -1;
                            au = 0;
                        }
                    }
                } else if (aj < 0) {
                    if (++aj == 0) {
                        aj = 1;
                    }
                } else {
                    if (aj < 20 && at % 5 == 0) {
                        ++aj;
                    }
                    if (aj > 3) {
                        aj = 3;
                    }
                    if (aj > 2 && (k.c(ag, ah + 3) & 2) == 2) {
                        aj = 0;
                        ai = 0;
                        ah = k.e(ah + 3);
                    }
                    if (k.a(ag, ah, 64) && ah % k.f > 8) {
                        q.d(ag, k.d(ah) + 8);
                        ak = 11;
                        ai = as << 1;
                        aj >>= 2;
                        ah = k.d(ah) + 12;
                        ad = -1;
                        if (aA != 0) break;
                        ak = 12;
                        break;
                    }
                    if (k.a(ag, ah, 4096)) {
                        ak = 12;
                        ad = -1;
                        break;
                    }
                    h.q();
                }
                if (ai > 0) {
                    --ai;
                } else if (ai < 0) {
                    ++ai;
                }
                if (ai != 0) {
                    h.s();
                }
                block22 : switch (aL) {
                    case 0: {
                        switch (at) {
                            case 1: {
                                ad = -1;
                                al = 13;
                                break;
                            }
                            case 2: {
                                ad = 0;
                                al = 15;
                                break;
                            }
                            case 3: {
                                ad = 1;
                                al = 16;
                                h.p();
                                break;
                            }
                            case 4: {
                                ad = 2;
                                al = 16;
                                break;
                            }
                            case 5: {
                                ad = 2;
                                al = 16;
                                if (!(aP && co || !aP)) {
                                    aP = false;
                                    aL = 2;
                                } else {
                                    aL = 3;
                                }
                                at = 0;
                            }
                        }
                        break;
                    }
                    case 3: {
                        switch (at) {
                            case 1: {
                                ad = 3;
                                al = 13;
                                break;
                            }
                            case 2: {
                                ad = 4;
                                al = 13;
                                break;
                            }
                            case 3: {
                                ad = 5;
                                al = 13;
                                if (aP) {
                                    aP = false;
                                    aL = 2;
                                } else {
                                    aL = 10;
                                    if (co) {
                                        co = false;
                                    }
                                }
                                at = 0;
                            }
                        }
                        break;
                    }
                    case 9: {
                        switch (at) {
                            case 1: 
                            case 2: {
                                ad = 6;
                                al = 17;
                                break;
                            }
                            case 3: {
                                ad = 12;
                                al = 19;
                                break;
                            }
                            case 4: {
                                ad = 13;
                                al = 19;
                                if (!(!i.j || ae == 0 && (af == 1 && aA < 5 || af == 2 && aA < 6 || af == 3 && aA < 7 || af == 0 && aA < 1))) {
                                    cn = false;
                                    if (q.a(ag - i.m) < 40 && q.a(ah - i.n) < 10) {
                                        cn = true;
                                        q.b(i.m, i.n - (i.x >> 1), as);
                                        i.t = 9;
                                        i.v = -8;
                                        q.u();
                                    }
                                }
                                if (!(!f.o || !i.b || ae == 0 && (af == 1 && aA < 5 || af == 2 && aA < 6 || af == 3 && aA < 7 || af == 0 && aA < 1))) {
                                    cn = false;
                                    if (q.a(ag - f.b) < 50 && q.a(ah - f.c) < 15) {
                                        cn = true;
                                        q.b(f.b, f.c - (f.n >> 1), as);
                                        f.j = 9;
                                        f.l = -8;
                                        f.m = 0;
                                        q.u();
                                    }
                                }
                                if (!(!i.E || ae == 0 && (af == 1 && aA < 5 || af == 2 && aA < 6 || af == 3 && aA < 7 || af == 0 && aA < 1))) {
                                    cn = false;
                                    if (q.a(ag - i.H) < 50 && q.a(ah - i.I) < 50) {
                                        cn = true;
                                        q.b(i.H, i.I - 15, as);
                                        i.K = 9;
                                        i.P = -8;
                                        q.u();
                                    }
                                }
                                if (ae == 0) {
                                    if (af == 1 && aA < 5 || af == 2 && aA < 6 || af == 3 && aA < 7) break block22;
                                    if (af == 0 && aA < 1) break block22;
                                }
                                n2 = h.t();
                                cn = false;
                                if (n2 == -1) break block22;
                                cn = true;
                                q.b(a.e[n2], a.f[n2] - (a.g[n2] >> 1), as);
                                a.i[n2] = 9;
                                a.j[n2] = -8;
                                q.u();
                                break;
                            }
                            case 5: {
                                ad = 14;
                                al = 19;
                                break;
                            }
                            case 6: {
                                ad = 14;
                                al = 19;
                                if (cn) {
                                    ak = 3;
                                    ai = 0;
                                    aj = -8;
                                    co = true;
                                    aP = false;
                                    break;
                                }
                                at = 0;
                                au = 0;
                                aL = 10;
                            }
                        }
                        break;
                    }
                    case 2: {
                        switch (at) {
                            case 1: {
                                ad = 6;
                                al = 17;
                                break;
                            }
                            case 2: {
                                ad = 7;
                                al = 18;
                                break;
                            }
                            case 3: {
                                ad = 8;
                                al = 19;
                                break;
                            }
                            case 4: {
                                ad = 9;
                                al = 20;
                                if (co) {
                                    h.o();
                                    break;
                                }
                                h.p();
                                break;
                            }
                            case 5: {
                                ad = 10;
                                al = 20;
                                break;
                            }
                            case 6: {
                                ad = 11;
                                al = 20;
                                if (ae == 0 && af == 3) {
                                    o.b.addElement(new j(ag - (bQ[ad] - 20) * as, ah + bR[ad] + 5, 39));
                                    ((j)o.b.lastElement()).a = as;
                                }
                                if (co) {
                                    co = false;
                                    aL = 10;
                                    aP = false;
                                } else if (aP && aK < 2) {
                                    if (q.ap == 14) {
                                        if (cn) {
                                            aP = false;
                                            aL = 9;
                                        } else {
                                            aP = false;
                                            aL = 10;
                                            ++aK;
                                        }
                                    } else if (aj == 0 && cn) {
                                        aP = false;
                                        aL = 9;
                                    } else {
                                        aP = false;
                                        aL = 10;
                                        ++aK;
                                    }
                                } else {
                                    aL = 10;
                                }
                                at = 0;
                            }
                        }
                        break;
                    }
                    case 10: {
                        switch (at) {
                            case 1: {
                                co = false;
                                al = 13;
                                ad = -1;
                                ak = aj != 0 ? 4 : 1;
                                aK = 0;
                                au = 0;
                            }
                        }
                    }
                }
                if (ae == 1 || g || h) {
                    ad = -1;
                }
                if (ak != 5 || al == 46) break;
                if (aj != 0) {
                    al += 14;
                    break;
                }
                if (ai == 0) break;
                al += 22;
                break;
            }
            case 6: {
                h.k();
                break;
            }
            case 7: {
                q.bb[0] = 0;
                if ((k.c(ag + ao, ah - ap) & 4) == 4) {
                    ai = ai > 0 ? -ai : ai;
                    ag = k.e(ag);
                }
                if ((k.c(ag - ao, ah - ap) & 8) == 8) {
                    ai = ai > 0 ? ai : -ai;
                    ag = k.e(ag);
                }
                ag += ai;
                ah += aj;
                al = 21;
                if (ai > 1) {
                    --ai;
                }
                if (ai < -1) {
                    ++ai;
                }
                if (++aj > 0) {
                    aj = 0;
                }
                if (++at >= 6) {
                    ak = 4;
                    av = -20;
                    au = 0;
                    at = 0;
                    aj = -1;
                }
                h.r();
                break;
            }
            case 8: {
                al = 22;
                if (at < 5) {
                    ++at;
                } else if (!q.t[8]) {
                    ak = 1;
                }
                ++au;
                break;
            }
            case 11: {
                h.n();
                break;
            }
            case 12: {
                ah += aj;
                if (aj < 20 && q.aq % 2 == 0) {
                    ++aj;
                }
                al = 7;
                if (ah >= k.d) {
                    ay = 0;
                    ak = 6;
                    ad = -1;
                    break;
                }
                if (k.a(ag, ah, 2)) {
                    if (!k.a(ag, (ah = k.d(ah)) - 2, 64) && !k) {
                        ak = 6;
                    } else {
                        ak = 1;
                        aj = 0;
                        ai = 0;
                        au = 0;
                        at = 0;
                    }
                    ah = k.e(ah);
                    break;
                }
                if (k || !k.a(ag, ah, 4096)) break;
                ay = 0;
                ak = 6;
                ad = -1;
                break;
            }
            case 19: {
                n2 = 0;
                f.j = 2;
                int n3 = al = f.k == 2 ? 44 : 43;
                if (at > 0) {
                    --at;
                }
                if (as == 1) {
                    if (ag + ao >= 4680 || k.a(ag + ao, f.c - ap, 4)) {
                        ai = 0;
                        ag = k.e(ag + ao) - ao;
                        n2 = 1;
                    }
                } else if (q.ap == 2 && ag - ao - 1 <= 120 || k.a(ag - ao - 1, f.c - ap, 8)) {
                    ai = 0;
                    ag = k.e(ag - ao - 1) + k.f + ao;
                    n2 = 1;
                }
                ag = (k.c(ag, f.c - 1) & 0x20000) == 131072 ? (ag += ai >> 1) : (ag += ai);
                f.b = n2 == 1 ? ag : ag - as * 10;
                f.c = ah + 9;
                if (ai > 0) {
                    --ai;
                } else if (ai < 0) {
                    ++ai;
                } else {
                    ak = 18;
                    f.j = 3;
                    aE = 0;
                }
                if (q.aK != -1) {
                    if (q.a(ag - q.aL[q.aK]) >= 20) {
                        q.aK = -1;
                        if (k.a(ag, ah, 2)) {
                            ak = 18;
                        } else {
                            ak = 20;
                            aj = 2;
                            ai = 3 * as;
                            au = 0;
                        }
                    }
                } else if ((k.c(ag, f.c) & 0x20000) != 131072 && (k.c(ag, f.c) & 2) != 2) {
                    ak = 20;
                    f.j = 11;
                    ai = 3 * as;
                    au = 0;
                }
                h.l();
                n2 = h.t();
                if (n2 == -1 || a.i[n2] != 2 && a.i[n2] != 3) break;
                a.a(n2, 2, -as, 0);
                q.b(a.e[n2], a.f[n2] - (a.g[n2] >> 1), as);
                q.a("-2", 0, a.e[n2], a.f[n2] - a.g[n2], 0, -2);
                break;
            }
            case 18: {
                f.j = 3;
                f.b = ag;
                f.c = ah + 9;
                al = f.k == 0 ? 43 : 44;
                break;
            }
            case 20: {
                f.j = 11;
                try {
                    if (as == 1) {
                        if (ag + ao >= 4680 || (k.c(ag + ao, f.c - ap) & 4) == 4) {
                            ai = 0;
                        }
                    } else if (q.ap == 2 && ag - ao - 1 <= 120 || (k.c(ag - ao, f.c - ap) & 8) == 8) {
                        ai = 0;
                    }
                    ad = -1;
                    ag += ai;
                    ah += aj;
                    if (aj < 20) {
                        ++aj;
                    }
                    if (ai > 0) {
                        --ai;
                    }
                    al = q.aq % 8 > 3 ? 43 : 44;
                    if (aj > 4 && ((k.c(ag, f.c) & 0x20000) == 131072 || (k.c(ag, f.c) & 2) == 2)) {
                        ak = 18;
                        f.j = 3;
                        aj = 0;
                        ai = 0;
                        au = 0;
                        at = 0;
                        if ((k.c(ag, f.c) & 0x20000) == 131072) {
                            f.c = k.e(f.c) + 9;
                            ah = f.c - 9;
                        } else {
                            ah = k.e(f.c) - 9;
                        }
                    }
                    f.b = ag - (as << 3);
                    f.c = ah + 9;
                    if (k.a(ag, f.c, 4096)) {
                        null[q.aj] = true;
                        q.aj = -1;
                        ak = 12;
                    }
                }
                catch (Exception exception) {}
                if (av >= 0) break;
                ++av;
                break;
            }
            case 21: {
                f.j = 7;
                if (as == 1) {
                    if (ag + ao >= 4680 || (k.c(ag + ao, f.c - ap) & 4) == 4) {
                        ai = 0;
                    }
                } else if (q.ap == 2 && ag - ao - 1 <= 120 || (k.c(ag - ao, f.c - ap) & 8) == 8) {
                    ai = 0;
                }
                ag += ai;
                if ((ah += aj) < 0) {
                    if (k.k != -1) break;
                    ah = 0;
                    aj = 0;
                }
                f.b = ag;
                f.c = ah + 9;
                if (ai > 0) {
                    --ai;
                }
                if (++aj == 0) {
                    ak = 20;
                    f.j = 11;
                    at = 0;
                    au = 1;
                    aj = 1;
                    q.A = true;
                }
                if (av < 0) {
                    ++av;
                }
                al = q.aq % 8 > 3 ? 43 : 44;
                if (at <= 0) break;
                --at;
                al = 45;
                break;
            }
            case 23: {
                h.m();
                break;
            }
            case 13: {
                if (q.ba[0] != 0) break;
                al = 51;
                break;
            }
            case 14: {
                ++au;
                if (!k.a(ag, ah, 2)) {
                    au = 0;
                    ak = 4;
                }
                if (au >= 4) {
                    if (ah % 24 != 0 || !k.a(ag, ah, 2)) {
                        au = 0;
                        at = 0;
                        ak = 4;
                    } else {
                        au = 0;
                        ak = 1;
                    }
                }
                al = 46;
                break;
            }
            case 15: {
                if (av < 6) {
                    ah -= ++av;
                    ag += as << 1;
                    if (av != 6) break;
                    if (as == 1) {
                        bd = 137;
                        bb = 1;
                    } else {
                        bd = 57;
                        bb = -1;
                    }
                    bc = 8;
                    h.a(8);
                    break;
                }
                if (bb == 1) {
                    if ((bd -= cp) <= bf) {
                        bd = bf;
                        if (++cq == 3) {
                            bg = false;
                            bb = -1;
                            cq = 0;
                            if (bf != 97) {
                                al = al == 47 ? 48 : 47;
                            }
                        }
                    }
                } else if (bb == -1) {
                    if (!bg && q.a((bd += cp) - 97) < 2) {
                        bd = 97;
                        if (bc > 0 && !bg) {
                            h.a(--bc);
                        }
                    }
                    if (bd >= be) {
                        bd = be;
                        if (++cq == 3) {
                            bg = false;
                            bb = 1;
                            cq = 0;
                            if (be != 97) {
                                al = al == 47 ? 48 : 47;
                            }
                        }
                    }
                }
                ag = q.aY[0] + l.b(bd, 80) + (as == -1 ? 6 : -1);
                ah = q.aZ[0] + l.c(bd, 80);
                break;
            }
            case 16: {
                ad = -1;
                ag += ai;
                if ((ah += aj) < 0) {
                    if (k.k != -1) break;
                    ah = 0;
                    aj = -1;
                }
                if (ai > 0 && aj > 0) {
                    --ai;
                }
                if (aj == 0) {
                    ++ai;
                }
                if (aj < 20) {
                    ++aj;
                }
                al = at < 5 ? 4 : (at < 10 ? 23 : 7);
                ++at;
                if (as == 1) {
                    if ((k.c(ag + ao, ah) & 4) == 4) {
                        ai = 0;
                        ak = 4;
                        al = 7;
                        at = 0;
                        aj = 5;
                    }
                } else if ((k.c(ag - ao, ah) & 8) == 8) {
                    ai = 0;
                    ak = 4;
                    al = 7;
                    at = 0;
                    aj = 5;
                }
                if (ah <= an || !k.a(ag, ah - an, 16384)) break;
                ak = 4;
                ai = 0;
                at = 0;
                au = 0;
                aj = 1;
            }
        }
        if (q.aq % 3 == 0) {
            if (aQ) {
                if ((aA -= 7) <= 0) {
                    aA = 0;
                    aQ = false;
                    int n4 = 0;
                    while (n4 < 4) {
                        Image image = A[n4];
                        h.A[n4] = cj[n4];
                        h.cj[n4] = image;
                        ++n4;
                    }
                }
                q.A = true;
            }
            if (f && !o) {
                o = true;
                Image[] imageArray = new Image[10];
                B = imageArray;
                imageArray[0] = q.c("/cp/cl0.png");
                int n5 = 1;
                while (n5 < 10) {
                    h.B[n5] = q.c("/cp/cl" + n5 + "0.png");
                    ++n5;
                }
            }
            if (!f && o) {
                o = false;
                B = new Image[10];
                int n6 = 0;
                while (n6 < 10) {
                    h.B[n6] = q.c("/cp/cl" + n6 + ".png");
                    ++n6;
                }
            }
        }
    }

    public static void a(int n2) {
        switch (n2) {
            case 0: {
                bf = 97;
                be = 97;
                cp = 0;
                return;
            }
            case 1: {
                be = 102;
                bf = 92;
                cp = 1;
                return;
            }
            case 2: {
                be = 107;
                bf = 87;
                cp = 1;
                return;
            }
            case 3: {
                be = 112;
                bf = 82;
                cp = 2;
                return;
            }
            case 4: {
                be = 117;
                bf = 77;
                cp = 2;
                return;
            }
            case 5: {
                be = 122;
                bf = 72;
                cp = 3;
                return;
            }
            case 6: {
                be = 127;
                bf = 67;
                cp = 3;
                return;
            }
            case 7: {
                be = 132;
                bf = 62;
                cp = 4;
                return;
            }
            case 8: {
                be = 137;
                bf = 57;
                cp = 4;
                return;
            }
            case 9: {
                be = 142;
                bf = 52;
                cp = 5;
                return;
            }
            case 10: {
                be = 147;
                bf = 47;
                cp = 5;
                return;
            }
            case 11: {
                be = 152;
                bf = 42;
                cp = 6;
                return;
            }
        }
    }

    private static boolean j() {
        if (a.c > 0) {
            int n2 = 0;
            while (n2 < a.c) {
                if (q.a(ag - a.e[n2]) <= 20 && a.f[n2] - ah <= 20 && a.f[n2] > ah) {
                    if (!a.a(n2, aH, -as, 2)) {
                        q.a("-" + aH, 0, a.e[n2], a.f[n2] - a.g[n2], 0, -2);
                    }
                    p = false;
                    q.n = 0;
                    return true;
                }
                ++n2;
            }
        }
        if (f.o && f.a == -1 && q.a(ag - f.b) <= 25 && f.c - ah <= 40) {
            f.a(aH, as, 2);
            p = false;
            q.n = 0;
            return true;
        }
        if (i.a && q.a(ag - i.m) <= i.y && i.n - ah <= i.x && i.n > ah) {
            i.a(aH, as, 1);
            p = false;
            q.n = 0;
            return true;
        }
        return false;
    }

    private static void k() {
        ag += ai;
        if (ai > 1) {
            --ai;
        }
        if (ai < -1) {
            ++ai;
        }
        al = 21;
        if ((ah += ++aj) >= k.d || ah > q.ay + q.T + an || k.a(ag, ah - an, 4096)) {
            if (q.aH && q.j[q.aG][q.aI] == 5) {
                ay = 1;
                aA = 1;
                ak = 1;
                ag = 1;
                ah = 1;
                aj = 0;
                ai = 0;
                au = 0;
                at = 0;
                q.A = true;
                q.e(q.k[q.aG][q.aI]);
                ++q.aI;
                return;
            }
            ag = aF;
            ah = aG;
            if (q.ap == 15) {
                if (!aV) {
                    ag = 192;
                    ah = 384;
                } else {
                    ag = 1800;
                    ah = 432;
                }
            }
            ak = 4;
            ay = az / 3;
            if ((aA /= 3) < 0) {
                aA = 0;
            }
            aj = 0;
            ai = 0;
            au = 0;
            at = 0;
            if ((aN >>= 1) < 0) {
                aN = 0;
            }
            if (q.ap == 20) {
                ag = 624;
                ah = 600;
                ak = 1;
                q.c(5);
            }
            if (q.ap == 14) {
                if (!d.g) {
                    ag = 48;
                    ah = 528;
                    ak = 1;
                    q.ak = null;
                    q.c(13);
                } else {
                    ag = 48;
                    ah = 192;
                    ak = 4;
                }
            }
            q.v();
            q.bo = 0;
            q.bn = 0;
            k = false;
            i = false;
            n.l = false;
            n.m = false;
            q.A = true;
            i.q = i.r;
            bi = bj;
            f.g = f.h;
            i.N = i.O;
            if (d.h) {
                int n2 = 0;
                while (n2 < d.i) {
                    if (d.r[n2] != -1) {
                        d.d[n2] = d.j;
                    }
                    ++n2;
                }
            }
            q.z();
            q.g(5);
            q.B = 7;
        }
    }

    private static final void l() {
        if (q.a) {
            return;
        }
        if (k.a(ag, ah + 1, 2048)) {
            k.b(ag, ah + 1, 1024);
            k.b(ag, ah - 2, 1024);
        }
        if (k.a(ag - k.f, ah + 1, 1024)) {
            k.c(ag - k.f, ah + 1, 1024);
            k.c(ag - k.f, ah - 2, 1024);
        }
        if (k.a(ag + k.f, ah + 1, 1024)) {
            k.c(ag + k.f, ah + 1, 1024);
            k.c(ag + k.f, ah - 2, 1024);
        }
    }

    private static void m() {
        if (++at == 1) {
            q.c(ag, ah - 40);
            q.n = 0;
            if (cj == null) {
                cj = new Image[4];
                try {
                    h.cj[0] = Image.createImage((String)"/cp/ch0x.png");
                    h.cj[1] = Image.createImage((String)"/cp/ch1x.png");
                }
                catch (Exception exception) {}
                h.cj[2] = cj[1];
                h.cj[3] = cj[0];
            }
        } else if (at >= 30) {
            ak = 1;
            at = 0;
            if (aA == 0) {
                return;
            }
            int n2 = 0;
            while (n2 < 4) {
                Image image = A[n2];
                h.A[n2] = cj[n2];
                h.cj[n2] = image;
                ++n2;
            }
            aQ = !aQ;
        } else if (at <= 10) {
            if (at % 5 == 0) {
                int n3 = 0;
                while (n3 < 4) {
                    Image image = A[n3];
                    h.A[n3] = cj[n3];
                    h.cj[n3] = image;
                    ++n3;
                }
            }
        } else if (at < 20) {
            if (at % 3 == 0) {
                int n4 = 0;
                while (n4 < 4) {
                    Image image = A[n4];
                    h.A[n4] = cj[n4];
                    h.cj[n4] = image;
                    ++n4;
                }
            }
        } else if (at < 30 && at % 2 == 0) {
            int n5 = 0;
            while (n5 < 4) {
                Image image = A[n5];
                h.A[n5] = cj[n5];
                h.cj[n5] = image;
                ++n5;
            }
        }
        if (at % 2 > 0) {
            al = 0;
            return;
        }
        al = 1;
    }

    private static void n() {
        if (!k.a(ag, ah, 64)) {
            ak = 4;
        }
        if (++at >= 5) {
            at = 0;
            aE = 0;
        }
        al = at + 2;
        if (as == 1) {
            if (k.a(ag + ao, ah - 1, 4)) {
                ai = 0;
                ag = k.e(ag + ao) - ao;
            }
        } else if (k.a(ag - ao - 1, ah - 1, 8)) {
            ai = 0;
            ag = k.e(ag - ao - 1) + k.f + ao;
        }
        ag += ai;
        if (ai > 0) {
            --ai;
        } else if (ai < 0) {
            ++ai;
        } else {
            ak = 12;
            aE = 0;
        }
        if (q.aq % 8 == 0) {
            if ((aA -= 6) <= 0) {
                aA = 0;
                ai = 0;
                ak = 12;
            }
            q.e(1, q.L + aA * q.M / aB);
            q.A = true;
        }
        int n2 = 0;
        while (n2 < q.aJ) {
            if (q.a(ag - q.aL[n2]) < 20 && q.aM[n2] > ah && q.aM[n2] - 24 < ah) {
                ai = 0;
                ag = ag < q.aL[n2] ? q.aL[n2] - 20 : q.aL[n2] + 20;
                ak = 12;
                break;
            }
            ++n2;
        }
        q.d(ag, ah);
        q.a(as, ag - (as << 3), ah);
    }

    public static void c() {
        int n2 = 0;
        while (n2 < s) {
            block0 : switch (bV[n2]) {
                case 1: {
                    int n3;
                    int n4 = n3 = n2;
                    bW[n4] = bW[n4] + 1;
                    if (bW[n3] > 30) {
                        h.bW[n3] = 0;
                    }
                    h.bY[n3] = bW[n3] % (n3 + 18) < n3 + 6 ? 0 : 1;
                    if (v[n3]) {
                        h.bX[n3] = w;
                        h.z[n3] = w > x[n3] ? 1 : -1;
                        h.bT[n3] = z[n3] << 2;
                        h.bT[n3] = z[n3] > 0 ? bT[n3] + n3 % 2 : bT[n3] - n3 % 2;
                        h.bV[n3] = 2;
                        break;
                    }
                    if (u) break;
                    int n5 = n3;
                    bX[n5] = bX[n5] + 1;
                    if (bX[n3] > (cb[n3] + x[n3]) % 20 + (cb[n3] >> 1)) {
                        if (q.aQ && aY == n3) break;
                        h.bX[n3] = 0;
                        int n6 = (q.aq + cb[n3]) % 120 - 60;
                        n6 = n6 > 0 ? (n6 += cb[n3]) : (n6 -= cb[n3]);
                        h.bX[n3] = x[n3] + n6;
                        h.z[n3] = n6 > 0 ? 1 : -1;
                        h.bT[n3] = z[n3] << 2;
                        h.bV[n3] = 2;
                        if (aY == n3) {
                            aY = -1;
                            q.K = " ";
                            q.A = true;
                        }
                    }
                    if (q.aq % 10 != 1) break;
                    h.h(n3);
                    break;
                }
                case 2: {
                    int n3;
                    int n7 = n3 = n2;
                    bW[n7] = bW[n7] + 1;
                    if (bW[n3] >= 10) {
                        h.bW[n3] = 0;
                    }
                    h.bY[n3] = (bW[n3] >> 1) + 2;
                    int n8 = n3;
                    x[n8] = x[n8] + bT[n3];
                    if (x[n3] > ce[n3] || x[n3] < cd[n3] || q.a(x[n3] - bX[n3]) < 8) {
                        h.bX[n3] = 0;
                        h.bW[n3] = 0;
                        int n9 = n3;
                        x[n9] = x[n9] - bT[n3];
                        h.bT[n3] = 0;
                        h.bV[n3] = 1;
                        h.v[n3] = false;
                        break;
                    }
                    if (q.aq % 10 != 0) break;
                    h.h(n3);
                    break;
                }
                case 5: {
                    int n3;
                    int n10 = n3 = n2;
                    bW[n10] = bW[n10] + 1;
                    switch (bX[n3]) {
                        case 0: {
                            switch (bW[n3]) {
                                case 1: 
                                case 2: 
                                case 3: {
                                    h.ca[n3] = -1;
                                    h.bY[n3] = 13;
                                    break block0;
                                }
                                case 4: {
                                    h.ca[n3] = 0;
                                    h.bY[n3] = 14;
                                    break block0;
                                }
                                case 5: {
                                    h.ca[n3] = 1;
                                    h.bY[n3] = 15;
                                    break block0;
                                }
                                case 6: {
                                    h.ca[n3] = 1;
                                    h.bY[n3] = 15;
                                    h.g(n3);
                                    break block0;
                                }
                                case 7: {
                                    h.ca[n3] = 2;
                                    h.bY[n3] = 16;
                                    break block0;
                                }
                                case 8: {
                                    h.ca[n3] = 2;
                                    h.bY[n3] = 16;
                                    h.bX[n3] = 2;
                                    h.bW[n3] = 1;
                                }
                            }
                            break block0;
                        }
                        case 3: {
                            switch (bW[n3]) {
                                case 1: {
                                    h.ca[n3] = 1;
                                    h.bY[n3] = 15;
                                    break block0;
                                }
                                case 2: {
                                    h.ca[n3] = 1;
                                    h.bY[n3] = 15;
                                    break block0;
                                }
                                case 3: {
                                    h.ca[n3] = 2;
                                    h.bY[n3] = 16;
                                    break block0;
                                }
                                case 4: {
                                    h.ca[n3] = 2;
                                    h.bY[n3] = 16;
                                    h.bX[n3] = 1;
                                    h.bW[n3] = 1;
                                }
                            }
                            break block0;
                        }
                        case 1: {
                            switch (bW[n3]) {
                                case 1: {
                                    h.ca[n3] = 1;
                                    h.bY[n3] = 15;
                                    break block0;
                                }
                                case 2: {
                                    h.ca[n3] = 1;
                                    h.bY[n3] = 15;
                                    h.bX[n3] = 10;
                                }
                            }
                            break block0;
                        }
                        case 2: {
                            switch (bW[n3]) {
                                case 1: 
                                case 2: 
                                case 3: {
                                    h.ca[n3] = 3;
                                    h.bY[n3] = 17;
                                    break block0;
                                }
                                case 4: {
                                    h.ca[n3] = 4;
                                    h.bY[n3] = 18;
                                    break block0;
                                }
                                case 5: {
                                    h.ca[n3] = 5;
                                    h.bY[n3] = 19;
                                    break block0;
                                }
                                case 6: {
                                    h.ca[n3] = 6;
                                    h.bY[n3] = 20;
                                    h.g(n3);
                                    break block0;
                                }
                                case 7: {
                                    h.ca[n3] = 6;
                                    h.bY[n3] = 20;
                                    h.bX[n3] = 3;
                                    h.bW[n3] = 1;
                                }
                            }
                            break block0;
                        }
                        case 10: {
                            switch (bW[n3]) {
                                case 1: {
                                    h.ca[n3] = 2;
                                    h.bY[n3] = 16;
                                    break block0;
                                }
                                case 2: {
                                    h.ca[n3] = 0;
                                    h.bY[n3] = 14;
                                    break block0;
                                }
                                case 3: 
                                case 4: {
                                    h.bY[n3] = 13;
                                    h.ca[n3] = -1;
                                    break block0;
                                }
                                case 5: {
                                    h.bY[n3] = 13;
                                    h.ca[n3] = -1;
                                    h.bV[n3] = 1;
                                    h.bX[n3] = 120;
                                    h.bW[n3] = 0;
                                }
                            }
                        }
                    }
                }
            }
            ++n2;
        }
    }

    public static void b(int n2) {
        s = 0;
        v = new boolean[0];
        if (n2 == 16) {
            s = 5;
            x = new int[5];
            y = new int[s];
            bT = new int[s];
            bU = new int[s];
            bV = new int[s];
            bW = new int[s];
            z = new int[s];
            bX = new int[s];
            bY = new int[s];
            bZ = new int[s];
            ca = new int[s];
            cb = new int[s];
            cc = new int[s];
            cd = new int[s];
            ce = new int[s];
            t = new String[s][];
            v = new boolean[s];
            h.a(0, 0, 8, 10, 0, 0, 0, null);
            h.a(1, 1, 9, 10, 0, 0, 0, null);
            h.a(2, 2, 8, 11, 0, 0, 0, null);
            h.a(3, 3, 14, 11, 0, 0, 0, null);
            h.f(4);
            h.z[2] = 1;
            h.z[1] = 1;
            h.z[0] = 1;
            h.z[3] = -1;
            n2 = 0;
            while (n2 < 4) {
                h.v[n2] = false;
                ++n2;
            }
            u = true;
        }
    }

    private static void f(int n2) {
        if (cm == null) {
            cm = new Image[n2][2];
            int n3 = 0;
            while (n3 < n2) {
                int n4 = 0;
                while (n4 < 2) {
                    try {
                        h.cm[n3][n4] = Image.createImage((String)("/cp/f" + n3 + "h" + n4 + ".png"));
                    }
                    catch (Exception exception) {}
                    ++n4;
                }
                ++n3;
            }
        }
    }

    private static void a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, String[] stringArray) {
        h.x[n2] = n4 * 24 + (n2 << 2);
        h.y[n2] = n5 * 24;
        h.bT[n2] = 0;
        h.bU[n2] = 0;
        h.bV[n2] = 1;
        h.bW[n2] = 0;
        h.bX[n2] = (n2 + 1) * 20 + n2;
        h.bY[n2] = 0;
        h.bZ[n2] = n3;
        h.ca[n2] = -1;
        h.cc[n2] = -1;
        h.cd[n2] = 12;
        h.ce[n2] = 12;
        h.cb[n2] = 0;
        h.t[n2] = null;
    }

    public static final void d() {
        ad = -1;
        if (bN == null) {
            bN = q.c("/t/c.png");
        }
        if (cf == null) {
            try {
                cf = new Image[22];
                int n2 = 0;
                while (n2 < 22) {
                    h.cf[n2] = Image.createImage((String)("/cp/cb" + n2 + ".png"));
                    ++n2;
                }
                A = new Image[11];
                n2 = 0;
                while (n2 < 11) {
                    h.A[n2] = Image.createImage((String)("/cp/ch" + n2 + ".png"));
                    ++n2;
                }
                B = new Image[10];
                ck = new Image[10];
                n2 = 0;
                while (n2 < 10) {
                    h.B[n2] = Image.createImage((String)("/cp/cl" + n2 + ".png"));
                    h.ck[n2] = Image.createImage((String)("/cp/cl" + n2 + ".png"));
                    ++n2;
                }
            }
            catch (Exception exception) {}
        }
        h.e();
        am = 22;
        an = 32;
        ao = am >> 1;
        ap = an >> 1;
        aq = am >> 2;
        ar = an >> 2;
        aX = -1;
    }

    public static void e() {
        cl = new Image[15];
        System.gc();
        int n2 = 0;
        while (n2 < 15) {
            try {
                h.cl[n2] = Image.createImage((String)("/c/w" + n2 + "t" + af + ".png"));
            }
            catch (Exception exception) {
                try {
                    h.cl[n2] = Image.createImage((String)("/c/w" + n2 + "t0.png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
            }
            ++n2;
        }
    }

    public static final void c(int n2) {
        System.out.println("LOAD GIRL gm=" + E + " lv=" + n2 + " follow " + C);
        if (C) {
            E = n2;
        }
        if (n2 == 16) {
            F = 312;
            G = 264;
            R = -1;
            E = 16;
        }
        if (n2 == 4 && !q.i[17]) {
            F = 5136;
            G = 408;
            R = 1;
            E = 4;
        }
        if (n2 == 4 && q.i[17] && !q.i[19]) {
            E = 4;
        }
        if (E != n2) {
            return;
        }
        aZ = -1;
        if (cg == null) {
            try {
                cg = new Image[8];
                n2 = 0;
                while (n2 < 8) {
                    h.cg[n2] = Image.createImage((String)("/cp/gb" + n2 + ".png"));
                    ++n2;
                }
                ch = new Image[3];
                n2 = 0;
                while (n2 < 3) {
                    h.ch[n2] = Image.createImage((String)("/cp/gh" + n2 + ".png"));
                    ++n2;
                }
                ci = new Image[9];
                n2 = 0;
                while (n2 < 9) {
                    h.ci[n2] = Image.createImage((String)("/cp/gl" + n2 + ".png"));
                    ++n2;
                }
            }
            catch (Exception exception) {}
        }
        L = 22;
        M = 32;
        N = L >> 1;
        O = M >> 1;
        P = L >> 2;
        Q = M >> 2;
        if (C) {
            F = ag - as * 10;
            G = ah;
            R = as;
            J = 4;
            return;
        }
        J = 1;
        System.out.println("a_stand");
    }

    public static final void a(Graphics graphics) {
        if (E != q.ap) {
            return;
        }
        if (R == 1) {
            graphics.drawImage(ch[bO[K][0][0]], F + bO[K][0][1], G - bO[K][0][2], 0);
            graphics.drawImage(ci[bO[K][1][0]], F + bO[K][1][1], G - bO[K][1][2], 0);
            graphics.drawImage(cg[bO[K][2][0]], F + bO[K][2][1], G - bO[K][2][2], 0);
            if (aZ != -1) {
                graphics.drawImage(cl[aZ], F - bQ[aZ], G - bR[aZ], 0);
                return;
            }
        } else {
            graphics.drawRegion(ch[bO[K][0][0]], 0, 0, ch[bO[K][0][0]].getWidth(), ch[bO[K][0][0]].getHeight(), 2, F - bO[K][0][1], G - bO[K][0][2], 24);
            graphics.drawRegion(ci[bO[K][1][0]], 0, 0, ci[bO[K][1][0]].getWidth(), ci[bO[K][1][0]].getHeight(), 2, F - bO[K][1][1], G - bO[K][1][2], 24);
            graphics.drawRegion(cg[bO[K][2][0]], 0, 0, cg[bO[K][2][0]].getWidth(), cg[bO[K][2][0]].getHeight(), 2, F - bO[K][2][1], G - bO[K][2][2], 24);
            if (aZ != -1) {
                graphics.drawRegion(cl[aZ], 0, 0, cl[aZ].getWidth(), cl[aZ].getHeight(), 2, F + bQ[aZ], G - bR[aZ], 24);
            }
        }
    }

    public static final void b(Graphics graphics) {
        if (i) {
            al = q.aq % 20 > 2 ? 25 : 26;
        }
        if (q != 0) {
            graphics.drawImage(bN, q, r, 33);
        }
        if (m) {
            return;
        }
        if (bh && q.aq % 4 < 2) {
            return;
        }
        if (c.h != 0) {
            graphics.setClip(ag - 30, ah - 30, 60, 30);
        }
        if (as == 1) {
            graphics.drawImage(A[bP[al][0][0]], ag + bP[al][0][1], ah - bP[al][0][2], 0);
            graphics.drawImage(B[bP[al][1][0]], ag + bP[al][1][1], ah - bP[al][1][2], 0);
            graphics.drawImage(cf[bP[al][2][0]], ag + bP[al][2][1], ah - bP[al][2][2], 0);
            if (ad != -1) {
                graphics.drawImage(cl[ad], ag - bQ[ad], ah + bR[ad], 0);
            }
        } else {
            graphics.drawRegion(A[bP[al][0][0]], 0, 0, A[bP[al][0][0]].getWidth(), A[bP[al][0][0]].getHeight(), 2, ag - bP[al][0][1], ah - bP[al][0][2], 24);
            graphics.drawRegion(B[bP[al][1][0]], 0, 0, B[bP[al][1][0]].getWidth(), B[bP[al][1][0]].getHeight(), 2, ag - bP[al][1][1], ah - bP[al][1][2], 24);
            graphics.drawRegion(cf[bP[al][2][0]], 0, 0, cf[bP[al][2][0]].getWidth(), cf[bP[al][2][0]].getHeight(), 2, ag - bP[al][2][1], ah - bP[al][2][2], 24);
            if (ad != -1) {
                graphics.drawRegion(cl[ad], 0, 0, cl[ad].getWidth(), cl[ad].getHeight(), 2, ag + bQ[ad], ah + bR[ad], 24);
            }
        }
        if (k) {
            graphics.setColor(9471);
            graphics.drawRect(ag - 21, ah - an - 10, 42, 5);
            graphics.setColor(3134712);
            graphics.fillRect(ag - 20, ah - an - 9, 40 - q.bo * 40 / q.bq, 4);
        }
        if (i) {
            graphics.setColor(5169655);
            graphics.drawRect(ag - 21, ah - an - (k ? 20 : 10), 42, 5);
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(ag - 20, ah - an - (k ? 19 : 9), 40 - q.bn * 40 / q.bp, 4);
        }
    }

    public static void d(int n2) {
        if ((aN += n2) > b[aM - 24]) {
            if (!b.a && aM == 26) {
                aN = 0;
                q.g(6);
                q.b("B\u1ea1n c\u1ea7n ph\u1ea3i k\u00edch ho\u1ea1t \u0111\u1ec3 c\u00f3 th\u1ec3 l\u00ean level 27");
                q.B = 7;
                return;
            }
            q.a("level-up", 2, ag, ah - an, 0, -1);
            q.bu[0] = "T\u0103ng m\u00e1u +" + c[aM - 24];
            q.bu[1] = "T\u0103ng c\u00f4ng +" + a[aM - 24];
            q.bu[2] = "T\u0103ng mana +" + d[aM - 24];
            q.bv[0] = "M\u00e1u: " + az + "/" + (az + c[aM - 24]);
            q.bv[1] = "C\u00f4ng: " + aw + "/" + (aw + a[aM - 24]);
            q.bv[2] = "Mana: " + aB + "/" + (aB + d[aM - 24]);
            q.A();
        } else {
            q.a("+" + n2, 2, ag, ah - an, 0, -2);
        }
        q.A = true;
    }

    private static void o() {
        int n2 = h.t();
        int n3 = aw + q.aq % (aw >> 1);
        n3 += (n3 >> 1) * af;
        if (n2 != -1) {
            if (af == 1) {
                if (aA >= 5) {
                    h.a(a.e[n2] - 50, a.e[n2] + 50, n2);
                    q.e(1, q.L + (aA -= 5) * q.M / aB);
                    q.A = true;
                    o.a.addElement(new j(a.e[n2], a.f[n2], 3));
                    o.b.addElement(new j(a.e[n2], a.f[n2] - 130, 10));
                    a.a(n2, (n3 += aw >> 2) * 3 / 4, -as, 3);
                    q.a("-" + n3 * 3 / 4, 0, a.e[n2], a.f[n2] - a.g[n2], 0, -2);
                    q.b(a.e[n2], a.f[n2] - (a.g[n2] >> 1), as);
                    return;
                }
                q.a("\u0110\u00e3 h\u1ebft MP");
                return;
            }
            if (af == 3) {
                if (aA >= 7) {
                    q.e(1, q.L + (aA -= 7) * q.M / aB);
                    q.A = true;
                    n3 += aw >> 2;
                    if (a.l[n2] > 1 && a.m[n2] != 1) {
                        a.m[n2] = 1;
                    }
                    o.b.addElement(new j(a.e[n2], a.f[n2], 0));
                    a.a(n2, n3, -as, 3);
                    q.a("-" + n3, 0, a.e[n2], a.f[n2] - a.g[n2], 0, -2);
                    q.b(a.e[n2], a.f[n2] - (a.g[n2] >> 1), as);
                    return;
                }
                q.a("\u0110\u00e3 h\u1ebft MP");
                return;
            }
            if (af == 2) {
                if (aA >= 6) {
                    if (a.m[n2] != 1) {
                        a.m[n2] = 1;
                    }
                    q.e(1, q.L + (aA -= 6) * q.M / aB);
                    q.A = true;
                    o.b.addElement(new j(a.e[n2], a.f[n2] - 5, 28));
                    a.a(n2, (n3 += aw >> 2) * 3 / 4, -as, 3);
                    q.b(a.e[n2], a.f[n2] - (a.g[n2] >> 1), as);
                    q.a("-" + n3 * 3 / 4, 0, a.e[n2], a.f[n2] - a.g[n2], 0, -2);
                    n3 = 0;
                    while (n3 < 8) {
                        o.b.addElement(new j(a.e[n2] - 4, a.f[n2] - 7, n3 + 30));
                        ((j)o.b.lastElement()).a = n2;
                        ++n3;
                    }
                    return;
                }
                q.a("\u0110\u00e3 h\u1ebft MP");
                return;
            }
            if (aA >= 1) {
                a.a(n2, n3, -as, 3);
                q.a("-" + n3, 0, a.e[n2], a.f[n2] - a.g[n2], 0, -2);
                q.e(1, q.L + --aA * q.M / aB);
                q.b(a.e[n2], a.f[n2] - (a.g[n2] >> 1), as);
                return;
            }
            q.a("\u0110\u00e3 h\u1ebft MP");
        }
    }

    private static void p() {
        int n2;
        int n3 = aw + q.aq % (aw >> 1);
        n3 += (n3 >> 1) * af;
        if (aQ) {
            n3 <<= 1;
        }
        if (h) {
            q.b(ag + as * 15, ah - 17, 2, -2, as, 2);
            al = 46;
            ak = 14;
            return;
        }
        if (g) {
            q.b(ag + as * 12, ah - 17, 35, -35, as, 1);
            al = 51;
            ak = 13;
            return;
        }
        int n4 = h.t();
        if (ae == 1) {
            q.a(ag + (as << 3), ah - 12, (as << 3) + ai, aj << 1, false);
            return;
        }
        if (aj > 0 && ai == 0 && n4 != -1) {
            aj = -5;
        }
        cn = false;
        if (n4 != -1) {
            if (af == 1) {
                if (aA >= 5) {
                    h.a(a.e[n4] - 50, a.e[n4] + 50, n4);
                    q.e(1, q.L + (aA -= 5) * q.M / aB);
                    q.A = true;
                    o.a.addElement(new j(a.e[n4], a.f[n4], 3));
                    o.b.addElement(new j(a.e[n4], a.f[n4] - 130, 10));
                    a.a(n4, (n3 += aw >> 2) * 3 / 4, -as, 0);
                    q.a("-" + n3 * 3 / 4, 0, a.e[n4], a.f[n4] - a.g[n4], 0, -2);
                    q.b(a.e[n4], a.f[n4] - (a.g[n4] >> 1), as);
                } else {
                    q.a("\u0110\u00e3 h\u1ebft MP");
                }
            } else if (af == 3) {
                if (aA >= 7) {
                    q.e(1, q.L + (aA -= 7) * q.M / aB);
                    q.A = true;
                    n3 += aw >> 2;
                    if (a.l[n4] > 1 && a.m[n4] != 1) {
                        a.m[n4] = 1;
                    }
                    o.b.addElement(new j(a.e[n4], a.f[n4], 0));
                    a.a(n4, n3, -as, 0);
                    q.a("-" + n3, 0, a.e[n4], a.f[n4] - a.g[n4], 0, -2);
                    q.b(a.e[n4], a.f[n4] - (a.g[n4] >> 1), as);
                } else {
                    q.a("\u0110\u00e3 h\u1ebft MP");
                }
            } else if (af == 2) {
                if (aA >= 6) {
                    if (a.m[n4] != 1) {
                        a.m[n4] = 1;
                    }
                    q.e(1, q.L + (aA -= 6) * q.M / aB);
                    q.A = true;
                    o.b.addElement(new j(a.e[n4], a.f[n4] - 5, 28));
                    a.a(n4, (n3 += aw >> 2) * 3 / 4, -as, 0);
                    q.b(a.e[n4], a.f[n4] - (a.g[n4] >> 1), as);
                    q.a("-" + n3 * 3 / 4, 0, a.e[n4], a.f[n4] - a.g[n4], 0, -2);
                    n2 = 0;
                    while (n2 < 8) {
                        o.b.addElement(new j(a.e[n4] - 4, a.f[n4] - 7, n2 + 30));
                        ((j)o.b.lastElement()).a = n4;
                        ++n2;
                    }
                } else {
                    q.a("\u0110\u00e3 h\u1ebft MP");
                }
            } else if (aA >= 1) {
                a.a(n4, n3, -as, 0);
                q.a("-" + n3, 0, a.e[n4], a.f[n4] - a.g[n4], 0, -2);
                q.e(1, q.L + --aA * q.M / aB);
                q.b(a.e[n4], a.f[n4] - (a.g[n4] >> 1), as);
            } else {
                q.a("\u0110\u00e3 h\u1ebft MP");
            }
            cn = true;
            if (aj > 0) {
                aj = -7;
            }
            q.u();
        }
        n4 = 0;
        while (n4 < q.aJ) {
            if (q.a(ah - ap - q.aM[n4]) < 20 && q.a(ag + (as << 4) - q.aL[n4]) < 24) {
                q.aN[n4] = 2;
                q.aO[n4] = as << 3;
                q.u();
                break;
            }
            ++n4;
        }
        if (i.a && q.a(ah - i.n) < i.z && q.a(ag + 11 + (as << 4) - i.m) < i.y) {
            if (af == 1) {
                if (aA >= 5) {
                    q.e(1, q.L + (aA -= 5) * q.M / aB);
                    if (i.b) {
                        i.a(0, -as, 0);
                        cn = true;
                    } else if (!i.a(n3, -as, 0)) {
                        q.b(ag + (i.m - ag >> 1), ah - 12, as);
                        q.a("-" + n3, 0, i.m, i.n - i.x, 0, -2);
                    }
                } else {
                    q.a("\u0110\u00e3 h\u1ebft MP");
                    q.v();
                }
            } else if (af == 3) {
                if (aA >= 7) {
                    q.e(1, q.L + (aA -= 7) * q.M / aB);
                    if (i.b) {
                        i.a(0, -as, 0);
                        cn = true;
                    } else if (!i.a(n3, -as, 0)) {
                        q.b(ag + (i.m - ag >> 1), ah - 12, as);
                        q.a("-" + n3, 0, i.m, i.n - i.x, 0, -2);
                    }
                } else {
                    q.a("\u0110\u00e3 h\u1ebft MP");
                    q.v();
                }
            } else if (af == 2) {
                if (aA >= 6) {
                    q.e(1, q.L + (aA -= 6) * q.M / aB);
                    if (i.b) {
                        i.a(0, -as, 0);
                        cn = true;
                    } else if (!i.a(n3, -as, 0)) {
                        q.b(ag + (i.m - ag >> 1), ah - 12, as);
                        q.a("-" + n3, 0, i.m, i.n - i.x, 0, -2);
                    }
                } else {
                    q.a("\u0110\u00e3 h\u1ebft MP");
                    q.v();
                }
            } else if (aA >= 1) {
                q.e(1, q.L + aA * q.M / aB);
                --aA;
                if (i.b) {
                    i.a(0, -as, 0);
                    cn = true;
                } else if (!i.a(n3, -as, 0)) {
                    q.b(ag + (i.m - ag >> 1), ah - 12, as);
                    q.a("-" + n3, 0, i.m, i.n - i.x, 0, -2);
                }
            } else {
                q.a("\u0110\u00e3 h\u1ebft MP");
                q.v();
            }
        }
        if (f.o && f.a == -1 && q.a(ah - f.c) < 30 && q.a(ag - f.b) < 30) {
            if (i.b) {
                f.a(0, -as, 0);
                cn = true;
            } else {
                f.a(n3, as, 0);
                q.b(ag + (f.b - ag >> 1), ah - 12, as);
                q.a("-" + n3, 0, f.b, f.c - f.n, 0, -2);
            }
            q.u();
        }
        if (d.h) {
            n2 = 0;
            while (n2 < d.i) {
                if (q.a(ah - d.o[n2]) < 20 && q.a(ag + (as << 4) - d.n[n2]) < 20) {
                    if (af == 1) {
                        if (aA >= 5) {
                            q.e(1, q.L + (aA -= 5) * q.M / aB);
                            d.a(aw, n2);
                            q.b(ag + (d.n[n2] - ag >> 1), ah - 12, as);
                            q.a("-" + n3, 0, d.n[n2], d.o[n2] - 25, 0, -2);
                        } else {
                            q.a("\u0110\u00e3 h\u1ebft MP");
                            q.v();
                        }
                    } else if (af == 3) {
                        if (aA >= 7) {
                            q.e(1, q.L + (aA -= 7) * q.M / aB);
                            d.a(aw, n2);
                            q.b(ag + (d.n[n2] - ag >> 1), ah - 12, as);
                            q.a("-" + n3, 0, d.n[n2], d.o[n2] - 25, 0, -2);
                        } else {
                            q.a("\u0110\u00e3 h\u1ebft MP");
                            q.v();
                        }
                    } else if (af == 2) {
                        if (aA >= 6) {
                            q.e(1, q.L + (aA -= 6) * q.M / aB);
                            d.a(aw, n2);
                            q.b(ag + (d.n[n2] - ag >> 1), ah - 12, as);
                            q.a("-" + n3, 0, d.n[n2], d.o[n2] - 25, 0, -2);
                        } else {
                            q.a("\u0110\u00e3 h\u1ebft MP");
                            q.v();
                        }
                    } else if (aA >= 1) {
                        q.e(1, q.L + --aA * q.M / aB);
                        d.a(aw, n2);
                        q.b(ag + (d.n[n2] - ag >> 1), ah - 12, as);
                        q.a("-" + n3, 0, d.n[n2], d.o[n2] - 25, 0, -2);
                    } else {
                        q.a("\u0110\u00e3 h\u1ebft MP");
                        q.v();
                    }
                }
                ++n2;
            }
        }
        if (q.ap == 17 && !i.k && q.a(ah - bp) < 20 && q.a(ag + (as << 4) - bo) < 20) {
            if (af == 1) {
                if (aA >= 5) {
                    q.e(1, q.L + (aA -= 5) * q.M / aB);
                    if (!h.b(n3, as)) {
                        q.b(bo, bp - 12, -as);
                        q.a("-" + n3, 0, bo, bp - an, 0, -2);
                    }
                } else {
                    q.a("\u0110\u00e3 h\u1ebft MP");
                    q.v();
                }
            } else if (af == 3) {
                if (aA >= 7) {
                    q.e(1, q.L + (aA -= 7) * q.M / aB);
                    if (!h.b(n3, as)) {
                        q.b(bo, bp - 12, -as);
                        q.a("-" + n3, 0, bo, bp - an, 0, -2);
                    }
                } else {
                    q.a("\u0110\u00e3 h\u1ebft MP");
                    q.v();
                }
            } else if (af == 2) {
                if (aA >= 6) {
                    q.e(1, q.L + (aA -= 6) * q.M / aB);
                    if (!h.b(n3, as)) {
                        q.b(bo, bp - 12, -as);
                        q.a("-" + n3, 0, bo, bp - an, 0, -2);
                    }
                } else {
                    q.a("\u0110\u00e3 h\u1ebft MP");
                    q.v();
                }
            } else if (aA >= 1) {
                q.e(1, q.L + --aA * q.M / aB);
                if (!h.b(n3, as)) {
                    q.b(bo, bp - 12, -as);
                    q.a("-" + n3, 0, bo, bp - an, 0, -2);
                }
            } else {
                q.a("\u0110\u00e3 h\u1ebft MP");
                q.v();
            }
        }
        if (q.ap == 14 && d.g && !i.i) {
            n2 = 0;
            while (n2 < 10) {
                if (n.j[n2] != 0 && q.a(ah - n.f[n2]) < 25 && q.a(ag + (as << 4) - n.e[n2]) < 20) {
                    n.k[n2] = 1;
                    q.b(n.e[n2], n.f[n2] - 2, -as);
                    q.a("-" + n3, 0, n.e[n2], n.f[n2], 0, -2);
                }
                n2 = (byte)(n2 + 1);
            }
            if (q.a(ah - i.I) < 25 && q.a(ag + (as << 4) - i.H) < 20) {
                if (af == 1) {
                    if (aA >= 5) {
                        q.e(1, q.L + (aA -= 5) * q.M / aB);
                        if (i.b) {
                            cn = true;
                        } else {
                            i.a(n3, as);
                        }
                    } else {
                        q.a("\u0110\u00e3 h\u1ebft MP");
                        q.v();
                    }
                } else if (af == 3) {
                    if (aA >= 7) {
                        q.e(1, q.L + (aA -= 7) * q.M / aB);
                        if (i.b) {
                            cn = true;
                        } else {
                            i.a(n3, as);
                        }
                    } else {
                        q.a("\u0110\u00e3 h\u1ebft MP");
                        q.v();
                    }
                } else if (af == 2) {
                    if (aA >= 6) {
                        q.e(1, q.L + (aA -= 6) * q.M / aB);
                        if (i.b) {
                            cn = true;
                        } else {
                            i.a(n3, as);
                        }
                    } else {
                        q.a("\u0110\u00e3 h\u1ebft MP");
                        q.v();
                    }
                } else if (aA >= 1) {
                    q.e(1, q.L + --aA * q.M / aB);
                    if (i.b) {
                        cn = true;
                    } else {
                        i.a(n3, as);
                    }
                } else {
                    q.a("\u0110\u00e3 h\u1ebft MP");
                    q.v();
                }
            }
        }
        if (q.ap == 8 && ah == q.bx && q.a(ag + (as << 4) - q.bw) < 10) {
            if ((q.by = q.by == -1 ? 1 : -1) == -1) {
                q.bz = 1;
                return;
            }
            q.bz = -1;
            if (!q.i[15]) {
                q.f(15);
            }
        }
    }

    private static void q() {
        int n2 = 0;
        while (n2 < q.aJ) {
            if (q.aN[n2] != 4 && q.a(ag - q.aL[n2]) < 20 && q.aM[n2] > ah && q.aM[n2] - 24 <= ah) {
                ai = 0;
                aj = 0;
                ah = q.aM[n2] - 24;
                ak = 1;
                ad = -1;
                q.aK = n2;
                if (q.aN[n2] != 11) break;
                q.d(q.aL[n2], q.aM[n2] - 10);
                int n3 = n2;
                q.aM[n3] = q.aM[n3] + 4;
                int n4 = n2;
                q.aP[n4] = q.aP[n4] + 4;
                return;
            }
            ++n2;
        }
    }

    private static void r() {
        int n2 = 0;
        while (n2 < q.aJ) {
            if (q.a(ag - q.aL[n2]) < 20 && q.a(q.aM[n2] - ah) < 4) {
                ai = 0;
                ag = ag < q.aL[n2] ? q.aL[n2] - 20 : q.aL[n2] + 20;
                if (q.aN[n2] != 4) {
                    q.aN[n2] = 2;
                    q.aO[n2] = -as << 1;
                    return;
                }
            }
            ++n2;
        }
    }

    private static void s() {
        int n2 = 0;
        while (n2 < q.aJ) {
            if (q.a(ag - q.aL[n2]) < 20 && q.aM[n2] == ah) {
                ai = 0;
                ag = ag < q.aL[n2] ? q.aL[n2] - 20 : q.aL[n2] + 20;
                if (q.aN[n2] != 4) {
                    q.aN[n2] = 2;
                    q.aO[n2] = as << 2;
                    return;
                }
            }
            ++n2;
        }
    }

    public static void f() {
        cm = null;
    }

    private static int t() {
        int n2 = ag - 40;
        int n3 = ag + 40;
        int n4 = ah - an - 15;
        int n5 = ah + 5;
        int n6 = 0;
        while (n6 < a.c) {
            if (a.i[n6] != 5 && a.i[n6] != 1 && a.e[n6] >= n2 && a.e[n6] <= n3 && a.f[n6] >= n4 && a.f[n6] <= n5) {
                return n6;
            }
            ++n6;
        }
        return -1;
    }

    private static int a(int n2, int n3, int n4) {
        int n5 = ah - an - 30;
        int n6 = 0;
        int n7 = ah + 5;
        int n8 = 0;
        while (n8 < a.c) {
            if (a.i[n8] != 5 && a.i[n8] != 1 && a.e[n8] >= n2 && a.e[n8] <= n3 && a.f[n8] >= n5 && a.f[n8] <= n7 && n8 != n4) {
                if (n6 > 2) {
                    return -1;
                }
                ++n6;
                o.b.addElement(new j(a.e[n8], a.f[n8] - 130, 10));
                a.a(n8, aw / 6, -as, 0);
                q.a("-" + aw / 6, 0, a.e[n8], a.f[n8] - a.g[n8], 0, -2);
                q.b(a.e[n8], a.f[n8] - (a.g[n8] >> 1), as);
            }
            ++n8;
        }
        return -1;
    }

    public static final void a(int n2, int n3) {
        if ((ay -= n2) < 0) {
            ay = 0;
        }
        if (ay > 0) {
            q.e(0, q.L + ay * q.M / az);
        }
        ad = -1;
        q.A = true;
        q.aK = -1;
        if (f.o && f.a != -1) {
            f.a = 0;
        }
        if (ay <= 0) {
            if (!j) {
                ay = 0;
                aj = -7;
                ai = n3 << 2;
                q.aj = -1;
                ak = 6;
            } else {
                q = ag;
                r = ah;
                if (!k.a(q, r, 2)) {
                    r = k.d(r + 24);
                }
                ay = 1000;
                ah = q.O * 24;
                if (ah < 30) {
                    ah = 30;
                }
                ak = 4;
                q.b();
                q.c();
            }
        } else if (q.aj == -1 && ak != 23) {
            ai = n3 << 2;
            aj = -3;
            at = 0;
            au = 0;
            ak = 7;
        }
        q.b(ag, ah - 12, -as);
        q.a("-" + n2, 0, ag, ah - an, 0, -2);
    }

    public static final boolean b(int n2, int n3) {
        if (bs == 6) {
            return true;
        }
        if (bi > bj / 5) {
            bi -= n2;
            bk = 10;
        }
        bq = n3 << 2;
        br = -3;
        bB = 0;
        bC = 0;
        bs = 7;
        if (!i.j && bi <= bj / 5) {
            bk = 0;
            i.C = 28;
            q.b();
            q.c();
            i.l = q.b(1, 10);
            bs = 1;
            i.j = true;
            o.a.addElement(new j(i.m, i.n - 67, 20));
        }
        return false;
    }

    public static final void c(int n2, int n3) {
        aZ = -1;
        H = n3 << 2;
        I = -2;
        S = 0;
        T = 0;
        J = 7;
        q.b(F, G - 12, -R);
        q.a("-" + n2, 0, F, G - M, 0, -2);
    }

    public static void g() {
        B = null;
        ck = null;
        cf = null;
        A = null;
        cj = null;
        cl = null;
        bN = null;
        System.gc();
    }

    private static void g(int n2) {
        int n3 = aw;
        if (!a.a(cc[n2], n3, -z[n2])) {
            q.b(a.e[cc[n2]], a.f[cc[n2]] - (a.g[cc[n2]] >> 1), a.k[cc[n2]]);
            q.a("-" + n3, 0, a.e[cc[n2]], a.f[cc[n2]] - a.g[cc[n2]], 0, -2);
        }
    }

    private static void h(int n2) {
        int n3 = 0;
        while (n3 < a.c) {
            if (a.i[n3] != 5 && a.f[n3] == y[n2] && q.a(x[n2] + (z[n2] << 3) - a.e[n3]) < 20) {
                h.z[n2] = a.e[n3] < x[n2] ? -1 : 1;
                h.bV[n2] = 5;
                h.bX[n2] = 0;
                h.bW[n2] = 0;
                h.cc[n2] = n3;
            }
            ++n3;
        }
    }

    public static void c(Graphics graphics) {
        int n2 = 0;
        while (n2 < s) {
            if (x[n2] + ao >= q.ax && x[n2] - ao <= q.ax + q.S && y[n2] >= q.ay && y[n2] - an <= q.ay + q.T) {
                if (z[n2] == 1) {
                    graphics.drawImage(cm[bZ[n2]][bS[bY[n2]][0][0]], x[n2] + bS[bY[n2]][0][1], y[n2] - bS[bY[n2]][0][2], 0);
                    graphics.drawImage(ck[bS[bY[n2]][1][0]], x[n2] + bS[bY[n2]][1][1], y[n2] - bS[bY[n2]][1][2], 0);
                    graphics.drawImage(cf[bS[bY[n2]][2][0]], x[n2] + bS[bY[n2]][2][1], y[n2] - bS[bY[n2]][2][2], 0);
                    if (ca[n2] != -1) {
                        graphics.drawImage(cl[ca[n2]], x[n2] - bQ[ca[n2]], y[n2] - bR[ca[n2]], 0);
                    }
                } else {
                    graphics.drawRegion(cm[bZ[n2]][bS[bY[n2]][0][0]], 0, 0, cm[bZ[n2]][bS[bY[n2]][0][0]].getWidth(), cm[bZ[n2]][bS[bY[n2]][0][0]].getHeight(), 2, x[n2] - bS[bY[n2]][0][1], y[n2] - bS[bY[n2]][0][2], 24);
                    graphics.drawRegion(ck[bS[bY[n2]][1][0]], 0, 0, ck[bS[bY[n2]][1][0]].getWidth(), ck[bS[bY[n2]][1][0]].getHeight(), 2, x[n2] - bS[bY[n2]][1][1], y[n2] - bS[bY[n2]][1][2], 24);
                    graphics.drawRegion(cf[bS[bY[n2]][2][0]], 0, 0, cf[bS[bY[n2]][2][0]].getWidth(), cf[bS[bY[n2]][2][0]].getHeight(), 2, x[n2] - bS[bY[n2]][2][1], y[n2] - bS[bY[n2]][2][2], 24);
                    if (ca[n2] != -1) {
                        graphics.drawRegion(cl[ca[n2]], 0, 0, cl[ca[n2]].getWidth(), cl[ca[n2]].getHeight(), 2, x[n2] + bQ[ca[n2]], y[n2] - bR[ca[n2]], 24);
                    }
                }
            }
            ++n2;
        }
    }

    public static final void h() {
        block80: {
            if (bn != q.ap) {
                return;
            }
            try {
                switch (bs) {
                    case 1: {
                        if (bL > 0) {
                            h.u();
                        }
                        if (++bB > 30) {
                            bB = 0;
                        }
                        bt = bB % 15 < 5 ? 0 : 1;
                        if (q.aq % 5 == 0) {
                            h.u();
                        }
                        return;
                    }
                    case 2: {
                        if (++bB >= 10) {
                            bB = 0;
                        }
                        bt = (bB >> 1) + 2;
                        if (q.ap == 7) {
                            bo += bq;
                            break;
                        }
                        if (bA == 1) {
                            if (k.a(bo + bw, bp - bx, 4)) {
                                bs = 3;
                                br = -10;
                                break;
                            }
                        } else if (k.a(bo - bw - 1, bp - bx, 8)) {
                            bs = 3;
                            br = -10;
                            break;
                        }
                        bo = k.a(bo, bp, 65536) ? (bo += bA) : (bo += bq);
                        if (bo < 0) {
                            bo = 0;
                        }
                        if (bo >= k.c) {
                            bo = k.c - 1;
                        }
                        if (q.aK != -1) {
                            if (q.a(bo - q.aL[q.aK]) >= 20) {
                                q.aK = -1;
                                if (k.a(bo, bp, 2)) {
                                    bs = 1;
                                } else {
                                    bs = 4;
                                    br = 2;
                                    bq = 3 * bA;
                                    bC = 0;
                                }
                            }
                        } else if ((k.c(bo + bA * bu, bp) & 2) != 2) {
                            if (bL != 0) {
                                bs = 3;
                                br = -10;
                                break;
                            }
                            bs = 1;
                            bt = 0;
                            bq = 0;
                            bB = 0;
                            bC = 0;
                        }
                        q.a(bA, bo - (bA << 3), bp);
                        if (bL != 0 && q.a(bL - bo) < 20) {
                            bL = 0;
                        }
                        h.u();
                        return;
                    }
                    case 3: {
                        if (bq == 0) {
                            bq = bA << 2;
                        }
                        if (bA == 1) {
                            if ((k.c(bo + bw, bp - bx) & 4) == 4) {
                                bq = 0;
                            }
                        } else if ((k.c(bo - bw, bp - bx) & 8) == 8) {
                            bq = 0;
                        }
                        if (ah < 0) {
                            ah = 0;
                            aj = -1;
                        }
                        if ((bo += bq) < 0) {
                            bo = 0;
                        }
                        if (bo >= k.c) {
                            bo = k.c - 1;
                        }
                        bp += br;
                        bt = 7;
                        if (bq > 1) {
                            --bq;
                        }
                        if (++br == 0) {
                            bs = 4;
                            bB = 0;
                            bC = 0;
                            br = 1;
                        }
                        if (bD < 0) {
                            ++bD;
                        }
                        if (bp > bv && k.a(bo, bp - bv, 16384)) {
                            bp = k.d(bp);
                            bs = 4;
                            bB = 0;
                            bC = 0;
                            br = 1;
                        }
                        return;
                    }
                    case 4: {
                        if (bA == 1) {
                            if ((k.c(bo + bu, bp) & 4) == 4) {
                                bq = 0;
                            }
                        } else if ((k.c(bo - bu, bp) & 8) == 8) {
                            bq = 0;
                        }
                        cr = -1;
                        if ((bo += bq) < 0) {
                            bo = 0;
                        }
                        if (bo >= k.c) {
                            bo = k.c - 1;
                        }
                        bp += br;
                        if (br < 18) {
                            ++br;
                        }
                        if (br > 5) {
                            bq = 0;
                        }
                        if (bq > 1) {
                            --bq;
                        }
                        bt = 12;
                        if (bp >= k.d) {
                            bp = k.d - 1;
                            bs = 3;
                            br = -20;
                        }
                        if (br > 4 && (k.c(bo, bp) & 2) == 2) {
                            bs = 1;
                            br = 0;
                            bq = 0;
                            bC = 0;
                            bB = 0;
                            bp = k.e(bp);
                            q.a(-1, bo - -8, bp);
                            q.a(1, bo - 8, bp);
                        }
                        if (k.a(bo, bp, 64) && k.a(bo, bp + 24, 4096) && bp % k.f > 8) {
                            q.d(bo, k.d(bp) + 8);
                            bs = 3;
                            bA = ag > bo ? 1 : -1;
                            bq = bA << 2;
                            br = -11;
                        }
                        if (br > 6) {
                            bt = 6;
                        }
                        if (bD < 0) {
                            ++bD;
                        }
                        return;
                    }
                    case 5: {
                        switch (++bB) {
                            case 1: 
                            case 2: 
                            case 3: {
                                cr = -1;
                                bt = 13;
                                break block80;
                            }
                            case 4: 
                            case 5: {
                                cr = 0;
                                bt = 14;
                                break block80;
                            }
                            case 6: {
                                cr = 1;
                                bt = 15;
                                o.a.addElement(new j(ag, ah, 0));
                                h.a(10, bA);
                                q.b(ag, ah - (an >> 1), bA);
                                q.a("-" + aw, 0, ag, ah - an, 0, -2);
                                break block80;
                            }
                            case 7: {
                                cr = 2;
                                bt = 15;
                                break block80;
                            }
                            case 8: {
                                cr = -1;
                                bt = 16;
                                break block80;
                            }
                            case 9: {
                                cr = -1;
                                bt = 13;
                                bs = 1;
                                bB = 0;
                                bC = 0;
                            }
                            default: {
                                return;
                            }
                        }
                    }
                    case 7: {
                        cr = -1;
                        if ((k.c(bo + bw, bp - bx) & 4) == 4) {
                            bq = -bq;
                        }
                        if ((k.c(bo - bw, bp - bx) & 8) == 8) {
                            bq = -bq;
                        }
                        bo += bq;
                        bp += br;
                        bt = 7;
                        if (bq > 1) {
                            --bq;
                        }
                        if (bq < -1) {
                            ++bq;
                        }
                        if (++br > 0) {
                            br = 0;
                        }
                        if (++bB >= 7) {
                            bs = 4;
                            bD = -20;
                            bC = 0;
                            bB = 0;
                            br = 1;
                        }
                        if (q.aq % 5 == 0) {
                            h.u();
                        }
                        return;
                    }
                    case 8: {
                        if (q.aq % 3 == 0) {
                            if (bt == 49) {
                                bt = 50;
                                --bp;
                            } else {
                                bt = 49;
                                ++bp;
                            }
                        }
                        if (i.S < i.R.length - 11 && q.aq % 3 == 0) {
                            bp -= 2;
                        }
                        return;
                    }
                    case 6: {
                        cr = -1;
                        if (!k.a(bo, bp, 2)) {
                            if (++br == -7) {
                                o.b.addElement(new j(bo, bp, 25));
                            }
                            if (br == -3) {
                                o.b.addElement(new j(bo, bp, 25));
                            }
                            bp += br;
                            bt = 7;
                            break;
                        }
                        bp = k.d(bp);
                        bs = 1;
                        bA = bo > ag ? -1 : 1;
                        as = bo > ag ? 1 : -1;
                        ak = 1;
                        q.f(31);
                        h.d(400);
                    }
                    default: {
                        return;
                    }
                }
            }
            catch (Exception exception) {
                bo = ag;
                bp = ah;
                bq = 0;
                br = 0;
                bs = 4;
            }
        }
    }

    private static void u() {
        if (i.b) {
            return;
        }
        if (q.ap == 17 && !i.b) {
            if (!q.i[30]) {
                return;
            }
            if (i.k || q.az) {
                return;
            }
            if (ag < 1200 || ag > 1632) {
                bs = 1;
                return;
            }
            if (ak != 6 && q.a(ah - bp) < 10 && q.a(ag - bo) < 40 && k.a(bo, bp, 2)) {
                bA = ag > bo ? 1 : -1;
                bs = 5;
                cs = 0;
                bB = 0;
                bC = 0;
                return;
            }
            if (bo < 1200) {
                bA = 1;
                bq = 5;
                bs = 2;
                return;
            }
            if (bo > 1632) {
                bA = -1;
                bq = -5;
                bs = 2;
                return;
            }
            if (!k.a(bo + bA * am, bp, 2)) {
                br = -2;
                bs = 4;
                bq = bA * 7;
                return;
            }
            if (ah < bp && ak == 1) {
                if (k.a(bo + bA * 24, bp - 25, 2)) {
                    bA = bo > ag ? -1 : 1;
                    bq = 3 * bA;
                    br = -10;
                    bs = 3;
                    return;
                }
                bq = 5 * bA;
                bs = 2;
                return;
            }
            if (q.a(ah - bp) < 15) {
                if (ag > bo) {
                    bA = 1;
                    bq = 5;
                    bs = 2;
                    return;
                }
                if (ag < bo) {
                    bA = -1;
                    bq = -5;
                    bs = 2;
                    return;
                }
            } else if (bp < ah) {
                bq = bA * 5;
                bs = 2;
                return;
            }
            if (ag < 1152) {
                bs = 1;
                return;
            }
            return;
        }
        if (bl) {
            if (q.a(ag - bo) < 30) {
                int n2 = bA = ag >= bo ? 1 : -1;
            }
            if (bo < q.N * 24 || bo > q.P * 24) {
                int n3;
                bA = ag + (as << 5) > bo ? 1 : -1;
                int n4 = ag > bo ? q.ax - 24 : q.ax + q.S + 24;
                if (!k.a(n4, n3 = ah - 20, 8192)) {
                    bo = n4;
                    bp = n3;
                    bs = 4;
                    bq = 4;
                    br = 3;
                    return;
                }
                if (k.a(bo + bA * bu, bp, 2)) {
                    bA = ag > bo ? 1 : -1;
                    bs = 2;
                    bq = bA * 6;
                    return;
                }
            } else {
                if (bp < q.O * 24 || bp > q.Q * 24) {
                    bA = ag + (as << 5) > bo ? 1 : -1;
                    int n5 = ag;
                    int n6 = ah > bp ? q.ay - 20 : q.ay + q.T;
                    bo = n5;
                    bp = n6;
                    bs = 3;
                    bq = bA << 1;
                    br = -4;
                    return;
                }
                if (ah <= bp - 24 && !k.a(bo, bp, 16384)) {
                    if (k.a(bo - 24, bp - 1, 2)) {
                        bq = -5;
                        bA = -1;
                    }
                    if (k.a(bo + 24, bp - 1, 2)) {
                        bq = 5;
                        bA = 1;
                    }
                    bs = 3;
                    br = -8;
                    return;
                }
                if (ah <= bp - 24 && !k.a(bo, bp - 1, 16384) && !k.a(bo, bp, 16384)) {
                    if (k.a(bo - 24, bp - 25, 2)) {
                        bq = -5;
                        bA = -1;
                    }
                    if (k.a(bo + 24, bp - 25, 2)) {
                        bq = 5;
                        bA = 1;
                    }
                    bs = 3;
                    br = -10;
                    return;
                }
                if (ah <= bp - 49 && !k.a(bo, bp - 1, 16384) && !k.a(bo, bp, 16384)) {
                    if (k.a(bo - 24, bp - 49, 2)) {
                        bq = -10;
                        bA = -1;
                    }
                    if (k.a(bo + 24, bp - 49, 2)) {
                        bq = 10;
                        bA = 1;
                    }
                    bs = 3;
                    br = -15;
                    return;
                }
                if (q.a(ag + (as << 5) - bo) > (ak == 5 ? 10 : 40)) {
                    if (ah < bp && q.aq % 10 <= 4) {
                        int n7 = 2;
                        while (n7 >= 1) {
                            int n8 = 1;
                            while (n8 <= 2) {
                                if (k.a(bo + bA * 24 * n8, bp - n7 * 24, 2)) {
                                    bs = 3;
                                    bq = bA * (6 + (n8 - 1) * 3);
                                    br = -5 - n7 * 3;
                                    return;
                                }
                                ++n8;
                            }
                            --n7;
                        }
                        if (k.a(bo + bA * 48, bp - 24, 2)) {
                            bs = 3;
                            bq = bA * 5;
                            br = -8;
                            return;
                        }
                        if (!k.a(bo + bA * 48, bp - 2, 64) && (bp + 24 >= k.d || k.a(bo + bA * 48, bp + 24, 2))) {
                            bs = 3;
                            bq = bA * 7;
                            br = -4;
                            return;
                        }
                    } else {
                        if (ah > bp && q.aq % 10 <= 4) {
                            if (k.a(bo + bA * bu, bp, 2)) {
                                bA = ag > bo ? 1 : -1;
                                bs = 2;
                                bq = bA * q.aC;
                                return;
                            }
                            if (k.a(bo + bA * 48, bp + 24, 2)) {
                                bs = 3;
                                bq = bA * 7;
                                br = -5;
                                return;
                            }
                            if (k.a(bo + bA * 24, bp - 24, 2)) {
                                bs = 3;
                                bq = bA * 5;
                                br = -7;
                            } else if (k.a(bo + bA * 48, bp - 24, 2)) {
                                bs = 3;
                                bq = bA * 6;
                                br = -8;
                            } else if (k.a(bo + bA * 24, bp - 48, 2)) {
                                bs = 3;
                                bq = bA * 5;
                                br = -11;
                            }
                            bs = 3;
                            bA = bo < ag ? 1 : -1;
                            bq = bA << 2;
                            br = -4;
                            return;
                        }
                        if (k.a(bo + bA * bu, bp, 2)) {
                            bA = ag + (as << 5) > bo ? 1 : -1;
                            bs = 2;
                            bq = bA * 5;
                            return;
                        }
                    }
                } else {
                    bs = 1;
                }
            }
        }
    }

    public static final void d(Graphics graphics) {
        if (bn != q.ap) {
            return;
        }
        if (bm > 0 && bm % 5 > 2) {
            return;
        }
        if (bA == 1) {
            graphics.drawImage(cm[0][bS[bt][0][0]], bo + bS[bt][0][1], bp - bS[bt][0][2], 0);
            graphics.drawImage(ck[bS[bt][1][0]], bo + bS[bt][1][1], bp - bS[bt][1][2], 0);
            graphics.drawImage(cf[bS[bt][2][0]], bo + bS[bt][2][1], bp - bS[bt][2][2], 0);
            if (cr != -1) {
                graphics.drawImage(cl[cr], bo - bQ[cr], bp + bR[cr], 0);
            }
        } else {
            graphics.drawRegion(cm[0][bS[bt][0][0]], 0, 0, cm[0][bS[bt][0][0]].getWidth(), cm[0][bS[bt][0][0]].getHeight(), 2, bo - bS[bt][0][1], bp - bS[bt][0][2], 24);
            graphics.drawRegion(ck[bS[bt][1][0]], 0, 0, ck[bS[bt][1][0]].getWidth(), ck[bS[bt][1][0]].getHeight(), 2, bo - bS[bt][1][1], bp - bS[bt][1][2], 24);
            graphics.drawRegion(cf[bS[bt][2][0]], 0, 0, cf[bS[bt][2][0]].getWidth(), cf[bS[bt][2][0]].getHeight(), 2, bo - bS[bt][2][1], bp - bS[bt][2][2], 24);
            if (cr != -1) {
                graphics.drawRegion(cl[cr], 0, 0, cl[cr].getWidth(), cl[cr].getHeight(), 2, bo + bQ[cr], bp + bR[cr], 24);
            }
        }
        if (bk != 0 && !i.j) {
            graphics.setColor(0x330011);
            graphics.drawRect(bo - (am >> 1) - 1, bp - an - 7, am + 2, 4);
            int n2 = bi * am / bj;
            graphics.setColor(0xEE0000);
            graphics.fillRect(bo - (am >> 1), bp - an - 6, n2, 3);
            graphics.setColor(0x440022);
            graphics.fillRect(bo - (am >> 1) + n2, bp - an - 6, am - n2 + 1, 3);
        }
        if (i.j && i.B <= i.C && i.B % 5 < 4) {
            Midlet.c(graphics, "" + i.l, bo, bp - 70, 2);
        }
    }

    public static final void e(int n2) {
        if (bl) {
            bn = n2;
        }
        if (n2 == 7 && !q.i[7]) {
            bn = 7;
            bo = 2952;
            bp = 288;
            bA = -1;
        }
        if (n2 == 8 && p.h == 4 || p.h == 12) {
            bn = 8;
            bo = 768;
            bp = 840;
            bA = -1;
        }
        if (n2 == 17 && !i.k && q.i[29]) {
            bj = 8000;
            bi = 8000;
            bE = 500;
            bn = 17;
            bo = 1344;
            bp = 288;
            bA = -1;
        }
        if (bn != n2) {
            return;
        }
        h.f(1);
        cr = -1;
        bu = 22;
        bv = 32;
        bw = bu >> 1;
        bx = bv >> 1;
        by = bu >> 2;
        bz = bv >> 2;
        if (bl) {
            bo = ag - as * 10;
            bp = ah;
            bA = as;
            bs = 4;
            return;
        }
        bs = 1;
    }
}


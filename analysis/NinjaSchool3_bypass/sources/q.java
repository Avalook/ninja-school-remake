/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.io.ConnectionNotFoundException
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.lcdui.TextBox
 *  javax.microedition.midlet.MIDlet
 *  javax.microedition.rms.RecordStore
 *  javax.microedition.rms.RecordStoreException
 *  javax.microedition.rms.RecordStoreNotFoundException
 */
import java.io.IOException;
import java.util.Random;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public final class q
extends Canvas
implements Runnable,
CommandListener {
    private static int bG = -1;
    private static int bH = -1;
    private static boolean bI = false;
    public static boolean a = false;
    private static int bJ = 40;
    static boolean b = true;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    public static int g;
    public static int h;
    public static final boolean[] i;
    public static final int[][] j;
    public static final int[][] k;
    private static int[][] bK;
    private static int[][] bL;
    private static int[][] bM;
    private static int[][] bN;
    public static boolean l;
    private static int[][] bO;
    private static int[][] bP;
    private static int[][] bQ;
    private static int[][] bR;
    private static int[] bS;
    private static int[] bT;
    private static int[] bU;
    public static int[] m;
    private static String[] bV;
    private static int bW;
    private static int bX;
    private static int[] bY;
    private static int[] bZ;
    static int n;
    static q o;
    static boolean p;
    static boolean q;
    static int r;
    static int s;
    private static boolean ca;
    static boolean[] t;
    static boolean[] u;
    static int v;
    static int w;
    static int x;
    static int y;
    private static int cb;
    public static boolean z;
    public static boolean A;
    private static int cc;
    private static int cd;
    private static int ce;
    private static int cf;
    private static int cg;
    private static int ch;
    private static int ci;
    private static int cj;
    public static int B;
    private static boolean ck;
    public static int[] C;
    public static int[] D;
    public static int E;
    public static int F;
    public static int G;
    public static int H;
    public static int I;
    public static String J;
    public static String K;
    private static int cl;
    private static int cm;
    private static int cn;
    private static int co;
    private static int cp;
    private static int cq;
    private static int cr;
    public static int L;
    private static int cs;
    public static int M;
    private static int ct;
    private static int cu;
    private static int cv;
    private static int cw;
    private static int cx;
    private static int cy;
    private static String cz;
    public static int N;
    public static int O;
    private static int cA;
    private static int cB;
    public static int P;
    public static int Q;
    public static int R;
    public static int S;
    public static int T;
    public static int U;
    public static int V;
    public static int W;
    public static int X;
    private static int cC;
    private static int[] cD;
    private static int[] cE;
    private static int[] cF;
    private static int[] cG;
    private static int[] cH;
    private static int[] cI;
    private static int[] cJ;
    private static int[] cK;
    private static int[] cL;
    private static int[] cM;
    private static int[] cN;
    private static int[] cO;
    private static int[] cP;
    private static int[] cQ;
    private static int[] cR;
    private static int[] cS;
    private static int[] cT;
    private static int[] cU;
    private static boolean cV;
    private static int cW;
    private static String cX;
    public static int Y;
    private static int[] cY;
    public static int[] Z;
    public static int[] aa;
    public static int[] ab;
    private static int[] cZ;
    private static int[] da;
    private static int[] db;
    private static int[] dc;
    private static int dd;
    private static int[] de;
    private static int[] df;
    public static int[] ac;
    private static int[] dg;
    public static int[] ad;
    private static int[] dh;
    private static boolean[] di;
    public static int ae;
    public static int af;
    public static int[] ag;
    public static int[] ah;
    public static int[] ai;
    private static int[] dj;
    private static int[] dk;
    private static int[] dl;
    private static int[] dm;
    private static int[] dn;
    private static String[] do;
    private static int[] dp;
    private static int[] dq;
    private static int[] dr;
    private static int[] ds;
    private static int[] dt;
    private static int[] du;
    public static int aj;
    private static int dv;
    private static int dw;
    private static int dx;
    private static Image[][] dy;
    private static Image[] dz;
    public static Image[] ak;
    public static int[] al;
    public static Image[] am;
    private static Image[] dA;
    private static Image dB;
    public static Image an;
    private static Image[] dC;
    private static Image[] dD;
    public static Image ao;
    private static Image[][] dE;
    private static Image[] dF;
    private static Image[] dG;
    private static Image[] dH;
    private static Image[] dI;
    private static Image dJ;
    private static Image[] dK;
    private static Image[] dL;
    public static int ap;
    public static int aq;
    public static boolean ar;
    public static int[] as;
    public static int[] at;
    public static int[] au;
    public static int av;
    public static int aw;
    private static int dM;
    private static int dN;
    private static int dO;
    private static int dP;
    public static int ax;
    public static int ay;
    private static int dQ;
    private static int dR;
    public static boolean az;
    public static int aA;
    public static int aB;
    public static int aC;
    public static int aD;
    public static int aE;
    public static int aF;
    private static boolean dS;
    private static String[] dT;
    private static int dU;
    private static boolean dV;
    private static int dW;
    private static long dX;
    public static int aG;
    public static boolean aH;
    public static int aI;
    public static int aJ;
    public static int aK;
    public static int[] aL;
    public static int[] aM;
    public static int[] aN;
    public static int[] aO;
    public static int[] aP;
    private static int[] dY;
    private static int[] dZ;
    public static boolean aQ;
    private static int ea;
    private static int eb;
    private static int ec;
    private static int ed;
    private static String[][] ee;
    private static int ef;
    private static int eg;
    private static int eh;
    private static int ei;
    private static int ej;
    private static int ek;
    public static int[] aR;
    private static Image[] el;
    public static int[] aS;
    public static int[] aT;
    public static String[] aU;
    public static int aV;
    private static int em;
    public static boolean aW;
    private static Image[] en;
    private static int eo;
    private static int ep;
    private static int eq;
    private static int er;
    private static int es;
    private static int et;
    private static int eu;
    private static int ev;
    private static Image ew;
    private static int ex;
    private static int[] ey;
    private static int[] ez;
    private static int[] eA;
    private static int[] eB;
    private static int[] eC;
    public static boolean aX;
    private static Image eD;
    private static int[] eE;
    private static int[] eF;
    private static int[] eG;
    private static int[] eH;
    private static int eI;
    private static int eJ;
    static int[] aY;
    static int[] aZ;
    private static int[] eK;
    static int[] ba;
    private static int eL;
    private static int eM;
    static int[] bb;
    private static Image[] eN;
    private static int eO;
    private static int eP;
    private static int eQ;
    private static int eR;
    private static int eS;
    private static int eT;
    private static Image eU;
    private static int eV;
    private static int eW;
    private static Random eX;
    public static int bc;
    private static int eY;
    private static int[] eZ;
    public static byte[] bd;
    private static boolean fa;
    static int be;
    private static int fb;
    static int bf;
    private static Image fc;
    private static Image fd;
    private static int fe;
    private static int[] ff;
    static int bg;
    private static int fg;
    static int bh;
    private static int fh;
    static int bi;
    static int bj;
    private static int[] fi;
    static String[][] bk;
    private static String[][] fj;
    private static int[][] fk;
    private static String[][] fl;
    private static String[][] fm;
    private static String[][] fn;
    private static int[][] fo;
    private static Image[] fp;
    private static String[][] fq;
    private static String[][] fr;
    private static int fs;
    private static int ft;
    private static int fu;
    private static int fv;
    private static int[] fw;
    private static String[][] fx;
    static int[][] bl;
    private static int[] fy;
    static int bm;
    static int bn;
    static int bo;
    static int bp;
    static int bq;
    private static Image[] fz;
    private static int fA;
    private static int fB;
    private static int fC;
    private static String[] fD;
    private static String[] fE;
    private static int[] fF;
    public static boolean[] br;
    private static int[] fG;
    private static int[] fH;
    private static int[] fI;
    private static int[] fJ;
    private static Image fK;
    private static Image fL;
    private static Image fM;
    private static int fN;
    private static int fO;
    private static int fP;
    private static int fQ;
    private static int fR;
    private static int fS;
    private static int fT;
    private static int fU;
    private static int fV;
    private static int fW;
    private static int fX;
    private static int fY;
    private static int fZ;
    private static int ga;
    private static int gb;
    private static String gc;
    private static int gd;
    private static int ge;
    private static int gf;
    private static int gg;
    private static int gh;
    private static int gi;
    private static int gj;
    private static int gk;
    private static int gl;
    private static int gm;
    private static int gn;
    private static int go;
    private static Image[] gp;
    private static boolean gq;
    private static int gr;
    public static int bs;
    public static int bt;
    private static int gs;
    private static int gt;
    private static int gu;
    private static String gv;
    private static String gw;
    private static String[] gx;
    public static String[] bu;
    public static String[] bv;
    private static int gy;
    private static Image gz;
    private static Image gA;
    private static Image gB;
    private static int gC;
    public static int bw;
    public static int bx;
    public static int by;
    public static int bz;
    private static int[] gD;
    private static int[] gE;
    public static int[] bA;
    private static Image gF;
    private static int[] gG;
    private static int[] gH;
    private static int[] gI;
    private static int[] gJ;
    private static int[] gK;
    private static Image gL;
    private static int[] gM;
    private static int[] gN;
    private static int[] gO;
    private static int[] gP;
    private static int[] gQ;
    private static int[] gR;
    public static boolean bB;
    public static boolean bC;
    public static boolean bD;
    public static String[] bE;
    public static int bF;
    private static int gS;

    static {
        i = new boolean[100];
        j = new int[][]{{2, 3}, {2, 3}, {13, 3}, {1, 3}, {9, 3}, {9, 3}, {1, 3}, {2, 3}, {7, 3}, {2, 3}, {2, 3}, {13, 3}, {10, 3}, {13, 3}, {2, 3}, {13, 3}, {13, 3}, {14, 3}, {2, 3}, {2, 3}, {13, 3}, {15, 3}, {2, 3}, {2, 3}, {2, 3}, {13, 3}, {13, 3}, {13, 3}, {13, 3}, {2, 3}, {13, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}, {13, 3}};
        k = new int[][]{new int[2], {1, -1}, {2, -1}, {3, -1}, {4, -1}, {5, -1}, {6, -1}, {7, -1}, {8, -1}, {9, -1}, {10, -1}, {11, -1}, {12, -1}, {13, -1}, {14, -1}, {15, -1}, {16, -1}, {17, -1}, {18, -1}, {19, -1}, {20, -1}, {21, -1}, {22, -1}, {23, -1}, {24, -1}, {25, -1}, {26, -1}, {27, -1}, {28, -1}, {29, -1}, {30, -1}, {31, -1}, {32, -1}, {33, -1}, {34, -1}, {35, -1}, {36, -1}, {37, -1}, {38, -1}, {39, -1}, {40, -1}, {41, -1}, {42, -1}, {43, -1}, {44, -1}, {45, -1}, {46, -1}};
        int[][] nArrayArray = new int[47][];
        nArrayArray[0] = new int[2];
        nArrayArray[1] = new int[]{-1, -1};
        nArrayArray[2] = new int[]{6, -1};
        nArrayArray[3] = new int[]{3216, -1};
        nArrayArray[4] = new int[]{3264, -1};
        nArrayArray[5] = new int[]{3264, -1};
        nArrayArray[6] = new int[]{3264, -1};
        nArrayArray[7] = new int[]{-1, -1};
        nArrayArray[8] = new int[]{792, -1};
        nArrayArray[9] = new int[]{-1, -1};
        nArrayArray[10] = new int[]{-1, -1};
        int[] nArray = new int[2];
        nArray[1] = -1;
        nArrayArray[11] = nArray;
        nArrayArray[12] = new int[]{144, -1};
        nArrayArray[13] = new int[]{1, -1};
        nArrayArray[14] = new int[]{-1, -1};
        nArrayArray[15] = new int[]{1, -1};
        nArrayArray[16] = new int[]{6, -1};
        nArrayArray[17] = new int[]{-1, -1};
        nArrayArray[18] = new int[]{-1, -1};
        nArrayArray[19] = new int[]{-1, -1};
        nArrayArray[20] = new int[]{2, -1};
        nArrayArray[21] = new int[]{3, -1};
        nArrayArray[22] = new int[]{-1, -1};
        nArrayArray[23] = new int[]{-1, -1};
        nArrayArray[24] = new int[]{-1, -1};
        nArrayArray[25] = new int[]{4, -1};
        nArrayArray[26] = new int[]{4, -1};
        nArrayArray[27] = new int[]{4, -1};
        nArrayArray[28] = new int[]{4, -1};
        nArrayArray[29] = new int[]{-1, -1};
        nArrayArray[30] = new int[]{5, -1};
        nArrayArray[31] = new int[]{-1, -1};
        nArrayArray[32] = new int[]{-1, -1};
        nArrayArray[33] = new int[]{-1, -1};
        nArrayArray[34] = new int[0];
        nArrayArray[35] = new int[0];
        nArrayArray[36] = new int[0];
        nArrayArray[37] = new int[0];
        nArrayArray[38] = new int[0];
        nArrayArray[39] = new int[0];
        nArrayArray[40] = new int[0];
        nArrayArray[41] = new int[0];
        nArrayArray[42] = new int[0];
        nArrayArray[43] = new int[0];
        nArrayArray[44] = new int[0];
        nArrayArray[45] = new int[0];
        nArrayArray[46] = new int[]{7, -1};
        bK = nArrayArray;
        bL = new int[][]{{3312, 432}, {768, 840}, {1200, 192}, {1200, 192}, {696, 336}, {1344, 288}, {1512, 672}, {2232, 336}};
        bM = new int[][]{{35, 10, 74, 75, 76, 4, 4, 4, 4, 36, 4, 4, 4, 4, 4, 4, 17, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 3}, {4, 4, 3}, {4, 4, 4, 4, 4, 4, 3}, {4, 3}, {4, 3}, {4, 4, 3}, {45, 46, 4, 4, 4, 47, 7, 4, 4, 4, 3}, {45, 7, 4, 3}, {24, 4, 3}, {4, 3}, {4, 4, 4, 4, 4, 4, 3}, {4, 3}, {4, 4, 4, 4, 4, 3}, {4, 3}, {4, 4, 41, 4, 42, 4, 4, 3}, {4, 4, 4, 4, 4, 4, 3}, {4, 4, 17, 3}, {4, 4, 4, 4, 4, 17, 3}, {4, 4, 4, 4, 4, 4, 32, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 3}, {4, 3}, {4, 3}, {4, 4, 3}, {45, 48, 45, 7, 3}, {4, 4, 4, 4, 4, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 3}, {4, 4, 4, 4, 4, 3}, {4, 4, 4, 49, 4, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3}, {4, 4, 4, 4, 3}, {50, 4, 4, 4, 4, 4, 4, 4, 51, 4, 4, 4, 4, 4, 3}, {61, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3}, {52, 1, 1, 62, 25, 1, 1, 62, 25, 1, 1, 1, 1, 63, 1, 2, 64, 3}, {53, 1, 2, 1, 2, 1, 1, 1, 2, 62, 65, 25, 1, 2, 1, 2, 62, 70, 25, 1, 1, 2, 3}, {54, 1, 2, 1, 2, 1, 1, 1, 2, 62, 1, 2, 1, 1, 2, 67, 1, 2, 62, 1, 63, 1, 2, 68, 64, 1, 68, 1, 2, 68, 3}, {55, 25, 1, 2, 62, 72, 25, 1, 1, 2, 25, 3}, {56, 1, 2, 1, 1, 1, 2, 62, 65, 1, 1, 2, 66, 1, 2, 1, 2, 62, 1, 2, 63, 1, 64, 3}, {57, 1, 2, 65, 1, 1, 1, 2, 69, 62, 65, 1, 2, 1, 1, 1, 2, 62, 65, 1, 2, 63, 1, 1, 2, 64, 3}, {58, 1, 68, 2, 65, 1, 1, 62, 65, 25, 1, 1, 1, 2, 62, 1, 68, 1, 68, 2, 3}, {60, 25, 1, 1, 62, 25, 1, 1, 2, 62, 25, 1, 1, 2, 3}, {59, 25, 1, 1, 1, 1, 2, 62, 25, 1, 1, 2, 25, 62, 71, 65, 1, 2, 1, 3}, {50, 4, 4, 4, 4, 51, 4, 73, 29, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 29, 3}, {4, 3}, {73, 29, 3}, {4, 3}};
        int[][] nArrayArray2 = new int[47][];
        nArrayArray2[0] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int[] nArray2 = new int[10];
        nArray2[0] = -1;
        nArray2[1] = -1;
        nArray2[2] = -1;
        nArray2[3] = -1;
        nArray2[4] = -1;
        nArray2[5] = -1;
        nArray2[6] = -1;
        nArray2[7] = -1;
        nArray2[9] = -1;
        nArrayArray2[1] = nArray2;
        nArrayArray2[2] = new int[]{-1, 1, -1};
        nArrayArray2[3] = new int[]{-1, -1, -1, -1, -1, 2, -1};
        nArrayArray2[4] = new int[]{-1, -1};
        nArrayArray2[5] = new int[]{-1, -1};
        nArrayArray2[6] = new int[]{-1, 3, -1};
        nArrayArray2[7] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1};
        nArrayArray2[8] = new int[]{-1, -1, -1, -1};
        nArrayArray2[9] = new int[]{-1, -1, -1};
        nArrayArray2[10] = new int[]{-1, -1};
        nArrayArray2[11] = new int[]{-1, -1, -1, -1, -1, -1, -1};
        nArrayArray2[12] = new int[]{-1, -1};
        nArrayArray2[13] = new int[]{-1, -1, -1, -1, 17, -1};
        nArrayArray2[14] = new int[]{5, -1};
        nArrayArray2[15] = new int[]{-1, -1, -1, -1, -1, -1, 6, -1};
        nArrayArray2[16] = new int[]{-1, -1, -1, -1, -1, 7, -1};
        nArrayArray2[17] = new int[]{-1, -1, -1, -1};
        nArrayArray2[18] = new int[]{-1, -1, -1, -1, -1, -1, -1};
        nArrayArray2[19] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
        nArrayArray2[20] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        nArrayArray2[21] = new int[]{-1, -1};
        nArrayArray2[22] = new int[]{-1, -1};
        nArrayArray2[23] = new int[]{-1, -1, -1};
        nArrayArray2[24] = new int[]{-1, -1, -1, -1, -1};
        nArrayArray2[25] = new int[]{-1, -1, -1, -1, 8, -1};
        nArrayArray2[26] = new int[]{-1, -1, -1, -1, -1, -1, -1, 9, -1};
        nArrayArray2[27] = new int[]{-1, -1, -1, -1, 9, -1};
        nArrayArray2[28] = new int[]{-1, -1, -1, -1, -1, -1};
        nArrayArray2[29] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1};
        nArrayArray2[30] = new int[]{-1, -1, -1, -1, -1};
        nArrayArray2[31] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        nArrayArray2[32] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12, -1};
        nArrayArray2[33] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
        nArrayArray2[34] = new int[0];
        nArrayArray2[35] = new int[0];
        nArrayArray2[36] = new int[0];
        nArrayArray2[37] = new int[0];
        nArrayArray2[38] = new int[0];
        nArrayArray2[39] = new int[0];
        nArrayArray2[40] = new int[0];
        nArrayArray2[41] = new int[0];
        nArrayArray2[42] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        nArrayArray2[43] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        nArrayArray2[44] = new int[]{13, -1};
        nArrayArray2[45] = new int[]{-1, -1, -1};
        nArrayArray2[46] = new int[]{15, -1};
        bN = nArrayArray2;
        int[][] nArrayArray3 = new int[47][];
        int[] nArray3 = new int[18];
        nArray3[0] = -1;
        nArray3[1] = 2;
        nArray3[6] = 1;
        nArray3[7] = 2;
        nArray3[8] = 3;
        nArray3[9] = -1;
        nArray3[10] = 4;
        nArray3[11] = 5;
        nArray3[12] = 6;
        nArray3[13] = 7;
        nArray3[14] = 8;
        nArray3[15] = 9;
        nArray3[17] = 1;
        nArrayArray3[0] = nArray3;
        nArrayArray3[1] = new int[]{10, 11, 12, 13, 14, 15, 16, 17, 18, -1};
        nArrayArray3[2] = new int[]{19, 20, -1};
        nArrayArray3[3] = new int[]{21, 22, 23, 24, 25, 26, -1};
        nArrayArray3[4] = new int[]{27, -1};
        nArrayArray3[5] = new int[]{28, -1};
        nArrayArray3[6] = new int[]{29, 30, -1};
        int[] nArray4 = new int[11];
        nArray4[1] = -1;
        nArray4[2] = 31;
        nArray4[3] = 32;
        nArray4[4] = 33;
        nArray4[5] = -1;
        nArray4[6] = -1;
        nArray4[7] = 34;
        nArray4[8] = 35;
        nArray4[9] = 36;
        nArray4[10] = -1;
        nArrayArray3[7] = nArray4;
        nArrayArray3[8] = new int[]{1, -1, 37, -1};
        nArrayArray3[9] = new int[]{-1, 38, -1};
        nArrayArray3[10] = new int[]{39, -1};
        nArrayArray3[11] = new int[]{40, 41, 42, 43, 44, 45, -1};
        nArrayArray3[12] = new int[]{46, -1};
        nArrayArray3[13] = new int[]{47, 48, 49, 50, 51, -1};
        nArrayArray3[14] = new int[]{52, -1};
        nArrayArray3[15] = new int[]{53, 54, -1, 55, -1, 56, 57, -1};
        nArrayArray3[16] = new int[]{58, 59, 60, 61, 62, 63, -1};
        nArrayArray3[17] = new int[]{64, 65, 16, -1};
        nArrayArray3[18] = new int[]{66, 67, 68, 69, 70, 4, -1};
        nArrayArray3[19] = new int[]{71, 72, 73, 74, 75, 76, -1, -1};
        nArrayArray3[20] = new int[]{77, 78, 79, 80, 81, 82, 83, 84, 85, -1};
        nArrayArray3[21] = new int[]{86, -1};
        nArrayArray3[22] = new int[]{87, -1};
        nArrayArray3[23] = new int[]{88, 89, -1};
        nArrayArray3[24] = new int[]{3, -1, 8, -1, -1};
        nArrayArray3[25] = new int[]{90, 91, 92, 93, 94, -1};
        nArrayArray3[26] = new int[]{90, 91, 92, 95, 96, 97, 98, 99, -1};
        nArrayArray3[27] = new int[]{100, 96, 97, 98, 99, -1};
        nArrayArray3[28] = new int[]{101, 102, 103, -1, 104, -1};
        nArrayArray3[29] = new int[]{105, 106, 107, 108, 109, 110, 111, 112, 113, 114, -1};
        nArrayArray3[30] = new int[]{115, 116, 117, 118, -1};
        nArrayArray3[31] = new int[]{-1, 119, 120, 121, 122, 123, 124, 125, -1, 126, 127, 128, 129, 130, -1};
        nArrayArray3[32] = new int[]{-1, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, -1};
        nArrayArray3[33] = new int[]{-1, 53, 53, -1, 4, 53, 53, -1, 5, 53, 53, 53, 50, -1, 53, 50, -1, -1};
        int[] nArray5 = new int[23];
        nArray5[0] = -1;
        nArray5[1] = 50;
        nArray5[2] = 50;
        nArray5[3] = 52;
        nArray5[4] = 52;
        nArray5[5] = 53;
        nArray5[6] = 53;
        nArray5[7] = 53;
        nArray5[8] = 53;
        nArray5[9] = -1;
        nArray5[11] = 6;
        nArray5[12] = 54;
        nArray5[13] = 54;
        nArray5[14] = 53;
        nArray5[15] = 53;
        nArray5[16] = -1;
        nArray5[17] = -1;
        nArray5[18] = 7;
        nArray5[19] = 53;
        nArray5[20] = 53;
        nArray5[21] = 53;
        nArray5[22] = -1;
        nArrayArray3[34] = nArray5;
        nArrayArray3[35] = new int[]{-1, 53, 53, 54, 54, 53, 53, 53, 53, -1, 50, 50, 53, 53, 53, -1, 52, 52, -1, 53, -1, 53, 53, -1, -1, 53, -1, 53, 53, -1, -1};
        nArrayArray3[36] = new int[]{-1, 16, 53, 53, -1, -1, 17, 53, 53, 53, 20, -1};
        int[] nArray6 = new int[24];
        nArray6[0] = -1;
        nArray6[1] = 50;
        nArray6[2] = 50;
        nArray6[3] = 53;
        nArray6[4] = 53;
        nArray6[5] = 53;
        nArray6[6] = 53;
        nArray6[7] = -1;
        nArray6[9] = 53;
        nArray6[10] = 53;
        nArray6[11] = 53;
        nArray6[12] = -1;
        nArray6[13] = 54;
        nArray6[14] = 54;
        nArray6[15] = 51;
        nArray6[16] = 51;
        nArray6[17] = -1;
        nArray6[18] = 52;
        nArray6[19] = 52;
        nArray6[20] = -1;
        nArray6[21] = 53;
        nArray6[22] = -1;
        nArray6[23] = -1;
        nArrayArray3[37] = nArray6;
        int[] nArray7 = new int[27];
        nArray7[0] = -1;
        nArray7[1] = 53;
        nArray7[2] = 53;
        nArray7[4] = 53;
        nArray7[5] = 53;
        nArray7[6] = 53;
        nArray7[7] = 53;
        nArray7[8] = -1;
        nArray7[9] = -1;
        nArray7[10] = 1;
        nArray7[11] = 50;
        nArray7[12] = 50;
        nArray7[13] = 53;
        nArray7[14] = 53;
        nArray7[15] = 53;
        nArray7[16] = 53;
        nArray7[17] = -1;
        nArray7[19] = 49;
        nArray7[20] = 49;
        nArray7[21] = -1;
        nArray7[22] = 53;
        nArray7[23] = 53;
        nArray7[24] = 53;
        nArray7[25] = -1;
        nArray7[26] = -1;
        nArrayArray3[38] = nArray7;
        int[] nArray8 = new int[22];
        nArray8[0] = -1;
        nArray8[1] = 53;
        nArray8[2] = -1;
        nArray8[3] = 53;
        nArray8[4] = 3;
        nArray8[5] = 53;
        nArray8[6] = 53;
        nArray8[7] = -1;
        nArray8[9] = 9;
        nArray8[10] = 53;
        nArray8[11] = 53;
        nArray8[12] = 53;
        nArray8[13] = 53;
        nArray8[14] = -1;
        nArray8[15] = 53;
        nArray8[16] = -1;
        nArray8[17] = 53;
        nArray8[18] = -1;
        nArray8[19] = 53;
        nArray8[20] = 14;
        nArray8[21] = -1;
        nArrayArray3[39] = nArray8;
        nArrayArray3[40] = new int[]{-1, 10, 53, 53, -1, 18, 53, 53, 53, -1, 19, 53, 53, 53, -1};
        nArrayArray3[41] = new int[]{-1, 11, 53, 53, 53, 53, 53, -1, 12, 53, 53, 53, 13, -1, -1, 4, 56, 56, 49, -1};
        nArrayArray3[42] = new int[]{-1, 141, 142, 143, 144, -1, 145, -1, -1, -1};
        int[] nArray9 = new int[24];
        nArray9[0] = 146;
        nArray9[1] = 147;
        nArray9[2] = 148;
        nArray9[3] = 149;
        nArray9[4] = 150;
        nArray9[5] = 151;
        nArray9[6] = 152;
        nArray9[7] = 153;
        nArray9[8] = 154;
        nArray9[9] = 155;
        nArray9[10] = 162;
        nArray9[11] = 163;
        nArray9[12] = 164;
        nArray9[13] = 165;
        nArray9[14] = 166;
        nArray9[15] = 167;
        nArray9[16] = 168;
        nArray9[17] = 169;
        nArray9[18] = 156;
        nArray9[19] = 157;
        nArray9[20] = 158;
        nArray9[21] = 159;
        nArrayArray3[43] = nArray9;
        nArrayArray3[44] = new int[]{160, -1};
        nArrayArray3[45] = new int[]{-1, -1, -1};
        nArrayArray3[46] = new int[]{161, -1};
        bO = nArrayArray3;
        int[][] nArrayArray4 = new int[47][];
        int[] nArray10 = new int[18];
        nArray10[1] = 1;
        nArray10[2] = 2;
        nArray10[3] = 10;
        nArray10[4] = 20;
        nArray10[5] = 29;
        nArray10[6] = 30;
        nArray10[7] = 32;
        nArray10[8] = 40;
        nArray10[9] = 45;
        nArray10[10] = 62;
        nArray10[11] = 67;
        nArray10[12] = 72;
        nArray10[13] = 77;
        nArray10[14] = 82;
        nArray10[15] = 87;
        nArray10[16] = 92;
        nArray10[17] = 97;
        nArrayArray4[0] = nArray10;
        nArrayArray4[1] = new int[]{20, 24, 26, 28, 30, 32, 34, 36, 38, 40};
        nArrayArray4[2] = new int[]{20, 25, 37};
        nArrayArray4[3] = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70};
        nArrayArray4[4] = new int[]{10, 15};
        nArrayArray4[5] = new int[]{10, 15};
        nArrayArray4[6] = new int[]{5, 10, 15};
        nArrayArray4[7] = new int[]{5, 10, 15, 20, 25, 30, 35, 51, 56, 61, 66};
        nArrayArray4[8] = new int[]{5, 30, 45, 50};
        nArrayArray4[9] = new int[]{2, 19, 29};
        nArrayArray4[10] = new int[]{15, 20};
        nArrayArray4[11] = new int[]{5, 10, 15, 20, 25, 30, 35};
        nArrayArray4[12] = new int[]{5, 10};
        nArrayArray4[13] = new int[]{5, 10, 15, 20, 25, 30, 35};
        nArrayArray4[14] = new int[]{10, 15};
        int[] nArray11 = new int[8];
        nArray11[0] = 5;
        nArray11[1] = 10;
        nArray11[4] = 10;
        nArray11[5] = 15;
        nArray11[6] = 20;
        nArray11[7] = 25;
        nArrayArray4[15] = nArray11;
        nArrayArray4[16] = new int[]{5, 10, 15, 20, 40, 45, 50};
        int[] nArray12 = new int[4];
        nArray12[0] = 5;
        nArrayArray4[17] = nArray12;
        nArrayArray4[18] = new int[]{10, 15, 20, 25, 30, 35, 40};
        nArrayArray4[19] = new int[]{30, 35, 40, 55, 60, 65, 70, 75};
        nArrayArray4[20] = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 55};
        nArrayArray4[21] = new int[]{5, 10};
        nArrayArray4[22] = new int[]{5, 10};
        nArrayArray4[23] = new int[]{10, 15, 20};
        nArrayArray4[24] = new int[]{5, 10, 10, 30, 35};
        nArrayArray4[25] = new int[]{5, 10, 15, 20, 25, 30};
        nArrayArray4[26] = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45};
        nArrayArray4[27] = new int[]{5, 10, 15, 20, 25, 30};
        nArrayArray4[28] = new int[]{5, 10, 15, 20, 25, 30};
        nArrayArray4[29] = new int[]{5, 10, 15, 25, 35, 50, 55, 60, 65, 70, 90};
        nArrayArray4[30] = new int[]{5, 10, 20, 25, 30};
        nArrayArray4[31] = new int[]{5, 10, 15, 20, 25, 32, 40, 49, 49, 52, 70, 80, 85, 95, 100};
        nArrayArray4[32] = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
        nArrayArray4[33] = new int[]{5, 10, 20, 20, 44, 60, 72, 72, 97, 112, 113, 122, 127, 122, 127, 132, 132, 137};
        nArrayArray4[34] = new int[]{10, 15, 17, 17, 18, 20, 22, 24, 27, 27, 48, 53, 65, 65, 68, 68, 68, 88, 106, 110, 115, 120, 125};
        nArrayArray4[35] = new int[]{5, 10, 13, 14, 21, 25, 29, 30, 31, 31, 67, 70, 72, 77, 82, 83, 83, 84, 84, 120, 125, 125, 129, 129, 129, 132, 136, 136, 140, 140, 150};
        int[] nArray13 = new int[12];
        nArray13[2] = 13;
        nArray13[3] = 15;
        nArray13[4] = 15;
        nArray13[5] = 20;
        nArray13[6] = 25;
        nArray13[7] = 47;
        nArray13[8] = 52;
        nArray13[9] = 57;
        nArray13[10] = 57;
        nArray13[11] = 64;
        nArrayArray4[36] = nArray13;
        nArrayArray4[37] = new int[]{5, 7, 10, 17, 18, 19, 22, 22, 40, 56, 62, 67, 67, 67, 69, 72, 76, 76, 110, 112, 112, 112, 115, 115};
        nArrayArray4[38] = new int[]{15, 15, 20, 22, 25, 38, 33, 35, 35, 35, 50, 60, 62, 62, 64, 66, 67, 67, 80, 92, 95, 95, 95, 100, 105, 105, 105};
        nArrayArray4[39] = new int[]{10, 15, 17, 20, 17, 23, 27, 32, 43, 53, 60, 65, 70, 70, 81, 107, 109, 110, 110, 116, 121};
        nArrayArray4[40] = new int[]{15, 16, 24, 29, 29, 46, 55, 61, 65, 65, 81, 81, 96, 101, 106};
        nArrayArray4[41] = new int[]{15, 16, 25, 30, 35, 40, 45, 50, 67, 74, 79, 84, 84, 84, 103, 108, 108, 108, 108, 113};
        nArrayArray4[42] = new int[]{10, 15, 20, 25, 32, 37, 42, 42, 60, 80, 85, 90};
        nArrayArray4[43] = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120};
        nArrayArray4[44] = new int[]{5, 10};
        nArrayArray4[45] = new int[]{5, 10, 15};
        nArrayArray4[46] = new int[]{10, 15};
        bP = nArrayArray4;
        bQ = new int[][]{{2832, 288}, {744, 816}, {240, 192}, {3456, 456}, {-1, 672}, {-1, 672}, {2976, 552}, {3048, 576}, {2928, 408}, {1488, 288}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {672, 384}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {5136, 408}};
        bR = new int[][]{{2, 444, 408}};
        int[] nArray14 = new int[170];
        nArray14[3] = 1;
        nArray14[4] = 1;
        nArray14[5] = 1;
        nArray14[6] = 1;
        nArray14[7] = 1;
        nArray14[8] = 1;
        nArray14[9] = 1;
        nArray14[10] = 1;
        nArray14[12] = 1;
        nArray14[14] = 1;
        nArray14[15] = 1;
        nArray14[16] = 1;
        nArray14[18] = 1;
        nArray14[19] = 1;
        nArray14[21] = 1;
        nArray14[23] = 1;
        nArray14[25] = 1;
        nArray14[27] = 1;
        nArray14[28] = 1;
        nArray14[30] = 1;
        nArray14[31] = 4;
        nArray14[32] = 6;
        nArray14[33] = 6;
        nArray14[38] = 5;
        nArray14[41] = 1;
        nArray14[43] = 1;
        nArray14[44] = 1;
        nArray14[48] = 4;
        nArray14[49] = 4;
        nArray14[51] = 4;
        nArray14[54] = 4;
        nArray14[55] = 4;
        nArray14[58] = 1;
        nArray14[61] = 1;
        nArray14[62] = 1;
        nArray14[63] = 1;
        nArray14[64] = 3;
        nArray14[66] = 1;
        nArray14[67] = 1;
        nArray14[68] = 1;
        nArray14[69] = 1;
        nArray14[70] = 3;
        nArray14[71] = 3;
        nArray14[73] = 3;
        nArray14[74] = 3;
        nArray14[76] = 3;
        nArray14[77] = 7;
        nArray14[78] = 9;
        nArray14[80] = 8;
        nArray14[81] = 7;
        nArray14[82] = 9;
        nArray14[83] = 8;
        nArray14[85] = 7;
        nArray14[86] = 8;
        nArray14[87] = 7;
        nArray14[88] = 8;
        nArray14[89] = 7;
        nArray14[90] = 1;
        nArray14[92] = 1;
        nArray14[94] = 1;
        nArray14[96] = 1;
        nArray14[98] = 1;
        nArray14[101] = 1;
        nArray14[103] = 1;
        nArray14[106] = 1;
        nArray14[107] = 1;
        nArray14[108] = 1;
        nArray14[109] = 1;
        nArray14[111] = 1;
        nArray14[112] = 1;
        nArray14[114] = 1;
        nArray14[117] = 4;
        nArray14[119] = 4;
        nArray14[121] = 4;
        nArray14[122] = 4;
        nArray14[123] = 4;
        nArray14[124] = 4;
        nArray14[125] = 4;
        nArray14[131] = 6;
        nArray14[132] = 6;
        nArray14[133] = 6;
        nArray14[135] = 6;
        nArray14[136] = 6;
        nArray14[137] = 6;
        nArray14[138] = 6;
        nArray14[140] = 6;
        nArray14[143] = 1;
        nArray14[144] = 1;
        nArray14[146] = 1;
        nArray14[147] = 2;
        nArray14[148] = 1;
        nArray14[149] = 2;
        nArray14[150] = 1;
        nArray14[151] = 2;
        nArray14[152] = 1;
        nArray14[153] = 2;
        nArray14[154] = 1;
        nArray14[155] = 2;
        nArray14[156] = 1;
        nArray14[157] = 3;
        nArray14[158] = 1;
        nArray14[159] = 1;
        nArray14[162] = 1;
        nArray14[164] = 1;
        nArray14[165] = 3;
        nArray14[166] = 1;
        nArray14[167] = 2;
        nArray14[168] = 1;
        nArray14[169] = 2;
        bS = nArray14;
        int[] nArray15 = new int[170];
        nArray15[0] = -1;
        nArray15[1] = -1;
        nArray15[2] = -1;
        nArray15[3] = 1;
        nArray15[4] = 2;
        nArray15[5] = 2;
        nArray15[6] = 2;
        nArray15[7] = 2;
        nArray15[9] = 1;
        nArray15[11] = -1;
        nArray15[13] = -1;
        nArray15[17] = -1;
        nArray15[20] = -1;
        nArray15[21] = 2;
        nArray15[22] = -1;
        nArray15[23] = 2;
        nArray15[24] = -1;
        nArray15[25] = 2;
        nArray15[26] = -1;
        nArray15[27] = 2;
        nArray15[28] = 2;
        nArray15[29] = -1;
        nArray15[30] = 2;
        nArray15[31] = -1;
        nArray15[32] = -1;
        nArray15[33] = -1;
        nArray15[34] = -1;
        nArray15[35] = -1;
        nArray15[36] = -1;
        nArray15[37] = -1;
        nArray15[38] = -1;
        nArray15[39] = -1;
        nArray15[40] = -1;
        nArray15[41] = 2;
        nArray15[42] = -1;
        nArray15[43] = 2;
        nArray15[44] = 2;
        nArray15[45] = -1;
        nArray15[46] = -1;
        nArray15[47] = -1;
        nArray15[48] = -1;
        nArray15[49] = -1;
        nArray15[50] = -1;
        nArray15[51] = -1;
        nArray15[52] = -1;
        nArray15[53] = -1;
        nArray15[54] = -1;
        nArray15[55] = -1;
        nArray15[56] = -1;
        nArray15[57] = -1;
        nArray15[59] = -1;
        nArray15[60] = -1;
        nArray15[64] = -1;
        nArray15[65] = -1;
        nArray15[70] = -1;
        nArray15[71] = -1;
        nArray15[72] = -1;
        nArray15[73] = -1;
        nArray15[74] = -1;
        nArray15[75] = -1;
        nArray15[76] = -1;
        nArray15[77] = -1;
        nArray15[78] = -1;
        nArray15[79] = -1;
        nArray15[80] = -1;
        nArray15[81] = -1;
        nArray15[82] = -1;
        nArray15[83] = -1;
        nArray15[84] = -1;
        nArray15[85] = -1;
        nArray15[86] = -1;
        nArray15[87] = -1;
        nArray15[88] = -1;
        nArray15[89] = -1;
        nArray15[91] = -1;
        nArray15[93] = -1;
        nArray15[95] = -1;
        nArray15[97] = -1;
        nArray15[99] = -1;
        nArray15[100] = -1;
        nArray15[102] = -1;
        nArray15[104] = -1;
        nArray15[105] = -1;
        nArray15[110] = -1;
        nArray15[113] = -1;
        nArray15[115] = -1;
        nArray15[116] = -1;
        nArray15[117] = -1;
        nArray15[118] = -1;
        nArray15[119] = -1;
        nArray15[120] = -1;
        nArray15[121] = -1;
        nArray15[122] = -1;
        nArray15[123] = -1;
        nArray15[124] = -1;
        nArray15[125] = -1;
        nArray15[126] = -1;
        nArray15[127] = -1;
        nArray15[128] = -1;
        nArray15[129] = -1;
        nArray15[130] = -1;
        nArray15[131] = -1;
        nArray15[132] = -1;
        nArray15[133] = -1;
        nArray15[134] = -1;
        nArray15[135] = -1;
        nArray15[136] = -1;
        nArray15[137] = -1;
        nArray15[138] = -1;
        nArray15[139] = -1;
        nArray15[140] = -1;
        nArray15[141] = -1;
        nArray15[142] = -1;
        nArray15[145] = -1;
        nArray15[147] = 3;
        nArray15[151] = 1;
        nArray15[153] = 1;
        nArray15[155] = 3;
        nArray15[157] = -1;
        nArray15[160] = -1;
        nArray15[161] = -1;
        nArray15[163] = -1;
        nArray15[165] = -1;
        nArray15[167] = 2;
        nArray15[169] = 3;
        bT = nArray15;
        int[] nArray16 = new int[178];
        nArray16[1] = 1;
        nArray16[2] = 2;
        nArray16[3] = 3;
        nArray16[4] = 4;
        nArray16[5] = 5;
        nArray16[6] = 6;
        nArray16[7] = 7;
        nArray16[8] = 8;
        nArray16[9] = 9;
        nArray16[10] = 10;
        nArray16[11] = 11;
        nArray16[12] = 12;
        nArray16[13] = 13;
        nArray16[14] = 14;
        nArray16[15] = 15;
        nArray16[16] = 16;
        nArray16[17] = 17;
        nArray16[18] = 18;
        nArray16[19] = 19;
        nArray16[20] = 20;
        nArray16[21] = 21;
        nArray16[22] = 22;
        nArray16[23] = 23;
        nArray16[24] = 24;
        nArray16[25] = 25;
        nArray16[26] = 26;
        nArray16[27] = 27;
        nArray16[28] = 28;
        nArray16[29] = 29;
        nArray16[30] = 30;
        nArray16[31] = 31;
        nArray16[32] = 32;
        nArray16[33] = 33;
        nArray16[34] = 34;
        nArray16[35] = 35;
        nArray16[36] = 36;
        nArray16[37] = 37;
        nArray16[38] = 38;
        nArray16[39] = 39;
        nArray16[40] = 40;
        nArray16[41] = 41;
        nArray16[42] = 42;
        nArray16[43] = 43;
        nArray16[44] = 44;
        nArray16[45] = 45;
        nArray16[46] = 46;
        nArray16[47] = 47;
        nArray16[48] = 48;
        nArray16[49] = 49;
        nArray16[50] = 50;
        nArray16[51] = 51;
        nArray16[52] = 52;
        nArray16[53] = 53;
        nArray16[54] = 54;
        nArray16[55] = 55;
        nArray16[56] = 56;
        nArray16[57] = 57;
        nArray16[58] = 58;
        nArray16[59] = 59;
        nArray16[60] = 60;
        nArray16[61] = 61;
        nArray16[62] = 62;
        nArray16[63] = 63;
        nArray16[64] = 64;
        nArray16[65] = 65;
        nArray16[66] = 66;
        nArray16[67] = 67;
        nArray16[68] = 68;
        nArray16[69] = 69;
        nArray16[70] = 70;
        nArray16[71] = 71;
        nArray16[72] = 72;
        nArray16[73] = 73;
        nArray16[74] = 74;
        nArray16[75] = 75;
        nArray16[76] = 76;
        nArray16[77] = 77;
        nArray16[78] = 78;
        nArray16[79] = 79;
        nArray16[80] = 80;
        nArray16[81] = 81;
        nArray16[82] = 82;
        nArray16[83] = 83;
        nArray16[84] = 84;
        nArray16[85] = 85;
        nArray16[86] = 86;
        nArray16[87] = 87;
        nArray16[88] = 88;
        nArray16[89] = 89;
        nArray16[90] = 90;
        nArray16[91] = 91;
        nArray16[92] = 92;
        nArray16[93] = 93;
        nArray16[94] = 94;
        nArray16[95] = 95;
        nArray16[96] = 96;
        nArray16[97] = 97;
        nArray16[98] = 98;
        nArray16[99] = 99;
        nArray16[100] = 100;
        nArray16[101] = 101;
        nArray16[102] = 102;
        nArray16[103] = 103;
        nArray16[104] = 104;
        nArray16[105] = 105;
        nArray16[106] = 106;
        nArray16[107] = 107;
        nArray16[108] = 108;
        nArray16[109] = 109;
        nArray16[110] = 110;
        nArray16[111] = 111;
        nArray16[112] = 112;
        nArray16[113] = 113;
        nArray16[114] = 114;
        nArray16[115] = 115;
        nArray16[116] = 116;
        nArray16[117] = 117;
        nArray16[118] = 118;
        nArray16[119] = 119;
        nArray16[120] = 120;
        nArray16[121] = 121;
        nArray16[122] = 122;
        nArray16[123] = 123;
        nArray16[124] = 124;
        nArray16[125] = 125;
        nArray16[126] = 126;
        nArray16[127] = 127;
        nArray16[128] = 128;
        nArray16[129] = 129;
        nArray16[130] = 130;
        nArray16[131] = 131;
        nArray16[132] = 132;
        nArray16[133] = 133;
        nArray16[134] = 134;
        nArray16[135] = 135;
        nArray16[136] = 136;
        nArray16[137] = 137;
        nArray16[138] = 138;
        nArray16[139] = 139;
        nArray16[140] = 140;
        nArray16[141] = 141;
        nArray16[142] = 142;
        nArray16[143] = 143;
        nArray16[144] = 144;
        nArray16[145] = 145;
        nArray16[146] = 146;
        nArray16[147] = 147;
        nArray16[148] = 148;
        nArray16[149] = 149;
        nArray16[150] = 150;
        nArray16[151] = 151;
        nArray16[152] = 152;
        nArray16[153] = 153;
        nArray16[154] = 154;
        nArray16[155] = 155;
        nArray16[156] = 156;
        nArray16[157] = 157;
        nArray16[158] = 158;
        nArray16[159] = 159;
        nArray16[160] = 160;
        nArray16[161] = 161;
        nArray16[162] = 162;
        nArray16[163] = 163;
        nArray16[164] = 164;
        nArray16[165] = 165;
        nArray16[166] = 166;
        nArray16[167] = 167;
        nArray16[168] = 168;
        nArray16[169] = 169;
        nArray16[170] = 170;
        nArray16[171] = 171;
        nArray16[172] = 172;
        nArray16[173] = 173;
        nArray16[174] = 174;
        nArray16[175] = 175;
        nArray16[176] = 176;
        nArray16[177] = 177;
        bU = nArray16;
        m = new int[3];
        bV = new String[]{"Ch\u01a1i m\u1edbi", "Ch\u01a1i ti\u1ebfp", "Ch\u1ec9 d\u1eabn", "C\u1eadp nh\u1eadt game", "Tho\u00e1t"};
        int[] nArray17 = new int[5];
        nArray17[0] = -1;
        nArray17[1] = 2;
        nArray17[2] = 1;
        nArray17[3] = -2;
        bY = nArray17;
        bZ = new int[]{-3, 2, -1, 1, -10};
        n = -1;
        p = true;
        t = new boolean[14];
        u = new boolean[14];
        cb = 0;
        cz = "";
        aj = -1;
        ar = false;
        az = false;
        aB = 0;
        dT = new String[]{""};
        dU = 0;
        aG = 12;
        aK = -1;
        aQ = false;
        aS = new int[7];
        aT = new int[7];
        aU = new String[7];
        aV = 2;
        em = 0;
        eq = -1;
        bb = new int[3];
        eV = -1;
        eW = -1;
        eX = new Random();
        bc = -1;
        int[] nArray18 = new int[]{11, 12, 12, 11, 11, -1, -1, 12, -1, 11, 11, -1};
        eY = 0;
        int[] nArray19 = new int[4];
        nArray19[1] = 4;
        nArray19[2] = 2;
        nArray19[3] = 6;
        eZ = nArray19;
        bd = new byte[3];
        be = 0;
        fb = 0;
        bf = 0;
        ff = new int[]{24, 28, 30, 32, 34};
        fi = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 0xFFFFFF, 16765346, 16693877, 15439166, 13859359, 12281360};
        bk = new String[][]{{"B\u00ecnh HP nh\u1ecf", "B\u00ecnh HP v\u1eeba", "B\u00ecnh HP to", "B\u00ecnh MP nh\u1ecf", "B\u00ecnh MP v\u1eeba", "B\u00ecnh MP to"}, {"Fukiya", "Hebi", "L\u00f4i thi\u00ean ki\u1ebfm", "H\u00e0n B\u0103ng Ki\u1ebfm", "H\u1ecfa Long Ki\u1ebfm"}};
        fj = new String[][]{{"5000vn\u0111 / 50 b\u00ecnh", "10000vn\u0111 / 50 b\u00ecnh", "10000vn\u0111 / 30 b\u00ecnh", "5000vn\u0111 / 50 b\u00ecnh", "10000vn\u0111 / 50 b\u00ecnh", "10000vn\u0111 / 30 b\u00ecnh"}, {"5000vn\u0111", "5000vn\u0111", "5000vn\u0111", "10000vn\u0111", "15000vn\u0111"}};
        fk = new int[][]{{200, 600, 1500, 200, 600, 1200}, {12000, 12000, 12000, 45000, 182000}};
        fl = new String[][]{{"200$ / 1 b\u00ecnh", "600$ / 1 b\u00ecnh", "1500$ / 1 b\u00ecnh", "200$ / 1 b\u00ecnh", "600$ / 1 b\u00ecnh", "1200$ / 1 b\u00ecnh"}, {"12000$", "12000$", "12000$", "45000$", "182000$"}};
        fm = new String[][]{{"10000vn\u0111 /1 \u0111\u00f4i", "5000vn\u0111 /1 l\u00e1", "5000vn\u0111 /5 l\u00e1", "5000vn\u0111 /1 vi\u00ean", "5000vn\u0111 /2 vi\u00ean"}, {"5000vn\u0111 / 1 con", "15000vn\u0111 / 1 con"}};
        fn = new String[][]{{"20000$ /1 \u0111\u00f4i", "400$ /1 l\u00e1", "400$ /1 l\u00e1", "10000$ / 1 vi\u00ean", "5000$ / 1 vi\u00ean"}, {"10000$ / 1 con", "30000$ / 1 con"}};
        fo = new int[][]{{20000, 400, 400, 1000, 5000, 1200}, {10000, 30000}};
        fq = new String[][]{{"BinhHP_Nho(50)", "BinhHP_Vua(50)", "BinhHP_To(30)", "BinhMP_Nho(50)", "BinhMP_Vua(50)", "BinhMP_To(30)"}, {"Fukiya", "Hebi", "LoiThienKiem", "HanBangKiem", "HoaLongKiem"}, {"Ashiko", "BuaNo", "BuaTangHinh", "BuaDonTho", "NgocTheThan", "VienOxi"}, {"CuThongThai", "ChimGiupViec"}};
        fr = new String[][]{{"sms://8524", "sms://8624", "sms://8624", "sms://8524", "sms://8624", "sms://8624"}, {"sms://8524", "sms://8524", "sms://8524", "sms://8624", "sms://8724"}, {"sms://8624", "sms://8524", "sms://8524", "sms://8524", "sms://8524"}, {"sms://8524", "sms://8724"}};
        fw = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 0xFFFFFF, 16765346, 16693877, 15439166, 13859359, 12281360};
        fx = new String[][]{{"B\u00ecnh HP nh\u1ecf", "B\u00ecnh HP v\u1eeba", "B\u00ecnh HP to", "B\u00ecnh MP nh\u1ecf", "B\u00ecnh MP v\u1eeba", "B\u00ecnh MP to"}, {"Ashiko", "B\u00f9a n\u1ed5", "B\u00f9a t\u00e0ng h\u00ecnh", "Ng\u1ecdc th\u1ebf th\u00e2n", "Vi\u00ean oxi", "B\u1ea3n \u0111\u1ed3 \u0111\u1ed9ng Tamatamo"}, {"C\u00fa th\u00f4ng th\u00e1i", "Chim gi\u00fap vi\u1ec7c"}};
        bl = new int[][]{{5, 2, 1, 5, 2, 1}, new int[6], new int[2]};
        fy = new int[]{1000, 2000, 3000, 200, 400, 600};
        bp = 500;
        bq = 900;
        fD = new String[]{"S\u1eed d\u1ee5ng item", "S\u1eed d\u1ee5ng v\u0169 kh\u00ed", "Xem b\u1ea3n \u0111\u1ed3", "Xem nhi\u1ec7m v\u1ee5", "L\u01b0u v\u00e0 tho\u00e1t"};
        fE = new String[]{"Khu \u0111\u00e1 \u0111\u1ecf Akai", "Khu \u0111\u00e1 \u0111\u1ecf Aiko", "C\u00e1nh \u0111\u1ed3ng Hiya", "L\u00e0ng Echigo", "S\u00f4ng b\u0103ng Yamato", "H\u1ed3 tr\u0103ng", "H\u1ebbm n\u00fai Takana", "Mi\u1ec7ng n\u00fai l\u1eeda", "Hang n\u00fai Kurai", "R\u1eebng ch\u1ebft Kanashii", "\u0110\u1ed9ng Tamatamo", "L\u00e0ng Oshin", "\u0110\u1ec1n Amaterasu", "\u0110\u1ec1n Harumoto", "Phong \u1ea5n Ounio", "R\u1eebng t\u1ed1i"};
        fF = new int[]{50, 60, 70, 80, 90, 70, 100, 120, 150, 180, 220, 250, 300, 320, 350, 370, 390, 420, 450, 200};
        br = new boolean[16];
        fG = new int[]{120, 60, 187, 79, 35, 47, 135, 179, 175, 166, 215, 103, 35, 219, 183, 195};
        fH = new int[]{67, 43, 35, 111, 23, 147, 131, 103, 123, 145, 135, 219, 203, 183, 215, 158};
        fI = new int[]{55, 37, 194, 2, 228, 2, 2, 2, 1, 1, 1, 193, 145, 145, 27, 1};
        fJ = new int[]{28, 38, 14, 19, 17, 3, 20, 28, 3, 23, 6, 3, 20, 6, 17, 16};
        gc = "Chuy\u1ec3n \u0111\u1ebfn";
        bs = 5;
        bu = new String[]{"", "", ""};
        bv = new String[]{"", "", ""};
        gC = 840;
        by = -1;
        gD = new int[3];
        gE = new int[3];
        bA = new int[3];
    }

    public static void a() {
        if (h.ak == 0 || !aW) {
            return;
        }
        aW = false;
        int[] nArray = new int[500];
        int n2 = 0;
        if (dX > 0L) {
            dW += (int)((System.currentTimeMillis() - dX) / 1000L);
        }
        dX = System.currentTimeMillis();
        ++n2;
        nArray[0] = dW;
        ++n2;
        nArray[1] = ap;
        ++n2;
        nArray[2] = aq;
        ++n2;
        nArray[3] = ae;
        ++n2;
        nArray[4] = h.u ? 1 : 0;
        ++n2;
        nArray[5] = h.D;
        ++n2;
        nArray[6] = h.E;
        ++n2;
        nArray[7] = h.af;
        ++n2;
        nArray[8] = h.C ? 1 : 0;
        ++n2;
        nArray[9] = h.bl ? 1 : 0;
        ++n2;
        nArray[10] = n.l ? 1 : 0;
        ++n2;
        nArray[11] = n.m ? 1 : 0;
        ++n2;
        nArray[12] = p.h;
        ++n2;
        nArray[13] = b ? 1 : 0;
        ++n2;
        nArray[14] = f;
        ++n2;
        nArray[15] = g;
        ++n2;
        nArray[16] = h;
        ++n2;
        nArray[17] = c;
        ++n2;
        nArray[18] = d;
        ++n2;
        nArray[19] = e;
        int n3 = 0;
        while (n3 < i.length) {
            nArray[n2++] = i[n3] ? 1 : 0;
            ++n3;
        }
        nArray[n2++] = p.a ? 1 : 0;
        nArray[n2++] = p.b ? 1 : 0;
        nArray[n2++] = p.c ? 1 : 0;
        nArray[n2++] = p.d ? 1 : 0;
        nArray[n2++] = p.e ? 1 : 0;
        nArray[n2++] = p.f ? 1 : 0;
        nArray[n2++] = p.g ? 1 : 0;
        int n4 = 0;
        while (n4 < br.length) {
            nArray[n2++] = br[n4] ? 1 : 0;
            ++n4;
        }
        n3 = 0;
        while (n3 < 3) {
            n4 = 0;
            while (n4 < bl[n3].length) {
                nArray[n2++] = bl[n3][n4];
                ++n4;
            }
            ++n3;
        }
        nArray[n2++] = h.e ? 1 : 0;
        nArray[n2++] = ar ? 1 : 0;
        nArray[n2++] = h.ad;
        nArray[n2++] = h.ae;
        nArray[n2++] = h.ag;
        nArray[n2++] = h.ah;
        nArray[n2++] = h.ai;
        nArray[n2++] = h.aj;
        nArray[n2++] = h.ak;
        nArray[n2++] = h.al;
        nArray[n2++] = h.am;
        nArray[n2++] = h.an;
        nArray[n2++] = h.ao;
        nArray[n2++] = h.ap;
        nArray[n2++] = h.aq;
        nArray[n2++] = h.ar;
        nArray[n2++] = h.as;
        nArray[n2++] = h.at;
        nArray[n2++] = h.au;
        nArray[n2++] = h.av;
        nArray[n2++] = h.aw;
        nArray[n2++] = h.ax;
        nArray[n2++] = h.aH;
        nArray[n2++] = h.aI;
        nArray[n2++] = h.aJ;
        nArray[n2++] = h.ay;
        nArray[n2++] = h.az;
        nArray[n2++] = h.aA;
        nArray[n2++] = h.aB;
        nArray[n2++] = h.aC;
        nArray[n2++] = h.aD;
        nArray[n2++] = h.aE;
        nArray[n2++] = h.aF;
        nArray[n2++] = h.aG;
        nArray[n2++] = h.aK;
        nArray[n2++] = h.aL;
        nArray[n2++] = h.aM;
        nArray[n2++] = h.aN;
        nArray[n2++] = h.aO ? 1 : 0;
        nArray[n2++] = h.aP ? 1 : 0;
        nArray[n2++] = h.aQ ? 1 : 0;
        nArray[n2++] = h.aR;
        nArray[n2++] = h.aS;
        nArray[n2++] = h.aT;
        nArray[n2++] = h.aU ? 1 : 0;
        nArray[n2++] = h.aW;
        nArray[n2++] = h.aX;
        nArray[n2++] = h.aY;
        nArray[n2++] = h.l ? 1 : 0;
        nArray[n2++] = h.i ? 1 : 0;
        nArray[n2++] = h.j ? 1 : 0;
        nArray[n2++] = h.f ? 1 : 0;
        nArray[n2++] = h.g ? 1 : 0;
        nArray[n2++] = h.h ? 1 : 0;
        nArray[n2++] = h.o ? 1 : 0;
        n3 = 0;
        while (n3 < 3) {
            nArray[n2++] = h.n[n3] ? 1 : 0;
            ++n3;
        }
        nArray[n2++] = h.k ? 1 : 0;
        nArray[n2++] = bo;
        nArray[n2++] = bn;
        nArray[n2++] = h.aZ;
        nArray[n2++] = h.ba;
        n4 = 0;
        while (n4 < 10) {
            nArray[n2++] = as[n4];
            nArray[n2++] = at[n4];
            nArray[n2++] = au[n4];
            ++n4;
        }
        nArray[n2++] = av;
        nArray[n2++] = aw;
        nArray[n2++] = dM;
        nArray[n2++] = dN;
        nArray[n2++] = dO;
        nArray[n2++] = dP;
        nArray[n2++] = ax;
        nArray[n2++] = ay;
        nArray[n2++] = dQ;
        nArray[n2++] = dR;
        nArray[n2++] = aH ? 1 : 0;
        nArray[n2++] = aG;
        nArray[n2++] = aI;
        nArray[n2++] = h.F;
        nArray[n2++] = h.G;
        nArray[n2++] = h.H;
        nArray[n2++] = h.I;
        nArray[n2++] = h.J;
        nArray[n2++] = h.K;
        nArray[n2++] = h.L;
        nArray[n2++] = h.M;
        nArray[n2++] = h.N;
        nArray[n2++] = h.O;
        nArray[n2++] = h.P;
        nArray[n2++] = h.Q;
        nArray[n2++] = h.R;
        nArray[n2++] = h.S;
        nArray[n2++] = h.T;
        nArray[n2++] = h.U;
        nArray[n2++] = h.V;
        nArray[n2++] = h.W;
        nArray[n2++] = h.X;
        nArray[n2++] = h.Y;
        nArray[n2++] = h.Z;
        nArray[n2++] = h.aa;
        nArray[n2++] = h.ab;
        nArray[n2++] = h.bo;
        nArray[n2++] = h.bp;
        nArray[n2++] = h.bq;
        nArray[n2++] = h.br;
        nArray[n2++] = h.bs;
        nArray[n2++] = h.bt;
        nArray[n2++] = h.bu;
        nArray[n2++] = h.bv;
        nArray[n2++] = h.bw;
        nArray[n2++] = h.bx;
        nArray[n2++] = h.by;
        nArray[n2++] = h.bz;
        nArray[n2++] = h.bA;
        nArray[n2++] = h.bB;
        nArray[n2++] = h.bC;
        nArray[n2++] = h.bD;
        nArray[n2++] = h.bE;
        nArray[n2++] = h.bF;
        nArray[n2++] = h.bG;
        nArray[n2++] = h.bH;
        nArray[n2++] = h.bI;
        nArray[n2++] = h.bJ;
        nArray[n2++] = h.bK;
        nArray[n2++] = i.c ? 1 : 0;
        nArray[n2++] = i.d ? 1 : 0;
        nArray[n2++] = i.e ? 1 : 0;
        nArray[n2++] = i.f ? 1 : 0;
        nArray[n2++] = i.g ? 1 : 0;
        nArray[n2++] = f.a;
        nArray[n2++] = i.h ? 1 : 0;
        nArray[n2++] = i.i ? 1 : 0;
        nArray[n2++] = i.k ? 1 : 0;
        nArray[n2++] = d.g ? 1 : 0;
        nArray[n2++] = bm;
        nArray[n2++] = c.h;
        nArray[n2++] = c.g;
        nArray[n2++] = aV;
        n4 = 2;
        while (n4 < 7) {
            nArray[n2++] = aT[n4];
            nArray[n2++] = aS[n4];
            ++n4;
        }
        if (f.a > -1) {
            nArray[n2++] = f.b;
            nArray[n2++] = f.c;
            nArray[n2++] = f.i;
        }
        nArray[n2++] = f.f;
        nArray[n2++] = gC;
        nArray[n2++] = bz;
        nArray[n2++] = by;
        n4 = 0;
        while (n4 < bA.length) {
            nArray[n2++] = bA[n4];
            ++n4;
        }
        nArray[n2++] = bF;
        nArray[n2++] = bB ? 1 : 0;
        nArray[n2++] = bC ? 1 : 0;
        nArray[n2++] = bD ? 1 : 0;
        n4 = 0;
        while (n4 < 3) {
            nArray[n2++] = m[n4];
            ++n4;
        }
        nArray[n2++] = c.l;
        n4 = 0;
        while (n4 < fI.length) {
            nArray[n2++] = fI[n4];
            nArray[n2++] = fJ[n4];
            ++n4;
        }
        nArray[n2++] = h.aV ? 1 : 0;
        nArray[n2++] = aJ;
        if (aJ > 0) {
            nArray[n2++] = aK;
            if (aN != null) {
                n4 = 0;
                while (n4 < aN.length) {
                    nArray[n2++] = aN[n4];
                    ++n4;
                }
            }
            if (dZ != null) {
                n4 = 0;
                while (n4 < dZ.length) {
                    nArray[n2++] = dZ[n4];
                    ++n4;
                }
            }
            if (dY != null) {
                n4 = 0;
                while (n4 < dY.length) {
                    nArray[n2++] = dY[n4];
                    ++n4;
                }
            }
            if (aO != null) {
                n4 = 0;
                while (n4 < aO.length) {
                    nArray[n2++] = aO[n4];
                    ++n4;
                }
            }
            if (aP != null) {
                n4 = 0;
                while (n4 < aP.length) {
                    nArray[n2++] = aP[n4];
                    ++n4;
                }
            }
            if (aL != null) {
                n4 = 0;
                while (n4 < aL.length) {
                    nArray[n2++] = aL[n4];
                    ++n4;
                }
            }
            if (aM != null) {
                n4 = 0;
                while (n4 < aM.length) {
                    nArray[n2++] = aM[n4];
                    ++n4;
                }
            }
        }
        int[] nArray2 = nArray;
        int n5 = nArray.length;
        byte[] byArray = new byte[n5 << 2];
        n3 = 0;
        while (n3 < n5) {
            byArray[n3 << 2] = nArray2[n3] >> 24;
            byArray[(n3 << 2) + 1] = (byte)(nArray2[n3] >>> 16);
            byArray[(n3 << 2) + 2] = (byte)(nArray2[n3] >>> 8);
            byArray[(n3 << 2) + 3] = (byte)nArray2[n3];
            ++n3;
        }
        Midlet.a("ninjasave", byArray);
        Midlet.a(true);
    }

    public static void a(boolean bl) {
        Object[] objectArray = Midlet.b("ninjasave");
        int n2 = objectArray.length;
        int[] nArray = new int[n2 >> 2];
        int n3 = 0;
        while (n3 < nArray.length) {
            nArray[n3] = (objectArray[n3 << 2] << 24) + ((objectArray[(n3 << 2) + 1] & 0xFF) << 16) + ((objectArray[(n3 << 2) + 2] & 0xFF) << 8) + (objectArray[(n3 << 2) + 3] & 0xFF);
            ++n3;
        }
        objectArray = nArray;
        n3 = 0;
        ++n3;
        dW = objectArray[0];
        ++n3;
        ap = objectArray[1];
        ++n3;
        aq = objectArray[2];
        ++n3;
        ae = objectArray[3];
        ++n3;
        h.u = objectArray[4] == 1;
        ++n3;
        h.D = objectArray[5];
        ++n3;
        h.E = objectArray[6];
        ++n3;
        h.af = objectArray[7];
        ++n3;
        h.C = objectArray[8] == 1;
        ++n3;
        h.bl = objectArray[9] == 1;
        ++n3;
        n.l = objectArray[10] == 1;
        ++n3;
        n.m = objectArray[11] == 1;
        ++n3;
        p.h = objectArray[12];
        ++n3;
        b = objectArray[13] == 1;
        ++n3;
        f = objectArray[14];
        ++n3;
        g = objectArray[15];
        ++n3;
        h = objectArray[16];
        ++n3;
        c = objectArray[17];
        ++n3;
        d = objectArray[18];
        ++n3;
        e = objectArray[19];
        int n4 = 0;
        while (n4 < i.length) {
            q.i[n4] = objectArray[n3++] == 1;
            ++n4;
        }
        p.a = objectArray[n3++] == 1;
        p.b = objectArray[n3++] == 1;
        p.c = objectArray[n3++] == 1;
        p.d = objectArray[n3++] == 1;
        p.e = objectArray[n3++] == 1;
        p.f = objectArray[n3++] == 1;
        p.g = objectArray[n3++] == 1;
        int n5 = 0;
        while (n5 < br.length) {
            q.br[n5] = objectArray[n3++] == 1;
            ++n5;
        }
        n4 = 0;
        while (n4 < 3) {
            n5 = 0;
            while (n5 < q.bl[n4].length) {
                q.bl[n4][n5] = objectArray[n3++];
                ++n5;
            }
            ++n4;
        }
        h.e = objectArray[n3++] == 1;
        ar = objectArray[n3++] == 1;
        h.ad = objectArray[n3++];
        h.ae = objectArray[n3++];
        h.ag = objectArray[n3++];
        h.ah = objectArray[n3++];
        h.ai = objectArray[n3++];
        h.aj = objectArray[n3++];
        h.ak = objectArray[n3++];
        h.al = objectArray[n3++];
        h.am = objectArray[n3++];
        h.an = objectArray[n3++];
        h.ao = objectArray[n3++];
        h.ap = objectArray[n3++];
        h.aq = objectArray[n3++];
        h.ar = objectArray[n3++];
        h.as = objectArray[n3++];
        h.at = objectArray[n3++];
        h.au = objectArray[n3++];
        h.av = objectArray[n3++];
        h.aw = objectArray[n3++];
        h.ax = objectArray[n3++];
        h.aH = objectArray[n3++];
        h.aI = objectArray[n3++];
        h.aJ = objectArray[n3++];
        h.ay = objectArray[n3++];
        h.az = objectArray[n3++];
        h.aA = objectArray[n3++];
        h.aB = objectArray[n3++];
        h.aC = objectArray[n3++];
        h.aD = objectArray[n3++];
        h.aE = objectArray[n3++];
        h.aF = objectArray[n3++];
        h.aG = objectArray[n3++];
        h.aK = objectArray[n3++];
        h.aL = objectArray[n3++];
        h.aM = objectArray[n3++];
        h.aN = objectArray[n3++];
        h.aO = objectArray[n3++] == 1;
        h.aP = objectArray[n3++] == 1;
        h.aQ = objectArray[n3++] == 1;
        h.aR = objectArray[n3++];
        h.aS = objectArray[n3++];
        h.aT = objectArray[n3++];
        h.aU = objectArray[n3++] == 1;
        h.aW = objectArray[n3++];
        h.aX = objectArray[n3++];
        h.aY = objectArray[n3++];
        h.l = objectArray[n3++] == 1;
        h.i = objectArray[n3++] == 1;
        h.j = objectArray[n3++] == 1;
        h.f = objectArray[n3++] == 1;
        h.g = objectArray[n3++] == 1;
        h.h = objectArray[n3++] == 1;
        boolean bl2 = h.o = objectArray[n3++] == 1;
        if (h.f && h.o) {
            Image[] imageArray = new Image[10];
            h.B = imageArray;
            imageArray[0] = q.c("/cp/cl0.png");
            n5 = 1;
            while (n5 < 10) {
                h.B[n5] = q.c("/cp/cl" + n5 + "0.png");
                ++n5;
            }
        }
        n4 = 0;
        while (n4 < 3) {
            h.n[n4] = objectArray[n3++] == 1;
            ++n4;
        }
        h.k = objectArray[n3++] == 1;
        bo = objectArray[n3++];
        bn = objectArray[n3++];
        h.aZ = objectArray[n3++];
        h.ba = objectArray[n3++];
        n5 = 0;
        while (n5 < 10) {
            q.as[n5] = objectArray[n3++];
            q.at[n5] = objectArray[n3++];
            q.au[n5] = objectArray[n3++];
            ++n5;
        }
        av = objectArray[n3++];
        aw = objectArray[n3++];
        dM = objectArray[n3++];
        dN = objectArray[n3++];
        dO = objectArray[n3++];
        dP = objectArray[n3++];
        ax = objectArray[n3++];
        ay = objectArray[n3++];
        dQ = objectArray[n3++];
        dR = objectArray[n3++];
        aH = objectArray[n3++] == 1;
        aG = objectArray[n3++];
        aI = objectArray[n3++];
        h.F = objectArray[n3++];
        h.G = objectArray[n3++];
        h.H = objectArray[n3++];
        h.I = objectArray[n3++];
        h.J = objectArray[n3++];
        h.K = objectArray[n3++];
        h.L = objectArray[n3++];
        h.M = objectArray[n3++];
        h.N = objectArray[n3++];
        h.O = objectArray[n3++];
        h.P = objectArray[n3++];
        h.Q = objectArray[n3++];
        h.R = objectArray[n3++];
        h.S = objectArray[n3++];
        h.T = objectArray[n3++];
        h.U = objectArray[n3++];
        h.V = objectArray[n3++];
        h.W = objectArray[n3++];
        h.X = objectArray[n3++];
        h.Y = objectArray[n3++];
        h.Z = objectArray[n3++];
        h.aa = objectArray[n3++];
        h.ab = objectArray[n3++];
        if (ap == 7 && !i[7]) {
            n5 = 0;
            while (n5 < 5) {
                ++n3;
                ++n5;
            }
        } else {
            h.bo = objectArray[n3++];
            h.bp = objectArray[n3++];
            h.bq = objectArray[n3++];
            h.br = objectArray[n3++];
            h.bs = objectArray[n3++];
        }
        h.bt = objectArray[n3++];
        h.bu = objectArray[n3++];
        h.bv = objectArray[n3++];
        h.bw = objectArray[n3++];
        h.bx = objectArray[n3++];
        h.by = objectArray[n3++];
        h.bz = objectArray[n3++];
        h.bA = objectArray[n3++];
        h.bB = objectArray[n3++];
        h.bC = objectArray[n3++];
        h.bD = objectArray[n3++];
        h.bE = objectArray[n3++];
        h.bF = objectArray[n3++];
        h.bG = objectArray[n3++];
        h.bH = objectArray[n3++];
        h.bI = objectArray[n3++];
        h.bJ = objectArray[n3++];
        h.bK = objectArray[n3++];
        if (!(i.c = objectArray[n3++] == 1)) {
            q.au[1] = 0;
        }
        if (!(i.d = objectArray[n3++] == 1)) {
            q.au[0] = 0;
        }
        i.e = objectArray[n3++] == 1;
        if (!(i.f = objectArray[n3++] == 1)) {
            q.au[3] = 0;
        }
        if (!(i.g = objectArray[n3++] == 1)) {
            q.au[2] = 0;
        }
        f.a = objectArray[n3++];
        i.h = objectArray[n3++] == 1;
        i.i = objectArray[n3++] == 1;
        i.k = objectArray[n3++] == 1;
        d.g = objectArray[n3++] == 1;
        if ((bm = objectArray[n3++]) > 0) {
            n5 = 0;
            while (n5 < 2) {
                q.dF[n5] = q.c("/c/pt" + (n5 + 2) + ".png");
                ++n5;
            }
        }
        c.h = objectArray[n3++];
        c.g = objectArray[n3++];
        aV = objectArray[n3++];
        n5 = 2;
        while (n5 < 7) {
            q.aT[n5] = objectArray[n3++];
            switch (aT[n5]) {
                case 1: {
                    q.aU[n5] = "L\u00f4i thi\u00ean ki\u1ebfm";
                    break;
                }
                case 2: {
                    q.aU[n5] = "H\u00e0n b\u0103ng ki\u1ebfm";
                    break;
                }
                case 3: {
                    q.aU[n5] = "H\u1ecfa long ki\u1ebfm";
                    break;
                }
                case 4: {
                    q.aU[n5] = "Fukiya";
                    break;
                }
                case 5: {
                    q.aU[n5] = "Hebi";
                }
            }
            q.aS[n5] = objectArray[n3++];
            ++n5;
        }
        if (f.a > -1) {
            f.b = objectArray[n3++];
            f.c = objectArray[n3++];
            f.i = objectArray[n3++];
        }
        f.f = objectArray[n3++];
        gC = objectArray[n3++];
        bz = objectArray[n3++];
        by = objectArray[n3++];
        n5 = 0;
        while (n5 < bA.length) {
            q.bA[n5] = objectArray[n3++];
            ++n5;
        }
        bF = objectArray[n3++];
        bB = objectArray[n3++] == 1;
        bC = objectArray[n3++] == 1;
        bD = objectArray[n3++] == 1;
        n5 = 0;
        while (n5 < 3) {
            q.m[n5] = objectArray[n3++];
            ++n5;
        }
        c.l = objectArray[n3++];
        n5 = 0;
        while (n5 < fI.length) {
            q.fI[n5] = objectArray[n3++];
            q.fJ[n5] = objectArray[n3++];
            ++n5;
        }
        h.aV = objectArray[n3++] == 1;
        if ((aJ = objectArray[n3++]) > 0) {
            aK = objectArray[n3++];
            try {
                n5 = 0;
                while (n5 < aN.length) {
                    q.aN[n5] = objectArray[n3++];
                    ++n5;
                }
                n5 = 0;
                while (n5 < dZ.length) {
                    q.dZ[n5] = objectArray[n3++];
                    ++n5;
                }
                n5 = 0;
                while (n5 < dY.length) {
                    q.dY[n5] = objectArray[n3++];
                    ++n5;
                }
                n5 = 0;
                while (n5 < aO.length) {
                    q.aO[n5] = objectArray[n3++];
                    ++n5;
                }
                n5 = 0;
                while (n5 < aP.length) {
                    q.aP[n5] = objectArray[n3++];
                    ++n5;
                }
                n5 = 0;
                while (n5 < aL.length) {
                    q.aL[n5] = objectArray[n3++];
                    ++n5;
                }
                n5 = 0;
                while (n5 < aM.length) {
                    q.aM[n5] = objectArray[n3++];
                    ++n5;
                }
                return;
            }
            catch (Exception exception) {}
        }
    }

    public static final int a(int n2) {
        if (n2 > 0) {
            return n2;
        }
        return -n2;
    }

    public static void a(int n2, int n3, int n4, int n5, int n6) {
        int n7 = 0;
        while (n7 < af) {
            if (ai[n7] == 0) break;
            ++n7;
        }
        if (n7 == af) {
            return;
        }
        q.ag[n7] = n2;
        q.ah[n7] = n3;
        q.dj[n7] = n4;
        q.dk[n7] = n5;
        q.ai[n7] = 1;
        q.dm[n7] = n6;
        if (n6 < 160) {
            q.dn[n7] = 0;
            return;
        }
        if (n6 < 600) {
            q.dn[n7] = 1;
            return;
        }
        q.dn[n7] = 2;
    }

    public static void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        if (n7 == 1) {
            return;
        }
        n5 = 0;
        while (n5 < Y) {
            if (ab[n5] == 0) break;
            ++n5;
        }
        if (n5 == Y) {
            return;
        }
        q.Z[n5] = n2;
        q.aa[n5] = n3;
        q.cZ[n5] = n4;
        q.da[n5] = -5;
        q.ab[n5] = 1;
        q.dc[n5] = n6;
        q.cY[n5] = n7;
        if (n6 < 200) {
            q.db[n5] = 0;
            return;
        }
        if (n6 < 400) {
            q.db[n5] = 1;
            return;
        }
        q.db[n5] = 2;
    }

    static void b() {
        int n2 = 0;
        while (n2 < 14) {
            q.t[n2] = false;
            ++n2;
        }
    }

    static void c() {
        int n2 = 0;
        while (n2 < 14) {
            q.u[n2] = false;
            ++n2;
        }
    }

    private static void F() {
        if (ed == -1) {
            return;
        }
        h.aR = -1;
        h.aW = 0;
        switch (ed) {
            case 0: {
                q.a("T\u00ecm Ashiko");
                p.h = 1;
                return;
            }
            case 1: {
                int[] nArray = bl[1];
                nArray[0] = nArray[0] + 1;
                q.a("T\u00ecm b\u00ed k\u00edp h\u01b0\u1edbng d\u1eabn s\u1eed d\u1ee5ng Ashiko");
                p.h = 2;
                return;
            }
            case 2: {
                h.aR = 0;
                h.aW = 0;
                q.a("Gi\u1ebft 20 Thi\u00ean T\u00ea");
                p.h = 3;
                return;
            }
            case 3: {
                q.a("Ti\u1ebfp t\u1ee5c \u0111i t\u00ecm b\u00ed k\u00edp");
                p.h = 2;
                return;
            }
            case 4: {
                q.a("Gi\u1ea3i c\u1ee9u Kh\u00f3i tr\u01b0\u1eddng Ikazuki");
                p.h = 4;
                return;
            }
            case 5: {
                q.a("\u0110\u00e3 h\u1ecdc xong c\u00e1ch s\u1eed d\u1ee5ng Ashiko");
                p.c = true;
                return;
            }
            case 6: {
                p.d = true;
                q.a("Quay v\u1ec1 g\u1eb7p th\u1ea7y Matsu");
                p.h = 5;
                return;
            }
            case 7: {
                q.a("L\u00ean \u0111\u01b0\u1eddng \u0111\u1ebfn l\u00e0ng Oshin");
                p.h = 6;
                return;
            }
            case 8: {
                q.a("Gi\u1ebft c\u00e1 m\u1ed9t s\u1eebng l\u1ea5y s\u00e1ch th\u1ea7n");
                p.h = 7;
                return;
            }
            case 9: {
                q.a("T\u00ecm l\u00f4ng c\u1ee7a ph\u01b0\u1ee3ng ho\u00e0ng l\u1eeda");
                p.h = 8;
                return;
            }
            case 10: {
                q.a("Quay v\u1ec1 g\u1eb7p c\u00f4 Toyotomi");
                p.h = 9;
            }
            case 11: {
                q.a("L\u00ean \u0111\u01b0\u1eddng \u0111i ti\u00eau di\u1ec7t Mumutaro");
                p.h = 10;
                return;
            }
            case 12: {
                q.bA[2] = 1;
                return;
            }
            case 13: {
                q.bA[2] = -1;
                return;
            }
            case 15: {
                q.a("T\u00ecm th\u00fa c\u01b0\u1ee1i");
                p.h = 11;
                return;
            }
            case 17: {
                q.a("D\u00f9ng Ashiko nh\u1ea3y l\u00ean v\u00e1ch \u0111\u00e1 g\u1ea1t c\u1ea7n");
                p.h = 12;
            }
        }
    }

    private static void G() {
        ak = null;
        an = null;
    }

    private static void H() {
        c.a();
        q.G();
        k.a();
        dB = null;
        System.gc();
        a.a();
        h.f();
        dy = null;
        System.gc();
    }

    private static void I() {
        dA = null;
        dJ = null;
        q.H();
    }

    public static final void d() {
        int n2;
        cC = 0;
        if (dy == null) {
            dy = new Image[4][2];
            n2 = 0;
            while (n2 < 4) {
                int n3 = 0;
                while (n3 < 2) {
                    try {
                        q.dy[n2][n3] = Image.createImage((String)("/c/ar" + n2 + n3 + ".png"));
                    }
                    catch (IOException iOException) {}
                    ++n3;
                }
                ++n2;
            }
        }
        cC = 10;
        cD = new int[10];
        cE = new int[cC];
        cF = new int[cC];
        cG = new int[cC];
        cH = new int[cC];
        cI = new int[cC];
        n2 = 0;
        while (n2 < cC) {
            q.cF[n2] = -1;
            ++n2;
        }
    }

    public static void e() {
        if (en == null) {
            en = new Image[4];
            int n2 = 0;
            while (n2 < 2) {
                try {
                    q.en[n2] = Image.createImage((String)("/e/e" + n2 + ".png"));
                }
                catch (IOException iOException) {}
                ++n2;
            }
        }
    }

    public static void f() {
        if (dz == null) {
            dz = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.dz[n2] = Image.createImage((String)("/u/arr" + n2 + ".png"));
                }
                catch (Exception exception) {}
                ++n2;
            }
        }
    }

    public static void g() {
        eu = -1;
        dx = 0;
        if (ap == 19 && !h.n[0]) {
            eu = 7;
            q.am[7] = q.c("/i/pt2.png");
            q.a(48, 48);
            q.c(48, 43);
            return;
        }
        if (ap == 20 && !h.n[1]) {
            eu = 8;
            q.am[8] = q.c("/i/pt2.png");
            q.a(432, 192);
            q.c(432, 187);
            return;
        }
        if (ap == 21 && !h.n[2]) {
            eu = 9;
            q.am[9] = q.c("/i/pt2.png");
            q.a(384, 312);
            q.c(384, 283);
            return;
        }
        if (ap == 0) {
            eu = 0;
        }
        if (ap == 7) {
            eu = 1;
        }
        if (ap == 4 && !p.e) {
            eu = 2;
        }
        if (ap == 11 && p.e) {
            eu = 2;
        }
        if (ap == 12) {
            eu = 3;
        }
        try {
            q.am[q.eu] = Image.createImage((String)("/i/bk" + eu + ".png"));
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void b(int n2) {
        aJ = 0;
        if (n2 == 4) {
            aJ = 1;
            if (dB == null) {
                try {
                    dB = Image.createImage((String)"/i/b.png");
                }
                catch (Exception exception) {}
            }
            aL = new int[aJ];
            aM = new int[aJ];
            aN = new int[aJ];
            aO = new int[aJ];
            aP = new int[aJ];
            dY = new int[aJ];
            dZ = new int[aJ];
            n2 = 15;
            n2 = 174;
            n2 = 0;
            q.aL[0] = 174 * k.f + 12;
            q.aM[0] = 15 * k.f + 24;
            q.aN[0] = 1;
        }
    }

    public static final void h() {
        gb = ap;
        if (gb == 17) {
            gb = 9;
        }
        if (gb == 22) {
            gb = 2;
        }
        S = v;
        if (w >= 208) {
            ck = true;
            co = 50;
            T = w - co;
            cn = S;
            cl = 0;
            cm = T + 1;
        } else {
            co = 16;
            T = w - co;
            ck = false;
            co = 0;
            cn = S;
            cl = 0;
            cm = T + 1;
            M = S / 3;
            cu = M + 29;
            cv = M + 50;
        }
        cx = w / 6;
        if (cx < 48) {
            cx = 48;
        }
        U = S >> 1;
        V = T >> 1;
        W = 3 * T / 4;
        X = S / 6;
        cA = S / k.f + 2;
        cB = T / k.f + 2;
        if (S % 24 != 0) {
            ++cA;
        }
        dQ = (k.a - 1) * k.f - S;
        dR = (k.b - 1) * k.f - T;
        ax = av = h.ag - U + X * h.as;
        ay = aw = h.ah - W;
        if (ax < 0) {
            ax = 0;
        }
        if (ax > dQ) {
            ax = dQ;
        }
        if (ay < 0) {
            ay = 0;
        }
        if (ay > dR) {
            ay = dR;
        }
        if ((N = ax / k.f - 1) < 0) {
            N = 0;
        }
        O = ay / k.f;
        P = N + cA;
        Q = O + cB;
        if (O < 0) {
            O = 0;
        }
        if (Q > k.b - 1) {
            Q = k.b - 1;
        }
    }

    public static void i() {
        q.j();
        if (!ck) {
            return;
        }
        try {
            if (dC == null) {
                dC = new Image[2];
                int n2 = 0;
                while (n2 < 2) {
                    q.dC[n2] = Image.createImage((String)("/u/c" + n2 + ".png"));
                    ++n2;
                }
            }
            cp = dC[0].getWidth();
            cq = dC[1].getWidth();
            cr = S - cp - cq + 1;
            L = 78;
            cs = cm + 7;
            M = S - 134;
            ct = S - 84;
            cw = 5;
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    public static void j() {
        try {
            if (ao == null) {
                ao = Image.createImage((String)"/u/co.png");
            }
            Image[] imageArray = new Image[2];
            dK = imageArray;
            imageArray[1] = Image.createImage((String)"/u/wpt0.png");
            q.dK[0] = Image.createImage((String)("/u/wpt" + (h.af + 1) + ".png"));
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static void k() {
        af = 5;
        if (dD == null) {
            try {
                dD = new Image[3];
                int n2 = 0;
                while (n2 < 3) {
                    q.dD[n2] = Image.createImage((String)("/i/dm" + n2 + ".png"));
                    ++n2;
                }
            }
            catch (Exception exception) {}
        }
        ag = new int[af];
        ah = new int[af];
        ai = new int[af];
        dj = new int[af];
        dk = new int[af];
        dl = new int[af];
        dn = new int[af];
        dm = new int[af];
    }

    public static final void l() {
        if (a || ap == 9 || ap == 10 || ap == 8 || ap == 14) {
            return;
        }
        if (dE == null) {
            dE = new Image[2][5];
            int n2 = 0;
            while (n2 < 2) {
                int n3 = 0;
                while (n3 < 5) {
                    try {
                        q.dE[n2][n3] = Image.createImage((String)("/e/d" + n2 + n3 + ".png"));
                    }
                    catch (IOException iOException) {}
                    ++n3;
                }
                ++n2;
            }
        }
        cS = new int[2];
        cT = new int[2];
        int[] nArray = new int[2];
        cU = nArray;
        q.cU[1] = -1;
        nArray[0] = -1;
    }

    public static final void m() {
        int n2;
        if (dF == null) {
            dF = new Image[2];
            n2 = 0;
            while (n2 < 2) {
                q.dF[n2] = q.c("/c/pt" + n2 + ".png");
                ++n2;
            }
        }
        dd = 10;
        de = new int[10];
        df = new int[dd];
        ac = new int[dd];
        dg = new int[dd];
        ad = new int[dd];
        di = new boolean[dd];
        dh = new int[dd];
        n2 = 0;
        while (n2 < dd) {
            q.ac[n2] = -1;
            ++n2;
        }
    }

    public static void n() {
        if (ap != 5 && ap != 13) {
            return;
        }
        if (ew == null) {
            if (ap == 5) {
                ew = q.c("/c/m.png");
            }
            if (ap == 13) {
                ew = q.c("/c/t.png");
            }
        }
        ex = 10;
        ey = new int[10];
        ez = new int[ex];
        eC = new int[ex];
        eA = new int[ex];
        eB = new int[ex];
        int n2 = 0;
        while (n2 < ex) {
            q.eC[n2] = -1;
            ++n2;
        }
    }

    public static final void o() {
        dp = new int[5];
        dq = new int[5];
        dr = new int[5];
        ds = new int[5];
        dt = new int[5];
        du = new int[5];
        do = new String[5];
        int n2 = 0;
        while (n2 < 5) {
            q.dt[n2] = -1;
            ++n2;
        }
    }

    public static void c(int n2) {
        if (e.a > 0) {
            e.a = 0;
        }
        B = 2;
        ap = n2;
        e.a(ap);
        Midlet.d = 1;
        q.ae();
        new Thread(Midlet.b).start();
    }

    public static void p() {
        if (dG == null) {
            dG = new Image[2];
            int n2 = 0;
            while (n2 < 2) {
                try {
                    q.dG[n2] = Image.createImage((String)("/e/l" + n2 + ".png"));
                }
                catch (IOException iOException) {}
                ++n2;
            }
        }
    }

    private static void J() {
        B = 2;
        q.ae();
        Midlet.d = 3;
        ap = 18;
        e.a(18);
        bW = 0;
        try {
            Image[] imageArray = new Image[2];
            dA = imageArray;
            imageArray[0] = Image.createImage((String)"/u/t20.png");
            q.dA[1] = Image.createImage((String)"/u/t21.png");
            dJ = Image.createImage((String)"/u/tt.png");
            fd = q.c("/u/fi.png");
        }
        catch (Exception exception) {}
        aw = 0;
        ay = 0;
        av = 0;
        ax = 0;
        dN = 1;
        dM = 1;
        cg = w - 30;
        ch = w;
        cc = 30;
        cd = w - 50;
        ce = cf = cc + cd >> 1;
        if (Midlet.a()) {
            cb = 1;
        }
        new Thread(Midlet.b).start();
    }

    public static void q() {
        Y = 5;
        if (dH == null) {
            try {
                Image[] imageArray = new Image[4];
                dH = imageArray;
                imageArray[0] = Image.createImage((String)"/i/pt0.png");
                q.dH[1] = Image.createImage((String)"/i/pt1.png");
                q.dH[2] = Image.createImage((String)"/i/pt2.png");
                q.dH[3] = Image.createImage((String)"/i/pt3.png");
            }
            catch (Exception exception) {}
        }
        Z = new int[Y];
        aa = new int[Y];
        ab = new int[Y];
        cZ = new int[Y];
        da = new int[Y];
        db = new int[Y];
        dc = new int[Y];
        cY = new int[Y];
    }

    public static final void r() {
        if (dI == null) {
            dI = new Image[3];
            int n2 = 0;
            while (n2 < 3) {
                q.dI[n2] = q.c("/e/sp" + n2 + ".png");
                ++n2;
            }
        }
        cJ = new int[2];
        cK = new int[2];
        cL = new int[2];
        cM = new int[2];
        cN = new int[2];
        q.cL[1] = -1;
        q.cL[0] = -1;
    }

    public static final void s() {
        if (eD == null) {
            eD = q.c("/e/set.png");
        }
        if (ap == 11 && !i.e) {
            q.c(2904, 456, 1, 1);
            return;
        }
        eI = 0;
    }

    public static void d(int n2) {
        aH = false;
        az = false;
        if (n2 == 16 && aX) {
            h.ag = 240;
            h.ah = 240;
            h.as = 1;
            q.f(43);
            return;
        }
        if (n2 == 16 && b) {
            h.ag = 240;
            h.ah = 240;
            h.as = 1;
            q.f(0);
        }
        if (n2 == 0 && !i[1]) {
            h.ag = 1488;
            h.ah = 672;
            h.as = 1;
            q.f(1);
        }
        if (n2 == 0 && !i[2] && p.a) {
            q.f(2);
        }
        if (n2 == 3 && i[2] && !i[3]) {
            q.f(3);
        }
        if (n2 == 3 && i[3] && !p.b) {
            q.f(4);
        }
        if (n2 == 3 && !i[2]) {
            q.f(5);
        }
        if (n2 == 3 && p.b && !i[6]) {
            q.f(6);
        }
        if (n2 == 7 && !i[7]) {
            q.f(7);
        }
        if (n2 == 3 && !p.d && i[7]) {
            q.f(12);
        }
        if (n2 == 8 && !i[8]) {
            q.f(8);
        }
        if (n2 == 3 && i[9] && bA[0] != 10 && !i[11]) {
            q.f(11);
        }
        if (n2 == 8 && !i[13] && bA[0] == 10) {
            q.f(13);
        }
        if (n2 == 8 && bz == -1 && !i[15]) {
            q.f(15);
        }
        if (n2 == 0 && !i[16] && p.h == 5 && p.d) {
            c.a = 0;
            i.D = false;
            q.f(16);
        }
        if (n2 == 4 && !i[17]) {
            q.f(17);
            h.J = 2;
            h.H = 4;
        }
        if (n2 == 16 && !b) {
            h.ag = 240;
            h.ah = 240;
            h.as = 1;
            q.f(18);
        }
        if (n2 == 4 && i[17] && !i[19]) {
            h.ag = f;
            h.ah = g;
            h.as = h;
            h.F = c;
            h.G = d;
            h.R = e;
            q.f(19);
        }
        if (n2 == 6 && !i[20]) {
            q.f(20);
        }
        if (!(n2 != 6 || !i[20] || bB && bC && bD)) {
            q.f(21);
        }
        if (n2 == 11 && i[24] && i.e && !p.f) {
            if (!i[25] && !p.e) {
                q.f(25);
            }
            if (!i[25] && !i[26] && p.e) {
                q.f(26);
            }
            if (!i[27] && i[25] && p.e) {
                q.f(27);
            }
        }
        if (n2 == 11 && !i[28] && p.f) {
            q.f(28);
        }
        if (n2 == 17 && !i[30] && i[29]) {
            q.f(30);
        }
        if (n2 == 14 && !i[44]) {
            q.f(44);
        }
        if (n2 == 2 && !i[46] && !p.g && i[16]) {
            q.f(46);
        }
    }

    public static final void t() {
        if (a) {
            return;
        }
        dL = new Image[3];
        int n2 = 0;
        while (n2 < 3) {
            try {
                q.dL[n2] = Image.createImage((String)("/e/w" + n2 + ".png"));
            }
            catch (IOException iOException) {}
            ++n2;
        }
        cO = new int[2];
        cP = new int[2];
        cQ = new int[2];
        cR = new int[2];
        q.cQ[1] = -1;
        q.cQ[0] = -1;
    }

    private static void K() {
        if (ei < ee[ec].length) {
            ei = ee[ec].length;
            eh = 0;
            return;
        }
        ++ec;
        ei = 0;
        eh = 0;
    }

    private static void a(Graphics graphics) {
        if (ap == 21) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, k.c, k.d);
            return;
        }
        if (a && ap == 15 && bc > -1) {
            bc = bc < 9 ? ++bc : -1;
            graphics.setColor(0xD8D8DA);
            if (bc % 3 == 0) {
                graphics.fillRect(0, 0, S, T);
            }
        }
        if (z && !a) {
            int n2;
            graphics.setColor(E);
            graphics.fillRect(0, 0, S, T);
            if (Midlet.g != 1 && ak[3] != null) {
                n2 = -((ax >> aR[3]) % al[3]);
                while (n2 < S) {
                    if (Midlet.g == 4 || Midlet.g == 5) {
                        graphics.drawImage(ak[3], n2, T - ak[3].getHeight(), 0);
                    } else {
                        graphics.drawImage(ak[3], n2, I, 0);
                    }
                    n2 += al[3];
                }
            }
            if (Midlet.g != 1 && Midlet.g != 4 && Midlet.g != 5 && ak[2] != null) {
                if (ap == 8) {
                    graphics.drawImage(ak[2], U, H, 0);
                } else {
                    n2 = -((ax >> aR[2]) % al[2]);
                    while (n2 < S) {
                        graphics.drawImage(ak[2], n2, H, 0);
                        n2 += al[2];
                    }
                }
            }
            if (Midlet.g != 4 && Midlet.g != 5 && ak[1] != null) {
                if (ap != 15) {
                    n2 = -((ax >> aR[1]) % al[1]);
                    while (n2 < S) {
                        graphics.drawImage(ak[1], n2, G, 0);
                        n2 += al[1];
                    }
                } else if (bc > -1) {
                    bc = bc < 9 ? ++bc : -1;
                    graphics.setColor(0xD8D8DA);
                    if (bc % 3 == 0) {
                        graphics.fillRect(0, G, S, ak[1].getHeight());
                    }
                }
            }
            if (ap == 8 || ap == 14) {
                graphics.setColor(0x110000);
                graphics.fillRect(0, F + 20, S, T);
            }
            if (ak[0] != null) {
                n2 = -((ax >> aR[0]) % al[0]);
                while (n2 < S) {
                    graphics.drawImage(ak[0], n2, F, 0);
                    n2 += al[0];
                }
            }
            if (an != null) {
                n2 = 0;
                while (n2 < 2) {
                    graphics.drawImage(an, C[n2], D[n2], 3);
                    ++n2;
                }
                return;
            }
        } else {
            graphics.setColor(E);
            graphics.fillRect(0, 0, S, T);
            if (a) {
                return;
            }
            int n3 = 0;
            while (n3 < 2) {
                graphics.drawImage(an, C[n3], D[n3], 3);
                ++n3;
            }
        }
    }

    private static void b(Graphics graphics) {
        if (ck) {
            return;
        }
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.drawImage(h.A[0], 0, 2, 0);
        graphics.setColor(0);
        graphics.drawRect(23, 2, M + 1, 4);
        graphics.drawRect(23, 8, M + 1, 4);
        graphics.drawRect(23, 14, M + 1, 3);
        graphics.setColor(0xFF0000);
        int n2 = h.ay * M / h.az;
        graphics.fillRect(24, 3, n2, 3);
        graphics.setColor(0x550000);
        graphics.fillRect(n2 + 24, 3, M - n2, 3);
        graphics.setColor(255);
        n2 = h.aA * M / h.aB;
        graphics.fillRect(24, 9, n2, 3);
        graphics.setColor(0x550000);
        graphics.fillRect(n2 + 24, 9, M - n2, 3);
        graphics.setColor(7274435);
        n2 = h.aN * M / h.b[h.aM - 24];
        System.out.println("hpWidth " + n2);
        graphics.fillRect(24, 15, n2, 2);
        graphics.setColor(19506);
        graphics.fillRect(n2 + 24, 15, M - n2, 2);
        Midlet.a(graphics, "lv:" + h.aM, 2, cu, 7, 0);
        Midlet.a(graphics, String.valueOf(ae) + "$", 1, cv, 7, 0);
    }

    private static void c(Graphics graphics) {
        A = false;
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(-1, cm - 1, cn + 2, 100);
        graphics.setColor(0);
        if (!ck) {
            graphics.fillRect(0, cm, cn, 17);
            Midlet.a(graphics, J, 3, cm + 3, 0);
            if (K == "Ban Do") {
                graphics.drawRegion(dH[2], 0, 75, 13, 15, 0, cn - 3, cm + 3, 24);
                return;
            }
            Midlet.a(graphics, K, cn - 3, cm + 3, 1);
            return;
        }
        graphics.fillRect(0, cm + 33, cn, 17);
        Midlet.a(graphics, J, 3, cm + 36, 0);
        if (K.equals("Ban Do")) {
            graphics.drawRegion(dH[2], 0, 75, 13, 15, 0, cn - 3, cm + 36, 24);
        } else {
            Midlet.a(graphics, K, cn - 3, cm + 36, 1);
        }
        graphics.setColor(4352);
        graphics.fillRect(cp, cm, cr, 1);
        graphics.fillRect(cp, cm + 35, cr, 1);
        graphics.fillRect(cp, cm + 33, cr, 1);
        graphics.fillRect(cp, cm + 30, cr, 1);
        graphics.fillRect(cp, cm + 28, cr, 1);
        graphics.fillRect(cp, cm + 26, cr, 1);
        graphics.fillRect(cp, cm + 12, cr, 1);
        graphics.fillRect(cp, cm + 24, cr, 1);
        graphics.fillRect(cp, cm + 18, cr, 1);
        graphics.fillRect(cp, cm + 16, cr, 1);
        graphics.fillRect(cp, cm + 2, cr, 1);
        graphics.fillRect(cp, cm + 4, cr, 1);
        graphics.fillRect(cp, cm + 6, cr, 1);
        graphics.fillRect(cp, cm + 14, cr, 1);
        graphics.setColor(12562018);
        graphics.fillRect(cp, cm + 1, cr, 1);
        graphics.fillRect(cp, cm + 5, cr, 1);
        graphics.fillRect(cp, cm + 17, cr, 1);
        graphics.fillRect(cp, cm + 34, cr, 1);
        graphics.fillRect(cp, cm + 29, cr, 1);
        graphics.setColor(14667167);
        graphics.fillRect(cp, cm + 3, cr, 1);
        graphics.fillRect(cp, cm + 15, cr, 1);
        graphics.fillRect(cp, cm + 27, cr, 1);
        graphics.setColor(0x333333);
        graphics.fillRect(cp, cm + 7, cr, 5);
        graphics.fillRect(cp, cm + 19, cr, 5);
        graphics.fillRect(cp, cm + 31, cr, 2);
        graphics.setColor(9463099);
        graphics.fillRect(cp, cm + 25, cr, 1);
        graphics.fillRect(cp, cm + 13, cr, 1);
        graphics.drawImage(dC[0], 0, cm, 0);
        graphics.drawImage(dC[1], 0 + cn, cm, 24);
        int n2 = h.ay * M / h.az;
        graphics.setColor(0x770000);
        graphics.fillRect(L, cs, n2, 2);
        graphics.setColor(0xCC0000);
        graphics.fillRect(L, cs + 1, n2, 4);
        n2 = h.aA * M / h.aB;
        graphics.setColor(119);
        graphics.fillRect(L, cs + 12, n2, 2);
        graphics.setColor(204);
        graphics.fillRect(L, cs + 14, n2, cw - 2);
        if (bd[0] != 2) {
            Midlet.a(graphics, "" + ae, 1, cn - 18, cm + 6, 2);
        }
        n2 = h.aN * ct / h.b[h.aM - 24];
        graphics.setColor(94373);
        graphics.fillRect(31, cm + 31, n2, 1);
        graphics.setColor(65535);
        graphics.fillRect(31, cm + 32, n2, 1);
        Midlet.a(graphics, "" + h.aM, 2, 26, cm + 11, 2);
        graphics.drawImage(dK[h.ae], 43, cm + 16, 3);
        if (bd[1] != 3) {
            Midlet.a(graphics, "" + (bl[0][0] + bl[0][1] + bl[0][2]), 2, cn - 29, cm + 19, 2);
        }
        if (bd[2] != 4) {
            Midlet.a(graphics, "" + (bl[0][3] + bl[0][4] + bl[0][5]), 2, cn - 9, cm + 19, 2);
        }
    }

    private static void d(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(-1, -1, v + 2, w + 2);
        graphics.setColor(0);
        graphics.fillRect(0, 0, S, cy);
        if (ck) {
            graphics.fillRect(0, w - cy + 1, S, cy);
            return;
        }
        graphics.fillRect(0, w - 13, v, 13);
    }

    private static void e(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(-1, -1, v + 2, w + 2);
        ea = ee[ec].length;
        int n2 = eg - ay - (ea << 4);
        boolean bl = true;
        if (n2 < 10) {
            bl = false;
            n2 = 10;
        }
        graphics.setColor(0xFFFFFF);
        graphics.fillRect(ek, n2 - 5, ej, (ea << 4) + 7);
        graphics.setColor(17408);
        graphics.drawRect(ek - 1, n2 - 5, ej + 1, (ea << 4) + 7);
        int n3 = 0;
        while (n3 < ei) {
            Midlet.b(graphics, ee[ec][n3], ek + (ej >> 1), n2 + (n3 << 4), 2);
            ++n3;
        }
        if (ei < ee[ec].length) {
            Midlet.b(graphics, ee[ec][ei].substring(0, eh), ek + (ej >> 1), n2 + (ei << 4), 2);
        }
        graphics.setColor(0);
        graphics.fillRect(0, cm + 33, cn, 15);
        if (!l && bl && ef > ax + 10 && ef < ax + S - 10) {
            graphics.drawImage(ao, ef - ax + 3, eg - ay + 2, 17);
        }
        if (aq % 10 < 8) {
            Midlet.a(graphics, "Ti\u1ebfp t\u1ee5c", U, w - 13, 2);
        }
    }

    private static void f(Graphics graphics) {
        graphics.setClip(-1, -1, v + 2, w + 1);
        q.a(graphics);
        graphics.translate(-ax, -ay);
        e.b(graphics);
        k.b(graphics);
        if (a) {
            k.c(graphics);
        } else {
            k.d(graphics);
        }
        k.a(graphics);
        h.b(graphics);
        h.a(graphics);
        h.c(graphics);
        c.a(graphics);
        k.e(graphics);
        e.a(graphics);
        q.d(graphics);
        if (aQ) {
            q.e(graphics);
        }
    }

    public static final void b(int n2, int n3, int n4, int n5, int n6) {
        int n7 = -1;
        int n8 = 0;
        while (n8 < cC) {
            if (cF[n8] == -1) {
                n7 = n8;
                break;
            }
            ++n8;
        }
        if (n7 == -1) {
            return;
        }
        q.cF[n7] = 1;
        q.cD[n7] = n2;
        q.cE[n7] = n3;
        q.cG[n7] = n4;
        q.cH[n7] = n5;
        q.cI[n7] = n6;
    }

    public static void a(int n2, int n3) {
        n3 = k.d(n3);
        q.as[q.eu] = n2;
        q.at[q.eu] = n3;
        q.au[q.eu] = 1;
    }

    public static void e(int n2) {
        dU = 0;
        aA = n2;
        aB = 0;
        az = true;
        dV = false;
        bG = h.ae;
        bH = h.af;
        q.b();
        q.c();
    }

    public static void a(String[] stringArray, int n2, int n3, int n4) {
        ed = n2;
        ef = n3;
        eg = n4;
        eb = stringArray.length;
        ec = 0;
        ee = new String[eb][];
        if (n2 == 14 || n2 == 16) {
            ej = S - 2;
            ax = 0;
        } else {
            ej = S >> 1;
        }
        if (ej < 120) {
            ej = 120;
        }
        n2 = 0;
        while (n2 < eb) {
            q.ee[n2] = Midlet.b(stringArray[n2], ej - 20);
            ++n2;
        }
        ek = ef - (ej >> 1) - ax;
        if (ek < 2) {
            ek = 2;
        }
        if (ek + ej > S - 2) {
            ek = S - 2 - ej;
        }
        aQ = true;
        eh = 0;
        ei = 0;
    }

    public static final boolean a(int n2, int n3, int n4) {
        if (a) {
            return false;
        }
        int n5 = n2 = n2 == 1 ? 0 : 1;
        if (cU[n2] != -1) {
            return false;
        }
        q.cU[n2] = 0;
        q.cS[n2] = n3;
        q.cT[n2] = n4;
        return true;
    }

    public static final void a(int n2, int n3, int n4, int n5, boolean bl) {
        int n6 = -1;
        int n7 = 0;
        while (n7 < dd) {
            if (ac[n7] == -1) {
                n6 = n7;
                break;
            }
            ++n7;
        }
        if (n6 == -1) {
            return;
        }
        q.ac[n6] = 1;
        q.de[n6] = n2;
        q.df[n6] = n3;
        q.ad[n6] = n4;
        q.dh[n6] = n5;
        q.di[n6] = bl;
    }

    public static final void b(int n2, int n3, int n4, int n5, int n6, int n7) {
        eW = -1;
        eV = -1;
        int n8 = 0;
        while (n8 < 3) {
            if (bb[n8] != 0) {
                return;
            }
            ++n8;
        }
        eL = n7;
        eN = new Image[2];
        eM = 0;
        switch (eL) {
            case 1: {
                q.eN[0] = q.c("/c/ar10.png");
                q.eN[1] = q.c("/c/ar11.png");
                aY = new int[1];
                aZ = new int[1];
                eK = new int[1];
                ba = new int[1];
                q.aY[0] = n2;
                q.aZ[0] = n3;
                q.eK[0] = n4;
                q.ba[0] = n5;
                q.bb[0] = n6;
                return;
            }
            case 2: {
                q.eN[0] = q.c("/c/ar00.png");
                q.eN[1] = q.c("/c/ar01.png");
                aY = new int[3];
                aZ = new int[3];
                eK = new int[3];
                ba = new int[3];
                n8 = 0;
                while (n8 < 3) {
                    q.aY[n8] = n2;
                    q.aZ[n8] = n3;
                    q.eK[n8] = n4;
                    q.ba[n8] = 0;
                    q.bb[n8] = n6;
                    ++n8;
                }
                break;
            }
        }
    }

    public static final void a(int n2, int n3, int n4, int n5) {
        if (eU == null) {
            eU = q.c("/e/r.png");
        }
        eS = 1;
        eO = n2;
        eP = n3;
        eQ = n4;
        eR = 0;
        eT = 0;
    }

    public static void b(int n2, int n3, int n4, int n5) {
        int n6 = -1;
        int n7 = 0;
        while (n7 < ex) {
            if (eC[n7] == -1) {
                n6 = n7;
                break;
            }
            ++n7;
        }
        if (n6 == -1) {
            return;
        }
        q.eC[n6] = 1;
        q.ey[n6] = n2;
        q.ez[n6] = n3;
        q.eA[n6] = n4;
        q.eB[n6] = n5;
    }

    public static int b(int n2, int n3) {
        return n2 + eX.nextInt(n3 - n2);
    }

    public static final void a(String string, int n2, int n3, int n4, int n5, int n6) {
        n5 = -1;
        int n7 = 0;
        while (n7 < 5) {
            if (dt[n7] == -1) {
                n5 = n7;
                break;
            }
            ++n7;
        }
        if (n5 == -1) {
            return;
        }
        q.do[n5] = string;
        q.dp[n5] = n3;
        q.dq[n5] = n4;
        q.dr[n5] = 0;
        q.ds[n5] = n6;
        q.dt[n5] = 0;
        q.du[n5] = n2;
    }

    public static void c(int n2, int n3) {
        dx = 1;
        dv = n2;
        dw = n3;
    }

    public static void a(String string) {
        cX = string;
        cW = 0;
        cV = true;
    }

    public static final boolean b(int n2, int n3, int n4) {
        int n5;
        int n6 = n5 = cL[0] == -1 ? 0 : 1;
        if (cL[n5] != -1) {
            return false;
        }
        q.cL[n5] = 0;
        q.cN[n5] = n4;
        q.cJ[n5] = n2;
        q.cK[n5] = n3;
        return true;
    }

    public static final void c(int n2, int n3, int n4, int n5) {
        eJ = n5;
        if (eJ == 0) {
            eI = 4;
            eE = new int[4];
            eF = new int[eI];
            eG = new int[eI];
            eH = new int[eI];
            q.eG[0] = 0;
            q.eH[0] = n4;
            q.eE[0] = n2;
            q.eF[0] = n3;
            n5 = 1;
            while (n5 < eI) {
                q.eG[n5] = 0;
                q.eH[n5] = n4;
                q.eE[n5] = eE[n5 - 1] + n4 * 32;
                q.eF[n5] = n3;
                ++n5;
            }
            return;
        }
        eI = 18;
        eE = new int[18];
        eF = new int[eI];
        eG = new int[eI];
        n5 = 1;
        while (n5 < eI) {
            q.eG[n5] = 0;
            q.eE[n5] = n2;
            q.eF[n5] = n3 - n5 * 24;
            ++n5;
        }
    }

    public static void f(int n2) {
        aI = 0;
        aG = n2;
        aH = true;
    }

    public static final boolean d(int n2, int n3) {
        int n4;
        if (a) {
            return false;
        }
        int n5 = n4 = cQ[0] == -1 ? 0 : 1;
        if (cQ[n4] != -1) {
            return false;
        }
        q.cQ[n4] = 0;
        q.cO[n4] = n2;
        q.cP[n4] = n3;
        return true;
    }

    private static void L() {
        int n2 = h.ak == 8 ? h.ap / 3 : h.ap;
        int n3 = 0;
        while (n3 < cC) {
            if (cF[n3] == 1) {
                int n4 = n3;
                cD[n4] = cD[n4] + cG[n3];
                if (cD[n3] < ax || cD[n3] > ax + S || cE[n3] < ay || cE[n3] > ay + T) {
                    q.cF[n3] = -1;
                } else if (q.a(h.ag - cD[n3]) < h.ao && q.a(h.ah - n2 - cE[n3]) < n2) {
                    if (h.ak == 5 && h.ae == 0 && cI[n3] < 3) {
                        q.cG[n3] = -cG[n3];
                        int n5 = n3;
                        cD[n5] = cD[n5] + cG[n3];
                        return;
                    }
                    h.a(cH[n3], cD[n3] < h.ag ? 1 : -1);
                    q.cF[n3] = -1;
                    return;
                }
            }
            ++n3;
        }
    }

    private static void M() {
        int n2 = 0;
        while (n2 < aJ) {
            switch (aN[n2]) {
                case 2: {
                    int n3 = n2;
                    aL[n3] = aL[n3] + aO[n2];
                    if (aO[n2] > 0) {
                        int n4 = n2;
                        aO[n4] = aO[n4] - 1;
                    } else if (aO[n2] < 0) {
                        int n5 = n2;
                        aO[n5] = aO[n5] + 1;
                    } else {
                        q.aN[n2] = 1;
                    }
                    if (!k.a(aL[n2] - 12, aM[n2] + 1, 2) && !k.a(aL[n2] + 12, aM[n2] + 1, 2)) {
                        q.aN[n2] = 4;
                        q.aP[n2] = 2;
                    }
                    if (!k.a(aL[n2] + 12, aM[n2] - 2, 4) && !k.a(aL[n2] - 12, aM[n2] - 2, 8)) break;
                    q.aL[n2] = k.e(aL[n2]) + 12;
                    break;
                }
                case 4: {
                    int n6 = n2;
                    aM[n6] = aM[n6] + aP[n2];
                    if (aP[n2] < 20) {
                        int n7 = n2;
                        aP[n7] = aP[n7] + 1;
                    }
                    if (k.a(aL[n2] + 12, aM[n2], 2) || k.a(aL[n2] - 12, aM[n2], 2)) {
                        q.aM[n2] = k.d(aM[n2]);
                        q.aP[n2] = 0;
                        q.aN[n2] = 1;
                        break;
                    }
                    if (!k.a(aL[n2], aM[n2] - k.f, 64)) break;
                    q.aM[n2] = k.d(aM[n2]) + 1;
                    q.d(aL[n2], aM[n2] - 13);
                    q.d(aL[n2] + 1, aM[n2] - 10);
                    q.aP[n2] = 1;
                    q.dY[n2] = -1;
                    q.dZ[n2] = 1;
                    q.aN[n2] = 11;
                    break;
                }
                case 11: {
                    if (aq % 2 != 0) break;
                    int n8 = n2;
                    aM[n8] = aM[n8] + dY[n2];
                    int n9 = n2;
                    aP[n9] = aP[n9] + dY[n2];
                    if (aP[n2] >= 1) {
                        q.dY[n2] = -1;
                    }
                    if (aP[n2] <= -1) {
                        q.dY[n2] = 1;
                    }
                    int n10 = n2;
                    aL[n10] = aL[n10] + dZ[n2];
                    if (n2 == aK) {
                        h.ah = aM[aK] - 24;
                        h.ag += dZ[n2];
                    }
                    if (!k.a(aL[n2] + 12, aM[n2], 4) && !k.a(aL[n2] - 12, aM[n2], 8)) break;
                    q.dZ[n2] = -dZ[n2];
                }
            }
            ++n2;
        }
    }

    public static void u() {
        if (h.aQ) {
            n = 0;
        }
    }

    private static final void N() {
        if (ax != av || ay != aw) {
            dM = av - ax << 2;
            dN = aw - ay << 2;
            dO &= 0xF;
            ay += (dP += dN) >> 4;
            dP &= 0xF;
            if ((ax += (dO += dM) >> 4) < 0) {
                ax = 0;
            }
            if (ax > dQ) {
                ax = dQ;
            }
            if (ay < 0) {
                ay = 0;
            }
            if (ay > dR) {
                ay = dR;
            }
        }
        if (n >= 0) {
            ax += bY[n];
            ay += bZ[n];
            if (++n == 5) {
                n = -1;
            }
            if (ax < 0) {
                ax = 0;
            }
            if (ax > dQ) {
                ax = dQ;
            }
            if (ay < 0) {
                ay = 0;
            }
            if (ay > dR) {
                ay = dR;
            }
        }
        if ((N = ax / k.f - 1) < 0) {
            N = 0;
        }
        O = ay / k.f;
        P = N + cA;
        Q = O + cB;
        if (O < 0) {
            O = 0;
        }
        if (Q > k.b - 1) {
            Q = k.b - 1;
        }
    }

    private static boolean O() {
        if (!az) {
            if (cy > 1) {
                cy >>= 1;
                A = true;
            }
            return false;
        }
        if (dV) {
            if (u[5]) {
                q.c();
                if (aQ) {
                    q.K();
                    if (ec == eb) {
                        aQ = false;
                        dV = false;
                        q.F();
                    }
                }
            }
        } else {
            if (cy < cx) {
                cy += (cx - cy >> 1) + 1;
            }
            if (++dU >= bP[aA][aB]) {
                dU = bP[aA][aB];
                block1 : switch (bM[aA][aB]) {
                    case 3: {
                        az = false;
                        A = true;
                        q.i[q.aA] = true;
                        if (bo > 0) {
                            h.k = true;
                        }
                        if (bn > 0) {
                            h.i = true;
                        }
                        if (aG == 1 || aG == 16) {
                            c.h = 1;
                        }
                        if (aA == 3) {
                            q.f(4);
                        }
                        if (!a.n) {
                            a.n = true;
                        }
                        if (aA == 7) {
                            q.fI[3] = 148;
                            q.fJ[3] = 10;
                        }
                        if (aA == 9 && !i[11] && bA[0] != 10) {
                            q.f(10);
                        }
                        if (aA == 11) {
                            q.f(12);
                        }
                        if (aA == 20) {
                            q.f(21);
                            q.af();
                            B = 18;
                            n.o = true;
                        }
                        if (aA == 21) {
                            q.af();
                            B = 18;
                            n.o = true;
                        }
                        if (aA == 22) {
                            q.f(21);
                        }
                        if (aA == 24) {
                            eI = 0;
                            if (p.e) {
                                q.f(26);
                            } else {
                                q.f(25);
                            }
                        }
                        if (aA == 28) {
                            h.aN = 0;
                            h.az += h.c[++h.aM - 2];
                            h.aw += h.a[h.aM - 2];
                            h.ax += 2;
                            h.aH += 2;
                            h.aI += 2;
                            h.aJ += 2;
                            h.ay = h.az;
                            h.aA = h.aB += h.d[h.aM - 2];
                            q.g(13);
                            B = 7;
                            dx = 0;
                            q.au[q.eu] = 0;
                        }
                        if (aA >= 33) {
                            i.e();
                            h.ah = k.d(h.ah);
                            if (!k.a(h.ag, h.ah, 2)) {
                                h.ak = 4;
                            }
                        }
                        if (aA == 32) {
                            i.O = 9000;
                            i.N = 9000;
                            i.S = 0;
                            i.K = 7;
                        }
                        if (aA == 42) {
                            h.d(500);
                        }
                        q.W();
                        ++aB;
                        break;
                    }
                    case 9: {
                        ax = bQ[bO[aA][aB]][0] - U;
                        ay = bQ[bO[aA][aB]][1] - V;
                        if (ax < 0) {
                            ax = 0;
                        }
                        if (ax > dQ) {
                            ax = dQ;
                        }
                        if (ay < 0) {
                            ay = 0;
                        }
                        if (ay > dR) {
                            ay = dR;
                        }
                        av = ax;
                        aw = ay;
                        ++aB;
                        break;
                    }
                    case 10: {
                        av = bQ[bO[aA][aB]][0] - U;
                        aw = bQ[bO[aA][aB]][1] - V;
                        if (av < 0) {
                            av = 0;
                        }
                        if (av > dQ) {
                            av = dQ;
                        }
                        if (aw < 0) {
                            aw = 0;
                        }
                        if (aw > dR) {
                            aw = dR;
                        }
                        ++aB;
                        break;
                    }
                    case 8: {
                        h.aO = false;
                        ++aB;
                        break;
                    }
                    case 14: {
                        av = ax += 2;
                        if (ax <= bQ[bO[aA][aB]][0] - U) break;
                        ++aB;
                        break;
                    }
                    case 34: {
                        av = ax -= 2;
                        if (ax >= bQ[bO[aA][aB]][0] - U) break;
                        ++aB;
                        break;
                    }
                    case 4: {
                        if (h.ak != 1 && h.ak != 18) break;
                        dS = false;
                        int n2 = 0;
                        int n3 = 0;
                        switch (bS[bO[aA][aB]]) {
                            case 0: {
                                n2 = h.ag;
                                n3 = h.ah;
                                break;
                            }
                            case 1: {
                                n2 = c.b[bT[bO[aA][aB]]];
                                n3 = c.c[bT[bO[aA][aB]]];
                                break;
                            }
                            case 2: {
                                n2 = h.x[bT[bO[aA][aB]]];
                                n3 = h.y[bT[bO[aA][aB]]];
                                break;
                            }
                            case 4: {
                                n2 = h.bo;
                                n3 = h.bp;
                                break;
                            }
                            case 3: {
                                n2 = h.F;
                                n3 = h.G;
                                break;
                            }
                            case 5: {
                                n2 = a.e[38];
                                n3 = a.f[38];
                                break;
                            }
                            case 6: {
                                n2 = i.H;
                                n3 = i.I - 20;
                                break;
                            }
                            case 7: {
                                n2 = i.m + i.s * 22;
                                n3 = i.n - 9;
                                break;
                            }
                            case 8: {
                                n2 = i.m + i.s * 25;
                                n3 = i.n + 3;
                                break;
                            }
                            case 9: {
                                n2 = i.m + i.s * 10;
                                n3 = i.n + 3;
                            }
                        }
                        q.a(m.c[bU[bO[aA][aB]]], bN[aA][aB], n2, n3 -= 40);
                        if (bN[aA][aB] == 1) {
                            c.h = 1;
                        }
                        ++aB;
                        dV = true;
                        break;
                    }
                    case 39: {
                        h.u = false;
                        ++aB;
                        break;
                    }
                    case 24: {
                        q.bA[0] = -1;
                        h.as = 1;
                        a.n = false;
                        o.b.addElement(new j(a.e[38], a.f[38] - 40, 22));
                        ++aB;
                        break;
                    }
                    case 40: {
                        ++aB;
                        break;
                    }
                    case 25: {
                        q.b();
                        q.c();
                        h.m = true;
                        o.b.addElement(new j(h.ag, h.ah - 15, 26));
                        h.ag = bQ[bO[aA][aB]][0];
                        h.ah = bQ[bO[aA][aB]][1];
                        System.out.println("onda_bien " + bO[aA][aB]);
                        System.out.println("x " + h.ag);
                        System.out.println("y " + h.ah);
                        h.as = -h.as;
                        ++aB;
                        break;
                    }
                    case 22: {
                        int n4 = 0;
                        while (n4 < h.s) {
                            if (q.a(h.x[n4] - bO[aA][aB]) < 24) {
                                h.u = true;
                                ++aB;
                                break block1;
                            }
                            ++n4;
                        }
                        break;
                    }
                    case 16: {
                        h.v[q.bO[q.aA][q.aB]] = true;
                        h.w = h.x[bO[aA][aB]] + 200;
                        ++aB;
                        break;
                    }
                    case 21: {
                        h.x[0] = bO[aA][aB];
                        ++aB;
                        break;
                    }
                    case 17: {
                        if (b) {
                            b = false;
                        }
                        q.i[q.aA] = true;
                        q.H();
                        Midlet.f = true;
                        q.c(bO[aA][aB]);
                        return true;
                    }
                    case 7: {
                        h.aO = true;
                        ++aB;
                        break;
                    }
                    case 1: {
                        fa = true;
                        o.h(bO[aA][aB]);
                        if (bO[aA][aB] == 53 && i.a && aA > 51 && aA < 68) {
                            i.t = 6;
                        }
                        ++aB;
                        break;
                    }
                    case 2: {
                        q.i(bO[aA][aB]);
                        ++aB;
                        break;
                    }
                    case 35: {
                        l = true;
                        ++aB;
                        break;
                    }
                    case 36: {
                        if (aG == 0) {
                            o.a.addElement(new j(300, 204, 21));
                        }
                        l = false;
                        ++aB;
                        break;
                    }
                    case 37: {
                        c.b[q.bR[q.bO[q.aA][q.aB]][0]] = bR[bO[aA][aB]][1];
                        c.c[q.bR[q.bO[q.aA][q.aB]][0]] = bR[bO[aA][aB]][2];
                        ++aB;
                        break;
                    }
                    case 32: {
                        h.ac = bO[aA][aB];
                        h.C = false;
                        h.J = 2;
                        h.R = 1;
                        h.H = 5 * h.R;
                        if (h.F < P * 24 - 20) break;
                        h.E = -1;
                        ++aB;
                        break;
                    }
                    case 38: {
                        h.R = bO[aA][aB];
                        ++aB;
                        break;
                    }
                    case 19: {
                        h.C = true;
                        ++aB;
                        break;
                    }
                    case 20: {
                        h.C = false;
                        ++aB;
                        break;
                    }
                    case 41: {
                        int n5 = 0;
                        while (n5 < 30) {
                            o.a.addElement(new j(q.b(744, 778), q.b(816, 864), 24));
                            ++n5;
                        }
                        ++aB;
                        break;
                    }
                    case 42: {
                        h.as = 1;
                        int n6 = 0;
                        while (n6 < 30) {
                            ((j)o.a.elementAt((int)(o.a.size() - 1 - n6))).b = true;
                            ++n6;
                        }
                        o.a.addElement(new j(h.bo, h.bp - 20, 21));
                        h.bn = -1;
                        n.a(2, h.bo, h.bp);
                        n.g[0] = 6;
                        n.h[0] = -6;
                        n.k[0] = 2;
                        ++aB;
                        break;
                    }
                    case 43: {
                        h.bL = 10000;
                        ++aB;
                        break;
                    }
                    case 44: {
                        h.bL = 0;
                        h.bs = 1;
                        ++aB;
                        break;
                    }
                    case 45: {
                        i.E = false;
                        h.aO = false;
                        av = bQ[bO[aA][aB]][0] - U;
                        aw = bQ[bO[aA][aB]][1] - V;
                        if (av < 0) {
                            av = 0;
                        }
                        if (av > dQ) {
                            av = dQ;
                        }
                        if (aw < 0) {
                            aw = 0;
                        }
                        if (aw > dR) {
                            aw = dR;
                        }
                        ++aB;
                        break;
                    }
                    case 46: {
                        if (h.bo > 2808) {
                            h.bs = 2;
                            h.bA = -1;
                            h.bq = -3;
                        }
                        if (h.bo == 2826) {
                            o.a.addElement(new j(2760, 264, 21));
                        }
                        if (h.bo < 2808 && !i.E) {
                            h.bo = 2808;
                            h.bs = 1;
                        }
                        if (!i.E) break;
                        i.J = i.R[i.S];
                        h.bs = 8;
                        if (++i.S < i.R.length - 11 && aq % 3 == 0) {
                            --i.I;
                        }
                        if (i.S != i.R.length - 11) break;
                        i.K = 2;
                        ++aB;
                        break;
                    }
                    case 47: {
                        i.K = -1;
                        i.J = i.R[++i.S];
                        if (i.S != i.R.length - 1) break;
                        h.bs = 1;
                        o.a.addElement(new j(2760, 240, 21));
                        o.a.addElement(new j(2808, 238, 21));
                        i.E = false;
                        h.bn = -1;
                        ++aB;
                        break;
                    }
                    case 48: {
                        if (c.i == 1) {
                            o.a.addElement(new j(c.j, c.k - 15, 25));
                        }
                        if (++c.i == 20) {
                            c.a(m.f[14], c.j, c.k - 40);
                        }
                        if (c.i < 85) break;
                        c.l = 2;
                        ++aB;
                        break;
                    }
                    case 49: {
                        q.a(h.ag + h.as * 20, 312);
                        q.c(h.ag + h.as * 20, 307);
                        ++aB;
                        break;
                    }
                    case 50: {
                        h.bm = 1;
                        ++aB;
                        break;
                    }
                    case 51: {
                        if (ap == 14) {
                            c.a = 0;
                            o.a.addElement(new j(c.b[0], c.c[0] - 20, 21));
                            ++aB;
                            break;
                        }
                        h.bn = -1;
                        o.a.addElement(new j(h.bo, h.bp - 20, 21));
                        q.bA[1] = 1;
                        h.bm = 0;
                        ++aB;
                        break;
                    }
                    case 52: {
                        if (q.a(i.m - 288) < 12) {
                            i.m = 288;
                        }
                        if (i.m < 288) {
                            i.u = i.u == 3 ? 4 : 3;
                            i.s = 1;
                            i.m += 7;
                        }
                        if (i.m > 288) {
                            i.u = i.u == 3 ? 4 : 3;
                            i.s = -1;
                            i.m -= 7;
                        }
                        if (i.m != 288) break;
                        if (i.m > h.ag + 20) {
                            h.as = 1;
                            h.ai = 5;
                            h.ak = 2;
                        }
                        if (i.m < h.ag - 20) {
                            h.as = -1;
                            h.ai = -5;
                            h.ak = 2;
                        }
                        if (h.ah < 672) {
                            h.aj = 5;
                            h.ak = 4;
                        }
                        if (q.a(672 - i.n) < 5) {
                            i.n = 672;
                        }
                        if (i.n < 672) {
                            i.n += 5;
                        }
                        if (q.a(h.ag - i.m) >= 50 || i.n != 672) break;
                        h.ak = 1;
                        h.as = h.ag > i.m ? -1 : 1;
                        q.bQ[4][0] = i.m + h.as * 35;
                        q.bQ[5][0] = i.m - h.as * 25;
                        ++aB;
                        break;
                    }
                    case 53: {
                        if (h.ah > 600) {
                            if (h.ag < 3024) {
                                h.ak = 2;
                                h.ai = 5;
                                h.as = 1;
                            } else {
                                h.ak = 3;
                                h.aj = -7;
                                h.at = 0;
                                h.ai = 0;
                            }
                        }
                        if (h.ah <= 552) {
                            if (k.a(h.ag, h.ah, 2)) {
                                h.as = 1;
                                h.ak = 2;
                                h.ai = 5;
                            } else {
                                h.ak = 4;
                                h.aj = 5;
                            }
                        } else if (h.ah < 600) {
                            h.as = 1;
                            h.ai = 4;
                        }
                        if (q.a(i.n - 576) < 10) {
                            i.n = 576;
                        }
                        if (i.n < 576) {
                            i.n += 2;
                        }
                        if (i.n > 576) {
                            i.n -= 2;
                        }
                        if (h.ah != 600 || q.a(h.ag - 3072) >= 24 || i.n != 576) break;
                        h.as = -1;
                        h.g = false;
                        h.h = false;
                        h.ae = 1;
                        h.as = h.ag > i.m ? -1 : 1;
                        ++aB;
                        break;
                    }
                    case 54: {
                        if (q.a(h.ag - f.b) < 20 && q.a(f.c - h.ah) < 10) {
                            f.c = 312;
                            f.j = 3;
                            h.ak = 1;
                            h.as = h.ag > f.b ? -1 : 1;
                            int n7 = h.as == 1 ? 54 : 52;
                            q.bO[q.aA][4] = n7;
                            q.bO[q.aA][3] = n7;
                            int n8 = bO[aA][3] == 54 ? 52 : 54;
                            q.bO[q.aA][16] = n8;
                            q.bO[q.aA][17] = n8;
                            f.d = 7;
                            f.e = -12;
                            ++aB;
                        }
                        if (h.ah < 312) {
                            h.aj = 5;
                            h.ak = 4;
                            break;
                        }
                        if (f.b > h.ag + 10) {
                            h.as = 1;
                            h.ai = 5;
                            h.ak = 2;
                            break;
                        }
                        if (f.b >= h.ag - 10) break;
                        h.as = -1;
                        h.ai = -5;
                        h.ak = 2;
                        break;
                    }
                    case 55: {
                        if (i.n == 432) {
                            h.as = -1;
                            h.ae = 0;
                            h.g = false;
                            h.h = false;
                            ++aB;
                            h.as = h.ag > 4920 ? -1 : 1;
                            int n9 = i.m;
                            q.bQ[17][0] = n9;
                            q.bQ[16][0] = n9;
                            q.bQ[16][1] = 288;
                            q.bQ[17][1] = 240;
                            h.ak = 4;
                        }
                        if (q.a(432 - i.n) < 10) {
                            i.n = 432;
                        }
                        if (i.n > 432) {
                            i.n -= 4;
                        }
                        if (i.n < 432) {
                            i.n += 4;
                        }
                        if (h.ag > 4920) {
                            i.t = 2;
                            i.s = 1;
                            break;
                        }
                        i.t = 2;
                        i.s = -1;
                        break;
                    }
                    case 56: {
                        if (q.a(h.ag - i.m) < 80 && q.a(i.n - h.ah) < 10) {
                            i.t = 3;
                            h.av = 0;
                            h.au = 0;
                            h.at = 0;
                            h.ak = 1;
                            h.as = h.ag > i.m ? -1 : 1;
                            h.ae = 1;
                            h.e();
                            q.b();
                            q.c();
                            ++aB;
                        } else if (i.m > h.ag + 20) {
                            h.as = 1;
                            h.ai = 5;
                            h.ak = 2;
                        } else if (i.m < h.ag - 20) {
                            h.as = -1;
                            h.ai = -5;
                            h.ak = 2;
                        }
                        if (i.n >= h.ah) break;
                        h.ak = 3;
                        h.aj = -5;
                        h.at = 0;
                        break;
                    }
                    case 57: {
                        if (aq % 2 == 0) {
                            if (i.u == 0) {
                                i.u = 1;
                            } else if (i.u == 1) {
                                i.u = 2;
                            } else if (i.u == 2) {
                                i.u = 0;
                            }
                        }
                        if (q.a(h.ah - 70 - i.n) < 10) {
                            i.n = h.ah - 70;
                        }
                        if (i.n > h.ah - 70) {
                            i.n -= 3;
                        }
                        if (i.n < h.ah - 70) {
                            i.n += 3;
                        }
                        if (q.a(h.ag + h.as * 70 - i.m) < 10) {
                            i.m = h.ag + h.as * 70;
                        }
                        if (i.m > h.ag + h.as * 70) {
                            i.s = -1;
                            i.m -= 3;
                        }
                        if (i.m < h.ag + h.as * 70) {
                            i.s = 1;
                            i.m += 3;
                        }
                        if (h.ag + h.as * 70 != i.m || h.ah - 70 != i.n) break;
                        if (h.as == 1) {
                            q.bO[q.aA][20] = 51;
                            q.bO[q.aA][19] = 51;
                        }
                        h.g = true;
                        h.h = false;
                        q.b();
                        q.c();
                        i.t = 3;
                        ++aB;
                        break;
                    }
                    case 58: {
                        if (!k.a(h.bo, h.bp, 2)) {
                            h.bs = 4;
                            h.bq = 2 * h.bA;
                        } else if (h.bp < 288) {
                            h.bs = 2;
                            h.bq = 5;
                            h.bA = 1;
                        } else if (q.a(h.bo - 1416) < 15) {
                            h.bo = 1416;
                            h.bs = 1;
                            h.bA = -1;
                        } else if (h.bo < 1416) {
                            h.bs = 2;
                            h.bq = 5;
                            h.bA = 1;
                        } else if (h.bo > 1416) {
                            h.bs = 2;
                            h.bq = -5;
                            h.bA = -1;
                        }
                        if (!k.a(h.ag, h.ah, 2)) {
                            h.ak = 4;
                            h.ai = 2 * h.as;
                        } else if (h.ah < 288) {
                            h.ak = 2;
                            h.ai = -5;
                            h.as = -1;
                        } else if (q.a(1344 - h.ag) < 15) {
                            h.ag = 1344;
                            h.as = 1;
                            h.ak = 1;
                        } else if (h.ag > 1344) {
                            h.ak = 2;
                            h.as = -1;
                            h.ai = -5;
                        } else if (h.ag < 1344) {
                            h.ak = 2;
                            h.as = 1;
                            h.ai = 5;
                        }
                        if (h.bp != 288 || h.ah != 288 || h.ag != 1344 || h.bo != 1416) break;
                        q.b();
                        q.c();
                        h.bA = -1;
                        h.as = 1;
                        h.ae = 0;
                        ++aB;
                        break;
                    }
                    case 59: {
                        if (q.a(i.I - 336) < 10 && i.H >= 168 && i.H <= 528) {
                            i.I = 336;
                            ++aB;
                            q.b();
                            q.c();
                            h.g = false;
                            h.h = false;
                            h.ae = 0;
                            int n10 = i.H;
                            q.bQ[13][0] = n10;
                            q.bQ[12][0] = n10;
                            q.bQ[11][0] = n10;
                            q.bQ[11][1] = i.I - 10;
                            q.bQ[12][1] = i.I - 100;
                            q.bQ[13][1] = i.I;
                            q.bQ[14][0] = i.H - 80;
                            q.bQ[14][1] = 384;
                        }
                        if (q.a(i.I - 336) < 10) {
                            i.I = 336;
                        }
                        if (i.I > 336) {
                            i.I -= 4;
                        }
                        if (i.I < 336) {
                            i.I += 4;
                        }
                        if (i.H < 168) {
                            i.H += 4;
                        }
                        if (i.H <= 528) break;
                        i.H -= 4;
                        break;
                    }
                    case 60: {
                        if (q.a(d.n[d.k] - h.ag) == 50) {
                            h.as = h.ag > d.n[d.k] ? -1 : 1;
                            h.g = false;
                            h.h = false;
                            q.bQ[10][0] = d.n[d.k];
                            q.bQ[10][1] = d.o[d.k] - 15;
                            q.bQ[18][0] = d.n[d.k];
                            q.bQ[18][1] = d.o[d.k];
                            q.bQ[19][0] = d.n[d.k];
                            q.bQ[19][1] = d.o[d.k] - 15;
                            ++aB;
                        }
                        if (h.ag >= d.n[d.k]) {
                            if (q.a(d.n[d.k] + 50 - h.ag) <= 10) {
                                h.ag = d.n[d.k] + 50;
                            }
                            if (h.ag > d.n[d.k] + 50) {
                                h.ai = -5;
                                h.as = -1;
                                h.ak = 2;
                            }
                            if (h.ag < d.n[d.k] + 50) {
                                h.ai = 5;
                                h.as = 1;
                                h.ak = 2;
                            }
                        }
                        if (h.ag >= d.n[d.k]) break;
                        if (q.a(d.n[d.k] - 50 - h.ag) <= 10) {
                            h.ag = d.n[d.k] - 50;
                        }
                        if (h.ag > d.n[d.k] - 50) {
                            h.ai = -5;
                            h.as = -1;
                            h.ak = 2;
                        }
                        if (h.ag >= d.n[d.k] - 50) break;
                        h.ai = 5;
                        h.as = 1;
                        h.ak = 2;
                        break;
                    }
                    case 61: {
                        i.H = h.ag + h.as * 70;
                        i.I = 384;
                        i.L = -h.as;
                        i.K = 2;
                        o.a.addElement(new j(i.H, i.I - 16, 21));
                        ++aB;
                        break;
                    }
                    case 62: {
                        i.l = q.b(1, 10);
                        i.B = 0;
                        o.a.addElement(new j(i.m, i.n - 67, 20));
                        ++aB;
                        break;
                    }
                    case 63: {
                        aq = 0;
                        ++aB;
                        break;
                    }
                    case 64: {
                        ++aB;
                        break;
                    }
                    case 65: {
                        switch (bO[aA][aB]) {
                            case 0: {
                                h.h = false;
                                h.g = false;
                                h.ae = 0;
                                break;
                            }
                            case 1: {
                                h.h = false;
                                h.g = false;
                                h.ae = 1;
                                break;
                            }
                            case 2: {
                                h.h = false;
                                h.g = true;
                                h.ae = 0;
                                h.af = 5;
                                break;
                            }
                            case 3: {
                                h.g = false;
                                h.h = true;
                                h.ae = 0;
                                h.af = 4;
                                break;
                            }
                            case 4: {
                                h.f = true;
                            }
                        }
                        h.e();
                        q.j();
                        ++aB;
                        break;
                    }
                    case 66: {
                        if (aq % 5 == 0 && ++i.u > 1) {
                            i.u = 0;
                        }
                        if ((i.m += 5) >= i.p) {
                            i.m = i.p;
                            ++aB;
                        }
                        if (i.m <= h.ag + 30) break;
                        i.m = h.ag + 30;
                        ++aB;
                        break;
                    }
                    case 67: {
                        if (f.d > 4) {
                            --f.d;
                        }
                        f.k = 5;
                        if ((f.c += ++f.e) <= 264 || !k.a(f.b -= f.d * h.as, f.c, 2)) break;
                        f.c = k.d(f.c);
                        ++aB;
                        break;
                    }
                    case 68: {
                        int n11 = h.as;
                        int n12 = h.ah - 15;
                        int n13 = h.ag + h.as * 20;
                        et = 1;
                        eq = 1;
                        eo = n13;
                        ep = n12;
                        es = n11;
                        er = 0;
                        ++aB;
                        break;
                    }
                    case 69: {
                        if (aq % 2 == 0) {
                            i.u = i.u == 0 ? 1 : 0;
                        }
                        i.m += h.as << 1;
                        if (h.ah - (i.n -= 4) < 70) break;
                        i.t = 3;
                        ++aB;
                        break;
                    }
                    case 70: {
                        if (i.n >= 600) {
                            ++aB;
                        }
                        if (++i.v > 5) {
                            i.v = 0;
                        }
                        if (i.v > 3) {
                            i.u = 2;
                            i.n += 4;
                            break;
                        }
                        i.u = 3;
                        break;
                    }
                    case 71: {
                        if (k.a(i.H, i.I, 2)) {
                            n = 0;
                            ++aB;
                            i.I = k.d(i.I);
                        }
                        i.J = 5;
                        i.I += 7;
                        break;
                    }
                    case 72: {
                        i.u = 3;
                        i.o = -15;
                        i.t = 6;
                        ++aB;
                        break;
                    }
                    case 73: {
                        q.a(m.a, 14, x, y + 50);
                        dV = true;
                        try {
                            RecordStore.deleteRecordStore((String)"saved");
                            RecordStore.deleteRecordStore((String)"ninjasave");
                        }
                        catch (RecordStoreNotFoundException recordStoreNotFoundException) {
                            RecordStoreNotFoundException recordStoreNotFoundException2 = recordStoreNotFoundException;
                            recordStoreNotFoundException.printStackTrace();
                        }
                        catch (RecordStoreException recordStoreException) {
                            RecordStoreException recordStoreException2 = recordStoreException;
                            recordStoreException.printStackTrace();
                        }
                        Midlet.c = true;
                        cb = 0;
                        ++aB;
                        break;
                    }
                    case 74: {
                        q.c();
                        q.b();
                        q.a(m.b[0], 16, x, y + 50);
                        dV = true;
                        ++aB;
                        break;
                    }
                    case 75: {
                        q.c();
                        q.b();
                        q.a(m.b[1], 16, x, y + 50);
                        dV = true;
                        ++aB;
                        break;
                    }
                    case 76: {
                        q.c();
                        q.b();
                        q.a(m.b[2], 16, x, y + 50);
                        dV = true;
                        ++aB;
                        break;
                    }
                    case 29: {
                        q.c();
                        q.b();
                        aX = false;
                        k.b();
                        q.G();
                        c.a();
                        dB = null;
                        dD = null;
                        System.gc();
                        a.a();
                        h.f();
                        dy = null;
                        dE = null;
                        dF = null;
                        dG = null;
                        dI = null;
                        dL = null;
                        dz = null;
                        System.gc();
                        h.g();
                        n.a();
                        q.J();
                        return true;
                    }
                }
            }
        }
        if (!i.b) {
            q.t[5] = false;
            q.u[5] = false;
        }
        return false;
    }

    private static void P() {
        int n2 = 0;
        while (n2 < af) {
            if (ai[n2] != 0) {
                if (q.a(h.ah - (h.an >> 1) - (ah[n2] - 7)) < 10 && q.a(h.ag - ag[n2]) < 10) {
                    q.e(2, 0);
                    ae += dm[n2];
                    A = true;
                    q.ai[n2] = 301;
                    q.a("+" + dm[n2], 1, ag[n2], ah[n2] - 15, 0, -1);
                }
                if (n.l && q.a(n.b - (ah[n2] - 7)) < 10 && q.a(n.a - ag[n2]) < 10) {
                    q.e(2, 0);
                    ae += dm[n2];
                    A = true;
                    q.ai[n2] = 301;
                    q.a("+" + dm[n2], 1, ag[n2], ah[n2] - 15, 0, -1);
                }
                int n3 = n2;
                ag[n3] = ag[n3] + dj[n2];
                int n4 = n2;
                ah[n4] = ah[n4] + dk[n2];
                if (dj[n2] > 1) {
                    int n5 = n2;
                    dj[n5] = dj[n5] - 1;
                }
                if (dj[n2] < -1) {
                    int n6 = n2;
                    dj[n6] = dj[n6] + 1;
                }
                if (dk[n2] < 20) {
                    int n7 = n2;
                    dk[n7] = dk[n7] + 1;
                }
                int n8 = n2;
                ai[n8] = ai[n8] + 1;
                if (ai[n2] > 200) {
                    q.ai[n2] = 0;
                }
                q.dl[n2] = ai[n2] % 20 > 18 ? 0 : (ai[n2] % 20 > 16 ? 1 : 2);
                if (ah[n2] < 0 || ah[n2] >= k.d || ag[n2] >= k.c || ag[n2] < 0) {
                    q.ai[n2] = 0;
                } else if (dk[n2] > 2 && (k.c(ag[n2], ah[n2]) & 2) == 2) {
                    q.ah[n2] = k.d(ah[n2]);
                    if (dk[n2] > 2) {
                        q.dk[n2] = -(dk[n2] >> 1);
                    } else {
                        q.dj[n2] = 0;
                        q.dk[n2] = 0;
                    }
                }
            }
            ++n2;
        }
    }

    private static void Q() {
        if (aQ && ei < ee[ec].length && ++eh >= ee[ec][ei].length()) {
            eh = 0;
            ++ei;
        }
    }

    private static void R() {
        if (a || ap == 9 || ap == 10 || ap == 8 || ap == 14) {
            return;
        }
        int n2 = 0;
        while (n2 < 2) {
            if (cU[n2] != -1) {
                int n3 = n2;
                cU[n3] = cU[n3] + 1;
                if (cU[n2] >= 5) {
                    q.cU[n2] = -1;
                }
                if (n2 == 0) {
                    int n4 = n2;
                    cS[n4] = cS[n4] - 1;
                } else {
                    int n5 = n2;
                    cS[n5] = cS[n5] + 1;
                }
                int n6 = n2;
                cT[n6] = cT[n6] - 1;
            }
            ++n2;
        }
    }

    private static void S() {
        int n2 = 0;
        while (n2 < ex) {
            if (eC[n2] == 1) {
                int n3 = n2;
                ey[n3] = ey[n3] + eA[n2];
                int n4 = n2;
                ez[n4] = ez[n4] + eB[n2];
                if (ey[n2] < ax || ey[n2] > ax + S || ez[n2] < ay || ez[n2] > ay + T) {
                    q.eC[n2] = -1;
                } else {
                    if (ap == 5) {
                        if (eA[n2] > 0 && (k.c(ey[n2], ez[n2]) & 4) == 4) {
                            q.eC[n2] = 2;
                            q.ey[n2] = k.e(ey[n2]);
                        } else if (eA[n2] < 0 && (k.c(ey[n2], ez[n2]) & 8) == 8) {
                            q.eC[n2] = 2;
                            q.ey[n2] = k.e(ey[n2]) + k.f;
                        }
                    }
                    if (eA[n2] != 0 && q.a(h.ag - ey[n2]) < h.ao && q.a(h.ah - h.ap - ez[n2]) < h.ap) {
                        h.a(a.a[9], -h.as);
                        q.eC[n2] = -1;
                        return;
                    }
                }
            } else if (ap == 5 && eC[n2] > 1) {
                int n5 = n2;
                eC[n5] = eC[n5] + 1;
                if (eC[n2] >= 10) {
                    q.eC[n2] = 1;
                    q.eB[n2] = 12;
                    q.eA[n2] = 0;
                }
            }
            ++n2;
        }
    }

    private static void T() {
        int n2;
        if (eL == 2 && ap == 8 && bA[0] == -1 && bb[0] != 0 && aY[0] >= 696) {
            n2 = 0;
            while (n2 < 3) {
                q.bb[n2] = -1;
                ++n2;
            }
        }
        if (bb[0] != 0) {
            aY[0] = aY[0] + eK[0] * bb[0];
            aZ[0] = aZ[0] + ba[0];
            if (eV != -1) {
                if (ba[0] >= 0) {
                    a.e[q.eV] = aY[0];
                    a.f[q.eV] = aZ[0];
                    if (a.f[eV] > h.ah - 120 && k.a(a.e[eV], a.f[eV], 2)) {
                        a.f[q.eV] = k.d(a.f[eV]);
                        eV = -1;
                    }
                } else if (eV != -1) {
                    a.e[q.eV] = aY[0];
                    a.f[q.eV] = aZ[0];
                }
            }
        }
        switch (eL) {
            case 1: {
                int n3;
                int n4;
                if (bb[0] == 0) {
                    return;
                }
                if (bb[0] != 3 && k.a(aY[0], aZ[0], 65536) && h.ah > aZ[0]) {
                    q.bb[0] = 3;
                    q.ba[0] = 0;
                    q.eK[0] = 0;
                    q.aY[0] = k.e(aY[0]) + 20;
                    q.aZ[0] = k.d(aZ[0]) + 6;
                    h.bb = h.as;
                    h.al = 48;
                    h.ak = 15;
                    h.av = 2;
                    h.ah -= 18;
                }
                if (bb[0] == 3) break;
                ba[0] = ba[0] + 7;
                eK[0] = eK[0] - 7;
                if (i.b) {
                    i.m = aY[0];
                    i.n = aZ[0];
                }
                if (bb[0] != 2) {
                    n2 = 0;
                    while (n2 < a.c) {
                        if (a.i[n2] != 5 && a.i[n2] != 1 && a.i[n2] != 10) {
                            n4 = 0;
                            if (ba[0] <= 0 && eV == -1 && a.f[n2] >= h.ah - 130) {
                                n3 = -1;
                                if (ba[0] == 0) {
                                    n3 = 5;
                                }
                                if (ba[0] == -7) {
                                    n3 = 4;
                                }
                                if (ba[0] == -14) {
                                    n3 = 3;
                                }
                                if (ba[0] == -21) {
                                    n3 = 2;
                                }
                                if (ba[0] == -28) {
                                    n3 = 1;
                                }
                                if (n3 != -1 && q.a(h.ag + h.as * n3 * 24 - a.e[n2]) < a.h[n2] && h.ah > a.f[n2] && q.a(h.ah - n3 * 24 - a.f[n2]) < a.g[n2]) {
                                    n4 = 1;
                                }
                            }
                            if (n4 == 1) {
                                if (!a.a(n2, h.aI, bb[0], 0)) {
                                    q.b(a.e[n2], a.f[n2] - (a.g[n2] >> 1), a.k[n2]);
                                    q.a("-" + h.aI, 0, a.e[n2], a.f[n2] - a.g[n2], 0, -2);
                                }
                                if (ba[0] <= -7) {
                                    q.eK[0] = 0;
                                    q.ba[0] = 0;
                                }
                                a.e[n2] = aY[0];
                                a.f[n2] = aZ[0];
                                eV = eW = n2;
                                break;
                            }
                        }
                        ++n2;
                    }
                }
                if (bb[0] != 2 && n.p) {
                    n2 = 0;
                    while (n2 < 10) {
                        if (n.j[n2] != 0) {
                            n4 = 0;
                            if (ba[0] <= 0 && eV == -1 && n.f[n2] >= h.ah - 130) {
                                n3 = -1;
                                if (ba[0] == 0) {
                                    n3 = 5;
                                }
                                if (ba[0] == -7) {
                                    n3 = 4;
                                }
                                if (ba[0] == -14) {
                                    n3 = 3;
                                }
                                if (ba[0] == -21) {
                                    n3 = 2;
                                }
                                if (ba[0] == -28) {
                                    n3 = 1;
                                }
                                if (n3 != -1 && q.a(h.ag + h.as * n3 * 24 - n.e[n2]) < 20 && h.ah > n.f[n2] && q.a(h.ah - n3 * 24 - n.f[n2]) < 25) {
                                    n4 = 1;
                                }
                            }
                            if (n4 == 1) {
                                n = 4;
                                o.a.addElement(new j(n.e[n2], n.f[n2], 25));
                                n3 = 0;
                                while (n3 < q.b(7, 10)) {
                                    o.a.addElement(new j(q.b(n.e[n2] - 40, n.e[n2] + 40), q.b(n.f[n2] - 40, n.f[n2] + 10), 27));
                                    n3 = (byte)(n3 + 1);
                                }
                                n.k[n2] = 2;
                                n.e[n2] = q.b(P * 24, P * 24 + 50);
                                n.f[n2] = q.b(240, 336);
                                n.h[n2] = 0;
                                n.i[n2] = -1;
                                n.g[n2] = q.b(2, 5) * n.i[n2];
                                if (ba[0] <= -7) {
                                    q.eK[0] = 0;
                                    q.ba[0] = 0;
                                }
                                n.e[n2] = aY[0];
                                n.f[n2] = aZ[0];
                                break;
                            }
                        }
                        ++n2;
                    }
                }
                if (d.h) {
                    if (i.b) {
                        if (d.k != -1) {
                            n2 = 0;
                            if (ba[0] <= 0 && eV == -1 && d.o[d.k] >= h.ah - 130) {
                                n4 = -1;
                                if (ba[0] == 0) {
                                    n4 = 5;
                                }
                                if (ba[0] == -7) {
                                    n4 = 4;
                                }
                                if (ba[0] == -14) {
                                    n4 = 3;
                                }
                                if (ba[0] == -21) {
                                    n4 = 2;
                                }
                                if (ba[0] == -28) {
                                    n4 = 1;
                                }
                                if (n4 != -1 && q.a(h.ag + h.as * n4 * 24 - d.n[d.k]) < d.e && h.ah > d.o[d.k] && q.a(h.ah - n4 * 24 - d.o[d.k]) < d.f) {
                                    n2 = 1;
                                }
                            }
                            if (n2 == 1) {
                                d.a(h.aI, d.k);
                                q.b(d.n[d.k], d.o[d.k] - 10, d.m[d.k]);
                                q.a("-" + h.aI, 0, d.n[d.k], d.o[d.k] - 20, 0, -2);
                                if (ba[0] <= -7) {
                                    q.eK[0] = 0;
                                    q.ba[0] = 0;
                                }
                                d.n[d.k] = aY[0];
                                d.o[d.k] = aZ[0];
                                eV = eW = d.k;
                                return;
                            }
                        }
                    } else {
                        n2 = 0;
                        while (n2 < d.i) {
                            if (d.r[n2] != -1) {
                                n4 = 0;
                                if (ba[0] <= 0 && eV == -1 && d.o[n2] >= h.ah - 130) {
                                    n3 = -1;
                                    if (ba[0] == 0) {
                                        n3 = 5;
                                    }
                                    if (ba[0] == -7) {
                                        n3 = 4;
                                    }
                                    if (ba[0] == -14) {
                                        n3 = 3;
                                    }
                                    if (ba[0] == -21) {
                                        n3 = 2;
                                    }
                                    if (ba[0] == -28) {
                                        n3 = 1;
                                    }
                                    if (n3 != -1 && q.a(h.ag + h.as * n3 * 24 - d.n[n2]) < d.e && h.ah > d.o[n2] && q.a(h.ah - n3 * 24 - d.o[n2]) < d.f) {
                                        n4 = 1;
                                    }
                                }
                                if (n4 == 1) {
                                    d.a(h.aI, n2);
                                    q.b(d.n[n2], d.o[n2] - 10, d.m[n2]);
                                    q.a("-" + h.aI, 0, d.n[n2], d.o[n2] - 20, 0, -2);
                                    if (ba[0] <= -7) {
                                        q.eK[0] = 0;
                                        q.ba[0] = 0;
                                    }
                                    d.n[n2] = aY[0];
                                    d.o[n2] = aZ[0];
                                    eV = eW = n2;
                                    break;
                                }
                            }
                            n2 = (byte)(n2 + 1);
                        }
                    }
                }
                if (i.a) {
                    n2 = 0;
                    if (ba[0] <= 0 && eV == -1 && i.n >= h.ah - 130) {
                        n4 = -1;
                        if (ba[0] == 0) {
                            n4 = 5;
                        }
                        if (ba[0] == -7) {
                            n4 = 4;
                        }
                        if (ba[0] == -14) {
                            n4 = 3;
                        }
                        if (ba[0] == -21) {
                            n4 = 2;
                        }
                        if (ba[0] == -28) {
                            n4 = 1;
                        }
                        if (n4 != -1 && q.a(h.ag + h.as * n4 * 24 - i.m) < i.w && h.ah > i.n && q.a(h.ah - n4 * 24 - i.n) < i.x) {
                            n2 = 1;
                        }
                    }
                    if (n2 == 1) {
                        if (!i.a(h.aI, bb[0], 0)) {
                            q.b(i.m, i.n - (i.x >> 1), i.s);
                            q.a("-" + h.aI, 0, i.m, i.n - i.x, 0, -2);
                        }
                        q.eK[0] = -7;
                        q.ba[0] = 7;
                    }
                }
                if (i.E && d.g) {
                    n2 = 0;
                    if (ba[0] <= 0 && eV == -1 && i.I >= h.ah - 130) {
                        n4 = -1;
                        if (ba[0] == 0) {
                            n4 = 5;
                        }
                        if (ba[0] == -7) {
                            n4 = 4;
                        }
                        if (ba[0] == -14) {
                            n4 = 3;
                        }
                        if (ba[0] == -21) {
                            n4 = 2;
                        }
                        if (ba[0] == -28) {
                            n4 = 1;
                        }
                        if (n4 != -1 && q.a(h.ag + h.as * n4 * 24 - i.H) < 22 && h.ah > i.I && q.a(h.ah - n4 * 24 - i.I) < 42) {
                            n2 = 1;
                        }
                    }
                    if (n2 == 1) {
                        i.a(h.aI, bb[0]);
                        q.eK[0] = -7;
                        q.ba[0] = 7;
                    }
                }
                if (ap == 17 && !i.k) {
                    n2 = 0;
                    if (ba[0] <= 0 && eV == -1 && h.bp >= h.ah - 130) {
                        n4 = -1;
                        if (ba[0] == 0) {
                            n4 = 5;
                        }
                        if (ba[0] == -7) {
                            n4 = 4;
                        }
                        if (ba[0] == -14) {
                            n4 = 3;
                        }
                        if (ba[0] == -21) {
                            n4 = 2;
                        }
                        if (ba[0] == -28) {
                            n4 = 1;
                        }
                        if (n4 != -1 && q.a(h.ag + h.as * n4 * 24 - h.bo) < h.am && h.ah > h.bp && q.a(h.ah - n4 * 24 - h.bp) < h.an) {
                            n2 = 1;
                        }
                    }
                    if (n2 == 1) {
                        if (!h.b(h.aI, bb[0])) {
                            q.b(h.bo, h.bp - (h.an >> 1), h.bA);
                            q.a("-" + h.aI, 0, h.bo, h.bp - h.an, 0, -2);
                        }
                        q.eK[0] = -7;
                        q.ba[0] = 7;
                    }
                }
                if (aZ[0] <= h.ah && (q.a(aY[0] - h.ag) >= 20 || q.a(aZ[0] - h.ah) >= 20)) break;
                q.bb[0] = 0;
                h.ak = 1;
                if (eW == -1) break;
                if (a.d[eW] == 2 || a.d[eW] == 3 || a.d[eW] == 8 || a.d[eW] == 10 || a.d[eW] == 13 || a.d[eW] == 14 || a.d[eW] == 17 || a.d[eW] == 18 || a.d[eW] == 27 || a.d[eW] == 22 || a.d[eW] == 23) {
                    eW = -1;
                }
                if (eW != -1 && !k.a(a.e[eW], a.f[eW], 2)) {
                    a.i[q.eW] = 11;
                    a.j[q.eW] = 1;
                }
                eW = -1;
                return;
            }
            case 2: {
                if (++eM >= 5 && eM < 10) {
                    ba[0] = ba[0] - 1;
                    ba[2] = ba[2] + 1;
                } else if (eM >= 10) {
                    q.ba[2] = 0;
                    q.ba[0] = 0;
                }
                aY[1] = aY[1] + eK[1] * bb[1];
                aY[2] = aY[2] + eK[2] * bb[2];
                aZ[2] = aZ[2] + ba[2];
                n2 = 0;
                while (n2 < 3) {
                    if (bb[n2] != 0) {
                        int n5 = n2;
                        eK[n5] = eK[n5] + 1;
                        int n6 = 0;
                        while (n6 < a.c) {
                            if (a.i[n6] != 5 && a.i[n6] != 1 && a.i[n6] != 10 && q.a(aY[n2] - a.e[n6]) < 10 && q.a(a.f[n6] - (a.g[n6] >> 1) - aZ[n2]) < 10) {
                                if (!a.a(n6, h.aJ, bb[n2], 4)) {
                                    q.b(a.e[n6], a.f[n6] - (a.g[n6] >> 1), a.k[n6]);
                                    q.a("-" + h.aJ, 0, a.e[n6], a.f[n6] - a.g[n6], 0, -2);
                                }
                                q.bb[n2] = 0;
                            }
                            ++n6;
                        }
                        if (n.p) {
                            n6 = 0;
                            while (n6 < 10) {
                                if (n.j[n6] != 0 && q.a(aY[n2] - n.e[n6]) < 10 && q.a(n.f[n6] - 10 - aZ[n2]) < 10) {
                                    n = 4;
                                    o.a.addElement(new j(n.e[n6], n.f[n6], 25));
                                    int n7 = 0;
                                    while (n7 < q.b(7, 10)) {
                                        o.a.addElement(new j(q.b(n.e[n6] - 40, n.e[n6] + 40), q.b(n.f[n6] - 40, n.f[n6] + 10), 27));
                                        n7 = (byte)(n7 + 1);
                                    }
                                    n.k[n6] = 2;
                                    n.e[n6] = q.b(P * 24, P * 24 + 50);
                                    n.f[n6] = q.b(240, 336);
                                    n.h[n6] = 0;
                                    n.i[n6] = -1;
                                    n.g[n6] = q.b(2, 5) * n.i[n6];
                                    q.bb[n2] = 0;
                                    break;
                                }
                                n6 = (byte)(n6 + 1);
                            }
                        }
                        if (aY[n2] < N * 24 || aY[n2] > P * 24) {
                            q.bb[n2] = 0;
                        }
                    }
                    ++n2;
                }
                break;
            }
        }
    }

    private static void U() {
        int n2 = 0;
        while (n2 < dd) {
            if (ac[n2] == 1) {
                int n3 = n2;
                de[n3] = de[n3] + ad[n2];
                int n4 = n2;
                df[n4] = df[n4] + dh[n2];
                if (de[n2] < ax || de[n2] > ax + S || df[n2] < ay || df[n2] > ay + T) {
                    q.ac[n2] = -1;
                } else {
                    if (ap == 8 && bA[0] == -1 && ad[n2] > 0 && de[n2] >= 696 && de[n2] <= 720 && df[n2] >= 792 && df[n2] <= 840) {
                        q.ac[n2] = 2;
                        q.de[n2] = k.e(de[n2]) + 2;
                    }
                    if (ad[n2] > 0 && (k.c(de[n2], df[n2]) & 4) == 4) {
                        q.ac[n2] = 2;
                        q.de[n2] = k.e(de[n2]);
                    } else if (ad[n2] < 0 && (k.c(de[n2], df[n2]) & 8) == 8) {
                        q.ac[n2] = 2;
                        q.de[n2] = k.e(de[n2]) + k.f;
                    }
                    int n5 = n2;
                    dg[n5] = dg[n5] + 1;
                    if (dg[n2] > 1) {
                        q.dg[n2] = 0;
                    }
                    if (ad[n2] != 0) {
                        int n6 = 0;
                        while (n6 < a.c) {
                            if (a.i[n6] != 5 && a.i[n6] != 1 && a.i[n6] != 10 && a.d[n6] != 27 && q.a(a.e[n6] - de[n2]) < a.h[n6] && q.a(a.f[n6] - (a.g[n6] >> 1) - df[n2]) < a.g[n6]) {
                                int n7;
                                int n8 = n7 = bm > 0 ? h.ax + 10 : h.ax;
                                if (bm > 0 && h.ae == 1) {
                                    o.b.addElement(new j(a.e[n6], df[n2], 25));
                                    if (--bm == 0) {
                                        int n9 = 0;
                                        while (n9 < 2) {
                                            q.dF[n9] = q.c("/c/pt" + n9 + ".png");
                                            ++n9;
                                        }
                                    }
                                }
                                if (a.b(n6, n2, n7)) break;
                                q.b(a.e[n6], a.f[n6] - (a.g[n6] >> 1), a.k[n6]);
                                q.a("-" + n7, 0, a.e[n6], a.f[n6] - a.g[n6], 0, -2);
                                break;
                            }
                            n6 = (byte)(n6 + 1);
                        }
                        if (!di[n2] && q.a(h.ag - de[n2]) < h.ao && q.a(h.ah - h.ap - df[n2]) < h.ap) {
                            if (h.ak == 5 && h.ae == 0) {
                                q.ad[n2] = -ad[n2];
                                q.dh[n2] = -3;
                                return;
                            }
                            h.a(h.ax, -h.as);
                            q.ac[n2] = -1;
                            return;
                        }
                        if (ap == 17 && !i.k && q.a(h.bo - de[n2]) < h.am >> 1 && q.a(h.bp - (h.an >> 1) - df[n2]) < h.an >> 1) {
                            if (h.aA >= 1) {
                                h.b(h.ax, -h.bA);
                                q.b(de[n2], df[n2], h.bA);
                                q.a("-" + h.ax, 0, h.bo, h.bp - h.an, 0, -2);
                                q.ac[n2] = -1;
                            } else {
                                q.a("\u0110\u00e3 h\u1ebft MP");
                                q.v();
                            }
                        }
                    }
                }
            } else if (ac[n2] > 1) {
                int n10 = n2;
                ac[n10] = ac[n10] + 1;
                if (ac[n2] >= 10) {
                    q.ac[n2] = 1;
                    q.dh[n2] = 12;
                    q.ad[n2] = 0;
                }
            }
            n2 = (byte)(n2 + 1);
        }
    }

    private static void V() {
        int n2 = 0;
        while (n2 < 5) {
            if (dt[n2] != -1) {
                int n3 = n2;
                dt[n3] = dt[n3] + q.a(ds[n2]);
                if (dt[n2] > 30) {
                    q.dt[n2] = -1;
                }
                int n4 = n2;
                dp[n4] = dp[n4] + dr[n2];
                int n5 = n2;
                dq[n5] = dq[n5] + ds[n2];
            }
            ++n2;
        }
    }

    private static void W() {
        if (bH == -1 || bG == -1) {
            return;
        }
        h.ae = bG;
        h.af = bH;
        bG = -1;
        bH = -1;
        if (h.ae == 1) {
            h.g = false;
            h.h = false;
        }
        if (h.ae == 0) {
            if (h.af < 4) {
                h.g = false;
                h.h = false;
            }
            if (h.af == 4) {
                h.h = true;
                h.g = false;
            }
            if (h.af == 5) {
                h.g = true;
                h.h = false;
            }
        }
        q.j();
        h.e();
    }

    public static void v() {
        i.l = -1;
        i.j = false;
        i.B = 0;
        i.b = false;
        az = false;
        i.A = 0;
        i.Q = 0;
        h.bk = 0;
        q.b();
        q.c();
        i.q = i.r / 5 + h.aw * 3;
        f.g = f.h / 5 + h.aw * 3;
        h.bi = h.bj / 5 + h.aw * 3;
        o.a.c();
        i.N = i.O / 5 + h.aw * 3;
        if (i.N > i.O) {
            i.N = i.O;
        }
        if (i.q > i.r) {
            i.q = i.r;
        }
        if (f.g > f.h) {
            f.g = f.h;
        }
        if (h.bi > h.bj) {
            h.bi = h.bj;
        }
        if (d.h) {
            int n2 = 0;
            while (n2 < d.i) {
                d.q[n2] = 0;
                n2 = (byte)(n2 + 1);
            }
            d.d[d.k] = d.j / 5 + h.aw * 3;
            if (d.d[d.k] > d.j) {
                d.d[d.k] = d.j;
            }
        }
        q.W();
        if (!k.a(h.ag, h.ah, 2)) {
            h.ak = 4;
        }
    }

    private static void X() {
        if (++aq == 10000) {
            aq = 0;
        }
        q.Q();
        q.N();
        q.ab();
        if (q.O()) {
            return;
        }
        h.c();
        h.b();
        h.a();
        e.a();
    }

    public static void e(int n2, int n3) {
        if (!ck || a) {
            return;
        }
        switch (n2) {
            case 0: {
                o.b.addElement(new j(n3 - 1, cs + 3, 41));
                return;
            }
            case 1: {
                o.b.addElement(new j(n3 - 1, cs + 15, 41));
                return;
            }
            case 2: {
                o.b.addElement(new j(cn - 20, cm + 10, 41));
                ((j)o.b.lastElement()).a = 2;
                q.bd[0] = 2;
                return;
            }
            case 3: {
                o.b.addElement(new j(cn - 31, cm + 23, 41));
                ((j)o.b.lastElement()).a = 3;
                q.bd[1] = 3;
                return;
            }
            case 4: {
                o.b.addElement(new j(cn - 11, cm + 23, 41));
                ((j)o.b.lastElement()).a = 4;
                q.bd[2] = 4;
            }
        }
    }

    private static void Y() {
        int n2 = 0;
        while (n2 < Y) {
            if (ab[n2] != 0) {
                int n3;
                if (q.a(h.ah - (h.an >> 1) - (aa[n2] - 7)) < 10 && q.a(h.ag - Z[n2]) < 10) {
                    if (cY[n2] == 0) {
                        n3 = 0;
                        while (n3 < 5) {
                            o.b.addElement(new j(h.ag - 14 + n3 * 7, h.ah - 12, 44));
                            n3 = (byte)(n3 + 1);
                        }
                        q.a("+" + dc[n2], 0, h.ag, h.ah - h.an, 0, -1);
                        if ((h.ay += dc[n2]) > h.az) {
                            h.ay = h.az;
                        }
                        q.e(0, L + h.ay * M / h.az);
                    } else {
                        if ((h.aA += dc[n2]) > h.aB) {
                            h.aA = h.aB;
                        }
                        q.e(1, L + h.aA * M / h.aB);
                        q.a("+" + dc[n2], 2, Z[n2], aa[n2] - 15, 0, -1);
                    }
                    A = true;
                    q.ab[n2] = 301;
                }
                if (n.l && q.a(n.b - (aa[n2] - 7)) < 10 && q.a(n.a - Z[n2]) < 10) {
                    if (cY[n2] == 0) {
                        n3 = 0;
                        while (n3 < 5) {
                            o.b.addElement(new j(h.ag - 14 + n3 * 7, h.ah - 12, 44));
                            n3 = (byte)(n3 + 1);
                        }
                        q.a("+" + dc[n2], 0, h.ag, h.ah - h.an, 0, -1);
                        if ((h.ay += dc[n2]) > h.az) {
                            h.ay = h.az;
                        }
                        q.e(0, L + h.ay * M / h.az);
                    } else {
                        if ((h.aA += dc[n2]) > h.aB) {
                            h.aA = h.aB;
                        }
                        q.e(1, L + h.aA * M / h.aB);
                        q.a("+" + dc[n2], 2, Z[n2], aa[n2] - 15, 0, -1);
                    }
                    A = true;
                    q.ab[n2] = 301;
                }
                int n4 = n2;
                Z[n4] = Z[n4] + cZ[n2];
                int n5 = n2;
                aa[n5] = aa[n5] + da[n2];
                if (cZ[n2] > 1) {
                    int n6 = n2;
                    cZ[n6] = cZ[n6] - 1;
                }
                if (cZ[n2] < -1) {
                    int n7 = n2;
                    cZ[n7] = cZ[n7] + 1;
                }
                if (da[n2] < 20) {
                    int n8 = n2;
                    da[n8] = da[n8] + 1;
                }
                int n9 = n2;
                ab[n9] = ab[n9] + 1;
                if (ab[n2] > 200) {
                    q.ab[n2] = 0;
                }
                if (aa[n2] >= k.d || aa[n2] < 0 || Z[n2] >= k.c || Z[n2] < 0) {
                    q.ab[n2] = 0;
                } else if (da[n2] > 2 && (k.c(Z[n2], aa[n2]) & 2) == 2) {
                    q.aa[n2] = k.d(aa[n2]);
                    if (da[n2] > 2) {
                        q.da[n2] = -(da[n2] >> 1);
                    } else {
                        q.cZ[n2] = 0;
                        q.da[n2] = 0;
                    }
                }
            }
            ++n2;
        }
    }

    private static void Z() {
        int n2 = 0;
        while (n2 < 2) {
            if (cL[n2] != -1) {
                int n3 = n2;
                cL[n3] = cL[n3] + 1;
                int n4 = n2;
                cJ[n4] = cJ[n4] + (cN[n2] << 2);
                int n5 = n2;
                cK[n5] = cK[n5] - 1;
                if (cL[n2] >= 6) {
                    q.cL[n2] = -1;
                } else {
                    q.cM[n2] = (cL[n2] >> 1) % 3;
                }
            }
            ++n2;
        }
    }

    private static void aa() {
        if (eI == 0) {
            return;
        }
        if (eJ == 0) {
            int n2 = 0;
            while (n2 < eI) {
                if (q.a(h.ag - eE[n2]) < 15 && q.a(h.ah - eF[n2]) < 30) {
                    h.a(a.a[11], eH[n2]);
                    o.a.addElement(new j(h.ag + (eH[n2] << 3), h.ah - 20, 17));
                    h.m = true;
                    eI = 0;
                    return;
                }
                if (n2 == eI - 1) {
                    eI = 0;
                    return;
                }
                ++n2;
            }
            return;
        }
        int n3 = 0;
        while (n3 < eI) {
            if (c.l != 1) {
                if (aq % 2 == 0) {
                    q.eG[n3] = eG[n3] == 0 ? 1 : 0;
                }
            } else {
                q.eG[n3] = aq % c.i > 5 ? 0 : 1;
            }
            if (q.a(h.ag - eE[n3]) < 15 && q.a(h.ah - eF[n3]) < 30) {
                h.a(0, 1);
                h.aj = 1;
                h.m = true;
                o.b.addElement(new j(h.ag + 15, h.ah - 25, 23));
            }
            ++n3;
        }
    }

    private static void ab() {
        block31: {
            block30: {
                if (!aH) break block30;
                switch (j[aG][aI]) {
                    case 2: {
                        q.e(k[aG][aI]);
                        ++aI;
                        return;
                    }
                    case 1: {
                        if (h.ag > bK[aG][aI]) {
                            q.e(k[aG][aI]);
                            ++aI;
                            return;
                        }
                        break block31;
                    }
                    case 4: {
                        if (h.ag > ax + S) {
                            q.e(k[aG][aI]);
                            ++aI;
                            return;
                        }
                        break block31;
                    }
                    case 6: {
                        if (h.ag < bK[aG][aI]) {
                            q.e(k[aG][aI]);
                            ++aI;
                            return;
                        }
                        break block31;
                    }
                    case 7: {
                        if ((h.ak == 1 || h.ak == 2) && h.ah == bK[aG][aI]) {
                            q.e(k[aG][aI]);
                            ++aI;
                            return;
                        }
                        break block31;
                    }
                    case 8: {
                        if (h.bo > bK[aG][aI]) {
                            if (h.bo <= h.ag) {
                                q.e(k[aG][aI]);
                            } else {
                                q.e(k[aG][aI] + 1);
                            }
                            ++aI;
                            return;
                        }
                        break block31;
                    }
                    case 10: {
                        if (h.ag < bK[aG][aI]) {
                            h.ag = bK[aG][aI] + 1;
                            q.e(k[aG][aI]);
                            return;
                        }
                        break block31;
                    }
                    case 9: {
                        if (h.ag > bK[aG][aI]) {
                            h.ag = bK[aG][aI] - 1;
                            q.e(k[aG][aI]);
                            return;
                        }
                        break block31;
                    }
                    case 11: {
                        if (q.a(h.ag - bK[aG][aI]) < 55) {
                            q.e(k[aG][aI]);
                            ++aI;
                            return;
                        }
                        break block31;
                    }
                    case 13: {
                        if (q.a(h.ag - bL[bK[aG][aI]][0]) < 65 && h.ah <= bL[bK[aG][aI]][1] && h.ah >= bL[bK[aG][aI]][1] - 48) {
                            q.e(k[aG][aI]);
                            if (aG == 2 || aG == 16) {
                                c.h = -1;
                                c.a = 1;
                                i.D = true;
                                c.g = 50;
                            }
                            ++aI;
                            return;
                        }
                        break block31;
                    }
                    case 14: {
                        if (q.a(h.ag - h.F) < 60) {
                            h.R = h.F > h.ag ? -1 : 1;
                            int n2 = h.as = h.F > h.ag ? 1 : -1;
                            if (k.a(h.ag, h.ah, 2)) {
                                h.ak = 1;
                            }
                            h.J = 1;
                            q.e(k[aG][aI]);
                            c = h.F;
                            d = h.G;
                            e = h.R;
                            f = h.ag;
                            g = h.ah;
                            h = h.as;
                            ++aI;
                            return;
                        }
                        break block31;
                    }
                    case 15: {
                        if (h.ag > bL[bK[aG][aI]][0] && h.ah <= bL[bK[aG][aI]][1]) {
                            h.ag = bL[bK[aG][aI]][0] - 1;
                            q.e(k[aG][aI]);
                            return;
                        }
                        break block31;
                    }
                    default: {
                        return;
                    }
                }
            }
            if (aG == 12) {
                Display.getDisplay((MIDlet)Midlet.b).setCurrent((Displayable)Midlet.a);
                aG = 0;
            }
        }
    }

    private static void ac() {
        if (a) {
            return;
        }
        int n2 = 0;
        while (n2 < 2) {
            if (cQ[n2] != -1) {
                int n3 = n2;
                cP[n3] = cP[n3] - 1;
                if (aq % 2 == 0) {
                    int n4 = n2;
                    cQ[n4] = cQ[n4] + 1;
                    if (cQ[n2] > 2) {
                        q.cQ[n2] = -1;
                    } else {
                        q.cR[n2] = cQ[n2];
                    }
                }
            }
            ++n2;
        }
    }

    public static void w() {
        Midlet.a = new q();
        q.ab();
        q q2 = Midlet.a;
        Midlet.a(Midlet.g == 0 ? 1 : 0);
        q.x();
        B = 13;
        Midlet.c = true;
        new Thread(q2).start();
    }

    public q() {
        this.setFullScreenMode(true);
        System.gc();
        l.a();
        o = this;
        ca = this.getKeyCode(8) == -20;
        v = this.getWidth();
        w = this.getHeight();
        x = v / 2;
        y = w / 2;
        b.a = b.f() == 1;
        b.c();
        b.a();
        b.d = x - (b.e.stringWidth(b.b[1]) >> 1);
    }

    protected final void keyPressed(int n2) {
        if (n2 == 35) {
            if (cz.equals("1984")) {
                q.a("Learned All Skill");
            } else if (cz.equals("33538")) {
                h.aB = 500000;
                h.aA = 500000;
                h.az = 500000;
                h.ay = 500000;
                q.a("Unlimit HP MP");
            } else if (cz.equals("33305")) {
                h.aw = 10000;
                q.a("Unlimit Damage");
            } else if (cz.equals("2972")) {
                h.d(100000);
            } else if (cz.equals("2973")) {
                ae += 50000;
                q.a("Money+=50000");
            } else if (cz.equals("12456")) {
                TextBox textBox = new TextBox("Type anything:", "", 50, 0);
                textBox.addCommand(new Command("OK", 4, 0));
                textBox.addCommand(new Command("Back", 2, 0));
                textBox.setCommandListener((CommandListener)this);
                Display.getDisplay((MIDlet)Midlet.b).setCurrent((Displayable)textBox);
            } else if (cz.equals("1589")) {
                int n3 = 0;
                while (n3 < br.length) {
                    q.br[n3] = true;
                    ++n3;
                }
                q.a("Unlock all map!");
            } else if (cz.equals("8463")) {
                int n4 = dW + (int)((System.currentTimeMillis() - dX) / 1000L);
                q.a(String.valueOf(n4 / 3600) + ":" + n4 % 3600 / 60 + ":" + n4 % 60);
            }
            A = true;
            cz = "";
        }
        if (n2 >= 48 && n2 <= 57 && (cz = String.valueOf(cz) + (char)n2).length() > 10) {
            cz = "";
        }
        if (ca) {
            switch (n2) {
                case -21: 
                case 21: {
                    n2 = -6;
                    break;
                }
                case -22: 
                case 22: {
                    n2 = -7;
                    break;
                }
                case -23: {
                    n2 = -8;
                    break;
                }
                case -2: {
                    n2 = -3;
                    break;
                }
                case -5: {
                    n2 = -4;
                    break;
                }
                case -6: {
                    n2 = -2;
                    break;
                }
                case -20: {
                    n2 = -5;
                }
            }
        }
        if (az && !i.b && n2 != -5) {
            return;
        }
        this.h(n2);
    }

    protected final void keyReleased(int n2) {
        if (ca) {
            switch (n2) {
                case -21: 
                case 21: {
                    n2 = -6;
                    break;
                }
                case -22: 
                case 22: {
                    n2 = -7;
                    break;
                }
                case -23: {
                    n2 = -8;
                    break;
                }
                case -2: {
                    n2 = -3;
                    break;
                }
                case -5: {
                    n2 = -4;
                    break;
                }
                case -6: {
                    n2 = -2;
                    break;
                }
                case -20: {
                    n2 = -5;
                }
            }
        }
        if (az && n2 != -5) {
            return;
        }
        q.i(n2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void h(int n2) {
        if (i.b) {
            boolean bl;
            int n3 = n2;
            if (fa) {
                fa = false;
                bl = true;
            } else {
                if (fa) return;
                switch (i.l) {
                    case 1: {
                        if (n3 == 49) {
                            bl = true;
                            break;
                        }
                    }
                    case 2: {
                        if (n3 == 50) {
                            bl = true;
                            break;
                        }
                    }
                    case 3: {
                        if (n3 == 51) {
                            bl = true;
                            break;
                        }
                    }
                    case 4: {
                        if (n3 == 52) {
                            bl = true;
                            break;
                        }
                    }
                    case 5: {
                        if (n3 == 53) {
                            bl = true;
                            break;
                        }
                    }
                    case 6: {
                        if (n3 == 54) {
                            bl = true;
                            break;
                        }
                    }
                    case 7: {
                        if (n3 == 55) {
                            bl = true;
                            break;
                        }
                    }
                    case 8: {
                        if (n3 == 56) {
                            bl = true;
                            break;
                        }
                    }
                    case 9: {
                        if (n3 != 57) return;
                        bl = true;
                        break;
                    }
                    default: {
                        return;
                    }
                }
            }
            if (!bl) {
                return;
            }
        }
        switch (n2) {
            case -1: {
                if (ap == 4 && !i[17]) {
                    return;
                }
                q.t[2] = true;
                q.u[2] = true;
                return;
            }
            case -2: {
                q.t[8] = true;
                q.u[8] = true;
                return;
            }
            case -3: {
                q.t[4] = true;
                q.u[4] = true;
                return;
            }
            case -4: {
                q.t[6] = true;
                q.u[6] = true;
                return;
            }
            case -5: 
            case 10: {
                if (i.j && !i.b && i.B <= i.C) {
                    return;
                }
                q.t[5] = true;
                q.u[5] = true;
                return;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                if (ap == 4 && !i[17] && n2 - 48 == 2) {
                    return;
                }
                q.t[n2 - 48] = true;
                q.u[n2 - 48] = true;
                return;
            }
            case 42: {
                q.t[10] = true;
                q.u[10] = true;
                return;
            }
            case 35: {
                q.t[11] = true;
                q.u[11] = true;
                return;
            }
            case -21: 
            case -6: {
                q.t[12] = true;
                q.u[12] = true;
                return;
            }
            case -22: 
            case -7: {
                q.t[13] = true;
                q.u[13] = true;
                return;
            }
        }
    }

    private static void i(int n2) {
        switch (n2) {
            case -1: {
                q.t[2] = false;
                q.u[2] = false;
                return;
            }
            case -2: {
                q.t[8] = false;
                q.u[8] = false;
                return;
            }
            case -3: {
                q.t[4] = false;
                q.u[4] = false;
                return;
            }
            case -4: {
                q.t[6] = false;
                q.u[6] = false;
                return;
            }
            case -5: 
            case 10: {
                q.t[5] = false;
                return;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                q.t[n2 - 48] = false;
                return;
            }
            case 42: {
                q.t[10] = false;
                return;
            }
            case 35: {
                q.t[11] = false;
                return;
            }
            case -21: 
            case -6: {
                q.t[12] = false;
                return;
            }
            case -22: 
            case -7: {
                q.t[13] = false;
                return;
            }
        }
    }

    protected final void paint(Graphics object) {
        Object object2;
        try {
            switch (B) {
                case 1: {
                    object2 = object;
                    object2.setClip(-1, -1, S + 2, T + 2);
                    q.a((Graphics)object2);
                    object2.translate(-ax, -ay);
                    e.b((Graphics)object2);
                    k.b((Graphics)object2);
                    d.a((Graphics)object2);
                    i.e((Graphics)object2);
                    if (a) {
                        k.c((Graphics)object2);
                    } else {
                        k.d((Graphics)object2);
                    }
                    k.a((Graphics)object2);
                    c.c((Graphics)object2);
                    o.a.a((Graphics)object2);
                    d.b((Graphics)object2);
                    i.a((Graphics)object2);
                    c.a((Graphics)object2);
                    object = object2;
                    int n2 = 0;
                    while (n2 < af) {
                        if (ai[n2] != 0 && ag[n2] >= ax - 10 && ag[n2] <= ax + S + 10 && ah[n2] >= ay && ah[n2] <= ay + T + 20) {
                            object.drawRegion(dD[dn[n2]], 0, dl[n2] * 15, 8, 15, 0, ag[n2], ah[n2], 33);
                        }
                        ++n2;
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < Y) {
                        if (ab[n2] != 0 && Z[n2] >= ax - 10 && Z[n2] <= ax + S + 10 && aa[n2] >= ay && aa[n2] <= ay + T + 20) {
                            object.drawRegion(dH[cY[n2]], 0, db[n2] * 15, 13, 15, 0, Z[n2], aa[n2], 33);
                        }
                        ++n2;
                    }
                    i.d((Graphics)object2);
                    f.a((Graphics)object2);
                    object = object2;
                    if (bb[0] == -1) {
                        object.drawImage(eN[1], aY[0], aZ[0], 3);
                    }
                    if (bb[0] == 1) {
                        object.drawImage(eN[0], aY[0], aZ[0], 3);
                    }
                    switch (eL) {
                        case 1: {
                            if (bb[0] == 0) break;
                            object.setColor(0);
                            int n3 = n2 = bb[0] * bb[0] != 1 ? bb[0] / 3 : bb[0];
                            if (bb[0] == 3) {
                                object.drawLine(h.ag + n2 * 3 + (h.as == -1 ? -7 : 0), h.ah - 10, aY[0] - (n2 << 3), aZ[0] + 7);
                                break;
                            }
                            object.drawLine(h.ag + n2 * 7, h.ah - 13, aY[0] - (n2 << 3), aZ[0] + 7);
                            break;
                        }
                        case 2: {
                            n2 = 1;
                            while (n2 < 3) {
                                if (bb[n2] != 0) {
                                    if (bb[n2] == -1) {
                                        object.drawImage(eN[1], aY[n2], aZ[n2], 3);
                                    }
                                    if (bb[n2] == 1) {
                                        object.drawImage(eN[0], aY[n2], aZ[n2], 3);
                                    }
                                }
                                ++n2;
                            }
                            break;
                        }
                    }
                    h.d((Graphics)object2);
                    object = object2;
                    if (ap == 17) {
                        if (bA[1] == 0 || bA[1] == 10) {
                            object.drawRegion(gB, 0, 40, 13, 8, 0, gD[1], 248, 33);
                        }
                        if (bA[1] == 1 || bA[1] == -1) {
                            object.drawRegion(gB, 0, 48 - q.a(gE[1] - 240), 13, q.a(gE[1] - 240), 0, gD[1], gE[1], 33);
                        }
                    }
                    if (ap == 14 && (bA[2] == 1 || bA[2] == -1)) {
                        object.drawRegion(gB, 0, 48 - q.a(gE[2] - 360), 13, q.a(gE[2] - 360), 0, gD[2], gE[2], 33);
                    }
                    if (ap == 8) {
                        if (by == -1) {
                            object.drawImage(gA, bw, bx, 33);
                        }
                        if (by == 1) {
                            object.drawRegion(gA, 0, 0, gA.getWidth(), gA.getHeight(), 2, bw, bx, 33);
                        }
                        if (bz == 0) {
                            object.drawImage(gz, 768, 840, 33);
                        }
                        if (bz == 1 || bz == -1) {
                            object.drawRegion(gz, 0, 0, 37, 65 - q.a(840 - gC), 0, 768, 840, 33);
                        }
                        if (bA[0] == 0 || bA[0] == 10) {
                            object.drawRegion(gB, 0, 40, 13, 8, 0, gD[0], 800, 33);
                        }
                        if (bA[0] == 1 || bA[0] == -1) {
                            object.drawRegion(gB, 0, 48 - q.a(gE[0] - 792), 13, q.a(gE[0] - 792), 0, gD[0], gE[0], 33);
                        }
                    }
                    a.a((Graphics)object2);
                    h.c((Graphics)object2);
                    object = object2;
                    if (dx != 0 && (n2 = dx % 3) < 2) {
                        object.drawImage(dG[n2], dv, dw, 17);
                    }
                    object = object2;
                    if (eq != -1) {
                        if (es == -1) {
                            object.drawImage(en[er], eo, ep, 3);
                        } else {
                            object.drawRegion(en[er], 0, 0, en[er].getWidth(), en[er].getHeight(), 2, eo, ep, 3);
                        }
                    }
                    h.a((Graphics)object2);
                    object = object2;
                    if (gJ[0] != -1 || gJ[1] != -1) {
                        if (gJ[0] == 1) {
                            object.drawRegion(gF, 0, gK[0] * 29, 40, 29, 0, gG[0], gH[0], 33);
                        }
                        if (gJ[1] == 1) {
                            object.drawRegion(gF, 0, gK[1] * 29, 40, 29, 2, gG[1], gH[1], 33);
                        }
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < d.i) {
                        if (gO[n2] != -1) {
                            object.drawRegion(gL, 0, gR[n2] * 25, 26, 25, 0, gM[n2], gN[n2], 33);
                        }
                        ++n2;
                    }
                    n.a((Graphics)object2);
                    i.b((Graphics)object2);
                    h.b((Graphics)object2);
                    i.c((Graphics)object2);
                    object = object2;
                    n2 = 0;
                    while (n2 < aJ) {
                        if (ax <= aL[n2] + 12 && ax + S >= aL[n2] - 12 && ay <= aM[n2] && ay + T >= aM[n2] - 24) {
                            object.drawImage(dB, aL[n2], aM[n2], 33);
                        }
                        ++n2;
                    }
                    object = object2;
                    if (eu != -1 && au[eu] != 0) {
                        if (eu == 3) {
                            object.drawRegion(am[eu], 0, ev * 10, 16, 10, 0, as[eu], at[eu], 33);
                        } else if (eu == 7) {
                            object.drawRegion(am[eu], 0, 60, 13, 15, 0, as[eu], at[eu], 33);
                        } else if (eu == 8) {
                            object.drawRegion(am[eu], 0, 75, 13, 15, 0, as[eu], at[eu], 33);
                        } else if (eu == 9) {
                            object.drawRegion(am[eu], 0, 45, 13, 15, 0, as[eu], at[eu], 33);
                        } else {
                            object.drawImage(am[eu], as[eu], at[eu], 33);
                        }
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < 2) {
                        if (cL[n2] != -1) {
                            if (cN[n2] == 1) {
                                object.drawImage(dI[cM[n2]], cJ[n2], cK[n2], 3);
                            } else {
                                object.drawRegion(dI[cM[n2]], 0, 0, dI[cM[n2]].getWidth(), dI[cM[n2]].getHeight(), 2, cJ[n2], cK[n2], 3);
                            }
                        }
                        ++n2;
                    }
                    object = object2;
                    if (eI != 0) {
                        if (eJ == 0) {
                            n2 = 0;
                            while (n2 < eI) {
                                object.drawImage(eD, eE[n2], eF[n2], 3);
                                ++n2;
                            }
                        }
                        if (eJ == 1) {
                            n2 = 0;
                            while (n2 < eI) {
                                if (eG[n2] != 0) {
                                    object.drawRegion(eD, 0, 0, 32, 22, 4, eE[n2], eF[n2], 33);
                                }
                                ++n2;
                            }
                        }
                    }
                    object = object2;
                    if (!a && ap != 9 && ap != 10 && ap != 8 && ap != 14) {
                        n2 = 0;
                        while (n2 < 2) {
                            if (cU[n2] != -1) {
                                object.drawImage(dE[n2][cU[n2]], cS[n2], cT[n2], 3);
                            }
                            ++n2;
                        }
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < cC) {
                        if (cF[n2] != -1) {
                            object.drawImage(dy[cI[n2]][cG[n2] > 0 ? 0 : 1], cD[n2], cE[n2], 3);
                        }
                        ++n2;
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < dd) {
                        if (ac[n2] != -1) {
                            object.drawImage(dF[dg[n2]], de[n2], df[n2], 3);
                        }
                        ++n2;
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < ex) {
                        if (eC[n2] != -1) {
                            object.drawImage(ew, ey[n2], ez[n2], 3);
                        }
                        ++n2;
                    }
                    object = object2;
                    if (eS != 0) {
                        object.setColor(3638250);
                        object.drawRegion(eU, 0, eT * 41, 32, 41, 0, eO, eP, 3);
                    }
                    object = object2;
                    if (!a) {
                        n2 = 0;
                        while (n2 < 2) {
                            if (cQ[n2] != -1) {
                                object.drawImage(dL[cR[n2]], cO[n2], cP[n2], 3);
                            }
                            ++n2;
                        }
                    }
                    e.a((Graphics)object2);
                    k.e((Graphics)object2);
                    object = object2;
                    n2 = 0;
                    while (n2 < 5) {
                        if (dt[n2] != -1) {
                            Midlet.a(object, do[n2], du[n2], dp[n2], dq[n2], 0);
                        }
                        ++n2;
                    }
                    o.b.a((Graphics)object2);
                    q.b((Graphics)object2);
                    object = object2;
                    if (h.j) {
                        object.translate(-object.getTranslateX(), -object.getTranslateY());
                        object.drawRegion(dH[2], 0, 45, 13, 15, 0, S - 5, 3, 24);
                    }
                    if (A) {
                        q.c((Graphics)object2);
                    }
                    o.b.b((Graphics)object2);
                    if (cy > 1) {
                        q.d((Graphics)object2);
                    }
                    if (cV) {
                        object = object2;
                        object.translate(-object.getTranslateX(), -object.getTranslateY());
                        object.setClip(-1, -1, v + 2, w + 2);
                        object.setColor(0);
                        object.fillRect(0, 0, v, 21);
                        object.setColor(10275899);
                        object.fillRect(0, 19, v, 1);
                        if (aq % 10 > 2) {
                            Midlet.a(object, cX, U, 4, 2);
                        }
                    }
                    if (l) {
                        object2.setColor(0);
                        object2.fillRect(0, 0, v, w);
                    }
                    if (aQ) {
                        q.e((Graphics)object2);
                    }
                    if (c.m) {
                        c.b((Graphics)object2);
                    }
                    return;
                }
                case 2: {
                    object2 = object;
                    object2.setColor(0);
                    object2.fillRect(0, 0, v, 21);
                    Midlet.a((Graphics)object2, "Loading... " + bf + "%", v >> 1, 4, 2);
                    return;
                }
                case 3: {
                    object2 = object;
                    object2.setClip(-1, -1, v + 2, w + 1);
                    q.a((Graphics)object2);
                    object2.translate(-ax, -ay);
                    e.b((Graphics)object2);
                    if (a) {
                        k.c((Graphics)object2);
                    } else {
                        k.d((Graphics)object2);
                    }
                    k.a((Graphics)object2);
                    k.e((Graphics)object2);
                    e.a((Graphics)object2);
                    object2.translate(-object2.getTranslateX(), -object2.getTranslateY());
                    object2.setColor(0);
                    object2.fillRect(0, 0, S, ce);
                    object2.fillRect(0, cf, S, w - cf + 1);
                    if (bW == 40) {
                        object2.setColor(10275899);
                        object2.fillRect(0, ce - 1, v, 1);
                        object2.fillRect(0, cf + 1, v, 1);
                    }
                    object2.drawImage(dJ, x - 14 - ci, y - 10 + cj, 3);
                    if (bW > 20) {
                        object2.drawImage(dA[bX], x + 30 - ci, y + cj, 3);
                    }
                    object2.drawRegion(fd, 0, 0, fd.getWidth(), fd.getHeight(), eZ[eY], x - ci - 2, y + cj - 30, 3);
                    Midlet.a((Graphics)object2, "*", x - 55 - aq % 3, ch, 0);
                    Midlet.a((Graphics)object2, "/", x + 55 + aq % 3, ch, 1);
                    Midlet.a((Graphics)object2, bV[cb], x, ch, 3);
                    return;
                }
                case 4: {
                    object2 = object;
                    object2.setColor(0);
                    object2.fillRect(0, 0, v, w);
                    if (fc != null) {
                        object2.drawImage(fc, v >> 1, w >> 1, 3);
                    }
                    return;
                }
                case 5: {
                    q.k(object);
                    return;
                }
                case 6: {
                    q.l(object);
                    return;
                }
                case 7: {
                    q.n(object);
                    return;
                }
                case 14: {
                    q.o(object);
                    return;
                }
                case 8: {
                    q.j(object);
                    return;
                }
                case 9: {
                    q.i(object);
                    return;
                }
                case 10: {
                    q.m(object);
                    return;
                }
                case 11: {
                    q.f(object);
                    return;
                }
                case 12: {
                    q.f(object);
                    return;
                }
                case 13: {
                    b.a(object);
                    return;
                }
                case 15: {
                    object2 = object;
                    object2.translate(-object2.getTranslateX(), -object2.getTranslateY());
                    q.c((Graphics)object2);
                    q.b((Graphics)object2);
                    object2.setClip(0, 0, v, w);
                    object2.setColor(0);
                    object2.fillRect(U - 64, fs - 19, 129, 120);
                    object2.fillRect(0, w - 13, v, 13);
                    Midlet.a((Graphics)object2, "Ch\u1ecdn", 3, w - 13, 0);
                    Midlet.a((Graphics)object2, "Quay l\u1ea1i", v - 3, w - 13, 1);
                    object2.setColor(0xBB6611);
                    object2.drawRect(U - 63, fs - 18, 126, 117);
                    object2.setColor(6562304);
                    object2.fillRect(U - 61, fs - 16, 123, 115);
                    Midlet.a((Graphics)object2, "Ch\u1ecdn v\u0169 kh\u00ed", U, fs, 2);
                    object2.setColor(0);
                    object2.fillRect(U - 10, fs + 25, 19, 20);
                    object2.fillRect(U - 10, fs + 25, 19, 20);
                    object2.setColor(4856064);
                    object2.drawRect(U - 10, fs + 25, 20, 20);
                    object2.drawRect(U - 10, fs + 25, 20, 20);
                    object2.drawImage(el[aS[fu]], U - 8, fs + 28, 0);
                    Midlet.a((Graphics)object2, aU[fu], U, fs + 60, 2);
                    Midlet.a((Graphics)object2, "*", U - 28 - em % 3, fs + 28, 0);
                    Midlet.a((Graphics)object2, "/", U + 30 + em % 3, fs + 28, 1);
                    object2.drawImage(fz[0], U - 64, fs - 19, 20);
                    object2.drawImage(fz[1], U + 65, fs - 19, 24);
                    object2.drawImage(fz[2], U - 64, fs + 101, 36);
                    object2.drawImage(fz[3], U + 65, fs + 101, 40);
                    return;
                }
                case 16: {
                    q.h(object);
                    return;
                }
                case 17: {
                    object2 = object;
                    object2.translate(-object2.getTranslateX(), -object2.getTranslateY());
                    object2.setColor(0);
                    object2.fillRect(0, 0, S, T + 2);
                    object2.fillRect(0, w - 13, v, 15);
                    Midlet.a((Graphics)object2, "Quay l\u1ea1i", v - 3, w - 13, 1);
                    object2.setClip(0, 0, S, T);
                    object2.translate(-gd, -ge);
                    object2.drawImage(fM, 0, 0, 0);
                    object2.drawImage(dK[0], gh, gi, 24);
                    return;
                }
                case 18: {
                    q.g(object);
                }
            }
            return;
        }
        catch (Exception exception) {
            object2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    private static void g(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        q.c(graphics);
        q.b(graphics);
        graphics.setClip(0, 0, v, w);
        graphics.setColor(0);
        graphics.fillRect(U - 80, bg - 19, 159, 142);
        graphics.fillRect(0, w - 13, v, 13);
        if (gS == 0) {
            Midlet.a(graphics, "Ch\u1ecdn", 3, w - 13, 0);
            Midlet.a(graphics, "Quay l\u1ea1i", v - 3, w - 13, 1);
        }
        if (gS > 0) {
            Midlet.a(graphics, "C\u00f3", 3, w - 13, 0);
            Midlet.a(graphics, "Kh\u00f4ng", v - 3, w - 13, 1);
        }
        graphics.setColor(0xBB6611);
        graphics.drawRect(U - 79, bg - 18, 156, 139);
        graphics.setColor(6562304);
        graphics.fillRect(U - 78, bg - 16, 154, 137);
        if (gS == 0) {
            int n2 = 0;
            while (n2 < bE.length) {
                Midlet.a(graphics, bE[n2], U, bg - 8 + n2 * 15, 2);
                ++n2;
            }
            n2 = 0;
            while (n2 < 3) {
                Midlet.a(graphics, m.e[m[bF] + 10][n2], U - 40, bg + bE.length * 15 + n2 * 17, 0);
                if (fu == n2) {
                    graphics.drawImage(dK[h.ae], U - 65 + aq % 2, bg + bE.length * 15 + n2 * 17 - aq % 2, 0);
                }
                ++n2;
            }
        } else {
            int n3 = 0;
            while (n3 < bE.length) {
                Midlet.a(graphics, bE[n3], U, bg + n3 * 15, 2);
                ++n3;
            }
        }
        graphics.drawImage(fz[0], U - 80, bg - 19, 20);
        graphics.drawImage(fz[1], U + 79, bg - 19, 24);
        graphics.drawImage(fz[2], U - 80, bg + 123, 36);
        graphics.drawImage(fz[3], U + 79, bg + 123, 40);
        n.a(graphics);
    }

    private static void h(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        q.c(graphics);
        q.b(graphics);
        graphics.setClip(0, 0, v, w);
        graphics.setColor(0);
        graphics.fillRect(U - 66, bg - 19, 131, 142);
        graphics.fillRect(0, w - 13, v, 13);
        if (fu == -1) {
            Midlet.a(graphics, "Ch\u1ecdn", 3, w - 13, 0);
        } else {
            Midlet.a(graphics, "Mua", 3, w - 13, 0);
        }
        Midlet.a(graphics, "Quay l\u1ea1i", v - 3, w - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(U - 65, bg - 18, 128, 139);
        graphics.setColor(6562304);
        graphics.fillRect(U - 63, bg - 16, 125, 137);
        Midlet.a(graphics, "Ti\u1ec1n: " + ae + "$", U, bg - 8, 2);
        int n2 = 0;
        while (n2 < 5) {
            graphics.setColor(0);
            graphics.fillRect(fg + n2 * 20, bg + 10, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(fg + n2 * 20, bg + 10, 20, 20);
            graphics.drawRegion(dH[2], 0, n2 * 15, 13, 15, 0, fg + 10 + n2 * 20, bg + 20, 3);
            ++n2;
        }
        n2 = 0;
        while (n2 < 2) {
            graphics.setColor(0);
            graphics.fillRect(fg + 20 + n2 * 20, bg + 32, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(fg + 20 + n2 * 20, bg + 32, 18, 20);
            graphics.drawRegion(dH[3], 0, n2 * 15, 13, 15, 0, fg + 30 + n2 * 20, bg + 42, 3);
            ++n2;
        }
        graphics.setColor(fi[fh]);
        if (bi == 0) {
            graphics.drawRect(fg + bh * 20, bg + 10, 19, 20);
        } else {
            graphics.drawRect(fg + 20 + bh * 20, bg + 32, 19, 20);
        }
        Midlet.a(graphics, fx[bi + 1][bh], U, bg + 58, 2);
        if (fu != -1) {
            graphics.drawImage(dK[h.ae], U - 63 + aq % 2, bg + 79 + fu * 15 - aq % 2, 0);
        }
        Midlet.a(graphics, fn[bi][bh], U - 43, bg + 79, 0);
        Midlet.a(graphics, fm[bi][bh], U - 43, bg + 94, 0);
        graphics.drawImage(fz[0], U - 66, bg - 19, 20);
        graphics.drawImage(fz[1], U + 65, bg - 19, 24);
        graphics.drawImage(fz[2], U - 66, bg + 123, 36);
        graphics.drawImage(fz[3], U + 65, bg + 123, 40);
    }

    protected final void pointerDragged(int n2, int n3) {
        r = n2;
        s = n3;
    }

    protected final void pointerPressed(int n2, int n3) {
        r = n2;
        s = n3;
    }

    protected final void pointerReleased(int n2, int n3) {
        q = true;
        r = n2;
        s = n3;
    }

    /*
     * Unable to fully structure code
     */
    public final void run() {
        if (!q.p) {
            q.p = true;
            q.B = 13;
            b.c = 8;
        }
        try {
            while (q.p) {
                var1_1 = System.currentTimeMillis();
                switch (q.B) {
                    case 1: {
                        if (++q.aq == 10000) {
                            q.aq = 0;
                        }
                        if (h.aA <= 0 && !q.bI) {
                            h.aA = 0;
                            q.bI = true;
                            q.a("\u0110\u00e3 h\u1ebft MP");
                        }
                        if (q.bI && h.aA > 0) {
                            q.bI = false;
                        }
                        if (q.bc == -1 && q.aq % 50 == 0) {
                            q.bc = 0;
                        }
                        if (h.bm > 0) {
                            ++h.bm;
                        }
                        if (i.j) {
                            ++i.B;
                        }
                        if (h.ag < 0) {
                            if (k.g != -1) {
                                q.H();
                                h.aF = h.ag = k.m - 1;
                                h.aG = h.ah = k.n;
                                Midlet.f = true;
                                q.c(k.g);
                                break;
                            }
                        } else if (h.ag >= k.c - 24) {
                            if (k.h != -1) {
                                q.H();
                                if (k.i != -1 && h.ah > k.j) {
                                    h.ag = 0;
                                    h.aF = 0;
                                    h.aG = h.ah = k.r;
                                    Midlet.f = true;
                                    q.c(k.i);
                                    break;
                                }
                                h.aF = h.ag = k.o;
                                h.aG = h.ah = k.p;
                                Midlet.f = true;
                                q.c(k.h);
                                break;
                            }
                        } else if (h.ah + h.aj >= k.d && h.ak != 6) {
                            if (k.l != -1) {
                                System.out.println(k.l);
                                q.H();
                                h.aF = h.ag = k.u;
                                h.aG = h.ah = k.v;
                                Midlet.f = true;
                                q.c(k.l);
                                h.ak = 4;
                                break;
                            }
                        } else if (h.ah < 0 && k.k != -1) {
                            q.H();
                            h.aF = h.ag = k.s;
                            h.aG = h.ah = k.t;
                            Midlet.f = true;
                            q.c(k.k);
                            h.ak = 3;
                            h.aj = -3;
                            h.ai = 0;
                            break;
                        }
                        q.ab();
                        q.Q();
                        if (q.O()) break;
                        if (!q.a && q.an != null) {
                            var3_3 = 0;
                            while (var3_3 < 2) {
                                if (q.aq % (var3_3 + 2 << 3) == 0) {
                                    v0 = var3_3;
                                    q.C[v0] = q.C[v0] + 1;
                                    if (q.C[var3_3] > q.S + (q.an.getWidth() >> 1)) {
                                        q.C[var3_3] = -(q.an.getWidth() >> 1);
                                    }
                                }
                                ++var3_3;
                            }
                        }
                        if (h.ak != 6) ** GOTO lbl87
                        q.b();
                        q.c();
                        ** GOTO lbl586
lbl87:
                        // 1 sources

                        if (!i.j || i.b || i.l == -1) ** GOTO lbl123
                        if (i.B > i.C || !q.u[i.l]) ** GOTO lbl121
                        i.l = -1;
                        q.b();
                        q.c();
                        i.b = true;
                        if (q.ap == 22) {
                            f.j = 3;
                            f.e = -2;
                        } else {
                            i.t = 3;
                            i.o = -2;
                        }
                        i.B = i.C + 1;
                        o.a.c();
                        if (q.ap == 0) {
                            q.f(33);
                        }
                        if (q.ap == 7) {
                            q.f(34);
                        }
                        if (q.ap == 22) {
                            q.f(35);
                        }
                        if (q.ap == 4) {
                            q.f(36);
                        }
                        if (q.ap == 11) {
                            q.f(37);
                        }
                        if (q.ap == 12) {
                            q.f(38);
                        }
                        if (q.ap == 17) {
                            q.f(39);
                        }
                        if (q.ap == 14 && !d.g) {
                            q.f(40);
                        }
                        if (q.ap == 14 && d.g) {
                            h.as = -1;
                            q.f(41);
                        }
                        ** GOTO lbl586
lbl121:
                        // 1 sources

                        if (i.B == i.C) {
                            q.v();
                        }
lbl123:
                        // 4 sources

                        if (i.b && i.B <= i.C && i.l != -1) {
                            if (q.u[i.l]) {
                                q.u[i.l] = false;
                                q.t[i.l] = false;
                                i.l = -1;
                                o.a.removeAllElements();
                                i.B = i.C + 1;
                            } else if (i.B == i.C) {
                                q.v();
                            }
                        }
                        if (!q.aQ) ** GOTO lbl142
                        if (q.u[5]) {
                            q.c();
                            q.K();
                            if (q.ec == q.eb) {
                                q.aQ = false;
                                q.F();
                            }
                        }
                        q.A = true;
                        ** GOTO lbl586
lbl142:
                        // 1 sources

                        if (!q.u[12]) ** GOTO lbl148
                        q.ak();
                        q.B = 5;
                        q.b();
                        q.c();
                        ** GOTO lbl235
lbl148:
                        // 1 sources

                        if (h.ak != 15) ** GOTO lbl168
                        if (q.u[4]) {
                            q.u[4] = false;
                            if (q.a(h.bd - h.bf) < 20 && h.bc < 11 && !h.bg) {
                                h.bg = true;
                                h.a(++h.bc);
                            }
                        }
                        if (q.u[6]) {
                            q.u[6] = false;
                            if (q.a(h.bd - h.be) < 20 && h.bc < 11 && !h.bg) {
                                h.bg = true;
                                h.a(++h.bc);
                            }
                        }
                        if (q.u[5]) {
                            q.u[5] = false;
                            q.bb[0] = 0;
                            h.aj = 0;
                            h.ai = 0;
                            h.ak = 4;
                            h.av = 0;
                        }
                        ** GOTO lbl235
lbl168:
                        // 1 sources

                        if (!q.u[0]) ** GOTO lbl174
                        q.ad();
                        q.B = 15;
                        q.A = true;
                        q.u[0] = false;
                        ** GOTO lbl235
lbl174:
                        // 1 sources

                        if (!q.u[6]) ** GOTO lbl178
                        h.aE = 2;
                        q.u[6] = false;
                        ** GOTO lbl235
lbl178:
                        // 1 sources

                        if (!q.u[4]) ** GOTO lbl182
                        h.aE = -2;
                        q.u[4] = false;
                        ** GOTO lbl235
lbl182:
                        // 1 sources

                        if (!q.u[9]) ** GOTO lbl185
                        q.u[9] = false;
                        ** GOTO lbl586
lbl185:
                        // 1 sources

                        if (!q.u[7]) ** GOTO lbl188
                        q.u[7] = false;
                        ** GOTO lbl235
lbl188:
                        // 1 sources

                        if (!q.u[10]) ** GOTO lbl212
                        q.u[10] = false;
                        if (h.ak == 6 || h.ay >= h.az) ** GOTO lbl235
                        var3_3 = 0;
                        while (var3_3 < 3) {
                            if (q.bl[0][var3_3] <= 0) ** GOTO lbl209
                            q.e(3, 0);
                            v1 = q.bl[0];
                            v2 = var3_3;
                            v1[v2] = v1[v2] - 1;
                            var4_10 = 0;
                            while (var4_10 < 5) {
                                o.b.addElement(new j(h.ag - 14 + var4_10 * 7, h.ah - 12, 44));
                                var4_10 = (byte)(var4_10 + 1);
                            }
                            q.a("+" + q.fy[var3_3], 0, h.ag, h.ah - h.an, 0, -1);
                            if ((h.ay += q.fy[var3_3]) > h.az) {
                                h.ay = h.az;
                            }
                            q.e(0, q.L + h.ay * q.M / h.az);
                            q.A = true;
                            ** GOTO lbl235
lbl209:
                            // 1 sources

                            ++var3_3;
                        }
                        ** GOTO lbl235
lbl212:
                        // 1 sources

                        if (!q.u[11]) ** GOTO lbl235
                        q.u[11] = false;
                        if (h.ak == 6 || h.aA >= h.aB) ** GOTO lbl235
                        var3_3 = 3;
                        while (var3_3 < 6) {
                            if (q.bl[0][var3_3] <= 0) ** GOTO lbl233
                            q.e(4, 0);
                            var4_10 = 0;
                            while (var4_10 < 5) {
                                o.b.addElement(new j(h.ag - 14 + var4_10 * 7, h.ah - 12, 42));
                                var4_10 = (byte)(var4_10 + 1);
                            }
                            q.a("+" + q.fy[var3_3], 2, h.ag, h.ah - h.an, 0, -1);
                            v3 = q.bl[0];
                            v4 = var3_3;
                            v3[v4] = v3[v4] - 1;
                            if ((h.aA += q.fy[var3_3]) > h.aB) {
                                h.aA = h.aB;
                            }
                            q.e(1, q.L + h.aA * q.M / h.aB);
                            q.A = true;
                            break;
lbl233:
                            // 1 sources

                            ++var3_3;
                        }
lbl235:
                        // 13 sources

                        if (h.ak != 1) ** GOTO lbl341
                        if (!q.u[13]) ** GOTO lbl253
                        q.c();
                        if (q.K.equals("Giao ti\u1ebfp")) {
                            c.a(h.aX);
                        } else if (q.K.equals("Tr\u00f2 chuy\u1ec7n")) {
                            q.c();
                            h.z[h.aY] = -h.as;
                            q.a(h.t[h.aY], 0, h.x[h.aY], h.y[h.aY] - 44);
                        } else if (q.K.equals("N\u00f3i chuy\u1ec7n")) {
                            q.a(m.g[0], -1, h.bo, h.bp - 44);
                        } else if (q.K.equals("Ban Do")) {
                            q.am();
                            q.B = 17;
                        }
                        ** GOTO lbl586
lbl253:
                        // 1 sources

                        if (!q.t[2]) ** GOTO lbl272
                        if (f.a != 0 || h.ah != f.c || q.a(h.ag - f.b) >= 20) ** GOTO lbl263
                        h.ag = f.b;
                        h.ah = f.c - 9;
                        h.ak = 18;
                        f.a = 1;
                        q.b();
                        f.i = h.as;
                        f.k = 0;
                        ** GOTO lbl586
lbl263:
                        // 1 sources

                        h.aj = h.e != false ? -10 : -8;
                        h.ak = 3;
                        q.aK = -1;
                        h.at = 0;
                        h.aX = -1;
                        h.aY = -1;
                        q.K = h.n[1] != false && q.br[13] == false ? "Ban Do" : " ";
                        q.A = true;
                        ** GOTO lbl322
lbl272:
                        // 1 sources

                        if (q.t[4]) {
                            if (h.as == 1) {
                                h.as = -1;
                            } else {
                                h.ak = 2;
                                h.ai = -q.aC + q.aE;
                            }
                            h.aX = -1;
                            h.aY = -1;
                            q.K = h.n[1] != false && q.br[13] == false ? "Ban Do" : " ";
                            q.A = true;
                        } else if (q.t[6]) {
                            if (h.as == -1) {
                                h.as = 1;
                            } else {
                                h.ak = 2;
                                h.ai = q.aC + q.aF;
                            }
                            h.aY = -1;
                            h.aX = -1;
                            q.K = h.n[1] != false && q.br[13] == false ? "Ban Do" : " ";
                            q.A = true;
                        } else if (q.t[8]) {
                            h.at = 0;
                            h.au = 0;
                            h.ak = 8;
                        } else if (q.t[1]) {
                            h.aj = -10;
                            h.as = -1;
                            h.ai = -4;
                            h.ak = 3;
                            q.aK = -1;
                            h.at = 0;
                            h.aX = -1;
                            h.aY = -1;
                            q.K = h.n[1] != false && q.br[13] == false ? "Ban Do" : " ";
                            q.A = true;
                        } else if (q.t[3]) {
                            h.aj = -10;
                            h.as = 1;
                            h.ai = 4;
                            h.ak = 3;
                            q.aK = -1;
                            h.at = 0;
                            h.aX = -1;
                            h.aY = -1;
                            q.K = h.n[1] != false && q.br[13] == false ? "Ban Do" : " ";
                            q.A = true;
                        }
lbl322:
                        // 8 sources

                        if (q.u[5]) {
                            if (h.h) {
                                var3_3 = 0;
                                while (var3_3 < 3) {
                                    if (q.bb[var3_3] == 0) {
                                        ++var3_3;
                                        continue;
                                    }
                                    ** GOTO lbl586
                                }
                            } else {
                                h.ak = 5;
                                h.aL = 0;
                                h.at = 0;
                                h.aj = 0;
                                q.u[5] = false;
                                h.aX = -1;
                                h.aY = -1;
                                if (!h.n[1] || !q.K.equals("Ban Do")) {
                                    q.K = " ";
                                }
                                q.A = true;
                            }
                        }
                        ** GOTO lbl586
lbl341:
                        // 1 sources

                        if (h.ak == 2) {
                            if (q.t[2]) {
                                q.aK = -1;
                                h.aj = h.e != false ? -10 : -8;
                                h.ak = 3;
                                h.at = 0;
                            } else if (q.t[4]) {
                                if (h.as == 1) {
                                    h.as = -1;
                                } else {
                                    h.ai = k.a(h.ag, h.ah, 65536) ? -2 : -q.aC + h.aE + q.aE;
                                }
                            } else if (q.t[6]) {
                                if (h.as == -1) {
                                    h.as = 1;
                                } else {
                                    h.ai = k.a(h.ag, h.ah, 65536) != false ? 2 : q.aC + h.aE + q.aF;
                                }
                            }
                            if (q.u[5]) {
                                if (h.h) {
                                    var3_3 = 0;
                                    while (var3_3 < 3) {
                                        if (q.bb[var3_3] == 0) {
                                            ++var3_3;
                                            continue;
                                        }
                                        break;
                                    }
                                } else {
                                    h.ak = 5;
                                    h.aL = 0;
                                    h.at = 0;
                                    h.aj = 0;
                                    q.u[5] = false;
                                }
                            }
                        } else if (h.ak == 11) {
                            if (q.t[2]) {
                                h.aj = -10;
                                h.ak = 3;
                                h.at = 0;
                            } else if (q.t[4]) {
                                if (h.as == 1) {
                                    h.as = -1;
                                } else {
                                    h.ai = -h.aC + h.aE;
                                }
                            } else if (q.t[6]) {
                                if (h.as == -1) {
                                    h.as = 1;
                                } else {
                                    h.ai = h.aC + h.aE;
                                }
                            }
                        } else if (h.ak == 3) {
                            if (q.t[4]) {
                                if (h.as == 1) {
                                    h.as = -1;
                                } else {
                                    h.ai = -q.aC + q.aE;
                                }
                            } else if (q.t[6]) {
                                if (h.as == -1) {
                                    h.as = 1;
                                } else {
                                    h.ai = q.aC + q.aF;
                                }
                            }
                            if (q.u[5]) {
                                if (!h.g) {
                                    h.ak = 5;
                                    h.aL = 0;
                                    h.at = 0;
                                }
                                q.u[5] = false;
                            } else if (q.t[2] && h.e && h.aA > 0 && h.aj > -4 && h.at == 0) {
                                ++h.at;
                                h.aj = -7;
                                if ((h.aA -= 6) < 0) {
                                    h.aA = 0;
                                }
                                q.A = true;
                                q.a(-1, h.ag - -8, h.ah);
                                q.a(1, h.ag - 8, h.ah);
                                q.d(h.ag, h.ah);
                            }
                        } else if (h.ak == 16) {
                            if (k.a(h.ag, h.ah, 262144) && q.t[4]) {
                                q.t[4] = false;
                                h.as = -1;
                                h.ai = -5;
                                h.aj = -13;
                                q.d(h.ag, h.ah);
                            }
                            if (k.a(h.ag, h.ah, 262144) && q.t[6]) {
                                q.t[6] = false;
                                h.as = 1;
                                h.ai = 5;
                                h.aj = -13;
                                q.d(h.ag, h.ah);
                            }
                            if (k.a(h.ag, h.ah, 262144) && q.t[2]) {
                                q.t[2] = false;
                                h.ai = 0;
                                h.ak = 4;
                            }
                        } else if (h.ak == 4) {
                            if (q.u[8]) {
                                if (h.f && !h.p && h.aj > 4 && h.aj < 12) {
                                    if (h.aA >= 2) {
                                        q.e(1, q.L + (h.aA -= 2) * q.M / h.aB);
                                        h.p = true;
                                        h.aj += 10;
                                        o.a.addElement(new j(h.ag, h.ah, 18));
                                    } else {
                                        q.a("\u0110\u00e3 h\u1ebft MP");
                                    }
                                }
                                q.u[8] = false;
                            }
                            if (q.u[2]) {
                                q.c();
                                h.ah -= 3;
                                h.al = 7;
                            }
                            if (q.t[4]) {
                                if (h.as == 1) {
                                    h.as = -1;
                                } else {
                                    h.ai = -q.aC + q.aE;
                                }
                            } else if (q.t[6]) {
                                if (h.as == -1) {
                                    h.as = 1;
                                } else {
                                    h.ai = q.aC + q.aF;
                                }
                            }
                            if (q.u[5] && !h.g && h.aj > 4) {
                                if (h.h) {
                                    var3_3 = 0;
                                    while (var3_3 < 3) {
                                        if (q.bb[var3_3] == 0) {
                                            ++var3_3;
                                            continue;
                                        }
                                        break;
                                    }
                                } else {
                                    h.ak = 5;
                                    h.at = 0;
                                    h.aL = 0;
                                    q.u[5] = false;
                                }
                            }
                        } else if (h.ak == 5) {
                            if (q.t[4]) {
                                if (h.as == 1) {
                                    h.as = -1;
                                } else {
                                    h.ai = -q.aC + 2 + q.aE;
                                }
                            } else if (q.t[6]) {
                                if (h.as == -1) {
                                    h.as = 1;
                                } else {
                                    h.ai = q.aC - 2 + q.aF;
                                }
                            }
                            if (q.u[5]) {
                                h.aP = true;
                                q.u[5] = false;
                            }
                        } else if (h.ak == 18) {
                            if (q.t[2]) {
                                h.aj = -12;
                                h.ak = 21;
                                h.at = 10;
                                q.aK = -1;
                            } else if (q.t[4]) {
                                if (h.as == 1) {
                                    h.as = -1;
                                    f.i = -1;
                                } else {
                                    h.at = 1;
                                    h.ak = 19;
                                    h.at = 10;
                                    h.ai = -q.aD;
                                }
                            } else if (q.t[6]) {
                                if (h.as == -1) {
                                    h.as = 1;
                                    f.i = 1;
                                } else {
                                    h.ak = 19;
                                    h.at = 10;
                                    h.ai = q.aD;
                                }
                            } else if (q.t[8]) {
                                h.ak = 1;
                                f.i = -1;
                                f.a = 0;
                                h.ah += 9;
                            }
                        } else if (h.ak == 19) {
                            if (q.t[2]) {
                                h.aj = -12;
                                h.ak = 21;
                                q.aK = -1;
                                h.at = 10;
                            } else if (q.t[4]) {
                                if (h.as == 1) {
                                    h.as = -1;
                                    f.i = -1;
                                } else {
                                    h.ai = -q.aD;
                                }
                            } else if (q.t[6]) {
                                if (h.as == -1) {
                                    h.as = 1;
                                    f.i = 1;
                                } else {
                                    h.ai = q.aD;
                                }
                            }
                        } else if (h.ak == 21) {
                            if (q.t[4]) {
                                if (h.as == 1) {
                                    h.as = -1;
                                    f.i = -1;
                                } else {
                                    h.ai = -q.aD;
                                }
                            } else if (q.t[6]) {
                                if (h.as == -1) {
                                    h.as = 1;
                                    f.i = 1;
                                } else {
                                    h.ai = q.aD;
                                }
                            }
                        } else if (h.ak == 20) {
                            if (q.t[4]) {
                                if (h.as == 1) {
                                    h.as = -1;
                                    f.i = -1;
                                } else {
                                    h.ai = -q.aD;
                                }
                            } else if (q.t[6]) {
                                if (h.as == -1) {
                                    h.as = 1;
                                    f.i = 1;
                                } else {
                                    h.ai = q.aD;
                                }
                            }
                        } else if (h.ak == 12 && q.t[2]) {
                            h.aj = -10;
                            h.ak = 3;
                            h.ad = -1;
                            h.at = 0;
                        }
lbl586:
                        // 46 sources

                        q.N();
                        q.M();
                        q.V();
                        q.ac();
                        c.b();
                        q.R();
                        q.Z();
                        q.aa();
                        q.U();
                        q.S();
                        q.T();
                        if (q.eS != 0) {
                            ++q.eS;
                            if (q.a(h.ag - (q.eO += q.eQ)) < 30 && q.a(h.ah - (q.eP += q.eR)) < 30) {
                                h.a(a.a[18], q.eQ > 0 ? 1 : -1);
                                q.eS = 0;
                            } else {
                                if (q.eS % 3 == 0) {
                                    v5 = q.eT = q.eT == 0 ? 1 : 0;
                                }
                                if (q.eS == 50) {
                                    q.eS = 0;
                                }
                            }
                        }
                        q.L();
                        if (q.dx > 0 && ++q.dx > 30) {
                            q.dx = 1;
                        }
                        if (q.eq != -1) {
                            if (q.a((q.eo += q.es * ++q.et) - f.b) <= 30) {
                                f.k = 5;
                                f.b = q.eo + q.es * 30;
                            }
                            if (q.a(q.eo - h.bo) <= 30) {
                                h.bt = 7;
                                h.bo = q.eo + q.es * 30;
                            }
                            if (q.et >= 10) {
                                q.eq = -1;
                            }
                            if (q.et % 2 == 0) {
                                v6 = q.er = q.er == 0 ? 1 : 0;
                            }
                            if (q.et >= 15) {
                                q.eq = -1;
                            }
                        }
                        if (q.az && !i.j) {
                            h.i = false;
                            h.k = false;
                        } else {
                            if (h.k && ++q.bo >= q.bq) {
                                q.bo = 0;
                                h.k = false;
                            }
                            if (h.i && ++q.bn >= q.bp) {
                                q.bn = 0;
                                h.i = false;
                            }
                        }
                        h.b();
                        n.c();
                        h.a();
                        h.h();
                        h.c();
                        a.b();
                        d.a();
                        i.d();
                        i.c();
                        i.f();
                        i.b();
                        f.a();
                        q.P();
                        q.Y();
                        if (q.eu == -1 || q.au[q.eu] == 0) ** GOTO lbl728
                        if (q.au[q.eu] != 1) ** GOTO lbl720
                        if (q.eu == 3) {
                            if (!k.a(q.as[q.eu], q.at[q.eu], 2)) {
                                v7 = q.eu;
                                q.at[v7] = q.at[v7] + 2;
                                if (q.aq % 3 == 0) {
                                    ++q.ev;
                                }
                                if (q.ev > 3) {
                                    q.ev = 0;
                                }
                            } else if (q.dx == 0) {
                                q.c(q.as[q.eu], q.at[q.eu] - 32);
                            }
                        } else if (!k.a(q.as[q.eu], q.at[q.eu], 2)) {
                            v8 = q.eu;
                            q.at[v8] = q.at[v8] + 6;
                        }
                        if (q.a(h.ah - (h.an >> 1) - (q.at[q.eu] - 7)) >= 20 || q.a(h.ag - q.as[q.eu]) >= 10) ** GOTO lbl728
                        q.au[q.eu] = 2;
                        h.aU = true;
                        switch (q.eu) {
                            case 0: {
                                q.a("L\u1ea5y \u0111\u01b0\u1ee3c Ashiko");
                                p.a = true;
                                q.f(2);
                                ** break;
                            }
                            case 1: {
                                ** break;
                            }
                            case 2: {
                                p.e = true;
                                q.a("L\u1ea5y \u0111\u01b0\u1ee3c cu\u1ed1n s\u00e1ch th\u1ea7n");
                                if (q.i[25]) {
                                    p.h = 9;
                                    ** break;
                                }
                                ** GOTO lbl728
                            }
                            case 3: {
                                p.f = true;
                                q.a("T\u00ecm \u0111\u01b0\u1ee3c l\u00f4ng chim ph\u01b0\u1ee3ng ho\u00e0ng l\u1eeda");
                                p.h = 9;
                                if (q.dx > 0) {
                                    q.dx = 0;
                                    ** break;
                                }
                                ** GOTO lbl728
                            }
                            case 7: {
                                q.a("L\u1ea5y \u0111\u01b0\u1ee3c 3 vi\u00ean oxi");
                                h.n[0] = true;
                                v9 = q.bl[1];
                                v9[4] = v9[4] + 3;
                                if (q.dx > 0) {
                                    q.dx = 0;
                                    ** break;
                                }
                                ** GOTO lbl728
                            }
                            case 8: {
                                q.a("L\u1ea5y \u0111\u01b0\u1ee3c b\u1ea3n \u0111\u1ed3 \u0111\u1ed9ng Tamatamo");
                                h.n[1] = true;
                                q.bl[1][5] = 1;
                                if (q.dx > 0) {
                                    q.dx = 0;
                                    ** break;
                                }
                                ** GOTO lbl728
                            }
                            case 9: {
                                q.a("L\u1ea5y \u0111\u01b0\u1ee3c 3 vi\u00ean ng\u1ecdc th\u1ebf th\u00e2n");
                                h.n[2] = true;
                                v10 = q.bl[1];
                                v10[3] = v10[3] + 3;
                                if (q.dx > 0) {
                                    q.dx = 0;
                                    ** break;
                                }
                                ** GOTO lbl728
                            }
                            default: {
                                ** break;
                            }
                        }
lbl719:
                        // 8 sources

                        ** GOTO lbl728
lbl720:
                        // 1 sources

                        v11 = q.eu;
                        q.au[v11] = q.au[v11] + 1;
                        v12 = q.eu;
                        q.at[v12] = q.at[v12] - 3;
                        if (q.au[q.eu] > 40) {
                            q.au[q.eu] = 0;
                            if (q.eu == 1) {
                                q.f(14);
                            }
                        }
lbl728:
                        // 13 sources

                        c.d();
                        if (q.ap == 17) {
                            if (q.bA[1] == -1) {
                                if (q.gE[1] < 288) {
                                    q.gE[1] = q.gE[1] + 8;
                                }
                                if (h.ag + h.am >= 1644) {
                                    h.ag = 1644 - h.am;
                                }
                            }
                            if (q.bA[1] == 1) {
                                if (q.gE[1] > 248) {
                                    q.gE[1] = q.gE[1] - 8;
                                } else {
                                    q.gE[1] = 248;
                                    q.bA[1] = 10;
                                }
                            }
                        }
                        if (q.ap == 14) {
                            if (q.bA[2] == -1) {
                                if (q.gE[2] < 408) {
                                    q.gE[2] = q.gE[2] + 8;
                                }
                                if (h.ag + h.am >= 682) {
                                    h.ag = 682 - h.am;
                                }
                            }
                            if (q.bA[2] == 1) {
                                if (q.gE[2] > 360) {
                                    q.gE[2] = q.gE[2] - 8;
                                } else {
                                    q.gE[2] = 360;
                                    q.bA[2] = 10;
                                }
                            }
                        }
                        if (q.ap == 8) {
                            if (q.bA[0] != 10 && q.bA[0] != -1 && a.k[38] == -1 && h.ah == 840 && h.ag >= 624 && !q.az) {
                                q.f(9);
                            }
                            if (q.bA[0] == -1 && h.ak == 1 && h.ah == 792) {
                                q.bA[0] = 1;
                            }
                            if (q.bA[0] == -1) {
                                if (q.gE[0] < 840) {
                                    q.gE[0] = q.gE[0] + 8;
                                }
                                if (h.ag + h.ao > 696 && h.ah >= 696) {
                                    h.ag = 696 - h.ao;
                                    if (h.ak == 5) {
                                        h.ag -= 10;
                                        h.ak = 1;
                                    }
                                }
                            }
                            if (q.bA[0] == 1) {
                                q.gE[0] = q.gE[0] > 800 ? q.gE[0] - 8 : 800;
                            }
                            if (q.by != 0) {
                                if (q.bz == -1 && q.gC < 900) {
                                    ++q.gC;
                                }
                                if (q.bz == 1) {
                                    if (q.gC > 840) {
                                        --q.gC;
                                    }
                                    if (q.gC == 840) {
                                        q.bz = 0;
                                    }
                                }
                            }
                        }
                        if (i.j || q.az) {
                            q.gJ[1] = -1;
                            q.gJ[0] = -1;
                        }
                        if (q.gJ[0] != -1 || q.gJ[1] != -1) {
                            if (q.gJ[0] == 1) {
                                if (q.aq % 7 == 0) {
                                    q.gI[0] = q.gI[0] - 1;
                                }
                                q.gG[0] = q.gG[0] + q.gI[0];
                                q.gK[0] = q.gK[0] + 1;
                                if (q.gK[0] == 3) {
                                    q.gK[0] = 0;
                                }
                                if (q.gG[0] <= q.N * 24) {
                                    q.gJ[0] = -1;
                                }
                                if (q.a(h.ag - q.gG[0]) < 20 && q.a(h.ah - q.gH[0]) < 20 && !h.m) {
                                    h.a(d.c / 4, -1);
                                    h.m = true;
                                    o.b.addElement(new j(h.ag - 15, h.ah - 25, 23));
                                }
                            }
                            if (q.gJ[1] == 1) {
                                if (q.aq % 7 == 0) {
                                    q.gI[1] = q.gI[1] + 1;
                                }
                                q.gG[1] = q.gG[1] + q.gI[1];
                                q.gK[1] = q.gK[1] + 1;
                                if (q.gK[1] == 3) {
                                    q.gK[1] = 0;
                                }
                                if (q.gG[1] >= q.P * 24) {
                                    q.gJ[1] = -1;
                                }
                                if (q.a(h.ag - q.gG[1]) < 20 && q.a(h.ah - q.gH[1]) < 20 && !h.m) {
                                    h.a(100, 1);
                                    h.m = true;
                                    o.b.addElement(new j(h.ag + 15, h.ah - 25, 23));
                                }
                            }
                        }
                        q.aq();
                        e.a();
                        o.b.b();
                        o.a.b();
                        if (!q.cV || ++q.cW < 150) break;
                        q.cV = false;
                        break;
                    }
                    case 2: {
                        if (q.bf >= q.be) break;
                        q.bf += 5;
                        break;
                    }
                    case 3: {
                        e.a();
                        if (q.ci > 0) {
                            --q.ci;
                        }
                        if (q.cj > 0) {
                            --q.cj;
                        }
                        if (++q.aq == 10000) {
                            q.aq = 0;
                        }
                        if (q.bW == 40) {
                            ++q.eY;
                        }
                        if (q.eY >= q.eZ.length) {
                            q.eY = 0;
                        }
                        if (q.ce <= q.cc && q.cf >= q.cd) ** GOTO lbl833
                        q.b();
                        q.c();
                        ** GOTO lbl884
lbl833:
                        // 1 sources

                        if (q.u[4]) {
                            if (--q.cb < 0) {
                                q.cb = q.bV.length - 1;
                            }
                            q.u[4] = false;
                        }
                        if (q.u[6]) {
                            if (++q.cb == q.bV.length) {
                                q.cb = 0;
                            }
                            q.u[6] = false;
                        }
                        if (!q.u[5]) ** GOTO lbl884
                        q.c();
                        if (q.cb != 0) ** GOTO lbl853
                        if (Midlet.a()) {
                            q.g(8);
                            q.B = 7;
                        } else {
                            q.I();
                            Midlet.f = true;
                            q.dX = System.currentTimeMillis();
                            q.c(16);
                        }
                        ** GOTO lbl883
lbl853:
                        // 1 sources

                        if (q.cb != 1) ** GOTO lbl861
                        if (!Midlet.a()) ** GOTO lbl884
                        q.a(false);
                        q.I();
                        Midlet.e = true;
                        q.dX = System.currentTimeMillis();
                        q.c(q.ap);
                        ** GOTO lbl883
lbl861:
                        // 1 sources

                        if (q.cb == 2) {
                            q.I();
                            q.aX = true;
                            q.B = 2;
                            q.ae();
                            Midlet.d = 11;
                            q.ap = 16;
                            e.a(16);
                            new Thread(Midlet.b).start();
                        } else if (q.cb == 3) {
                            try {
                                Midlet.b.platformRequest("http://wapvn.mobi");
                                Midlet.b.notifyDestroyed();
                            }
                            catch (ConnectionNotFoundException v13) {
                                var3_4 = v13;
                                v13.printStackTrace();
                            }
                        } else if (q.cb == 4) {
                            Midlet.b.notifyDestroyed();
                        }
lbl883:
                        // 9 sources

                        q.u[5] = false;
lbl884:
                        // 4 sources

                        if (q.ax == 0) {
                            q.ax = 960;
                            q.ay = 48;
                            q.dM = 1;
                            q.dN = 1;
                        }
                        if ((q.ax += q.dM) > q.dQ) {
                            q.ax = q.dQ;
                            q.dM = -q.dM;
                        }
                        if (q.ax < 24) {
                            q.ax = 24;
                            q.dM = -q.dM;
                        }
                        if (q.ay < 0) {
                            q.ay = 0;
                            q.dN = -q.dN;
                        }
                        if (q.ay > q.dR) {
                            q.ay = q.dR;
                            q.dN = -q.dN;
                        }
                        if ((q.N = q.ax / k.f - 1) < 0) {
                            q.N = 0;
                        }
                        q.O = q.ay / k.f;
                        q.P = q.N + q.cA;
                        q.Q = q.O + q.cB;
                        if (q.O < 0) {
                            q.O = 0;
                        }
                        if (q.Q > k.b - 1) {
                            q.Q = k.b - 1;
                        }
                        if (q.bW == 24) {
                            q.cj = 3;
                            q.ci = 2;
                        }
                        if (q.bW == 40) {
                            if (q.ce > q.cc) {
                                q.ce -= 4;
                            }
                            if (q.cf < q.cd) {
                                q.cf += 4;
                            }
                            if (q.ch > q.cg) {
                                q.ch -= 2;
                            }
                        }
                        if (q.bW < 40) {
                            ++q.bW;
                        }
                        if (q.bW < 24) {
                            q.bX = 0;
                            break;
                        }
                        q.bX = 1;
                        break;
                    }
                    case 4: {
                        if (q.fe > 51) {
                            q.fc = null;
                            q.ap();
                            q.B = 10;
                        }
                        q.v = q.o.getWidth();
                        q.w = q.o.getHeight();
                        ++q.fe;
                        break;
                    }
                    case 5: {
                        if (++q.fC > 10000) {
                            q.fC = 0;
                        }
                        var3_5 = false;
                        if (q.u[13]) {
                            var3_5 = true;
                            q.fz = null;
                            k.c();
                            q.B = 1;
                            q.A = true;
                        } else if (q.u[5] || q.u[12]) {
                            var3_5 = true;
                            if (q.fB == 0) {
                                q.ah();
                                q.B = 8;
                            }
                            if (q.fB == 1) {
                                q.ad();
                                q.B = 15;
                            }
                            if (q.fB == 2) {
                                if (h.ak >= 18) {
                                    q.g(15);
                                    q.B = 7;
                                } else if (q.ap == 14 && !d.g) {
                                    q.g(14);
                                    q.B = 7;
                                } else if (q.gb == 19 || q.gb == 20 || q.gb == 21) {
                                    q.g(11);
                                    q.B = 7;
                                } else {
                                    q.al();
                                    q.B = 6;
                                }
                            }
                            if (q.fB == 3) {
                                q.fz = null;
                                k.c();
                                q.z();
                                q.g(5);
                                switch (p.h) {
                                    case 1: {
                                        v14 = "T\u00ecm Ashiko \u1edf khu \u0111\u00e1 \u0111\u1ecf Akai r\u1ed3i quay v\u1ec1 g\u1eb7p th\u1ea7y Matsumoto t\u1ea1i ch\u1ed7 h\u1eb9n";
                                        break;
                                    }
                                    case 2: {
                                        v14 = "T\u00ecm b\u00ed k\u00edp h\u01b0\u1edbng d\u1eabn s\u1eed d\u1ee5ng Ashiko \u1edf n\u01a1i n\u00f3ng nh\u1ea5t r\u1ed3i quay v\u1ec1 g\u1eb7p th\u1ea7y Matsumoto";
                                        break;
                                    }
                                    case 3: {
                                        v14 = "Gi\u1ebft 20 Thi\u00ean T\u00ea b\u00ean khu \u0111\u00e1 \u0111\u1ecf Aiko r\u1ed3i quay v\u1ec1 g\u1eb7p ng\u01b0\u1eddi g\u00e1c c\u1ed5ng l\u00e0ng Echigo";
                                        break;
                                    }
                                    case 4: {
                                        v14 = "Gi\u1ea3i c\u1ee9u Yoshida(bi\u1ec7t danh Kh\u00f3i)tr\u01b0\u1eddng Ikazuki";
                                        break;
                                    }
                                    case 5: {
                                        v14 = "Quay v\u1ec1 g\u1eb7p th\u1ea7y Matsu t\u1ea1i khu \u0111\u00e1 \u0111\u1ecf Akai";
                                        break;
                                    }
                                    case 6: {
                                        v14 = "L\u00ean \u0111\u01b0\u1eddng \u0111\u1ebfn l\u00e0ng Oshin g\u1eb7p c\u00f4 Toyotomi nh\u1eadn nhi\u1ec7m v\u1ee5 m\u1edbi";
                                        break;
                                    }
                                    case 7: {
                                        v14 = "Gi\u1ebft c\u00e1 m\u1ed9t s\u1eebng \u1edf d\u00f2ng s\u00f4ng b\u0103ng t\u00ecm cu\u1ed1n s\u00e1ch th\u1ea7n gi\u00fap c\u00f4 Toyotomi";
                                        break;
                                    }
                                    case 8: {
                                        v14 = "T\u00ecm l\u00f4ng chim c\u1ee7a con ph\u01b0\u1ee3ng ho\u00e0ng l\u1eeda r\u1ed3i quay v\u1ec1 g\u1eb7p c\u00f4 Toyotomi \u1edf l\u00e0ng Oshin";
                                        break;
                                    }
                                    case 9: {
                                        v14 = "Quay v\u1ec1 g\u1eb7p c\u00f4 Toyotomi \u1edf l\u00e0ng Oshin";
                                        break;
                                    }
                                    case 10: {
                                        v14 = "L\u00ean \u0111\u01b0\u1eddng \u0111\u1ebfn phong \u1ea5n Ounio \u0111\u1ec3 ti\u00eau di\u1ec7t qu\u1ef7 v\u01b0\u01a1ng Mumutaro";
                                        break;
                                    }
                                    case 11: {
                                        v14 = "T\u00ecm th\u00fa c\u01b0\u1ee1i \u0111\u1ec3 v\u01b0\u1ee3t qua c\u00e1nh \u0111\u1ed3ng Hiya";
                                        break;
                                    }
                                    case 12: {
                                        v14 = "D\u00f9ng Ashiko nh\u1ea3y l\u00ean ph\u00eda tr\u00ean v\u00e1ch \u0111\u00e1 g\u1ea1t c\u1ea7n c\u1ee9u Kh\u00f3i r\u1ed3i \u0111\u1ebfn gi\u00fap Kh\u00f3i tho\u00e1t kh\u1ecfi hang n\u00fai Kurai";
                                        break;
                                    }
                                    default: {
                                        v14 = "Kh\u00f4ng c\u00f3 nhi\u1ec7m v\u1ee5";
                                    }
                                }
                                q.b(v14);
                                q.bt = q.V - 40;
                                q.B = 7;
                            }
                            if (q.fB == 4) {
                                if (h.ak != 1) {
                                    q.fz = null;
                                    k.c();
                                    q.z();
                                    q.g(5);
                                    q.b("B\u1ea1n ch\u1ec9 c\u00f3 th\u1ec3 l\u01b0u v\u00e0 tho\u00e1t khi nh\u00e2n v\u1eadt \u0111ang \u0111\u1ee9ng y\u00ean.");
                                    q.bt = q.V - 40;
                                    q.B = 7;
                                } else {
                                    q.aW = true;
                                    q.p = false;
                                }
                            }
                        } else if (q.u[8]) {
                            var3_5 = true;
                            if (++q.fB > 4) {
                                q.fB = 0;
                            }
                        } else if (q.u[2]) {
                            var3_5 = true;
                            if (--q.fB < 0) {
                                q.fB = 4;
                            }
                        } else if (q.u[4]) {
                            var3_5 = true;
                        } else if (q.u[6]) {
                            var3_5 = true;
                        }
                        if (!var3_5) break;
                        q.c();
                        break;
                    }
                    case 6: {
                        if (++q.fQ > 10000) {
                            q.fQ = 0;
                        }
                        if (q.fX != q.fR || q.fY != q.fS) {
                            q.fT = q.fR - q.fX << 1;
                            q.fU = q.fS - q.fY << 1;
                            q.fV &= 15;
                            q.fY += (q.fW += q.fU) >> 4;
                            q.fW &= 15;
                            if ((q.fX += (q.fV += q.fT) >> 4) < -40) {
                                q.fX = -40;
                            }
                            if (q.fX > q.fZ) {
                                q.fX = q.fZ;
                            }
                            if (q.fY < -40) {
                                q.fY = -40;
                            }
                            if (q.fY > q.ga) {
                                q.fY = q.ga;
                            }
                        }
                        var3_6 = false;
                        if (q.u[5] || q.u[12]) {
                            q.c();
                            if (q.fP != -1) {
                                if (!q.br[q.fP]) {
                                    q.g(1);
                                    q.B = 7;
                                } else if (q.fP <= 2 && (p.h == 4 || p.h == 12) && q.i[7]) {
                                    q.g(10);
                                    q.B = 7;
                                } else if (q.gb == q.fP) {
                                    q.g(3);
                                    q.B = 7;
                                } else {
                                    q.g(2);
                                    q.B = 7;
                                }
                            }
                        }
                        if (q.u[13]) {
                            q.an();
                            q.fz = null;
                            k.c();
                            q.B = 1;
                            q.c();
                            q.b();
                            q.u[12] = true;
                            break;
                        }
                        if (q.t[2]) {
                            if ((q.fO -= 4) < -20) {
                                q.fO = -20;
                            }
                            var3_6 = true;
                        }
                        if (q.t[8]) {
                            if ((q.fO += 4) > 279) {
                                q.fO = 279;
                            }
                            var3_6 = true;
                        }
                        if (q.t[4]) {
                            if ((q.fN -= 4) < -20) {
                                q.fN = -20;
                            }
                            var3_6 = true;
                        }
                        if (q.t[6]) {
                            if ((q.fN += 4) > 279) {
                                q.fN = 279;
                            }
                            var3_6 = true;
                        }
                        if (!var3_6) break;
                        q.fR = q.fN - q.U;
                        q.fS = q.fO - q.V;
                        q.ao();
                        break;
                    }
                    case 7: {
                        if (++q.gu < 10) {
                            q.c();
                        }
                        if (q.u[12]) {
                            q.c();
                            switch (q.gs) {
                                case 1: 
                                case 3: 
                                case 4: 
                                case 10: {
                                    q.B = 6;
                                    break;
                                }
                                case 5: 
                                case 6: 
                                case 13: {
                                    q.fz = null;
                                    h.bh = false;
                                    if (q.gs == 13) {
                                        q.f(29);
                                    }
                                    q.B = 1;
                                    break;
                                }
                                case 8: {
                                    q.fz = null;
                                    q.I();
                                    Midlet.f = true;
                                    q.dX = System.currentTimeMillis();
                                    q.c(16);
                                    break;
                                }
                                case 2: {
                                    if (q.gt >= q.ae) {
                                        q.g(4);
                                        break;
                                    }
                                    q.e(2, 0);
                                    q.ae -= q.gt;
                                    q.an();
                                    q.fz = null;
                                    k.c();
                                    q.H();
                                    h.aF = h.ag = q.fI[q.fP] * 24;
                                    h.aG = h.ah = q.fJ[q.fP] * 24;
                                    Midlet.f = true;
                                    q.c(q.fP);
                                    break;
                                }
                                case 11: 
                                case 14: 
                                case 15: {
                                    q.fz = null;
                                    k.c();
                                    q.B = 1;
                                    q.A = true;
                                }
                            }
                        }
                        if (q.u[13]) {
                            q.c();
                            switch (q.gs) {
                                case 1: {
                                    break;
                                }
                                case 2: {
                                    q.B = 6;
                                    break;
                                }
                                case 7: {
                                    q.B = 9;
                                    break;
                                }
                                case 8: {
                                    q.cb = 1;
                                    q.B = 3;
                                    break;
                                }
                                case 9: {
                                    q.B = 16;
                                    break;
                                }
                                case 12: {
                                    q.fz = null;
                                    q.fz = null;
                                    k.c();
                                    q.B = 1;
                                }
                            }
                        }
                        q.A = true;
                        break;
                    }
                    case 14: {
                        if (++q.fC > 10000) {
                            q.fC = 0;
                        }
                        if (q.u[12]) {
                            h.aN = 0;
                            ++h.aM;
                            switch (q.gy) {
                                case 0: {
                                    h.az += h.c[h.aM - 25];
                                    break;
                                }
                                case 1: {
                                    h.aw += h.a[h.aM - 25];
                                    h.ax += 4;
                                    h.aH += 6;
                                    h.aI += 6;
                                    h.aJ += 6;
                                    break;
                                }
                                case 2: {
                                    h.aB += h.d[h.aM - 25];
                                }
                            }
                            h.ay = h.az;
                            h.aA = h.aB;
                            q.c();
                            q.B = 1;
                        }
                        if (q.u[2]) {
                            q.c();
                            --q.gy;
                        }
                        if (q.u[8]) {
                            q.c();
                            ++q.gy;
                        }
                        if (q.gy < 0) {
                            q.gy = 2;
                        }
                        if (q.gy > 2) {
                            q.gy = 0;
                        }
                        q.A = true;
                        break;
                    }
                    case 8: {
                        q.aj();
                        break;
                    }
                    case 9: {
                        if (++q.aq == 10000) {
                            q.aq = 0;
                        }
                        if (q.fu == -1) {
                            if (q.fh > 0) {
                                --q.fh;
                            }
                            if (q.u[5] || q.u[12]) {
                                q.c();
                                q.fu = 0;
                            }
                            if (q.u[13]) {
                                q.ag();
                                q.c();
                                q.b();
                                q.B = 1;
                                q.A = true;
                                break;
                            }
                            if (q.u[2]) {
                                q.c();
                                if (q.bi == 1) {
                                    q.bi = 0;
                                }
                            }
                            if (q.u[8]) {
                                q.c();
                                if (q.bi == 0) {
                                    q.bi = 1;
                                    if (q.bh < 0) {
                                        q.bh = 0;
                                    }
                                    if (q.bh > 4) {
                                        q.bh = 4;
                                    }
                                }
                            }
                            if (q.u[4]) {
                                q.c();
                                if (--q.bh < 0) {
                                    q.bh = q.bi == 0 ? 5 : 4;
                                }
                            }
                            if (!q.u[6]) break;
                            q.c();
                            if (q.bi == 0 && ++q.bh == 6) {
                                q.bh = 0;
                            }
                            if (q.bi != 1 || q.bh != 5) break;
                            q.bh = 0;
                            break;
                        }
                        if (q.u[2] || q.u[8]) {
                            q.fu = q.fu == 0 ? 1 : 0;
                            q.bj = q.fu;
                            q.c();
                        }
                        if (q.u[13]) {
                            q.u[13] = false;
                            q.fu = -1;
                            q.bj = 0;
                        }
                        if (!q.u[5] && !q.u[12]) break;
                        q.c();
                        if (q.bi == 0) {
                            if (q.bj == 0) {
                                if (q.bh >= 3 ? q.bl[0][3] + q.bl[0][4] + q.bl[0][5] > 99 : q.bl[0][0] + q.bl[0][1] + q.bl[0][2] > 99) break;
                                if (q.ae >= q.fk[q.bi][q.bh]) {
                                    q.e(q.bh > 2 ? 4 : 3, 0);
                                    v15 = q.bl[0];
                                    v16 = q.bh;
                                    v15[v16] = v15[v16] + 1;
                                    q.e(2, 0);
                                    q.ae -= q.fk[q.bi][q.bh];
                                    break;
                                }
                                q.g(7);
                                q.b("Kh\u00f4ng c\u00f3 \u0111\u1ee7 ti\u1ec1n: " + q.fk[q.bi][q.bh] + "$. B\u1ea1n ch\u1ec9 c\u00f3: " + q.ae + "$. H\u00e3y \u0111i t\u00ecm th\u00eam.");
                                q.bt = q.bg;
                                q.B = 7;
                                break;
                            }
                            b.a("TEAM BUY 41000 " + q.fq[q.bi][q.bh], q.fr[q.bi][q.bh], q.bh + 1);
                            break;
                        }
                        if (h.aM < q.ff[q.bh]) {
                            q.g(7);
                            q.b("B\u1ea1n ph\u1ea3i \u0111\u1ea1t c\u1ea5p \u0111\u1ed9 " + q.ff[q.bh] + " m\u1edbi c\u00f3 th\u1ec3 mua v\u0169 kh\u00ed n\u00e0y!");
                            q.bt = q.bg;
                            q.B = 7;
                            break;
                        }
                        if (q.j(q.bh)) {
                            q.g(7);
                            q.b("Kh\u00f4ng c\u1ea7n thi\u1ebft ph\u1ea3i mua v\u0169 kh\u00ed n\u00e0y v\u00ec v\u0169 kh\u00ed n\u00e0y \u0111\u00e3 mua r\u1ed3i!");
                            q.bt = q.bg;
                            q.B = 7;
                            break;
                        }
                        if (q.bj == 0) {
                            if (q.ae >= q.fk[q.bi][q.bh]) {
                                q.e(2, 0);
                                q.ae -= q.fk[q.bi][q.bh];
                                switch (q.bh) {
                                    case 0: {
                                        h.af = 4;
                                        h.h = true;
                                        h.g = false;
                                        q.aU[q.aV] = "Fukiya";
                                        q.aS[q.aV] = 5;
                                        break;
                                    }
                                    case 1: {
                                        h.af = 5;
                                        h.g = true;
                                        h.h = false;
                                        q.aU[q.aV] = "Hebi";
                                        q.aS[q.aV] = 6;
                                        break;
                                    }
                                    case 2: {
                                        h.af = 1;
                                        h.g = false;
                                        h.h = false;
                                        q.aU[q.aV] = "L\u00f4i thi\u00ean ki\u1ebfm";
                                        q.aS[q.aV] = 2;
                                        break;
                                    }
                                    case 3: {
                                        h.g = false;
                                        h.h = false;
                                        h.af = 2;
                                        q.aU[q.aV] = "H\u00e0n B\u0103ng Ki\u1ebfm";
                                        q.aS[q.aV] = 3;
                                        break;
                                    }
                                    case 4: {
                                        h.g = false;
                                        h.h = false;
                                        h.af = 3;
                                        q.aU[q.aV] = "H\u1ecfa long ki\u1ebfm";
                                        q.aS[q.aV] = 4;
                                    }
                                }
                                q.aT[q.aV] = h.af;
                                ++q.aV;
                                q.aW = true;
                                q.a();
                                h.e();
                                q.j();
                                q.g(7);
                                q.b("Xin ch\u00fac m\u1eebng. B\u1ea1n \u0111\u00e3 c\u00f3 v\u0169 kh\u00ed m\u1edbi: " + q.bk[q.bi][q.bh] + "!");
                                q.bt = q.bg;
                                q.B = 7;
                                break;
                            }
                            q.g(7);
                            q.b("Kh\u00f4ng c\u00f3 \u0111\u1ee7 ti\u1ec1n: " + q.fk[q.bi][q.bh] + "$. B\u1ea1n ch\u1ec9 c\u00f3: " + q.ae + "$. H\u00e3y \u0111i t\u00ecm th\u00eam.");
                            q.bt = q.bg;
                            q.B = 7;
                            break;
                        }
                        b.a("TMNS BUY 41000 " + q.fq[q.bi][q.bh], q.fr[q.bi][q.bh], q.bh + 7);
                        break;
                    }
                    case 10: {
                        if (q.gq) {
                            q.gp = null;
                            System.gc();
                            q.J();
                        }
                        var3_7 = false;
                        if (q.u[12]) {
                            if (q.gr == 0) {
                                Midlet.h = false;
                            } else {
                                q.a = false;
                            }
                            var3_7 = true;
                        } else if (q.u[13]) {
                            if (q.gr == 0) {
                                Midlet.h = true;
                            } else {
                                q.a = true;
                            }
                            var3_7 = true;
                        } else if (q.u[4]) {
                            q.c();
                            if (--q.bs < 0) {
                                q.bs = 0;
                            }
                        } else if (q.u[6]) {
                            q.c();
                            if (++q.bs > 10) {
                                q.bs = 10;
                            }
                        }
                        if (!var3_7) break;
                        q.c();
                        q.b();
                        if (q.gr == 0) {
                            q.gr = 1;
                            break;
                        }
                        q.gq = true;
                        break;
                    }
                    case 11: {
                        q.X();
                        break;
                    }
                    case 12: {
                        q.X();
                        break;
                    }
                    case 13: {
                        b.b();
                        break;
                    }
                    case 15: {
                        if (q.fv > 0) {
                            --q.fv;
                        }
                        if (q.u[5] || q.u[12]) {
                            if (q.fu == 0) {
                                h.ae = 1;
                                h.g = false;
                                h.h = false;
                            } else {
                                h.af = q.aT[q.fu];
                                h.ae = 0;
                                if (h.af < 4) {
                                    h.g = false;
                                    h.h = false;
                                }
                                if (h.af == 4) {
                                    h.h = true;
                                    h.g = false;
                                }
                                if (h.af == 5) {
                                    h.g = true;
                                    h.h = false;
                                }
                            }
                            q.j();
                            h.e();
                            q.fz = null;
                            k.c();
                            q.c();
                            q.b();
                            q.A = true;
                            q.B = 1;
                            break;
                        }
                        if (q.u[13]) {
                            q.fz = null;
                            k.c();
                            q.c();
                            q.b();
                            q.A = true;
                            q.B = 1;
                            break;
                        }
                        if (q.u[4]) {
                            q.c();
                            if (--q.fu < 0) {
                                q.fu = q.aV - 1;
                            }
                        }
                        if (q.u[6]) {
                            q.c();
                            if (++q.fu == q.aV) {
                                q.fu = 0;
                            }
                        }
                        if (++q.em < 100) break;
                        q.em = 0;
                        break;
                    }
                    case 16: {
                        if (++q.aq == 10000) {
                            q.aq = 0;
                        }
                        if (q.fu == -1) {
                            if (q.fh > 0) {
                                --q.fh;
                            }
                            if (q.u[5] || q.u[12]) {
                                q.c();
                                q.fu = 0;
                            }
                            if (q.u[13]) {
                                q.ag();
                                q.c();
                                q.b();
                                q.B = 1;
                                q.A = true;
                                break;
                            }
                            if (q.u[2]) {
                                q.c();
                                if (q.bi == 1) {
                                    q.bi = 0;
                                    q.bh = q.bh == 1 ? 2 : 1;
                                }
                            }
                            if (q.u[8]) {
                                q.c();
                                if (q.bi == 0) {
                                    q.bi = 1;
                                    q.bh = q.bh < 2 ? 0 : 1;
                                }
                            }
                            if (q.u[4]) {
                                q.c();
                                if (--q.bh < 0) {
                                    q.bh = q.bi == 0 ? 4 : 1;
                                }
                            }
                            if (!q.u[6]) break;
                            q.c();
                            if (q.bi == 0 && ++q.bh == 5) {
                                q.bh = 0;
                            }
                            if (q.bi != 1 || q.bh != 2) break;
                            q.bh = 0;
                            break;
                        }
                        if (q.u[2] || q.u[8]) {
                            q.fu = q.fu == 0 ? 1 : 0;
                            q.bj = q.fu;
                            q.c();
                        }
                        if (q.u[13]) {
                            q.u[13] = false;
                            q.fu = -1;
                            q.bj = 0;
                        }
                        if (!q.u[5] && !q.u[12]) break;
                        q.c();
                        if (!(q.bi == 0 && q.bh > 0 ? true : (q.bh == 0 && q.bl[q.bi + 1][0] == 0 ? true : q.bi == 1 && q.bh == 1 && q.bl[2][1] == 0))) {
                            q.g(9);
                            q.b("Kh\u00f4ng c\u1ea7n thi\u1ebft ph\u1ea3i mua m\u00f3n h\u00e0ng n\u00e0y v\u00ec m\u00f3n h\u00e0ng n\u00e0y \u0111\u00e3 mua r\u1ed3i!");
                            q.bt = q.bg;
                            q.B = 7;
                            break;
                        }
                        if (q.bj == 0) {
                            if (q.ae >= q.fo[q.bi][q.bh]) {
                                v17 = q.bl[q.bi + 1];
                                v18 = q.bh;
                                v17[v18] = v17[v18] + 1;
                                q.e(2, 0);
                                q.ae -= q.fo[q.bi][q.bh];
                                q.aW = true;
                                q.a();
                                break;
                            }
                            q.g(9);
                            q.b("Kh\u00f4ng c\u00f3 \u0111\u1ee7 ti\u1ec1n: " + q.fo[q.bi][q.bh] + "$. B\u1ea1n ch\u1ec9 c\u00f3: " + q.ae + "$. H\u00e3y \u0111i t\u00ecm th\u00eam.");
                            q.bt = q.bg;
                            q.B = 7;
                            break;
                        }
                        b.a("TEAM BUY 41000 " + q.fq[q.bi + 2][q.bh], q.fr[q.bi + 2][q.bh], q.bi * 5 + q.bh + 12);
                        break;
                    }
                    case 17: {
                        if (++q.fQ > 10000) {
                            q.fQ = 0;
                        }
                        if (q.gd != q.gj || q.ge != q.gk) {
                            q.gn = q.gj - q.gd << 1;
                            q.go = q.gk - q.ge << 1;
                            q.gl &= 15;
                            q.ge += (q.gm += q.go) >> 4;
                            q.gm &= 15;
                            if ((q.gd += (q.gl += q.gn) >> 4) < -40) {
                                q.gd = -40;
                            }
                            if (q.gd > q.gg) {
                                q.gd = q.gg;
                            }
                            if (q.ge < -40) {
                                q.ge = -40;
                            }
                            if (q.ge > q.gf) {
                                q.ge = q.gf;
                            }
                        }
                        var3_8 = false;
                        if (q.u[13]) {
                            q.an();
                            q.fz = null;
                            k.c();
                            q.B = 1;
                            q.c();
                            q.b();
                            q.A = true;
                            break;
                        }
                        if (q.t[2]) {
                            if ((q.gi -= 4) < -20) {
                                q.gi = -20;
                            }
                            var3_8 = true;
                        }
                        if (q.t[8]) {
                            if ((q.gi += 4) > 230) {
                                q.gi = 230;
                            }
                            var3_8 = true;
                        }
                        if (q.t[4]) {
                            if ((q.gh -= 4) < -20) {
                                q.gh = -20;
                            }
                            var3_8 = true;
                        }
                        if (q.t[6]) {
                            if ((q.gh += 4) > 400) {
                                q.gh = 400;
                            }
                            var3_8 = true;
                        }
                        if (!var3_8) break;
                        q.gj = q.gh - q.U;
                        q.gk = q.gi - q.V;
                        break;
                    }
                    case 18: {
                        if (++q.aq >= 1000) {
                            q.aq = 0;
                        }
                        n.c();
                        if (q.gS != 0) ** GOTO lbl1700
                        if (!q.u[5] && !q.u[12]) ** GOTO lbl1682
                        q.c();
                        if (q.fu != m.d[q.m[q.bF]]) ** GOTO lbl1589
                        if (q.bF == 0) {
                            q.bB = true;
                        }
                        if (q.bF == 1) {
                            q.bC = true;
                        }
                        if (q.bF == 2) {
                            q.bD = true;
                            q.ag();
                            n.o = false;
                            n.c = h.ag + h.as * q.b(10, 20);
                            n.d = h.ah - 50;
                            q.B = 1;
                            q.f(23);
                        }
                        q.gS = 1;
                        q.bE = Midlet.b(m.e[21][0], 120);
                        ** GOTO lbl1682
lbl1589:
                        // 1 sources

                        q.A = true;
                        q.gS = 2;
                        v19 = new StringBuffer(String.valueOf(m.e[20][0]));
                        var3_9 = q.b(0, 6);
                        switch (var3_9) {
                            case 0: {
                                if (q.bl[0][0] > 0) {
                                    q.e(3, 0);
                                    v20 = q.bl[0];
                                    v20[0] = v20[0] - 1;
                                    v21 = "1 B\u00ecnh HP nh\u1ecf";
                                    break;
                                }
                                ** GOTO lbl1641
                            }
                            case 1: {
                                if (q.bl[0][1] > 0) {
                                    q.e(3, 0);
                                    v22 = q.bl[0];
                                    v22[1] = v22[1] - 1;
                                    v21 = "1 B\u00ecnh HP v\u1eeba";
                                    break;
                                }
                                ** GOTO lbl1641
                            }
                            case 2: {
                                if (q.bl[0][2] > 0) {
                                    q.e(3, 0);
                                    v23 = q.bl[0];
                                    v23[2] = v23[2] - 1;
                                    v21 = "1 B\u00ecnh HP to";
                                    break;
                                }
                                ** GOTO lbl1641
                            }
                            case 3: {
                                if (q.bl[0][3] > 0) {
                                    q.e(4, 0);
                                    v24 = q.bl[0];
                                    v24[3] = v24[3] - 1;
                                    v21 = "1 B\u00ecnh MP nh\u1ecf";
                                    break;
                                }
                                ** GOTO lbl1641
                            }
                            case 4: {
                                if (q.bl[0][4] > 0) {
                                    q.e(4, 0);
                                    v25 = q.bl[0];
                                    v25[4] = v25[4] - 1;
                                    v21 = "1 B\u00ecnh MP v\u1eeba";
                                    break;
                                }
                                ** GOTO lbl1641
                            }
                            case 5: {
                                if (q.bl[0][5] > 0) {
                                    q.e(4, 0);
                                    v26 = q.bl[0];
                                    v26[5] = v26[5] - 1;
                                    v21 = "1 B\u00ecnh MP to";
                                    break;
                                }
                            }
lbl1641:
                            // 8 sources

                            default: {
                                if (q.bl[1][0] > 0) {
                                    v27 = q.bl[1];
                                    v27[0] = v27[0] - 1;
                                    v21 = "1 Ashiko";
                                    break;
                                }
                                if (q.bl[1][1] > 0) {
                                    v28 = q.bl[1];
                                    v28[1] = v28[1] - 1;
                                    v21 = "1 B\u00f9a n\u1ed5";
                                    break;
                                }
                                if (q.bl[1][2] > 0) {
                                    v29 = q.bl[1];
                                    v29[2] = v29[2] - 1;
                                    v21 = "1 B\u00f9a t\u00e0ng h\u00ecnh";
                                    break;
                                }
                                if (q.bl[1][3] > 0) {
                                    v30 = q.bl[1];
                                    v30[3] = v30[3] - 1;
                                    v21 = "1 Ng\u1ecdc th\u1ebf th\u00e2n";
                                    break;
                                }
                                if (q.bl[1][4] > 0) {
                                    v31 = q.bl[1];
                                    v31[4] = v31[4] - 1;
                                    v21 = "1 Vi\u00ean oxi";
                                    break;
                                }
                                if (q.bl[2][0] > 0) {
                                    v32 = q.bl[2];
                                    v32[0] = v32[0] - 1;
                                    v21 = "1 C\u00fa th\u00f4ng th\u00e1i";
                                    break;
                                }
                                if (q.bl[2][1] > 0) {
                                    v33 = q.bl[2];
                                    v33[1] = v33[1] - 1;
                                    v21 = "1 Chim gi\u00fap vi\u1ec7c";
                                    break;
                                }
                                if ((h.aA -= 200) < 0) {
                                    h.aA = 0;
                                }
                                q.e(1, 0);
                                v21 = "200 MP";
                            }
                        }
                        q.bE = Midlet.b(v19.append(v21).append(m.e[20][1]).toString(), 120);
lbl1682:
                        // 3 sources

                        if (q.u[13]) {
                            q.ag();
                            q.c();
                            q.b();
                            if (!(q.bB && q.bC && q.bD)) {
                                q.f(22);
                            }
                            n.c = h.ag + h.as * q.b(5, 25);
                            n.d = h.ah - 50;
                            n.o = false;
                            q.B = 1;
                            q.A = true;
                            break;
                        }
                        if (q.u[2]) {
                            q.u[2] = false;
                            v34 = q.fu = q.fu-- <= 0 ? 0 : q.fu;
                        }
                        if (q.u[8]) {
                            q.u[8] = false;
                            v35 = q.fu = q.fu++ >= 2 ? 2 : q.fu;
                        }
lbl1700:
                        // 4 sources

                        if (q.gS == 1) {
                            if (q.u[5] || q.u[12]) {
                                q.c();
                                q.gS = 0;
                                q.bE = Midlet.b(m.e[q.m[++q.bF]][0], 120);
                            }
                            if (q.u[13]) {
                                q.ag();
                                q.c();
                                q.b();
                                q.f(22);
                                n.o = false;
                                n.c = h.ag + h.as * q.b(0, 30);
                                n.d = h.ah - 50;
                                q.B = 1;
                                q.A = true;
                                break;
                            }
                        }
                        if (q.gS != 2) break;
                        if (q.u[5] || q.u[12]) {
                            q.c();
                            q.gS = 0;
                            q.bE = Midlet.b(m.e[q.m[q.bF]][0], 120);
                        }
                        if (!q.u[13]) break;
                        q.ag();
                        q.c();
                        q.b();
                        q.f(22);
                        n.o = false;
                        n.c = h.ag + h.as * q.b(10, 25);
                        n.d = h.ah - 50;
                        q.B = 1;
                        q.A = true;
                    }
                }
                this.repaint();
                this.serviceRepaints();
                while (System.currentTimeMillis() - var1_1 < (long)q.bJ) {
                    Thread.yield();
                }
            }
        }
        catch (Exception v36) {
            var1_2 = v36;
            v36.printStackTrace();
        }
        q.a();
        Midlet.b.notifyDestroyed();
    }

    public static void x() {
        fc = q.c("/u/lg.png");
    }

    private static void ad() {
        int n2;
        fs = V - 40;
        ft = U - 50;
        if (h.ae == 1) {
            fu = 0;
        } else {
            n2 = 1;
            while (n2 < 7) {
                if (aT[n2] == h.af) {
                    fu = n2;
                    break;
                }
                ++n2;
            }
            h.ae = 0;
        }
        if (fz == null) {
            fz = new Image[4];
            n2 = 0;
            while (n2 < 4) {
                try {
                    q.fz[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
        if (el == null) {
            el = new Image[7];
            try {
                n2 = 0;
                while (n2 < 7) {
                    q.el[n2] = Image.createImage((String)("/u/wpt" + n2 + ".png"));
                    ++n2;
                }
                return;
            }
            catch (IOException iOException) {}
        }
    }

    private static void ae() {
        bf = 0;
        be = 0;
        fb = 0;
    }

    public static void y() {
        bg = V - 40;
        fg = U - 50;
        k.e = null;
        bi = 0;
        bh = 0;
        fu = -1;
        fp = new Image[5];
        try {
            q.fp[0] = Image.createImage((String)"/u/wpt5.png");
            q.fp[1] = Image.createImage((String)"/u/wpt6.png");
            q.fp[2] = Image.createImage((String)"/u/wpt2.png");
            q.fp[3] = Image.createImage((String)"/u/wpt3.png");
            q.fp[4] = Image.createImage((String)"/u/wpt4.png");
        }
        catch (Exception exception) {}
        if (fz == null) {
            fz = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.fz[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
        System.gc();
    }

    private static void af() {
        bg = V - 40;
        fg = U - 50;
        k.e = null;
        bi = 0;
        fu = 0;
        fp = new Image[5];
        try {
            q.fp[0] = Image.createImage((String)"/u/wpt5.png");
            q.fp[1] = Image.createImage((String)"/u/wpt6.png");
            q.fp[2] = Image.createImage((String)"/u/wpt2.png");
            q.fp[3] = Image.createImage((String)"/u/wpt3.png");
            q.fp[4] = Image.createImage((String)"/u/wpt4.png");
        }
        catch (Exception exception) {}
        if (fz == null) {
            fz = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.fz[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
        if (!bB) {
            bF = 0;
        } else if (!bC) {
            bF = 1;
        } else if (!bD) {
            bF = 2;
        }
        bE = Midlet.b(m.e[m[bF]][0], 120);
        gS = 0;
        System.gc();
    }

    private static void ag() {
        fz = null;
        fp = null;
        System.gc();
        k.c();
    }

    private static void i(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        q.c(graphics);
        q.b(graphics);
        graphics.setClip(0, 0, v, w);
        graphics.setColor(0);
        graphics.fillRect(U - 74, bg - 19, 149, 142);
        graphics.fillRect(0, w - 13, v, 13);
        if (fu == -1) {
            Midlet.a(graphics, "Ch\u1ecdn", 3, w - 13, 0);
        } else {
            Midlet.a(graphics, "Mua", 3, w - 13, 0);
        }
        Midlet.a(graphics, "Quay l\u1ea1i", v - 3, w - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(U - 73, bg - 18, 146, 139);
        graphics.setColor(6562304);
        graphics.fillRect(U - 71, bg - 16, 143, 137);
        Midlet.a(graphics, "Ti\u1ec1n: " + ae + "$", U, bg - 8, 2);
        int n2 = 0;
        while (n2 < 3) {
            graphics.setColor(0);
            graphics.fillRect(fg - 10 + n2 * 20, bg + 10, 19, 20);
            graphics.fillRect(fg + 50 + n2 * 20, bg + 10, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(fg - 10 + n2 * 20, bg + 10, 20, 20);
            graphics.drawRect(fg + 50 + n2 * 20, bg + 10, 20, 20);
            graphics.drawRegion(dH[0], 0, n2 * 15, 13, 15, 0, fg + n2 * 20, bg + 20, 3);
            graphics.drawRegion(dH[1], 0, n2 * 15, 13, 15, 0, fg + 60 + n2 * 20, bg + 20, 3);
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            graphics.setColor(0);
            graphics.fillRect(fg - 10 + n2 * 20, bg + 32, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(fg - 10 + n2 * 20, bg + 32, 18, 20);
            graphics.drawImage(fp[n2], fg + n2 * 20, bg + 42, 3);
            ++n2;
        }
        graphics.setColor(fi[fh]);
        if (bi == 0) {
            graphics.drawRect(fg - 10 + bh * 20, bg + 10, 19, 20);
        } else {
            graphics.drawRect(fg - 10 + bh * 20, bg + 32, 19, 20);
        }
        Midlet.a(graphics, bk[bi][bh], U, bg + 58, 2);
        if (fu != -1) {
            graphics.drawImage(dK[h.ae], U - 63 + aq % 2, bg + 79 + fu * 15 - aq % 2, 0);
        }
        Midlet.a(graphics, fl[bi][bh], U - 43, bg + 79, 0);
        Midlet.a(graphics, fj[bi][bh], U - 43, bg + 94, 0);
        graphics.drawImage(fz[0], U - 74, bg - 19, 20);
        graphics.drawImage(fz[1], U + 75, bg - 19, 24);
        graphics.drawImage(fz[2], U - 74, bg + 123, 36);
        graphics.drawImage(fz[3], U + 75, bg + 123, 40);
    }

    private static boolean j(int n2) {
        int n3 = -1;
        switch (n2) {
            case 0: {
                n3 = 4;
                break;
            }
            case 1: {
                n3 = 5;
                break;
            }
            case 2: {
                n3 = 1;
                break;
            }
            case 3: {
                n3 = 2;
                break;
            }
            case 4: {
                n3 = 3;
            }
        }
        n2 = 2;
        while (n2 < aT.length) {
            if (aT[n2] == -1) {
                return false;
            }
            if (aT[n2] == n3) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    private static void ah() {
        fs = V - 40;
        ft = U - 50;
        bi = 0;
        fu = 0;
        if (fz == null) {
            fz = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.fz[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
    }

    private static boolean ai() {
        if (bi == 0) {
            return false;
        }
        if (bi == 1 && fu == 0 && h.f) {
            return true;
        }
        if (bi == 2) {
            if (fu == 1 && n.l) {
                return true;
            }
            if (fu == 0 && n.m) {
                return true;
            }
        }
        return false;
    }

    private static void j(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        q.c(graphics);
        q.b(graphics);
        graphics.setClip(0, 0, v, w);
        graphics.setColor(0);
        graphics.fillRect(U - 64, fs - 19, 129, 142);
        graphics.fillRect(0, w - 13, v, 13);
        if (q.ai()) {
            Midlet.a(graphics, "H\u1ee7y", 3, w - 13, 0);
        } else {
            Midlet.a(graphics, "S\u1eed d\u1ee5ng", 3, w - 13, 0);
        }
        Midlet.a(graphics, "Quay l\u1ea1i", v - 3, w - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(U - 63, fs - 18, 126, 139);
        graphics.setColor(6562304);
        graphics.fillRect(U - 61, fs - 16, 123, 137);
        Midlet.a(graphics, "S\u1eed d\u1ee5ng Item", U, fs - 8, 2);
        int n2 = 0;
        while (n2 < 3) {
            graphics.setColor(0);
            graphics.fillRect(ft - 10 + n2 * 20, fs + 10, 19, 20);
            graphics.fillRect(ft + 50 + n2 * 20, fs + 10, 19, 20);
            graphics.fillRect(ft - 10 + n2 * 20, fs + 30, 19, 20);
            graphics.fillRect(ft + 50 + n2 * 20, fs + 30, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(ft - 10 + n2 * 20, fs + 10, 20, 20);
            graphics.drawRect(ft + 50 + n2 * 20, fs + 10, 20, 20);
            graphics.drawRect(ft - 10 + n2 * 20, fs + 30, 20, 20);
            graphics.drawRect(ft + 50 + n2 * 20, fs + 30, 20, 20);
            graphics.drawRegion(dH[0], 0, n2 * 15, 13, 15, 0, ft + n2 * 20, fs + 20, 3);
            graphics.drawRegion(dH[1], 0, n2 * 15, 13, 15, 0, ft + 60 + n2 * 20, fs + 20, 3);
            graphics.drawRegion(dH[2], 0, n2 * 15, 13, 15, 0, ft + n2 * 20, fs + 40, 3);
            if (n2 + 3 == 5 && h.n[1] || n2 + 3 != 5) {
                graphics.drawRegion(dH[2], 0, (n2 + 3) * 15, 13, 15, 0, ft + 60 + n2 * 20, fs + 40, 3);
            }
            ++n2;
        }
        n2 = 0;
        while (n2 < 2) {
            graphics.setColor(0);
            graphics.fillRect(ft + 30 + n2 * 20, fs + 50, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(ft + 30 + n2 * 20, fs + 50, 20, 20);
            graphics.drawRegion(dH[3], 0, n2 * 15, 13, 15, 0, ft + 40 + n2 * 20, fs + 60, 3);
            ++n2;
        }
        graphics.setColor(fw[fv]);
        graphics.drawRect(ft + (bi == 2 ? 30 : -10) + fu * 20, fs + 10 + bi * 20, 20, 20);
        if (bi == 1 && fu == 5 && h.n[1] || bi != 1 || fu != 5) {
            Midlet.a(graphics, fx[bi][fu], U, fs + 82, 2);
            Midlet.a(graphics, "S\u1ed1 l\u01b0\u1ee3ng: " + bl[bi][fu], U, fs + 97, 2);
        }
        if (bi == 1 && fu == 5 && !h.n[1]) {
            Midlet.a(graphics, "? ? ?", U, fs + 97, 2);
        }
        graphics.drawImage(fz[0], U - 64, fs - 19, 20);
        graphics.drawImage(fz[1], U + 65, fs - 19, 24);
        graphics.drawImage(fz[2], U - 64, fs + 123, 36);
        graphics.drawImage(fz[3], U + 65, fs + 123, 40);
    }

    private static void aj() {
        if (fv > 0) {
            --fv;
        }
        if (u[5] || u[12]) {
            if (q.ai()) {
                if (bi == 1) {
                    h.f = false;
                }
                if (bi == 2) {
                    if (fu == 0) {
                        n.m = false;
                    } else {
                        n.l = false;
                    }
                }
            } else if (bl[bi][fu] > 0) {
                if (bi != 1 || fu != 5) {
                    int[] nArray = bl[bi];
                    int n2 = fu;
                    nArray[n2] = nArray[n2] - 1;
                }
                fv = fw.length - 1;
                block0 : switch (bi) {
                    case 0: {
                        if (fu <= 2) {
                            if (h.ay >= h.az) break;
                            int n3 = 0;
                            while (n3 < 5) {
                                o.b.addElement(new j(h.ag - 14 + n3 * 7, h.ah - 12, 44));
                                n3 = (byte)(n3 + 1);
                            }
                            q.a("+" + fy[fu], 0, h.ag, h.ah - h.an, 0, -1);
                            if ((h.ay += fy[fu]) > h.az) {
                                h.ay = h.az;
                            }
                            q.e(0, L + h.ay * M / h.az);
                            break;
                        }
                        if (h.aA >= h.aB) break;
                        int n4 = 0;
                        while (n4 < 5) {
                            o.b.addElement(new j(h.ag - 14 + n4 * 7, h.ah - 12, 42));
                            n4 = (byte)(n4 + 1);
                        }
                        q.a("+" + fy[fu], 2, h.ag, h.ah - h.an, 0, -1);
                        if ((h.aA += fy[fu]) > h.aB) {
                            h.aA = h.aB;
                        }
                        q.e(1, L + h.aA * M / h.aB);
                        break;
                    }
                    case 1: {
                        switch (fu) {
                            case 0: {
                                if (p.c) {
                                    h.f = true;
                                    if (h.aA < 1) break;
                                    aF = 0;
                                    aE = 0;
                                    break;
                                }
                                int[] nArray = bl[bi];
                                int n5 = fu;
                                nArray[n5] = nArray[n5] + 1;
                                q.g(12);
                                q.b("Kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng Ashiko v\u00ec b\u1ea1n ch\u01b0a bi\u1ebft c\u00e1ch s\u1eed d\u1ee5ng!");
                                bt = bg;
                                B = 7;
                                break;
                            }
                            case 1: {
                                bm += 20;
                                int n6 = 0;
                                while (n6 < 2) {
                                    q.dF[n6] = q.c("/c/pt" + (n6 + 2) + ".png");
                                    ++n6;
                                }
                                break block0;
                            }
                            case 2: {
                                h.i = true;
                                bn = 0;
                                break;
                            }
                            case 3: {
                                h.j = true;
                                break;
                            }
                            case 4: {
                                h.k = true;
                                bo = 0;
                                break;
                            }
                            case 5: {
                                if (!h.n[1]) break;
                                q.am();
                                B = 17;
                            }
                        }
                        break;
                    }
                    case 2: {
                        n.a(fu, N * 24, q.b(10, T - 30));
                    }
                }
            }
            q.c();
        }
        if (u[13]) {
            q.c();
            q.b();
            fz = null;
            k.c();
            A = true;
            B = 1;
            return;
        }
        if (u[2]) {
            q.c();
            if (--bi < 0) {
                bi = 0;
            }
            if (bi == 1) {
                fu = fu == 0 ? 2 : 3;
            }
        }
        if (u[8]) {
            q.c();
            if (++bi >= 2) {
                bi = 2;
                fu = fu >= 3 ? 1 : 0;
            }
        }
        if (u[4]) {
            q.c();
            if (--fu < 0) {
                fu = bl[bi].length - 1;
            }
        }
        if (u[6]) {
            q.c();
            if (++fu == bl[bi].length) {
                fu = 0;
            }
        }
    }

    private static void ak() {
        fA = V - 40;
        k.e = null;
        if (fz == null) {
            fz = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.fz[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
    }

    private static void k(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(U - 64, fA - 19, 129, 120);
        graphics.fillRect(0, w - 13, v, 13);
        Midlet.a(graphics, "Ch\u1ecdn", 3, w - 13, 0);
        Midlet.a(graphics, "Quay l\u1ea1i", v - 3, w - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(U - 63, fA - 18, 126, 117);
        graphics.setColor(6562304);
        graphics.fillRect(U - 61, fA - 16, 123, 115);
        int n2 = 0;
        while (n2 < 5) {
            Midlet.a(graphics, fD[n2], x - 30, fA + n2 * 18, 0);
            ++n2;
        }
        graphics.drawImage(dK[h.ae], x - 55 + (fC % 4 > 1 ? 1 : 0), fA + fB * 18 - (fC % 4 > 1 ? 1 : 0), 0);
        graphics.drawImage(fz[0], U - 64, fA - 19, 20);
        graphics.drawImage(fz[1], U + 65, fA - 19, 24);
        graphics.drawImage(fz[2], U - 64, fA + 101, 36);
        graphics.drawImage(fz[3], U + 65, fA + 101, 40);
    }

    private static void al() {
        fZ = 280 - S;
        ga = 280 - T;
        fP = 0;
        try {
            fK = Image.createImage((String)"/wm.png");
            fL = Image.createImage((String)"/u/x.png");
        }
        catch (Exception exception) {}
        fN = fG[gb];
        fO = fH[gb];
        q.ao();
        fR = fN - U;
        fS = fO - V;
        if (fX < -40) {
            fX = -40;
        }
        if (fX > fZ) {
            fX = fZ;
        }
        if (fY < -40) {
            fY = -40;
        }
        if (fY > ga) {
            fY = ga;
        }
    }

    private static void am() {
        gg = 400 - S;
        gf = 240 - T;
        fM = q.c("/map.png");
        gj = gh - U;
        gk = gi - V;
        if (gd < -40) {
            gd = -40;
        }
        if (gd > gg) {
            gd = gg;
        }
        if (ge < -40) {
            ge = -40;
        }
        if (ge > gf) {
            ge = gf;
        }
    }

    private static void an() {
        fM = null;
        fK = null;
        fL = null;
        System.gc();
    }

    private static void l(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(0, 0, S, T + 2);
        graphics.fillRect(0, w - 13, v, 13);
        Midlet.a(graphics, gc, 3, w - 13, 0);
        Midlet.a(graphics, "Quay l\u1ea1i", v - 3, w - 13, 1);
        graphics.setClip(0, 0, S, T);
        graphics.translate(-fX, -fY);
        graphics.drawImage(fK, 0, 0, 0);
        int n2 = 0;
        while (n2 < br.length) {
            if (n2 == gb) {
                graphics.drawImage(h.A[0], fG[n2] + 10, fH[n2], 3);
            }
            if (br[n2] && (n2 != fP || fQ % 10 > 5)) {
                graphics.drawImage(fL, fG[n2], fH[n2], 3);
            }
            ++n2;
        }
        graphics.drawImage(dK[0], fN, fO, 24);
        if (fP != -1) {
            graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
            graphics.setColor(6562304);
            graphics.fillRect(x - 60, T - 35, 120, 33);
            graphics.setColor(0xBB6611);
            graphics.drawRect(x - 59, T - 34, 117, 30);
            Midlet.a(graphics, fE[fP], x, T - 25, 2);
            graphics.drawImage(fz[0], x - 60, T - 35, 20);
            graphics.drawImage(fz[1], x + 60, T - 35, 24);
            graphics.drawImage(fz[2], x - 60, T - 2, 36);
            graphics.drawImage(fz[3], x + 60, T - 2, 40);
        }
    }

    private static void ao() {
        fP = -1;
        gc = "";
        int n2 = 0;
        while (n2 < br.length) {
            if (q.a(fN - fG[n2]) < 8 && q.a(fO - fH[n2]) < 8) {
                fP = n2;
                gc = "Chuy\u1ec3n \u0111\u1ebfn";
                return;
            }
            ++n2;
        }
    }

    private static void ap() {
        try {
            Image[] imageArray = new Image[3];
            gp = imageArray;
            imageArray[0] = Image.createImage((String)"/u/m0.png");
            q.gp[1] = Image.createImage((String)"/u/m1.png");
            q.gp[2] = Image.createImage((String)"/u/m2.png");
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static void m(Graphics graphics) {
        graphics.setColor(0);
        graphics.fillRect(0, 0, v, w);
        if (gq) {
            return;
        }
        if (gr == 1) {
            graphics.drawImage(gp[2], x, y - 30, 3);
        } else {
            int n2 = 0;
            while (n2 < 10) {
                graphics.drawImage(gp[n2 < bs ? 1 : 0], x - 60 + n2 * 12, y - 30, 0);
                ++n2;
            }
            Midlet.a(graphics, "*", x - 60, y - 10, 0);
            Midlet.a(graphics, "/", x + 60, y - 10, 1);
        }
        if (gr == 0) {
            Midlet.a(graphics, "B\u1eadt \u00e2m thanh?", x, y + 15, 2);
            Midlet.a(graphics, "B\u1eadt", 4, w - 14, 0);
            Midlet.a(graphics, "T\u1eaft", v - 4, w - 14, 1);
            return;
        }
        Midlet.a(graphics, "Ch\u1ea5t l\u01b0\u1ee3ng h\u00ecnh \u1ea3nh?", x, y + 15, 2);
        Midlet.a(graphics, "Cao", 4, w - 14, 0);
        Midlet.a(graphics, "Th\u1ea5p", v - 4, w - 14, 1);
    }

    public static void b(String string) {
        gx = Midlet.a(string, 100);
        bt = V - gx.length * 9;
    }

    public static void g(int n2) {
        q.z();
        gs = n2;
        if (n2 == 1) {
            q.b("Kh\u00f4ng th\u1ec3 chuy\u1ec3n. V\u00ec b\u1ea1n ch\u01b0a t\u1eebng \u0111i b\u1ed9 \u0111\u1ebfn \u0111\u00e2y!");
            gv = "OK";
            gw = "";
            return;
        }
        if (n2 == 3) {
            q.b("Kh\u00f4ng c\u1ea7n ph\u1ea3i chuy\u1ec3n v\u00ec b\u1ea1n \u0111ang \u1edf v\u1ecb tr\u00ed n\u00e0y!");
            gv = "OK";
            gw = "";
            return;
        }
        if (n2 == 10) {
            q.b("Kh\u00f4ng th\u1ec3 chuy\u1ec3n. T\u00ednh m\u1ea1ng Kh\u00f3i \u0111ang g\u1eb7p nguy hi\u1ec3m, c\u1ea7n b\u1ea1n gi\u1ea3i c\u1ee9u!");
            gv = "OK";
            gw = "";
            return;
        }
        if (n2 == 11) {
            q.b("\u0110\u1ecba \u0111i\u1ec3m kh\u00f4ng x\u00e1c \u0111\u1ecbnh. Kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng b\u1ea3n \u0111\u1ed3");
            gv = "OK";
            gw = "";
            return;
        }
        if (n2 == 14) {
            q.b("\u0110\u1ecba \u0111i\u1ec3m \u0111\u00e3 b\u1ecb phong \u1ea5n. Kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng b\u1ea3n \u0111\u1ed3");
            gv = "OK";
            gw = "";
            return;
        }
        if (n2 == 15) {
            q.b("Kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng b\u1ea3n \u0111\u1ed3 khi \u0111ang c\u01b0\u1ee1i ch\u00f3 tuy\u1ebft");
            gv = "OK";
            gw = "";
            return;
        }
        if (n2 == 2) {
            n2 = gb < fP ? gb : fP;
            int n3 = gb > fP ? gb : fP;
            int n4 = 0;
            while (n2 <= n3) {
                n4 += fF[n2];
                ++n2;
            }
            gt = n4 = (n4 << 1) / 3;
            q.b("Chuy\u1ec3n \u0111\u1ebfn \u0111\u00e2y ph\u1ea3i tr\u1ea3 " + n4 + "$. B\u1ea1n c\u00f3 " + ae + "$. Chuy\u1ec3n?");
            gv = "Chuy\u1ec3n";
            gw = "Kh\u00f4ng";
            return;
        }
        if (n2 == 4) {
            q.b("B\u1ea1n kh\u00f4ng c\u00f3 \u0111\u1ee7 ti\u1ec1n!");
            gv = "OK";
            gw = "";
            return;
        }
        if (n2 == 5) {
            az = false;
            q.b("B\u1ea1n \u0111\u00e3 b\u1ecb tr\u1ecdng th\u01b0\u01a1ng! M\u1ea5t m\u1ed9t s\u1ed1 \u0111i\u1ec3m kinh nghi\u1ec7m!");
            gv = "OK";
            gw = "";
            return;
        }
        if (n2 == 6) {
            q.b("Level up!");
            gv = "OK";
            gw = "";
            return;
        }
        if (n2 == 7) {
            q.b("Buy OK!");
            gv = "";
            gw = "OK";
            return;
        }
        if (n2 == 8) {
            q.b("D\u1eef li\u1ec7u c\u1ee7a ph\u1ea7n ch\u01a1i c\u0169 s\u1ebd b\u1ecb m\u1ea5t. B\u1ea1n c\u00f3 ch\u1eafc mu\u1ed1n ch\u01a1i m\u1edbi?");
            gv = "C\u00f3";
            gw = "Kh\u00f4ng";
            return;
        }
        if (n2 == 9) {
            q.b("Buy OK!");
            gv = "";
            gw = "OK";
            return;
        }
        if (n2 == 12) {
            gv = "";
            gw = "OK";
            return;
        }
        if (n2 == 13) {
            q.b("Ch\u00fac m\u1eebng b\u1ea1n v\u1eeba \u0111\u01b0\u1ee3c t\u0103ng 1 c\u1ea5p");
            gv = "OK";
            gw = "";
        }
    }

    public static void z() {
        bg = V - 40;
        gu = 0;
        if (fz == null) {
            fz = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.fz[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
    }

    private static void n(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(U - 74, bg - 19, 149, 142);
        graphics.fillRect(0, w - 13, v, 13);
        if (gs == 8) {
            graphics.fillRect(0, cf + 5, S, w - cf - 5);
        }
        Midlet.a(graphics, gv, 3, w - 13, 0);
        Midlet.a(graphics, gw, v - 3, w - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(U - 73, bg - 18, 146, 139);
        graphics.setColor(6562304);
        graphics.fillRect(U - 71, bg - 16, 143, 137);
        int n2 = 0;
        while (n2 < gx.length) {
            Midlet.a(graphics, gx[n2], x, bt + n2 * 18, 2);
            ++n2;
        }
        graphics.drawImage(fz[0], U - 74, bg - 19, 20);
        graphics.drawImage(fz[1], U + 75, bg - 19, 24);
        graphics.drawImage(fz[2], U - 74, bg + 123, 36);
        graphics.drawImage(fz[3], U + 75, bg + 123, 40);
    }

    public static void A() {
        bt = y - 40;
        B = 14;
    }

    private static void o(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(U - 64, bt - 19, 129, 102);
        graphics.fillRect(0, w - 13, v, 13);
        Midlet.a(graphics, "OK", 3, w - 13, 0);
        graphics.setColor(0xBB6611);
        graphics.drawRect(U - 63, bt - 18, 126, 99);
        graphics.setColor(6562304);
        graphics.fillRect(U - 61, bt - 16, 123, 97);
        graphics.setColor(0xBB6611);
        graphics.drawRect(U - 63, bt - 18, 126, 20);
        graphics.drawRect(U - 63, bt - 18, 126, 77);
        Midlet.a(graphics, "B\u1ea1n v\u1eeba \u0111\u1ea1t c\u1ea5p " + (h.aM + 1), x, bt - 14, 2);
        int n2 = 0;
        while (n2 < 3) {
            if (n2 != gy || fC % 10 < 7) {
                Midlet.a(graphics, bu[n2], x - 40, bt + 8 + n2 * 16, 0);
            }
            ++n2;
        }
        graphics.drawImage(dK[0], x - 58 + fC % 2, bt + 8 + 18 * gy, 0);
        Midlet.a(graphics, bv[gy], x, bt + 64, 2);
    }

    public static Image c(String string) {
        Image image = null;
        try {
            image = Image.createImage((String)string);
        }
        catch (Exception exception) {}
        if (image == null) {
            System.out.println("Load image '" + string + "' :ERORR ");
        }
        return image;
    }

    public final void commandAction(Command object, Displayable displayable) {
        if (object.getLabel().equals("OK")) {
            object = String.valueOf(b.e()) + " >> " + ((TextBox)displayable).getString();
            b.a((String)object, "sms://+84914710711", 0);
        }
        Display.getDisplay((MIDlet)Midlet.b).setCurrent((Displayable)this);
        this.setFullScreenMode(true);
    }

    public static void B() {
        if (ap == 8) {
            gz = q.c("/i/l0.png");
            gA = q.c("/i/l1.png");
            gB = q.c("/i/l2.png");
            bw = 984;
            bx = 168;
            q.gD[0] = 701;
            q.gE[0] = 800;
        }
        if (ap == 17) {
            gB = q.c("/i/l2.png");
            q.gD[1] = 1640;
            q.gE[1] = 248;
            if (bA[1] != 10) {
                q.bA[1] = -1;
            }
        }
        if (ap == 14) {
            gB = q.c("/i/l2.png");
            q.gD[2] = 682;
            q.gE[2] = 360;
        }
    }

    public static void C() {
        gF = q.c("/e/lua.png");
        gG = new int[2];
        gH = new int[2];
        gI = new int[2];
        gJ = new int[2];
        gK = new int[2];
        q.gH[1] = 408;
        q.gH[0] = 408;
        q.gJ[1] = -1;
        q.gJ[0] = -1;
    }

    public static void D() {
        if (gJ[0] != -1 && gJ[1] != -1) {
            return;
        }
        int n2 = q.b(0, 3);
        if (n2 == 0) {
            return;
        }
        if (n2 == 1 && gJ[0] == -1) {
            q.gJ[0] = 1;
            q.gG[0] = P * 24;
            q.gI[0] = -1;
        }
        if (n2 == 2) {
            if (gJ[0] == -1) {
                q.gJ[0] = 1;
                q.gG[0] = P * 24;
                q.gI[0] = -1;
            }
            if (gJ[1] == -1) {
                q.gJ[1] = 1;
                q.gG[1] = N * 24;
                q.gI[1] = 1;
            }
        }
    }

    public static void E() {
        gL = q.c("/cl.png");
        gM = new int[d.i];
        gN = new int[d.i];
        gO = new int[d.i];
        gP = new int[d.i];
        gQ = new int[d.i];
        gR = new int[d.i];
    }

    public static void c(int n2, int n3, int n4, int n5, int n6) {
        int n7 = 0;
        while (n7 < d.i) {
            if (gO[n7] == 1) {
                return;
            }
            ++n7;
        }
        q.gP[n2] = n5;
        q.gQ[n2] = n6;
        q.gM[n2] = n3;
        q.gN[n2] = n4;
        q.gO[n2] = 1;
    }

    private static void aq() {
        int n2 = 0;
        while (n2 < d.i) {
            if (gO[n2] != -1) {
                int n3 = n2;
                gM[n3] = gM[n3] + gP[n2];
                int n4 = n2;
                gN[n4] = gN[n4] + gQ[n2];
                q.gR[n2] = gR[n2] == 0 ? 1 : 0;
                if (q.a(gM[n2] - h.ag) < 20 && q.a(gN[n2] - h.ah) < 20) {
                    h.a(d.c / 2, gP[n2] > 0 ? -1 : 1);
                    q.gO[n2] = -1;
                }
                if (gM[n2] > P * 24 || gM[n2] < N * 24) {
                    q.gO[n2] = -1;
                }
            }
            ++n2;
        }
    }
}


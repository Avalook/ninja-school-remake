/*
 * Decompiled with CFR 0.152.
 */
public final class l {
    private static short[] a;
    private static short[] b;
    private static int[] c;

    static {
        short[] sArray = new short[91];
        sArray[1] = 18;
        sArray[2] = 36;
        sArray[3] = 54;
        sArray[4] = 71;
        sArray[5] = 89;
        sArray[6] = 107;
        sArray[7] = 125;
        sArray[8] = 143;
        sArray[9] = 160;
        sArray[10] = 178;
        sArray[11] = 195;
        sArray[12] = 213;
        sArray[13] = 230;
        sArray[14] = 248;
        sArray[15] = 265;
        sArray[16] = 282;
        sArray[17] = 299;
        sArray[18] = 316;
        sArray[19] = 333;
        sArray[20] = 350;
        sArray[21] = 367;
        sArray[22] = 384;
        sArray[23] = 400;
        sArray[24] = 416;
        sArray[25] = 433;
        sArray[26] = 449;
        sArray[27] = 465;
        sArray[28] = 481;
        sArray[29] = 496;
        sArray[30] = 512;
        sArray[31] = 527;
        sArray[32] = 543;
        sArray[33] = 558;
        sArray[34] = 573;
        sArray[35] = 587;
        sArray[36] = 602;
        sArray[37] = 616;
        sArray[38] = 630;
        sArray[39] = 644;
        sArray[40] = 658;
        sArray[41] = 672;
        sArray[42] = 685;
        sArray[43] = 698;
        sArray[44] = 711;
        sArray[45] = 724;
        sArray[46] = 737;
        sArray[47] = 749;
        sArray[48] = 761;
        sArray[49] = 773;
        sArray[50] = 784;
        sArray[51] = 796;
        sArray[52] = 807;
        sArray[53] = 818;
        sArray[54] = 828;
        sArray[55] = 839;
        sArray[56] = 849;
        sArray[57] = 859;
        sArray[58] = 868;
        sArray[59] = 878;
        sArray[60] = 887;
        sArray[61] = 896;
        sArray[62] = 904;
        sArray[63] = 912;
        sArray[64] = 920;
        sArray[65] = 928;
        sArray[66] = 935;
        sArray[67] = 943;
        sArray[68] = 949;
        sArray[69] = 956;
        sArray[70] = 962;
        sArray[71] = 968;
        sArray[72] = 974;
        sArray[73] = 979;
        sArray[74] = 984;
        sArray[75] = 989;
        sArray[76] = 994;
        sArray[77] = 998;
        sArray[78] = 1002;
        sArray[79] = 1005;
        sArray[80] = 1008;
        sArray[81] = 1011;
        sArray[82] = 1014;
        sArray[83] = 1016;
        sArray[84] = 1018;
        sArray[85] = 1020;
        sArray[86] = 1022;
        sArray[87] = 1023;
        sArray[88] = 1023;
        sArray[89] = 1024;
        sArray[90] = 1024;
        a = sArray;
    }

    public static void a() {
        b = new short[91];
        c = new int[91];
        int n2 = 0;
        while (n2 <= 90) {
            l.b[n2] = a[90 - n2];
            l.c[n2] = b[n2] == 0 ? Integer.MAX_VALUE : (a[n2] << 10) / b[n2];
            ++n2;
        }
    }

    public static final int a(int n2, int n3) {
        int n4;
        block6: {
            int n5;
            block7: {
                block5: {
                    block4: {
                        int n6;
                        if (n2 == 0) break block5;
                        n4 = Math.abs((n3 << 10) / n2);
                        int n7 = 0;
                        while (n7 <= 90) {
                            if (c[n7] >= n4) {
                                n6 = n7;
                                break block4;
                            }
                            ++n7;
                        }
                        n6 = n4 = 0;
                    }
                    if (n3 >= 0 && n2 < 0) {
                        n4 = 180 - n4;
                    }
                    if (n3 < 0 && n2 < 0) {
                        n4 += 180;
                    }
                    if (n3 >= 0 || n2 < 0) break block6;
                    n5 = 360 - n4;
                    break block7;
                }
                n5 = n3 > 0 ? 90 : 270;
            }
            n4 = n5;
        }
        return n4;
    }

    private static int a(int n2) {
        if (n2 >= 360) {
            n2 -= 360;
        }
        if (n2 < 0) {
            n2 += 360;
        }
        return n2;
    }

    public static int b(int n2, int n3) {
        n2 = l.a(n2);
        return ((n2 = l.a(n2)) >= 0 && n2 < 90 ? b[n2] : (n2 >= 90 && n2 < 180 ? -b[180 - n2] : (n2 >= 180 && n2 < 270 ? -b[n2 - 180] : b[360 - n2]))) * n3 >> 10;
    }

    public static int c(int n2, int n3) {
        n2 = l.a(n2);
        return ((n2 = l.a(n2)) >= 0 && n2 < 90 ? a[n2] : (n2 >= 90 && n2 < 180 ? a[180 - n2] : (n2 >= 180 && n2 < 270 ? -a[n2 - 180] : -a[360 - n2]))) * n3 >> 10;
    }
}


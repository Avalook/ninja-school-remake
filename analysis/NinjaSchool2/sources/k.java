/*
 * Decompiled with CFR 0.152.
 */
public final class k {
    int a;
    int b;
    int c;
    int d = 16;
    int e = 19;
    int f;

    public k(int n2, int n3, int n4) {
        this.a = n2;
        this.b = n3;
        this.c = 0;
        this.f = n4;
        if (this.f == 0) {
            t.o.addElement(new k(this.a, this.b - 1, -1));
            t.o.addElement(new k(this.a, this.b + 1, 1));
        }
    }
}


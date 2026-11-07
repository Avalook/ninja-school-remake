/*
 * Decompiled with CFR 0.152.
 */
public final class r
extends h {
    private int h;
    private int i;

    public r(int n2, int n3, int n4) {
        super(n2, n3, n4);
    }

    public final void a() {
        ++this.f;
        if (this.f >= h.a[this.d].length) {
            this.f = 0;
        }
        if (this.b < q.ay) {
            this.g = true;
        } else if (this.b > q.ay + q.ab) {
            this.g = true;
        } else if (this.c > q.az + q.ac) {
            this.g = true;
        }
        this.b += this.h;
        ++this.i;
        if (this.i > 10 + q.at % 40) {
            this.i = 0;
            ++this.h;
        }
        this.c += 2;
    }
}


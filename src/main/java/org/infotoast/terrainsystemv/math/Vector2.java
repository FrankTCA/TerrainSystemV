package org.infotoast.terrainsystemv.math;

public class Vector2 {
    private int x;
    private int z;

    public Vector2(int x1, int z1) {
        x = x1;
        z = z1;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector2) {
            Vector2 v2 = (Vector2)o;
            return (v2.getX() == x && v2.getZ() == z);
        }
        return false;
    }
}

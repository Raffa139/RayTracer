package de.re.common;

public class Color extends Vec3 {
    public Color(float r, float g, float b) {
        super(r, g, b);
    }

    public float r() {
        return e[0];
    }

    public float g() {
        return e[1];
    }

    public float b() {
        return e[2];
    }
}
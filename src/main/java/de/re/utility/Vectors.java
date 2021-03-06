package de.re.utility;

import de.re.common.Vec3;

import java.io.IOException;
import java.io.Writer;

public final class Vectors {
    private Vectors() {
    }

    // TODO: 05.12.2020 Implementation correct?
    public static void out(Writer out, Vec3 v) throws IOException {
        out.write(v.e[0] + " " + v.e[1] + " " + v.e[2]);
    }

    public static Vec3 add(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] + v.e[0], u.e[1] + v.e[1], u.e[2] + v.e[2]);
    }

    public static Vec3 sub(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] - v.e[0], u.e[1] - v.e[1], u.e[2] - v.e[2]);
    }

    public static Vec3 mul(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] * v.e[0], u.e[1] * v.e[1], u.e[2] * v.e[2]);
    }

    // TODO: 05.12.2020 Implementation correct?
    public static Vec3 mul(Vec3 v, float t) {
        return new Vec3(v).mul(t);
    }

    // TODO: 05.12.2020 Implementation correct?
    public static Vec3 div(Vec3 v, float t) {
        return new Vec3(v).mul(1/t);
    }

    public static float dot(Vec3 u, Vec3 v) {
        return u.e[0] * v.e[0]
             + u.e[1] * v.e[1]
             + u.e[2] * v.e[2];
    }

    public static Vec3 cross(Vec3 u, Vec3 v) {
        return new Vec3(
                u.e[1] * v.e[2] - u.e[2] * v.e[1],
                u.e[2] * v.e[0] - u.e[0] * v.e[2],
                u.e[0] * v.e[1] - u.e[1] * v.e[0]
        );
    }

    // TODO: 05.12.2020 Implementation correct?
    public static Vec3 unitVector(Vec3 v) {
        return new Vec3(v).div(v.length());
    }

    // TODO: 05.12.2020 This class or Vec3 class?
    public static Vec3 negate(Vec3 v) {
        return new Vec3(-v.e[0], -v.e[1], -v.e[2]);
    }

    public static Vec3 random() {
        return new Vec3(Maths.randomFloat(), Maths.randomFloat(), Maths.randomFloat());
    }

    public static Vec3 random(float min, float max) {
        return new Vec3(Maths.randomFloat(min, max), Maths.randomFloat(min, max), Maths.randomFloat(min, max));
    }

    public static Vec3 randomInUnitSphere() {
        while (true) {
            Vec3 p = random(-1.0f, 1.0f);
            if (p.lengthSquared() >= 1) {
                continue;
            }
            return p;
        }
    }

    public static Vec3 randomUnitVector() {
        return unitVector(randomInUnitSphere());
    }

    public static Vec3 randomInHemisphere(Vec3 normal) {
        Vec3 inUnitSphere = randomInUnitSphere();
        if (dot(inUnitSphere, normal) > 0.0f) { // In the same hemisphere as the normal
            return inUnitSphere;
        } else {
            return negate(inUnitSphere);
        }
    }

    public static Vec3 randomInUnitDisk() {
        while (true) {
            Vec3 p = new Vec3(Maths.randomFloat(-1.0f, 1.0f), Maths.randomFloat(-1.0f, 1.0f), 0.0f);
            if (p.lengthSquared() >= 1.0f) {
                continue;
            }
            return p;
        }
    }

    public static Vec3 reflect(Vec3 v, Vec3 n) {
        return new Vec3(sub(v, mul(n, 2.0f * dot(v, n))));
    }

    public static Vec3 refract(Vec3 uv, Vec3 n, float refRatio) {
        float cosTheta = Math.min(dot(negate(uv), n), 1.0f);
        Vec3 rOutPerp = add(uv, mul(n, cosTheta)).mul(refRatio); // Perpendicular ray
        Vec3 rOutParallel = n.mul((float) -Math.sqrt(Math.abs(1.0f - rOutPerp.lengthSquared()))); // Parallel ray
        return add(rOutPerp, rOutParallel);
    }
}

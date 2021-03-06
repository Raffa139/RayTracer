package de.re.models;

import de.re.common.Point3;
import de.re.common.Vec3;

public class Ray {
    public Point3 origin;

    public Vec3 direction;

    public Ray() {
    }

    public Ray(Point3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    // TODO: 05.12.2020 Maybe do not mutate direction?
    public Point3 at(float t) {
        Point3 orig = new Point3(origin);
        return (Point3) orig.add(direction.mul(t));
    }

    public void overwrite(Ray r) {
        this.origin = r.origin;
        this.direction = r.direction;
    }
}

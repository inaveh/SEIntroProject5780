package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Sphere extends RadialGeometry {
    /**
     * The center of the sphere
     */
    private final Point3D _center;

    /**
     * constructor for a new sphere object.
     *
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     *
     * @throws Exception in case of negative or zero radius from RadialGeometry constructor
     */
    public Sphere(double radius, Point3D center) {
        super(radius);
        _center = new Point3D(center);
    }

    @Override
    public String toString() {
        return String.format
                ("point: " + _center + ", radius: " + _radius);
    }

    /**
     * getter for the center property
     *
     * @return the center of the sphere
     */
    public Point3D getCenter() {
        return new Point3D(_center);
    }


    /**
     * get the normal to this sphere in a given point
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector normal = point.subtract(_center);
        return normal.normalize();
    }

    public Point3D get_center() {
        return _center;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(ray.getTargetPoint(_radius));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getTargetPoint(t1));
        else
            return List.of(ray.getTargetPoint(t2));
    }
}

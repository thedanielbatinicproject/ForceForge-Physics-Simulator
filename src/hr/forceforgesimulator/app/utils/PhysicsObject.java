package hr.forceforgesimulator.app.utils;

public class PhysicsObject {
    public double x, y;       // Position
    public double vx, vy;     // Velocity
    public double ax, ay;     // Acceleration
    public double mass;       // Mass of the object

    public PhysicsObject(double x, double y, double mass) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.ax = 0;
        this.ay = 0;
        this.mass = mass;
    }

    public void applyForce(double fx, double fy) {
        this.ax += fx / mass;
        this.ay += fy / mass;
    }

    public void update(double dt) {
        vx += ax * dt; // Up
    }
}
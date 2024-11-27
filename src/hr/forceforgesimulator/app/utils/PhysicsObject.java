package hr.forceforgesimulator.app.utils;

public class PhysicsObject {
    public double x, y, z;    // Position
    public double vx, vy, vz; // Velocity
    public double ax, ay, az; // Acceleration
    public double mass;       // Mass of the object

    public PhysicsObject(double x, double y, double z, double mass) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = 0;
        this.vy = 0;
        this.vz = 0;
        this.ax = 0;
        this.ay = 0;
        this.az = 0;
        this.mass = mass;
    }

    public void applyForce(double fx, double fy, double fz) {
        this.ax += fx / mass;
        this.ay += fy / mass;
        this.az += fz / mass;
    }

    public void update(double dt) {
        // Update velocity
        vx += ax * dt;
        vy += ay * dt;
        vz += az * dt;

        // Update position
        x += vx * dt;
        y += vy * dt;
        z += vz * dt;

        // Reset acceleration for the next frame
        ax = 0;
        ay = 0;
        az = 0;
    }
}


package hr.forceforgesimulator.app.utils;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL11.*;

public class PhysicsObject {
    public double x, y, z; // Position
    private double vx, vy, vz; // Velocity
    private double ax, ay, az; // Acceleration
    private double mass; // Mass of the object
    private double radius; // Radius for rendering

    public PhysicsObject(double x, double y, double z, double radius) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.mass = 1.0; // Default mass
        this.vx = this.vy = this.vz = 0.0;
        this.ax = this.ay = this.az = 0.0;
    }

    /**
     * Apply a force to the object.
     * @param fx Force in the X direction.
     * @param fy Force in the Y direction.
     * @param fz Force in the Z direction.
     */
    public void applyForce(double fx, double fy, double fz) {
        // Acceleration = Force / Mass
        this.ax += fx / mass;
        this.ay += fy / mass;
        this.az += fz / mass;
    }

    /**
     * Update the position and velocity of the object based on forces and delta time.
     * @param dt Time step (in seconds).
     */
    public void update(double dt) {
        // Update velocity using acceleration
        vx += ax * dt;
        vy += ay * dt;
        vz += az * dt;

        // Update position using velocity
        x += vx * dt;
        y += vy * dt;
        z += vz * dt;

        // Reset acceleration after each update (forces are applied per frame)
        ax = ay = az = 0.0;
    }

    /**
     * Render the object in 3D space.
     */
    public void render() {
        glPushMatrix();
        glTranslated(x, y, z); // Use glTranslated for double values
        drawSphere(radius, 16, 16);
        glPopMatrix();
    }

    /**
     * Draws a sphere using OpenGL primitives.
     * @param radius Radius of the sphere.
     * @param slices Number of horizontal slices.
     * @param stacks Number of vertical stacks.
     */
    private void drawSphere(double radius, int slices, int stacks) {
        for (int i = 0; i < stacks; i++) {
            double theta1 = i * Math.PI / stacks;
            double theta2 = (i + 1) * Math.PI / stacks;

            glBegin(GL_TRIANGLE_STRIP);
            for (int j = 0; j <= slices; j++) {
                double phi = j * 2 * Math.PI / slices;

                double x1 = Math.sin(theta1) * Math.cos(phi);
                double y1 = Math.cos(theta1);
                double z1 = Math.sin(theta1) * Math.sin(phi);

                double x2 = Math.sin(theta2) * Math.cos(phi);
                double y2 = Math.cos(theta2);
                double z2 = Math.sin(theta2) * Math.sin(phi);

                glVertex3d(x1 * radius, y1 * radius, z1 * radius);
                glVertex3d(x2 * radius, y2 * radius, z2 * radius);
            }
            glEnd();
        }
    }

    // Optional: Get the mass of the object
    public double getMass() {
        return mass;
    }

    // Optional: Set the mass of the object
    public void setMass(double mass) {
        this.mass = mass;
    }
}

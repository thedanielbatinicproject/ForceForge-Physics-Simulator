package hr.forceforgesimulator.app.utils;

import java.util.ArrayList;

public class PhysicsEngine {
    private ArrayList<PhysicsObject> objects;

    public PhysicsEngine() {
        objects = new ArrayList<>();
    }

    public void addObject(PhysicsObject obj) {
        objects.add(obj);
    }

    public void applyGlobalForce(double fx, double fy, double fz) {
        for (PhysicsObject obj : objects) {
            obj.applyForce(fx, fy, fz);
        }
    }

    public void update(double dt) {
        for (PhysicsObject obj : objects) {
            obj.update(dt);
        }
    }

    public ArrayList<PhysicsObject> getObjects() {
        return objects;
    }
}

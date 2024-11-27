package hr.forceforgesimulator.app;

import hr.forceforgesimulator.app.utils.PhysicsEngine;
import hr.forceforgesimulator.app.utils.PhysicsObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderer3D extends JPanel {
    private PhysicsEngine engine;
    private final double scale = 500; // Scale for perspective projection
    private final double cameraDistance = 500; // Camera distance along Z-axis

    public Renderer3D(PhysicsEngine engine) {
        this.engine = engine;
        setBackground(Color.WHITE); // Set the background to gray
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<PhysicsObject> objects = engine.getObjects();

        // Sort objects by Z to simulate depth (optional for occlusion effects)
        objects.sort((a, b) -> Double.compare(b.z, a.z));

        for (PhysicsObject obj : objects) {
            // Perspective projection: map 3D to 2D
            double screenX = (obj.x / (obj.z + cameraDistance)) * scale + getWidth() / 2;
            double screenY = (obj.y / (obj.z + cameraDistance)) * scale + getHeight() / 2;

            // Scale circle radius based on Z-coordinate
            double depthScaling = Math.max(0.1, cameraDistance / (obj.z + cameraDistance));
            int radius = (int) (50 * depthScaling); // Base radius scaled by depth

            // Draw the object
            g.setColor(Color.BLACK);
            g.fillOval((int) screenX - radius / 2, (int) screenY - radius / 2, radius, radius);
        }
    }
}



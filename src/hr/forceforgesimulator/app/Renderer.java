package hr.forceforgesimulator.app;

import hr.forceforgesimulator.app.utils.PhysicsEngine;
import hr.forceforgesimulator.app.utils.PhysicsObject;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class Renderer extends JPanel {
    private PhysicsEngine engine;

    public Renderer(PhysicsEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        ArrayList<PhysicsObject> objects = engine.getObjects();
        for (PhysicsObject obj : objects) {
            g.fillOval((int) obj.x, (int) obj.y, 10, 10); // Draw object as a circle
        }
    }
}

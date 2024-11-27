package hr.forceforgesimulator.app;


import javax.swing.Timer;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import hr.forceforgesimulator.app.utils.PhysicsEngine;
import hr.forceforgesimulator.app.utils.PhysicsObject;


public class ForceForgeAppMain {
	public static void main(String[] args) {
        JFrame frame = new JFrame("3D Physics Simulator");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the PhysicsEngine
        PhysicsEngine engine = new PhysicsEngine();

        // Add objects in 3D space
        engine.addObject(new PhysicsObject(100, 100, 200, 1));
        engine.addObject(new PhysicsObject(-100, -100, 300, 2));

        // Create the Renderer3D
        Renderer3D renderer = new Renderer3D(engine);
        frame.add(renderer);

        frame.setVisible(true);

        // Timer for simulation loop (60 FPS, 16ms per frame)
        Timer timer = new Timer(16, (ActionEvent e)  -> {
            engine.applyGlobalForce(0, 122, 1330); // Apply force in the -Z direction
            engine.update(0.016);              // Update physics (dt = 16 ms)
            renderer.repaint();                // Redraw the frame
        });

        // Start the timer
        timer.start();
    }
}

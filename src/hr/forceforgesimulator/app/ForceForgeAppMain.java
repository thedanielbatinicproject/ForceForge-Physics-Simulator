package hr.forceforgesimulator.app;
import javax.swing.*;
import hr.forceforgesimulator.app.utils.PhysicsEngine;
import hr.forceforgesimulator.app.utils.PhysicsObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForceForgeAppMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Physics Simulator");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PhysicsEngine engine = new PhysicsEngine();

        // Add two PhysicsObjects with different masses
        engine.addObject(new PhysicsObject(100, 100, 1)); 
        engine.addObject(new PhysicsObject(200, 200, 5)); 

        Renderer renderer = new Renderer(engine);
        frame.add(renderer);

        frame.setVisible(true);

        // Timer to update the simulation and redraw (60 FPS)
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.applyGlobalForce(120, 1000); // Apply constant downward gravity
                engine.update(0.016);          // Update physics (dt = 16 ms)
                renderer.repaint();            // Redraw the frame
            }
        });

        timer.start();
    }
}

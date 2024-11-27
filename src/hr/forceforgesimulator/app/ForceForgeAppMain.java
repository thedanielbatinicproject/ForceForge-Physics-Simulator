package hr.forceforgesimulator.app;




import java.util.ArrayList;

import hr.forceforgesimulator.app.utils.PhysicsEngine;
import hr.forceforgesimulator.app.utils.PhysicsObject;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class ForceForgeAppMain {
	
	private long window;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private ArrayList<PhysicsObject> objects = new ArrayList<>();
	
	public static void main(String[] args) {
		new ForceForgeAppMain().run();
	}
	
	
	public void run() {
        init();
        loop();
        glfwTerminate();
    }

    private void init() {
        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "3D Physics Simulator", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Center the window
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - WIDTH) / 2, (vidmode.height() - HEIGHT) / 2);

        // Make OpenGL context current
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // Enable v-sync

        // Show the window
        glfwShowWindow(window);

        // Initialize OpenGL
        GL.createCapabilities();

        // Set up OpenGL settings
        glEnable(GL_DEPTH_TEST);

        // Add physics objects
        objects.add(new PhysicsObject(0, 0, -5, 1));
        objects.add(new PhysicsObject(1, 1, -10, 0.5f));
    }
    
    private void loop() {
        setupProjection(); // Set up the perspective projection
        double lastTime = glfwGetTime();

        while (!glfwWindowShouldClose(window)) {
            double currentTime = glfwGetTime();
            double dt = currentTime - lastTime;
            lastTime = currentTime;

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            setupCamera(); // Position the camera

            // Apply forces and update physics
            for (PhysicsObject obj : objects) {
                obj.applyForce(124, -9.8 * obj.getMass(), 0); // Apply gravity
                obj.update(dt); // Update physics with delta time
                obj.render(); // Render the object
            }

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    
    
    
    
    //Aditional methods
    private void setupProjection() {
        double fov = 70.0; // Field of View in degrees
        double aspectRatio = (double) WIDTH / HEIGHT;
        double near = 0.1; // Near clipping plane
        double far = 100.0; // Far clipping plane

        double top = Math.tan(Math.toRadians(fov / 2)) * near;
        double bottom = -top;
        double right = top * aspectRatio;
        double left = -right;

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glFrustum(left, right, bottom, top, near, far);
        glMatrixMode(GL_MODELVIEW);
    }
    private void setupCamera() {
        glLoadIdentity();
        glTranslated(0.0, 0.0, -10.0); // Move the camera back along the Z-axis
    }


}

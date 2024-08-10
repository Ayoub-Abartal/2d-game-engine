package fame;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;


import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private int width, height;
    private String title;

    private static Window window;
    private long glfwWindow;
    private Window(){
        this.width = 1920;
        this.height = 1080;
        this.title = "Game Engine";
    }

    public static Window get(){
        if(Window.window == null){
            return new Window();
        }else
            return Window.window;
    }

    public void run(){
        System.out.println("Welcome to LWJGL "+ Version.getVersion()+"!");

        init();
        loop();

        // Free memory after executing
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Free the Callback Error
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    public void init(){
        // Setup err callback
        GLFWErrorCallback.createPrint(System.err).set();

        // initialize GLFW
        if(!glfwInit()){
            throw new IllegalStateException("Unable to intialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create the window
        glfwWindow = glfwCreateWindow(this.width,this.height,this.title, NULL,NULL);

        //
        if(glfwWindow == NULL){
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        // make the opengl context
        glfwMakeContextCurrent(glfwWindow);
        // Enable v-sync
        glfwSwapInterval(1);

        // make the window visible
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

    }

    public void loop(){

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.

        while(!glfwWindowShouldClose(glfwWindow)) {

            // Poll events
            glfwPollEvents();

            glClearColor(1.0f,0.0f,0.0f,1.0f); 		// Set the clear color
            glClear(GL_COLOR_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(glfwWindow); // swap the color buffers


            // Poll for window events. The key callback above will only be
            // invoked during this call.
        }
    }
}

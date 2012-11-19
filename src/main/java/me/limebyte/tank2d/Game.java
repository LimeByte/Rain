package me.limebyte.tank2d;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import me.limebyte.tank2d.graphics.Screen;
import me.limebyte.tank2d.input.KeyboardListener;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static int width = 290;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    public static final String TITLE = "Tank 2D";
    private static final int FPS = 60;

    private Thread thread;
    protected JFrame frame;
    private KeyboardListener keyListener;
    private boolean running = false;

    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private int currentFPS = FPS;
    private int currentTPS = FPS;

    private int x = 0, y = 0;
    private int moveSpeed = 20;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        keyListener = new KeyboardListener();

        addKeyListener(keyListener);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long time = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;
        final double ns = 1000000000 / FPS;
        int frames = 0;
        int updates = 0;

        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - time) / ns;
            time = currentTime;

            while (delta > 0) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                currentTPS = updates;
                currentFPS = frames;
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        screen.render(x, y);
        System.arraycopy(screen.pixels, 0, pixels, 0, screen.pixels.length);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.setColor(new Color(0f, 0f, 0f, 0.7f));
        g.fillRect(5, 5, 150, 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Tank 2D - Prototype", 10, 20);
        g.drawString(currentFPS + " fps, " + currentTPS + " ticks", 10, 40);

        g.dispose();
        bs.show();
    }

    private void update() {
        keyListener.update();

        if (keyListener.faster) {
            moveSpeed++;
        }

        if (keyListener.slower) {
            moveSpeed--;
        }

        if (keyListener.left) {
            x += moveSpeed / 10;
        }

        if (keyListener.right) {
            x -= moveSpeed / 10;
        }

        if (keyListener.up) {
            y += moveSpeed / 10;
        }

        if (keyListener.down) {
            y -= moveSpeed / 10;
        }
    }

}

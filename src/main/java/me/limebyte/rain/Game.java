package me.limebyte.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import me.limebyte.rain.entity.mob.Player;
import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.input.KeyboardListener;
import me.limebyte.rain.level.Level;
import me.limebyte.rain.level.LoadedLevel;
import me.limebyte.rain.sound.Song;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static int width = 290;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    public static final String NAME = "Rain";
    public static final int TPS = 60;

    private Thread thread;
    protected JFrame frame;
    private KeyboardListener keyListener;
    private boolean running = false;

    private Screen screen;
    private Level level;
    private Player player;
    public static final Song song = new Song("/music/oots.wav", true);

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private int currentFPS = 0;
    private int currentTPS = TPS;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        keyListener = new KeyboardListener();
        level = new LoadedLevel("/levels/test.png");
        player = new Player("Foster", keyListener);

        addKeyListener(keyListener);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
        song.play();
    }

    public synchronized void stop() {
        running = false;
        song.stop();
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
        final double ns = 1000000000 / TPS;
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
        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);
        System.arraycopy(screen.pixels, 0, pixels, 0, screen.pixels.length);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.setColor(new Color(0f, 0f, 0f, 0.7f));
        g.fillRect(5, 5, 150, 54);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fMetrics = g.getFontMetrics();
        g.drawString(NAME + " - Prototype", 10, 20);
        g.drawString(currentFPS + " fps, " + currentTPS + " ticks", 10, 36);

        // Nametag
        int tagWidth = fMetrics.stringWidth(player.getName()) + 12;
        int tagHeight = fMetrics.getHeight() + 12;
        int tagX = screen.width / 2 * scale + 6;
        int tagY = screen.height / 2 * scale - 80;

        g.setColor(new Color(0f, 0f, 0f, 0.3f));
        if (tagWidth < 100) {
            g.fillRect(tagX - 50, tagY, 96, tagHeight);
        } else {
            g.fillRect(tagX - tagWidth / 2, tagY, tagWidth, tagHeight);
        }

        g.setColor(Color.WHITE);
        g.drawString(player.getName(), tagX - tagWidth / 2 + 6, tagY + tagHeight / 2 + 4);

        g.dispose();
        bs.show();
    }

    private void update() {
        keyListener.update();
        player.update();
    }

}

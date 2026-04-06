

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Sala2 extends JPanel implements ActionListener, KeyListener {

    static final int ANCHO = 1200;
    static final int ALTO  = 600;

    static final Color ROJO_PARED  = new Color(160, 20, 20);
    static final Color PISO        = new Color(180, 140, 110);
    static final Color BLANCO      = new Color(240, 235, 225);

    int munX = 400;
    int munY = 450;
    final int VEL = 3;
    boolean izq, der, arr, abj;

    BufferedImage imgCristo2, imgPez, imgCalcifer, imgVirgen, imgMarco;

    public Sala2() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setFocusable(true);
        addKeyListener(this);

        imgCristo2  = cargarImagen("Sala2/cristo2.jpg");
        imgPez      = cargarImagen("Sala2/pez.jpg");
        imgCalcifer = cargarImagen("Sala2/calcifer.jpg");
        imgVirgen   = cargarImagen("Sala2/virgen.jpg");
        imgMarco    = cargarImagen("marco_transparente.png");

        Timer timer = new Timer(16, this);
        timer.start();
    }

    BufferedImage cargarImagen(String ruta) {
        try {
            return ImageIO.read(new File(ruta));
        } catch (Exception e) {
            System.out.println("No se encontro: " + ruta);
            return null;
        }
    }

    void dibujarCuadro(Graphics2D g, BufferedImage img, BufferedImage marco, int x, int y, int w, int h) {
        if (marco != null) {
            g.drawImage(marco, x - 25, y - 25, w + 50, h + 50, null);
        }
        if (img != null) {
            g.drawImage(img, x, y, w, h, null);
        } else {
            g.setColor(new Color(120, 15, 15));
            g.fillRect(x, y, w, h);
        }
    }

    void dibujarMunequito(Graphics2D g, int x, int y) {
        g.setColor(new Color(232, 224, 213));
        g.fillOval(x - 12, y - 52, 24, 24);
        g.fillRect(x - 8, y - 28, 16, 30);
        g.fillRect(x - 8, y + 2, 6, 20);
        g.fillRect(x + 2, y + 2, 6, 20);
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Pared roja
        g.setColor(ROJO_PARED);
        g.fillRect(0, 0, ANCHO, 400);

        // Piso
        g.setColor(PISO);
        g.fillRect(0, 400, ANCHO, 200);

        // Techo blanco
        g.setColor(BLANCO);
        g.fillRect(0, 0, ANCHO, 60);

        // Riel de spots
        g.setColor(new Color(180, 180, 180));
        g.fillRect(0, 55, ANCHO, 5);

        // Spots individuales
        int[] spotsX = new int[]{150, 350, 550, 750, 950, 1100};
        for (int sx : spotsX) {
            g.setColor(new Color(150, 150, 150));
            g.fillRect(sx, 50, 20, 15);
            g.setColor(new Color(255, 240, 200, 40));
            int[] lx = new int[]{sx, sx + 20, sx + 60, sx - 40};
            int[] ly = new int[]{60, 60, 300, 300};
            g.fillPolygon(lx, ly, 4);
        }

        // Cuadros
        dibujarCuadro(g, imgCristo2,  imgMarco, 80,  120, 130, 170);
        dibujarCuadro(g, imgPez,      imgMarco, 310, 120, 130, 170);
        dibujarCuadro(g, imgCalcifer, imgMarco, 700, 120, 130, 170);
        dibujarCuadro(g, imgVirgen,   imgMarco, 980, 120, 130, 170);

        // Muñequito
        dibujarMunequito(g, munX, munY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (izq) munX -= VEL;
        if (der) munX += VEL;
        if (arr) munY -= VEL;
        if (abj) munY += VEL;
        munX = Math.max(12, Math.min(ANCHO - 12, munX));
        munY = Math.max(350, Math.min(ALTO - 20, munY));
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT  -> izq = true;
            case KeyEvent.VK_RIGHT -> der = true;
            case KeyEvent.VK_UP    -> arr = true;
            case KeyEvent.VK_DOWN  -> abj = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT  -> izq = false;
            case KeyEvent.VK_RIGHT -> der = false;
            case KeyEvent.VK_UP    -> arr = false;
            case KeyEvent.VK_DOWN  -> abj = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame v = new JFrame("Sala 2 - Paintbrush Blue");
            v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            v.add(new Sala2());
            v.pack();
            v.setLocationRelativeTo(null);
            v.setVisible(true);
        });
    }
}
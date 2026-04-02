
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Galeria extends JPanel implements ActionListener, KeyListener {

    // Tamaño ventana
    static final int ANCHO = 1200;
    static final int ALTO = 600;

    // Variables del muñequito
    int munX = 400;
    int munY = 350;
    final int VEL = 3;

    // Rectángulos de los cuadros
    Rectangle rCristo = new Rectangle(150, 100, 120, 150);
    Rectangle rTotoro = new Rectangle(540, 100, 120, 150);
    Rectangle rVango = new Rectangle(930, 100, 120, 150);

    // Colores (igual que en Python)
    static final Color NEGRO = new Color(10, 10, 10);
    static final Color PARED = new Color(26, 26, 26);
    static final Color PISO = new Color(120, 82, 50); // café duela madera
    static final Color AZUL_MAYA = new Color(26, 107, 138);
    static final Color DORADO = new Color(201, 169, 110);
    static final Color BLANCO_CALIDO = new Color(232, 224, 213);

    // Teclas presionadas
    boolean izq, der, arr, abj;

    // Imágenes de los cuadros
    BufferedImage imgCristo, imgTotoro, imgVango;

    public Galeria() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(NEGRO);
        setFocusable(true);
        addKeyListener(this);

        // Cargar imágenes (si no existe el archivo, se dibuja un placeholder)
        imgCristo = cargarImagen("cristo.jpeg");
        imgTotoro = cargarImagen("totoro.jpeg");
        imgVango = cargarImagen("vango.jpeg");

        // Timer a 60 FPS (~16ms por frame)
        Timer timer = new Timer(16, this);
        timer.start();
    }

    // Carga imagen o devuelve null si no existe
    BufferedImage cargarImagen(String ruta) {
        try {
            return ImageIO.read(new File(ruta));
        } catch (Exception e) {
            System.out.println("No se encontró: " + ruta + " — se usará placeholder");
            return null;
        }

    }

    // Dibuja un cuadro con su imagen (o placeholder) y marco dorado
    void dibujarCuadro(Graphics2D g, BufferedImage img, int x, int y, int w, int h, boolean vendido) {
        if (img != null) {
            g.drawImage(img, x, y, w, h, null);
        } else {
            // Placeholder gris con texto
            g.setColor(new Color(60, 60, 60));
            g.fillRect(x, y, w, h);
            g.setColor(BLANCO_CALIDO);
            g.setFont(new Font("Arial", Font.PLAIN, 11));
            g.drawString("sin imagen", x + 18, y + h / 2);
        }

        // Marco dorado
        g.setColor(DORADO);
        g.setStroke(new BasicStroke(3));
        g.drawRect(x, y, w, h);

        // Punto rojo de vendido (como el Totoro 🔴)
        if (vendido) {
            g.setColor(new Color(200, 30, 30));
            g.fillOval(x + w - 14, y - 8, 14, 14);
        }
    }

    // Dibuja el muñequito (mismo que en Python)
    void dibujarMunequito(Graphics2D g, int x, int y) {
        g.setColor(BLANCO_CALIDO);
        // Cabeza
        g.fillOval(x - 12, y - 52, 24, 24);
        // Cuerpo
        g.fillRect(x - 8, y - 28, 16, 30);
        // Pierna izquierda
        g.fillRect(x - 8, y + 2, 6, 20);
        // Pierna derecha
        g.fillRect(x + 2, y + 2, 6, 20);
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo negro
        g.setColor(NEGRO);
        g.fillRect(0, 0, ANCHO, ALTO);

        // Pared
        g.setColor(PARED);
        g.fillRect(0, 0, ANCHO, 400);

        // Piso
        g.setColor(PISO);
        g.fillRect(0, 400, ANCHO, 200);

        // arco superior
        // Marco de la ventana
        g.fillRect(50, 250, 55, 80); // más pequeña
        g.fillArc(50, 210, 55, 80, 0, 180); // arco más pequeño
        g.drawRect(50, 250, 55, 80);
        g.drawArc(50, 210, 55, 80, 0, 180);

        // Primero el relleno azul
        g.setColor(new Color(135, 185, 210));
        g.fillRect(50, 250, 55, 80);
        g.fillArc(50, 210, 55, 80, 0, 180);

        // Luego el marco café encima
        g.setColor(new Color(60, 40, 25));
        g.drawRect(50, 250, 55, 80);
        g.drawArc(50, 210, 55, 80, 0, 180);
        g.drawLine(77, 210, 77, 330);
        g.drawLine(50, 280, 105, 280);

        // Cuadros: Cristo | Totoro (vendido 🔴) | Van Gogh

        dibujarCuadro(g, imgCristo, 150, 100, 120, 150, false);
        dibujarCuadro(g, imgTotoro, 540, 100, 120, 150, true);
        dibujarCuadro(g, imgVango, 930, 100, 120, 150, false);

        // Muñequito
        dibujarMunequito(g, munX, munY);

        // En paintComponent (dibujo)
        dibujarCuadro(g, imgCristo, rCristo.x, rCristo.y, rCristo.width, rCristo.height, false);
        dibujarCuadro(g, imgTotoro, rTotoro.x, rTotoro.y, rTotoro.width, rTotoro.height, true);
        dibujarCuadro(g, imgVango, rVango.x, rVango.y, rVango.width, rVango.height, false);

        // Vigas del techo
        g.setColor(new Color(45, 30, 20)); // café oscuro
        g.fillRect(0, 0, ANCHO, 20); // viga superior
        g.fillRect(150, 0, 30, 80); // viga 1
        g.fillRect(350, 0, 30, 80); // viga 2
        g.fillRect(550, 0, 30, 80); // viga 3
        g.fillRect(750, 0, 30, 80); // viga 4
        g.fillRect(950, 0, 30, 80); // viga 5

        // Textos fuera de la condición de colisión
        g.setColor(BLANCO_CALIDO);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Cristo", 145, 70);
        g.drawString("Totoro", 385, 70);
        g.drawString("Van Gogh", 625, 70);

        dibujarMunequito(g, munX, munY);

    }

    @Override

    public void actionPerformed(ActionEvent e) {
        // Mover muñequito según teclas

        /*
         * Se calcula en actionPerformed() usando las coordenadas de los rectángulos,
         * independientemente de cómo y cuándo se dibujen.
         */

        int nuevoX = munX;
        int nuevoY = munY;

        if (izq)
            nuevoX -= VEL;
        if (der)
            nuevoX += VEL;
        if (arr)
            nuevoY -= VEL;
        if (abj)
            nuevoY += VEL;

        Rectangle muneco = new Rectangle(nuevoX - 8, nuevoY - 28, 16, 50);

        if (!muneco.intersects(rCristo) &&
                !muneco.intersects(rTotoro) &&

                !muneco.intersects(rVango)) {
            munX = nuevoX;
            munY = nuevoY;
        }

        // Límites de pantalla
        munX = Math.max(12, Math.min(ANCHO - 12, munX));
        munY = Math.max(350, Math.min(ALTO - 20, munY));

        repaint();

    }

    // KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> izq = true;
            case KeyEvent.VK_RIGHT -> der = true;
            case KeyEvent.VK_UP -> arr = true;
            case KeyEvent.VK_DOWN -> abj = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> izq = false;
            case KeyEvent.VK_RIGHT -> der = false;
            case KeyEvent.VK_UP -> arr = false;
            case KeyEvent.VK_DOWN -> abj = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Galería de Arte - Paintbrush Blue");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.add(new Galeria());
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
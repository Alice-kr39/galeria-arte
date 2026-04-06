/ Puerta derecha
        g.setColor(new Color(40, 25, 15)); // café oscuro
        g.fillRect(1100, 250, 70, 150); // puerta

        // Marco de la puerta
        g.setColor(new Color(90, 60, 30)); // café más claro
        g.setStroke(new BasicStroke(4));
        g.drawRect(1100, 250, 70, 150);

        // Arco superior
        g.fillArc(1100, 210, 70, 80, 0, 180);
        g.setColor(new Color(90, 60, 30));
        g.drawArc(1100, 210, 70, 80, 0, 180);

        // Pomo
        g.setColor(DORADO);
        g.fillOval(1108, 320, 10, 10);

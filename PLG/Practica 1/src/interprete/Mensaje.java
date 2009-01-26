package interprete;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Mensaje extends Frame implements ActionListener {

    private static final long serialVersionUID=3918001L;

    /**
     *  Constructor que crea e inicializa la ventana
     */
    public Mensaje () {
        initializeDisplay("");
    }

    /**
     *  Constructor que crea e inicializa la ventana, y le pone titulo.
     */
    public Mensaje (String titulo) {
        /* the alternative constructor has a title */
        initializeDisplay(titulo);
    }

    private Vector listOfNames = new Vector();
    private Button okButton;
    private Label outDisplay;
    private Watcher okWatcher = new Watcher();

    private void initializeDisplay (String titulo) {
        setTitle(titulo);
        setLayout(new BorderLayout());

        Panel p = new Panel(new BorderLayout());
        okButton = new Button("OK");
        okButton.addActionListener(this);
        okButton.setEnabled(true);
        p.add("Center",okButton);
        add("South",p);
        addWindowListener (new WindowAdapter () {
                public void windowClosing(WindowEvent e) {
                    System.exit (0);
                }
            });
        setVisible(false);
    }

    /**
     *  No usar directamente esta operacion interna de la clase
     */
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == okButton) {
            okWatcher.ready();
        }
    }

    /**
     *  Muestra el mensaje y espera a que se pulse el boton OK
     */
    public void escribe (String s) {
        outDisplay = new Label(s,Label.CENTER);
        add("Center",outDisplay);
        this.pack();
        this.setSize(this.getWidth()+40,100);
        setVisible(true);
        okWatcher.watch();
        setVisible(false);
    }

    class Watcher {

        private boolean ok;

        Watcher () {
            ok = false;
        }

        synchronized void watch () {
            while (!ok) {
                try {wait(500); }
                catch(InterruptedException e) {}
            }
            ok = false;
        }

        synchronized void ready () {
            ok = true;
            notify();
        }
    }

}

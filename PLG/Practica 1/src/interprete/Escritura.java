package interprete;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Escritura extends Frame implements ActionListener {

    private static final long serialVersionUID=3918001L;

    private String title;

    /**
       Constructor que crea el objeto con su titulo
    */
    public Escritura (String t) {
        title = t;
        initializeDisplay();
    }

    private class Data {
        TextField field;
        String value;
    }

    private Hashtable<String,Data> table = new Hashtable<String,Data>(10);
    private int xwidth, yheight;
    private Button okButton, closeButton;
    private TextArea outDisplay;
    private Panel inDisplay;
    private ScrollPane inPane, outPane;
    private Watcher okWatcher = new Watcher();

    private void initializeDisplay () {
        xwidth = 500;
        yheight = 400;
        setSize(xwidth,yheight);
        setTitle(title);
        setLayout(new BorderLayout());

        Panel p = new Panel (new FlowLayout(FlowLayout.CENTER,15,0));
        inPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
        inPane.setSize(xwidth - 60, yheight - 60);
        inDisplay = new Panel(new GridLayout (0,2,10,10));
        inPane.add(inDisplay);
        p.add(inPane);
        add(p,"Center");


        p = new Panel(new BorderLayout());
        okButton = new Button("OK");
        okButton.addActionListener(this);
        okButton.setEnabled(false);
        p.add("Center",okButton);
        closeButton = new Button("Close");
        closeButton.addActionListener(this);
        closeButton.setEnabled(true);
        p.add("East",closeButton);
        add("South",p);
        addWindowListener (new WindowAdapter () {
                public void windowClosing(WindowEvent e) {
                    System.exit (0);
                }
            });
        //setVisible(true);
    }

    /**
     *  No usar directamente esta operacion interna de la clase
     */
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == okButton) {
            okWatcher.ready();
        } else
        if (e.getSource() == closeButton) {
            System.exit(0);
        }
    }

    private void insertValue(Data d, String s, TextField t) {
        Panel p;
        p = new Panel(new FlowLayout(FlowLayout.RIGHT));
        p.add(new Label(s));
        inDisplay.add(p);
        t.addActionListener(this);
        t.setEditable(false);
        p = new Panel(new FlowLayout(FlowLayout.LEFT));
        p.add(t);
        inDisplay.add(p);
        d.field = t;
    }

    /**
     * Crea una caja con la etiqueta y valor indicados
     */
    public void insertaValor (String s, int n) {
        Data d = new Data();
        TextField t = new TextField(10);
        insertValue(d, s, t);
        d.value = Integer.toString(n);
        t.setText(d.value);
        table.put(s, d);
    }

    /**
     * Crea una caja con la etiqueta y valor indicados
     */
    public void insertaValor (String s, double n) {
        Data d = new Data();
        TextField t = new TextField(10);
        insertValue(d, s, t);
        d.value = Double.toString(n);
        t.setText(d.value);
        table.put(s, d);
    }

    /**
     *  Crea una caja con la etiqueta y valor indicados
     */
    public void insertaValor (String s, String n) {
        Data d = new Data();
        TextField t = new TextField(n.length()+2);
        insertValue(d, s, t);
        d.value = n;
        t.setText(d.value);
        table.put(s, d);
    }

    /**
     *  Espera a que se pulse el boton OK y cierra la ventana
     */
    public void espera () {
        okButton.setEnabled(true);
        this.pack();
        this.setVisible(true);
        okWatcher.watch();
        this.setVisible(false);
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

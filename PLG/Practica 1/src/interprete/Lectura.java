package interprete;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.Hashtable;

public class Lectura extends Frame implements ActionListener {

        private static final long serialVersionUID=3918001L;

        private String title;

        /**
         * Crea una ventana con su titulo
         */
        public Lectura (String t) {
            title = t;
            initializeDisplay();
        }

        private class Data {
            TextField field;
            String value;
        }

        private Hashtable<String,Data> table = new Hashtable<String,Data> (10);
        private int xwidth, yheight;
        private Button okButton, closeButton;
        private TextArea outDisplay;
        private Panel inDisplay;
        private ScrollPane inPane, outPane;
        private Watcher okWatcher = new Watcher();

        private void initializeDisplay () {
            xwidth = 520;
            yheight = 420;
            setSize(xwidth,yheight);
            setTitle(title);
            setLayout(new BorderLayout());

            Panel op = new Panel (new FlowLayout(FlowLayout.CENTER,15,0));
            outPane = new ScrollPane();
            outPane.setSize(xwidth - 40, 80);
            outDisplay = new TextArea("",4, 24,TextArea.SCROLLBARS_VERTICAL_ONLY);
            outDisplay.setEditable(false);
            outPane.add(outDisplay);
            op.add(outPane);
            add(op,"North");

            Panel p = new Panel (new FlowLayout(FlowLayout.CENTER,15,0));
            inPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
            inPane.setSize(xwidth - 40, yheight - 120);
            inDisplay = new Panel(new GridLayout (0,2,10,10));
            inPane.add(inDisplay);
            p.add(inPane);
            add(p,"Center");


            p = new Panel(new BorderLayout());
            okButton = new Button("Aceptar");
            okButton.addActionListener(this);
            okButton.setEnabled(false);
            p.add("Center",okButton);
            closeButton = new Button("Cerrar");
            closeButton.addActionListener(this);
            closeButton.setEnabled(true);
            p.add("East",closeButton);
            add("South",p);
            addWindowListener (new WindowAdapter () {
                    public void windowClosing(WindowEvent e) {
                        System.exit (0);
                    }
                });
            setVisible(true);
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

        private Data getEntry (String s) {
            if (table.containsKey(s))
                return table.get(s);
            else {
                outDisplay.append("\nERROR: No such input label: "+s+"\n");
                return null;
            }
        }

        /**
         *  Lee un int de la caja con esa etiqueta
         */
        public Integer leeInt () {
            String s = "Entero";
        	try {
                Data d = getEntry(s);
                return Integer.valueOf(d.value);
            } catch (NumberFormatException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entero en la entrada "+s+" con formato incorrecto");
                throw e;
            } catch (NullPointerException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entrada "+s+" no encontrada");
                throw e;
            }
        }

        /**
         *  Lee un double de la caja con esa etiqueta
         */
        public double leeDouble () {
            String s = "Double";
        	try {
                Data d = getEntry(s);
                return Double.valueOf(d.value);
            } catch (NumberFormatException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entero en la entrada "+s+" con formato incorrecto");
                throw e;
            } catch (NullPointerException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entrada "+s+" no encontrada");
                throw e;
            }
        }
               
        /**
         * Lee un String de la caja con esa etiqueta
         */
        public Character leeString () {
        	String s = "VarChar";
        	try {
                Data d = getEntry(s);
                return d.value.charAt(0);
            } catch (NullPointerException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entrada "+s+" no encontrada");
                throw e;
            }
        }

        private void insertPrompt(Data d, String s, TextField t) {
            Panel p;
            p = new Panel(new FlowLayout(FlowLayout.RIGHT));
            p.add(new Label(s));
            inDisplay.add(p);
            t.addActionListener(this);
            t.setEditable(true);
            p = new Panel(new FlowLayout(FlowLayout.LEFT));
            p.add(t);
            inDisplay.add(p);
            d.field = t;
        }

        /**
         * Crea una caja para leer un valor int
         */
        public void creaEntrada (String s, int n) {
            Data d = new Data();
            TextField t = new TextField(10);
            insertPrompt(d, s, t);
            d.value = Integer.toString(n);
            t.setText(d.value);
            if (table.containsKey(s)) {
                Mensaje error=new Mensaje("Error");
                error.escribe("Entrada "+s+" esta repetida");
            }
            table.put(s, d);
        }

        /**
         *  Crea una caja para leer un valor double
         */
        public void creaEntrada (String s, double n) {
            Data d = new Data();
            TextField t = new TextField(15);
            insertPrompt(d, s, t);
            d.value = Double.toString(n);
            t.setText(d.value);
            if (table.containsKey(s)) {
                Mensaje error=new Mensaje("Error");
                error.escribe("Entrada "+s+" esta repetida");
            }
            table.put(s, d);
        }

        /**
         * Crea una caja para leer un valor String
         */
        public void creaEntrada (String s, String n) {
            Data d = new Data();
            int maxLength=n.length()+2;
            if (maxLength<20) {
                maxLength=20;
            }
            TextField t = new TextField(maxLength);
            insertPrompt(d, s, t);
            d.value = n;
            t.setText(d.value);
            if (table.containsKey(s)) {
                Mensaje error=new Mensaje("Error");
                error.escribe("Entrada "+s+" esta repetida");
            }
            table.put(s, d);
        }

        /**
         *  Muestra un mensaje, y espera a que el
         *  usuario teclee datos y pulse aceptar; luego cierra la ventana<p>
         */
        public void esperaYCierra (String s) {
            espera(s);
            setVisible(false);
        }
        /**
         *  Espera a que el usuario teclee datos y pulse aceptar; 
         *  luego cierra la ventana<p>
         */
        public void esperaYCierra () {
            espera("");
            setVisible(false);
        }

        /** 
         * Espera a que el usuario teclee datos y pulse aceptar <p> 
         */
        public void espera () {
            espera("");
        }

        /**
         *  Muestra un mensaje, y espera a que el
         *  usuario teclee datos y pulse aceptar<p>
         */
        public void espera (String s) {
            outDisplay.append("\n"+s);
            okButton.setEnabled(true);
            this.pack();
            setVisible(true);
            okWatcher.watch();
            outDisplay.append("\n\n");
            // copy all the values from the boxes to the table
            for (Enumeration e = table.keys(); e.hasMoreElements();) {
                String name = (String) e.nextElement();
                Data d = table.get(name);
                d.value = d.field.getText();
                table.put(name, d);
            }
        }

        /**
         *  Muestra un string en la cabecera
         */
        public void println (String s) {
            outDisplay.append(s+"\n");
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
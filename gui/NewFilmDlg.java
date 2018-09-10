package videoteca.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase NewFilmDlg que representa el dialogo para a�adir peliculas
 */
public class NewFilmDlg extends JDialog {
    // Panel principal del dialogo
    private Container contentPane;

    // Componentes visuales del dialogo
    private final JTextField titleTxf;
    private final JComboBox<String> genreCbx;
    private final JTextField yearTxf;
    private final JButton okBtn;
    private final JButton cancelBtn;

    private boolean wasOk;  // wasOk = true  : los datos introducidos son válidos
                            // wasOk = false : los datos introducidos no se tienen en cuenta
    private String genre;   // almacena el género de la película introducida
    
    // Listener que valida los datos de la nueva pelicula
    private ActionListener okHandler= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            wasOk =true;
            setVisible(false);
        }
    };

    // Listener que descarta los datos de la nueva pelicula
    private ActionListener cancelHandler= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            setVisible(false);
        }
    };

    /**
     * A�ade un nuevo componente al panel principal precedido con 
     * una etiqueta y con la alineacion indicada
     */ 
    private void addEntryLine(String text, JComponent comp, int aling){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(aling));
        panel.add(new JLabel(text+":"));
        panel.add(comp);
        contentPane.add(panel);
    }

    /**
     * Añade los botones de la aplicación al panel principal 
     */ 
    private void addButtons(){
        JPanel buttons= new JPanel();
        buttons.add(okBtn);
        buttons.add(cancelBtn);
        contentPane.add(buttons);
    }

    /**
     * Constructor del diálogo al que se le pasa una referencia 
     * a la ventana principal de la que depende
     * 
     * @param f referencia al frame de la ventana principal
     */ 
    public NewFilmDlg(JFrame f) {

        // Se inicializan las opciones del diálogo
        super(f, "Nueva pelicula");
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(f);
        wasOk = false;
        genre = "";

        // Se crea el componente para introducir el título de la película
        titleTxf = new JTextField(15);
        titleTxf.addActionListener(okHandler);

        // Se crea el componente para introducir el año de la película
        yearTxf = new JTextField(6);
        yearTxf.addActionListener(okHandler);

        // Se crea el componente para introducir el género de la película
        genreCbx = new JComboBox<>(new String[]{"Otro", "Musical", "Ciencia ficcion", "epica", "Animacion"});
        genreCbx.setSelectedItem("Otro");
        genre = (String) genreCbx.getSelectedItem();
        genreCbx.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    genre = (String) genreCbx.getSelectedItem();
                }
            }

        });

        // Se crean los botones de interacción con el diálogo
        okBtn = new JButton("Aceptar");
        okBtn.addActionListener(okHandler);
        cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(cancelHandler);

        // Interfaz del panel principal al que se le a�aden los componentes visuales
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        addEntryLine("Titulo   ", titleTxf,FlowLayout.RIGHT);
        addEntryLine("A�o     ", yearTxf,FlowLayout.LEFT);
        addEntryLine("Genero", genreCbx,FlowLayout.LEFT);
        addButtons();
        
        pack(); 
    }

    /**
     * Devuelve el nombre de la película
     * 
     * @return título de la película introducida
     */ 
    public String getFilmTitle() {
        return titleTxf.getText();
    }

    /**
     * Devuelve el año de estreno de la película
     * 
     * @return año de la película introducida
     */ 
    public String getYear() {
        return yearTxf.getText();
    }

    /**
     * Devuelve el género de la película
     * 
     * @return género de la película introducida
     */ 
    public String getGenre() {
        if (genre.equals("Otro") && isOk()) {
            String newGenre = JOptionPane.showInputDialog(NewFilmDlg.this, 
                "Introduzca el genero de la pelicula",
                "Nuevo genero",
                JOptionPane.PLAIN_MESSAGE);
            if (newGenre != null) {
                genre = newGenre;
            } 
        }

        return genre;
    }

    /**
     * Devuelve si los datos introducidos se aceptan o no
     * 
     * @return true los datos de la película introducida son válidos o
     * @return false y los datos de la película introducida no se tienen en cuenta
     */ 
    public boolean isOk() {
        return wasOk;
    }
}

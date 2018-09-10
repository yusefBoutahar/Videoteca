package videoteca.gui;

import videoteca.source.*;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


/**
 * Clase GenresListDlg que representa en un dialogo del Listado de peliculas de cada Genero
 * en un JTextArea
 */

public class GenresListDlg extends JDialog {

    private JTextArea area;

    public GenresListDlg (Frame f, Videoteca v) {

        super(f,"Listado de peliculas de cada Genero");
        setLocationRelativeTo(f);// se encarga de que el JFrame este centrado
        setVisible(true);
        setResizable(false);// hace que no se pueda redimensionar
        setModal(true);
               
        area = new JTextArea (v.getGenres().toString());
        area.setEditable(false);
        add(area);
        pack();
        

        
        

    }
}
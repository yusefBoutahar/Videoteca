package videoteca.gui;
import videoteca.source.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import javafx.scene.layout.Border;

/**
 * Clase VideotecaGUI que representa la ventana principal de la videoteca
 */
public class VideotecaGUI extends JFrame {
    
    private final JButton anadir, generos; //Botones de a�adir pelicula y generos de peliculas
    private final JPanel panelBotones, panelOrden;
    private final JTextArea films; //Area donde estaran las peliculas
    private final JLabel orden;
    private final JRadioButton titulo, ano;
    private final ButtonGroup grupo;
    private final Videoteca miVideoteca = new Videoteca("Mi videoteca");
    
    public VideotecaGUI(String t){
        super(t);

        setResizable(true);// hace que se pueda redimensionar
        setMinimumSize(new Dimension(550,350));
        setVisible(true);
        Container c = getContentPane();

        
        //-------------------------------------------------------------------------------------

        /** AREA DE TEXTO DONDE IRAN LAS PELICULAS**/

        // a�adimos las peliculas
        initVideoteca(miVideoteca);// inicaliza la videoteca en el meotodo initVideoteca (esta abajo) 
        films = new JTextArea(this.miVideoteca.toString());
        films.setEditable(false);// para que no se edite el area(el texto)
        
        JScrollPane jsp = new JScrollPane(films);// le a�adimos barras de desplazamientoi
        c.add(jsp);

        //---------------------------------JButtons----------------------------------------------------


        /** BOTONES PARA A�ADIR PELICULAS Y VER LOS GENEROS **/
        
        panelBotones = new JPanel();
        panelBotones.setLayout(new BorderLayout(0,-8));
        
        JPanel aux1 = new JPanel();
        anadir = new JButton("Anadir pelicula");
        anadir.addActionListener(new GestorBotones());
        aux1.add(anadir);
        
        JPanel aux2 = new JPanel();
        generos = new JButton("Generos de peliculas");
        generos.addActionListener(new GestorBotones());
        aux2.add(generos);
        
        panelBotones.add(aux1,BorderLayout.NORTH);
        panelBotones.add(aux2,BorderLayout.CENTER);
        c.add(panelBotones, BorderLayout.WEST);
        
        
        //-----------------------------JRadioButtons--------------------------------------------------------
        
        
        /** LOS BOTONES PARA ORDENAR **/
        
        
        panelOrden = new JPanel();  
        orden = new JLabel("Orden: ");
        
        panelOrden.add(orden);
        grupo = new ButtonGroup();
        
        titulo = new JRadioButton("Por titulo", true);// por defecto es este
        titulo.addActionListener(new GestorRadioButtons());
        ano = new JRadioButton("Por a�o", false);
        ano.addActionListener(new GestorRadioButtons());
        
        panelOrden.add(titulo);
        grupo.add(titulo);
        panelOrden.add(ano);
        grupo.add(ano);
        c.add(panelOrden, BorderLayout.SOUTH);

        //-------------------------------------------------------------------------------------

        pack();// se adapta al tama�o por defecto de lo que hay en su interior
        
        //-------------------------------------------------------------------------------------
        
        /** HACE QUE LA VENTANA ESTE CENTRADA EN LA PANTALLA */
        
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension dimPantalla = miPantalla.getScreenSize();// captura la resolucion de la pantalla
        int alturaPantalla = dimPantalla.height;
        int anchoPantalla = dimPantalla.width;
        
        setSize(600,400);
        setLocation(anchoPantalla/4, alturaPantalla/4);// con esto estara centrado
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    private class GestorRadioButtons implements ActionListener {
    	
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == titulo){// cuando se pulsa por titulo
                miVideoteca.setOrder(0);// por defecto
                films.setText(miVideoteca.toString());
                
            } 
            if(e.getSource() == ano) {
                miVideoteca.setOrder(1);
                films.setText(miVideoteca.toString());
            }
        }
    }
    
    
    
    private class GestorBotones implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == anadir) {
            	try {
            		NewFilmDlg nuevo = new NewFilmDlg(VideotecaGUI.this);
            		nuevo.setVisible(true);
            		if(nuevo.isOk()) {// si se pulso aceptar
            			miVideoteca.add(new Film(nuevo.getFilmTitle(),nuevo.getGenre() ,Integer.parseInt(nuevo.getYear())));// a�adimos la nueva peli
            			films.setText(miVideoteca.toString());// edita el area que ya esta
            			
            		}
            	} catch (Exception e1) {
            		JOptionPane.showMessageDialog(null,
            				"No se ha podido a�adir la pelicula",
            				"ERROR!!",
            				JOptionPane.ERROR_MESSAGE);
            	}
            }
            if(e.getSource() == generos) {

            	JOptionPane.showMessageDialog(null,
            			miVideoteca.getGenres().toString(),
        				"Listado de peliculas de cada Genero",
        				JOptionPane.PLAIN_MESSAGE);
            	
                //GenresListDlg generos = new GenresListDlg(VideotecaGUI.this, miVideoteca);
                //generos.setVisible(true);

            }
        }

    }
    

// DESCOMENTAR PARA INICIALIZAR LA VIDEOTECA
    /**
     * Inicializa la videoteca con peliculas de varios generos
     */ 
    private void initVideoteca(Videoteca videoteca){
        videoteca.setOrder(0);
        
        videoteca.add(new Film("Los miserables", "Musical", 2012));
        videoteca.add(new Film("El fantasma de la opera", "Musical", 2004));
        videoteca.add(new Film("Cantando bajo la lluvia", "Musical", 1952));
        videoteca.add(new Film("Forrest Gump", "Comedia", 1994));
        videoteca.add(new Film("Star Wars: Episodio IV - Una nueva esperanza", "Ciencia ficcion", 1977));
        videoteca.add(new Film("Star Wars: Episodio V - El Imperio contraataca", "Ciencia ficcion", 1980));
        videoteca.add(new Film("Star Wars: Episodio VI - El regreso del Jedi", "Ciencia ficcion", 1983));
        videoteca.add(new Film("Star Wars: Episodio I - La amenaza fantasma", "Ciencia ficcion", 1999));
        videoteca.add(new Film("Star Wars: Episodio II - El ataque de los clones", "Ciencia ficcion", 2002));
        videoteca.add(new Film("Star Wars: Episodio III - La venganza de los Sith", "Ciencia ficcion", 2005));
        videoteca.add(new Film("Star Wars: Episodio VII - El despertar de la Fuerza", "Ciencia ficcion", 2015));
        videoteca.add(new Film("Rogue One: Una historia de Star Wars", "Ciencia ficcion", 2016));
        videoteca.add(new Film("Lo que el viento se llevo", "epica", 1939));
        videoteca.add(new Film("Lawrence de Arabia", "epica", 1962));
        videoteca.add(new Film("La lista de Schindler", "epica", 1993));
        videoteca.add(new Film("El rey leon", "Animacion", 1994));
        videoteca.add(new Film("La bella y la bestia", "Animacion", 1991));
        videoteca.add(new Film("Buscando a Nemo", "Animacion", 2004));
        videoteca.add(new Film("Buscando a Nemo", "Dibujos", 2004));
    }



    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            
        }
        new VideotecaGUI("Mi videoteca");
    }
}

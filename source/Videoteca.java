package videoteca.source;
import java.util.*;

/**
 * La clase Videoteca representa el conjunto de peliculas registradas
 * Se usara para representar una coleccion de peliculas
 * 
 * @author Yousuf Boutahar El Maachi
 * @version 25/11/2017
 *
 */
public class Videoteca {

    // CREACION DE CAMPOS DE CLASE
    
    private int ord = 0;    // guardara el orden establecido de la videoteca
    private String nombre;  // nombre de la Videoteca
    private Set<Film> estanteria = new HashSet<Film>();// Conjunto de peliculas
                                                       // en principio sin orden
/*
    HashSet = orden aleatorio
    TreeSet = orden establecido
*/

    public Videoteca (String nombre) {
        this.nombre = nombre;
    }

    /**
     * Añade una nueva película no repetida y devuelve verdadero, o no la
     * añade y devuelve falso si la película ya existe.
     * 
     * @param pelicula
     * @return true o false
    */
    public boolean add(Film pelicula) {
        for(Film f: estanteria) {
            if(f.equals(pelicula)) {
                return false;
            }
        }
        return estanteria.add(pelicula);
    }

    /**
     * Establece el orden en que se genera el listado de películas con getFilms():
     * ord = 0: Por título, género y year (opción por defecto)
     * ord = 1: Por year, título y género
     * 
     * @param ord
    */
    public void setOrder(int ord) {
        if(ord == 0 || ord == 1){
            this.ord = ord;
        }
    }

    /**
     * Devuelve el conjunto de peliculas en el orden establecido por
     * setOrder o null si no hay peliculas registradas
     * 
     * @return Set<Film>, o null
    */
    public Set<Film>getFilms(){
        if(estanteria == null){ // si no hay peliculas
            return null;
        } 
        if(this.ord == 1) {
            Set<Film> aux = new TreeSet<Film>(new Comparator<Film>() {// Usamos una clase interna/anonima para el ordenamiento 
                @Override
                public int compare(Film a, Film b) {
                    if(a.getYear() - b.getYear() == 0){// si los a�os son iguales ordenamos segun los titulos. 
                        return a.getTitle().compareTo(b.getTitle()); 
                    } else {
                        return a.getYear() - b.getYear();
                    }
                }
            });// final de la clase interna/anonima
            aux.addAll(estanteria);
            return aux;
        }
        // SI NO ES 1 ES CERO
        Set<Film> aux = new TreeSet<Film>();// (Ordena por defecto)
        aux.addAll(estanteria);
        return aux;
    }

    /**
     * Devuelve una lista de ristras en orden alfabético. Cada ristra se obtiene
     * concatenando el género de la película seguido del carácter dos puntos y
     * un espacio en blanco (": ") y seguido del número de películas de cada
     * género.
     * 
     * @return List<String>
    */
    public List<String>getGenres(){
    	
    	List<String> lista = new LinkedList<String>();
    	SortedMap<String, Integer> mapa = new TreeMap<String, Integer>();
    	for(Film f: estanteria){
    		Integer nPelis = mapa.get(f.getGenre());
    		
    		if(mapa.containsKey(f.getGenre())){
    			mapa.put(f.getGenre() ,nPelis+1 );
    		} else {
    			mapa.put(f.getGenre() ,1);
    		}
    	}
    	for(Map.Entry<String, Integer> e : mapa.entrySet()){// ya estaran ordenados por orden alfabetico por el TreeMap()
    		lista.add(e.getKey()+": "+e.getValue());
    	}
    	return lista;
/*
        List<String> lista = new LinkedList<String>();
        int cont = 0;
        for(Film f: estanteria) {
            for(Film p: estanteria) {
                if(f.getGenre().equals(p.getGenre())){
                    cont++;
                }
            }// final segundo for
            if(!lista.contains(f.getGenre()+": "+cont)) {
                lista.add(f.getGenre()+": "+cont);
            }
            cont = 0;// reestablecemos el contador
        }

        Collections.sort(lista, new Comparator<String>() {// Usando clase interna/anonima
            public int compare(String a, String b) {
                return a.compareTo(b);// orden alfabetico
            }
        });
        return lista;
*/

    }

    /**
     * Devuelve una String con el listado numerado de películas registradas en el
     * orden establecido, empezando en uno (después del número va un cierra
     * paréntesis y un espacio en blanco) separadas por saltos de línea ("\n") y
     * precedidas por el nombre de la videoteca seguido de dos puntos y un
     * salto de línea (":\n").
     * 
     * @return String
     */
    @Override
    public String toString() {
        
        Set<Film> aux = getFilms();
        String s1 = this.nombre + ":\n";
        int i = 1;
        for(Film v: aux) {
            s1 += i++ +") " + v.toString() + "\n";
        }
        return s1;
    }
    
    /* --------------------------------------------METODOS ADICIONALES---------------------------------------------------------------------*/

    /**
     * Por si solo queremos comprobar el nombre de la Videoteca
     * sin tener que llamar al toString() y realizar todo el trabajo
     * que realiza este.
     * 
     * @return El nombre de la Videoteca
     */
    public String getNombre() {// NO lo pide el Guion de la practica
        return nombre;
    }
    
    /**
     * @return El orden establecido de la Videoteca
     */
    public String dimOrden() {
        String s1 = "Se ordena por: ";
        if((ord == 0 || ord == 1)) {// para asegurarnos de que ha pasado por SetOrder()

            switch(ord) {
                case 0:
                    return s1 += " titulo, genero y year (opcion por defecto)";
                case 1:
                    return s1 += " year, titulo y genero";
                default: return "NO SE HA ESTABLECIDO NINGUN ORDEN";
            }
        }
        return "NO SE HA ESTABLECIDO NINGUN ORDEN"; // si no ha pasado por SetOrder()
    }
    
    
    
    

}
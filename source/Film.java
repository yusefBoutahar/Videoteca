package videoteca.source;
/**
 * Clase Film que representa peliculas 
 * 
 * @author JDGD, editada por Yousuf Boutahar El Maachi
 * @vesion 21/11/2017
 * 
 */
public class Film implements Comparable<Film>{
    private String title;   // nombre de la pelicula
    private String genre;   // clasificacion tematicade la pelicula
    private int year;       // year de estreno de la pelicula

    /**
     * Constructor al que se le pasa el titulo, 
     * el genero y el año de estreno de la pelicula
     * 
     * @param title nombre de la pelicula
     * @param genre genero de la pelicula
     * @param year anio de estreno de la pelicula
     */ 
    public Film(String title, String genre, int year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    /**
     * Devuelve el nombre de la pelicula
     * 
     * @return titulo de la pelicula
     */ 
    public String getTitle() {
        return title;
    }

    /**
     * Devuelve la clasificacion tematica de la pelicula
     * 
     * @return genero de la pelicula
     */ 
    public String getGenre() {
        return genre;
    }

    /**
     * Devuelve el year de estreno de la pelicula
     * 
     * @return year de la pelicula
     */ 
    public int getYear() {
        return year;
    }

    /**
     * Metodo hashCode() redefinido por usar HashSet<>
     * Este método viene a complementar al método equals
     * y sirve para comparar objetos de una forma más rápida en estructuras Hash
     * Cuando Java compara dos objetos en estructuras de
     * tipo hash (HashMap, HashSet etc) primero invoca al método hashcode y luego el equals.
     * 
     * @return nuevo hashSet
     */
    @Override
    public int hashCode() {
        final int primo = 31;
        int result = 1;
        result = primo * result + ((genre == null) ? 0 : genre.hashCode());// transparencia de aulaga
        result = primo * result + ((title == null) ? 0 : title.hashCode());
        result = primo * result + year;
        return result;
    }

    /**
     * Dos películas se consideran que son la misma si tienen el mismo título y se
     * estrenaron el mismo year.
     * 
     * @return true o false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Film)) {
            return false;
        }
        Film other = (Film) obj;
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        if (year != other.year) {
            return false;
        }
        return true;
    }

    /**
     * La representación como ristra de caracteres de una película se
     * obtiene concatenando el título, el género y el año separados por un espacio en blanco
     * antes y después de una barra vertical (" | ").
     * 
     * @return La ristra correspondiente a la pelicula
    */
    @Override
    public String toString() {
        return this.title + " | " + this.genre +  " | " + this.year;

    }

    /**
     * Este es el ordenamiento por defecto de las peliculas
     * en la Videoteca.
     * 
     * @return ordenamiento Por título, género y year (opción por defecto)
     */ 
    @Override
    public int compareTo(Film otro) {
        
        if(this.title.compareTo(otro.title) == 0 && this.year != otro.year){
            return this.genre.compareTo(otro.genre); 
        } else {
            return this.title.compareTo(otro.title);
        }
        
        /*
        if(this.title.compareTo(otro.title) == 0) {
            // si los titulos son iguales, pasamos a los generos
            if(this.genre.compareTo(otro.genre) == 0) {
                // si los generos son iguales, pasamos a los year
                if(this.year < otro.year){
                    return -1;
                } 
                if(this.year > otro.year){
                    return 1;
                } 
                return 0;
            } else {
                return this.genre.compareTo(otro.genre);
            }
        } else {
            return this.title.compareTo(otro.title);
        }
        */
    }



    
}
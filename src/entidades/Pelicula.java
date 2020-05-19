package entidades;

public class Pelicula {

    private int id;
    private String titol;
    private String estreno;
    private String rating;
    private String idioma;

    public Pelicula(int id, String titol, String estreno, String rating, String idioma) {
        this.id = id;
        this.titol = titol;
        this.estreno = estreno;
        this.rating = rating;
        this.idioma = idioma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id=" + id + ", titol=" + titol + ", estreno=" + estreno + ", rating=" + rating + ", idioma=" + idioma + '}';
    }

}

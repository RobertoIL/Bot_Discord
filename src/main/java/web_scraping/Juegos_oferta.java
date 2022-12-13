package web_scraping;

public class Juegos_oferta{
   private String nombre;
   private String precio;
   private String sitio_web;
   private String imagen;

    public Juegos_oferta(String nombre, String precio, String sitio_web, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.sitio_web = sitio_web;
        this.imagen = imagen;
    }

    public Juegos_oferta(String nombre, String precio, String sitio_web) {
        this.nombre = nombre;
        this.precio = precio;
        this.sitio_web = sitio_web;
    }


    @Override
    public String toString() {
        return "nombre= " + nombre + "\n" +
                "precio= " + precio + "\n" +
                "sitio_web= " + sitio_web + "\n";
    }
}

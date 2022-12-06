package web_scraping;

public class Juegos_oferta extends Juego{
   private String precio;


    public Juegos_oferta(String nombre,String precio, String sitio_web, String imagen) {
        super(nombre, sitio_web, imagen);
        this.precio = precio;
    }

    public Juegos_oferta(String nombre, String sitio_web, String precio) {
        super(nombre,sitio_web);
        this.precio = precio;
    }
}

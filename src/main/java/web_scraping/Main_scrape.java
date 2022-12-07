package web_scraping;

import Firebase.CRUDFirebase;

import java.util.stream.Collectors;

public class Main_scrape {
    public static void main(String[] args) {
        Conseguir_los_juegos_en_oferta inicio = new Conseguir_los_juegos_en_oferta();
        inicio.obtenerOfertasdeGog();
        inicio.obtenerOfertasSteam();
        CRUDFirebase baseConLasOfertas = new CRUDFirebase();
        for (int i = 0; i < inicio.getJuegos_ofertados().size(); i++) {
            baseConLasOfertas.crearDocumento(inicio.getJuegos_ofertados(),inicio.getJuegos_ofertados().keySet().stream().collect(Collectors.toList()).get(i),"juegos_ofertas");
        }
        for (int i = 0; i < inicio.getJuegos_gratis().size(); i++) {
            baseConLasOfertas.crearDocumento(inicio.getJuegos_gratis(),inicio.getJuegos_gratis().keySet().stream().collect(Collectors.toList()).get(i),"juegos_gratis");
        }
    }
}

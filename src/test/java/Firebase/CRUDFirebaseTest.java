package Firebase;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import web_scraping.Juegos_gratis;

class CRUDFirebaseTest {

    @Test
    void testConstructor() {

    }


    @Test
    void testAgregarJuegosGratis() {
        CRUDFirebase.agregarJuegosGratis("Nombre", "Sitio web", "Imagen");
    }


    @Test
    void testAgregarJuegosOferta() {
        CRUDFirebase.agregarJuegosOferta("Nombre", "Precio", "Sitio web", "Imagen");
    }


    @Test
    void testObtenerJuegosGratis() {


    }


}


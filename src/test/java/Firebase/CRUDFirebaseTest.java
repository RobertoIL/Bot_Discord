package Firebase;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import web_scraping.Juegos_gratis;

class CRUDFirebaseTest {

    @BeforeAll
    static void beforeAll() {
        CRUDFirebase crudFirebase = new CRUDFirebase();
    }

    @Test
    void testConstructor() {
        beforeAll();
    }

    @Test
    void addData(){
        CRUDFirebase crudFirebase = new CRUDFirebase();
        //Assertions.assertDoesNotThrow();
    }



}


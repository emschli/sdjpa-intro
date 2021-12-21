package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by jt on 7/3/21.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //brauchen wird damit unten @Order(n) funktioniert
@DataJpaTest // lädt nur die nötigsten Sachen -> der Datainitializer wird nicht automatisch ausgeführt!
            // Wir starten mit einer leeren H2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"}) //holt sich zusätzlich beans dazu!
public class SpringBootJpaTestClass3Slice {

    @Autowired
    BookRepository bookRepository;

    @Commit //Veränderungen durch Tests bleiben erhalten was man quasi nie möchte...
    @Order(1) // Wenn veränderungen erhalten bleiben ist die Reihenfolge noch wichtiger!!
    @Test
    void testJpaTestSplice() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

        bookRepository.save(new Book("My Book", "1235555", "Self", null));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3); //nur weil der vorherige Test verändert hat erwarten wir das hier

    }
}

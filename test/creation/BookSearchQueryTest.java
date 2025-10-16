package creation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class BookSearchQueryTest {

    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

        List<Book> books = new BookSearchQueryBuilder().withLastName("dickens").build().execute();

        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("dickens"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

        List<Book> books = new BookSearchQueryBuilder().withFirstName("Jane").build().execute();

        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("Austen"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueByTitle() {

        List<Book> books = new BookSearchQueryBuilder().withTitle("Two Cities").build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("dickens"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

        List<Book> books = new BookSearchQueryBuilder().publishedBefore(1700).build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Shakespeare"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

        List<Book> books = new BookSearchQueryBuilder().publishedAfter(1950).build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Golding"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

        List<Book> books = new BookSearchQueryBuilder().withLastName("dickens")
                .publishedBefore(1840).build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("charles dickens"));
    }
}

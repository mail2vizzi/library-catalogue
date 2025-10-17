package creation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class BookSearchQueryTest {

    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

        List<Book> books = new BookSearchQueryBuilder().withLastName("Schildt").build().execute();

        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("Schildt"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

        List<Book> books = new BookSearchQueryBuilder().withFirstName("Joshua").build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Joshua Bloch"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueByTitle() {

        List<Book> books = new BookSearchQueryBuilder().withTitle("Java Concurrency in Practice").build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Brian"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

        List<Book> books = new BookSearchQueryBuilder().publishedBefore(2000).build().execute();

        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("Erich Gamma"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

        List<Book> books = new BookSearchQueryBuilder().publishedAfter(2010).build().execute();

        assertThat(books.size(), is(3));
        assertTrue(books.get(0).matchesAuthor("Craig Walls"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

        List<Book> books = new BookSearchQueryBuilder().withLastName("Goetz")
                .publishedBefore(2008).build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Brian Goetz"));
    }
}

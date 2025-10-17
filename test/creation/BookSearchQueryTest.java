package creation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import creation.catalogues.BritishLibraryCatalogue;
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

    @Test
    public void searchesForBooksInBritishLibraryCatalogueByAuthorSurname() {

        List<Book> books = new BookSearchQueryBuilder().withCatalogue(BritishLibraryCatalogue.getInstance())
                .withLastName( "dickens").build().execute();

        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("dickens"));
    }

    @Test
    public void searchesForBooksInBritishLibraryCatalogueByAuthorFirstname() {

        List<Book> books = new BookSearchQueryBuilder().withCatalogue(BritishLibraryCatalogue.getInstance())
                .withFirstName("Jane").build().execute();

        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("Austen"));
    }

    @Test
    public void searchesForBooksInBritishLibraryCatalogueByTitle() {

        List<Book> books = new BookSearchQueryBuilder().withCatalogue(BritishLibraryCatalogue.getInstance())
                .withTitle("Two Cities").build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("dickens"));
    }

    @Test
    public void searchesForBooksInBritishLibraryCatalogueBeforeGivenPublicationYear() {

        List<Book> books = new BookSearchQueryBuilder().withCatalogue(BritishLibraryCatalogue.getInstance())
                .publishedBefore(1700).build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Shakespeare"));
    }

    @Test
    public void searchesForBooksInBritishLibraryCatalogueAfterGivenPublicationYear() {

        List<Book> books = new BookSearchQueryBuilder().withCatalogue(BritishLibraryCatalogue.getInstance())
                .publishedAfter(1950).build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Golding"));
    }

    @Test
    public void searchesForBooksInBritishLibraryCatalogueWithCombinationOfParameters() {

        List<Book> books = new BookSearchQueryBuilder().withCatalogue(BritishLibraryCatalogue.getInstance())
                .withLastName("dickens").publishedBefore(1840).build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("charles dickens"));
    }
}

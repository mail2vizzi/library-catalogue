package creation.catalogues;

import creation.Book;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class GeneralLibraryCatalogue implements LibraryCatalogue{
    List<Book> searchFor(String query, Stream<Book> catalogue) {
        return catalogue
                .filter(book -> book.matchesAuthor(QueryParser.lastNameFrom(query)))
                .filter(book -> book.matchesAuthor(QueryParser.firstNameFrom(query)))
                .filter(book -> book.matchesTitle(QueryParser.titleFrom(query)))
                .filter(book -> book.publishedSince(QueryParser.publishedAfterFrom(query)))
                .filter(book -> book.publishedBefore(QueryParser.publishedBeforeFrom(query)))
                .collect(Collectors.toList());
    }
}

package creation.catalogues;

import creation.Book;

import java.io.*;
import java.net.URL;
import java.util.*;

public final class OfficeLibraryCatalogue extends GeneralLibraryCatalogue {
    static {
        INSTANCE = new OfficeLibraryCatalogue();
    }

    private final static LibraryCatalogue INSTANCE;
    private final Collection<Book> catalogue = allTheBooks();

    private OfficeLibraryCatalogue() {
        // Prevent instantiation
    }

    @Override
    public List<Book> search(String query) {
        return super.searchFor(query, catalogue.stream());
    }

    public static LibraryCatalogue getInstance() {
        return INSTANCE;
    }

    private Collection<Book> allTheBooks() {
        System.out.println("Memory Usage: 1GB...");
        String line;
        String csvSplitBy = ",";
        URL resource = getClass().getClassLoader().getResource("data.csv");
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                if (values.length == 3) {
                    String title = values[0].trim();
                    String author = values[1].trim();
                    int year = Integer.parseInt(values[2].trim());
                    books.add(new Book(title, author, year));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(books);

    }
}

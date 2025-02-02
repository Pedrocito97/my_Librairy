package pedro.mylibrairy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pedro.mylibrairy.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);

    Page<Book> findByRating(int rating, Pageable pageable);

    Page<Book> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);

    Page<Book> findByCategoryContainingIgnoreCase(String category, Pageable pageable);

    Page<Book> findAllByUserUsername(String username, Pageable pageable);
}

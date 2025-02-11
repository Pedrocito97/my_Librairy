package pedro.mylibrairy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pedro.mylibrairy.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByUserUsername(String username, Pageable pageable);
}

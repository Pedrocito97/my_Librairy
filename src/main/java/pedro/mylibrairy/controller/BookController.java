package pedro.mylibrairy.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.validation.annotation.Validated;
import pedro.mylibrairy.dto.BookDTO;
import pedro.mylibrairy.model.Book;
import pedro.mylibrairy.model.User;
import pedro.mylibrairy.repository.BookRepository;
import pedro.mylibrairy.repository.UserRepository;



@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    private BookDTO convertToDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getRating(), book.getCategory());
    }



    @GetMapping
    public ResponseEntity<Page<BookDTO>> getAllBooks(@AuthenticationPrincipal UserDetails userDetails,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "title") String sortBy) {

        String username = userDetails.getUsername(); // Récupère le nom de l'utilisateur connecté
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> books = bookRepository.findAllByUserUsername(username, pageable);

        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Page.empty());
        }

        Page<BookDTO> bookDTOs = books.map(this::convertToDTO);
        return ResponseEntity.ok(bookDTOs);
    }



    // DELETE a book only if it belongs to the authenticated user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {

        String username = userDetails.getUsername(); // Récupère l'utilisateur connecté
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ID: " + id));

        if (!book.getUser().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Retourne 403 si l'utilisateur n'est pas le propriétaire
        }

        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in");
        }

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

        book.setUser(user);
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }
}

package pedro.mylibrairy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public void addBook(Book book) {
        books.add(book);
        book.setUser(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setUser(null);
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<Book> getBooks() { return books; }
    public void setBooks(Set<Book> books) { this.books = books; }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // No roles for now
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Always return true for now
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Always return true for now
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Always return true for now
    }

    @Override
    public boolean isEnabled() {
        return true; // Always return true for now
    }
}
package space.devincoopers.portfolio.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Hasher {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().matches("Love4Bananas!", "$2a$10$aMiOjEm3qTDcaKQQ4hXnduQJerUvLQxjQvVz9CvjWHiHu2RydbycK"));
    }
}

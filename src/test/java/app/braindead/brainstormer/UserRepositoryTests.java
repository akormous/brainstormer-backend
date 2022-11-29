package app.braindead.brainstormer;

import app.braindead.brainstormer.user.User;
import app.braindead.brainstormer.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    // Test methods

    @Test
    public void testCreateUser() {
        User u = new User();
        u.setEmail("abcd@abcd.com");
        u.setPassword("12345678");
        u.setUsername("abcd");

        User savedUser = userRepository.save(u);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assert(u.getEmail()).equals(existUser.getEmail());
    }
}

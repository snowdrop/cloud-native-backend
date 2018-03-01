package me.snowdrop.cloudnative.backend;

import com.jayway.restassured.RestAssured;
import me.snowdrop.cloudnative.backend.repository.NoteRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocalTest extends AbstractBoosterApplicationTest {

    @Value("${local.server.port}")
    private int port;

    @Before
    public void beforeTest() {
        noteRepository.deleteAll();
        RestAssured.baseURI = String.format("http://localhost:%d/api/notes", port);
    }

}

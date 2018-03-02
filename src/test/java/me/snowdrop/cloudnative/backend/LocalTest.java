package me.snowdrop.cloudnative.backend;

import com.jayway.restassured.RestAssured;
import me.snowdrop.cloudnative.backend.model.Note;
import me.snowdrop.cloudnative.backend.repository.NoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocalTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    protected NoteRepository noteRepository;

    @Before
    public void beforeTest() {
        noteRepository.deleteAll();
        RestAssured.baseURI = String.format("http://localhost:%d/api/notes", port);
    }

    @Test
    public void testGetEmptyArray() {
        when().get()
                .then()
                .statusCode(200)
                .body(is("[]"));
    }


    @Test
    public void testGetOneNote() {
        Note cherry = new Note();
        cherry.setContent("cherry");
        cherry.setTitle("excellent");
        noteRepository.save(cherry);

        when().get(String.valueOf(cherry.getId()))
                .then()
                .statusCode(200)
                .body("id", is(cherry.getId().intValue()))
                .body("content", is(cherry.getContent()))
                .body("title", is(cherry.getTitle()));
    }

}

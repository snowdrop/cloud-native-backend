package me.snowdrop.cloudnative.backend;

import me.snowdrop.cloudnative.backend.model.Note;
import me.snowdrop.cloudnative.backend.repository.NoteRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

public abstract class AbstractBoosterApplicationTest {

    @Autowired
    protected NoteRepository noteRepository;

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

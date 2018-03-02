package me.snowdrop.cloudnative.backend;

import java.net.URL;

import com.jayway.restassured.RestAssured;
import me.snowdrop.cloudnative.backend.repository.NoteRepository;
import org.arquillian.cube.openshift.impl.enricher.RouteURL;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(Arquillian.class)
public class OpenShiftIT extends AbstractBoosterApplicationTest {

    @RouteURL("${app.name}")
    public URL baseURL;

    @Before
    public void setup() throws Exception {
        noteRepository.deleteAll();
        RestAssured.baseURI = baseURL + "api/notes";
    }

}

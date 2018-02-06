package me.snowdrop.app.repository;

import me.snowdrop.app.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Spring Boot Team.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

}

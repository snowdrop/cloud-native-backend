package me.snowdrop.cloudnative.backend.repository;

import me.snowdrop.cloudnative.backend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Spring Boot Team.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

}

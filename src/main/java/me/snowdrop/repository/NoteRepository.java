package me.snowdrop.repository;

import me.snowdrop.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

}

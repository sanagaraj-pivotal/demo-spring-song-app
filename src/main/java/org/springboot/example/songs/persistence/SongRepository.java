package org.springboot.example.songs.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Fabian Krüger
 */
public interface SongRepository extends JpaRepository<Song, Long> {
}

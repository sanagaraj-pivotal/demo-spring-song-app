package org.springboot.example.songs.persistence;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Fabian Krüger
 */
@DataJpaTest
@Transactional
class SongStatTest {

   @Autowired
   private SongRepository songRepository;

   @Autowired
   private SongStatRepository songStatRepository;

    @Test
    void test_renameMe() {
        Song song = new Song();
        song.setSongName("Some Song");
        songRepository.save(song);
        SongStat songStat = new SongStat();
        songStat.setSong(song);
        songStat.setRegion(Locale.forLanguageTag("de"));
        songStat.setTimesPlayed(13);
        songStat = songStatRepository.save(songStat);
        assertThat(songStat.getId()).isNotNull();
    }
}
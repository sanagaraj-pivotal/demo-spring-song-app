/*
 * Copyright 2021 - 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springboot.example.songs.business;

import com.translation.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springboot.example.songs.persistence.SongStat;
import org.springboot.example.songs.persistence.Song;
import org.springboot.example.controllers.dto.TopSongs;
import org.springboot.example.config.RegionConfig;
import org.springboot.example.songs.persistence.SongRepository;
import org.springboot.example.songs.persistence.SongStatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SongService {

    private final SongStatRepository songStatRepository;
    private final SongRepository songRepository;
    private final RegionConfig regionConfig;
    private final TranslationService translationService;

    public void updateSongStat(Long songId, Locale region, int  timesPlayed) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new IllegalArgumentException("Song with id '" + songId + " does not exist."));
        Optional<SongStat> bySongAndRegion = songStatRepository.findOptionalBySongAndRegion(song, region);
        if(bySongAndRegion.isEmpty()) {
            SongStat songStat = new SongStat();
            songStat.setSong(song);
            songStat.setRegion(region);
            songStat.incrementTimesPlayed(timesPlayed);
            songStatRepository.save(songStat);
        } else {
            bySongAndRegion.get().incrementTimesPlayed(timesPlayed);
        }
    }

    public TopSongs topSongsByRegion(Locale region) {
        List<SongStat> top3Songs = songStatRepository
                .findTop3SongsByRegionOrderByTimesPlayedDesc(region);

        return TopSongs.builder()
                .region(region)
                .title(translationService.translate("Top 10 songs"))
                .songs(top3Songs)
                .build();

    }

    public List<Song> getSongs() {
        List<Song> songs = songRepository.findAll();
        return songs;
    }

    public List<SongStat> getSongStats() {
        return songStatRepository.findAll();
    }
}

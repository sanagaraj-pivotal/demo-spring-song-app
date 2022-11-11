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

package org.springboot.example.songs.web;

import lombok.RequiredArgsConstructor;
import org.springboot.example.controllers.dto.TopSongs;
import org.springboot.example.songs.business.SongService;
import org.springboot.example.songs.persistence.Song;
import org.springboot.example.songs.persistence.SongStat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping("/top-songs")
    public TopSongs getTopSongsByRegion(Locale region) {
        return songService.topSongsByRegion(region);
    }

    @PostMapping(value = "/song-stats", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateSongStat(@Validated @RequestBody SongPlayed songPlayedRequest) {
        String regionStr = songPlayedRequest.getRegion();
        Locale region = Locale.forLanguageTag(regionStr);
        songService.updateSongStat(songPlayedRequest.getSongId(), region, songPlayedRequest.getTimesPlayed());
    }

    @GetMapping("/songs")
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping("/song-stats")
    public List<SongStat> getSongStats() {
        return songService.getSongStats();
    }
}

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

package org.springboot.example.songs.persistence;

import lombok.Getter;
import lombok.Setter;
import org.springboot.example.songs.persistence.Song;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Locale;

@Entity
@Getter
@Table(name = "SONG_STATS")
public class SongStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Song.class, optional = false)
    @Setter
    private Song song;

    @Setter
    @Positive
    @Column(name = "TIMES_PLAYED")
    private int timesPlayed;

    @Setter
    private Locale region;

    public void incrementTimesPlayed(int timesPlayed) {
        this.timesPlayed += timesPlayed;
    }
}

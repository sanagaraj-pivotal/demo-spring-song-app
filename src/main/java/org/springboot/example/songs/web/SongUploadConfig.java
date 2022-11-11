package org.springboot.example.songs.web;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class SongUploadConfig {
    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(-1);
        return commonsMultipartResolver;
    }
}

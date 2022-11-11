package org.springboot.example.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Locale;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "region")
public class RegionConfig {
    private Locale regionCode;

    public RegionConfig(Locale regionCode) {
        this.regionCode = regionCode;
    }
}

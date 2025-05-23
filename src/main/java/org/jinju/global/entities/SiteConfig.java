package org.jinju.global.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.jinju.global.constants.Device;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SiteConfig {
    private String siteTitle;
    private String description;
    private String keywords;
    private int cssVersion;
    private int jsVersion;
    private boolean useEmailAuth;
    private Device device;
}

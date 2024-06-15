// WebConfig.java

package com.example.imagegallery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration class to customize the Spring MVC settings.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Injects the value of the property 'gallery.image.folder' from application.properties
    @Value("${gallery.image.folder}")
    private String imageFolder;

    /**
     * Configures the locations of static resources such as images.
     *
     * @param registry the resource handler registry to add resource handlers to
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + imageFolder + "/");
    }
}

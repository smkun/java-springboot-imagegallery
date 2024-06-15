// GalleryController.java

package com.example.image_gallery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * GalleryController handles HTTP requests for the image gallery and about pages.
 */
@Controller
public class GalleryController {

    private static final Logger logger = Logger.getLogger(GalleryController.class.getName());

    // Injects the value of the property 'gallery.image.folder' from application.properties
    @Value("${gallery.image.folder}")
    private String imageFolder;

    /**
     * Handles GET requests to the root URL ("/") and returns the gallery view.
     *
     * @param model the model to add attributes to for rendering views
     * @return the name of the Thymeleaf template to render
     */
    @GetMapping("/")
    public String gallery(Model model) {
        logger.info("Accessing gallery endpoint");
        File folder = new File(imageFolder);
        
        // Filters the files in the directory to include only .jpg and .png files
        File[] images = folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));

        List<ImageDTO> imageDTOs = new ArrayList<>();
        if (images != null) {
            // Adds each image file's name to the list of ImageDTOs
            for (File image : images) {
                imageDTOs.add(new ImageDTO(image.getName()));
                logger.info("Image found: " + image.getName());
            }
        } else {
            logger.warning("No images found in directory: " + imageFolder);
        }

        // Adds the list of images to the model to be used in the Thymeleaf template
        model.addAttribute("images", imageDTOs);
        return "gallery";
    }

    /**
     * Handles GET requests to the "/about" URL and returns the about view.
     *
     * @return the name of the Thymeleaf template to render
     */
    @GetMapping("/about")
    public String about() {
        logger.info("Accessing about endpoint");
        return "about";
    }

    /**
     * Handles GET requests to the "/test" URL and returns the test view.
     *
     * @return the name of the Thymeleaf template to render
     */
    @GetMapping("/test")
    public String test() {
        logger.info("Accessing test endpoint");
        return "test";
    }
}

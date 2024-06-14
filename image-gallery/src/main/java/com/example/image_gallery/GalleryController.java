package com.example.image_gallery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class GalleryController {

    private static final Logger logger = Logger.getLogger(GalleryController.class.getName());

    @Value("${gallery.image.folder}")
    private String imageFolder;

    @GetMapping("/")
    public String gallery(Model model) {
        logger.info("Accessing gallery endpoint");
        File folder = new File(imageFolder);
        File[] images = folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));

        List<ImageDTO> imageDTOs = new ArrayList<>();
        if (images != null) {
            for (File image : images) {
                imageDTOs.add(new ImageDTO(image.getName()));
                logger.info("Image found: " + image.getName());
            }
        } else {
            logger.warning("No images found in directory: " + imageFolder);
        }

        model.addAttribute("images", imageDTOs);
        return "gallery";
    }

    @GetMapping("/about")
    public String about() {
        logger.info("Accessing about endpoint");
        return "about";
    }

    @GetMapping("/test")
    public String test() {
        logger.info("Accessing test endpoint");
        return "test";
    }
}

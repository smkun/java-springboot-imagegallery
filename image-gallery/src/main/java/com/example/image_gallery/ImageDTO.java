// ImageDTO.java

package com.example.image_gallery;

/**
 * Data Transfer Object (DTO) for representing an image.
 */
public class ImageDTO {
    private String name;

    // Constructor to initialize the image name
    public ImageDTO(String name) {
        this.name = name;
    }

    // Getter for image name
    public String getName() {
        return name;
    }

    // Setter for image name
    public void setName(String name) {
        this.name = name;
    }
}

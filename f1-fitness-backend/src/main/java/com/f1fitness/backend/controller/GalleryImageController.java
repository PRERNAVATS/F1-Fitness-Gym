package com.f1fitness.backend.controller;

import com.f1fitness.backend.dto.ApiResponse;
import com.f1fitness.backend.dto.GalleryImageDto;
import com.f1fitness.backend.service.GalleryImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
public class GalleryImageController {

    @Autowired
    private GalleryImageService galleryImageService;

    @PostMapping
    public ResponseEntity<ApiResponse<GalleryImageDto>> addImage(@Valid @RequestBody GalleryImageDto imageDto) {
        GalleryImageDto createdImage = galleryImageService.addImage(imageDto);
        ApiResponse<GalleryImageDto> response = new ApiResponse<>(true, "Gallery image added successfully", createdImage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GalleryImageDto>>> getAllImages() {
        List<GalleryImageDto> images = galleryImageService.getAllImages();
        ApiResponse<List<GalleryImageDto>> response = new ApiResponse<>(true, "Gallery images retrieved successfully", images);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GalleryImageDto>> getImageById(@PathVariable Long id) {
        GalleryImageDto image = galleryImageService.getImageById(id);
        ApiResponse<GalleryImageDto> response = new ApiResponse<>(true, "Gallery image retrieved successfully", image);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GalleryImageDto>> updateImage(
            @PathVariable Long id,
            @Valid @RequestBody GalleryImageDto imageDto) {
        GalleryImageDto updatedImage = galleryImageService.updateImage(id, imageDto);
        ApiResponse<GalleryImageDto> response = new ApiResponse<>(true, "Gallery image updated successfully", updatedImage);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteImage(@PathVariable Long id) {
        galleryImageService.deleteImage(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Gallery image deleted successfully");
        return ResponseEntity.ok(response);
    }
}

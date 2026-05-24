package com.f1fitness.backend.service;

import com.f1fitness.backend.dto.GalleryImageDto;

import java.util.List;

public interface GalleryImageService {
    GalleryImageDto addImage(GalleryImageDto imageDto);
    List<GalleryImageDto> getAllImages();
    GalleryImageDto getImageById(Long id);
    GalleryImageDto updateImage(Long id, GalleryImageDto imageDto);
    void deleteImage(Long id);
}

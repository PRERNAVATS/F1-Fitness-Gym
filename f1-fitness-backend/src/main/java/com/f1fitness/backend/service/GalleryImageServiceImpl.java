package com.f1fitness.backend.service;

import com.f1fitness.backend.dto.GalleryImageDto;
import com.f1fitness.backend.entity.GalleryImage;
import com.f1fitness.backend.exception.ResourceNotFoundException;
import com.f1fitness.backend.repository.GalleryImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryImageServiceImpl implements GalleryImageService {

    @Autowired
    private GalleryImageRepository galleryImageRepository;

    @Override
    public GalleryImageDto addImage(GalleryImageDto imageDto) {
        GalleryImage image = mapToEntity(imageDto);
        GalleryImage savedImage = galleryImageRepository.save(image);
        return mapToDto(savedImage);
    }

    @Override
    public List<GalleryImageDto> getAllImages() {
        return galleryImageRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GalleryImageDto getImageById(Long id) {
        GalleryImage image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gallery Image not found with id: " + id));
        return mapToDto(image);
    }

    @Override
    public GalleryImageDto updateImage(Long id, GalleryImageDto imageDto) {
        GalleryImage image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gallery Image not found with id: " + id));

        image.setTitle(imageDto.getTitle());
        image.setImageUrl(imageDto.getImageUrl());
        image.setDescription(imageDto.getDescription());

        GalleryImage updatedImage = galleryImageRepository.save(image);
        return mapToDto(updatedImage);
    }

    @Override
    public void deleteImage(Long id) {
        GalleryImage image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gallery Image not found with id: " + id));
        galleryImageRepository.delete(image);
    }

    private GalleryImage mapToEntity(GalleryImageDto dto) {
        return GalleryImage.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .imageUrl(dto.getImageUrl())
                .description(dto.getDescription())
                .build();
    }

    private GalleryImageDto mapToDto(GalleryImage entity) {
        return GalleryImageDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .imageUrl(entity.getImageUrl())
                .description(entity.getDescription())
                .build();
    }
}

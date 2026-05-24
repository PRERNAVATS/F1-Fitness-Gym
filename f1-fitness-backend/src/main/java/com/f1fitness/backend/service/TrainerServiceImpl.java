package com.f1fitness.backend.service;

import com.f1fitness.backend.dto.TrainerDto;
import com.f1fitness.backend.entity.Trainer;
import com.f1fitness.backend.exception.ResourceNotFoundException;
import com.f1fitness.backend.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public TrainerDto addTrainer(TrainerDto trainerDto) {
        Trainer trainer = mapToEntity(trainerDto);
        Trainer savedTrainer = trainerRepository.save(trainer);
        return mapToDto(savedTrainer);
    }

    @Override
    public List<TrainerDto> getAllTrainers() {
        return trainerRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainerDto getTrainerById(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + id));
        return mapToDto(trainer);
    }

    @Override
    public TrainerDto updateTrainer(Long id, TrainerDto trainerDto) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + id));

        trainer.setName(trainerDto.getName());
        trainer.setSpecialization(trainerDto.getSpecialization());
        trainer.setExperience(trainerDto.getExperience());
        trainer.setImageUrl(trainerDto.getImageUrl());
        trainer.setBio(trainerDto.getBio());

        Trainer updatedTrainer = trainerRepository.save(trainer);
        return mapToDto(updatedTrainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + id));
        trainerRepository.delete(trainer);
    }

    private Trainer mapToEntity(TrainerDto dto) {
        return Trainer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .specialization(dto.getSpecialization())
                .experience(dto.getExperience())
                .imageUrl(dto.getImageUrl())
                .bio(dto.getBio())
                .build();
    }

    private TrainerDto mapToDto(Trainer entity) {
        return TrainerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .specialization(entity.getSpecialization())
                .experience(entity.getExperience())
                .imageUrl(entity.getImageUrl())
                .bio(entity.getBio())
                .build();
    }
}

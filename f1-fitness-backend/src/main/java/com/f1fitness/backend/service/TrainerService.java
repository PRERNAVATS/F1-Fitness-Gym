package com.f1fitness.backend.service;

import com.f1fitness.backend.dto.TrainerDto;

import java.util.List;

public interface TrainerService {
    TrainerDto addTrainer(TrainerDto trainerDto);
    List<TrainerDto> getAllTrainers();
    TrainerDto getTrainerById(Long id);
    TrainerDto updateTrainer(Long id, TrainerDto trainerDto);
    void deleteTrainer(Long id);
}

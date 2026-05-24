package com.f1fitness.backend.controller;

import com.f1fitness.backend.dto.ApiResponse;
import com.f1fitness.backend.dto.TrainerDto;
import com.f1fitness.backend.service.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping
    public ResponseEntity<ApiResponse<TrainerDto>> addTrainer(@Valid @RequestBody TrainerDto trainerDto) {
        TrainerDto createdTrainer = trainerService.addTrainer(trainerDto);
        ApiResponse<TrainerDto> response = new ApiResponse<>(true, "Trainer added successfully", createdTrainer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TrainerDto>>> getAllTrainers() {
        List<TrainerDto> trainers = trainerService.getAllTrainers();
        ApiResponse<List<TrainerDto>> response = new ApiResponse<>(true, "Trainers retrieved successfully", trainers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainerDto>> getTrainerById(@PathVariable Long id) {
        TrainerDto trainer = trainerService.getTrainerById(id);
        ApiResponse<TrainerDto> response = new ApiResponse<>(true, "Trainer retrieved successfully", trainer);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainerDto>> updateTrainer(
            @PathVariable Long id,
            @Valid @RequestBody TrainerDto trainerDto) {
        TrainerDto updatedTrainer = trainerService.updateTrainer(id, trainerDto);
        ApiResponse<TrainerDto> response = new ApiResponse<>(true, "Trainer updated successfully", updatedTrainer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Trainer deleted successfully");
        return ResponseEntity.ok(response);
    }
}

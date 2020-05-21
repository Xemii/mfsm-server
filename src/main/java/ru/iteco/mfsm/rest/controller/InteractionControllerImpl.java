package ru.iteco.mfsm.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.iteco.mfsm.interaction.InteractionServiceImpl;
import ru.iteco.mfsm.interaction.domain.InteractionEntity;
import ru.iteco.mfsm.rest.dto.InteractionDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InteractionControllerImpl {
    @Autowired
    private InteractionServiceImpl interactionService;

    @GetMapping("/interactions")
    public List<InteractionDTO> getInteractionsList() {
        List<InteractionEntity> entities = interactionService.findAll();

        List<InteractionDTO> result = new ArrayList<>();
        for (InteractionEntity entity: entities) {
            result.add(mapToDTO(entity));
        }

        return result;
    }

    @GetMapping("/interactions/{id}")
    public InteractionDTO getInteractionByID(@PathVariable("id") String id) {
        InteractionEntity entity = interactionService.findById(id);
        if (ObjectUtils.isEmpty(entity)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Обращение не найдено");
        }

        return mapToDTO(entity);
    }

    @PostMapping("/interactions")
    public InteractionDTO createInteraction(@RequestBody InteractionDTO request) {
        InteractionEntity entity = mapToEntity(request);

        InteractionEntity savedEntity = interactionService.save(entity);
        return mapToDTO(savedEntity);
    }

    @PutMapping("/interactions/{id}")
    public InteractionDTO updateInteraction(@PathVariable("id") String id, @RequestBody InteractionDTO request) {
        InteractionEntity entity = mapToEntity(request);
        entity.setId(id);

        InteractionEntity savedEntity = interactionService.save(entity);
        return mapToDTO(savedEntity);
    }

    private InteractionDTO mapToDTO(InteractionEntity entity) {
        InteractionDTO dto = new InteractionDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContactName(!ObjectUtils.isEmpty(entity.getContact()) ? entity.getContact().getName(): null);
        return dto;
    }

    private InteractionEntity mapToEntity(InteractionDTO dto) {
        InteractionEntity entity = new InteractionEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        return entity;
    }
}

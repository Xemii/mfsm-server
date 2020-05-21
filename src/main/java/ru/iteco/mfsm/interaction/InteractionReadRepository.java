package ru.iteco.mfsm.interaction;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.mfsm.interaction.domain.InteractionEntity;

import java.util.List;
import java.util.Optional;

public interface InteractionReadRepository extends JpaRepository<InteractionEntity, String> {
    @Override
    Optional<InteractionEntity> findById(String s);

    @Override
    List<InteractionEntity> findAll();
}

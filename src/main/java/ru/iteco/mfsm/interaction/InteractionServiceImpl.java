package ru.iteco.mfsm.interaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.iteco.mfsm.dao.mfsm.MFSMDAO;
import ru.iteco.mfsm.interaction.domain.InteractionEntity;

import java.util.List;

@Service
public class InteractionServiceImpl {
    @Autowired
    private InteractionReadRepository readRepository;

    @Autowired
    private MFSMDAO writeRepository;

    public InteractionEntity save(InteractionEntity interaction) {
        if (ObjectUtils.isEmpty(interaction.getId())) {
            return writeRepository.createEntity("Interaction", "/interactions", interaction, InteractionEntity.class);
        }
        return writeRepository.updateEntity("Interaction", "/interactions/" + interaction.getId(), interaction, InteractionEntity.class);
    }

    public List<InteractionEntity> findAll() {
        return readRepository.findAll();
    }

    public InteractionEntity findById(String id) {
        return readRepository.findById(id).orElse(null);
    }
}

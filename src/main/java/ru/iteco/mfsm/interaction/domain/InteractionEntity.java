package ru.iteco.mfsm.interaction.domain;

import lombok.Getter;
import lombok.Setter;
import ru.iteco.mfsm.dao.mfsm.MFSMEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "interactions")
public class InteractionEntity implements MFSMEntity {
    @Id
    private String id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private ContactEntity contact;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "InteractionEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

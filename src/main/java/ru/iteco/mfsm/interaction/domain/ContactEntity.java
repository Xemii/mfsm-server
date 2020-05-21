package ru.iteco.mfsm.interaction.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Table(name = "contacts")
@Entity
public class ContactEntity {
    @Id
    private String id;
    private String name;
}

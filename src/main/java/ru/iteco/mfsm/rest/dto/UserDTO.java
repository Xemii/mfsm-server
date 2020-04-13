package ru.iteco.mfsm.rest.dto;

import ru.iteco.mfsm.dao.mfsm.MFSMEntity;

public class UserDTO implements MFSMEntity {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

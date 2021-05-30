package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileMoodle {
    @Id
    private Long id;
    @Column(name = "contenthash")
    private String contenthash;
    @Column(name = "filename")
    private String name;
    @Column(name = "userid")
    private Long idUser;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public String getContenthash() {
        return contenthash;
    }

    public String getName() {
        return name;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Long getModified() {
        return modified;
    }
}
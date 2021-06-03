package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileMoodle {
    @Id
    private Long id;
    @Column(name = "contenthash")
    private String contentHash;
    @Column(name = "filename")
    private String name;
    @Column(name = "userid")
    private Long idUser;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public String getContentHash() {
        return contentHash;
    }

    public String getName() {
        return name;
    }

    public Long getIdUser() {
        return idUser;
    }
}
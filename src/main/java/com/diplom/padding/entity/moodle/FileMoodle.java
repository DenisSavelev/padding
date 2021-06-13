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
    @Column(name = "itemid")
    private Long idItem;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private UserMoodle user;
    @Column(name = "component")
    private String component;
    @Column(name = "author")
    private String author;
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

    public Long getIdItem() {
        return idItem;
    }

    public UserMoodle getUser() {
        return user;
    }
}
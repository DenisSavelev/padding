package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "assignsubmission_file")
public class FileTaskMoodle {
    @Id
    private Long id;
    @Column(name = "assignment")
    private Long idTask;
    @Column(name = "submission")
    private Long idFile;

    public Long getId() {
        return id;
    }

    public Long getIdTask() {
        return idTask;
    }

    public Long getIdFile() {
        return idFile;
    }
}
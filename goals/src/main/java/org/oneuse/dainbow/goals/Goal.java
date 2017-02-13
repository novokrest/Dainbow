package org.oneuse.dainbow.goals;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String title;

    public Goal() { }

    public Goal(long id) {
        this.id = id;
    }

    public Goal(String title) {
        this.title = title;
    }

    public Goal(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

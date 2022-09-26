package net.javaguides.springbootbackend.model;


import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;


    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }


    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}



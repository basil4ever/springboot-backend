package net.javaguides.springbootbackend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name="employees",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email_id")
        })
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "employee_files",
    joinColumns = @JoinColumn(name = "employees_id"),
    inverseJoinColumns = @JoinColumn(name = "file_id"))
    private Set<File> files = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<Invoice> invoices;


    public Employee() {

    }

    public Employee(String firstName, String lastName, String emailId) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Set<File> getFiles() { return files; }

    public void setFiles(Set<File> files) { this.files = files; }

}


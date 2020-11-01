package com.SpringJwt.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "instructor")
@ApiModel(description = "Details about the Instructor")
public class Instructor extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "the unique id of an instructor")
    private int id;
    @ApiModelProperty(notes = "the first_name of an instructor")
    @NotNull(message = "First name can not be null")
    private String first_name;
    @ApiModelProperty(notes = "the last_name of an instructor")
    @NotNull(message = "Last name can not be null")
    private String last_name;
    @ApiModelProperty(notes = "the email of an instructor")
    @Email
    private String email;

    @OneToMany(mappedBy = "instructor",cascade = {CascadeType.ALL})
    private List<Course> courses;

    public Instructor(){

    }

    public Instructor(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

package com.management.management.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="company")
public class Company implements Serializable {
    @Id
    @GeneratedValue()
    @Column(name="companyid")
    private int compId;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Employee> employees;


    @Column(name="companyname")
    private String name;

}

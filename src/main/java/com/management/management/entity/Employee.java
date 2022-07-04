package com.management.management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name ="employee")
public class Employee  implements Serializable {
    @Id
    @GeneratedValue()
    @Column(name="empid")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="companyid", nullable=false,insertable = false ,updatable = false)
    @JsonBackReference
    private Company company;


    @Column(name="empname")
    private String name;



}

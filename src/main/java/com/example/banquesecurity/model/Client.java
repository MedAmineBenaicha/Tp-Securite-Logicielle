package com.example.banquesecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class Client {
    @Id
    @GeneratedValue
    private Integer id;
    private String nom;
    private String prenom;
    private String password;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles=new ArrayList<>();


    public Client(Integer id, String nom, String prenom)
    {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
    }
}

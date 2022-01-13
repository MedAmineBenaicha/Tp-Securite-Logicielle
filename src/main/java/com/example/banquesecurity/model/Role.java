package com.example.banquesecurity.model;

import com.example.banquesecurity.config.AppUserPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Authorities")
public class Role {
    @Id
    @GeneratedValue
    private Integer id;

    private String authority;


}

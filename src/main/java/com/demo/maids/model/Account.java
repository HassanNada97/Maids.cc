package com.demo.maids.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "accounts")
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Size(max = 100)
    @NotNull
    @Column(name = "password", nullable = false, length = 100)
    private String password;

}
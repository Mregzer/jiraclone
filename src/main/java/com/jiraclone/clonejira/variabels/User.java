package com.jiraclone.clonejira.variabels;

import com.jiraclone.clonejira.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    @Column(name ="username")
    private String username;
    @Column(name ="email")
//    private String name;
//    private String surname;
//    private Date birthdate;
    private String email;
    @Column(name ="role")
    private Role role = Role.USER;
    @Column(name ="password")
//    private Department department;
    private String password;
}

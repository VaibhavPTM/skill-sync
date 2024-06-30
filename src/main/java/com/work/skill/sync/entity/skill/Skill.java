package com.work.skill.sync.entity.skill;

import com.work.skill.sync.entity.user.UserSkill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Relationship with users
    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserSkill> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserSkill> getUsers() {
        return users;
    }

    public void setUsers(Set<UserSkill> users) {
        this.users = users;
    }
}

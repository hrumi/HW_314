//package ru.kata.spring.boot_security.demo.model;
//
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "roles")
//public class Role implements GrantedAuthority {
//
//    @Id
//    private Long id;
//    @Column
//    private String name;
//    @Transient
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
//
//    public Role(Long id) {
//        this.id = id;
//    }
//
//    public Role(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Role() {
//
//    }
//
//    @Override
//    public String getAuthority() {
//        return getName();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//}

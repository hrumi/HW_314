package ru.kata.spring.boot_security.demo.model;

//import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
//@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column
    private Integer age;

    @Column
    private String password;

//    @Transient
//    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER)  //Грузит Roles c User
    private Set<Role> roles;
//    @ManyToMany
//    @JoinTable(name = "users_roles",
//        joinColumns = @JoinColumn(name = "users_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;


    //Геттеры
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

//    public String getPasswordConfirm() {
//        return passwordConfirm;
//    }

    //Сеттеры

//    public void setPasswordConfirm(String passwordConfirm) {
//        this.passwordConfirm = passwordConfirm;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //equals hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;
        return age.equals(user.age);
    }

    @Override
    public int hashCode() {
        int result = Math.toIntExact(id);
        result = 31 * result + name.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }

    //from UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

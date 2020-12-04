package jonerys.test.springcrud.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="users")
public class User {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "login", nullable = false, length = 255)
    private String login;

    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private Role role;
}

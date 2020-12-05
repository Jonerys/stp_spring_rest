package jonerys.test.springcrud.security;

import jonerys.test.springcrud.model.Role;
import jonerys.test.springcrud.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {
    private final String login;
    private final String password;
    private final Role role;
    private final List<SimpleGrantedAuthority> authorities;

    public SecurityUser(String login, String password, Role role, List<SimpleGrantedAuthority> authorities){
        this.login = login;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Role getRole(){
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static UserDetails getUser(User user){
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getRole().getAuthorities());
    }
}

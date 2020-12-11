package jonerys.test.springcrud.config;

import jonerys.test.springcrud.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("CustomUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/goodslist").hasAuthority(Permission.ADMIN_PERMISSIONS.getPermission())
                .antMatchers("/getgoodslist").permitAll()
                .antMatchers("/addgood").hasAuthority(Permission.ADMIN_PERMISSIONS.getPermission())
                .antMatchers("/updategood").hasAuthority(Permission.ADMIN_PERMISSIONS.getPermission())
                .antMatchers("/deletegood").hasAuthority(Permission.ADMIN_PERMISSIONS.getPermission())
                .antMatchers("/warehouseslist").hasAuthority(Permission.ADMIN_PERMISSIONS.getPermission())
                .antMatchers("/getwarehouseslist").hasAuthority(Permission.ADMIN_PERMISSIONS.getPermission())
                .antMatchers("/addwarehouse").hasAuthority(Permission.ADMIN_PERMISSIONS.getPermission())
                .antMatchers("/updatewarehouse").hasAuthority(Permission.ADMIN_PERMISSIONS.getPermission())
                .antMatchers("/deletewarehouse").hasAuthority(Permission.ADMIN_PERMISSIONS.getPermission())
                .antMatchers("/goodsinwarehouse/").hasAuthority(Permission.USER_PERMISSIONS.getPermission())
                .anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/welcome");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(12); }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}

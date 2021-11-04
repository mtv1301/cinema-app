package cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/register", "/movie-sessions/available").permitAll()
                .mvcMatchers(HttpMethod.GET, "/cinema-halls", "/movies").permitAll()
                .mvcMatchers(HttpMethod.POST,"/movies", "/cinema-halls", "/movie-sessions")
                .hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/movie-sessions/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/movie-sessions/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/users/by-email",
                        "/movie-sessions/available",
                        "/movie-sessions/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/orders",
                        "/shopping-carts/by-user",
                        "/movie-sessions/available",
                        "/movie-sessions/{id}").hasRole("USER")
                .mvcMatchers(HttpMethod.PUT,"/shopping-carts/movie-sessions").hasRole("USER")
                .mvcMatchers(HttpMethod.POST, "/orders/complete").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}

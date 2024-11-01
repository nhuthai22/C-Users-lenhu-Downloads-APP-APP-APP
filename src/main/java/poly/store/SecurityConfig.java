package poly.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import poly.store.entity.Account;
import poly.store.service.AccountService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AccountService accountService;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();

        http.authorizeRequests()
            .requestMatchers("/home/index", "/security/login/**").permitAll()
            .anyRequest().authenticated();

        http.formLogin()
            .loginPage("/security/login/form")
            .loginProcessingUrl("/security/login")
            .defaultSuccessUrl("/login-success", false)
            .failureUrl("/security/login/error");

        http.rememberMe().tokenValiditySeconds(86400);

        http.logout()
            .logoutUrl("/security/logoff")
            .logoutSuccessUrl("/security/logoff/success");

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Account user = accountService.findById(username);
            if (user != null) {
                String password = getPasswordEncoder().encode(user.getPassword());
                String role = user.getRole() ? "ROLE_ADMIN" : "ROLE_USER"; // Assuming role is a boolean where true = ADMIN
                return User.withUsername(username).password(password).authorities(role).build();
            }
            throw new UsernameNotFoundException(username + " not found!");
        };
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(HttpMethod.OPTIONS, "/**");
    }
}

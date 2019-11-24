package pl.sda.tasklist.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] MATCHERS =
            {"/user/**", "/new-category", "/delete-category", "/new-task", "/delete-task", "/log-out"};
    private static final String[] ADMIN_MATCHERS = {"/admin/**"};
    private static final String[] GLOBAL_MATCHERS = {"/sign-up", "/", "/sign-in", "/home"};
    private final UserDetailsService userDetailsService;
    private final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(MATCHERS).hasAnyAuthority("ROLE_USER")
                .antMatchers(ADMIN_MATCHERS).hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(GLOBAL_MATCHERS).permitAll()
                .and()
                .formLogin().loginPage("/sign-in").defaultSuccessUrl("/")
                .and()
                .rememberMe().key("change-me")
                .and()
                .logout().logoutSuccessUrl("/").deleteCookies("JSESSIONID");
//        For H2 console
        http.csrf()
                .ignoringAntMatchers("/h2/**");
        http.headers()
                .frameOptions()
                .sameOrigin();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT u.user_name, u.password, 1 "
                                + "FROM user_entity u "
                                + "WHERE u.user_name = ?"
                )
                .authoritiesByUsernameQuery(
                        "SELECT u.user_name, r.name, 1 "
                                + "FROM user_entity u "
                                + "INNER JOIN user_entity_roles ur ON ur.user_entity_id = u.id "
                                + "INNER JOIN user_role_entity r ON r.id = ur.roles_id "
                                + "WHERE u.user_name = ?"
                )
                .passwordEncoder(passwordEncoder());
    }
}

package spring.security.fullstack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.security.fullstack.jwt.JwtAuthorizationFilter;
import spring.security.fullstack.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    //define type of the encryption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //define authorize pages or unathorize pages
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //cross-origin resource  because we works with different ports
        http.cors().and()
                .authorizeRequests()
                //These are public pages
                .antMatchers("/resources/**", "/error", "/api/user/**", "/api/products/**", "/api/category/**").permitAll()
                //These can be rechable for just have admin role
                .antMatchers("/admin/**").hasRole("ADMIN")
                //all remaning paths should need authentication
                .anyRequest().fullyAuthenticated()
                .and()
                //logout will log the user out by invalidate session
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout", "POST"))
                .and()
                //login form and path
                .formLogin().loginPage("/api/user/login")
                .and()
                //enable basic authetication. Http header: basis username:password
                .httpBasic()
                .and()
                //cross side request forgery
                .csrf().disable();
//for testing
//        http.cors();
//        http.csrf().disable();
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll();
        http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenProvider));
    }

    //define user detail service
    //how the user details are keept in the application (database, elda, or in memory)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    //Cross origin resource sharing
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }
}

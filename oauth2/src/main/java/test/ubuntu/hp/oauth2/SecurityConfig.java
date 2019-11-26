package test.ubuntu.hp.oauth2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// to configure the users of the server resources
	@Autowired
	private DataSource dataSource;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
		auth
        .inMemoryAuthentication()
        .withUser("user1"). password("{noop}password1").roles("USER")
        .and()
        .withUser("user2").password("{noop}password2").roles("USER", "ADMIN");
    }
 
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() 
      throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll();
    }
}

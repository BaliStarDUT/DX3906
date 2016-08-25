package lol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @date 2016年7月14日 上午11:29:08
 * @author James Yang
 * @version 1.0
 * @since
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.and()
		.httpBasic();
//        http
//            .authorizeRequests()
//	            .antMatchers("/resources/**", "/signup", "/about").permitAll()  
//				.antMatchers("/admin/**").hasRole("ADMIN")       
//				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')") 
//				.anyRequest().authenticated() 
//			.and()
//		.formLogin()
//			.permitAll(); 
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("user1").password("password").roles("ADMIN").and()
                .withUser("user2").password("password").roles("DBA");
    }
    
}

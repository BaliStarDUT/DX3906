package top.hunaner.lol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers("/hostinfo/**").permitAll();
 		http
// 			.authorizeRequests().antMatchers("/**").hasRole("USER")
// 			.and()
// 			.httpBasic();
// 		"/weather/**","/position/**",
            .authorizeRequests()
            	.antMatchers("/resources/view/**").hasRole("ADMIN")   
	            .antMatchers("/resources/**","/signup", 
	            		"/about","/h2-console/**").permitAll()  
				.antMatchers("/admin/**").hasRole("ADMIN")       
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')") 
				.anyRequest().authenticated() 
			.and()
		.formLogin().loginPage("/resources/view/login.html")
			.loginProcessingUrl("/login.do")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/",true)
			.permitAll();
        http.csrf().disable();
        http.logout()
        		.logoutUrl("/logout")
        		.logoutSuccessUrl("/resources/view/login.html")
        		.invalidateHttpSession(true);
        		
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    	.jdbcAuthentication();
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("admin").roles("ADMIN").and()
                .withUser("user2").password("password").roles("DBA");
    }
    
}

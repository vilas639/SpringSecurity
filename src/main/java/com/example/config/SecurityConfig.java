package com.example.config;



import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("!prod")
public class SecurityConfig {
	
	
	
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//            .authorizeHttpRequests(request -> request
//                .requestMatchers("/api/*").permitAll()  // Allow access to /api/hello   
//                .anyRequest().authenticated()               // Restrict access to other endpoints
//            )
        
//        .authorizeHttpRequests(request -> request.anyRequest().denyAll())  //deny all
//          .authorizeRequests(request -> request.anyRequest().permitAll())  //permit all 
       
           
        .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())  //only http
        .sessionManagement(scm -> scm.invalidSessionUrl("/invaliuser").maximumSessions(3).maxSessionsPreventsLogin(true))  //prevent the concuurent login 
        .csrf(csrf -> csrf.disable()) // Disable CSRF protection (if necessary)
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/public", "/contact", "/register/*", "/*").permitAll() // Allow public access to specific endpoints
            .requestMatchers("/api", "/cards").hasRole("USER") // Restrict access to /api and /cards to users with ROLE_USER
            .requestMatchers("/admin").hasRole("ADMIN") // Restrict access to /admin to users with ROLE_ADMIN
            .anyRequest().authenticated() // All other requests require authentication
        )
        .formLogin();
           

   
        
        return http.build();
    }
	
	
//	@Bean
//	public UserDetailsService userdetail()
//	{
//		
//		UserDetails u1=User.withUsername("vilas").password(passwordEncoder().encode("1234")).authorities("ADMIN").build();
//		UserDetails u2=User.withUsername("aarav").password(passwordEncoder().encode("456")).authorities("USER").build();
//		UserDetails u3=User.withUsername("roshani").password(passwordEncoder().encode("789")).authorities("MANAGER").build();
//	
//		
//		return new InMemoryUserDetailsManager(u1,u2,u3);
//	}
	
//	@Bean
//	public UserDetailsService userdetail(DataSource datasource)
//	{
//		
//		
//	
//		
//		return new JdbcUserDetailsManager(datasource);
//	}
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
//	public static void main(String[] args) {
//		
//		SecurityConfig s= new  SecurityConfig();
//		String p=s.passwordEncoder().encode("1234");
//		System.out.println(p);
//	}

}

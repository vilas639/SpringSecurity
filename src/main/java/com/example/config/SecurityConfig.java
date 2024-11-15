package com.example.config;



import java.util.Arrays;
import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@Profile("!prod")
public class SecurityConfig {
	
	
	
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		  CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http
//            .authorizeHttpRequests(request -> request
//                .requestMatchers("/api/*").permitAll()  // Allow access to /api/hello   
//                .anyRequest().authenticated()               // Restrict access to other endpoints
//            )
        
//        .authorizeHttpRequests(request -> request.anyRequest().denyAll())  //deny all
//          .authorizeRequests(request -> request.anyRequest().permitAll())  //permit all 
       
           
        .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())  //only http
       // .sessionManagement(scm -> scm.invalidSessionUrl("/invaliuser").maximumSessions(3).maxSessionsPreventsLogin(true))  //prevent the concuurent login 
        .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   //for jwt

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
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
//        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                        CorsConfiguration config = new CorsConfiguration();
//                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//                        config.setAllowedMethods(Collections.singletonList("*"));
//                        config.setAllowCredentials(true);
//                        config.setAllowedHeaders(Collections.singletonList("*"));
//                        config.setExposedHeaders(Arrays.asList("Authorization"));
//                        config.setMaxAge(3600L);
//                        return config;
//                    }
//                }))
//                .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
//                        .ignoringRequestMatchers( "/contact","/register", "/apiLogin")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
//                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
//                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // Only HTTP
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/myAccount").hasRole("USER")
//                        .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers("/myLoans").hasRole("USER")
//                        .requestMatchers("/myCards").hasRole("USER")
//                        .requestMatchers("/user").authenticated()
//                        .requestMatchers("/notices", "/contact", "/error", "/register", "/invalidSession", "/apiLogin").permitAll());
//        http.formLogin(withDefaults());
//        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
//        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
//        return http.build();
//    }
	
	
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
	
//	  @Bean
//	    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
//	            PasswordEncoder passwordEncoder) {
//	        EazyBankUsernamePwdAuthenticationProvider authenticationProvider =
//	                new EazyBankUsernamePwdAuthenticationProvider(userDetailsService, passwordEncoder);
//	        ProviderManager providerManager = new ProviderManager(authenticationProvider);
//	        providerManager.setEraseCredentialsAfterAuthentication(false);
//	        return  providerManager;
//	    }
	
	
//	public static void main(String[] args) {
//		
//		SecurityConfig s= new  SecurityConfig();
//		String p=s.passwordEncoder().encode("1234");
//		System.out.println(p);
//	}

}

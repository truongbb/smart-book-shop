package vn.ntqsolution.smart_shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import vn.ntqsolution.smart_shop.security.JwtAuthenticationEntryPoint;
import vn.ntqsolution.smart_shop.security.JwtRequestFilter;
import vn.ntqsolution.smart_shop.utils.Constants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Qualifier("userDetailsServiceImpl")
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  //  https://www.ninjadevcorner.com/2018/09/stateless-authentication-jwt-secure-spring-boot-rest-api.html
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      // we don't need CSRF because we store token in header
      .csrf().disable()
      .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
      // don't create session
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeRequests()
      .antMatchers("/swagger-resources/**", "/v2/api-docs").permitAll()
      .antMatchers(HttpMethod.GET, "/", "**/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
      .antMatchers(HttpMethod.OPTIONS).permitAll()
      .antMatchers("/**/register", "/**/authenticate", "/**/active-account/**").permitAll()
//      .antMatchers("/").hasRole(Constants.Role.CUSTOMER)
//      .antMatchers("/smart_shop/admin").hasRole(Constants.Role.MANAGER)
      .anyRequest().authenticated();

    // Custom JWT based security filter
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    // disable page caching
    http.headers().cacheControl().disable();
  }
}

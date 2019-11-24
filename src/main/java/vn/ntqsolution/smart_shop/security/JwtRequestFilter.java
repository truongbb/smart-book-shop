package vn.ntqsolution.smart_shop.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.ntqsolution.smart_shop.utils.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

  @Qualifier("userDetailsServiceImpl")
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
    throws ServletException, IOException {

    httpServletResponse.addHeader("Access-Control-Allow-Headers",
      "Access-Control-Allow-Origin, Origin, Accept, X-Requested-With, Authorization, refreshauthorization, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Access-Control-Allow-Credentials");
    if (httpServletResponse.getHeader("Access-Control-Allow-Origin") == null)
      httpServletResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:9870");
    if (httpServletResponse.getHeader("Access-Control-Allow-Credentials") == null)
      httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
    if (httpServletResponse.getHeader("Access-Control-Allow-Methods") == null)
      httpServletResponse.addHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");

    String requestHeader = httpServletRequest.getHeader(Constants.Security.HEADER_AUTHORIZATION);

    String jwtToken = null;
    String username = null;
    if (requestHeader != null && requestHeader.startsWith(Constants.Security.HEADER_PREFIX)) {
      jwtToken = requestHeader.substring(7);
      username = jwtTokenUtil.getUsernameFromToken(jwtToken);
    } else {
      log.error("JWT token ERROR: NULL or doesn't start with 'Bearer' string");
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(authenToken);
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}

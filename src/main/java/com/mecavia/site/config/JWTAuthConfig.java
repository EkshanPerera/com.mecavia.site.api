package com.mecavia.site.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mecavia.site.dto.AuthenticationResponseDto;
import com.mecavia.site.service.JWTService;
import com.mecavia.site.util.VarList;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthConfig extends OncePerRequestFilter {
	
	@Autowired
	private final JWTService jwtService;
	
	@Autowired
	private final UserDetailsService userDetailsService;
	
	@Autowired
	private final AuthenticationResponseDto authenticationResponseDto;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if(authHeader==null ||! authHeader.startsWith("Bearer ") ) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		try {
			userEmail = jwtService.exactUsername(jwt);
			if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
				if(jwtService.isTokenValid(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		} catch (Exception e) {
//			NOT WORKING PROPERLY
			authenticationResponseDto.setCode(VarList.RSP_TOKEN_EXPIRED);
			authenticationResponseDto.setMassage(e.getMessage());
			authenticationResponseDto.setToken(null);
			response.getWriter().println(authenticationResponseDto);
			System.out.println(e.getMessage());
		}
		filterChain.doFilter(request, response);
	}

}

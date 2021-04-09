package com.cognizant.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private ManagerDetailsService managerDetailsService;
	@Autowired
	Environment env;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeadder = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		log.debug(authHeadder);
		if (authHeadder != null && authHeadder.startsWith("Bearer ")) {
			jwt = authHeadder.substring(7);
			try {
				username = jwtUtil.extractUsername(jwt);
			} catch (IllegalArgumentException e) {
				logger.error(env.getProperty("illegel.arg"));
			} catch (ExpiredJwtException e) {
				logger.error(env.getProperty("token.expired"));
			} catch (SignatureException e) {
				logger.error(env.getProperty("auth.failed"));
			}
		} else {
			logger.warn(env.getProperty("token.not.found"));
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.managerDetailsService.loadUserByUsername(username);
			if (jwtUtil.validateToken(jwt)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
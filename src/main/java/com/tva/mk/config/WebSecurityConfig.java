package com.tva.mk.config;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.tva.mk.common.Const;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	// region -- Fields --

	@Resource(name = "userService")
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtEntryPoint unauthorizedHandler;

	// end

	// region -- Methods --

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Bean
	public JwtFilter authenticationTokenFilterBean() throws Exception {
		return new JwtFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Get environment variable
		String mod = System.getenv(Const.Mode.DEV);

		if (mod != null && "Y".equals(mod)) {
			http.cors().and().csrf().disable();
		} else {
			http.csrf().disable();
			http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
		}

		http.authorizeRequests()
				.antMatchers("/", "/user/sign-in", "/user/sign-up", "/user/refresh-token", "/user/verify-mail",
						"/user/forgot-password", "/user/verify-active-code", "/common/search", "/test/reset-auth",
						"/test/active-code", "/test/token", "/setting/exrate")
				.permitAll().antMatchers("/user/reset-password").hasAuthority(Const.Authentication.ROLE_ADMIN)
				.anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Get environment variable
		String mod = System.getenv(Const.Mode.DEV);

		if (mod != null && "Y".equals(mod)) {
			web.ignoring().antMatchers("/*.html", "/*.css", "/*.js", "/*.png", "/*.ico", "/*.jpg", "/*.jpeg", "/*.gif",
					"/*.bmp", "/assets/**", "/*.ttf", "/*.woff", "/*.woff2", "/*.eot", "/*.svg", "/v2/api-docs",
					"/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html",
					"/webjars/**");
		} else {
			web.ignoring().antMatchers("/*.html", "/*.css", "/*.js", "/*.png", "/*.ico", "/*.jpg", "/*.jpeg", "/*.gif",
					"/*.bmp", "/assets/**", "/*.ttf", "/*.woff", "/*.woff2", "/*.eot", "/*.svg", "/v2/api-docs",
					"/configuration/ui", "/configuration/security", "/webjars/**");
		}
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		// Get environment variable
		String mod = System.getenv("DEV_MODE");

		if (mod != null && "Y".equals(mod)) {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedOrigins(Arrays.asList("*"));
			config.setAllowedMethods(Arrays.asList("*"));
			config.setAllowedHeaders(Arrays.asList("*"));
			source.registerCorsConfiguration("/**", config);
		}

		return source;
	}

	// end
}
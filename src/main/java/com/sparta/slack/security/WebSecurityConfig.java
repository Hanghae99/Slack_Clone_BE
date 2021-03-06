package com.sparta.slack.security;

import com.sparta.slack.security.filter.FormLoginFilter;
import com.sparta.slack.security.filter.JwtAuthFilter;
import com.sparta.slack.security.jwt.HeaderTokenExtractor;
import com.sparta.slack.security.provider.FormLoginAuthProvider;
import com.sparta.slack.security.provider.JWTAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTAuthProvider jwtAuthProvider;
    private final HeaderTokenExtractor headerTokenExtractor;
    private final UserDetailsServiceImpl userDetailsServiceImpl;


    public WebSecurityConfig(JWTAuthProvider jwtAuthProvider,
                             HeaderTokenExtractor headerTokenExtractor,
                             UserDetailsServiceImpl userDetailsServiceImpl
    ) {
        this.jwtAuthProvider = jwtAuthProvider;
        this.headerTokenExtractor = headerTokenExtractor;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean   // ???????????? ?????????
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .authenticationProvider(formLoginAuthProvider())
                .authenticationProvider(jwtAuthProvider)
                .userDetailsService(userDetailsServiceImpl);
    }

    @Override
    public void configure(WebSecurity web) {

        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().httpStrictTransportSecurity()
                .maxAgeInSeconds(0)
                .includeSubDomains(true);
        http
                .cors()
                .configurationSource(corsConfigurationSource());
        http
                .httpBasic().disable()
                .csrf().disable()
                .headers() .frameOptions().sameOrigin().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        http
                .authorizeRequests()
                .antMatchers("/user/signup", "/user/login","/user/loginView").permitAll()
                // ?????? ???????????? '??????'
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .permitAll();
    }

    //cors
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        //configuration.addAllowedOrigin("http://54.180.90.59:3000");
        //configuration.addAllowedOrigin("http://54.180.90.59:8080");
        configuration.addAllowedOrigin("http://hanghae99-sout.s3-website.ap-northeast-2.amazonaws.com"); // ?????? ???
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("Authorization");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter formLoginFilter = new FormLoginFilter(authenticationManager());
        formLoginFilter.setFilterProcessesUrl("/user/login");
        formLoginFilter.setAuthenticationSuccessHandler(formLoginSuccessHandler());
        formLoginFilter.afterPropertiesSet();
        return formLoginFilter;
    }

    @Bean
    public FormLoginSuccessHandler formLoginSuccessHandler() {
        return new FormLoginSuccessHandler();
    }

    @Bean
    public FormLoginAuthProvider formLoginAuthProvider() {

        return new FormLoginAuthProvider((BCryptPasswordEncoder) encodePassword());
    }

    private JwtAuthFilter jwtFilter() throws Exception {
        List<String> skipPathList = new ArrayList<>();

        // Static ?????? ?????? ??????
        skipPathList.add("GET,/images/**");
        skipPathList.add("GET,/css/**");
        skipPathList.add("GET,/js/**");
//        skipPathList.add("POST,/**");
//        skipPathList.add("GET,/**");



        skipPathList.add("GET,/webjars/**");
        // h2-console ??????
        skipPathList.add("GET,/h2-console/**");
        skipPathList.add("POST,/h2-console/**");

        // ?????? ?????? API ??????
        skipPathList.add("POST,/user/signup");

        //??????????????? API ??????
        skipPathList.add("POST,/user/login");
        skipPathList.add("GET,/user/loginView");

        skipPathList.add("GET,/api/post/**");
        skipPathList.add("GET,/favicon.ico");

        skipPathList.add("GET,/gs-guide-websocket/**/**");
        skipPathList.add("GET,/gs-guide-websocket/**");
        skipPathList.add("GET,/gs-guide-websocket");

        skipPathList.add("GET,/app/**");
        skipPathList.add("GET,/app/hello");

        skipPathList.add("GET,/chatRoom/get");
        skipPathList.add("POST,/chatRoom/create");


        FilterSkipMatcher matcher = new FilterSkipMatcher(
                skipPathList,
                "/**"
        );

        JwtAuthFilter filter = new JwtAuthFilter(
                matcher,
                headerTokenExtractor
        );
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

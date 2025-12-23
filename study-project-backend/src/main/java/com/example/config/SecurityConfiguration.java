package com.example.config;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.RestBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(conf -> {
                    conf.anyRequest().authenticated();// 任意请求都需要认证
                })
                .formLogin(conf -> {
                    conf.loginProcessingUrl("/api/auth/login");// 设置登录请求的处理地址
                    conf.successHandler(this::onAuthenticationSuccess);//登录成功处理
                    conf.failureHandler(this::onAuthenticationFailure);//登录失败处理
                    conf.permitAll();

                }
                )

                .logout(logout->{
                        logout.logoutUrl("/api/auth/logout");//推出登录地址
                        logout.logoutSuccessUrl("/");//设置退出登录成功后跳转的地址为首页"/"
                        logout.permitAll();
                })
                .exceptionHandling(conf -> {
                    conf
                            .authenticationEntryPoint(this::onAuthenticationFailure);
                })
                .csrf(AbstractHttpConfigurer::disable)// 关闭 CSRF 保护
                .build();



    }
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.success("登录成功")));// 登录成功后返回"登录成功"
}
   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.fail(401,exception.getMessage())));// 登录失败返回"登录失败"
}
}

package com.example.config;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.RestBean;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Resource
    AuthorizeService authorizeService;
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
                        logout.logoutSuccessHandler(this::onAuthenticationSuccess);
                        logout.logoutSuccessUrl("/");//设置退出登录成功后跳转的地址为首页"/"
                        logout.permitAll();
                })
                .exceptionHandling(conf -> {
                    conf
                            .authenticationEntryPoint(this::onAuthenticationFailure);
                })
                .cors(conf -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    //添加前端站点地址，这样就可以告诉浏览器信任了
                    cors.addAllowedOrigin("http://localhost:5173");
                    //虽然也可以像这样允许所有 cors.addAllowedOriginPattern("*");
                    //但是这样并不安全，我们应该只许可给我们信任的站点
                    cors.setAllowCredentials(true);  //允许跨域请求中携带Cookie
                    cors.addAllowedHeader("*");   //其他的也可以配置，为了方便这里就 * 了
                    cors.addAllowedMethod("*");
                    cors.addExposedHeader("*");
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", cors);  //直接针对于所有地址生效
                    conf.configurationSource(source);
                })
                .csrf(AbstractHttpConfigurer::disable)// 关闭 CSRF 保护
                .userDetailsService(authorizeService)//自定义用户登录
                .build();



    }


    @Bean//自定义认证管理器
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authManagerBuilder.userDetailsService(authorizeService);

        return authManagerBuilder.build();  // 直接调用 build()，去掉 and()
    }
  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  } 
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setCharacterEncoding("UTF-8");
        if (request.getRequestURI().endsWith("/login"))
        response.getWriter().write(JSONObject.toJSONString(RestBean.success("登录成功")));// 登录成功后返回"登录成功"
        else if (request.getRequestURI().endsWith("/logout"))
            response.getWriter().write(JSONObject.toJSONString(RestBean.success("退出登录成功")));
}
   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.fail(401,exception.getMessage())));// 登录失败返回"登录失败"
}
}

package com.example;

import com.example.entity.RestBean;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class StudyProjectBackendApplicationTests {
 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	@Test
	void contextLoads() {
		System.out.println("加密后的密码为:"+encoder.encode("123456"));
	}

}

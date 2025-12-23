package com.example;

import com.example.entity.RestBean;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class StudyProjectBackendApplicationTests {
 @Resource
 RestBean restBean;
	@Test
	void contextLoads() {

	}

}

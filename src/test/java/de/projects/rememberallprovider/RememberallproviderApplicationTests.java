package de.projects.rememberallprovider;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RememberallproviderApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private RemarkRepository repository;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void persistRemark() {
		assertThat(restTemplate.postForEntity("http://localhost:" + port + "/remarks",
				new Remark("test", "positive"), ResponseEntity.class).getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void getAllRemarks() {
		restTemplate.postForEntity("http://localhost:" + port + "/remarks",
				new Remark("test", "positive"), ResponseEntity.class);
		restTemplate.postForEntity("http://localhost:" + port + "/remarks",
				new Remark("test2", "negative"), ResponseEntity.class);
		assertThat(restTemplate.getForEntity("http://localhost:" + port + "/allRemarks",
				RemarksList.class).getBody()).isEqualTo(new RemarksList(new Remark("test", "positive"), new Remark("test2", "negative")));
	}

	@AfterEach
	void cleanTest() {
		repository.deleteAll();
	}
}

package de.projects.rememberallprovider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "localTest")
class RememberallproviderApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private RemarkRepository repository;
	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	void emptyDB() {
		repository.deleteAll();
	}

	@Test
	void persistRemark() {
		assertThat(postRemark(remark("test", "positive")).getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void getAllRemarks() {
		postRemark(remark("test", "positive"));
		postRemark(remark("test2", "negative"));


		assertThat(getRemarksList().getRemarks()).hasSize(2);
		assertThat(getRemarksList()).isEqualTo(new RemarksList(remark("test", "positive"), remark("test2", "negative")));
	}

	private Remark remark(String description, String quality) {
		return new Remark(description, quality);
	}

	private ResponseEntity<ResponseEntity> postRemark(Remark... remark) {
		return restTemplate.postForEntity("http://localhost:" + port + "/remarks", remark, ResponseEntity.class);
	}

	private RemarksList getRemarksList() {
		return restTemplate.getForEntity("http://localhost:" + port + "/allRemarks",
				RemarksList.class).getBody();
	}
}

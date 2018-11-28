package de.projects.rememberallprovider;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
	private final RemarkRepository repository;

	public Controller(RemarkRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/remarks")
	public ResponseEntity<String> submitRemark(@RequestBody Remark remark) {
		repository.save(new RemarkEntity(remark.getDescription(), remark.getQuality()));
		return ResponseEntity.ok().build();
	}

	@GetMapping("/allRemarks")
	public ResponseEntity<RemarksList> getAllRemarks() {
		return ResponseEntity.of(Optional.of(new RemarksList(repository.findAll().stream()
				.map(entity -> new Remark(entity.getDescription(), entity.getQuality())).collect(Collectors.toList()))));
	}
}

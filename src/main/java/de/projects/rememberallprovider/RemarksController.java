package de.projects.rememberallprovider;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RemarksController {
	private final RemarkRepository repository;

	public RemarksController(RemarkRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/remarks")
	public ResponseEntity<String> submitRemark(@RequestBody List<Remark> remarks) {
		repository.saveAll(remarks.stream().map(remark -> new RemarkEntity(remark.getDescription(), remark.getQuality())).collect(Collectors.toList()));
		return ResponseEntity.ok().build();
	}

	@GetMapping("/allRemarks")
	public ResponseEntity<RemarksList> getAllRemarks() {
		return ResponseEntity.of(Optional.of(new RemarksList(repository.findAll().stream()
				.map(entity -> new Remark(entity.getDescription(), entity.getQuality())).collect(Collectors.toList()))));
	}
}

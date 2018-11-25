package de.projects.rememberallprovider;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "remark")
@NoArgsConstructor
class RemarkEntity {
	@Id
	@Column(name = "idremark")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String quality;

	RemarkEntity(String description, String quality) {
		this.description = description;
		this.quality = quality;
	}
}

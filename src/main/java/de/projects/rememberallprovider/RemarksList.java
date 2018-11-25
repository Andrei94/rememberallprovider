package de.projects.rememberallprovider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
class RemarksList {
	private List<Remark> remarks;

	RemarksList(Remark... remarks) {
		this.remarks = Arrays.asList(remarks);
	}
}

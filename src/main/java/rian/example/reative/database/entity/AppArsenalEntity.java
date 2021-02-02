package rian.example.reative.database.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Arsenal")
@NamedQuery(name = "Arsenal.findAll", query = "SELECT arsenal FROM AppArsenalEntity arsenal ORDER BY arsenal.otherInfo")
public class AppArsenalEntity{

	@Id
	@SequenceGenerator(name = "apparsenalSequence", sequenceName = "apparsenal_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "apparsenalSequence")
	private Long id;

	@Column(length = 40, unique = true)
	private String otherInfo;

	@Builder.Default
	@Column(name = "created_at", nullable = false, updatable = false)
	LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "modified_at", nullable = false)
	LocalDateTime modifiedAt;

}

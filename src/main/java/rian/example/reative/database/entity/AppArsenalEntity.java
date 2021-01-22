package rian.example.reative.database.entity;

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
public class AppArsenalEntity {

	@Id
	@SequenceGenerator(name = "productSequence", sequenceName = "product_id_seq", allocationSize = 1, initialValue = 10)
	@GeneratedValue(generator = "productSequence")
	private Long id;

	@Column(length = 40, unique = true)
	private String otherInfo;

}

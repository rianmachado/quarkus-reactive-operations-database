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
@Table(name = "product")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM ProductEntity p ORDER BY p.description")
public class ProductEntity {

	@Id
	@SequenceGenerator(name = "productSequence", sequenceName = "product_id_seq", allocationSize = 1, initialValue = 10)
	@GeneratedValue(generator = "productSequence")
	public Long id;

	@Column(length = 40, unique = true)
	public String description;

}

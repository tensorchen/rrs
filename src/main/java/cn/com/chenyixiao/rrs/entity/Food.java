package cn.com.chenyixiao.rrs.entity;
// Generated 2016-4-6 18:18:32 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Food generated by hbm2java
 */
@Entity
@Table(name = "food", catalog = "rrs")
public class Food implements java.io.Serializable {

	private long id;
	private String name;

	public Food() {
	}

	public Food(long id) {
		this.id = id;
	}

	public Food(long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

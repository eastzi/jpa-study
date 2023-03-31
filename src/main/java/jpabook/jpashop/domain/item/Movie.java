package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;	

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item {

	private String directer;
	private String actor;
}

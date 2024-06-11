package com.rushi.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter 
@Getter
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	private String name;
	
	private String description; 
	
	private Double price; 
	
	private Integer stock;

	private String productPic;
	
	private Integer discount;
	
	private Double pricebeforeDiscount;
	
	@CreationTimestamp
	@Column(name="create_dt",updatable=false)
	private LocalDate createDate;
	
	@UpdateTimestamp()
	@Column(name="update_dt",insertable=false)
	private LocalDate updateDate;
	
	@ManyToOne
	@JoinColumn(name="Category_id",nullable=false)
	private Category category;

}

package com.nnk.springboot.domain;

import java.sql.Timestamp;

//retirer au début à valider
//import org.springframework.beans.factory.annotation.Required;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "bid_list")
public class BidList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BidListId")
	private int BidListId;
	@Column(name = "account")
	private String account;
	@Column(name = "type")
	private String type;
	@Column(name = "bidQuantity")
	private Double bidQuantity;
	@Column(name = "askQuantity")
	private Double askQuantity;
	@Column(name = "bid")
	private Double bid;
	@Column(name = "ask")
	private Double ask;
	@Column(name = "benchmark")
	private String benchmark;
	@Column(name = "bidListDate")
	private Timestamp bidListDate;
	@Column(name = "commentary")
	private String commentary;
	@Column(name = "security")
	private String security;
	@Column(name = "status")
	private String status;
	@Column(name = "trader")
	private String trader;
	@Column(name = "book")
	private String book;
	@Column(name = "creationName")
	private String creationName;
	@Column(name = "creationName")
	private Timestamp creationDate;
	@Column(name = "revisionName")
	private String revisionName;
	@Column(name = "revisionDate")
	private Timestamp revisionDate;
	@Column(name = "dealName")
	private String dealName;
	@Column(name = "dealType")
	private String dealType;
	@Column(name = "sourceListId")
	private String sourceListId;
	@Column(name = "side")
	private String side;

	public BidList(String accountTest, String typeTest, double v) {
	}

	// TODO: Map columns in data table BIDLIST with corresponding java fields
}

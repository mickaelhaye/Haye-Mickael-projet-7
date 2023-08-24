package com.nnk.springboot.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicUpdate;

//retirer au début à valider
//import org.springframework.beans.factory.annotation.Required;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * this class is the entity Bidlist.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "bid_list")
public class BidList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid_list_id")
	private int bidListId;
	@Column(name = "account")
	private String account;
	@Column(name = "type")
	private String type;
	@Column(name = "bid_quantity")
	private Double bidQuantity;
	@Column(name = "ask_quantity")
	private Double askQuantity;
	@Column(name = "bid")
	private Double bid;
	@Column(name = "ask")
	private Double ask;
	@Column(name = "benchmark")
	private String benchmark;
	@Column(name = "bid_list_date")
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
	@Column(name = "creation_name")
	private String creationName;
	@Column(name = "creation_date")
	private Timestamp creationDate;
	@Column(name = "revision_name")
	private String revisionName;
	@Column(name = "revision_date")
	private Timestamp revisionDate;
	@Column(name = "deal_name")
	private String dealName;
	@Column(name = "deal_type")
	private String dealType;
	@Column(name = "source_list_id")
	private String sourceListId;
	@Column(name = "side")
	private String side;

	/**
	 * constructor for test
	 * 
	 * @param string
	 * @param string2
	 * @param d
	 */
	public BidList(String string, String string2, double d) {
	}
}

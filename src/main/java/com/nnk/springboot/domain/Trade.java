package com.nnk.springboot.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicUpdate;

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
 * this class is the entity Trade.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate

@Table(name = "trade")
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trade_id")
	private int id;

	@Column(name = "account")
	private String account;

	@Column(name = "type")
	private String type;

	@Column(name = "buy_quantity")
	private Double buyQuantity;

	@Column(name = "sell_quantity")
	private Double sellQuantity;

	@Column(name = "buy_price")
	private Double buyPrice;

	@Column(name = "sell_price")
	private Double sellPrice;

	@Column(name = "benchmark")
	private String benchmark;

	@Column(name = "trade_date")
	private Timestamp tradeDate;

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

}

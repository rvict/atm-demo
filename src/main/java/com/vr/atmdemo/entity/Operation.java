package com.vr.atmdemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The Class Operation.
 */
@Entity
public class Operation {

	/** The id. */
	@Id
	@GeneratedValue
	private long id;
	
	/** The code. */
	private OperationType code;
	
	/** The amount. */
	private double amount;
	
	/** The timestamp. */
	private long timestamp;

	/** The card. */
	@ManyToOne
	private Card card;

	/**
	 * Instantiates a new operation.
	 */
	public Operation() {
	}

	/**
	 * Instantiates a new operation.
	 *
	 * @param code the code
	 */
	public Operation(OperationType code) {
		this.code = code;
		this.timestamp = System.currentTimeMillis();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public OperationType getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(OperationType code) {
		this.code = code;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the card.
	 *
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Sets the card.
	 *
	 * @param card the new card
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}

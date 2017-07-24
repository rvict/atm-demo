/*
 * 
 */
package com.vr.atmdemo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.CreditCardNumber;

/**
 * The Class Card.
 */
@Entity
public class Card {

	/** The id. */
	@Id
	@GeneratedValue
	private long id;

	/** The card number. */
	@CreditCardNumber
	private String number;
	
	/** The card pin. */
	private String pin;
	
	/** The card balance. */
	private double balance;
	
	/** The card pin fail count. */
	private int pinFailCount;
	
	/** The card status. */
	private boolean isActive;
	
	/** The card operations. */
	@OneToMany(mappedBy = "card", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Operation> operations;
	
	/**
	 * Instantiates a new card.
	 */
	public Card() {
	}

	/**
	 * Instantiates a new card.
	 *
	 * @param number the card number
	 * @param pin the card PIN code
	 * @param balance the card balance
	 * @param isActive the card status
	 */
	public Card(String number, String pin, double balance, boolean isActive) {
		this.number = number;
		this.pin = pin;
		this.balance = balance;
		this.isActive = isActive;
	}

	/**
	 * Gets the card id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the card id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the card number.
	 *
	 * @return the card number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the card number.
	 *
	 * @param number the card new number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Gets the card PIN code.
	 *
	 * @return the card PIN code
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * Sets the card PIN code.
	 *
	 * @param pin the new card PIN code
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * Gets the card balance.
	 *
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Sets the card balance.
	 *
	 * @param balance the new card balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * Gets the number of wrong inputs.
	 *
	 * @return the pin fail count
	 */
	public int getPinFailCount() {
		return pinFailCount;
	}

	/**
	 * Sets the number of wrong inputs.
	 *
	 * @param pinFailCount the new pin fail count
	 */
	public void setPinFailCount(int pinFailCount) {
		this.pinFailCount = pinFailCount;
	}

	/**
	 * Checks if card is active.
	 *
	 * @return true, if card is active
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Sets the active/blocked status.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * Gets the operations.
	 *
	 * @return the operations
	 */
	public List<Operation> getOperations() {
		return operations;
	}

	/**
	 * Sets the operations.
	 *
	 * @param operations the new operations
	 */
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
}

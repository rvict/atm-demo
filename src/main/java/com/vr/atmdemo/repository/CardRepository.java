package com.vr.atmdemo.repository;

import com.vr.atmdemo.entity.Card;
import org.springframework.data.repository.CrudRepository;

/**
 * The Interface CardRepository.
 */
public interface CardRepository extends CrudRepository<Card, Long> {

	/**
	 * Find card by card number and card status.
	 *
	 * @param number the card number
	 * @param isActive the card status
	 * @return the card
	 */
	Card findOneByNumberAndIsActive(String number, boolean isActive);
	
	/**
	 * Check if card exists by number and card status.
	 *
	 * @param number the card number
	 * @param isActive the card status
	 * @return true, if successful
	 */
	boolean existsByNumberAndIsActive(String number, boolean isActive);
	
	/**
	 * Check if card exists by card number and PIN code and card status.
	 *
	 * @param number the card number
	 * @param pin the pin
	 * @param isActive the card status
	 * @return true, if successful
	 */
	boolean existsByNumberAndPinAndIsActive(String number, String pin, boolean isActive);
}

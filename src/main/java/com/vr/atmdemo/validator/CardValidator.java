package com.vr.atmdemo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.vr.atmdemo.entity.Card;
import com.vr.atmdemo.repository.CardRepository;

/**
 * The Class CardValidator.
 */
@Component
public class CardValidator {

	/** The Constant PIN_LENGTH. */
	private static final int PIN_LENGTH = 4;

	/** The repository. */
	@Autowired
	private CardRepository repository;

	/**
	 * Validate if exists.
	 *
	 * @param card the card
	 * @param result the result
	 * @return true, if successful
	 */
	public boolean validateIfExists(Card card, Errors result) {
		if (!repository.existsByNumberAndIsActive(card.getNumber(), true)) {
			result.rejectValue("number", "incorrect.card.number");
			return false;
		}
		return true;
	}
	
	/**
	 * Validate PIN.
	 *
	 * @param number the number
	 * @param pin the pin
	 * @param result the result
	 * @return true, if successful
	 */
	public boolean validatePIN(String number, String pin, Errors result) {
		if (pin == null || pin.isEmpty() || pin.length() != PIN_LENGTH || !pin.matches("[0-9]+")) {
			result.rejectValue("pin", "size.card.pin");
			return false;
		}
		return repository.existsByNumberAndPinAndIsActive(number, pin, true);
	}
}

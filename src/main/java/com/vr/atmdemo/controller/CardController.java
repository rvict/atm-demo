package com.vr.atmdemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.vr.atmdemo.entity.Card;
import com.vr.atmdemo.entity.Operation;
import com.vr.atmdemo.entity.OperationType;
import com.vr.atmdemo.repository.CardRepository;
import com.vr.atmdemo.validator.CardValidator;

/**
 * The Card Controller.
 */
@Controller
@SessionAttributes({"card","operation"})
public class CardController {

	/** The repository. 
	 * Inject spring-data-jpa repository
	 * */
	@Autowired
	private CardRepository repository;

	/** Inject the card validator. */
	@Autowired
	CardValidator cardValidator;

	/**
	 * Home page url.
	 *
	 * @return the string
	 */
	@GetMapping("/")
	public String index() {
		return "redirect:/card-number";
	}

	/**
	 * Initialize card number page.
	 *
	 * @param model the injected mvc model object
	 * @return the string
	 */
	@GetMapping("/card-number")
	public String inputNumber(Model model) {
		model.addAttribute(new Card());
		return "card-number-view";
	}

	/**
	 * Process card number.
	 *
	 * @param card the card
	 * @param result the binding result
	 * @param model the injected mvc model object
	 * @return the string
	 */
	@PostMapping("/card-number")
	public String inputNumber(@Valid @ModelAttribute Card card, BindingResult result, Model model) {

		if (result.hasFieldErrors("number") || !cardValidator.validateIfExists(card, result)) {
			return "card-number-view";
		}

		Card newCard = new Card();
		newCard.setNumber(card.getNumber());
		model.addAttribute(newCard);

		return "redirect:/card-pin";
	}

	/**
	 * Initialize card input page.
	 *
	 * @return the string
	 */
	@GetMapping("/card-pin")
	public String inputPin() {
		return "card-pin-view";
	}

	/**
	 * Process PIN code.
	 *
	 * @param card the Card entity object
	 * @param result the binding result
	 * @return the operation page url string
	 */
	@PostMapping("/card-pin")
	public String inputPin(@Valid @ModelAttribute Card card, BindingResult result) {
		if (!cardValidator.validatePIN(card.getNumber(), card.getPin(), result)) {
			Card existingCard = repository.findOneByNumberAndIsActive(card.getNumber(), true);
			if(existingCard == null || existingCard.getPinFailCount() > 3) {
				throw new RuntimeException("Card is blocked! You have maximum PIN try limit!");
			}
			existingCard.setPinFailCount(existingCard.getPinFailCount() +1);
			existingCard.setActive(existingCard.getPinFailCount() < 3);
			repository.save(existingCard);
			return "card-pin-view";
		}
		return "redirect:/operations";
	}

	/**
	 * Show operations.
	 *
	 * @param card the Card entity object
	 * @param model the injected mvc model object
	 * @return the operation view name
	 */
	@GetMapping("/operations")
	public String showOperations(@ModelAttribute Card card, Model model) {
		Card existingCard = repository.findOneByNumberAndIsActive(card.getNumber(), true);
		if (existingCard == null) {
			throw new RuntimeException("Card not found!!!");
		}
		model.addAttribute(existingCard);
		return "card-operations-view";
	}

	/**
	 * Show balance.
	 *
	 * @param card the Card entity object
	 * @param model the injected mvc model object
	 * @return the card balance view name
	 */
	@GetMapping("/balance")
	public String showBalance(@ModelAttribute Card card, Model model) {
		Operation operation = new Operation(OperationType.balance);
		operation.setAmount(card.getBalance());
		model.addAttribute(operation);
		operation.setCard(card);
		card.getOperations().add(operation);
		repository.save(card);

		model.addAttribute("date", String.format("%tc", System.currentTimeMillis()));
		return "card-balance-view";
	}

	/**
	 * Withdraw amount.
	 *
	 * @param model the injected mvc model object
	 * @return the card withdrawal view name
	 */
	@GetMapping("/withdrawal")
	public String withdrawAmount(Model model) {
		model.addAttribute(new Operation(OperationType.withdrawal));
		return "card-withdrawals-view";
	}

	/**
	 * Withdraw amount.
	 *
	 * @param card the Card entity object
	 * @param operation the Operation entity object
	 * @param result the binding result
	 * @param model the injected mvc model object
	 * @return the card withdrawal view name
	 */
	@PostMapping("/withdrawal")
	public String withdrawAmount(@ModelAttribute Card card, @ModelAttribute Operation operation, BindingResult result,
			Model model) {

		if (card.getBalance() - operation.getAmount() > 0) {
			card.setBalance(card.getBalance() - operation.getAmount());
			operation.setCard(card);
			card.getOperations().add(operation);
			repository.save(card);

			model.addAttribute(operation);
			model.addAttribute("date", String.format("%tc", System.currentTimeMillis()));

			return "card-operation-report-view";
		}

		result.rejectValue("amount", "amount.err", "The entered amount exceeds the account balance");
		return "card-withdrawals-view";
	}

	/**
	 * Exit.
	 *
	 * @param session the current session object
	 * @return the redirect url to home page
	 */
	@GetMapping("/exit")
	public String exit(SessionStatus session) {
		session.setComplete();
		return "redirect:/";
	}

	/**
	 * Handle error.
	 *
	 * @param req Http servlet request object
	 * @param ex the exception object
	 * @return the mvc model and view object
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex) {
		ModelAndView model = new ModelAndView();
		model.addObject("errorMsg", ex);
		model.addObject("url", req.getRequestURI());
		model.setViewName("error-view");
		return model;
	}
}

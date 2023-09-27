package com.mstradingcards.ServiceCartes.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mstradingcards.ServiceCartes.dto.CartesDTO;
import com.mstradingcards.ServiceCartes.enums.Rarity;
import com.mstradingcards.ServiceCartes.services.CartesService;

@RestController
@RequestMapping("/api/cards")
public class CartesController {

	@Autowired
	private CartesService cartesService;

	@GetMapping("/byName")
	public List<CartesDTO> getCardsByName(@RequestParam String name) {
		return cartesService.findCardsByName(name);
	}

	@GetMapping("/byRarity")
	public List<CartesDTO> getCardsByRarity(@RequestParam Rarity rarity) {
		return cartesService.findCardsByRarity(rarity);
	}

	@GetMapping("/byAttack")
	public List<CartesDTO> getCardsByAttackGreaterThan(@RequestParam int attackValue) {
		return cartesService.findCardsByAttackGreaterThan(attackValue);
	}

	@GetMapping("/byHealth")
	public List<CartesDTO> getCardsByHealthLessThan(@RequestParam int healthValue) {
		return cartesService.findCardsByHealthLessThan(healthValue);
	}

	@GetMapping("/byNameOrDescription")
	public List<CartesDTO> getCardsByNameOrDescription(@RequestParam String keyword) {
		return cartesService.findCardsByNameOrDescription(keyword);
	}

	@GetMapping("/getAllCards")
	public List<CartesDTO> getAllCards() {
		return cartesService.getAllCards();
	}

	/* ADMIN ONLY */
	@PostMapping("/addCard")
	public CartesDTO addCard(@Valid @RequestBody CartesDTO cardDTO) {
		return cartesService.addCard(cardDTO);
	}

	@PostMapping("/bulkAddCard")
	public List<CartesDTO> bulkAddCard(@Valid @RequestBody List<CartesDTO> listCardsDTO) {
		return cartesService.bulkAddCard(listCardsDTO);
	}

	@GetMapping("/getCard/{id}")
	public CartesDTO getCardById(@PathVariable Long id) {
		return cartesService.getCardById(id);
	}

	@PutMapping("/updateCard/{id}")
	public CartesDTO updateCard(@PathVariable Long id, @Valid @RequestBody CartesDTO cardDTO) {
		return cartesService.updateCard(id, cardDTO);
	}

	@DeleteMapping("/deleteCard/{id}")
	public void deleteCard(@PathVariable Long id) {
		cartesService.deleteCard(id);
	}
	/* ADMIN ONLY */

}

package com.mstradingcards.ServiceCartes.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mstradingcards.ServiceCartes.dto.CartesDTO;
import com.mstradingcards.ServiceCartes.enums.Rarity;
import com.mstradingcards.ServiceCartes.models.Cartes;
import com.mstradingcards.ServiceCartes.repository.CartesRepository;

@Service
public class CartesService {
	@Autowired
	private CartesRepository cartesRepository;

	public List<CartesDTO> findCardsByName(String name) {
		return mapToCardDTOList(cartesRepository.findByName(name));
	}

	public List<CartesDTO> findCardsByRarity(Rarity rarity) {
		return mapToCardDTOList(cartesRepository.findByRarity(rarity));
	}

	public List<CartesDTO> findCardsByAttackGreaterThan(int attackValue) {
		return mapToCardDTOList(cartesRepository.findByAttackGreaterThan(attackValue));
	}

	public List<CartesDTO> findCardsByHealthLessThan(int healthValue) {
		return mapToCardDTOList(cartesRepository.findByHealthLessThan(healthValue));
	}

	public List<CartesDTO> findCardsByNameOrDescription(String keyword) {
		return mapToCardDTOList(cartesRepository.findByNameOrDescription(keyword));
	}

	public CartesDTO addCard(CartesDTO cardDTO) {
		Assert.notNull(cardDTO, "CardDTO must not be null");
		Cartes card = mapToCardEntity(cardDTO);
		return mapToCardDTO(cartesRepository.save(card));
	}

	public List<CartesDTO> bulkAddCard(@Valid List<CartesDTO> listCardsDTO) {
		List<Cartes> listCard = mapToCardList(listCardsDTO);
		return mapToCardDTOList(cartesRepository.saveAll(listCard));
	}

	public List<CartesDTO> getAllCards() {
		return mapToCardDTOList(cartesRepository.findAll());
	}

	public CartesDTO getCardById(Long id) {
		return mapToCardDTO(cartesRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Card with id " + id + " not found")));
	}

	public CartesDTO updateCard(Long id, CartesDTO cardDTO) {
		Assert.notNull(cardDTO, "CardDTO must not be null");
		Cartes existingCard = mapToCardEntity(cardDTO);
		return mapToCardDTO(cartesRepository.save(existingCard));
	}

	public void deleteCard(Long id) {
		cartesRepository.deleteById(id);
	}

	private Cartes mapToCardEntity(CartesDTO dto) {
		Cartes card = new Cartes();
		card.setName(dto.getName());
		card.setRarity(dto.getRarity());
		card.setAttack(dto.getAttack());
		card.setHealth(dto.getHealth());
		card.setDescription(dto.getDescription());
		return card;
	}

	private CartesDTO mapToCardDTO(Cartes card) {
		CartesDTO dto = new CartesDTO();
		dto.setName(card.getName());
		dto.setRarity(card.getRarity());
		dto.setAttack(card.getAttack());
		dto.setHealth(card.getHealth());
		dto.setDescription(card.getDescription());
		return dto;
	}

	private List<CartesDTO> mapToCardDTOList(List<Cartes> cards) {
		return cards.stream().map(card -> {
			CartesDTO cardDTO = new CartesDTO();
			cardDTO.setName(card.getName());
			cardDTO.setRarity(card.getRarity());
			cardDTO.setAttack(card.getAttack());
			cardDTO.setHealth(card.getHealth());
			cardDTO.setDescription(card.getDescription());
			return cardDTO;
		}).collect(Collectors.toList());
	}

	private List<Cartes> mapToCardList(List<CartesDTO> cardsDTO) {
		return cardsDTO.stream().map(cardDTO -> {
			Cartes card = new Cartes();
			card.setName(cardDTO.getName());
			card.setRarity(cardDTO.getRarity());
			card.setAttack(cardDTO.getAttack());
			card.setHealth(cardDTO.getHealth());
			card.setDescription(cardDTO.getDescription());
			return card;
		}).collect(Collectors.toList());
	}

}

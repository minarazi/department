package com.example.department.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.department.domain.Buy;
import com.example.department.dto.BuyDTO;
import com.example.department.dto.ProductDTO;
import com.example.department.repositories.BuyRepository;
import com.example.department.services.BuyService;
import com.example.department.services.mapper.BuyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class BuyServiceImpl implements BuyService {

	private final BuyRepository buyRepository;
	private final BuyMapper buyMapper;

	@Override
	public BuyDTO findBuyById(Long id) {

		Optional<Buy> buyOptional = buyRepository.findById(id);
		if (!buyOptional.isPresent()) {
			throw new RuntimeException("Buy not found!");
		}

		Buy buy = buyOptional.get();
		BuyDTO buyDto = buyMapper.entityToDto(buy);
		return buyDto;
	}

	@Override
	public List<BuyDTO> findAllBuy() {

		return buyRepository.findAll().stream().map(buyMapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public Buy findById(Long id) {
		Optional<Buy> buyOptional = buyRepository.findById(id);
		if (!buyOptional.isPresent()) {
			throw new RuntimeException("Buy not found!");
		}
		return buyOptional.get();
	}

	public Buy totalPriceCalculator(Buy buy) {

		Integer price = buy.getProduct().getPrice();
		int amount = buy.getAmount();

		Integer totalPrice = price * amount;

		buy.setTotalPrice(totalPrice);

		return buy;

	}

	@Override
	public BuyDTO createBuy(BuyDTO buyDTO) {
		Buy buy = buyMapper.dtoToEntity(buyDTO);

		buy = totalPriceCalculator(buy);
		buy = buyRepository.save(buy);

		log.info("created CompanyId" + buy.getId());

		return buyMapper.entityToDto(buy);
	}

	@Override
	public BuyDTO findBuyByPersonId(Long personId) {
		// TODO Auto-generated method stub
		return null;
	}

}

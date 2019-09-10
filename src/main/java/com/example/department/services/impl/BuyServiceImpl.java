package com.example.department.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.department.domain.Buy;
import com.example.department.dto.BuyDTO;
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

	public void checkProductAmount(Buy buy) {

		int buyAmount = buy.getAmount();
		Integer productAmount = buy.getProduct().getAmount();
		if (buyAmount > productAmount) {

			throw new RuntimeException("Product amount is Not enough!");

		}
	}

	public Buy totalPriceCalculator(Buy buy) {

		Integer price = buy.getProduct().getPrice();
		int amount = buy.getAmount();

		Integer totalPrice = price * amount;

		buy.setTotalPrice(totalPrice);

		return buy;

	}

	@Transactional
	@Override
	public BuyDTO createBuy(BuyDTO buyDTO) {
		Buy buy = buyMapper.dtoToEntity(buyDTO);

		checkProductAmount(buy);
		buy = totalPriceCalculator(buy);
		buy = buyRepository.save(buy);

		log.info("created CompanyId" + buy.getId());

		return buyMapper.entityToDto(buy);
	}

	@Override
	public List<BuyDTO> findByPersonId(Long personId) {

		return buyRepository.findByPerson_Id(personId).stream().map(buyMapper::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<BuyDTO> findByDepartmentId(Long departmentId) {

		return buyRepository.findByPerson_Department_Id(departmentId).stream().map(buyMapper::entityToDto)
				.collect(Collectors.toList());
	}

}

package com.example.department.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.department.domain.Buy;
import com.example.department.dto.BuyDTO;
import com.example.department.dto.DepartmentBuyDTO;
import com.example.department.repositories.BuyRepository;
import com.example.department.services.BuyService;
import com.example.department.services.ProductService;
import com.example.department.services.mapper.BuyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class BuyServiceImpl implements BuyService {

	private static final String PRODUCT_AMOUNT_IS_NOT_ENOUGH = "Product amount is Not enough!";
	private static final String BUY_NOT_FOUND = "Buy not found!";

	private final BuyRepository buyRepository;
	private final BuyMapper buyMapper;
	private final ProductService productService;

	@Override
	public BuyDTO findBuyById(Long id) {

		Optional<Buy> buyOptional = buyRepository.findById(id);
		if (!buyOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, BUY_NOT_FOUND);
		}

		Buy buy = buyOptional.get();

		return buyMapper.entityToDto(buy);
	}

	@Override
	public List<BuyDTO> findAllBuy() {

		return buyRepository.findAll().stream().map(buyMapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public Buy findById(Long id) {
		Optional<Buy> buyOptional = buyRepository.findById(id);
		if (!buyOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, BUY_NOT_FOUND);
		}
		return buyOptional.get();
	}

	public void checkProductAmount(Buy buy) {

		int buyAmount = buy.getAmount();
		Integer productAmount = buy.getProduct().getAmount();
		if (buyAmount > productAmount) {

			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, PRODUCT_AMOUNT_IS_NOT_ENOUGH);
		}
	}

	public Buy totalPriceCalculator(Buy buy) {

		Integer price = buy.getProduct().getPrice();
		int amount = buy.getAmount();

		Integer totalPrice = price * amount;

		buy.setTotalPrice(totalPrice);

		return buy;

	}

	public void updateProductAmount(Buy buy) {
		int buyamount = buy.getAmount();
		Integer productAmount = buy.getProduct().getAmount();
		productAmount = productAmount - buyamount;
		buy.getProduct().setAmount(productAmount);
		productService.updateProduct(buy.getProduct());
	}

	@Transactional
	@Override
	public BuyDTO createBuy(BuyDTO buyDTO) {
		Buy buy = buyMapper.dtoToEntity(buyDTO);

		checkProductAmount(buy);
		buy = totalPriceCalculator(buy);
		buy = buyRepository.save(buy);
		updateProductAmount(buy);
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

	@Override
	public DepartmentBuyDTO getAllBuyAmountAndPriceByDepartmentId(Long departmentId) {

		int totalBuyAmount = buyRepository.findByPerson_Department_Id(departmentId).stream().mapToInt(Buy::getAmount)
				.sum();
		int totalBuyPrice = buyRepository.findByPerson_Department_Id(departmentId).stream().mapToInt(Buy::getTotalPrice)
				.sum();

		DepartmentBuyDTO departmentBuyDTO = new DepartmentBuyDTO();
		departmentBuyDTO.setDepartmentId(departmentId);
		departmentBuyDTO.setTotalBuyAmount(totalBuyAmount);
		departmentBuyDTO.setTotalBuyPrice(totalBuyPrice);

		return departmentBuyDTO;
	}

}

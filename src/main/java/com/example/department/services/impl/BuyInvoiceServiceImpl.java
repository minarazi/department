package com.example.department.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.department.domain.BuyInvoice;
import com.example.department.dto.BuyInvoiceDTO;
import com.example.department.dto.DepartmentBuyInvoiceDTO;
import com.example.department.repositories.BuyInvoiceRepository;
import com.example.department.services.BuyInvoiceService;
import com.example.department.services.ProductService;
import com.example.department.services.mapper.BuyInvoiceMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class BuyInvoiceServiceImpl implements BuyInvoiceService {

	private static final String PRODUCT_AMOUNT_IS_NOT_ENOUGH = "Product amount is Not enough!";
	private static final String BUY_INVOICE_NOT_FOUND = "BuyInvoice not found!";

	private final BuyInvoiceRepository buyInvoiceRepository;
	private final BuyInvoiceMapper buyInvoiceMapper;
	private final ProductService productService;

	@Override
	public BuyInvoiceDTO findBuyInvoiceById(Long id) {

		Optional<BuyInvoice> buyInvoiceOptional = buyInvoiceRepository.findById(id);
		if (!buyInvoiceOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, BUY_INVOICE_NOT_FOUND);
		}

		BuyInvoice buyInvoice = buyInvoiceOptional.get();

		return buyInvoiceMapper.entityToDto(buyInvoice);
	}

	@Override
	public List<BuyInvoiceDTO> findAllBuyInvoice() {

		return buyInvoiceRepository.findAll().stream().map(buyInvoiceMapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public BuyInvoice findById(Long id) {
		Optional<BuyInvoice> buyInvoiceOptional = buyInvoiceRepository.findById(id);
		if (!buyInvoiceOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, BUY_INVOICE_NOT_FOUND);
		}
		return buyInvoiceOptional.get();
	}

	public void checkProductAmount(BuyInvoice buyInvoice) {

		int buyInvoiceAmount = buyInvoice.getAmount();
		Integer productAmount = buyInvoice.getProduct().getAmount();
		if (buyInvoiceAmount > productAmount) {

			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, PRODUCT_AMOUNT_IS_NOT_ENOUGH);
		}
	}

	public BuyInvoice totalPriceCalculator(BuyInvoice buyInvoice) {

		Integer price = buyInvoice.getProduct().getPrice();
		int amount = buyInvoice.getAmount();

		Integer totalPrice = price * amount;

		buyInvoice.setTotalPrice(totalPrice);

		return buyInvoice;

	}

	public void updateProductAmount(BuyInvoice buyInvoice) {
		int buyInvoiceamount = buyInvoice.getAmount();
		Integer productAmount = buyInvoice.getProduct().getAmount();
		productAmount = productAmount - buyInvoiceamount;
		buyInvoice.getProduct().setAmount(productAmount);
		productService.updateProduct(buyInvoice.getProduct());
	}

	@Transactional
	@Override
	public BuyInvoiceDTO createBuyInvoice(BuyInvoiceDTO buyInvoiceDTO) {
		BuyInvoice buyInvoice = buyInvoiceMapper.dtoToEntity(buyInvoiceDTO);

		checkProductAmount(buyInvoice);
		buyInvoice = totalPriceCalculator(buyInvoice);
		buyInvoice = buyInvoiceRepository.save(buyInvoice);
		updateProductAmount(buyInvoice);
		log.info("created CompanyId" + buyInvoice.getId());

		return buyInvoiceMapper.entityToDto(buyInvoice);
	}

	@Override
	public List<BuyInvoiceDTO> findByPersonId(Long personId) {

		return buyInvoiceRepository.findByPerson_Id(personId).stream().map(buyInvoiceMapper::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<BuyInvoiceDTO> findByDepartmentId(Long departmentId) {

		return buyInvoiceRepository.findByPerson_Department_Id(departmentId).stream().map(buyInvoiceMapper::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentBuyInvoiceDTO getAllBuyInvoiceAmountAndPriceByDepartmentId(Long departmentId) {

		int totalBuyInvoiceAmount = buyInvoiceRepository.findByPerson_Department_Id(departmentId).stream()
				.mapToInt(BuyInvoice::getAmount).sum();
		int totalBuyInvoicePrice = buyInvoiceRepository.findByPerson_Department_Id(departmentId).stream()
				.mapToInt(BuyInvoice::getTotalPrice).sum();

		DepartmentBuyInvoiceDTO departmentBuyInvoiceDTO = new DepartmentBuyInvoiceDTO();
		departmentBuyInvoiceDTO.setDepartmentId(departmentId);
		departmentBuyInvoiceDTO.setTotalBuyAmount(totalBuyInvoiceAmount);
		departmentBuyInvoiceDTO.setTotalBuyPrice(totalBuyInvoicePrice);

		return departmentBuyInvoiceDTO;
	}

}

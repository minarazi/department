package com.example.department.services;

import java.util.List;

import com.example.department.domain.Buy;
import com.example.department.dto.BuyDTO;

public interface BuyService {

	public BuyDTO findBuyById(Long id);

	public List<BuyDTO> findAllBuy();

	public Buy findById(Long id);

	public BuyDTO findBuyByPersonId(Long personId);

	public BuyDTO createBuy(BuyDTO buyDTO);
}

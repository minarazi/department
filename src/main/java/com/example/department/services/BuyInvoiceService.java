package com.example.department.services;

import java.util.List;

import com.example.department.domain.BuyInvoice;
import com.example.department.dto.BuyInvoiceDTO;
import com.example.department.dto.DepartmentBuyInvoiceDTO;

public interface BuyInvoiceService {

	public BuyInvoiceDTO findBuyInvoiceById(Long id);

	public List<BuyInvoiceDTO> findAllBuyInvoice();

	public BuyInvoice findById(Long id);

	public List<BuyInvoiceDTO> findByPersonId(Long personId);

	public List<BuyInvoiceDTO> findByDepartmentId(Long departmentId);

	public BuyInvoiceDTO createBuyInvoice(BuyInvoiceDTO buyInvoiceDTO);

	public DepartmentBuyInvoiceDTO getAllBuyInvoiceAmountAndPriceByDepartmentId(Long departmentId);
}

package com.rushi.rest;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.dto.OrderDto;
import com.rushi.dto.ReportsDto;
import com.rushi.exception.ReportServiceException;
import com.rushi.service.ReportService;
import com.rushi.utils.ExcelGenerator;
import com.rushi.utils.PdfGenerator;

@RestController
public class ReportRestController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/FilterOrderExcel")
	public ResponseEntity<InputStreamResource> downoladExcel(ReportsDto reportDto) throws ReportServiceException{
		List<OrderDto> orders=new ArrayList<>();
		
		if(reportDto.getStatus()!=null) {
			orders= reportService.orderByStatus(reportDto.getStatus());
		}
		if(reportDto.getStartDate() !=null || reportDto.getEndDate()!=null) {
			List<OrderDto> dateFilteredOrders=reportService.getOrdersBetweenDate(reportDto.getStartDate(), reportDto.getEndDate());
			if(!orders.isEmpty()) {
				orders.retainAll(dateFilteredOrders);
			}else {
				orders= dateFilteredOrders;
			}
		}
		
		if(reportDto.getStatus()==null && reportDto.getStartDate()==null&& reportDto.getEndDate()==null) {
		orders=reportService.getAllOrders();
		}
		
		if(orders!=null) {
			try {
				ByteArrayInputStream in=ExcelGenerator.generateExcel(orders);
				InputStreamResource resource=new InputStreamResource(in);
				HttpHeaders headers=new HttpHeaders();
				return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
	}
	@GetMapping("/FilterOrderPdf")
	public ResponseEntity<InputStreamResource> downloadPdf(ReportsDto reportsDto) throws ReportServiceException {

		List<OrderDto> orders = new ArrayList<>();

		if (reportsDto.getStatus() != null) {
			orders = reportService.orderByStatus(reportsDto.getStatus());
		}

		if (reportsDto.getStartDate() != null || reportsDto.getEndDate() != null) {
			List<OrderDto> dateFilteredOrders = reportService.getOrdersBetweenDate(reportsDto.getStartDate(),
					reportsDto.getEndDate());
			if (!orders.isEmpty()) {
				orders.retainAll(dateFilteredOrders);
			} else {
				orders = dateFilteredOrders;
			}
		}

		if (reportsDto.getStatus() == null && reportsDto.getStartDate() == null && reportsDto.getEndDate() == null) {
			orders = reportService.getAllOrders();
		}

		if (orders != null) {
			ByteArrayInputStream in = PdfGenerator.generatePdf(orders);
			InputStreamResource resource = new InputStreamResource(in);
			HttpHeaders headers = new HttpHeaders();
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
		
}

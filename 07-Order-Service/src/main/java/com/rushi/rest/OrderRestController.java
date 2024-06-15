package com.rushi.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.reactive.ApplicationContextServerWebExchangeMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.constants.AppConstants;
import com.rushi.dto.ProductOrderDto;
import com.rushi.props.AppProps;
import com.rushi.response.ApiResponse;
import com.rushi.service.IOrderService;

@RestController
public class OrderRestController {
		
	    @Autowired
	    private IOrderService orderService;
	    
	    @Autowired
	    private AppProps appProps;

	    @PostMapping("/order")
	    public ResponseEntity<ApiResponse<List<ProductOrderDto>>> addOrder(@RequestBody List<ProductOrderDto> productOrderDto) {
	        List<ProductOrderDto> createdOrders = orderService.addOrder(productOrderDto);
	        ApiResponse<List<ProductOrderDto>> response=new ApiResponse<>();
	        Map<String,String> messages=appProps.getMessages();
	        if(createdOrders!=null) {
	        	response.setStatusCode(200);
	        	response.setMessage(messages.get(AppConstants.LIST_ADDED));
	        	response.setData(createdOrders);
	        }
	        else {
	        	response.setStatusCode(500);
	        	response.setMessage("");
	        }
	        
	        return new ResponseEntity<>(response,HttpStatus.OK);
	  
	    }

	    @PutMapping("r/{orderId}")
	    public ResponseEntity<ApiResponse<ProductOrderDto>> updateOrder(@PathVariable Integer orderId, @RequestBody ProductOrderDto productOrderDto) {
	       
	    	ApiResponse<ProductOrderDto> response=new ApiResponse<>();
	    	Map<String,String> messages=appProps.getMessages();
	    	productOrderDto.setOrderId(orderId);
	        ProductOrderDto updatedOrder = orderService.updateOrder(productOrderDto);
	        
	        if(updatedOrder!=null) {
	        	response.setStatusCode(200);
	        	response.setMessage(messages.get(AppConstants.ORDER_UDPATE));
	        	response.setData(updatedOrder);
	        }else {
	        	response.setStatusCode(500);
	        	response.setMessage(messages.get(AppConstants.ORDER_UPDATE_ERR));
	        }
	        return new ResponseEntity<>(response,HttpStatus.OK);
	    }

	    @GetMapping("/user/{userId}")
	    public ResponseEntity<ApiResponse<List<ProductOrderDto>>> getOrderByUserId(@PathVariable Integer userId) {
	    	ApiResponse<List<ProductOrderDto>> response=new ApiResponse<>();
	    	Map<String,String> messages=appProps.getMessages();
	        List<ProductOrderDto> orders = orderService.getOrderByUserId(userId);
	        if(orders!=null) {
	        	response.setStatusCode(200);
	        	response.setMessage(messages.get(AppConstants.ORDER_RETRIEVE));
	        	response.setData(orders);
	        }
	        else {
	        	response.setStatusCode(500);
	        	response.setMessage(messages.get(AppConstants.ORDER_RETRIEVE_FAILED));
	        }
	        return new  ResponseEntity<>(response,HttpStatus.OK);
	    }

	    @GetMapping("/orders")
	    public ResponseEntity<ApiResponse<List<ProductOrderDto>>> getOrderByDateAndStatus(
	            @RequestParam LocalDate orderDate,
	            @RequestParam String orderStatus) {
	        
	        ApiResponse<List<ProductOrderDto>> response = new ApiResponse<>();
	        Map<String, String> messages = appProps.getMessages();
	        List<ProductOrderDto> orders = orderService.getOrderByDateAndStauts(orderDate, orderStatus);

	        if (orders != null ) {
	            response.setStatusCode(200);
	            response.setMessage(messages.get(AppConstants.ORDER_RETRIEVE_BY_DATE));
	            response.setData(orders);
	        } else {
	            response.setStatusCode(500);
	            response.setMessage(messages.get(AppConstants.ORDER_NOT_FOUND_BY_DATE));
	        }

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    @GetMapping("/allorders")
	    public ResponseEntity<ApiResponse<List<ProductOrderDto>>> getAllOrders() {
	    	ApiResponse<List<ProductOrderDto>> response=new ApiResponse<>();
	        List<ProductOrderDto> orders = orderService.getAllOrders();
	        Map<String,String> messages=appProps.getMessages();
	        if(orders !=null) {
	        response.setStatusCode(200);
	        response.setMessage(messages.get(AppConstants.ALL_ORDER_RETRIEVE));
	        response.setData(orders);
	        }else {
	        	response.setStatusCode(500);
	        	response.setMessage(messages.get(AppConstants.All_ORDER_RETRIEVE_ERR));
	        }
	        return new ResponseEntity<>(response,HttpStatus.OK);
	    }
}

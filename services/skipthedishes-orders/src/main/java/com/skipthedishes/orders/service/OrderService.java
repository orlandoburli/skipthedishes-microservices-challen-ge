package com.skipthedishes.orders.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skipthedishes.model.dto.CustomerDto;
import com.skipthedishes.model.dto.OrderDto;
import com.skipthedishes.model.interfaces.IOrder;
import com.skipthedishes.orders.domain.Order;
import com.skipthedishes.orders.exceptions.OrderException;
import com.skipthedishes.orders.exceptions.OrderNotFoundException;
import com.skipthedishes.orders.messages.MessageByLocaleService;
import com.skipthedishes.orders.repository.OrderRepository;

@Service
public class OrderService {

	private final ModelMapper modelMapper;

	private final MessageByLocaleService messageByLocaleService;

	private final OrderRepository orderRepository;

	private final RestTemplate restTemplate;

	@Autowired
	public OrderService(ModelMapper modelMapper, MessageByLocaleService messageByLocaleService,
			OrderRepository orderRepository, RestTemplate restTemplate) {
		this.modelMapper = modelMapper;
		this.messageByLocaleService = messageByLocaleService;
		this.orderRepository = orderRepository;
		this.restTemplate = restTemplate;
	}

	public OrderDto create(IOrder orderToCreate) throws OrderException {
		// TODO Validations...
		this.validateOrder(orderToCreate);
		this.validateCustomer(orderToCreate);
		this.validateProducts(orderToCreate);

		final Order order = this.modelMapper.map(orderToCreate, Order.class, "id");

		final Order orderCreate = this.orderRepository.save(order);

		return this.modelMapper.map(orderCreate, OrderDto.class);
	}

	public OrderDto findById(Long id) throws OrderNotFoundException {

		final Optional<Order> findById = this.orderRepository.findById(id);

		if (!findById.isPresent()) {
			throw new OrderNotFoundException(this.messageByLocaleService.getMessage("order.not.found"));
		}

		return this.modelMapper.map(findById.get(), OrderDto.class);
	}

	private void validateProducts(IOrder order) {
		// TODO Auto-generated method stub

	}

	private void validateCustomer(IOrder order) throws OrderException {
		// TODO Auto-generated method stub
		if (order.getCustomerId() != null) {
			// TODO Validate accross Customer microService
			final CustomerDto customerDto = this.restTemplate
					.getForObject("http://localhost:8010/api/v1/Customer/" + order.getCustomerId(), CustomerDto.class);

			if (customerDto == null) {
				throw new OrderException(this.messageByLocaleService.getMessage("customer.invalid"));
			}
		} else {
			throw new OrderException(this.messageByLocaleService.getMessage("customer.not.informed"));
		}
	}

	private void validateOrder(IOrder order) {
		// TODO Auto-generated method stub
	}

	public List<OrderDto> findByTokenSession(String token) {
		return null;
	}
}

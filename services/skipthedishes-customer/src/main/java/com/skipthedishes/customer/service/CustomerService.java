package com.skipthedishes.customer.service;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.skipthedishes.customer.constants.IMessages;
import com.skipthedishes.customer.domain.Customer;
import com.skipthedishes.customer.domain.CustomerSession;
import com.skipthedishes.customer.domain.enums.CustomerSessionStatus;
import com.skipthedishes.customer.exceptions.CustomerNotFoundException;
import com.skipthedishes.customer.exceptions.DuplicatedCustomerEmailException;
import com.skipthedishes.customer.exceptions.WeakPasswordException;
import com.skipthedishes.customer.messages.MessageByLocaleService;
import com.skipthedishes.customer.repository.CustomerRepository;
import com.skipthedishes.customer.repository.CustomerSessionRepository;
import com.skipthedishes.model.dto.CustomerDto;
import com.skipthedishes.model.interfaces.ICustomer;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	private final CustomerSessionRepository customerSessionRepository;

	private final ModelMapper modelMapper;

	private final MessageByLocaleService messageByLocaleService;

	@Autowired
	public CustomerService(final CustomerRepository customerRepository, final ModelMapper modelMapper,
			final MessageByLocaleService messageByLocaleService, CustomerSessionRepository customerSessionRepository) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
		this.messageByLocaleService = messageByLocaleService;
		this.customerSessionRepository = customerSessionRepository;
	}

	/**
	 * Returns a Customer from an Id.
	 *
	 * @param id
	 *            Id of the Customer.
	 * @return The CustomerDto data.
	 * @throws CustomerNotFoundException
	 *             If the customer is not found, throws this exceptions
	 */
	public CustomerDto findById(Long id) throws CustomerNotFoundException {
		final Optional<Customer> findById = this.customerRepository.findById(id);

		findById.orElseThrow(() -> new CustomerNotFoundException(
				this.messageByLocaleService.getMessage(IMessages.CUSTOMER_NOT_FOUND)));

		return this.modelMapper.map(findById.get(), CustomerDto.class);
	}

	public CustomerDto create(CustomerDto customerDto) throws DuplicatedCustomerEmailException, WeakPasswordException {
		this.validateIfEmailExists(customerDto.getEmail());
		this.validatePasswordStrength(customerDto.getPassword());

		final Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer, "customerId");

		final Customer insert = this.customerRepository.save(customer);

		return this.modelMapper.map(insert, CustomerDto.class);
	}

	public String authenticate(String email, String password) throws CustomerNotFoundException {

		final Optional<Customer> findByEmail = this.customerRepository.findByEmail(email);

		findByEmail.orElseThrow(() -> new CustomerNotFoundException(
				this.messageByLocaleService.getMessage(IMessages.INVALID_EMAIL_PASSWORD)));

		final Customer customer = findByEmail.get();

		this.validatePassword(password, customer);

		return this.buildSessionToken(customer);
	}

	private String buildSessionToken(ICustomer customer) {
		final CustomerSession session = new CustomerSession();

		session.setStatus(CustomerSessionStatus.ONLINE);
		session.setId(UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));
		session.setCustomerId(customer.getId());

		final CustomerSession save = this.customerSessionRepository.save(session);

		return save.getId();

	}

	private void validatePassword(String password, final ICustomer customer) throws CustomerNotFoundException {
		if (customer == null || !customer.getPassword().equals(password)) {
			throw new CustomerNotFoundException(
					this.messageByLocaleService.getMessage(IMessages.INVALID_EMAIL_PASSWORD));
		}
	}

	public void validatePasswordStrength(String password) throws WeakPasswordException {
		if (StringUtils.isEmpty(password) || password.trim().length() < 5) {
			throw new WeakPasswordException(this.messageByLocaleService.getMessage(IMessages.WEAK_PASSWORD));
		}
	}

	public void validateIfEmailExists(String email) throws DuplicatedCustomerEmailException {

		if (this.customerRepository.findByEmail(email).isPresent()) {
			throw new DuplicatedCustomerEmailException(
					this.messageByLocaleService.getMessage(IMessages.EMAIL_ALREADY_EXISTS));
		}

	}

}

package com.geekshirt.ordenservice.config.client;

import com.geekshirt.ordenservice.config.OrderServiceConfig;
import com.geekshirt.ordenservice.dto.AccountDto;
import com.geekshirt.ordenservice.dto.AddressDto;
import com.geekshirt.ordenservice.dto.CreditCardDto;
import com.geekshirt.ordenservice.dto.CustomerDto;
import com.geekshirt.ordenservice.utils.AccountStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
public class CustomerServiceClient {
    private RestTemplate restTemplate;

    @Autowired
    private OrderServiceConfig config;

    public CustomerServiceClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public Optional<AccountDto> findAccountById(String accountId) {

        Optional<AccountDto> result = Optional.empty();

        try {
            result = Optional.ofNullable(restTemplate.getForObject(config.getCustomerServiceUrl() + "/{id}", AccountDto.class, accountId));

        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() != HttpStatus.NOT_FOUND) {
                System.out.println("AQUI------------------------------------");
                throw exception;
            }
        }

        return result;
    }

    public AccountDto createDummyAccount() {
        AddressDto addressDto = AddressDto.builder().street("picaflor").city("bsas").state("adrogue").country("Argentina").zipCode("1846").build();
        CustomerDto customerDto = CustomerDto.builder().lastName("Nuñez").firstName("Ivan").email("ivan@gmail.com").build();
        CreditCardDto creditCardDto = CreditCardDto.builder().nameOnCard("IvanCard")
                .number("1234 2345 3456 4567")
                .expirationMonth("03")
                .expirationYear("2024")
                .type("VISA")
                .ccv("123")
                .build();
        AccountDto accountDto = AccountDto.builder().address(addressDto)
                .customer(customerDto)
                .creditCard(creditCardDto)
                .status(AccountStatus.ACTIVE)
                .build();

        return accountDto;
    }

    public AccountDto createAccount(AccountDto accountDto) {
        return restTemplate.postForObject(config.getCustomerServiceUrl(), accountDto, AccountDto.class);
    }

    public AccountDto createAccountBody(AccountDto accountDto) {
        ResponseEntity<AccountDto> responseAccountEntity = restTemplate.postForEntity(config.getCustomerServiceUrl(), accountDto, AccountDto.class);
        log.info("Response: -->" + responseAccountEntity.getHeaders());
        return responseAccountEntity.getBody();
    }

    public void updateAccount(AccountDto accountDto) {
        restTemplate.put(config.getCustomerServiceUrl() + "/{id}", accountDto, accountDto.getId());
    }

    public void deleteAccount(AccountDto accountDto) {
        restTemplate.delete(config.getCustomerServiceUrl() + "/{id}", accountDto.getId());
    }
}

package com.geekshirt.ordenservice.dto;

import com.geekshirt.ordenservice.utils.AccountStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDto {
    private Long id;
    private AddressDto address;
    private CustomerDto customer;
    private CreditCardDto creditCard;
    private AccountStatus status;
}

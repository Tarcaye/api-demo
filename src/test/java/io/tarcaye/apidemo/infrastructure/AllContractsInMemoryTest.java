package io.tarcaye.apidemo.infrastructure;

import io.tarcaye.apidemo.domain.model.Contract;
import io.tarcaye.apidemo.domain.model.Contracts;
import io.tarcaye.apidemo.domain.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AllContractsInMemoryTest {

    private final AllContractsInMemory allContractsInMemory = new AllContractsInMemory();

    @Test
    void return_the_contracts_of_an_existing_customer() {
        Customer customer = Customer.of("9001");

        Optional<Contracts> contracts = allContractsInMemory.findFor(customer);

        assertThat(contracts).hasValueSatisfying(it -> assertThat(it.asList()).hasSize(2));
    }

    @Test
    void return_empty_for_non_existing_customer() {
        Customer nonExistingCustomer = Customer.of("9");

        Optional<Contracts> contracts = allContractsInMemory.findFor(nonExistingCustomer);

        assertThat(contracts).isEmpty();
    }

    @Test
    void add_contract() {
        Customer newCustomer = Customer.of("2");

        allContractsInMemory.add(new Contract(newCustomer));

        Optional<Contracts> contracts = allContractsInMemory.findFor(newCustomer);
        assertThat(contracts).hasValueSatisfying(it -> assertThat(it.asList()).hasSize(1));
    }
}
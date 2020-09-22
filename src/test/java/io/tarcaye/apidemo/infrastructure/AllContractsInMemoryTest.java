package io.tarcaye.apidemo.infrastructure;

import io.tarcaye.apidemo.domain.model.Contracts;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AllContractsInMemoryTest {

    private final AllContractsInMemory allContractsInMemory = new AllContractsInMemory();

    @Test
    void return_the_contracts_of_an_existing_customer() {
        String existingCustomerId = "9001";
        assertThat(allContractsInMemory.findFor(existingCustomerId)).hasValueSatisfying(it -> assertThat(it.asList()).hasSize(2));
    }

    @Test
    void return_empty_for_non_existing_customer() {
        String nonExistingCustomerId = "9";

        Optional<Contracts> contracts = allContractsInMemory.findFor(nonExistingCustomerId);

        assertThat(contracts).isEmpty();
    }
}
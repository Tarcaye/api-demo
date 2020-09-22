package io.tarcaye.apidemo.infrastructure;

import io.tarcaye.apidemo.domain.model.AllContracts;
import io.tarcaye.apidemo.domain.model.Contract;
import io.tarcaye.apidemo.domain.model.Contracts;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Optional;

@Repository
public class AllContractsInMemory implements AllContracts {

    @Override
    public Optional<Contracts> findFor(String customerId) {
        if (customerId.equals("9001"))
            return Optional.of(new Contracts(Collections.singletonList(new Contract())));
        return Optional.empty();
    }
}

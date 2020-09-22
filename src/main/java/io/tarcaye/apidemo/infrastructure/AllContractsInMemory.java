package io.tarcaye.apidemo.infrastructure;

import io.tarcaye.apidemo.domain.model.AllContracts;
import io.tarcaye.apidemo.domain.model.Contract;
import io.tarcaye.apidemo.domain.model.Contracts;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.util.Arrays.asList;

@Repository
public class AllContractsInMemory implements AllContracts {

    private List<Contract> contracts;

    public AllContractsInMemory() {
        contracts = asList(new Contract(), new Contract());
    }

    @Override
    public Optional<Contracts> findFor(String customerId) {
        if (customerId.equals("9001")) {
            return Optional.of(new Contracts(contracts));
        }
        return Optional.empty();
    }
}

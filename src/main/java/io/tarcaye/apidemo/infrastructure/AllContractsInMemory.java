package io.tarcaye.apidemo.infrastructure;

import io.tarcaye.apidemo.domain.model.AllContracts;
import io.tarcaye.apidemo.domain.model.Contract;
import io.tarcaye.apidemo.domain.model.Contracts;
import io.tarcaye.apidemo.domain.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Repository
public class AllContractsInMemory implements AllContracts {

    private final List<Contract> contracts;

    public AllContractsInMemory() {
        Customer customer = Customer.of("9001");
        contracts = new ArrayList<>(asList(new Contract(customer, 1L, Contract.Type.ACHAT), new Contract(customer, 2L, Contract.Type.ACHAT)));
    }

    @Override
    public Optional<Contracts> findFor(Customer customer) {
        List<Contract> contracts = this.contracts.stream().filter(it -> it.belongsTo(customer)).collect(Collectors.toList());
        if (contracts.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new Contracts(contracts));
    }


    @Override
    public void add(Contract contract) {
        contracts.add(contract);
    }

}

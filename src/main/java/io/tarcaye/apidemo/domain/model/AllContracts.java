package io.tarcaye.apidemo.domain.model;

import java.util.Optional;

public interface AllContracts {
    Optional<Contracts> findFor(Customer customer);

    void add(Contract contract);
}

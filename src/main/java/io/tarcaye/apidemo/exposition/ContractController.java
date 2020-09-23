package io.tarcaye.apidemo.exposition;

import io.tarcaye.apidemo.domain.model.AllContracts;
import io.tarcaye.apidemo.domain.model.Contract;
import io.tarcaye.apidemo.domain.model.Customer;
import io.tarcaye.apidemo.exposition.model.ContractRequest;
import io.tarcaye.apidemo.exposition.model.ContractsResponse;
import io.tarcaye.apidemo.exposition.model.ContractsResponse.ContractResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractController {

    private final AllContracts allContracts;

    public ContractController(AllContracts allContracts) {
        this.allContracts = allContracts;
    }

    @GetMapping("/customer/{customerId}/contracts")
    ContractsResponse all(@PathVariable String customerId) {
        return allContracts
                .findFor(Customer.of(customerId))
                .map(ContractsResponse::of)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @PostMapping("/customer/{customerId}/contracts")
    ContractResponse add(@PathVariable String customerId, ContractRequest contractRequest) {
        Contract contract = new Contract(Customer.of(customerId));
        allContracts.add(contract);
        return ContractResponse.from(contract);
    }
}

package io.tarcaye.apidemo.exposition;

import io.tarcaye.apidemo.domain.model.AllContracts;
import io.tarcaye.apidemo.domain.model.Contract;
import io.tarcaye.apidemo.domain.model.Customer;
import io.tarcaye.apidemo.exposition.model.ContractRequest;
import io.tarcaye.apidemo.exposition.model.ContractsResponse;
import io.tarcaye.apidemo.exposition.model.ContractsResponse.ContractResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContractController {

    private final AllContracts allContracts;

    public ContractController(AllContracts allContracts) {
        this.allContracts = allContracts;
    }

    @GetMapping("/customers/{customerId}/contracts")
    ContractsResponse all(@PathVariable String customerId) {
        return allContracts
                .findFor(Customer.of(customerId))
                .map(ContractsResponse::of)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @PostMapping("/customers/{customerId}/contracts")
    ContractResponse add(@PathVariable String customerId, @RequestBody ContractRequest contractRequest) {
        Contract contract = contractRequest.toCore(customerId);
        allContracts.add(contract);
        return ContractResponse.from(contract);
    }
}

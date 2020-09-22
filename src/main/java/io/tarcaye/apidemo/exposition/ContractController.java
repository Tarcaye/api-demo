package io.tarcaye.apidemo.exposition;

import io.tarcaye.apidemo.domain.model.AllContracts;
import io.tarcaye.apidemo.exposition.model.ContractsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractController {

    private final AllContracts allContracts;

    public ContractController(AllContracts allContracts) {
        this.allContracts = allContracts;
    }

    @GetMapping("/customer/{customerId}/contracts")
    ContractsResponse all(@PathVariable String customerId) {
        return allContracts.findFor(customerId).map(ContractsResponse::of)
                .orElseThrow(CustomerNotFoundException::new);
    }
}

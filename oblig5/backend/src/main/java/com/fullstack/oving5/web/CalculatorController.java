package com.fullstack.oving5.web;


import com.fullstack.oving5.dto.Computation;
import com.fullstack.oving5.service.CalculatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/compute/")
public class CalculatorController {


    private CalculatorService calculatorService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Double compute(@RequestBody Computation computation) {
        log.debug(computation.getCurrent() + " " + computation.getOperator() + " " + computation.getNumber());
        return calculatorService.calculate(computation);
    }
}

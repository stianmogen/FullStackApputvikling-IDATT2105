package com.fullstack.oving5.service;

import com.fullstack.oving5.dto.Computation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@NoArgsConstructor
@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public Double calculate(Computation computation) {
        return computation.getOperator()
                .operation.operate(computation.getCurrent(), computation.getNumber());
    }

    public enum Operator {
        ADDITION(Double::sum),
        SUBTRACTION((a, b) -> a - b),
        MULTIPLICATION((a, b) -> a * b),
        DIVISION((a, b) -> a / b);

        private final DoubleMath operation;

        Operator(DoubleMath operation) {
            this.operation = operation;
        }
    }

    interface DoubleMath {
        Double operate(double a, double b);
    }

}

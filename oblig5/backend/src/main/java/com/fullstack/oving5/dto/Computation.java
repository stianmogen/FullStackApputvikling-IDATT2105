package com.fullstack.oving5.dto;

import com.fullstack.oving5.service.CalculatorServiceImpl;
import lombok.Data;

@Data
public class Computation {
    Double current;
    CalculatorServiceImpl.Operator operator;
    Double number;
}

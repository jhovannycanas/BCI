package com.bci.control;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    String mensaje;
}

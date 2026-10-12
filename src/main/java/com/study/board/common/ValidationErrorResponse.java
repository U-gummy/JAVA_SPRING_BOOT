package com.study.board.common;

import java.util.List;

public record ValidationErrorResponse(String message, List<FieldErrorResponse> errors) {
}

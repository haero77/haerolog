package com.haerolog.global.error;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

/**
 *  {
 *   "code": "400",
 *   "message": "잘못된 요청입니다.",
 *   "validation": {
 *     "title": "값을 입력해주세요."
 *   }
 * }
 */
@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    private ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }

    public static ErrorResponse badRequest(List<FieldError> fieldErrors) {
        return ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .validation(toValidation(fieldErrors))
                .build();
    }

    private static Map<String, String> toValidation(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage()
                ));
    }

}

package com.haerolog.global.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
//@JsonInclude(value = Include.NON_EMPTY) // 호돌맨: 비선호. 비어있는 것도 하나의 정보라고 판단할 수 있다.
public class ErrorResponse {

    private final int status;
    private final String code; // 향후를 대비해서 int가 아닌
    private final String message;
    private final Map<String, String> validation;

    @Builder
    private ErrorResponse(int status, String code, String message, Map<String, String> validation) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.validation = validation;
    }

    public static ErrorResponse badRequest(List<FieldError> fieldErrors) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .message("잘못된 요청입니다.")
                .validation(toValidation(fieldErrors))
                .build();
    }

    private static Map<String, String> toValidation(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage
                ));
    }

}

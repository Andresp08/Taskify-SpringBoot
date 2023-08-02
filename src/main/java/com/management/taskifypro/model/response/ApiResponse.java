package com.management.taskifypro.model.response;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "httpHeaders", "httpStatusCode", "message", "data", "otherParams" })
public class ApiResponse<Entity> {
    
    private final HttpHeaders httpHeaders;
    private final int httpStatusCode;
    private final String message;
    private final Entity data;
    private final Map<String, Object> otherParams;

    private ApiResponse(ApiResponseBuilder builder) {
        this.httpHeaders = builder.httpHeaders;
        this.httpStatusCode = builder.httpStatusCode;
        this.message = builder.message;
        this.data = (Entity) builder.data;
        this.otherParams = builder.otherParams;
    }

    public static class ApiResponseBuilder<Entity> {

        private HttpHeaders httpHeaders = new HttpHeaders();
        private final int httpStatusCode;
        private final String message;
        private Entity data;
        private Map<String, Object> otherParams = Collections.emptyMap();

        public ApiResponseBuilder(int httpStatusCode, String message) {
            this.httpStatusCode = httpStatusCode;
            this.message = message;
        }

        public ApiResponseBuilder<Entity> withHttpHeader(HttpHeaders httpHeader) {
            this.httpHeaders = httpHeader;
            return this;
        }

        public ApiResponseBuilder<Entity> withData(Entity data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder<Entity> withOtherParams(Map<String, Object> otherParams) {
            this.otherParams = otherParams;
            return this;
        }

        public ResponseEntity<ApiResponse> build() {
            ApiResponse<Entity> apiResponse = new ApiResponse<>(this);
            return ResponseEntity.status(apiResponse.getHttpStatusCode()).headers(apiResponse.getHttpHeaders()).body(apiResponse);
        }
    }
}

package com.example.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    String message ;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T payload  ;

    public ApiResponse(T payload) {
        this.payload = payload;
    }

    public ApiResponse(String message) {
        this.message = message;
    }
}

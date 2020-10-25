package com.alwin.jpa.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserOnlyNameEmailDto {
    private String name,email;
}

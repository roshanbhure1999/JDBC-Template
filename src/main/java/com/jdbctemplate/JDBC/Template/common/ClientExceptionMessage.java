package com.jdbctemplate.JDBC.Template.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
public class ClientExceptionMessage {
   private HttpStatus status;
   private String message;
   private String source;
}

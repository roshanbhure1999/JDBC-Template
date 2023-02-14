package com.jdbctemplate.JDBC.Template.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ExceptionMessage {
    private HttpStatus exceptionClientStatus;
    private String exceptionServerCode;
    private String exceptionMessage;
    private String exceptionMessageDetail;
    private String exceptionMessageInformation;
    private Throwable rootCause;
    private String exceptionSource;
    private String source;
    private String errorLevel = "ERROR_LEVEL_IS_MAJOR___BY_DEFAULT___THIS_ERROR_LEVEL_NEEDS_TO_SET_BY_DEVELOPER_TO_EITHER___MAJOR_AND_ABOVE___OR___MAJOR_AND_BELOW_ERROR_LEVEL_CATEGORY";
}

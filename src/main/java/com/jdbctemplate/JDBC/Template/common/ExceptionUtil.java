package com.jdbctemplate.JDBC.Template.common;

import com.sunrise.wincash.payment.util.ApplicationConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class ExceptionUtil {
    public static SunriseWincashException createSunriseWincashException
            (HttpStatus exceptionClientStatus, String exceptionServerCode,
             String exceptionMessage,
             String exceptionSource, Throwable rootCause, String source, String level
            ) {
        return new SunriseWincashException(ExceptionMessage.builder()
                .exceptionClientStatus(exceptionClientStatus)
                .exceptionServerCode(exceptionServerCode)
                .exceptionMessage(exceptionMessage)
                .exceptionMessageDetail(rootCause.getMessage())
                .exceptionMessageInformation(exceptionMessage)
                .exceptionSource(exceptionSource)
                .rootCause(rootCause)
                .source(source)
                .errorLevel(level).build());
    }

    public static ClientExceptionMessage getClientException(HttpStatus exceptionMessage,
                                                            String message, String source) {
        return ClientExceptionMessage.builder().status(exceptionMessage)
                .message(message).source(source).build();
    }

    public static ClientExceptionMessage getClientException(SunriseWincashException sunriseWincashException) {
        if (Objects.nonNull(sunriseWincashException) && Objects.nonNull(sunriseWincashException.getExceptionMessage())) {
            ExceptionMessage exceptionMessage = sunriseWincashException.getExceptionMessage();
            return ClientExceptionMessage.builder().status(exceptionMessage.getExceptionClientStatus())
                    .message(exceptionMessage.getExceptionMessage()).source(exceptionMessage.getExceptionSource()).build();
        } else {
            return ClientExceptionMessage.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Internal Server Error").source(ApplicationConstant.SOURCE).build();
        }
    }

    public static ClientExceptionMessage getClientException(Exception exception) {
        return ClientExceptionMessage.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Internal Server Error").source(ApplicationConstant.SOURCE).build();

    }
//TODO: replace source with error.getmethod,classname
    public static void logException(Exception exception, String source) {
        if (exception instanceof SunriseWincashException) {
            //log.error("rootCause.getStackTrace()[0].getClassName() :: {}"+exception.getCause().getStackTrace()[0].getClassName());
            SunriseWincashException sunriseWincashException = (SunriseWincashException) exception;
            ExceptionMessage exceptionMessage = sunriseWincashException.getExceptionMessage();
            log.error("\n \n Getting SunriseWincashException :: " +
                            "\n exceptionClientStatus : {} :: " +
                            "\n exceptionServerCode : {} ::" +
                            "\n exceptionMessageDetail : {} :: " +
                            "\n rootCause : {} :: " +
                            "\n exceptionSource : {} " +
                            "\n errorLevel : {} " +
                            "\n Source : {} \n \n ",
                    exceptionMessage.getExceptionClientStatus(),
                    exceptionMessage.getExceptionServerCode(),
                    exceptionMessage.getExceptionMessageDetail(),
                    exceptionMessage.getRootCause(),
                    exceptionMessage.getExceptionSource(),
                    exceptionMessage.getErrorLevel(),
                    exceptionMessage.getSource()
                    );
            log.error("Stack-Trace ::", sunriseWincashException);

        } else {
            log.error("\n\nGetting Exception :: EmployeeController-getById :: " +
                            "\nexceptionClientStatus : {} :: " +
                            "\nexceptionServerCode : {} ::" +
                            "\nexceptionMessageDetail : {} :: " +
                            "\nexceptionSource : {} " +
                            "\nSource : {} \n\n",
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.value(),
                    "Internal Server Exception",
                    ApplicationConstant.SOURCE,
                    source);
            log.error("Stack-Trace ::", exception.getStackTrace() + "\n\n");

        }

    }
}


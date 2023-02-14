package com.jdbctemplate.JDBC.Template.common;

public class SunriseWincashException extends Exception{
  private final ExceptionMessage exceptionMessage;


    public SunriseWincashException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getExceptionMessage());
        this.exceptionMessage = exceptionMessage;

    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}

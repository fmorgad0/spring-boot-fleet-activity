package com.examples.springbootfleetactivity.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Class used to return error information on unsuccessful requests
 */
@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {

  private Date timestamp;
  private String message;
  private String details;

}
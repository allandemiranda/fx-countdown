package br.allandemiranda.fx.robot.exception.impl;

import br.allandemiranda.fx.robot.exception.ConflictException;

/**
 * Exception thrown when an Expert Advisor already exists.
 */
public class ExpertAdvisorConflictException extends ConflictException {

  /**
   * Constructs a new ExpertAdvisorConflictException with the specified error message.
   *
   * @param message detail message
   */
  public ExpertAdvisorConflictException(String message) {
    super(message);
  }
}

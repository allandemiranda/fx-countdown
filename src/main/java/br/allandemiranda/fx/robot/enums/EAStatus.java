package br.allandemiranda.fx.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * Lifecycle status state machine for an Expert Advisor.
 */
@NullMarked
@Getter
@AllArgsConstructor
public enum EAStatus {

  CREATED("CREATED"),
  VALIDATING_SCOPE("VALIDATING_SCOPE"),
  VALIDATING_SCOPE_COMPLETE("VALIDATING_SCOPE_COMPLETE"),
  VALIDATING_SCOPE_ERROR("VALIDATING_SCOPE_ERROR"),
  BUILDING_START("BUILDING_START"),
  BUILDING_ERROR("BUILDING_ERROR"),
  READY_TO_USE("READY_TO_USE");

  private final String value;
}

package br.allandemiranda.fx.robot.enums;

import org.springframework.context.annotation.Description;

@Description("ENUM_DEAL_REASON")
public enum DealReason {
  DEAL_REASON_SL,               // The deal was executed as a result of Stop Loss activation
  DEAL_REASON_TP,               // The deal was executed as a result of Take Profit activation
  DEAL_REASON_ROLLOVER,         // The deal was executed due to a rollover
}

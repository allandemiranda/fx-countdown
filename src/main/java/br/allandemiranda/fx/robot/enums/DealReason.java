package br.allandemiranda.fx.robot.enums;

import org.springframework.context.annotation.Description;

/**
 * The reason for deal execution is contained in the DEAL_REASON property. A deal can be executed as a result of triggering of an order placed from a mobile application or an MQL5 program, as well as a result of the StopOut event, variation margin calculation, etc. Possible values of DEAL_REASON are
 * described in the ENUM_DEAL_REASON enumeration. For non-trading deals resulting from balance, credit, commission and other operations, DEAL_REASON_CLIENT is indicated as the reason.
 */
@Description("ENUM_APPLIED_PRICE")
public enum DealReason {
  DEAL_REASON_CLIENT,           // The deal was executed as a result of activation of an order placed from a desktop terminal
  DEAL_REASON_MOBILE,           // The deal was executed as a result of activation of an order placed from a mobile application
  DEAL_REASON_WEB,              // The deal was executed as a result of activation of an order placed from the web platform
  DEAL_REASON_EXPERT,           //  The deal was executed as a result of activation of an order placed from an MQL5 program, i.e. an Expert Advisor or a script
  DEAL_REASON_SL,               // The deal was executed as a result of Stop Loss activation
  DEAL_REASON_TP,               // The deal was executed as a result of Take Profit activation
  DEAL_REASON_SO,               // The deal was executed as a result of the Stop Out event
  DEAL_REASON_ROLLOVER,         // The deal was executed due to a rollover
  DEAL_REASON_VMARGIN,          // The deal was executed after charging the variation margin
  DEAL_REASON_SPLIT,            // The deal was executed after the split (price reduction) of an instrument, which had an open position during split announcement
  DEAL_REASON_CORPORATE_ACTION, // The deal was executed as a result of a corporate action: merging or renaming a security, transferring a client to another account, etc.
}

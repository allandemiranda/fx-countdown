# Algorithmic Trading & Expert Advisor Builder API

A RESTful API for financial market data management, technical indicator computation/storage, and automated trading robot (**Expert Advisor**) generation combining quantitative analysis (**GARCH**) and Machine Learning (**XGBoost**).

---

<!-- TOC -->
* [Algorithmic Trading & Expert Advisor Builder API](#algorithmic-trading--expert-advisor-builder-api)
  * [Overview](#overview)
  * [System Workflow](#system-workflow)
  * [Quick Start](#quick-start)
  * [API Endpoints](#api-endpoints)
    * [1. Symbols & Market Data](#1-symbols--market-data)
      * [**Symbols**](#symbols)
      * [**Candlesticks & Ticks**](#candlesticks--ticks)
      * [**Timeframes**](#timeframes)
    * [2. Expert Advisors (Trading Robots)](#2-expert-advisors-trading-robots)
      * [**EA Management**](#ea-management)
      * [**Building & Training Pipeline**](#building--training-pipeline)
      * [**Orders**](#orders)
    * [3. Indicator Data per EA](#3-indicator-data-per-ea)
  * [Step-by-Step EA Generation Guide](#step-by-step-ea-generation-guide)
    * [Part 1 - Populating Database with Chart Data](#part-1---populating-database-with-chart-data)
    * [Part 2 - Injecting Configuration and Indicator Data](#part-2---injecting-configuration-and-indicator-data)
    * [Part 3 - Generating Trading Scenarios and Training the ML Model](#part-3---generating-trading-scenarios-and-training-the-ml-model)
      * [Processing Steps:](#processing-steps)
    * [Part 4 - Cleaning Temporary Data](#part-4---cleaning-temporary-data)
    * [(Extra) Part 5 - Re-analyzing Indicators & Retraining the Model](#extra-part-5---re-analyzing-indicators--retraining-the-model)
<!-- TOC -->

---

## Overview

This API provides a complete infrastructure for quantitative trading pipelines:
* **Market Data & Asset Management:** Registration of financial symbols (e.g., `EURUSD`), candlestick series, and tickEntity data.
* **Technical Indicators:** Storage and querying of technical indicator calculations including RSI, MACD, Stochastic, Bollinger Bands, ATR, ADX, and Moving Averages.
* **Expert Advisor (EA) Builder:** EA configuration, temporal scope validation, data cleaning/reset, GARCH scenario generation, and XGBoost machine learning model training.

---

## System Workflow

The system's operation is based on predictions generated from quantitative analysis and ML models.

- In MT5, the script detects when a new `CandlestickValidate` is closed/created and collects all necessary data to send to the system.
- MT5 sends a batch of `candlesticks`, `indicators`, and `ticks`.
- The system computes additional `indicators` (if applicable) and feeds this dataset into the ML model to generate separate predictions for Buy and Sell operations.
- If a prediction meets the EA's configured margin threshold, the system calculates `TP` (Take Profit) and `SL` (Stop Loss) prices for the order.
- The system returns an array of operations (a Buy or Sell order, both, or no operation) along with their expected `TP` and `SL` levels.

> **Note:** The EA status must be `READY_TO_USE` to generate predictions and provide the green light to open a trade.

---

## Quick Start

* **Default Base URL:** `http://localhost:8080`
* **OpenAPI Version:** 3.1.0

---

## API Endpoints

> **Note:** We don't have autenticatino implemented on the API, all endpints is open.

### 1. Symbols & Market Data

#### **Symbols**
* `GET /symbols` — List all registered symbols.
* `POST /symbols` — Register a new symbolEntity or update an existing one.
* `GET /symbols/{name}` — Retrieve details for a specific symbolEntity (e.g., `EURUSD`).

#### **Candlesticks & Ticks**
* `POST /symbols/{symbolName}/candlesticks/{timeframe}` — Ingest candlestick history for a given symbolEntity and timeframe, or update existing records.
* `POST /symbols/{name}/ticks` — Ingest tickEntity-level price updates, or update existing records.

#### **Timeframes**
* `GET /timeframes` — Get all supported timeframe identifiers.

---

### 2. Expert Advisors (Trading Robots)

#### **EA Management**
* `GET /symbols/{symbolName}/chart/{timeframe}/expert_advisors` — List all EAs associated with a specific chart setup.
* `POST /symbols/{symbolName}/chart/{timeframe}/expert_advisors` — Create a new EA with risk parameters, evaluation scope, indicator specs, and ML configurations for a specific chart.
* `GET /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}` — Retrieve details of a specific EA.
* `PATCH /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}` — Update the EA description and other optional parameters.
* `DELETE /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}` — Delete an EA and all associated input data.

#### **Building & Training Pipeline**
* `POST /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/validate_scope` — Validate the historical time range scope for the EA.
* `POST /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/clean_build` — Clean input and indicator datasets for the EA.
* `POST /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/generate_xgboost` — Trigger XGBoost model training for the EA.

#### **Orders**
* `POST /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/order` — Validate incoming market data and return an open order recommendation if criteria are met.

---

### 3. [Indicator](https://www.mql5.com/en/docs/indicators) Data per EA

Endpoints to query and push calculated indicator series for a specific EA (`{name}`):

| Indicator                | Query (`GET`)                                                                                    | Insert (`POST`)                                                                                 |
|:-------------------------|:-------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------|
| **RSI**                  | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/rsis`        | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/rsis`       |
| **MACD**                 | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/macds`       | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/macds`      |
| **Stochastic**           | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/stochastics` | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/stochastics`|
| **Bollinger Bands**      | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/bandss`      | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/bandss`     |
| **Fast Moving Average**  | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/ma_fasts`    | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/ma_fasts`   |
| **Slow Moving Average**  | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/ma_slows`    | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/ma_slows`   |
| **ATR**                  | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/atrs`        | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/atrs`       |
| **ADX**                  | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/adxs`        | `/symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/adxs`       |

---

## Step-by-Step EA Generation Guide

### Part 1 - Populating Database with Chart Data

When you want to create a new EA or retrain the XGBoost model, it is best to do so over the weekend when the market is closed. Use this step to sync all historical data required to run the analysis and train the ML model over the desired scope.

The MT5 script selects a collection of [Symbols](https://www.mql5.com/en/docs/marketinformation) and [Timeframes](https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes) to populate the database.

- The MT5 script calls the [Symbol](https://www.mql5.com/en/docs/marketinformation/symbolselect) endpoint to register a new symbolEntity or update it if it already exists:

```markdown
MT5 → POST HTTP /symbols
{
    name:       string,
    point:      double, // [SYMBOL_POINT](https://www.mql5.com/en/docs/constants/environment_state/marketinfoconstants#enum_symbol_info_double) — Symbol point value
    swapLong:   double, // [SYMBOL_SWAP_LONG](https://www.mql5.com/en/docs/constants/environment_state/marketinfoconstants#enum_symbol_info_double) — Long swap value
    swapShort:  double  // [SYMBOL_SWAP_SHORT](https://www.mql5.com/en/docs/constants/environment_state/marketinfoconstants#enum_symbol_info_double) — Short swap value
}
```

- MT5 then sends all available [Candlesticks](https://www.mql5.com/en/docs/series/ibars) for the chart, one by one:

```markdown
MT5 → POST HTTP /symbols/{symbolName}/candlesticks/{timeframe}
{
  timestamp:  datetime,
  open:       double,   // [MODE_OPEN](https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode) — Opening price
  low:        double,   // [MODE_LOW](https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode) — Low price
  high:       double,   // [MODE_HIGH](https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode) — High price
  close:      double    // [MODE_CLOSE](https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode) — Closing price
}
```

- Next, MT5 sends all available [Ticks](https://www.mql5.com/en/docs/constants/structures/mqltick) for the Symbol, one by one:

```markdown
MT5 → POST HTTP /symbols/{name}/ticks
{
  timestamp:  datetime,
  ask:        double,
  bid:        double
}
```
*Note: MT5 must always provide valid Bid and Ask quotes.*

---

### Part 2 - Injecting Configuration and Indicator Data

Now you can create the new EA. If you want to use a different parameter configuration for an existing EA, you will need to register it under a new name (e.g., appending `_v2`).

- Configure the analysis scope, indicators, GARCH, risk, and ML parameters:

```markdown
MT5 → POST HTTP /symbols/{symbolName}/chart/{timeframe}/expert_advisors
{
    name:           string, // Name of the new EA
    description:    string, // Short description
    garch: {
        horizon:    int,    // Forecast horizon
        priceSize:  int     // Sample/window size
    },
    iadx: {                 // [https://www.mql5.com/en/docs/indicators/iadx](https://www.mql5.com/en/docs/indicators/iadx)
        period: int
    },
    iatr: {                 // [https://www.mql5.com/en/docs/indicators/iatr](https://www.mql5.com/en/docs/indicators/iatr)
        period: int
    },
    ibands: {               // [https://www.mql5.com/en/docs/indicators/ibands](https://www.mql5.com/en/docs/indicators/ibands)
        period:     int,
        shift:      int,
        deviations: double,
        applyTo:    string  // [ENUM_APPLIED_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_applied_price_enum)
    },
    imacd: {                // https://www.mql5.com/en/docs/indicators/imacd
        fastEma: int,
        slowEma: int,
        macdSma: int,
        applyTo: string     // [ENUM_APPLIED_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_applied_price_enum)
    },
    imaFast: {              // https://www.mql5.com/en/docs/indicators/ima
        period:     int,
        shift:      int,
        method:     string, // [ENUM_MA_METHOD](https://mql5.com/en/docs/constants/indicatorconstants/enum_ma_method)
        applyTo:    string  // [ENUM_APPLIED_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_applied_price_enum)
    },
    imaSlow: {              // https://www.mql5.com/en/docs/indicators/ima
        period:     int,
        shift:      int,
        method:     string, // [ENUM_MA_METHOD](https://mql5.com/en/docs/constants/indicatorconstants/enum_ma_method)
        applyTo:    string  // [ENUM_APPLIED_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_applied_price_enum)
    },
    irsi: {                 // [https://www.mql5.com/en/docs/indicators/irsi](https://www.mql5.com/en/docs/indicators/irsi)
        period:     int,
        applyTo:    string  // [ENUM_APPLIED_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_applied_price_enum)
    },
    iStochastic: {          // https://www.mql5.com/en/docs/indicators/istochastic
        kPeriod:    int,
        dPeriod:    int,
        slowing:    int,
        method:     string, // [ENUM_MA_METHOD](http://mql5.com/en/docs/constants/indicatorconstants/enum_ma_method)
        priceField: string  // [ENUM_STO_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_sto_price_enum)
    },
    priceRiskLevel: {       // 1.5 → aggressive | 2.0 → standard | 2.5 → conservative | 3.0 → very conservative
        kTP: double,
        kSL: double
    },
    scope: {
        startScope: datetime,   // Start time of analysis
        endScope:   datetime    // End time of analysis (exclusive)
    },
    xgBoost: {                          // [https://xgboost.readthedocs.io/en/stable/jvm/index.html](https://xgboost.readthedocs.io/en/stable/jvm/index.html)
        horizon:                int,    // Number of future indicator periods used for prediction
        maxDepth:               int,    // Maximum tree depth
        eta:                    float,  // Learning rate
        subsample:              float,  // Training sample ratio per tree
        colSampleByTree:        float,  // Feature subsample ratio per tree
        minChildWeight:         int,    // Minimum sum of instance weight in a child node
        lambda:                 float,  // L2 regularization term
        alpha:                  float,  // L1 regularization term
        rounds:                 int,    // Number of boosting rounds (trees)
        minimalLevelAccepted:   double  // Minimum prediction probability threshold to trigger an open position
    }
}
```

After creating the EA, populate the [indicators](https://www.mql5.com/en/docs/indicators) data. Refer to [Indicator Data per EA](#3-indicator-data-per-ea-) for individual `POST` endpoints. All requests follow a similar schema:

```markdown
MT5 → POST HTTP /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/{indicator_name}s
{
    timestamp:      datetime,
    "paramName":    double,
    ...             // Additional indicator parameters as needed
}
```

Upon creation, the EA's status is set to `CREATED`. This unlocks the scope validation step, which checks data availability and adjusts the scope if the existing data does not cover the requested time window. (EAs in the `READY_TO_USE` status can also run `validate_scope`).

```markdown
CLIENT → POST HTTP /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/validate_scope
```

When validation begins, the status changes to `VALIDATING_SCOPE`. If an error occurs, it is updated to `VALIDATING_SCOPE_ERROR`; upon successful validation/adjustment, it transitions to `VALIDATING_SCOPE_COMPLETE`.

---

### Part 3 - Generating Trading Scenarios and Training the ML Model

Once all data has been inserted and validated, trigger the endpoint to generate forecasts, simulate trading scenarios, and train the ML model:

```markdown
CLIENT → POST HTTP /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/generate_xgboost
```

> **Note:** The endpoint responds immediately with `204 No Content` because processing is executed asynchronously as a background batch job.

The job only starts if the EA status is `VALIDATING_SCOPE_COMPLETE`; otherwise, an error code is returned. Once started, the status switches to `BUILDING_START` (case [Part 5](#extra-part-5---re-analyzing-indicators--retraining-the-model)).

#### Processing Steps:
1. **GARCH Forecasts:**
  * Calculated using the EA's `horizon` and `priceSize` parameters.
  * Produces `omega`, `alpha`, `beta`, and `sigmaAgg`.
  * Evaluated separately for Ask and Bid series (since MT5 does not natively provide Ask candlesticks, the system constructs them from tickEntity history).
  * `sigmaAgg` is used to calculate TP and SL levels in the Risk step; `omega`, `alpha`, and `beta` are kept as features for ML training.
2. **Trading Simulation:**
  * Using TP/SL levels, the system simulates trades across the historical scope and records outcomes: `DEAL_REASON_SL`, `DEAL_REASON_TP`, or `DEAL_REASON_ROLLOVER` (based on [ENUM_DEAL_REASON](https://www.mql5.com/en/docs/constants/tradingconstants/dealproperties)).
  * Open trades (`null` deal reason) are excluded from training data.
3. **DMatrix Dataset Generation:**
  * Regressive lookback windows are built using the `xgBoost.horizon` setting (e.g., a horizon of 3 uses the current candlestick/indicators plus the prior 2).
  * Datasets are formatted and written to: `/{EA_name}_{BUY or SELL}_{symbolName}_{TIMEFRAME}.libsvm` (configured under `application.yaml`).
  * Buy and Sell datasets are processed in parallel threads while writing sequentially to their respective files.
  * Status transitions to `BUILDING_COMPLETE` on success, or `BUILDING_ERROR` on failure.
4. **Model Training:**
  * If `BUILDING_COMPLETE`, status updates to `GENERATING_ML_MODEL`.
  * XGBoost trains models for Buy and Sell targets using the configured hyperparameters.
  * The trained models are exported as JSON: `/{EA_name}_{BUY or SELL}_{symbolName}_{TIMEFRAME}.json`.
  * Upon successful completion, the status is set to `READY_TO_USE` (or `GENERATING_ML_MODEL_ERROR` if training fails).

> **Note:** Any pre-existing files `libsvm` and `json` with this pattern are deleted before start this process steps

---

### Part 4 - Cleaning Temporary Data

Once training completes, temporary indicator records can be cleaned up to free resources for future analysis:

```markdown
CLIENT → POST HTTP /symbols/{symbolName}/chart/{timeframe}/expert_advisors/{name}/clean_build
```

---

### (Extra) Part 5 - Re-analyzing Indicators & Retraining the Model

If the model's predictive performance degrades over time due to market regime shifts, you can retrain it over fresh historical data using the existing EA parameters:

1. Update the market data as described in [Part 1](#part-1---populating-database-with-chart-data).
2. Populate the latest indicator data as described in [Part 2](#part-2---injecting-configuration-and-indicator-data). *(Note: If MT5 requests an order evaluation during this stage, the API safely returns an empty payload).*
3. Run scope validation (`validate_scope`).
4. Trigger model re-generation ([Part 3](#part-3---generating-trading-scenarios-and-training-the-ml-model)) and clean up temporary data ([Part 4](#part-4---cleaning-temporary-data)).


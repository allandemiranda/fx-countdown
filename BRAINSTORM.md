## ML FOREX DECISION - COUNTDOWN

This project will be an improvement of https://github.com/allandemiranda/fx-countdown.git , so you can use this project to take information and copy past the code when necessary.

# Description

This is a rest api to generate scenarios to generate an ML base and be able to take marketing decision when necessary. However, I mentioned MT5 (MQL5)[https://www.mql5.com/en/docs/], this software that we will develop now will
focus on a midware of functionalities to manager and take a decision like an Expert Adviser from MT5, that is limited by the language MSQL5.

# Technologies

Java 25, Spring Boot 4, Reactive Model, PostgresSQL, Docker, Junt 5, XGBoost4j, and others technologies that you can check on the pom.

OBS: You can add others technologies or change the technologist if necessary, but you cand abdicate of MT5 (SQL5), java 25, spring boot 4, reactive mode, and docker to run the services.

# Functionalities

OBS:

- This is a close local sistem, so, don't need care about security or authentication, and all the process circle life will be done step by step.
- Speed, performance, and best architecture for the business of this project need be included on this project.
- Remember that the MT5 always use the server location data time, and we can not be on the same time zone, take care when you be manipulating this data.
- Logs on the system need be added in all process, and implemented a technology to show in the bast way, including the perception of this project that have 4 different micro business area.

## Part 1 - Populating database charts available

The MT5 has a Script that should select a collection of Symbols (https://www.mql5.com/en/docs/marketinformation) to create all charts for all Timeframes available on the MT5
(https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes).

- The MT5 script will call the endpoint Symbol for create the new Symbol on the system (https://www.mql5.com/en/docs/marketinformation/symbolselect):

```web-request
MT5 → POST HTTP api/symbols/{name}
{
    point:      double  [SYMBOL_POINT](Symbol point value)(https://www.mql5.com/en/docs/constants/environment_state/marketinfoconstants#enum_symbol_info_double),
    swapLong:   double  [SYMBOL_SWAP_LONG](Long swap value)(https://www.mql5.com/en/docs/constants/environment_state/marketinfoconstants#enum_symbol_info_double),
    swapShort:  double  [SYMBOL_SWAP_SHORT](Short swap value)(https://www.mql5.com/en/docs/constants/environment_state/marketinfoconstants#enum_symbol_info_double)
}
```

- After MT5 will prepare to send all the Candlestick (https://www.mql5.com/en/docs/series/ibars) disposable for the Chart, one per one:

```web-request
MT5 → POST HTTP api/symbols/{name}/charts/{timeframe}
{
  timestamp:  datetime,
  open:       double  [MODE_OPEN](Opening price)(https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode),
  low:        double  [MODE_LOW](Low price)(https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode),
  high:       double  [MODE_HIGH](High price)(https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode),
  close:      double  [MODE_CLOSE](Close price)(https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode)
}
```

- After MT5 will prepare all tick form the Symbol (https://www.mql5.com/en/docs/constants/structures/mqltick) disposable, one per one:

```web-request
MT5 → POST HTTP api/symbols/{name}/ticks
{
  timestamp:  datetime,
  ask:        double,
  bid:        double
}
```

Always the MT5 will provide a price with bid and ask valid.

OBS:

- In these endpoints, if the object (Symbol, Candlestick, Tick) already exist, just update the data.
- We expected that this data will be updated when necessery for keeping the fulture analisis more precice;
- This Part 1 can be skipped if we make sure that the data is updated and available for the period to be analyzed in the following sections;
- Never the client will call these Part 1 to update data until any analysis fase be ongoing;

## Part 2 - Injecting a configuration and indicator data to be evaluated

In this part the MT5 will provide a data necessary for create the scenarios and build an ML, using other MT5 script. This data need be identifier unic, and no more unbased in Symbol or Chart (like on the objects from Part 1).

- The MT5 should provide an input configuration of scope analysis, indications, Garch, Risk, and ML build:

```web-request
MT5 → POST HTTP api/dashboards
{
    chart: {
      symbol:     string (symbol_name),
      timeframe:  string [ENUM_TIMEFRAMES](https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes)
    },    
    mlConfig: {
        scope: {
          start:  datetime,
          end:    datetime,
        },    
        garch: {
          horizon:    double,
          priceSize:  double 
        },
        riskLevel: {
          kTP:      double,
          kSL:      double
        },
        xgboost4j: { -> https://github.com/allandemiranda/fx-trader/blob/main/src/main/java/br/allandemiranda/fx/share/XgbTrainer.java
          horizon: int, ---> nunca maior ou igual a garch.horizon
          maxDepth: int,
          eta: float,
          subsample: float,
          colSampleByTree: float,
          minChildWeight: int,
          lambdaL2: float,
          alphaL1: float,
        },
        minimalLevelAccepted: double
    },
    indicators: {
      iAdx: {
        adxPeriod:  int
      },
      iAtr: {
        maPeriod: int
      },
      iBands: {
        bandsPeriod:  int,
        bandsShift:   int,
        deviation:    double,
        appliedPrice: string [ENUM_APPLIED_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_applied_price_enum)
      },
      iMacd: {
        fastEmaPeriod:  int,
        slowEmaPeriod:  int,
        signalPeriod:   int,
        appliedPrice:   string [ENUM_APPLIED_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_applied_price_enum)
      },
      iMa: {
        slow: {
          maPeriod:     int,
          maShift:      int,
          maMethod:     string [ENUM_MA_METHOD](http://mql5.com/en/docs/constants/indicatorconstants/enum_ma_method),
          appliedPrice: string [ENUM_APPLIED_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_applied_price_enum)
        },
        fast: {
          maPeriod:     int,
          maShift:      int,
          maMethod:     string [ENUM_MA_METHOD](http://mql5.com/en/docs/constants/indicatorconstants/enum_ma_method),
          appliedPrice: string [ENUM_APPLIED_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_applied_price_enum)
        }
      },
      iRsi: {
        maPeriod:     int,
        appliedPrice: string [ENUM_APPLIED_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_applied_price_enum)
      },
      iStochastic: {
        Kperiod:    int,
        Dperiod:    int,
        slowing:    int,
        maMethod:   string [ENUM_MA_METHOD](http://mql5.com/en/docs/constants/indicatorconstants/enum_ma_method),
        priceField: string [ENUM_STO_PRICE](https://www.mql5.com/en/docs/constants/indicatorconstants/prices#enum_sto_price_enum)
      }
    }
}
```

OBS: Generate an error if the chart don't exist on the system

Upon completion of this operation, MT5 will receive the created object, which must contain the object's ID (UUID). This UUID will be necessary to mark the indicators received by MT5, and subsequently to link them with data
generated during the analysis process. At this moment the Dashboard change the status of this analysis to waiting for data input.

List of indicators that we will use on the system (https://www.mql5.com/en/docs/indicators):

- ADX (Average Directional Index)
- ATR (Average True Range)
- BANDS (Bollinger Bands®)
- MACD (MACD)
- MA (Moving Average) (Fast and Slow)
- RSI (Relative Strength Index)
- STOCHASTIC (Stochastic Oscillator)

Now the MT5 shude call each indicator (https://www.mql5.com/en/docs/constants/indicatorconstants/lines), one per one that are inside the scope (between start and stop):

- For ADX objects:

```web-request
MT5 → POST HTTP api/dashboards/{uuid}/adxs
{
  timestamp: datetime,
  mainLine: double,
  plusDiLine: double,
  minusDiLine: double
}
```

- For ATR objects:

```web-request
MT5 → POST HTTP api/dashboards/{uuid}/atrs
{
  timestamp: datetime,
  atr:       double
}
```

- For Bands objects:

```web-request
MT5 → POST HTTP api/dashboards/{uuid}/bandss
{
  timestamp:  datetime,
  baseLine:   double,
  upperBand:  double,
  lowerBand:  double
}
```

- For MACD objects:

```web-request
MT5 → POST HTTP api/dashboards/{uuid}/macds
{
  timestamp:    datetime,
  mainLine:     double,
  signalLine:   double
}
```

- For Ma Fast objects:

```web-request
MT5 → POST HTTP api/dashboards/{uuid}/maFasts
{
  timestamp:  datetime,
  ma:         double
}
```

- For Ma Slow objects:

```web-request
MT5 → POST HTTP api/dashboards/{uuid}/maSlows
{
  timestamp:  datetime,
  ma:         double
}
```

- For RSI objects:

```web-request
MT5 → POST HTTP api/dashboards/{uuid}/rsis
{
  timestamp:  datetime,
  rsi:        double
}
```

- For Stochastic objects:

```web-request
MT5 → POST HTTP api/dashboards/{uuid}/stochastics
{
  timestamp:  datetime,
  mainLine:   double,
  signalLine: double
}
```

OBS: Not is expected happening, but if the "POST HTTP api/dashboards/{uuid}/{indicator}s" be call more than one time for the same "timestamp", we just ignor;

After the MT5 send all indicators, the MT5 needs call the scope validation endpoint. This validation will be util to verifi the real scope time, because we can consider a valid scope the time that have all indicators and
candlestick available:

- Check the start datetime:
    - Candlestick have this timestamp? Yes, go for next, No, what is the next available timestamp after the one? Pickup this one and go for the next;
    - Indicator ADX have this timestamp that come for the test before? Yes, go for next, No, what is the next available timestamp after the one ? Pickup this one and go for the next;
    - Indicator ...
    - Indicator Stochastic have this timestamp? come for the test before? Yes, set on Dashboard the new start time, No, what is the next available timestamp after the one? Set on Dashboard the new start time;
    - If the Start time be equals or after End time, we should return an error code for MT5, and set the error validation scope status on Dashboard, and stop the next step.
- Check the end datetime:
    - Candlestick have this timestamp? Yes, go for next, No, what is the previous available timestamp before the one? Pickup this one and go for the next;
    - Indicator ADX have this timestamp that come for the test before? Yes, go for next, No, what is the previous available timestamp before the one? Pickup this one and go for the next;
    - Indicator ...
    - Indicator Stochastic have this timestamp come for the test before? Yes, set on Dashboard the new end time, No, what is the previous available timestamp before the one? Set on Dashboard the new end time;
    - If the Start time be equals or after End time, we should return an error code for MT5, and set the error validation scope status on Dashboard, and stop the next step.
- Validate the mlConfig.garch.priceSize:
    - Validate on the chart if the start to end scope contains more candlesticks that priceSize number, if not, set the error validation scope status on Dashboard, and stop the next step.
    - Validate on the chart if there is equal or more candlesticks before the start scope timestamp that priceSize number, if not, set the error validation scope status on Dashboard, and stop the next step.

```web-request
MT5 → POST HTTP api/dashboards/{uuid}/scope_re_size
```

After the validation, change the status of the Dashboard to ready for generate trading scenarios.

## Part 3 - Generating trading scenarios and ML

Here's the cherry on top. We don't need the MT5 for this part, the call of the endpoints on this part will be done manualy by the user (me). You can see
in https://github.com/allandemiranda/fx-countdown/blob/master/src/main/java/br/allandemiranda/fx/robot/controller/impl/DashboardController.java an old implementation of part of the code that is necessery to develop in this part,
see metodo "Mono<Void> generateML".

- The first goal of this Part 3 is to obtain a Garch Forecast for each candlestick on the chart within the scope;
- You can see the Garch Forecast code logic ready in https://github.com/allandemiranda/fx-countdown/blob/master/src/main/java/br/allandemiranda/fx/robot/util/Garch11ModuleUtils.java
- Garch Forecast contains the "sigmaAgg" value per candlestick, with this data, plus Symbol data, plus kSL and kTP input data, we will calculate the level price TP and SL to a BUY or a SELL operation;
- You can see the Risk Level Calculator code logic ready in https://github.com/allandemiranda/fx-countdown/blob/master/src/main/java/br/allandemiranda/fx/robot/util/RiskLevelCalculatorUtils.java
- With the TP, and SL for BUY and SELL operation, for each candlestick on the scope, we will run the scenarios using the Tick data, applying rollover, and setting the DealReason
  (https://github.com/allandemiranda/fx-countdown/blob/master/src/main/java/br/allandemiranda/fx/robot/enums/DealReason.java) when necessary;
    - The gain will be calculated in points;
    - If the rollover points discount if high that the TP (meas that if the operation win the total of points is zero or negative), the DealReason for swap should be set;
    - After the end of this trading simulation between the scope time, remove the results that didn't close the operation (null DealReason);
- Now with all simulation done, we need create the DMatrix file;
    - For XGBoost4j take all the data generated from the simulation and put directly on the memory, is not a god solution, so I believe that creating a matrix file, and using DMatrix for that, will save memory;
    - The first collum of the file is the MLLabel (https://github.com/allandemiranda/fx-countdown/blob/master/src/main/java/br/allandemiranda/fx/robot/enums/MLLabel.java):
        - To calculate the MLLabel me need check the DealReason of BUY and SELL simulation;
        - If both was TP, we determine the MLLabel for the trading time more short, if they are equals time, set NEUTRAL;
        - If TP are with BUY, set BUY;
        - If TP are with SELL, set SELL;
        - If no one is TP, set NEUTRAL;
    - The others collum are the data from each indicator, Garch Forecast and Candlestick;
        - Transform all data in float, except for Candlestick;
            - Candlestick not will set open, close, high and low data, will calculate the type (neutral 0f, bullish 1f, bearish 2f), upperShadow, lowShadow, and body in points by Symbol;
        - Keep the code logic order of the collum per data save global on the system, because we need use this same order for the next part 4;
        - This file location is a folder that should be defined on the yam properties, and the file name is the uuid of the Dashboard;
- After create the file data input, use the mlConfig.xgboost4j data to set the parameters necessary to generate an ML file;
    - This file location is a folder that should be defined on the yam properties, and the file name is the uuid of the Dashboard plus "_v{version number}";
    - If not exist a file that start with the same uuid, create one with the end "_v1", if exist a file starting with the same uuid, increase the version number, like if was "{uuid}_v1" the next version will be "{uuid}_v2";
    - The version number should be stored in the Dashboard;
- Remember that update the Dashboard status in each step;
- Try to do all this process inside a unic flux, not persisting data and fetching data from the database, with the exception of the files that need create, but on case of DMatrix file;
- This will be an async operation, the HTTP request will return immediately a HttpStatus.ACCEPTED without body answer, but the system will start step by step the full process;

```web-request
MT5 → POST HTTP api/dashboards/{uuid}/generate_ml → HttpStatus.ACCEPTED
```

## Part 4 - Trading real live

Now the MT5 will start an Expert Advisor that will interact with the system, to determinate if is time to open an operation Buy or Sell, or not open an operation.

- The MT5 Expert Advisor will call the system passing the uuid of the Dashboard, and will receive all information from the Dashboard, however not at all data is necessary be storage on the MT5 Expert Advisor.

```web-request
MT5 → GET HTTP api/dashboards/{uuid}
```

- MT5 Expert Advisor will wait a new candlestick open on the chart, when a new one open, the MT5 will call the endpoint of the system passing the full data necessary. The configuration os indicators and the minimal quantity of
  candlesticks and indicators that should send from MT5 to the system on this step, it's in the last step answer.

```web-request
MT5 → GET HTTP api/dashboards/{uuid}/trading
{
    candlestick: [{
      timestamp:  datetime,
      open:       double  [MODE_OPEN](Opening price)(https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode),
      low:        double  [MODE_LOW](Low price)(https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode),
      high:       double  [MODE_HIGH](High price)(https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode),
      close:      double  [MODE_CLOSE](Close price)(https://www.mql5.com/en/docs/constants/chartconstants/enum_timeframes#enum_seriesmode)
    }]
    adx: [{
      timestamp: datetime,
      mainLine: double,
      plusDiLine: double,
      minusDiLine: double
    }],
    atr: [{
      timestamp: datetime,
      atr:       double
    }],
    bands: [{
      timestamp: datetime,
      atr:       double
    }],
    macd: [{
      timestamp:    datetime,
      mainLine:     double,
      signalLine:   double
    }],
    ma: {
      slow: [{
        timestamp:  datetime,
        ma:         double
      }],
      fast: [{
        timestamp:  datetime,
        ma:         double
      }]
    },
    rsi: [{
      timestamp:  datetime,
      rsi:        double
    }],
    stochastic: [{
      timestamp:  datetime,
      mainLine:   double,
      signalLine: double
    }]
}
---
MT5 <-- OK (in case that is to open a operation)
{
  type: string [ENUM_POSITION_TYPE](https://www.mql5.com/en/docs/constants/tradingconstants/positionproperties#enum_position_type),
  tp: string [POSITION_TP](https://www.mql5.com/en/docs/constants/tradingconstants/positionproperties#enum_position_property_double),
  sl: string [POSITION_SL](https://www.mql5.com/en/docs/constants/tradingconstants/positionproperties#enum_position_property_double),
  comment: string [POSITION_COMMENT](https://www.mql5.com/en/docs/constants/tradingconstants/positionproperties#enum_position_property_string)
}
MT5 <-- NO_CONTENT (in case that is not to open a operation)
```

- The system knows the XGBoost4j file, injecting the data, and will generate a percentage of probability for Buy, Sell and Neutral.
- Using the configuration data minimalLevelAccepted that is stored on teh Dashboard, if one Sell or Buy is height that this value, the answer needs to contain the green light to open a position.
- Until received the answer, if not a non-content answer http code, the operation should be executed immediately on the MT5.
- For the comment, needs contains the probability for each operation (buy, sell, neutral) generate by the XGBoost4j, plus the time in seconds to the system generate the answer, plus the uuid of the expertAdvisor.

# Others functionalities to be implemented and explications

All POST HTTP methods should return the object created or updated or the original one for ignored creation, with the exception of endpoints that is async and return ACCEPTED code.

## Implement the deleted

- DELETE HTTP "api/symbols"
    - When you want to delete all symbols on the system;
    - It's a cascade operation for eliminate all database dedicate to generate the scenarios (Part 1 data), all candlesticks and ticks need be deleted too;
    - Remember that Dashboard not will be impacted on the cascade, however have a link between than using the id (on case of Symbol the 'name') to generate the scenarios, in case of the Symbol not be present no more, the Dashboard
      should return an error with the explanation and updating the status;
- DELETE HTTP "api/symbols/{name}"
    - When you want to delete only one symbol on the system;
    - It's a cascade operation for eliminate all charts that use this symbol and the ticks that use this symbol;
    - Remember that Dashboard not will be impacted on the cascade, however have a link between than using the id (on case of Symbol the 'name') to generate the scenarios, in case of the Symbol not be present no more, the Dashboard
      should return an error with the explanation and updating the status;
- DELETE HTTP "api/symbols/{name}/charts"
    - When you want to delete only all Candlesticks on the system;
- DELETE HTTP "api/symbols/{name}/charts/{timeframe}"
    - When you want to delete only Candlesticks that have the Symbol name and timeframe chart request;
- DELETE HTTP "api/symbols/{name}/ticks"
    - When you want to delete all ticks that have the Symbol name;
    - We can add a filter to delete only a ticks by timestamps between two datetimes, like "api/symbols/{name}/ticks?timestampAfter={datetime_start}&timestampBefore={datetime_final}"
- DELETE HTTP "api/dashboards" not allowed (don't developer)
- DELETE HTTP "api/dashboards/{uuid}"
    - When you want to delete all data from a specificities expertAdvisor;
    - Is a cascade mode from dashboards to indicators, deleting all indicators data linked to this expertAdvisor;
    - Delete the XGBoost files and the DMatrix file that have the uuid from this expertAdvisor;
- DELETE HTTP "api/dashboards/{uuid}/{indicator}s" not allowed (don't developer)

## Implement the updated

The unic endpoint that can be updated is "api/dashboards/{uuid}" and the request will update only the mlConfig object plus an information of the new version if applicable.

```web-request
MT5 → PATCH HTTP api/dashboards/{uuid}
{
    scope: {
      start:  datetime,
      end:    datetime,
    },    
    garch: {
      horizon:    double,
      priceSize:  double 
    },
    riskLevel: {
      kTP:      double,
      kSL:      double
    },
    xgboost4j: {
      horizon: int,
      maxDepth: int,
      eta: float,
      subsample: float,
      colSampleByTree: float,
      minChildWeight: int,
      lambdaL2: float,
      alphaL1: float,
    },
    ml: {
      minimalLevelAccepted: double,
      versionNumber: int
    }    
}
```

Remember that this a PATCH operation, no one of the objects on the body are required, but until they have, should be updated on the database, and returned the full expertAdvisor object. At the end of the update of the data, run the
code loging inside the "POST HTTP api/dashboards/{uuid}/scope_re_size" if the scope.start or scope.end was updated, and with the correct scope size, return the object updated.

If there is updates on garch, or riskLevel, or xgboost4j, or ml, the gols of this update is after re-run manually the "Part 3 - Generating trading scenarios and ML". The "versionNumber" is exactly the version of the xgboost file
like I explained comes from the end file name "_v{versionNumber}".

Some POST metodos developed to create data was an update functionality logic explained on each end point in the case of called for more than one time with the same part of compose kay, like same timestamp.

## Fetch data

- GET api/symbols{?{filter}}
    - Return all symbols in a flux, is possible apply filter for all parameter of Symbol
- GET api/symbols/{name}
    - Return the symbol if existed, if not 404
- GET api/symbols/{name}/charts/{timeframe}
    - Return the flux of candlesticks that correspond to the symbol name and timeframe, if existed, if not 404
    - Should return on chronological timestamp order
    - This path is a concatenation of validations;
- GET api/symbols/{name}/ticks{?{filter}}
    - Return all ticks in a flux, is possible apply filter for all parameter of Tick
    - Should return on chronological timestamp order
    - This path is a concatenation of validations;
- GET api/dashboards{?{filter}}
    - Return all dashboards in a flux, is possible apply filter for all parameter of Dashboard
- GET api/dashboards/{uuid}
    - Return the dashboards if existed, if not 404
- GET api/dashboards/{uuid}/{indicator}s{?{filter}}
    - Return all indicators in a flux, is possible apply filter for all parameter of Indicator
- GET api/dashboards/{uuid}/versions
    - Return all version number available in the files from the same uuid, in a flux

##

api/symbols/{name}/charts/{timeframe}/candlesticks/{timestamp}
api/dashboards/{uuid}/{indicator}s/{timestamp}
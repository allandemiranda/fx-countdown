# Future System Versions & Features Roadmap

This document outlines the list of functionalities and architectural evolutions planned to be developed once the current version is stabilized.

---

## 1. Business Logic Improvements & Refactoring

Even after the core business requirements are fulfilled, the codebase should be reviewed to optimize performance, abstraction, and data flow:

* **Layer Auditing:** Review `Repository`, `Service`, `Mapper`, `DTO`, and `Controller` classes to evaluate if logic and abstractions can be improved.
  * Eliminate unnecessary classes.
  * Refactor existing classes.
  * Create new classes where separation of concerns is needed.
* **Error Handling:** Standardize error response payloads and HTTP status codes returned to clients.
* **Utility Classes:** Review and clean up the usage of `Utils` classes.
* **Data Flow & Interface Optimization:** Evaluate interfaces and data streams to reduce redundant calls and minimize payload sizes during high-volume processing.

---

## 2. XGBoost with Validation Split & Early Stopping

Currently, XGBoost uses the entire dataset solely for training. We will enhance model training by supporting a dedicated validation dataset to prevent overfitting:

* **New EA Parameter (`validationPercentage`):** Added during EA creation (and updatable via the EA `PATCH` endpoint, similar to the EA description).
  * Specifies the percentage of the end slice of the generated `DMatrix` to be allocated to the `validation` watchlist instead of `train`.
* **Early Stopping Parameter (`earlyStoppingRounds`):** Halts training when validation performance stops improving.

```java
// DMatrix train, DMatrix validation, int rounds, int earlyStoppingRounds

Map<String, DMatrix> watch = Map.of("train", train, "validation", validation);
Booster booster = XGBoost.train(train, params, rounds, watch, null, null, null, earlyStoppingRounds);
```

---

## 3. Authentication & Security

Implement endpoint security and access control using **Spring Security**:

* **Token-Based Authentication:** Implement JWT token generation with expiration rules.
* **Role Hierarchy:**
  * **`MT5`:** Access restricted strictly to endpoints required by the MT5 terminal.
  * **`Client`:** Access to management and query endpoints used to verify data consistency.
  * **`Admin`:** Full access to all endpoints (an Admin includes all `Client` and `MT5` permissions).
* **Identity Management:**
  * Endpoints to request and generate new credentials.
  * Admin endpoints to view users and roles, approve new requests, edit permissions, and revoke access.

---

## 4. Microservices Decomposition

Due to the size and complexity of the platform, the monolith will be split into dedicated microservices:

* **API Gateway:**
  * Load balancing control.
  * Resource and routing management for external clients (MT5 and Web UI).
* **Security & Authentication Microservice:**
  * Dedicated service backed by a **MongoDB** database.
* **Market Data Microservice (`Symbol`, `Candlestick`, `Tick`):**
  * Dedicated service backed by a **SQL** database for storing market data.
* **Validation & ML Generation Microservice:**
  * Dedicated service backed by a **SQL** database.
  * Stores indicator data temporarily during the ML build process and cleans it up afterward.
* **EA & Dashboard Microservice:**
  * Dedicated service backed by a **MongoDB** database.
  * Manages EA configurations and input parameters.
* **ML Artifact Storage Microservice:**
  * Dedicated service backed by a **SQL** database.
  * Stores generated `.libsvm` and `.json` model files in local disk storage.
  * Exposes an interface to save, fetch, and delete files over the network without clients directly referencing physical file paths.
* **MT5 Order & Inference Microservice:**
  * Dedicated service backed by a **SQL** database.
  * High-availability, low-latency microservice dedicated to calculating and returning predictions to MT5 in real time.

---

## 5. Continuous Trading & In Live Learning Analysis

Currently, the system requires manual database updates and model retraining every few weeks to prevent prediction drift. An automated continuous learning workflow will be introduced:

* **New EA Parameters:**
  * `continuousAnalysis` (boolean): Enables or disables continuous learning (the manual approach remains supported).
  * `trainBatchSize` (int): Number of newly completed simulated trade rows required to trigger an incremental model update.
  * `validationBatchSize` (int): Number of newly completed simulated trade rows allocated for the validation watch dataset.
* **Trigger Condition:** Once the volume of new completed trades reaches `trainBatchSize + validationBatchSize`, an incremental update is initiated.
* **Zero-Downtime Hot-Swapping:**
  * The Storage microservice moves the active EA model file to a temporary file (`.tmp`) to continue serving live order predictions.
  * Once the new EA model is generated, the temporary file is removed and requests are routed to the new model.
* **Live Market Data Ingestion:**
  * Keep `Symbol`, `Candlestick`, and `Tick` data synced automatically.
  * Handle timezone differences between MT5 and local servers.
  * Check Forex market open/closed schedules to avoid unnecessary requests.
  * Support an MQL5 script polling the system every 5 minutes with new candlesticks, ticks, and symbol updates.

---

## 6. Candlestick Pattern Recognition via AI

Complement quantitative ML predictions with candlestick chart pattern analysis:

* **Pattern Analysis Window:** Evaluate a minimum lookback horizon of 3 completed candlesticks upon every new bar closed by MT5.
* **AI Microservice (`ext-ai`):**
  * Manages connections to AI models/LLMs (using local models such as **LLaMA**).
  * Lists available AI connections and handles new connection registration.
  * Provides load balancing across multiple models/connections.
  * Manages chart context and parameter configurations.
* **Deduplication & Caching:** Implement a cache mechanism per `(symbol, timeframe)` so each completed candlestick pattern is evaluated only once.
* **EA Toggle:** This mechanism can be enabled or disabled per EA (enabled by default).

---

## 7. Economic Calendar Filter

Utilize macroeconomic news data from MT5 to evaluate whether trading operations should be paused:

* **Calendar Processing Microservice:**
  * Ingests real-time economic calendar updates from MT5.
  * When changes or high-impact events are detected, calls the AI microservice to evaluate whether trading should continue on affected charts (`symbol` / `timeframe`).
* **EA Toggle:** Configurable per EA (enabled by default).

---

## 8. Multi-Broker Account Risk Management

Track account margin, equity, and aggregate risk before opening new positions:

* **Multi-Account Tracking:** Allow a client to connect one or multiple broker accounts.
* **Account Telemetry:** MT5 continuously transmits account updates to a dedicated Risk Management microservice.
* **Pre-Trade Risk Auditing:**
  * Identify which account is requesting an order.
  * Validate if the operation is safe based on existing open positions, free margin, and account exposure.
  * Approve or reject the trade execution.
* **Periodic Audits:** Generate periodic global risk and performance reports, using AI to evaluate and propose adjustments to EA parameters or strategies.

---

## 9. Web Front-End Interface (Angular)

Develop a web-based dashboard using **Angular** for management and monitoring:

* Management portal for non-MT5 interactions.
* Authentication and user management panel.
* Account risk and performance charts/reports.
* EA catalog, configuration manager, and status monitor.
* Real-time trading performance dashboard.
* Microservices health and status monitoring (admin views).
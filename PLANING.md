# Project Audit & Remediation Plan: EA Builder API

## 1. Goal & Context

The current pre-developed version of the project is known to have bugs, incomplete business logic, performance bottlenecks, and deviations from the official specification defined in [README.md](README.md)[cite: 2].

The objective of this document is to guide the **Software Engineer / Analyst** through:
1. Auditing the current codebase against the business requirements in [README.md](README.md)[cite: 1, 2].
2. Documenting what is implemented correctly, incorrectly, or missing[cite: 2].
3. Formulating an actionable execution plan to fix, complete, and optimize the system under strict non-functional constraints[cite: 2].

---

## 2. Non-Negotiable Constraints & Standards

Any remediation and further development must strictly comply with the following:

* **Tech Stack:** Java 25, Spring Framework 7, Spring Boot 4, and Docker containerization[cite: 2].
> MT5, using [MQL5 scripts](https://www.mql5.com/en/docs), will be on of the client that will provide and consume data from this application
* **Hardware & Memory Budget:**
    * Strict memory constraint: The application must operate safely within **< 15 GB RAM** (accounting for other concurrent host processes)[cite: 2].
    * High-volume dataset transformations (ticks, candles, DMatrix `.libsvm` files) must be designed for streaming or chunked memory management to prevent out-of-memory errors[cite: 1].
* **Observability & Logging Architecture:**
    * Provide a containerized log storage infrastructure (runnable via Docker alongside the application)[cite: 2].
    * Implement clean logging configuration files supporting the following log strategy[cite: 2]:
        * **`INFO` (Mandatory for `validate_scope` and `generate_xgboost`):** Clear, sequential step-by-step progress tracking of ongoing pipeline stages[cite: 1, 2].
        * **`WARN`:** Non-blocking anomalies (e.g., automatic scope adjustments, simulated trades not hitting `DEAL_REASON_TP`, unclosed trades)[cite: 1, 2].
        * **`DEBUG`:** Controller and boundary-level payloads for live troubleshooting with MT5/client requests[cite: 1, 2].
        * **`TRACE`:** Deep execution traces through internal calculation routines and data pipeline steps[cite: 2].
* **Automated Testing Requirements:**
    * Comprehensive Unit tests covering all domain classes and methods[cite: 2].
    * Persistence / Repository tests[cite: 2].
    * Integration tests for end-to-end data pipelines[cite: 2].
    * E2E tests validating the full trading bot generation lifecycle[cite: 2].

---

## 3. Application Audit Methodology

The analyst should inspect each domain area described in [README.md](README.md) and classify every feature into one of the categories below[cite: 1, 2]:

### 3.1. Correctly Implemented
* Features matching the business rules in [README.md](README.md)[cite: 1, 2].
* **Action:** Review data structures and algorithms for performance, memory footprint, and maintainability[cite: 2].

### 3.2. Incorrectly Implemented / Broken
* Features partially built, containing logical bugs, incorrect state transitions, or failing edge cases[cite: 2].
* **Action:** Document the root cause, broken assumptions, and planned refactoring steps[cite: 2].

### 3.3. Missing Features
* Endpoints, validation flows, ML/GARCH pipeline steps, or helper functionalities present in [README.md](README.md) but absent in code[cite: 1, 2].
* **Action:** Specify technical design and implementation steps[cite: 2].

---

## 4. Analyst Audit Checklist & Findings

> *The analyst should evaluate the modules below, compare with [README.md](README.md), and document current status (`[OK]`, `[NEEDS_FIX]`, `[MISSING]`) along with the technical remediation steps[cite: 1, 2].*

### Area A: Market Data & Symbol Management
* **Scope to check:** `/symbols`, `/candlesticks`, `/ticks`, `/timeframes` endpoints, Ask/Bid candle synthesis from ticks[cite: 1].
* **Analyst Findings & Remediation Plan:**
    * *[Fill in current status, identified bugs, and planned fixes]*

### Area B: EA Lifecycle & Scope Validation
* **Scope to check:** EA CRUD, state machine transitions (`CREATED` $\rightarrow$ `VALIDATING_SCOPE` $\rightarrow$ `VALIDATING_SCOPE_COMPLETE`/`ERROR` $\rightarrow$ `READY_TO_USE`), data availability checks against requested temporal scopes[cite: 1].
* **Analyst Findings & Remediation Plan:**
    * *[Fill in current status, identified bugs, and planned fixes]*

### Area C: Technical Indicators Pipeline
* **Scope to check:** Query (`GET`) and Insert (`POST`) for all supported indicators (RSI, MACD, Stochastic, Bollinger Bands, Moving Averages, ATR, ADX) and data cleanup (`clean_build`)[cite: 1].
* **Analyst Findings & Remediation Plan:**
    * *[Fill in current status, identified bugs, and planned fixes]*

### Area D: Quantitative & ML Engine (`generate_xgboost`)
* **Scope to check:** Asynchronous execution (`204 No Content`), GARCH(1,1) parameter estimation, Risk level & TP/SL calculation, trading simulation (`DEAL_REASON_*`), sliding lookback window DMatrix `.libsvm` generation, model training, artifact export (`.json`), and parallel thread safety[cite: 1].
* **Analyst Findings & Remediation Plan:**
    * *[Fill in current status, identified bugs, and planned fixes]*

### Area E: Inference & Live Order Polling (`/order`)
* **Scope to check:** Real-time indicator evaluation on candle close, threshold filtering (`minimalLevelAccepted`), response latency, empty payload fallback[cite: 1].
* **Analyst Findings & Remediation Plan:**
    * *[Fill in current status, identified bugs, and planned fixes]*

---

## 5. Execution Roadmap

Once the audit is completed, the analyst must organize all identified tasks into prioritized development phases:

1. **Phase 1 — Core Fixes & Database Integrity:** Fix data ingestion, symbol/candlestick persistence, and scope validation[cite: 1, 2].
2. **Phase 2 — Quantitative & Training Pipeline:** Stabilize GARCH calculations, trade simulation, DMatrix streaming, and XGBoost integration[cite: 1, 2].
3. **Phase 3 — Performance & Concurrency:** Profile memory usage (< 15 GB RAM limit) and optimize thread safety[cite: 2].
4. **Phase 4 — Observability & QA:** Implement structured logging, Docker log stack, and the full test suite (Unit, Integration, E2E)[cite: 2].
CREATE TABLE IF NOT EXISTS indicator_adx (
     id UUID PRIMARY KEY,
     chart_id UUID NOT NULL,
     timestamp TIMESTAMP WITH TIME ZONE NOT NULL,
     main_line NUMERIC(20, 10) NOT NULL,
     plus_di_line NUMERIC(20, 10) NOT NULL,
     minus_di_line NUMERIC(20, 10) NOT NULL,
     CONSTRAINT uk_adx_chart_timestamp UNIQUE (chart_id, timestamp)
);

CREATE INDEX IF NOT EXISTS idx_adx_chart_id ON indicator_adx(chart_id);
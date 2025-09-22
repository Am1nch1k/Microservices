CREATE TABLE IF NOT EXISTS vehicles (
    license_plate VARCHAR(15) PRIMARY KEY,
    type VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS parking_session (
    id BIGSERIAL PRIMARY KEY,
    license_plate VARCHAR(15) NOT NULL,
    entry_time TIMESTAMP NOT NULL,
    exit_time TIMESTAMP,
    version BIGINT DEFAULT 0,
    CONSTRAINT fk_vehicle
        FOREIGN KEY (license_plate)
        REFERENCES vehicles(license_plate)
);

COMMENT ON TABLE vehicles IS 'Таблица автомобилей';
COMMENT ON COLUMN vehicles.type IS 'Тип ТС (легковой, грузовой и т.д.)';
COMMENT ON TABLE parking_session IS 'Сессии парковки';
COMMENT ON COLUMN parking_session.exit_time IS 'NULL, если машина ещё на парковке';
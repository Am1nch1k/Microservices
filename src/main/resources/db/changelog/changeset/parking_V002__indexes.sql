CREATE INDEX IF NOT EXISTS idx_parking_sessions_active
    ON parking_sessions(license_plate)
    WHERE exit_time IS NULL;

CREATE INDEX IF NOT EXISTS idx_parking_sessions_entry_time
    ON parking_sessions(entry_time);

CREATE INDEX IF NOT EXISTS idx_parking_sessions_exit_time
    ON parking_sessions(exit_time);
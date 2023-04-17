\c host_agent

CREATE TABLE IF NOT EXISTS host_info (
    id SERIAL PRIMARY KEY,
    hostname VARCHAR(50) NOT NULL,
    cpu_number INTEGER NOT NULL,
    cpu_architecture VARCHAR(50) NOT NULL,
    cpu_model_name VARCHAR(50) NOT NULL,
    cpu_mhz FLOAT NOT NULL,
    l2_cache VARCHAR(50) NOT NULL,
    total_mem INTEGER NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS host_usage (
    id SERIAL PRIMARY KEY,
    timestamp TIMESTAMP NOT NULL,
    host_id INTEGER REFERENCES host_info(id),
    memory_free INTEGER NOT NULL,
    cpu_idle FLOAT NOT NULL,
    cpu_kernel FLOAT NOT NULL,
    cpu_user FLOAT NOT NULL,
    disk_io INTEGER NOT NULL,
    disk_available INTEGER NOT NULL
);

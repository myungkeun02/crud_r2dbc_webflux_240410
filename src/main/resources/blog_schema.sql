CREATE TABLE IF NOT EXISTS "blog" (
    id SERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    version BIGINT DEFAULT 0 NOT NULL
)
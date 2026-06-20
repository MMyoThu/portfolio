CREATE TABLE admin_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE audit_log (
    id BIGSERIAL PRIMARY KEY,
    session_id VARCHAR(100),
    page_name VARCHAR(200),
    visitor_ip VARCHAR(100),
    browser VARCHAR(200),
    country VARCHAR(100),
    city VARCHAR(100),
    visit_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE contact_message (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(200),
    subject VARCHAR(200),
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE project (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200),
    description TEXT,
    github_url VARCHAR(500),
    demo_url VARCHAR(500),
    technology_stack TEXT,
    image_url VARCHAR(500),
    featured BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE game (
    id BIGSERIAL PRIMARY KEY,
    game_code VARCHAR(50) UNIQUE,
    game_name VARCHAR(100),
    description TEXT,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE game_score (
    id BIGSERIAL PRIMARY KEY,
    game_id BIGINT NOT NULL,
    player_name VARCHAR(100),
    score INTEGER,
    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_game_score_game FOREIGN KEY(game_id) REFERENCES game(id)
);

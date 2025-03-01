-- Создание пользователей
CREATE USER logservice WITH PASSWORD 'logservice';
CREATE USER notification WITH PASSWORD 'notification-pwd';

-- Назначение привилегий
GRANT ALL PRIVILEGES ON DATABASE postgres TO logservice;
GRANT ALL PRIVILEGES ON DATABASE postgres TO notification;

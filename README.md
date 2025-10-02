```markdown
# 🚀 Microservices Architecture Project

Современная реализация микросервисной архитектуры на Java с использованием Spring Boot и Spring Cloud.

## 📋 О проекте

Этот проект демонстрирует полноценную микросервисную архитектуру с разделением ответственности, межсервисной коммуникацией и централизованной конфигурацией.

## 🏗️ Архитектура проекта

```
src/main/java/
├── 📦 api-gateway/              # API Gateway - единая точка входа
├── 📦 config-server/            # Config Server - централизованная конфигурация
├── 📦 eureka-server/            # Service Discovery - регистрация и обнаружение сервисов
├── 📦 user-service/             # User Service - управление пользователями
└── 📦 application.yml           # Общие конфигурации
```

## 🛠️ Технологический стек

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Cloud**
  - Spring Cloud Gateway
  - Eureka Server
  - Config Server
- **Maven** - управление зависимостями
- **Docker** - контейнеризация

## 📁 Структура сервисов

### 🔑 API Gateway (`api-gateway/`)
- Единая точка входа для всех клиентских запросов
- Маршрутизация и балансировка нагрузки
- Аутентификация и авторизация
- Rate limiting

### ⚙️ Config Server (`config-server/`)
- Централизованное управление конфигурациями
- Поддержка разных окружений (dev, prod, test)
- Обновление конфигураций без перезапуска сервисов

### 🔍 Eureka Server (`eureka-server/`)
- Service discovery и регистрация
- Мониторинг состояния сервисов
- Балансировка нагрузки на клиентской стороне

### 👥 User Service (`user-service/`)
- Управление пользователями (CRUD операции)
- Бизнес-логика пользовательских операций
- Интеграция с другими сервисами

## 🚀 Быстрый старт

### Предварительные требования
- Java 17 или выше
- Maven 3.6+
- Git

### Запуск проекта

1. **Клонируйте репозиторий:**
```bash
git clone https://github.com/Am1nch1k/Microservices.git
cd Microservices
```

2. **Соберите проект:**
```bash
mvn clean install
```

3. **Запустите сервисы в правильном порядке:**

```bash
# 1. Запустите Eureka Server
mvn spring-boot:run -pl eureka-server

# 2. Запустите Config Server
mvn spring-boot:run -pl config-server

# 3. Запустите User Service
mvn spring-boot:run -pl user-service

# 4. Запустите API Gateway
mvn spring-boot:run -pl api-gateway
```

### Порты по умолчанию
- **Eureka Server**: 8761 - http://localhost:8761
- **Config Server**: 8888
- **API Gateway**: 8080
- **User Service**: 8081

## 🔧 Конфигурация

Основные конфигурации находятся в `src/main/resources/application.yml`. Каждый сервис может иметь свои специфичные настройки в соответствующих директориях.

## 📚 API Endpoints

### Через API Gateway (порт 8080)
```
GET     /api/users/**          -> User Service
POST    /api/users/**          -> User Service
PUT     /api/users/**          -> User Service
DELETE  /api/users/**          -> User Service
```

### Прямые endpoints
- **Eureka Dashboard**: http://localhost:8761
- **User Service**: http://localhost:8081

## 🐳 Docker поддержка

```bash
# Сборка Docker образов
docker-compose build

# Запуск всех сервисов
docker-compose up
```

## 🧪 Тестирование

```bash
# Запуск всех тестов
mvn test

# Запуск тестов конкретного сервиса
mvn test -pl user-service
```

## 📊 Мониторинг

Проект поддерживает интеграцию с:
- Spring Boot Actuator
- Micrometer metrics
- Prometheus и Grafana

## 🤝 Вклад в проект

Мы приветствуем вклад в проект! Пожалуйста:

1. Форкните репозиторий
2. Создайте feature ветку (`git checkout -b feature/amazing-feature`)
3. Закоммитьте изменения (`git commit -m 'Add some amazing feature'`)
4. Запушьте в ветку (`git push origin feature/amazing-feature`)
5. Откройте Pull Request

## 📄 Лицензия

Этот проект лицензирован под MIT License - смотрите файл [LICENSE](LICENSE) для деталей.





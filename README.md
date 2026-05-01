# Warehouse API

Spring Boot REST API для управління складом (каталогом товарів).

## Технології

- **Java 17**
- **Spring Boot 3.5.13**
- **Spring Data JPA** — робота з базою даних
- **PostgreSQL** — основна СУБД
- **Liquibase** — міграції БД
- **Lombok** — зменшення boilerplate коду

## Архітектура

Проект використовує трирівневу архітектуру:

```
Controller → Service → Repository
```

- **Controller** — HTTP endpoints, серіалізація/десеріалізація
- **Service** — бізнес-логіка
- **Repository** — доступ до даних (JPA)

## Структура проекту

```
src/main/java/ua/edu/duan/warehouse/
├── WarehouseApplication.java          # Точка входу
├── controller/
│   ├── WarehouseController.java       # REST endpoints
│   └── ItemDto.java                   # DTO для передачі даних
├── dao/
│   └── repository/
│       └── CatalogRepository.java     # JPA репозиторій
├── service/
│   ├── CatalogService.java            # Інтерфейс сервісу
│   └── impl/
│       └── CatalogServiceImpl.java    # Реалізація сервісу
```

## API Endpoints

| Метод | Endpoint | Опис |
|---|---|---|
| `GET` | `/api/hello-world` | Перевірка роботи |
| `GET` | `/api/warehouse` | Отримати всі елементи |
| `GET` | `/api/warehouse/search?prefix=...` | Пошук за початком назви (case-insensitive) |
| `POST` | `/api/item` | Додати новий елемент |
| `PUT` | `/api/item/{id}` | Оновити існуючий елемент |
| `DELETE` | `/api/item/{id}` | Видалити елемент |

### Приклади запитів

**Додати елемент:**
```json
POST /api/item
{
  "itemName": "Widget",
  "description": "A useful widget",
  "icon": "https://example.com/icon.png",
  "attributes": "{\"color\": \"blue\"}"
}
```

**Пошук за назвою:**
```
GET /api/warehouse/search?prefix=wid
```

## Запуск

### Вимоги

- Java 17+
- PostgreSQL (схема `items`)

### Конфігурація БД

Налаштування в `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/warehouse?currentSchema=items
spring.datasource.username=postgres
spring.datasource.password=1234
```

### Запуск

```bash
# Unix
./gradlew bootRun

# Windows
gradlew.bat bootRun
```

Сервер запускається на `http://localhost:8080`.

## Тестування

Проект містить Postman колекцію `warehouse-api.postman_collection.json`.

Імпортуй через: **File → Import → вибери файл**

Колекція включає всі endpoints з прикладовими даними. Змінні `baseUrl` та `itemId` налаштовуються в колекції.

## Ліцензія

Практичний проект для КН-23-1.

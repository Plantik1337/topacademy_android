# Архитектура проекта TopAcademy Android

## Обзор

Проект реструктурирован согласно принципам **Clean Architecture** с разделением по фичам (feature-based architecture). Каждая фича содержит три основных слоя: **Domain**, **Data** и **Presentation**.

## Структура проекта

```
app/src/main/java/com/example/topacademy_android/
├── MainActivity.kt                    # Главная активность с переключением темы
├── HomeActivity.kt                    # Домашний экран с навигацией по фичам
└── features/                          # Все фичи приложения
    ├── calculator/                    # Фича калькулятора
    │   ├── domain/
    │   │   ├── model/                 # Domain модели (пусто для калькулятора)
    │   │   ├── repository/            # Интерфейсы репозиториев
    │   │   │   └── CalculatorRepository.kt
    │   │   └── usecase/               # Use Cases (бизнес-логика)
    │   │       └── CalculateExpressionUseCase.kt
    │   ├── data/
    │   │   └── repository/            # Реализации репозиториев
    │   │       └── CalculatorRepositoryImpl.kt
    │   └── presentation/
    │       ├── ui/                    # UI компоненты
    │       │   └── CalculatorActivity.kt
    │       └── viewmodel/             # ViewModels
    │           └── CalculatorViewModel.kt
    │
    ├── weather/                       # Фича погоды
    │   ├── domain/
    │   │   ├── model/                 # Domain модели
    │   │   │   └── WeatherModels.kt
    │   │   ├── repository/            # Интерфейсы репозиториев
    │   │   │   └── WeatherRepository.kt
    │   │   └── usecase/               # Use Cases
    │   │       └── GetWeatherForecastUseCase.kt
    │   ├── data/
    │   │   ├── remote/                # Сетевой слой
    │   │   │   ├── dto/               # Data Transfer Objects
    │   │   │   │   └── WeatherDto.kt
    │   │   │   ├── mapper/            # Маппинг DTO -> Domain
    │   │   │   │   └── WeatherMapper.kt
    │   │   │   └── WeatherApiService.kt
    │   │   └── repository/            # Реализации репозиториев
    │   │       └── WeatherRepositoryImpl.kt
    │   └── presentation/
    │       ├── ui/                    # UI компоненты
    │       │   ├── WeatherActivity.kt
    │       │   └── WeatherDetailActivity.kt
    │       ├── adapter/               # RecyclerView адаптеры
    │       │   └── WeatherForecastAdapter.kt
    │       ├── helper/                # UI утилиты
    │       │   └── WeatherUIHelper.kt
    │       └── viewmodel/             # ViewModels
    │           └── WeatherViewModel.kt
    │
    └── carlist/                       # Фича списка машин
        ├── domain/
        │   ├── model/                 # Domain модели
        │   │   └── Car.kt
        │   ├── repository/            # Интерфейсы репозиториев
        │   │   └── CarRepository.kt
        │   └── usecase/               # Use Cases
        │       └── GetCarsUseCase.kt
        ├── data/
        │   └── repository/            # Реализации репозиториев
        │       └── CarRepositoryImpl.kt
        └── presentation/
            ├── ui/                    # UI компоненты
            │   └── ListActivity.kt
            ├── adapter/               # RecyclerView адаптеры
            │   └── CarAdapter.kt
            └── viewmodel/             # ViewModels
                └── CarListViewModel.kt
```

## Принципы архитектуры

### 1. Разделение по слоям (Layered Architecture)

#### Domain Layer (Доменный слой)
- **Назначение**: Содержит бизнес-логику приложения
- **Компоненты**:
  - `model/` - Domain модели (чистые Kotlin классы без Android зависимостей)
  - `repository/` - Интерфейсы репозиториев
  - `usecase/` - Use Cases (варианты использования)
- **Зависимости**: Не зависит от других слоев
- **Правило**: Самый внутренний слой, не знает о существовании других слоев

#### Data Layer (Слой данных)
- **Назначение**: Управление данными (API, база данных, кэш)
- **Компоненты**:
  - `repository/` - Реализации интерфейсов из Domain слоя
  - `remote/` - Сетевые запросы, API сервисы
  - `remote/dto/` - Data Transfer Objects для API
  - `remote/mapper/` - Преобразование DTO в Domain модели
  - `local/` - Локальное хранение (если есть)
- **Зависимости**: Зависит только от Domain слоя
- **Правило**: Реализует интерфейсы из Domain, скрывает детали источников данных

#### Presentation Layer (Слой представления)
- **Назначение**: UI и взаимодействие с пользователем
- **Компоненты**:
  - `ui/` - Activities, Fragments, Compose экраны
  - `viewmodel/` - ViewModels для управления состоянием UI
  - `adapter/` - RecyclerView адаптеры
  - `helper/` - UI утилиты и хелперы
- **Зависимости**: Зависит только от Domain слоя (через Use Cases)
- **Правило**: Не знает о Data слое, общается с Domain через Use Cases

### 2. Dependency Rule (Правило зависимостей)

```
Presentation Layer → Domain Layer ← Data Layer
```

- Внешние слои зависят от внутренних
- Внутренние слои не знают о внешних
- Domain слой - центральный, независимый

### 3. Use Cases (Варианты использования)

Каждая бизнес-операция выделена в отдельный Use Case:

- `CalculateExpressionUseCase` - вычисление математических выражений
- `GetWeatherForecastUseCase` - получение прогноза погоды
- `GetCarsUseCase` - получение списка автомобилей

### 4. Repository Pattern

Абстракция доступа к данным:
- Интерфейсы в Domain слое
- Реализации в Data слое
- Скрывает источники данных от бизнес-логики

## Преимущества данной архитектуры

1. **Тестируемость**: Каждый слой можно тестировать независимо
2. **Масштабируемость**: Легко добавлять новые фичи
3. **Поддерживаемость**: Четкое разделение ответственности
4. **Независимость от фреймворка**: Domain слой не зависит от Android
5. **Переиспользование**: Use Cases можно использовать в разных UI

## Dependency Injection

В текущей реализации используется **ручное DI** (без фреймворков):
- ViewModels создают зависимости напрямую
- Repository implementations инстанцируются в ViewModels
- Use Cases получают repository через конструктор

### Пример цепочки зависимостей:

```kotlin
// В ViewModel
private val apiService = WeatherApiService.create()
private val repository = WeatherRepositoryImpl(apiService)
private val getWeatherForecastUseCase = GetWeatherForecastUseCase(repository)
```

## Навигация между фичами

Навигация осуществляется через `HomeActivity`, который содержит кнопки для перехода к каждой фиче:

```kotlin
binding.btnCalculator.setOnClickListener {
    startActivity(Intent(this, CalculatorActivity::class.java))
}
binding.btnList.setOnClickListener {
    startActivity(Intent(this, ListActivity::class.java))
}
binding.btnWeather.setOnClickListener {
    startActivity(Intent(this, WeatherActivity::class.java))
}
```

## Рекомендации для дальнейшего развития

1. **Добавить DI фреймворк** (Dagger/Hilt, Koin)
2. **Добавить навигационный компонент** (Navigation Component)
3. **Добавить локальное хранение** (Room Database)
4. **Добавить тесты** для каждого слоя
5. **Добавить обработку ошибок** (sealed classes для состояний)
6. **Мигрировать на Jetpack Compose** для современного UI

## Заключение

Проект успешно реструктурирован согласно принципам Clean Architecture с разделением по фичам. Каждая фича изолирована и следует единым принципам архитектуры, что обеспечивает хорошую масштабируемость и поддерживаемость кода. 
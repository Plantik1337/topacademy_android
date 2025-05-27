# TopAcademy Android - Калькулятор

## Описание
Android приложение с калькулятором, реализованным согласно принципам чистой архитектуры.

## Реализованная функциональность

### Калькулятор
- ✅ Базовые математические операции (+, -, ×, ÷)
- ✅ Поддержка десятичных чисел (с запятой)
- ✅ Сложные выражения с несколькими операндами
- ✅ Смена знака числа (+/-)
- ✅ Очистка (C) и удаление последнего символа (⌫)
- ✅ Обработка ошибок ввода

### Архитектура
- **Data Layer**: `CalculatorRepository` - обработка математических выражений
- **Domain Layer**: `CalculateExpressionUseCase` - бизнес-логика вычислений
- **Presentation Layer**: `CalculatorViewModel` + `CalculatorActivity` - UI и состояние

### Технические особенности
- ✅ MVVM архитектура с LiveData
- ✅ ViewBinding для безопасной работы с UI
- ✅ Библиотека exp4j для вычисления выражений
- ✅ Material Design компоненты
- ✅ Поддержка светлой и темной темы
- ✅ Все строки в strings.xml
- ✅ Все цвета в colors.xml
- ✅ Все размеры в dimens.xml
- ✅ Unit тесты для проверки функциональности

### Использованные библиотеки
- `net.objecthunter:exp4j:0.4.8` - для вычисления математических выражений
- `androidx.lifecycle:lifecycle-viewmodel-ktx` - для ViewModel
- `androidx.lifecycle:lifecycle-livedata-ktx` - для LiveData
- `androidx.activity:activity-ktx` - для Activity extensions

## Запуск
1. Откройте проект в Android Studio
2. Синхронизируйте Gradle
3. Запустите на устройстве или эмуляторе

## Тестирование
Запуск unit тестов:
```bash
./gradlew test
```

## Структура проекта
```
app/src/main/java/com/example/topacademy_android/
├── CalculatorActivity.kt          # UI слой
├── CalculatorViewModel.kt         # Presentation слой
├── CalculateExpressionUseCase.kt  # Domain слой
└── CalculatorRepository.kt        # Data слой
``` 
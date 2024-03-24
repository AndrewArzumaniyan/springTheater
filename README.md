# springTheater - театральная касса REST API

## <b>Стек</b>:
1. Java 17
2. Sprint boot 
3. Maven
4. Hibernate
5. Lombok
6. JUnit Jupiter

## Доступные запросы:

### BeginPlaceController

#### `/api/v1/begin-place/{beginPlaceId}` (GET)
Возвращает информацию о месте начала спектакля по его идентификатору `beginPlaceId`.

#### `/api/v1/begin-place/{beginPlaceId}` (PUT)
Обновляет статус места начала спектакля по его идентификатору `beginPlaceId`.

### PlaceViewController

#### `/api/v1/place/all/begin/{begin_id_param}` (GET)
Возвращает список мест (PlaceView) для заданного идентификатора начала спектакля `begin_id_param`.

### PlayViewController

#### `/api/v1/play/all` (GET)
Возвращает список всех спектаклей (PlayView).

#### `/api/v1/play/all/date/{date}` (GET)
Возвращает список спектаклей (PlayView) для заданной даты в формате yyyy-mm.

#### `/api/v1/play/all/theater/{theatreId}` (GET)
Возвращает список спектаклей (PlayView) для заданного идентификатора театра `theatreId`.

### TheaterController

#### `/api/v1/theater/all` (GET)
Возвращает список всех театров.

#### `/api/v1/theater/{theaterId}` (GET)
Возвращает информацию о театре по его идентификатору `theaterId`.

#### `/api/v1/theater` (POST)
Создает новый театр.

#### `/api/v1/theater` (PUT)
Обновляет информацию о существующем театре.

#### `/api/v1/theater/{theaterId}` (DELETE)
Удаляет театр по его идентификатору `theaterId`.


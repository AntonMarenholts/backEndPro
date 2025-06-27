Все три метода следуют очень похожему и эффективному паттерну для получения и преобразования данных:

Получение Stream из репозитория: Вместо того чтобы сразу загружать все найденные посты в память в виде списка, мы
получаем из репозитория Stream. Это ленивый, более производительный подход, особенно для больших объемов данных.

Преобразование (Mapping): Мы используем метод .map() из Stream API, чтобы преобразовать каждый объект Post (сущность
базы данных) в объект PostDto (объект для передачи данных клиенту). Это делается с помощью библиотеки ModelMapper.

Сборка в коллекцию: С помощью .collect(Collectors.toList()) мы собираем все преобразованные объекты PostDto в итоговый
список List, который и возвращается из метода.

Транзакционность: Все три метода помечены аннотацией @Transactional(readOnly = true). Это критически важно, так как для
работы Stream из репозитория требуется активное соединение с базой данных. Эта аннотация гарантирует, что транзакция (и,
следовательно, соединение с БД) будет открыта на все время выполнения метода, включая этап сборки стрима в коллекцию.

1. findPostByAuthor(String author)
   Этот метод находит все посты по имени автора.
   @Override
   @Transactional(readOnly = true)
   public Iterable<PostDto> findPostByAuthor(String author) {
   return postRepository.findByAuthor(author) // Шаг 1
   .map(p -> modelMapper.map(p, PostDto.class)) // Шаг 2
   .collect(Collectors.toList()); // Шаг 3
   }
   Пояснение:

Шаг 1 postRepository.findByAuthor(author): Мы вызываем метод из PostRepository. Spring Data JPA автоматически понимает
по названию этого метода, что нужно сгенерировать SQL-запрос, который найдет все записи в таблице posts, где поле author
равно переданному значению. Результат возвращается в виде Stream<Post>.

Шаг 2 .map(...): Каждый Post из стрима преобразуется в PostDto.

Шаг 3 .collect(...): Все PostDto собираются в List.

2. findPostByTags(List<String> tags)
   Этот метод находит все посты, у которых есть хотя бы один из перечисленных тегов.
   @Override
   @Transactional(readOnly = true)
   public Iterable<PostDto> findPostByTags(List<String> tags) {
   return postRepository.findByTagsNameIn(tags) // Шаг 1
   .map(p -> modelMapper.map(p, PostDto.class)) // Шаг 2
   .collect(Collectors.toList()); // Шаг 3
   }
   Пояснение:

Шаг 1 postRepository.findByTagsNameIn(tags): Spring Data JPA снова творит магию. По названию метода findByTagsNameIn он
понимает, что нужно:

Найти посты (findBy).

У которых есть связанные сущности Tags (Tags).

У этих Tags есть поле name (Name).

И значение этого поля name должно быть в переданном списке tags (In).
Результат также возвращается как Stream<Post>.

Шаги 2 и 3: Аналогичны предыдущему методу — преобразование в DTO и сборка в список.

3. findPostByPeriod(LocalDate dateFrom, LocalDate dateTo)
   Этот метод находит все посты, созданные в определенном временном диапазоне.
   @Override
   @Transactional(readOnly = true)
   public Iterable<PostDto> findPostByPeriod(LocalDate dateFrom, LocalDate dateTo) {
   return postRepository.findByDateCreatedBetween(dateFrom.atStartOfDay(), dateTo.atTime(23, 59, 59)) // Шаг 1
   .map(p -> modelMapper.map(p, PostDto.class)) // Шаг 2
   .collect(Collectors.toList()); // Шаг 3
   }
   Пояснение:

Шаг 1 postRepository.findByDateCreatedBetween(...): Метод репозитория findByDateCreatedBetween ожидает два аргумента
типа LocalDateTime.

Мы передаем ему dateFrom.atStartOfDay(), что преобразует начальную дату в LocalDateTime с временем 00:00:00.

И dateTo.atTime(23, 59, 59), что преобразует конечную дату в LocalDateTime с временем 23:59:59. Это делается для того,
чтобы поиск включал весь день конечной даты.
Spring Data JPA генерирует SQL-запрос с условием WHERE dateCreated BETWEEN ? AND ? и возвращает Stream<Post>.

Шаги 2 и 3: Преобразование и сборка в List, как и в предыдущих методах.
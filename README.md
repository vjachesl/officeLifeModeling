Тестовое задание на должность Специалиста по автоматизации тестирования

Необходимо описать задачу с применением принципов ООП на языке Java или C#.
При выполнении задания просьба пользоваться системами контроля версий (GitHub, Bitbucket…).
Необходимо смоделировать рабочий процесс офиса в течении одного месяца.
В офисе работает 10 - 100 сотрудников (задается случайно), каждый из них имеет одну или более одной должности (задается случайно для каждого сотрудника): Программист, Дизайнер, Тестировщик, Менеджер, Директор, Бухгалтер, Уборщик. Уборщики не могут выполнять никакой другой работы. Должность Директора и Бухгалтера можно совмещать только с должностью Менеджера. 
Каждый сотрудник имеет свой рабочий график, не более 8 часов в день и 40 часов в неделю.
Каждая должность имеет свою почасовую ставку, работа в выходные оплачивается в двойном размере. Директор, Менеджер и Бухгалтер имеют фиксированную ставку.
В фирме должны быть хотя бы один Директор, Менеджер и Бухгалтер.
В процессе моделирования каждый час Директор дает одно или более одного распоряжения своим сотрудникам. Выполнение каждого распоряжения может занять от одного до двух часов каждым сотрудником, в должности которого входит выполнение поставленного задания. Если Директор дал несколько распоряжений, которые может выполнять один и тот же сотрудник, тогда данный рабочий выполняет наиболее приоритетное задание. Если задания имеют одинаковый приоритет, тогда следует выполнять наиболее оплачиваемое из них. Сотрудник не может выполнять более одного распоряжения одновременно.
Если на выполнение распоряжения в офисе не имеется ресурсов, фирма передает задание фрилансерам (удаленным сотрудникам). Распоряжение об уборке офиса передавать на фриланс нельзя.
Каждую неделю Бухгалтер начисляет зарплату сотрудникам исходя из фактически отработанных часов. Оплата фрилансерам выполняется в конце каждого дня.
По окончании месяца необходимо сформировать суммарный отчет о выполненной работе и выданной зарплате по всем рабочим (сотрудникам офиса и фрилансерам) и для каждого сотрудника в отдельности, и сохранить его в текстовый документ.

Минимальный набор должностных обязанностей:
Программист – «писать код»
Дизайнер – «рисовать макет»
Тестировщик – «тестировать программу»
Менеджер – «продавать услуги»
Бухгалтер – «составить отчетность»
Уборщик – «выполнить уборку в офисе»

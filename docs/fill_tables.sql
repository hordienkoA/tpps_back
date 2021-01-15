/**
 * Fill database with test data
 */

INSERT INTO buyfly.airline
       (id,  IATA, ICAO,  name)
VALUES (201, 'AA', 'AAL', 'American Airlines'),
       (202, 'LH', 'DLH', 'Lufthansa'),
       (203, 'AY', 'FIN', 'Finnair'),
       (204, 'SU', 'AFL', 'Aeroflot'),
       (205, 'TK', 'THY', 'Turkish Airlines'),
       (206, 'VX', 'VRD', 'Virgin America'),
       (207, 'DL', 'DAL', 'Delta Air Lines'),
       (208, 'KL', 'KLM', 'KLM'),
       (209, 'AF', 'AFR', 'Air France'),
       (210, 'BA', 'BAW', 'British Airways'),
       (211, 'FR', 'RYR', 'Ryanair'),
       (212, 'QR', 'QTR', 'Qatar Airways'),
       (213, 'KE', 'KAL', 'Korean Air'),
       (214, 'SQ', 'SQC', 'Singapore Airlines Cargo'),
       (215, 'AC', 'ACA', 'Air Canada'),
       (216, 'CA', 'CCA', 'Air China'),
       (217, 'EK', 'UAE', 'Emirates'),
       (218, 'MS', 'MSR', 'EgyptAir');


INSERT INTO buyfly.aircraft
       (id,  model,    name,         airline_id)
VALUES (401, 'A380',   'Airbus',            212),
       (402, '707',    'Boeing',            201),
       (403, 'MD-11',  'McDonnell Douglas', 207),
       (404, 'L-1011', 'Lockheed',          216),
       (405, 'A340',   'Airbus',            202),
       (406, '747',    'Boeing',            208);


INSERT INTO buyfly.seat
       (id,  number, price, seatClass, taken, aircraft_id)
VALUES (501, '9F',   115.3,    'F'   , false, 402),
       (502, '8B',    75.9,    'J'   , true,  402),
       (503, '3G',    55.0,    'W'   , false, 404),
       (504, '1A',    25.9,    'Y'   , false, 406),
       (505, '6H',    25.7,    'Y'   , false, 405),
       (506, 'B3',    30.5,    'Y'   , true,  406);


INSERT INTO buyfly.city (id, country, name)
VALUES (101, 'United States', 'New York'),
       (102, 'United States', 'Los Angeles'),
       (103, 'United States', 'Atlanta'),
       (104, 'United States', 'Miami'),
       (105, 'United States', 'Chicago'),
       (106, 'United States', 'Washington'),
       (107, 'United Kingdom', 'London'),
       (108, 'United Kingdom', 'Edinburg'),
       (109, 'United Kingdom', 'Liverpool'),
       (110, 'United Kingdom', 'Manchester'),
       (111, 'United Kingdom', 'Glasgow'),
       (112, 'Ukraine', 'Kyiv'),
       (113, 'Ukraine', 'Lviv'),
       (114, 'Ukraine', 'Odessa'),
       (115, 'United Arab Emirates', 'Dubai'),
       (116, 'United Arab Emirates', 'Abu Dhabi'),
       (117, 'United Arab Emirates', 'Sharjah'),
       (118, 'Poland', 'Warsaw'),
       (119, 'Poland', 'Poznań'),
       (120, 'Poland', 'Rzeszów '),
       (121, 'Poland', 'Kraków'),
       (122, 'Finland', 'Helsinki'),
       (123, 'Finland', 'Lappeenranta'),
       (124, 'France', 'Paris'),
       (125, 'France', 'Bordeaux'),
       (126, 'France', 'Lille'),
       (127, 'France', 'Marseille'),
       (128, 'Germany', 'Berlin'),
       (129, 'Germany', 'Dortmund'),
       (130, 'Germany', 'Frankfurt');


INSERT INTO buyfly.airport
       (id,  IATA,  ICAO,   taxPrice,   city_id, name)
VALUES (301, 'HHN', 'EDFH',      0.0,       130, 'Frankfurt–Hahn Airport'),
       (302, 'FRA', 'EDDF',      1.2,       130, 'Frankfurt Airport'),
       (303, 'TXL', 'EDDT',      1.3,       128, 'Berlin Tegel Airport'),
       (304, 'SXF', 'EDDB',      0.0,       128, 'Berlin Schönefeld Airport'),
       (305, 'DTM', 'EDLW',      0.0,       129, 'Dortmund Airport'),
       (306, 'HHN', 'EDFH',      1.1,       101, 'John F. Kennedy International Airport'),
       (307, 'LGA', 'KLGA',      0.0,       101, 'LaGuardia Airport'),
       (308, 'LAX', 'KLAX',      0.0,       102, 'Los Angeles International Airport'),
       (309, 'ATL', 'KATL',      0.0,       103, 'Hartsfield–Jackson Atlanta International Airport'),
       (310, 'MIA', 'KMIA',      0.0,       104, 'Miami International Airport'),
       (311, 'MDW', 'KMDW',      0.0,       105, 'Midway International Airport'),
       (312, 'RFD', 'KRFD',      0.0,       105, 'Chicago Rockford International Airport'),
       (313, 'ORD', 'KORD',      0.0,       105, "O'Hare International Airport"),
       (314, 'DCA', 'KDCA',      0.0,       106, 'Ronald Reagan Washington National Airport'),
       (315, 'IAD', 'KIAD',      0.0,       106, 'Washington Dulles International Airport'),
       (316, 'LCY', 'EGLC',      0.0,       107, 'London City Airport'),
       (317, 'LHR', 'EGLL',      1.5,       107, 'Heathrow Airport'),
       (318, 'LGW', 'EGKK',      0.0,       107, 'Gatwick Airport'),
       (319, 'EDI', 'EGPH',      0.0,       108, 'Edinburgh Airport'),
       (320, 'LPL', 'EGGP',      0.0,       109, 'Liverpool John Lennon Airport'),
       (321, 'MAN', 'EGCC',      0.0,       110, 'Manchester Airport'),
       (322, 'PIK', 'EGPK',      0.0,       111, 'Glasgow Prestwick Airport'),
       (323, 'GLA', 'EGPF',      0.0,       111, 'Glasgow Airport'),
       (324, 'IEV', 'UKKK',      0.0,       112, 'Igor Sikorsky Kyiv International Airport (Zhuliany)'),
       (325, 'KBP', 'UKBB',      0.0,       112, 'Boryspil International Airport'),
       (326, 'LWO', 'UKLL',      0.0,       113, 'Lviv Danylo Halytskyi International Airport'),
       (327, 'ODS', 'UKOO',      0.0,       114, 'Odessa International Airport'),
       (328, 'DXB', 'OMDB',      0.0,       115, 'Dubai International Airport'),
       (329, 'DWC', 'OMDW',      0.0,       115, 'Al Maktoum International Airport'),
       (330, 'AUH', 'OMAA',      0.0,       116, 'Abu Dhabi International Airport'),
       (331, 'SHJ', 'OMSJ',      0.0,       117, 'Sharjah International Airport'),
       (332, 'WAW', 'EPWA',      0.0,       118, 'Warsaw Chopin Airport'),
       (333, 'WMI', 'EPMO',      0.0,       118, 'Warsaw Modlin Airport'),
       (334, 'POZ', 'EPPO',      0.0,       119, 'Poznań–Ławica Henryk Wieniawski Airport'),
       (335, 'RZE', 'EPRZ',      0.0,       120, 'Rzeszów–Jasionka Airport'),
       (336, 'KRK', 'EPKK',      0.0,       121, 'John Paul II International Airport Kraków–Balice'),
       (337, 'HEL', 'EFHK',      0.0,       122, 'Helsinki Airport'),
       (338, 'LPP', 'EFLP',      0.0,       123, 'Lappeenranta Airport'),
       (339, 'CDG', 'LFPG',      0.0,       124, 'Charles de Gaulle Airport'),
       (340, 'ORY', 'LFPO',      0.0,       124, 'Orly Airport'),
       (341, 'BOD', 'LFBD',      0.0,       125, 'Bordeaux–Mérignac Airport'),
       (342, 'LIL', 'LFQQ',      0.0,       126, 'Lille Airport'),
       (343, 'MRS', 'LFML',      0.0,       127, 'Marseille Provence Airport');


INSERT INTO buyfly.user
       (id,  dateOfBirth, documentExpirationDate, documentNumber, email,             enabled, firstName, lastName,  password, phoneNumber, registered, sex,      userRole)
VALUES (10, '1989-12-21',           '2019-07-10',     'TR645698', 'smith@gmail.com', false,   'John',    'Smith',   'qwe123', '1227984523',      true, 'MALE',   'CUSTOMER'),
       (11, '1993-08-05',           '2025-11-03',     'EM321753', 'ellie@gmail.com', false,   'Alice',   'McElroy', 'qwe123', '0557894535',      true, 'FEMALE', 'CUSTOMER'),
       (12, '1991-08-05',           '2020-08-26',     'EM378173', 'godie@gmail.com', true,    'Natalie', 'Dormer',  'qwe123', '0547842430',      true, 'FEMALE', 'ADMIN'),
       (13, '1993-08-05',           '2025-11-03',     'RI328653', 'manny@gmail.com', true,    'Bertie',  'Wooster', 'qwe123', '0557894556',      true, 'MALE',   'MANGER');


INSERT INTO buyfly.flight
       (id,  boardingTime,          departureTime,         arrivalTime,           gate, number,  aircraft_id, dest_airport_id, origin_airport_id)
VALUES (701, '2017-07-25 15:47:00', '2017-07-25 16:15:00', '2017-07-25 23:47:00', '37', 'S7 1155',       402,             307,               316),
       (702, '2017-05-03 01:15:00', '2017-05-03 01:35:00', '2017-05-03 14:37:00', '29', 'WK 2200',       403,             328,               313),
       (703, '2017-03-15 13:20:00', '2017-03-15 13:45:00', '2017-03-15 16:03:00', '3H', 'PG 909' ,       404,             326,               332),
       (704, '2017-10-25 15:47:00', '2017-10-25 16:15:00', '2017-07-25 22:43:00', '37', 'QR 234' ,       405,             340,               330);


INSERT INTO buyfly.boardingpass
      (id, additionalWeightPrice, bookingDate,          checkedLuggagePrice, status,   user_id, flight_id, seat_id)
VALUES (801,                20.5, '2016-12-20 21:18:43',                0.0, 'BOOKED',      10,       701,     501),
       (802,                20.5, '2016-12-20 21:18:43',                0.0, 'BOOKED',      10,       701,     502);

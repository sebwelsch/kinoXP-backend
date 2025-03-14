-- Insert sample users (cinema employees)
INSERT INTO users (username, password) VALUES
('admin', '$2a$10$FO.CdSO4lFbrYX1SHMakYeqarGqDTDcFdUvD1E7/PSts2lJvuSX5O');

-- Insert sample cinema
INSERT INTO cinema (name, description, location) VALUES
('KinoXP', 'The worlds best cinema!', 'Copenhagen');

-- Insert theater halls
INSERT INTO theater_halls (cinema_id, name, seats, `rows`) VALUES
(1, 'Small Hall', 240, 20),
(1, 'Large Hall', 400, 25);

-- Insert movies
INSERT INTO movies (name, description, age_rating, category, actors, duration, cover_image) VALUES
('Mickey 17', 'Adapted from the novel Mickey7 by Edward Ashton, this stars Robert Pattinson as an "expendable" - a disposable crew member on a space mission, selected for dangerous tasks because he can be renewed if his body dies, with his memories largely intact. With one regeneration, though, things go very wrong.', 15, 'Science Fiction', '["Robert Pattinson", "Mark Ruffalo", "Naomi Ackie", "Patsy Ferran"]', 137, '../img/mickey17.png'),
('Dune: Part Two', 'The sequel to Denis Villeneuves adaptation of Frank Herberts epic novel, following Paul Atreides as he seeks revenge and embraces his destiny.', 13, 'Science Fiction', '["Timothée Chalamet", "Zendaya", "Rebecca Ferguson", "Javier Bardem"]', 165, '../img/dune2.png'),
('Oppenheimer', 'Christopher Nolans biographical thriller about the life of J. Robert Oppenheimer and the creation of the atomic bomb.', 16, 'Drama', '["Cillian Murphy", "Emily Blunt", "Matt Damon", "Robert Downey Jr."]', 180, '../img/oppenheimer.png'),
('The Batman', 'A dark and gritty take on the iconic superhero, following Bruce Wayne in his second year as Gothams vigilante.', 15, 'Action', '["Robert Pattinson", "Zoë Kravitz", "Paul Dano", "Colin Farrell"]', 176, '../img/thebatman.png'),
('Avatar: The Way of Water', 'James Camerons long-awaited sequel takes us back to Pandora and follows the Sully family as they face new challenges.', 12, 'Science Fiction', '["Sam Worthington", "Zoe Saldana", "Sigourney Weaver", "Kate Winslet"]', 192, '../img/avatar2.png'),
('John Wick: Chapter 4', 'Keanu Reeves returns as the legendary assassin, facing new enemies and expanding the world of John Wick.', 16, 'Action', '["Keanu Reeves", "Donnie Yen", "Bill Skarsgård", "Laurence Fishburne"]', 169, '../img/johnwick4.png'),
('Mission: Impossible – Dead Reckoning Part One', 'Ethan Hunt and his IMF team take on their most dangerous mission yet.', 13, 'Action', '["Tom Cruise", "Rebecca Ferguson", "Simon Pegg", "Hayley Atwell"]', 163, '../img/missionimpossible7.png'),
('Spider-Man: Across the Spider-Verse', 'Miles Morales embarks on a multiversal adventure, encountering different Spider-People and facing a new threat.', 7, 'Animation', '["Shameik Moore", "Hailee Steinfeld", "Oscar Isaac", "Jake Johnson"]', 140, '../img/spiderman_across.png'),
('The Marvels', 'Carol Danvers, Kamala Khan, and Monica Rambeau join forces to face a cosmic threat in this Marvel Cinematic Universe adventure.', 12, 'Superhero', '["Brie Larson", "Iman Vellani", "Teyonah Parris", "Samuel L. Jackson"]', 125, '../img/themarvels.png');

-- Insert shows
INSERT INTO shows (movie_id, hall_id, start_date, end_date, time) VALUES
(1, 1, '2025-03-18', '2025-06-18', '["13:00", "17:00", "22:00"]'),
(2, 2, '2025-03-22', '2025-06-22', '["14:00", "18:00", "21:00"]'),
(3, 1, '2025-03-26', '2025-06-26', '["12:30", "16:30", "20:30"]'),
(4, 2, '2025-03-28', '2025-06-28', '["13:45", "17:45", "21:45"]'),
(5, 1, '2025-04-01', '2025-07-01', '["14:00", "18:00", "22:00"]'),
(6, 2, '2025-04-05', '2025-07-05', '["15:00", "19:00", "23:00"]'),
(7, 1, '2025-04-08', '2025-07-08', '["12:15", "16:15", "20:15"]'),
(8, 2, '2025-04-12', '2025-07-12', '["13:30", "17:30", "21:30"]'),
(9, 1, '2025-04-15', '2025-07-15', '["14:30", "18:30", "22:30"]');

-- Insert bookings
INSERT INTO bookings (show_id, time, seats, date, customer_name, customer_email) VALUES
(1, '13:00', 2, '2025-03-19', 'Michael Scott', 'michael@dundermifflin.com'),
(2, '18:00', 4, '2025-03-23', 'Pam Beesly', 'pam@dundermifflin.com'),
(3, '12:30', 3, '2025-03-27', 'Dwight Schrute', 'dwight@dundermifflin.com'),
(4, '17:45', 5, '2025-03-29', 'Jim Halpert', 'jim@dundermifflin.com'),
(5, '14:00', 2, '2025-04-03', 'Angela Martin', 'angela@dundermifflin.com'),
(6, '19:00', 6, '2025-04-06', 'Stanley Hudson', 'stanley@dundermifflin.com'),
(7, '16:15', 4, '2025-04-09', 'Phyllis Vance', 'phyllis@dundermifflin.com'),
(8, '13:30', 2, '2025-04-13', 'Kevin Malone', 'kevin@dundermifflin.com'),
(9, '14:30', 3, '2025-04-16', 'Oscar Martinez', 'oscar@dundermifflin.com');

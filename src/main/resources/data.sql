-- Create countries
INSERT INTO country (code, picture)
    VALUES
        ('SRB', 'https://tenisu.latelier.co/resources/Serbie.png'),
        ('USA', 'https://tenisu.latelier.co/resources/USA.png'),
        ('SUI', 'https://tenisu.latelier.co/resources/Suisse.png'),
        ('ESP', 'https://tenisu.latelier.co/resources/Espagne.png');

-- Create players
INSERT INTO player (id, first_name, last_name, short_name, sex, country_code, picture, rank, points, weight, height, age)
    VALUES
        (52, 'Novak', 'Djokovic', 'N.DJO', 'M', 'SRB', 'https://tenisu.latelier.co/resources/Djokovic.png', 2, 2542, 80000, 188, 31),
        (95, 'Venus', 'Williams', 'V.WIL', 'F', 'USA', 'https://tenisu.latelier.co/resources/Venus.webp', 52, 1105, 74000, 185, 38),
        (65, 'Stan', 'd', 'S.WAW', 'M', 'SUI', 'https://tenisu.latelier.co/resources/Wawrinka.png', 21, 1784, 81000, 183, 33),
        (102, 'Serena', 'Williams', 'S.WIL', 'F', 'USA', 'https://tenisu.latelier.co/resources/Serena.png', 10, 3521, 72000, 175, 37),
        (17, 'Rafael', 'Nadal', 'R.NAD', 'M', 'ESP', 'https://tenisu.latelier.co/resources/Nadal.png', 1, 1982, 85000, 185, 33);

-- Create players latest match results
INSERT INTO player_last (player_id, last)
    VALUES
        (52, 1), (52, 1), (52, 1), (52, 1), (52, 1),
        (95, 0), (95, 1), (95, 0), (95, 0), (95, 1),
        (65, 1), (65, 1), (65, 1), (65, 0), (65, 1),
        (102, 0), (102, 1), (102, 1), (102, 1), (102, 0),
        (17, 1), (17, 0), (17, 0), (17, 0), (17, 1);
insert into car (make, model, production_start_date, production_end_date)
values
    ('Honda', 'Prelude III', 1987, 1991),
    ('Citreon', 'Xantia', 1993, 2001),
    ('Ferrari', 'F430', 2004, 2009),
    ('Honda', 'Civic II', 1979, 1983),
    ('Audi', 'A4 B6', 2000, 2006);

insert into service_ticket (description, start_date, end_date, car_id)
values
    ('2f9x1UzvvC', '2003-04-25','2003-09-09', 1),
    ('gXqE0sklxt', '2004-07-14','2004-12-11', 1),
    ('8POzKoXAC8', '2005-01-23','2005-12-05', 1),
    ('fiek7Pmjdy', '2007-06-20','2008-05-21', 2),
    ('IXhZaEcwTQ', '2009-06-21','2010-11-30', 3),
    ('l0nangA0Bv', '2011-05-06','2012-06-14', 4),
    ('smtQKvyxeB', '2014-02-27','2014-08-29', 5),
    ('3lxlZufkbS', '2016-03-08','2016-12-27', 2),
    ('mONiAB9Ow2', '2017-07-14','2017-10-17', 1);

insert into part (days_to_dispatch, description, available, name, price, car_id)
values
    (26,'cKlpbT5CWu', true, 'RDO9HVTiMS',364.00, 1),
    (14,'QmQvZsS8Ec', true, 'pHWAY2KvJE',929.00, 2),
    (7, 'YfoaqFcpGt', true, 'eMUE9SBJMa',827.00, 3),
    (15,'AUBhGfjRME', true, 'wFcOtoJKyS',495.00, 4),
    (4, 'LIMYXQFmkx', true, 'QhjBTmnY8N',205.00, 5),
    (27,'LIMYXQFmkx', true, 'mVUiK9ionE',406.00, 1),
    (12,'YfoaqFcpGt', true, 'xUvS1SnJR0',857.00, 2);

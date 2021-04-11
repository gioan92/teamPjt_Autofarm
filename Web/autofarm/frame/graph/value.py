

class CO2:
    def __init__(self, date, time, co2):
        self.date = date;
        self.time = time;
        self.co2 = co2;

    def __str__(self):
        return str(self.date) + ' '  + str(self.time) + ' '  + str(self.co2);


class Temperature:
    def __init__(self, date, time, tem):
        self.date = date;
        self.time = time;
        self.tem = tem;

    def __str__(self):
        return str(self.date) + ' ' + str(self.time) + ' ' + str(self.tem);


class Humidity:
    def __init__(self, date, time, hum):
        self.date = date;
        self.time = time;
        self.hum = hum;

    def __str__(self):
        return str(self.date) + ' '  + str(self.time) + ' '  + str(self.hum);
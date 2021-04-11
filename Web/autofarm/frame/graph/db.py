from frame.graph.connect import Db;
from frame.graph.sql import Sql;
from frame.graph.value import Temperature;
from frame.graph.value import Humidity;
from frame.graph.value import CO2;


class TemperatureDb(Db):
    def selectall(self):
        conn = super().getConnection();
        cursor = conn.cursor();
        cursor.execute(Sql.selectalltem);
        result1 = cursor.fetchall();
        result2 = [];
        for r in result1:
            temp = str(r[0]).split();
            date = temp[0];
            time = temp[1];
            temp_obj = Temperature(date, time, r[1]);
            result2.append(temp_obj);
        return result2;


class HumidityDb(Db):
    def selectall(self):
        conn = super().getConnection();
        cursor = conn.cursor();
        cursor.execute(Sql.selectallhum);
        result1 = cursor.fetchall();
        result2 = [];
        for r in result1:
            temp = str(r[0]).split();
            date = temp[0];
            time = temp[1];
            hum_obj = Humidity(date, time, r[1]);
            result2.append(hum_obj);
        return result2;

class CO2Db(Db):
    def selectall(self):
        conn = super().getConnection();
        cursor = conn.cursor();
        cursor.execute(Sql.selectallco2);
        result1 = cursor.fetchall();
        result2 = [];
        for r in result1:
            temp = str(r[0]).split();
            date = temp[0];
            time = temp[1];
            co2_obj = CO2(date, time, r[1]);
            result2.append(co2_obj);
        return result2;


if __name__ == '__main__':
    for data in CO2Db().selectall():
        print(data);
    print('==================================');
    temperature_data = TemperatureDb().selectall();
    humidity_data = HumidityDb().selectall();
    for _ in range(len(temperature_data)):
        print(_, temperature_data[_], humidity_data[_]);
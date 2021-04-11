from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.
from frame.graph.db import *


def graph(request):
    return render(request, 'graph/graph.html');

def graph_CO2(request):
    co2_data = CO2Db().selectall();
    categories = ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17',
                  '18', '19', '20', '21', '22', '23'];
    datas = [{
        'name': 'CO2',
        'type': 'line',
        'data': []
    }];
    for _ in range(-1, -25, -1):
        datas[0]['data'].insert(0, co2_data.pop().co2);
    context = {
        'categories':categories,
        'datas':datas,
    };
    return JsonResponse(context);

def graph_temphum(request):
    temperature_data = TemperatureDb().selectall();
    humidity_data = HumidityDb().selectall();
    categories = ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16',
                  '17', '18', '19', '20', '21', '22', '23'];
    datas = [{
        'name': 'humidity',
        'type': 'line',
        'yAxis': 1,
        'data': [],
        'tooltip': {
            'valueSuffix': ' %'
        }

    }, {
        'name': 'Temperature',
        'type': 'line',
        'data': [],
        'tooltip': {
            'valueSuffix': '°C'
        }
    }];
    for _ in range(-1, -25, -1):
        datas[0]['data'].insert(0, humidity_data.pop().hum);
        datas[1]['data'].insert(0, temperature_data.pop().tem);
    context = {
        'categories':categories,
        'datas':datas,
    };
    return JsonResponse(context);
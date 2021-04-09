from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.
def graph(request):
    return render(request, 'graph/graph.html');

def graph_CO2(request):
    datas = [{
        'name': 'CO2',
        'type': 'line',
        'data': [1320, 1300, 1275, 1240, 1200, 1163, 1155, 1189, 1230, 1257, 1288, 1311]
    }];
    context = {
        'datas':datas,
    };
    return JsonResponse(context);

def graph_temphum(request):
    datas = [{
        'name': 'humidity',
        'type': 'line',
        'yAxis': 1,
        'data': [49.9, 71.5, 80.4, 88.2, 86.0, 76.0, 64.6, 68.5, 67.4, 70.1, 72.6, 74.4],
        'tooltip': {
            'valueSuffix': ' %'
        }

    }, {
        'name': 'Temperature',
        'type': 'line',
        'data': [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6],
        'tooltip': {
            'valueSuffix': '°C'
        }
    }];
    context = {
        'datas':datas,
    };
    return JsonResponse(context);
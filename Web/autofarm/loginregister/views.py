from django.shortcuts import render

# Create your views here.
def login(request):
    return render(request, 'loginregister/login.html');

def register(request):
    return render(request, 'loginregister/register.html');




## python django 환경 구축하기



1) 파이참에서 Python Project 생성



<u>**2. interpreter setting anaconda로 변경**</u>



3) pip install django



4) pip install mysqlclient



5) python manage.py startapp [appname]



6) App 설정 변경

* App하위에 templates 디렉토리 생성

* config - settings.py를 수정

```python
INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'hello' ## 추가
]	## ex) hello가 App name이다. ##

TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [],
        'DIRS': [os.path.join(BASE_DIR, 'templates')], ## 추가
        'APP_DIRS': True,
        'OPTIONS': {
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        },
    },
]


```



* config - urls.py를 수정

```python
urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('hello.urls')), ## 추가
    # 127.0.0.1: 뒤에 아무것도 안 쓰면 hello.urls로 가세요라는 의미
]
```



  - hello 폴더에 urls.py를 복사
  - urls.py 수정

```python
urlpatterns = [
    path('admin/', admin.site.urls),
    path('', views.home, name='home'), ## 추가
    # views 안의 home이 동작하게 해주세요라는 의미
]
```



  - views.py 파일에 home 함수 추가

```python
def home(request):
    return render(request, 'home.html');
```



  - template 폴더에 home.html 생성

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Hello Django world !!!</h1>
</body>
</html>
```



5) python manage.py runserver 또는 python manage.py runserver 80 (포트 :8000 없어도 됨)

6) Browser(127.0.0.1) 접속




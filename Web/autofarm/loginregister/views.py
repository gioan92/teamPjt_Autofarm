from django.shortcuts import render, redirect
from frame.loginregister.db import UserDb
# Create your views here.
def login(request):
    return render(request, 'loginregister/login.html');

def register(request):
    return render(request, 'loginregister/register.html');






def registerimpl(request):
    u_id = request.POST['u_id']
    u_name = request.POST['u_name']
    u_pwd_1 = request.POST['u_pwd_1']
    u_pwd_2 = request.POST['u_pwd_2']
    u_contact = request.POST['u_contact']
    u_addr = request.POST['u_addr']
    if u_pwd_1 != u_pwd_2:
        context = {
            'message': '회원가입 실패'
        };
        return render(request, 'loginregister/register.html', context);
    else:
        try:
            UserDb().insert(u_id, u_name, u_pwd_1, u_contact, u_addr);
        except:
            context = {
                'message': '회원가입 실패'
            };
            return render(request, 'loginregister/register.html', context);
        context = {
            'login': 'success'
        };
        request.session['u_id'] = u_id
        request.session['u_name'] = u_name
        return render(request, 'autofarm/main.html', context)




# 로그인 처리하는 함수
def loginimpl(request):
    u_id = request.POST['u_id']
    u_pwd = request.POST['u_pwd']

    try:
        user = UserDb().selectid(u_id);
        # 비밀번호가 일치하는 경우, 로그인 성공한 경우
        if u_pwd == user.u_pwd:
            request.session['u_id'] = u_id
            request.session['u_name'] = user.u_name
            context = {
                'login':'success',
                'u_name': user.u_name
            };
        else:
            raise Exception
    # 로그인 실패인 경우
    except:
        context = {
            'login':'fail',
            'message': '로그인 실패'
        };
        return render(request, 'loginregister/login.html', context)
    return render(request, 'autofarm/main.html', context)






def logout(request):
    if 'u_id' in request.session:
        if request.session['u_id']:
            del request.session['u_id']
    if 'u_name' in request.session:
        if request.session['u_name']:
            del request.session['u_name']
    return redirect('base')
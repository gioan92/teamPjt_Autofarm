from django.shortcuts import render
from frame.shop.db import ShopDb

# Create your views here.
def shop(request):
    shoplist = ShopDb().selectall()
    context = {
        'shoplist': shoplist
    }
    return render(request, 'shop/shop.html', context);

def shop_detail(request):
    i_id = request.GET['i_id']
    item = ShopDb().selectbyid(int(i_id))
    context = {
        'item': item
    }
    return render(request, 'shop/shop_detail.html', context);
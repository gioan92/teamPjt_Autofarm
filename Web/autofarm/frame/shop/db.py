from frame.shop.sql import Sql
from frame.shop.connect import Db
from frame.shop.value import Shop


class ShopDb(Db):
    def selectall(self):
        conn = super().getConnection();
        cursor = conn.cursor();
        cursor.execute(Sql.selectall);
        result = cursor.fetchall();
        all = [];
        for i in result:
            item = Shop(i[0], i[1], i[2], i[3], i[4], i[5]);
            all.append(item);
        super().close(conn, cursor);
        return all;
    
    def selectbyid(self, i_id):
        conn = super().getConnection();
        cursor = conn.cursor();
        cursor.execute(Sql.selectbyid % i_id);
        i = cursor.fetchone();
        item = Shop(i[0], i[1], i[2], i[3], i[4], i[5]);
        super().close(conn, cursor);
        return item;






# userlist Test Function ..........
def insert_test():
    users = UserDb().select_review();
    for u in users:
        print(u);


# def userlistone_test():
#     users = UserDb().selectone('id01');
#     print(users);

if __name__ == '__main__':
    insert_test();

from frame.loginregister.sql import Sql
from frame.loginregister.connect import Db
from frame.loginregister.value import User


class UserDb(Db):
    def insert(self,u_id,u_name,u_pwd,u_contact,u_addr):
        try:
            conn = super().getConnection();
            print(conn)
            cursor = conn.cursor();
            cursor.execute(Sql.userinsert % (u_id,u_pwd,u_name,u_contact,u_addr));
            conn.commit();
        except Exception as e:
            conn.rollback();
            print('예외발생',e)
            raise Exception;
        finally:
            super().close(conn, cursor);

    def selectid(self, u_id):
        conn = super().getConnection();
        cursor = conn.cursor();
        cursor.execute(Sql.selectid % u_id);
        u = cursor.fetchone();
        user = User(u[0], u[1], u[2], u[3], u[4]);
        super().close(conn, cursor);
        return user;






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

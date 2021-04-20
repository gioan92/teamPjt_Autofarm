






class User:
    def __init__(self, u_id,u_pwd,u_name, u_contact, u_addr):
        self.u_id = u_id;
        self.u_pwd = u_pwd;
        self.u_name = u_name;        
        self.u_contact = u_contact;
        self.u_addr = u_addr;

    def __str__(self):
        return self.u_id + ' ' + self.u_name + ' ' + self.u_pwd + ' '\
                +self.u_contact + ' ' + self.u_addr + ' ';

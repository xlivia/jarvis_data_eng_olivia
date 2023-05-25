class Employee:
    def __init__(self, employee_id, name, role_id, login_id):
        self.employee_id = employee_id
        self.name = name
        self.role_id = role_id
        self.login_id = login_id

class Department:
    def __init__(self, department_id, name):
        self.department_id = department_id
        self.name = name

class Project:
    def __init__(self, project_id, name):
        self.project_id = project_id
        self.name = name

class Role:
    def __init__(self, role_id, name):
        self.role_id = role_id
        self.name = name

class Login:
    def __init__(self, login_id, username, password):
        self.login_id = login_id
        self.username = username
        self.password = password

class DepartmentEmployee:
    def __init__(self, department_id, employee_id):
        self.department_id = department_id
        self.employee_id = employee_id

class ProjectEmployee:
    def __init__(self, project_id, employee_id):
        self.project_id = project_id
        self.employee_id = employee_id

class RoleEmployee:
    def __init__(self, role_id, employee_id):
        self.role_id = role_id
        self.employee_id = employee_id

class EmployeeLogin:
    def __init__(self, employee_id, login_id):
        self.employee_id = employee_id
        self.login_id = login_id
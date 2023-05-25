class Member:
    def __init__(self, member_id, name):
        self.member_id = member_id
        self.name = name

class Loan:
    def __init__(self, loan_id, member_id, book_id):
        self.loan_id = loan_id
        self.member_id = member_id
        self.book_id = book_id

class Book:
    def __init__(self, book_id, title):
        self.book_id = book_id
        self.title = title
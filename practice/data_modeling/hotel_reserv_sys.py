class Hotel:
    def __init__(self, hotel_id, name):
        self.hotel_id = hotel_id
        self.name = name

class Room:
    def __init__(self, room_id, hotel_id):
        self.room_id = room_id
        self.hotel_id = hotel_id

class Reservation:
    def __init__(self, reservation_id, customer_id, room_id):
        self.reservation_id = reservation_id
        self.customer_id = customer_id
        self.room_id = room_id

class Customer:
    def __init__(self, customer_id, name):
        self.customer_id = customer_id
        self.name = name

class Service:
    def __init__(self, service_id, name):
        self.service_id = service_id
        self.name = name

class Bill:
    def __init__(self, bill_id, reservation_id):
        self.bill_id = bill_id
        self.reservation_id = reservation_id
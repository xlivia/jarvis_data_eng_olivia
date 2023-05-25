class OnlineCustomer:
    def __init__(self, customer_id, name):
        self.customer_id = customer_id
        self.name = name

class ShoppingCart:
    def __init__(self, cart_id, customer_id):
        self.cart_id = cart_id
        self.customer_id = customer_id

class ShoppingCartItem:
    def __init__(self, item_id, cart_id, product_id):
        self.item_id = item_id
        self.cart_id = cart_id
        self.product_id = product_id

class Product:
    def __init__(self, product_id, name):
        self.product_id = product_id
        self.name = name
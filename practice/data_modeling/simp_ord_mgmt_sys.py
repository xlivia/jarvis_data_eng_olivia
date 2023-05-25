class Customer:
    def __init__(self, customer_id, name):
        self.customer_id = customer_id
        self.name = name

class Order:
    def __init__(self, order_id, customer_id):
        self.order_id = order_id
        self.customer_id = customer_id

class OrderItem:
    def __init__(self, order_item_id, order_id, product_id):
        self.order_item_id = order_item_id
        self.order_id = order_id
        self.product_id = product_id

class Product:
    def __init__(self, product_id, name):
        self.product_id = product_id
        self.name = name

class Invoice:
    def __init__(self, invoice_id, order_id):
        self.invoice_id = invoice_id
        self.order_id = order_id

class InvoiceItem:
    def __init__(self, invoice_item_id, invoice_id, product_id):
        self.invoice_item_id = invoice_item_id
        self.invoice_id = invoice_id
        self.product_id = product_id
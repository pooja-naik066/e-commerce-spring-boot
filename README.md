<h3> e-commerce-backend-application</h3>
<h4>Techstack : Java, Spring Boot, MySQL, Hibernate</h4>
<h6>REST API endpoints</h6>
<hr>
<h6>Customer endpoints</h6>
POST <code>/customers</code> <br>

Description : Add a new customer <br>

<code>{
  "name": "string",
  "email": "string",
  "phone": "string"
}
</code>

<hr>
PUT <code>/customers/{customerId}</code> <br>

Description : Update an existing customer <br>

<code>{
  "name": "string",
  "email": "string",
  "phone": "string"
}
</code>
<hr>
GET <code>/customers</code> <br>

Description : Retrieve all customers <br>
<hr>
GET <code>/customers/{customerId}</code> <br>

Description : Retrieve the details of a specific customer by ID<br>
<hr>
DELETE <code>/customers/{customerId}</code> <br>

Description : Delete a customer by ID <br>
<hr>
<h6>Book endpoints</h6>
POST <code>/books</code> <br>

Description : Add a new book to the catalog <br>

<code>{
  "title": "string",
  "author": "string",
  "description": "string",
  "genre": "string",
  "price": 0.0,
  "quantity": 0
}
</code>

<hr>
PUT <code>/books/{bookId}</code> <br>

Description : Update an existing book<br>

<code>{
   "title": "string",
  "author": "string",
  "description": "string",
  "genre": "string",
  "price": 0.0,
  "quantity": 0
}
</code>
<hr>
GET <code>/books</code> <br>

Description :  Retrieve all books <br>
<hr>
GET <code>/books/{bookId}</code> <br>

Description : Retrieve the details of a specific book by ID<br>
<hr>
DELETE <code>/books/{bookId}</code> <br>

Description : Delete a book by ID <br>
<hr>
<h6>Cart endpoints</h6>
POST <code>/carts/{customerId}</code> <br>

Description : Add books to the customer's cart <br>

<code>{
  "bookId": 0,
  "quantity": 0
}
</code>
<hr>
PUT <code>/carts/{customerId}</code> <br>

Description : Update books in the customer's cart <br>

<code>{
  "bookId": 0,
  "quantity": 0
}
</code>
<hr>
GET <code>/carts/{customerId}</code> <br>

Description : Retrieve the cart of a specific customer <br>
<hr>
DELETE <code>/carts/{customerId}/remove/{bookId}</code> <br>
Description :  Remove a specific book from the customer's cart <br>
<hr>
<h6>Order Endpoints</h6> <br>
POST <code>/orders/{customerId}</code> <br>
Description : Place an order for all items in the customer's cart
<hr>
GET <code>/orders/{customerId}</code> <br>
Description : Retrieve all orders placed by a specific customer









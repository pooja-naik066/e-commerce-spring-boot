<h3> e-commerce-backend-application</h3>
<h4>Techstack : Java, Spring Boot, MySQL, Hibernate</h4>
<h6>REST API endpoints</h6>
<hr>
<h6>CUSTOMER</h6>
POST <code>/customers</code> <br>

Description : Add a customer <br>

<code>{
  "name": "string",
  "email": "string",
  "phone": "string"
}
</code>

<hr>
PUT <code>/customers/{customerId}</code> <br>

Description : Update a customer <br>

<code>{
  "name": "string",
  "email": "string",
  "phone": "string"
}
</code>
<hr>
GET <code>/customers</code> <br>

Description : Get all customers <br>
<hr>
GET <code>/customers/{customerId}</code> <br>

Description : Find the customer with the given id<br>
<hr>
DELETE <code>/customers/{customerId}</code> <br>

Description : Delete the customer with the given Id <br>
<hr>
<h6>BOOK</h6>
POST <code>/books</code> <br>

Description : Add a book <br>

<code>{
  "title": "string",
  "author": "string",
  "description": "string",
  "genre": "string",
  "price": 0,
  "quantity": 0
}
</code>

<hr>
PUT <code>/books/{bookId}</code> <br>

Description : Update a book<br>

<code>{
   "title": "string",
  "author": "string",
  "description": "string",
  "genre": "string",
  "price": 0,
  "quantity": 0
}
</code>
<hr>
GET <code>/books</code> <br>

Description : Get all books <br>
<hr>
GET <code>/books/{bookId}</code> <br>

Description : Find the book with the given Id<br>
<hr>
DELETE <code>/books/{bookId}</code> <br>

Description : Delete the book with the given Id <br>
<hr>
<h6>Cart</h6>
POST <code>/carts/{customerId}</code> <br>

Description : Add books to the cart <br>

<code>{
  "bookId": 0,
  "quantity": 0
}
</code>
<hr>






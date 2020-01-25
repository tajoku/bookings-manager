# bookings-manager
A simple back-end API layer to manage bookings.
A message broker (specifically RabbitMQ) is used to manage the events produced and consumed by and from the application.
A REST layer is exposed to manage booking operations (CRUD), specifically:

● Get list of all bookings
● Get booking by id
● Add booking
● Update bookings
● Delete booking

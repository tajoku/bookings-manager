# bookings-manager
A simple back-end API layer to manage bookings.
A message broker (specifically RabbitMQ) is used to manage the events produced and consumed by and from the application.
A REST layer is exposed to manage booking operations (CRUD), specifically:

1. Get list of all bookings
2. Get booking by id
3. Add booking
4. Update bookings
5. Delete booking

# TODO APP

### backend: Python + Flask + SQlite DB + ORM SQLAlchemy
### frontend: TypeScript + Angular
---

### Aby uruchomić:

`python3 server.py` w katalogu `projectV2`
`ng serve` w katalogu `projectV2/frontend/src`

dokumentacja api przy użyciu Swaggera znajduje się na `http://localhost:5000/api/ui/`:

![](https://i.imgur.com/ynkOPTF.png)
![](https://i.imgur.com/7jrYTEh.png)
![](https://i.imgur.com/ogOF8MR.png)

Aplikacja Angularowa na `http://localhost:4200/`

![](https://i.imgur.com/1d9fhTL.gif)



### Krótki opis:

Prosta aplikacja zarządzająca listą todo. Można tworzyć, przeglądać, uaktualniać, zaznaczać jako wykonane, usuwać obiekty Todo.

RESTowe CRUD API na backendzie, wykorzystałem ORM SQLAlchemy, podstawowe operacje CRUD - create, read, update, delete

### Model:

todo_id : number
name: string(32)
description: string(1024)
done: boolean
created_at: Date

import os
from config import db
from models import Todo


TODOS = [
    {"name": "Spotkanie w parku", "description": "o godzinie 15 spotkanie z Adamem"},
    {"name": "Witaminy", "description": "wziąć witaminy B i C"},
    {"name": "Paszport", "description": "Zabrac paszport na lotnisko", "done": True},
]

if os.path.exists("people.db"):
    os.remove("people.db")

db.create_all()

for todo in TODOS:
    p = Todo(name=todo.get("name"), description=todo.get("description"), done=todo.get("done"))
    db.session.add(p)

db.session.commit()

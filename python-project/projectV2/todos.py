"""
Rest actions for todos module
"""

from flask import make_response, abort
from config import db
from models import Todo, TodoSchema


def read_all():
    """
    This function responds to a request for /api/todos
    with the complete lists of todos

    :return:        json string of list of todos
    """
    todo = Todo.query.all()

    todo_schema = TodoSchema(many=True)
    data = todo_schema.dump(todo)
    return data


def read_one(todo_id):
    f"""
    This function responds to a request for /api/todos/{todo_id}
    with one matching todo

    :param todo_id:   Id of todo to find
    :return:            todo matching id
    """
    todo = Todo.query.filter(Todo.todo_id == todo_id).one_or_none()

    if todo is not None:

        todo_schema = TodoSchema()
        data = todo_schema.dump(todo)
        return data

    else:
        abort(
            404,
            "Todo not found for Id: {todo_id}".format(todo_id=todo_id),
        )


def create(todo):
    """
    This function creates a new todo in the todos structure

    :param todo:  todo to create in todos structure
    :return:        201 on success, 409 on todo exists
    """
    name = todo.get("name")
    description = todo.get("description")

    existing_todo = (
        Todo.query.filter(Todo.name == name)
            .filter(Todo.description == description)
            .one_or_none()
    )

    if existing_todo is None:

        schema = TodoSchema()
        new_todo = schema.load(todo, session=db.session)

        db.session.add(new_todo)
        db.session.commit()

        data = schema.dump(new_todo)

        return data, 201

    else:
        abort(
            409,
            "Todo {name} {description} exists already".format(
                name=name, description=description
            ),
        )


def update(todo_id, todo):
    """
    This function updates an existing todo in the todos structure
    Throws an error if a todo with the name we want to update to
    already exists in the database.

    :param todo_id:   Id of the todo to update in the todos structure
    :param todo:      todo to update
    :return:           updated todo structure
    """
    update_todo = Todo.query.filter(
        Todo.todo_id == todo_id
    ).one_or_none()

    name = todo.get("name")
    description = todo.get("description")

    existing_todo = (
        Todo.query.filter(Todo.name == name)
            .filter(Todo.description == description)
            .one_or_none()
    )

    if update_todo is None:
        abort(
            404,
            "Todo not found for Id: {todo_id}".format(todo_id=todo_id),
        )

    elif (
            existing_todo is not None and existing_todo.todo_id != todo_id
    ):
        abort(
            409,
            "Todo {name} {description} exists already".format(
                name=name, description=description
            ),
        )

    else:

        schema = TodoSchema()
        update = schema.load(todo, session=db.session)

        update.todo_id = update_todo.todo_id

        db.session.merge(update)
        db.session.commit()

        data = schema.dump(update_todo)

        return data, 200


def delete(todo_id):
    """
    This function deletes a todo from the todos structure

    :param todo_id:   Id of the todo to delete
    :return:            200 on successful delete, 404 if not found
    """
    # Get the person requested
    todo = Todo.query.filter(Todo.todo_id == todo_id).one_or_none()

    # Did we find a person?
    if todo is not None:
        db.session.delete(todo)
        db.session.commit()
        return make_response(
            "Todo {todo_id} deleted".format(todo_id=todo_id), 200
        )

    # Otherwise, nope, didn't find that person
    else:
        abort(
            404,
            "Todo not found for Id: {todo_id}".format(todo_id=todo_id),
        )


def update_status(todo_id, status):
    """
    This function updates todo's status to done or not done (True/False)

    :param todo_id:   Id of the todo to delete
    :param status:   status = True - done otherwise False
    :return:         204 on successful, 404 if not found
    """

    update_todo = Todo.query.filter(
        Todo.todo_id == todo_id
    ).one_or_none()

    if update_todo is None:
        abort(
            404,
            "Todo not found for Id: {todo_id}".format(todo_id=todo_id),
        )

    else:
        new_todo = {"name": update_todo.get_name(), "description": update_todo.get_description(), "done": status}
        schema = TodoSchema()
        update = schema.load(new_todo, session=db.session)

        update.todo_id = update_todo.todo_id

        db.session.merge(update)
        db.session.commit()

        return 204

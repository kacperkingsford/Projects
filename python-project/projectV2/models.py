from datetime import datetime

from sqlalchemy import true

from config import db, ma


class Todo(db.Model):
    __tablename__ = "todo"
    todo_id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(32))
    description = db.Column(db.String(1024))
    done = db.Column(db.Boolean, default=False, nullable=False)
    created_at = db.Column(
        db.DateTime, default=datetime.utcnow, onupdate=datetime.utcnow
    )

    def get_name(self):
        return self.name

    def get_description(self):
        return self.description

    def get_done(self):
        return self.done


class TodoSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Todo
        sqla_session = db.session
        load_instance = true

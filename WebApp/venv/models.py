from datetime import datetime
from flask_sqlalchemy import SQLAlchemy


db = SQLAlchemy()


class Pacient(db.Model):
    __tablename = "pacient"
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(20), nullable=False)
    surrname = db.Column(db.String(30), nullable=False)
    pesel = db.Column(db.String(11), unique=True, nullable=False)
    email = db.Column(db.String(200), unique=True, nullable=False)
    password = db.Column(db.String(20), nullable=False)
    date_added = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)

    def __repr__(self):
        return f"Pacient('{self.login}', '{self.password}')"


class Doctor(db.Model):
    __tablename = "doctor"
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(20), nullable=False)
    surrname = db.Column(db.String(30), nullable=False)
    pesel = db.Column(db.String(11), unique=True, nullable=False)
    email = db.Column(db.String(200), unique=True, nullable=False)
    password = db.Column(db.String(20), nullable=False)
    date_added = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)

    # Create a String 
    def __repr__(self):
        return f"Doctor('{self.login}', '{self.password}')"
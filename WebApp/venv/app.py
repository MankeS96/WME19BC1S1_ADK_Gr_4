from flask import Flask, jsonify, render_template
from datetime import datetime
from flask_sqlalchemy import SQLAlchemy
import time

# Create a Flask Instance
app = Flask(__name__)

# Add Database
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///db.adk'
# Initialize The Database
db = SQLAlchemy(app)


class Pacient(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(20), nullable=False)
    surrname = db.Column(db.String(30), nullable=False)
    pesel = db.Column(db.String(11), unique=True, nullable=False)
    login = db.Column(db.String(20), unique=True, nullable=False)
    password = db.Column(db.String(20), nullable=False)
    date_added = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)

    def __repr__(self):
        return f"Pacient('{self.login}', '{self.password}')"


class Doctor(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(20), nullable=False)
    surrname = db.Column(db.String(30), nullable=False)
    pesel = db.Column(db.String(11), unique=True, nullable=False)
    login = db.Column(db.String(20), unique=True, nullable=False)
    password = db.Column(db.String(20), nullable=False)
    date_added = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)

    # Create a String 
    def __repr__(self):
        return f"Doctor('{self.login}', '{self.password}')"

@app.route('/test')
def test():
    response_body = {
        "imie": "Maciej",
        "nazwisko": "Kacprzyk"
    }
    return response_body

@app.route('/loginPage')
def loginPage():
    return "<h1>Login page</h1>"

@app.route('/registerPage')
def registerPage():
    return "<h1>Register page</h1>"

if __name__ == "__main__":
    app.run(debug=True)
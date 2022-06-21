from flask import Flask, jsonify, request, abort
from models import db, Doctor, Pacient
from config import AplicationConfig
from flask_bcrypt import Bcrypt
# from Signal import o

# Create a Flask Instance
app = Flask(__name__)
app.config.from_object(AplicationConfig)

bcrypt = Bcrypt(app)
db.init_app(app)

with app.app_context():
    db.create_all()

# @app.route('/plot', methods=['GET'])
# def plotSignal():
#     data = o.normalized_sig
#     return data

@app.route('/login')
def loginPage():
    return "<h1>Login page</h1>"


@app.route('/register', methods=["POST"])
def registerPage():
    name = request.json["name"]
    surrname = request.json["surrname"]
    pesel = request.json["pesel"]
    email = request.json["email"]
    password = request.json["password"]

    user_exists = Doctor.query.filter_by(email=email, pesel=pesel).first() is not None

    if user_exists:
        abort(409)

    hashed_password = bcrypt.generate_password_hash(password)
    new_user = Doctor(name=name, surrname=surrname, pesel=pesel, email=email, password=hashed_password)
    db.session.add(new_user)
    db.session.commit()

    return jsonify({
        "id": new_user.id,
        "email": new_user.email,
    })


if __name__ == "__main__":
    app.run(debug=True)
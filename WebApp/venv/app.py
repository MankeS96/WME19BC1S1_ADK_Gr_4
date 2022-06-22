from flask import Flask, jsonify, request, abort
from models import db, Doctor, Pacient
from config import AplicationConfig
from flask_bcrypt import Bcrypt
from Signal import Signal

app = Flask(__name__)
app.config.from_object(AplicationConfig)

bcrypt = Bcrypt(app)
db.init_app(app)

with app.app_context():
    db.create_all()

@app.route('/plot', methods=['GET'])
def plotSignal():
    o = Signal()
    o.get_signal('C:/Users/MankeS/PycharmProjects/WME19BC1S1_ADK_Gr_4/WebApp/venv/nagranie_1.wav')
    o.normalize_signal()
    o.reconstruction()
    sigRecc = o.recon_sig
    data = list(sigRecc[:20000])
    return jsonify(data)

@app.route('/widmo', methods=['GET'])
def plotWidmo():
    o = Signal()
    o.get_signal('C:/Users/MankeS/PycharmProjects/WME19BC1S1_ADK_Gr_4/WebApp/venv/nagranie_1.wav')
    o.normalize_signal()
    o.reconstruction()
    signal = o.recon_sig
    freq, power = o.fft_power_freq(1, signal)
    sigFreq = list(freq[:20000])
    sigPower = list(power[:20000])
    return jsonify({'freq': sigFreq, 'power': sigPower})

@app.route('/obwiednia', methods=['GET'])
def plotObwiednia():
    o = Signal()
    o.get_signal('C:/Users/MankeS/PycharmProjects/WME19BC1S1_ADK_Gr_4/WebApp/venv/nagranie_1.wav')
    o.normalize_signal()
    o.reconstruction()
    o.env()
    rec = o.recon_sig
    env = o.signal_enve
    sigRec = list(rec[:20000])
    sigEnv = list(env[:20000])
    return jsonify({'rec': sigRec, 'env': sigEnv})

@app.route('/spektogram', methods=['GET'])
def plotSpektogram():
    o = Signal()
    o.get_signal('C:/Users/MankeS/PycharmProjects/WME19BC1S1_ADK_Gr_4/WebApp/venv/nagranie_1.wav')

    return jsonify('xd')

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
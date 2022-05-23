from flask import Flask, render_template, url_for

app = Flask(__name__)

@app.route("/helo")
def home():
    return "Hello, Flask!"

if __name__ == "__main__":
    from waitress import serve
    serve(app, host="0.0.0.0", port=8080)
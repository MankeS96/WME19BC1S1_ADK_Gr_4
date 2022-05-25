from dotenv import load_dotenv
import os

load_dotenv()

class AplicationConfig:
    # SECRET_KEY = os.environ["SECRET KEY"]
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    SQLALCHEMY_ECHO = True
    SQLALCHEMY_DATABASE_URI = r"sqlite:///./db.sqlite"
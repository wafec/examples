from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String, Boolean, Text
from sqlalchemy.orm import sessionmaker

engine = create_engine("mysql+mysqlconnector://root:supersecret@devstack:3306/keystone")

Base = declarative_base()


class Service(Base):
    __tablename__ = "service"

    id = Column(String, primary_key=True)
    type = Column(String)
    enabled = Column(Boolean)
    extra = Column(Text)


class Endpoint(Base):
    __tablename__ = "endpoint"

    id = Column(String, primary_key=True)
    legacy_endpoint_id = Column(String)
    interface = Column(String)
    service_id = Column(String)
    url = Column(Text)
    extra = Column(Text)
    enabled = Column(Boolean)
    region_id = Column(String)


Session = sessionmaker(bind=engine)
session = Session()

endpoints = session.query(Endpoint).all()

for endpoint in endpoints:
    print(f'Endpoint url {endpoint.url}')
print()
import pika

connection = pika.BlockingConnection(pika.ConnectionParameters(host='devstack', credentials=pika.PlainCredentials('stackrabbit', 'supersecretsecret')))
channel = connection.channel()

channel.exchange_declare(exchange='nova', exchange_type='topic')


def callback(ch, method, properties, body):
    print(f'[body] {body}')
    print(f'[method] {method}')
    print(f'[properties] {properties}')


channel.basic_consume(callback, queue='conductor', no_ack=True)
channel.start_consuming()
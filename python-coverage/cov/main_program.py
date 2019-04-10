from cov.class_one import ClassOne
from cov.class_two import ClassTwo
import socket
import time
import threading
import argparse
import coverage


cov_obj = coverage.Coverage()


def main_script():
    print('Main script call')
    c1 = ClassOne()
    c2 = ClassTwo()
    c1.calling_one()
    c2.calling_two()
    for i in range(0, 100):
        c1.calling_one_again()
    for i in range(0, 10):
        c2.calling_two_again()


def infinite_loop():
    print('Starting infinite loop')
    while True:
        main_script()
        time.sleep(2)


HOST = '127.0.0.1'
PORT = 65432


def start_socket():
    print('Socket entering')
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen(1)
        conn, addr = s.accept()
        with conn:
            print('Connected by', addr)
            while True:
                data = conn.recv(1024)
                if not data:
                    break
                command = data.decode()
                if command == 'start coverage':
                    if not cov_obj:
                        cov_obj.start()
                elif command == 'end coverage':
                    if cov_obj:
                        cov_obj.stop()
                        cov_obj.save()
                        cov_obj.html_report()
                elif command == 'main script':
                    main_script()

    print('Socket exit')


def start_server():
    print('Starting server')
    try:
        t1 = threading.Thread(target=start_socket)
        t2 = threading.Thread(target=infinite_loop)
        t1.daemon = True
        t2.daemon = True
        t1.start()
        t2.start()
        while True:
            time.sleep(100)
    except (KeyboardInterrupt, SystemExit):
        print('Exiting')


def start_client():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))
        while True:
            command = input('Command:')
            s.sendall(command.encode())


if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('option', choices=['server', 'client'])

    args = parser.parse_args()
    if args.option == 'server':
        start_server()
    elif args.option == 'client':
        start_client()

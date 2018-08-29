import http.server
import socketserver
import requests

class RequestHandler(http.server.SimpleHTTPRequestHandler):
    def do_GET(self):
        request_path = self.path
        print('---- Request Start ----')
        print(request_path)
        print(self.headers)
        r = requests.get('http://192.168.1.20/image' + request_path, headers=self.headers)
        self.send_response(r.status_code)
        for key in r.headers:
            value = r.headers[key]
            self.send_header(key, value)
        print("## THE TEXT ##")
        print(r.text)
        self.wfile.write(bytes(r.text, 'utf-8'))

def main():
    port = 8080
    handler = RequestHandler
    print('Listening on localhost:%s' % port)
    with socketserver.TCPServer(("", port), handler) as httpd:
        httpd.serve_forever()

if __name__ == "__main__":
    main()
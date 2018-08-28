import http.server
import socketserver

class RequestHandler(http.server.SimpleHTTPRequestHandler):
    def do_GET(self):
        request_path = self.path
        print('---- Request Start ----')
        print(request_path)
        print(self.headers)

        self.send_response(200)

def main():
    port = 8080
    handler = RequestHandler
    print('Listening on localhost:%s' % port)
    with socketserver.TCPServer(("", port), handler) as httpd:
        httpd.serve_forever()

if __name__ == "__main__":
    main()
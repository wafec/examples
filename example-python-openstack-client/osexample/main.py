import shade

shade.simple_logging(debug=True)

cloud = shade.openstack_cloud(cloud='devstack')

flavors = cloud.list_flavors()

for flavor in flavors:
    print("Flavor ID: %s, Name: %s" % (flavor.id, flavor.name))

endpoints = cloud.list_endpoints()

for endpoint in endpoints:
    print("Endpoint URL: %s, Type: %s" % (endpoint.url, "?"))
    service = cloud.get_service(endpoint.service_id)
    print("  ", "Service Name: %s, Type: %s" % (service.name, service.type))
package osexample;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.identity.v3.Endpoint;
import org.openstack4j.model.identity.v3.Service;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.OSFactory;

import java.util.List;

public class App {
    public static void main(String[] args) {
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://devstack/identity/v3")
                .credentials("admin", "supersecret", Identifier.byName("Default"))
                .scopeToDomain(Identifier.byName("Default"))
                .authenticate();

        System.out.println("## LISTING SERVICE ENDPOINTS");
        List<? extends Service> services = os.identity().serviceEndpoints().list();
        for (Service service : services) {
            System.out.println(String.format("Service ID: %s, Name: %s", service.getId(), service.getName()));
            for (Endpoint endpoint : service.getEndpoints()) {
                System.out.println(String.format("  Endpoint: %s", endpoint.getUrl()));
            }
        }

        System.out.println("## LISTING USERS");
        List<? extends User> users = os.identity().users().list();
        for (User user : users) {
            System.out.println(String.format("User ID: %s, Name: %s", user.getId(), user.getName()));
        }

        List<? extends Flavor> flavors = os.compute().flavors().list();
        System.out.println("## LISTING FLAVORS");
        for (Flavor flavor : flavors) {
            System.out.println(String.format("Flavor ID: %s, Name: %s", flavor.getId(), flavor.getName()));
        }

        System.out.println("## LISTING COMPUTE SERVICES");
        List<? extends org.openstack4j.model.compute.ext.Service> servicesCompute =
                os.compute().services().list();
        for (org.openstack4j.model.compute.ext.Service service : servicesCompute) {
            System.out.println(String.format("Compute Service ID: %s, Host: %s", service.getId(), service.getHost()));
        }
    }
}

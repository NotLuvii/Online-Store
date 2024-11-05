package unb.microservices.PCS;

import SharedDataTypes.ProductPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

//singleton class to manage db instance.
@Service
public class DBConnection {

    private RestTemplate restTemplate  = new RestTemplate();;
    private static volatile DBConnection DBInstance;
    private DBConnection () {}

    public static DBConnection getDBInstance() {
        if (DBInstance == null)
            DBInstance = new DBConnection();
        return DBInstance;
    }


    public void createProduct(String name, double cost, int quantity, String type) {
        String url = "http://localhost:8081/add_product?name={name}&cost={cost}&quantity={quantity}&type={type}";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("cost", cost);
        params.put("quantity", quantity);
        params.put("type", type);
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Headers: " + response.getHeaders());
        System.out.println("Body: " + response.getBody());
    }


    public Product readProduct(int product_ID) {
        // request product from ID on the DB, returns a // ready in db named get_product. EA
        String url = "http://localhost:8081/get_product?product_ID={product_ID}";
        ProductPacket productPacket = restTemplate.getForObject(url, ProductPacket.class, product_ID);
        return deSerialize(productPacket);
    }


    public void updateProduct(ProductPacket toUpdate) {
        String url = "http://localhost:8081/update_product";
        HttpEntity<ProductPacket> request = new HttpEntity<>(toUpdate);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
    }

    public void deleteProduct(int product_ID) {
        String url = "http://localhost:8081/delete_product?product_ID={product_ID}";
        ProductPacket productPacket = restTemplate.getForObject(url, ProductPacket.class, product_ID);
        //deSerialize(productPacket);
    }

    public void getCategory(String category) {
        String url = "http://localhost:8081/getCategory?category={category}";
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);
    }

    private ProductPacket serialize (Product toSerialize) {
        return new ProductPacket(toSerialize.getProduct_ID(), toSerialize.getName(), toSerialize.getCost(), toSerialize.getQuantity(), toSerialize.getCategory());
    }

    private Product deSerialize(ProductPacket toDeserialize) {
        ProductFactory PF = null;
        Product result = null;
        if (toDeserialize.type.equals("dairy")) {
            result = new DairyProduct(toDeserialize.product_ID, toDeserialize.name, toDeserialize.cost, toDeserialize.quantity, toDeserialize.type);
        }
        else if (toDeserialize.type.equals("meat")) {
            result = new MeatProduct(toDeserialize.product_ID, toDeserialize.name, toDeserialize.cost, toDeserialize.quantity, toDeserialize.type);
        }
        return result;
    }

}
package unb.microservices.PCS;

import SharedDataTypes.ProductPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class PCSApplication implements CommandLineRunner {

	/**
	 * go to http://localhost:8080
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Booting up the PCS system...");
			SpringApplication.run(PCSApplication.class, args);
		}
		catch(Exception e) {
			System.out.println("Something went wrong: " + e.getMessage() + "\nStack Trace: ");
			e.printStackTrace();
		}
		System.out.println("PCS System online");
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("here");
//		DBConnection db = DBConnection.getDBInstance();
//		ProductFactory PF = new MeatFactory();
//		Product p = PF.createProduct(1, "Tomahawk steak", 3, 6, "meat");
//		Product pp = db.deleteProduct(69);
//		System.out.println(pp.getName());

	}

}

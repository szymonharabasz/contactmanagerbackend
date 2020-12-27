package hello;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
public class Application {

  private List<Contact> data;
  public Application() {
    data = Arrays.asList(
      new Contact("Stefan Autorski","01234543210","al. Inna 12\nKrzyżówkowo\n11-111","steve228uk@gmail.com","stefanautorski.to",""),
      new Contact("Janko Walski","0123456789","ul. Zagadkowa 123\nSzarada Duża\n10-010 Polska","janko@walski.com","http://janko-walski.info","Kilka słów na temat Janko."),
      new Contact("Karen Nerka","2345678","ul. Nieistniejąca 21\nBrystol\n12-332 Polska","karenne@email.com","karenne.com","")
    );
  }

  @GetMapping("/contacts")
  @CrossOrigin(origins = "http://localhost:3000")
  public List<Contact> getAllContacts() {
    System.out.println("Size now " + data.size());
    return data;
  }
  @GetMapping("/contacts/{id}")
  @CrossOrigin(origins = "http://localhost:3000")
  public Contact getContact(@PathVariable("id") String id) {
    return data.stream().filter(c -> c.getId().equals(id)).findFirst().orElseThrow();
  }
  @DeleteMapping("/contacts/{id}")
  @CrossOrigin(origins = "http://localhost:3000")
  public void deleteContact(@PathVariable("id") String id) {
    System.out.println("Removing item with id " + id);
    System.out.println("Size before " + data.size());
    data = data.stream().filter(c -> !c.getId().equals(id)).collect(Collectors.toList());
    System.out.println("Size after " + data.size());
  }
  @PutMapping("/contacts")
  @CrossOrigin(origins = "http://localhost:3000")
  public void putContact(@RequestParam Contact contact) {
    System.out.println("Adding new contact " + contact);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

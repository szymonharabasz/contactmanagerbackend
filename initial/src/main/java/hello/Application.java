package hello;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
@RestController
public class Application {

  @Autowired
  private ContactRepository repository;

  @PostConstruct
  public void initializeRepository() {
    Stream<Contact> data = Stream.of(
      new Contact("Stefan Autorski","01234543210","al. Inna 12\nKrzyżówkowo\n11-111","steve228uk@gmail.com","stefanautorski.to",""),
      new Contact("Janko Walski","0123456789","ul. Zagadkowa 123\nSzarada Duża\n10-010 Polska","janko@walski.com","http://janko-walski.info","Kilka słów na temat Janko."),
      new Contact("Karen Nerka","2345678","ul. Nieistniejąca 21\nBrystol\n12-332 Polska","karenne@email.com","karenne.com","")
    );
    data.forEach(c -> {
      if (repository.findByName(c.getName()) == null) repository.save(c);
    });
  }

  @GetMapping("/contacts")
  @CrossOrigin(origins = "http://localhost:3000")
  public List<Contact> getAllContacts() {
    return repository.findAll();
  }
  @GetMapping("/contacts/{id}")
  @CrossOrigin(origins = "http://localhost:3000")
  public Contact getContact(@PathVariable("id") String id) {
    return repository.findById(id).orElseThrow();
  }
  @DeleteMapping("/contacts/{id}")
  @CrossOrigin(origins = "http://localhost:3000")
  public void deleteContact(@PathVariable("id") String id) {
    System.out.println("Removing item with id " + id);
    repository.deleteById(id);
  }
  @PostMapping("/contacts")
  @CrossOrigin(origins = "http://localhost:3000")
  public void addContact(@RequestBody Contact contact) {
    System.out.println("Adding new contact " + contact);
    repository.save(contact);
  }
  @PutMapping("/contacts/{id}")
  @CrossOrigin(origins = "http://localhost:3000")
  public void updateContact(@PathVariable("id") String id, @RequestBody Contact contact) {
    Contact c = repository.findById(id).orElseThrow();
    c.setName(contact.getName());
    c.setPhone(contact.getPhone());
    c.setAddress(contact.getAddress());
    c.setEmail(contact.getEmail());
    c.setWebsite(contact.getWebsie());
    c.setNotes(contact.getNotes());
    repository.save(c);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

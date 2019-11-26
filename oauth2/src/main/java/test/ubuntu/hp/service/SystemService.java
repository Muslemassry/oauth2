package test.ubuntu.hp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.ubuntu.hp.model.Person;

@RestController
@RequestMapping("/person")
public class SystemService {
	private static Map<Integer, Person> personMap;
	static {
		personMap = new HashMap<Integer, Person>();
		Person tempPerson = new Person(1, "Ahmed", 21, "Alex");
		personMap.put(tempPerson.getId(), tempPerson);
		tempPerson = new Person(2, "Badawi", 22, "Beheyra");
		personMap.put(tempPerson.getId(), tempPerson);
		tempPerson = new Person(3, "Camal", 23, "Cairo");
		personMap.put(tempPerson.getId(), tempPerson);
		tempPerson = new Person(4, "Dawood", 24, "Damanhour");
		personMap.put(tempPerson.getId(), tempPerson);
	}
	
//	@PreAuthorize("#oauth2.hasScope('write')")
	@GetMapping("/{id}")
	public List<Person> getPerson(@PathVariable Integer id) {
		List personList = new ArrayList();
		personList.add(personMap.get(id));
		return personList;
	}
	
//	@PreAuthorize("#oauth2.hasScope('read')")
	@GetMapping("/")
	public List<Person> getAllPersons() {
		List personList = new ArrayList(personMap.values());
		return personList;
	}
	
}

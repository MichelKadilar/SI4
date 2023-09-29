package users;

import io.FileManager;

import java.util.ArrayList;
import java.util.List;

public class PersonManager<T extends Person> {

    List<T> personList;

    public PersonManager(String filename) {
        buildPersonList(filename);
    }

    public PersonManager() {
        personList = new ArrayList<>();
    }

    public void buildPersonList(String filename) {
        this.personList = (List<T>) FileManager.createList(filename);
    }

    public List<T> getPersonList() {
        return personList;
    }

    public boolean addPerson(T person) {
        if (!this.personList.contains(person)) {
            return this.personList.add(person);
        } else return false;
    }

    public boolean removePerson(T person) {
        if (!this.personList.contains(person)) {
            return this.personList.remove(person);
        } else return false;
    }

    public void clearList() {
        this.personList.clear();
    }
}

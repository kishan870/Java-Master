package dev.lpa;

import java.util.*;

public class MapViewMain {
    public static void main(String[] args) {

        Map<String, Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(contact -> contacts.put(contact.getName(), contact));
        ContactData.getData("email").forEach(contact -> contacts.merge(contact.getName(), contact,
                Contact::mergeContactData));

        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        Set<String> keyView = contacts.keySet();
        System.out.println(keyView);

        Set<String> copyOfKeys = new TreeSet<>(contacts.keySet());
        System.out.println(copyOfKeys);

        if(contacts.containsKey("Linus Van Pelt")) {
            System.out.println("Linus Van Pelt is there with us");
        }

        //Since keyView is a view to Map's keys, if a key is deleted from keyView,
        //it's also deleted from Map
        System.out.println("-".repeat(90));
        keyView.remove("Daisy Duck");
        System.out.println(keyView);
        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        //copyOfKeys has no effect on Map
        System.out.println("-".repeat(90));
        copyOfKeys.remove("Linus Van Pelt");
        System.out.println(copyOfKeys);
        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        //Another illustration
        System.out.println("-".repeat(90));
        keyView.retainAll(List.of("Linus Van Pelt", "Charlie Brown",
                "Robin Hood", "Mickey Mouse"));
        System.out.println(keyView);
        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        //keyView is backed by contacts Map
        System.out.println("-".repeat(90));
        keyView.clear();
        System.out.println(contacts);

        //Add Operations cannot be used on keyView
        System.out.println("-".repeat(90));
        try {
            keyView.add("Daisy Duck");
            System.out.println(contacts);
        } catch (UnsupportedOperationException e) {
            System.out.println("Add method to keyView results in " + e);
            keyView.clear();
        }

        System.out.println("-".repeat(90));
        ContactData.getData("phone").forEach(contact -> contacts.put(contact.getName(), contact));
        ContactData.getData("email").forEach(contact -> contacts.merge(contact.getName(), contact,
                Contact::mergeContactData));
        System.out.println(keyView);

        System.out.println("-".repeat(90));
        var values = contacts.values();
        values.forEach(System.out::println);

        //HashMap also backs values View
        System.out.println("-".repeat(90));
        values.retainAll(ContactData.getData("email"));
        System.out.println(keyView);
        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        System.out.println("-".repeat(90));
        List<Contact> list = new ArrayList<>(values);
        list.sort(Comparator.comparing(Contact::getNameLastFirst));
        list.forEach(c -> System.out.println(c.getNameLastFirst() + " : " + c));

        System.out.println("-".repeat(90));
        Contact first = list.get(0);
        contacts.put(first.getNameLastFirst(), first);
        values.forEach(System.out::println);
        keyView.forEach(System.out::println);

        HashSet<Contact> set = new HashSet<>(values);
        set.forEach(System.out::println);
        if (set.size() < contacts.keySet().size()) {
            System.out.println("Duplicate Values are in my Map");
        }

        //EntrySet
        var nodeSet = contacts.entrySet();
        for(var node: nodeSet) {
            System.out.println(node.getClass().getName());
            if(!node.getKey().equals(node.getValue().getName())) {
                System.out.println("Key doesn't match name " + node.getKey() + " : "
                        + node.getValue());
            }
        }
    }
}

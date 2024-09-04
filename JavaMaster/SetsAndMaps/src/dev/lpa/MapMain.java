package dev.lpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {
    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");
        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println("-".repeat(90));

        Map<String, Contact> contacts = new HashMap<>();

        for(Contact contact: fullList) {
            contacts.put(contact.getName(), contact);
        }

        contacts.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
        System.out.println("-".repeat(30));
        System.out.println(contacts.get("Charlie Brown"));
        System.out.println(contacts.get("Tim"));

        //Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contacts.getOrDefault("Tim Brown", new Contact("Unknown name")));

        System.out.println("-".repeat(30));
        contacts.clear();
        for(Contact contact: fullList) {
            Contact duplicate = contacts.put(contact.getName(), contact);
            if(duplicate != null) {
//                System.out.println("duplicate = " + duplicate);
//                System.out.println("Current = " + contact);
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }

        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        System.out.println("-".repeat(90));

        contacts.clear();
        for(Contact contact: fullList) {
            contacts.putIfAbsent(contact.getName(), contact);
        }

        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        System.out.println("-".repeat(90));
        contacts.clear();

        fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
                Contact::mergeContactData));

        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        System.out.println("-".repeat(90));
        for(String contactName: new String[] {"Daisy Duck", "Daffy Duck",
            "Scrooge McDuck"}) {
            contacts.computeIfAbsent(contactName, Contact::new);
        }

        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        System.out.println("-".repeat(90));
        for(String contactName: new String[] {"Daisy Duck", "Daffy Duck",
                "Scrooge McDuck"}) {
            contacts.computeIfPresent(contactName, (k, v) -> {
                v.addEmail("Fun Place");
                return v;
            });
        }
        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        System.out.println("-".repeat(90));
        contacts.replaceAll((k, v) -> {
            String newEmail = k.replaceAll(" ", "") + "@funplace.com";
            v.replaceEmailIfExists("DDuck@funplace.com", newEmail);
            return v;
        });
        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        System.out.println("-".repeat(90));
        Contact daisy = new Contact("Daisy Jane Duck", "daisyj@duck.com");

        Contact replacedContact = contacts.replace("Daisy Duck", daisy);
        System.out.println("daisy = " + daisy);
        System.out.println("Replaced contact = " + replacedContact);
        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        System.out.println("-".repeat(90));
        Contact updatedDaisy = replacedContact.mergeContactData(daisy);
        System.out.println("updatedDaisy = " + updatedDaisy);
        boolean success = contacts.replace("Daisy Duck", daisy, updatedDaisy);
        if(success) {
            System.out.println("Successfully replaced element");
        } else {
            System.out.printf("Did not match on both key: %s and value: %s %n"
                    .formatted("Daisy Duck", replacedContact));
        }
        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        System.out.println("-".repeat(90));
        success = contacts.remove("Daisy Duck", daisy);
        if(success) {
            System.out.println("Successfully removed element");
        } else {
            System.out.printf("Did not match on both key: %s and value: %s %n"
                    .formatted("Daisy Duck", daisy));
        }
        contacts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }
}

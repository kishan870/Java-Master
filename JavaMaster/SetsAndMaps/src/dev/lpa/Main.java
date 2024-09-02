package dev.lpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");

        printData("Phone List", phones);
        printData("Email list", emails);

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);

        printData("Email Set", emailContacts);
        printData("Phone Set", phoneContacts);

        System.out.println("-".repeat(20));

        int index = emails.indexOf(new Contact("robin hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
        robinHood.addEmail("Sherwood Forest");
        robinHood.replaceEmailIfExists("RHood@sherwoodforest.com", "RHood@sherwoodforest.com");
        robinHood.replaceEmailIfExists("RHood@sherwoodforest.org", "RHood@sherwoodforest.com");
        robinHood.replaceEmailIfExists("RHood@sherwoodforest.com", "RHood@sherwoodforest.org");
        System.out.println(robinHood);

        System.out.println("-".repeat(20));

        Set<Contact> unionSet = new HashSet<>();
        unionSet.addAll(emailContacts);
        unionSet.addAll(phoneContacts);
        printData("(Emails ∪ Phones) Union of emails and phone contacts: ", unionSet);

        Set<Contact> intersectionSet = new HashSet<>(emailContacts);
        intersectionSet.retainAll(phoneContacts);

        printData("(Emails ∩ Phones) Intersection of emails and phone contacts:", intersectionSet);

        Set<Contact> differenceSet = new HashSet<>(emailContacts);
        differenceSet.removeAll(phoneContacts);

        printData("(Emails - Phones) Difference between emails and phone contacts:", differenceSet);

    }

    public static void printData(String header, Collection<Contact> contacts) {
        System.out.println("-".repeat(25));
        System.out.println(header);
        System.out.println("-".repeat(25));
        contacts.forEach(System.out::println);
    }
}

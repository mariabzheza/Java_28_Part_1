package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria on 13.04.2016.
 */
public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsoluteFile());
        Writer writer = new FileWriter(file);
        for (ContactData contact: contacts) {
            //writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getFirstName(), contact.getLastName(),
                    contact.getNickName(), contact.getCompany(), contact.getTitle(), contact.getHomeAddress(),
                    contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone(),contact.getFax(),
                    contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getHomePage(),
                    contact.getAddress2(), contact.getPhone2(), contact.getNotes(), contact.getGroup()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstName(String.format("FirstN %s", i))
                    .withLastName(String.format("LastN %s", i))
                    .withNickName(String.format("Nick %s", i))
                    .withCompany(String.format("Company %s", i))
                    .withTitle(String.format("Title %s", i))
                    .withHomeAddress((String.format("HomeAddr %s", i)))
                    .withHomePhone((String.format("HomePhone %s", i)))
                    .withMobile((String.format("Mobile %s", i)))
                    .withWorkPhone((String.format("WorkPhone %s", i)))
                    .withFax((String.format("Fax %s", i)))
                    .withEmail((String.format("1-Email %s", i)))
                    .withEmail2((String.format("2-Email %s", i)))
                    .withEmail3((String.format("3-Email %s", i)))
                    .withHomePage((String.format("HomePage %s", i)))
                    .withAddress2((String.format("2-Address %s", i)))
                    .withPhone2((String.format("2-Phone %s", i)))
                    .withNotes((String.format("Notes %s", i)))
                    .withGroup("test1")
            );
        }
        return contacts;
    }
}

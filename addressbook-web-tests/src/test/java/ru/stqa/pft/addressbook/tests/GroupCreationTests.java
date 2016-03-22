package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all(); //change .getList method to .all
        //GroupData group = new GroupData("test1", null, null);
        GroupData group = new GroupData().withName("test1");
        app.group().create(group);
        Set<GroupData> after = app.group().all(); //change .getList method to .all
        Assert.assertEquals(after.size(), (before.size()+1));

        //Comparator
        //group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        //easiest comparator was bellow:
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

        before.add(group);
        Assert.assertEquals(before,after);
    }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().getList();
        GroupData group = new GroupData("test1", null, null);
        app.group().create(group);
        List<GroupData> after = app.group().getList();
        Assert.assertEquals(after.size(), (before.size()+1));
        // use Comparator without previous for
        /*Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        int max = after.stream().max(byId).get().getId();
        group.setId(max);*/
        //short line without previously commented lines
        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Comparator<? super GroupData> byId1 = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId1);
        after.sort(byId1);
        Assert.assertEquals(before,after);
        //The old version of comparison
        //Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}

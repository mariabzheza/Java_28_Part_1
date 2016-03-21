package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by maria on 02.03.2016.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if ( app.group().getList().size() == 0) {
            app.group().create(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().getList();
        int index = before.size()-1;
        GroupData group = new GroupData(before.get(index).getId(), "test1", null, "test3");
        app.group().modify(index, group);
        List<GroupData> after = app.group().getList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        //using new in java 8 to sort before and after lists
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
        //The old version of comparison
        //Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}

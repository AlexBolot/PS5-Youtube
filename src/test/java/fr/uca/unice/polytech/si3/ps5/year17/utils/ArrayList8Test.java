package fr.uca.unice.polytech.si3.ps5.year17.utils;

import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class ArrayList8Test
{
    private Predicate<TestObject> positive = testObject -> testObject.val1 > 0 && testObject.val2 > 0 && testObject.val3 > 0;

    private Comparator<TestObject> comparator = (testObject1, testObject2) -> {
        if (testObject1.val1 > testObject2.val1) return 1;
        if (testObject1.val1 < testObject2.val1) return -1;

        if (testObject1.val2 > testObject2.val2) return 1;
        if (testObject1.val2 < testObject2.val2) return -1;

        return Integer.compare(testObject1.val3, testObject2.val3);
    };

    //region ==================== getRandom (1 -> 2) =================
    @Test
    public void getRandom_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.add(testObject);
            }

            assertTrue(list.contains(list.getRandom()));

            list.clear();
        }

        //----------------------------//

        list.clear();

        TestObject testObject1 = randTestObject();
        list.add(testObject1);

        assertEquals(testObject1, list.getRandom());

        //----------------------------//

        TestObject testObject2 = randTestObject();
        list.add(testObject2);

        TestObject random = list.getRandom();

        boolean got1 = random.equals(testObject1);
        boolean got2 = random.equals(testObject2);

        assertTrue(got1 || got2);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getRandom_Empty ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.getRandom();
    }
    //endregion

    //region ==================== removeRandom (1 -> 2) ==============
    @Test
    public void removeRandom_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.add(testObject);
            }

            int size = list.size();
            TestObject testObject = list.removeRandom();

            assertFalse(list.contains(testObject));
            assertEquals(size - 1, list.size());

            list.clear();
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeRandom_Empty ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.removeRandom();
    }
    //endregion

    //region ==================== addIf (1 -> 4) =====================
    @Test
    public void addIf_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        int count = 0;

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.addIf(testObject, positive);

                if (positive.test(testObject)) count++;
            }

            assertEquals(count, list.size());

            list.clear();
            count = 0;
        }

        list.clear();
        assertEquals(0, list.countIf(positive));
    }

    @Test (expected = NullPointerException.class)
    public void addIf_NullValue ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addIf(null, positive);
    }

    @Test (expected = NullPointerException.class)
    public void addIf_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addIf(randTestObject(), null);
    }

    @Test (expected = NullPointerException.class)
    public void addIf_NullBoth ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addIf(null, null);
    }
    //endregion

    //region ==================== addAllIf (1 -> 4) ==================
    @Test
    public void addAllIf_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        ArrayList8<TestObject> tmpList = new ArrayList8<>();
        int count = 0;

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 50; j++)
            {
                for (int k = 0; k < 10; k++)
                {
                    TestObject testObject = randTestObject();
                    tmpList.add(testObject);
                    if (positive.test(testObject)) count++;
                }

                list.addAllIf(tmpList, positive);

                tmpList.clear();
            }

            assertEquals(count, list.size());

            list.clear();
            count = 0;
        }

        list.clear();
        list.addAllIf(new ArrayList8<>(), positive);
        assertEquals(0, list.size());
    }

    @Test (expected = NullPointerException.class)
    public void addAllIf_NullValue ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addAllIf(null, positive);
    }

    @Test (expected = NullPointerException.class)
    public void addAllIf_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addAllIf(new ArrayList8<>(), null);
    }

    @Test (expected = NullPointerException.class)
    public void addAllIf_NullBoth ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addAllIf(null, null);
    }
    //endregion

    //region ==================== contains (1 -> 3) ==================
    @Test
    public void contains_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        boolean foundPositive = false;

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();

                list.add(testObject);

                if (positive.test(testObject)) foundPositive = true;
            }

            assertEquals(foundPositive, list.contains(positive));

            list.clear();
        }
    }

    @Test
    public void contains_Empty ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        assertFalse(list.contains(positive));
    }

    @Test (expected = NullPointerException.class)
    public void contains_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.contains(null);
    }
    //endregion

    //region ==================== countIf (1 -> 2) ===================
    @Test
    public void countIf_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        int count = 0;

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.add(testObject);

                if (positive.test(testObject)) count++;
            }

            assertEquals(count, list.countIf(positive));

            list.clear();
            count = 0;
        }

        list.clear();
        assertEquals(0, list.countIf(positive));
    }

    @Test (expected = NullPointerException.class)
    public void countIf_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.countIf(null);
    }
    //endregion

    //region ==================== sublist (1 -> 2) ===================
    @Test
    public void sublist_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        int count = 0;

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.add(testObject);

                if (positive.test(testObject)) count++;
            }

            assertEquals(count, list.subList(positive).size());

            list.clear();
            count = 0;
        }

        list.clear();
        assertEquals(0, list.subList(positive).size());
    }

    @Test (expected = NullPointerException.class)
    public void sublist_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.subList(null);
    }
    //endregion

    //region ==================== findAny (1 -> 2) ===================
    @Test
    public void findAny_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.add(testObject);
            }

            Optional<TestObject> any1 = list.stream().filter(positive).findAny();
            Optional<TestObject> any2 = list.findAny(positive);

            if (any1.isPresent() && any2.isPresent())
            {
                assertEquals(any1.get(), any2.get());
            }
            else fail("any not found");

            list.clear();
        }
    }

    @Test (expected = NullPointerException.class)
    public void findAny_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.findAny(null);
    }
    //endregion

    //region ==================== findFirst (1 -> 2) =================
    @Test
    public void findFirst_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.add(testObject);
            }

            Optional<TestObject> first1 = list.stream().filter(positive).findFirst();
            Optional<TestObject> first2 = list.findFirst(positive);

            if (first1.isPresent() && first2.isPresent()) assertEquals(first1.get(), first2.get());
            else fail("any not found");

            list.clear();
        }
    }

    @Test (expected = NullPointerException.class)
    public void findFirst_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.findFirst(null);
    }
    //endregion

    //region ==================== max (1 -> 2) =================
    @Test
    public void max_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            TestObject max = null;

            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();

                if (max == null || comparator.compare(testObject, max) > 0) max = testObject;

                list.add(testObject);
            }

            Optional<TestObject> optMax = list.max(comparator);

            if (optMax.isPresent()) assertEquals(max, optMax.get());
            else fail("any not found");

            list.clear();
        }
    }

    @Test (expected = NullPointerException.class)
    public void max_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.max(null);
    }
    //endregion

    //region ==================== min (1 -> 2) =================
    @Test
    public void min_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            TestObject min = null;

            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();

                if (min == null || comparator.compare(testObject, min) < 0) min = testObject;

                list.add(testObject);
            }

            Optional<TestObject> optMin = list.min(comparator);

            if (optMin.isPresent()) assertEquals(min, optMin.get());
            else fail("any not found");

            list.clear();
        }
    }

    @Test (expected = NullPointerException.class)
    public void min_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.min(null);
    }
    //endregion

    private TestObject randTestObject ()
    {
        int val1 = ThreadLocalRandom.current().nextInt();
        int val2 = ThreadLocalRandom.current().nextInt();
        int val3 = ThreadLocalRandom.current().nextInt();

        return new TestObject(val1, val2, val3);
    }

    //region ==================== Private classes ====================
    private class TestObject
    {
        int val1;
        int val2;
        int val3;

        TestObject (int val1, int val2, int val3)
        {
            this.val1 = val1;
            this.val2 = val2;
            this.val3 = val3;
        }
    }
    //endregion
}
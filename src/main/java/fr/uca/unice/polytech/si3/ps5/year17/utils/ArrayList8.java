package fr.uca.unice.polytech.si3.ps5.year17.utils;

import java.util.*;
import java.util.function.Predicate;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ArrayList8	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 17/12/17 22:47
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("WeakerAccess")
public class ArrayList8<E> extends ArrayList<E>
{
    private Random random = new Random();

    public E getRandom ()
    {
        if (this.isEmpty()) throw new IndexOutOfBoundsException("List is empty");

        return get(random.nextInt(this.size()));
    }

    public E removeRandom ()
    {
        if (this.isEmpty()) throw new IndexOutOfBoundsException("List is empty");

        return remove(random.nextInt(this.size()));
    }

    public boolean addIf (E value, Predicate<? super E> filter)
    {
        return filter.test(value) && add(value);
    }

    public int addAllIf (Collection<? extends E> collection, Predicate<? super E> filter)
    {
        Objects.requireNonNull(collection);
        Objects.requireNonNull(filter);

        ArrayList8<E> paramList = new ArrayList8<>();
        paramList.addAll(collection);

        ArrayList8<E> sublist = paramList.subList(filter);

        this.addAll(sublist);

        return sublist.size();
    }

    public boolean contains (Predicate<? super E> filter)
    {
        Objects.requireNonNull(filter);

        for (E e : this)
        {
            if (filter.test(e)) return true;
        }

        return false;
    }

    public int countIf (Predicate<? super E> filter)
    {
        Objects.requireNonNull(filter);

        int count = 0;

        for (E e : this)
        {
            if (filter.test(e)) count++;
        }

        return count;
    }

    public ArrayList8<E> subList (Predicate<? super E> filter)
    {
        Objects.requireNonNull(filter);

        ArrayList8<E> newList = new ArrayList8<>();

        this.forEach(e -> newList.addIf(e, filter));

        return newList;
    }

    public Optional<E> findAny (Predicate<? super E> filter)
    {
        Objects.requireNonNull(filter);

        return this.isEmpty() ? Optional.empty() : subList(filter).stream().findAny();
    }

    public Optional<E> findFirst (Predicate<? super E> filter)
    {
        Objects.requireNonNull(filter);

        for (E e : this)
        {
            if (filter.test(e)) return Optional.of(e);
        }

        return Optional.empty();
    }

    public Optional<E> max (Comparator<? super E> comparator)
    {
        Objects.requireNonNull(comparator);

        if (this.isEmpty()) return Optional.empty();

        E max = this.getRandom();

        for (E e : this)
        {
            if (comparator.compare(e, max) > 0) max = e;
        }

        return Optional.of(max);
    }

    public Optional<E> min (Comparator<? super E> comparator)
    {
        Objects.requireNonNull(comparator);

        if (this.isEmpty()) return Optional.empty();

        E min = this.get(0);

        for (E e : this)
        {
            if (comparator.compare(e, min) < 0) min = e;
        }

        return Optional.of(min);
    }
}

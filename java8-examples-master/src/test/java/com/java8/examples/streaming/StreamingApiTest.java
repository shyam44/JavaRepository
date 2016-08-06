package com.java8.examples.streaming;

import static com.java8.examples.TestUtil.getPersons;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.java8.examples.Person;

/**
 * Test case showing the use of the streaming API.
 */
public class StreamingApiTest {

    private List<Person> persons;

    @Before
    public void setUp() throws Exception {
        persons = getPersons();
    }

    @Test
    public void collectLastNamesOfFemales() throws Exception {
        List<String> femaleLastNames =
                persons.stream()
                .filter(p -> p.getGender().equals(Person.Gender.FEMALE))
                .<String> map(p -> p.getLastName())
                .collect(Collectors.<String> toList());

        assertThat(femaleLastNames, containsInAnyOrder("Jungle", "Smith"));
    }

    @Test
    public void testName() throws Exception {
        Stream<Integer> stream = Stream.generate(new Supplier<Integer>() {
            int count = 0;

            @Override
            public Integer get() {
                System.out.println("get" + count);
                return count++;
            }
        });

        stream
                .filter(i -> i.intValue() % 2 == 0)
                .limit(100);


    }
}

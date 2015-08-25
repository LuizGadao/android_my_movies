package com.luizgadao.testzup;

import com.luizgadao.testzup.model.Person;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by luizcarlos on 24/08/15.
 */
public class PersonTest {

    @Test(expected = IllegalArgumentException.class)
    public void personDontHaveAgeLessThanZero(){
        Person person = new Person( "Justina", -2 );
    }

    @Test
    public void personAgeLessThan16(){
        Person person = new Person( "Luiz", 15 );
        Assert.assertFalse( person.canVote() );
    }

    @Test
    public void personAgeBiggerThan16(){
        Person person = new Person( "Carlos", 22 );
        Assert.assertTrue( person.canVote() );
    }

}

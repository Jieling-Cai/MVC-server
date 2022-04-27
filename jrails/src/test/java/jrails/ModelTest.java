package jrails;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ModelTest {

    private Model model;
    private Testcase model1 = new Testcase();
    private Testcase model2 = new Testcase();



    @Before
    public void setUp() throws Exception {
        model = new Model(){};
    }



    @Test
    public void testtostrings(){
        //Model.reset();
        //model1.title1 = "gujiji";
        //model1.author1 = true;
        model1.number = 343;
        System.out.println(model1.id);
        //model2.title1 = "gsgrsg";
        //model2.author1 = false;
        model2.number = -343;
        //model1.read();
        //model1.author1 = true;
        //model1.toStrings();
        model1.save();
        //model2.save();
        //System.out.println(model2.id());
        System.out.println(model1.id());

        //model1.destroy();
        //model2.destroy();
        //Model.reset();
        Testcase o = Model.find(Testcase.class, 1);
        //Testcase t = Model.find(Testcase.class, 3);
        //System.out.println(Model.all(Testcase.class));
        o.number = 34;
        o.save();
        System.out.println(o.id());
        List<Testcase> bs = Model.all(Testcase.class);
    }



    @Test
    public void id() {
        assertThat(model.id(), notNullValue());
    }



    @After
    public void tearDown() throws Exception {
    }
}
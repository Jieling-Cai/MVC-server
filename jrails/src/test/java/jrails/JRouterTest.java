package jrails;

import books.BookController;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JRouterTest {

    private JRouter jRouter;

    @Before
    public void setUp() throws Exception {
        jRouter = new JRouter();
    }

    @Test
    public void addRoute() {
        jRouter.addRoute("GET", "/", String.class, "index");
        jRouter.addRoute("POST", "/", BookController.class, "new_book");
        assertThat(jRouter.getRoute("GET", "/"), is("java.lang.String#index"));
        Map<String,String> map = new HashMap<>();
        System.out.println(jRouter.route("POST","/",map).toString());
    }


}
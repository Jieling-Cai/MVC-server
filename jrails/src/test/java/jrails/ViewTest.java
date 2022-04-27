package jrails;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class ViewTest {
    private int n;
    @Before
    public void initial(){
        Html html = new Html();
        html.str = "abc";
        n = 343;
    }


    @Test
    public void empty() {
        assertThat(View.empty().toString(), isEmptyString());
    }

    @Test
    public void test1(){
        //System.out.println(View.br().toString());
        assertEquals(View.br().toString(),"<br/>");
        assertEquals(View.p(View.t(n)).toString(),"<p>343</p>");
        assertEquals(View.div(View.t(n)).toString(),"<div>343</div>");
        assertEquals(View.strong(View.t(n)).toString(),"<strong>343</strong>");
        assertEquals(View.h1(View.p(View.t(n))).toString(),"<h1><p>343</p></h1>");
        assertEquals(View.tr(View.t(n)).toString(),"<tr>343</tr>");
        assertEquals(View.td(View.t(n)).toString(),"<td>343</td>");
        assertEquals(View.table(View.t(n)).toString(),"<table>343</table>");
        assertEquals(View.textarea("dfg",View.t(n)).toString(),"<textarea name=dfg>343</textarea>");
        assertEquals(View.submit("Save").toString(),"<input type=\"submit\" value=\"Save\"/>");
    }
}
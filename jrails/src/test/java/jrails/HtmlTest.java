package jrails;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class HtmlTest {

    private Html html;
    private int n;

    @Before
    public void setUp() throws Exception {
        html = new Html();
        html.str = "a";
        n = 343;
    }

    @Test
    public void empty() {
        assertThat(View.empty().toString(), isEmptyString());
    }

    @Test
    public void test1(){
        assertEquals(html.br().toString(),"<br/>");
        assertEquals(html.p(html.t(n)).toString(),"<p>343</p>");
        assertEquals(html.div(View.t(n)).toString(),"<div>343</div>");
        assertEquals(html.seq(View.t(n)).toString(),"a343");
        assertEquals(html.link_to("Show","/show?id=42").toString(),"<a href=\"/show?id=42\">Show</a>");
        assertEquals(html.form("/create",View.t(n)).toString(),"<form action=\"/create\" accept-charset=\"UTF-8\" method=\"post\">343</form>");
        assertEquals(html.submit("Save").toString(),"<input type=\"submit\" value=\"Save\"/>");
        //System.out.println(html.form("/create",View.t(n)).toString());
        /*
        assertEquals(View.h1(View.p(View.t(n))).toString(),"<h1><p>343</p></h1>");
        assertEquals(View.tr(View.t(n)).toString(),"<tr>343</tr>");
        assertEquals(View.td(View.t(n)).toString(),"<td>343</td>");
        assertEquals(View.table(View.t(n)).toString(),"<table>343</table>");
        assertEquals(View.textarea("dfg",View.t(n)).toString(),"<textarea name=dfg>343</textarea>");
*/
    }
}

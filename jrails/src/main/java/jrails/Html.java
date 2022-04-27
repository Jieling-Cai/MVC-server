package jrails;

public class Html {
    public String str;
    public String toString() {
        return this.str;
    }

    public Html seq(Html h) {
        Html html = new Html();
        html.str = this.str + h.str;
        return html;
        //throw new UnsupportedOperationException();
    }

    public Html br() {
        Html html = new Html();
        html.str = "<br/>";
        return html;

    }

    public Html t(Object o) {
        Html html = new Html();
        html.str = o.toString();
        return html;
    }

    public Html p(Html child) {
        Html html = new Html();
        html.str = "<p>" + child.str + "</p>";
        return html;
    }

    public Html div(Html child) {
        Html html = new Html();
        html.str = "<div>" + child.str + "</div>";
        return html;

    }

    public Html strong(Html child) {
        Html html = new Html();
        html.str = "<strong>" + child.str + "</strong>";
        return html;

    }

    public Html h1(Html child) {
        Html html = new Html();
        html.str = "<h1>" + child.str + "</h1>";
        return html;

    }

    public Html tr(Html child) {
        Html html = new Html();
        html.str = "<tr>" + child.str + "</tr>";
        return html;
    }

    public Html th(Html child) {
        Html html = new Html();
        html.str = "<th>" + child.str + "</th>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public Html td(Html child) {
        Html html = new Html();
        html.str = "<td>" + child.str + "</td>";
        return html;

    }

    public Html table(Html child) {
        Html html = new Html();
        html.str = "<table>" + child.str + "</table>";
        return html;

    }

    public Html thead(Html child) {
        Html html = new Html();
        html.str = "<thead>" + child.str + "</thead>";
        return html;

    }

    public Html tbody(Html child) {
        Html html = new Html();
        html.str = "<tbody>" + child.str + "</tbody>";
        return html;

    }

    public Html textarea(String name, Html child) {
        Html html = new Html();
        html.str = "<textarea name=\"" + name +"\">"+ child.str + "</textarea>";
        return html;

    }

    public Html link_to(String text, String url) {
        Html html = new Html();
        html.str = "<a href=\"" + url +"\">"+ text + "</a>";
        return html;

    }

    public Html form(String action, Html child) {
        Html html = new Html();
        html.str = "<form action=\"" + action +"\" accept-charset=\"UTF-8\" method=\"post\">"+ child.str + "</form>";
        return html;

    }

    public Html submit(String value) {
        Html html = new Html();
        html.str = "<input type=\"submit\" value=\"" + value + "\"/>";
        return html;

    }
}
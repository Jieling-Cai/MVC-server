package jrails;

public class View {
    public static Html empty() {
        Html html = new Html();
        html.str = "";
        return html;
    }

    public static Html br() {
        Html html = new Html();
        html.str = "<br/>";
        return html;
    }

    public static Html t(Object o) {
        Html html = new Html();
        html.str = o.toString();
        return html;
    }

    public static Html p(Html child) {
        Html html = new Html();
        html.str = "<p>" + child.str + "</p>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html div(Html child) {
        Html html = new Html();
        html.str = "<div>" + child.str + "</div>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html strong(Html child) {
        Html html = new Html();
        html.str = "<strong>" + child.str + "</strong>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html h1(Html child) {
        Html html = new Html();
        html.str = "<h1>" + child.str + "</h1>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html tr(Html child) {
        Html html = new Html();
        html.str = "<tr>" + child.str + "</tr>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html th(Html child) {
        Html html = new Html();
        html.str = "<th>" + child.str + "</th>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html td(Html child) {
        Html html = new Html();
        html.str = "<td>" + child.str + "</td>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html table(Html child) {
        Html html = new Html();
        html.str = "<table>" + child.str + "</table>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html thead(Html child) {
        Html html = new Html();
        html.str = "<thead>" + child.str + "</thead>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html tbody(Html child) {
        Html html = new Html();
        html.str = "<tbody>" + child.str + "</tbody>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html textarea(String name, Html child) {
        Html html = new Html();
        html.str = "<textarea name=\"" + name +"\">"+ child.str + "</textarea>";
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html link_to(String text, String url) {
        Html html = new Html();
        html.str = "<a href=\"" + url +"\">"+ text + "</a>";
        return html;

        //throw new UnsupportedOperationException();
    }

    public static Html form(String action, Html child) {
        Html html = new Html();
        html.str = "<form action=\"" + action +"\" accept-charset=\"UTF-8\" method=\"post\">"+ child.str + "</form>";
        return html;

    //throw new UnsupportedOperationException();
    }

    public static Html submit(String value) {
        Html html = new Html();
        html.str = "<input type=\"submit\" value=\"" + value + "\"/>";
        return html;
        //throw new UnsupportedOperationException();
    }
}
package jrails;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JRouter {

    private class Route{
        public String verb;
        public String path;
        public Class clazz;
        public String method;
        public Route(String verb, String path, Class clazz, String method){
            this.verb = verb;
            this.path = path;
            this.clazz = clazz;
            this.method = method;
        }
    }

    Set<Route> routes = new HashSet<>();

    public void addRoute(String verb, String path, Class clazz, String method) {
        routes.add(new Route(verb,path,clazz,method));
    }

    // Returns "clazz#method" corresponding to verb+URN
    // Null if no such route
    public String getRoute(String verb, String path) {
        for(Route r: routes){
            if((r.verb == verb) && (r.path == path)){
                return  r.clazz.getName() + "#" + r.method;
            }
        }
        return null;
        //throw new UnsupportedOperationException();
    }

    // Call the appropriate controller method and
    // return the result
    public Html route(String verb, String path, Map<String, String> params) {
        String str = getRoute(verb,path);
        if(str == null)throw new UnsupportedOperationException();
        else{
            String [] arr = str.split("#");
            try {
                Class c = Class.forName(arr[0]);
                Object o = c.newInstance();

                System.out.println(arr[1]);
                Method meth = c.getMethod(arr[1],Map.class);
                return (Html) meth.invoke(o,params);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new UnsupportedOperationException();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new UnsupportedOperationException();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new UnsupportedOperationException();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new UnsupportedOperationException();
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new UnsupportedOperationException();
            }
        }


    }
}

package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
    
    @Test
    public void testTodo() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/todo");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
    
    @Test
    public void testBikey() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/bikey");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testboardingpass() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/boardingpass");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}

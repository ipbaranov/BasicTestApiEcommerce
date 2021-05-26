package steps;

import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.spi.AuthFilter;

public class OAuth2Filter implements AuthFilter {

    String accessToken;

    OAuth2Filter(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        requestSpec.replaceHeader("Authorization", "Bearer " + accessToken);
        return ctx.next(requestSpec, responseSpec);
    }
}

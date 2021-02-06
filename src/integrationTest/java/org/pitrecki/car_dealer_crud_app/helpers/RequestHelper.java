package org.pitrecki.car_dealer_crud_app.helpers;

import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.pitrecki.car_dealer_crud_app.helpers.ParamEntries.aParamEntries;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

public final class RequestHelper {

    private static final String EMPTY = "";

    private RequestHelper() {
    }


    public static ResultActions doPost(MockMvc mvc, String url, String content) throws Exception {
        return doRequest(mvc, POST, url, aParamEntries(), content);
    }

    public static ResultActions doGet(MockMvc mvc, String url, ParamEntries params) throws Exception {
        return doRequest(mvc, GET, url, params, EMPTY);
    }

    public static ResultActions doPut(MockMvc mvc, String url, ParamEntries params) throws Exception {
        return doRequest(mvc, PUT, url, params, EMPTY);
    }

    private static ResultActions doRequest(MockMvc mvc, HttpMethod httpMethod, String url,
                                           ParamEntries params,
                                           String content) throws Exception {
        return mvc.perform(request(httpMethod, url)
                .params(params.collect())
                .contentType(APPLICATION_JSON)
                .content(content));
    }
}

package de.tum.cit.dos.eist;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

public class AdditionFunction implements RequestHandler<APIGatewayProxyRequestEvent, Integer> {

    public Integer handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> inputParamMap = input.getQueryStringParameters();

        // TODO implement logic von Lambda function um addition zu machen, extract query strings
        if (inputParamMap != null) {
            int x = Integer.parseInt(inputParamMap.getOrDefault("x", "0"));
            int y = Integer.parseInt(inputParamMap.getOrDefault("y", "0"));
            return x + y;
        }
        return Integer.MIN_VALUE;
    }
}

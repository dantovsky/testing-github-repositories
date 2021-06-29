package api.retrofit.generic;

import api.calls.GenericCalls;
import api.mappings.generic.CreateGenericRequest;
import api.retrofit.RetrofitBuilder;
import lombok.NoArgsConstructor;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;

import static api.data.Common.ACCEPT_HEADER;
import static api.data.Common.TOKEN;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Generic {

    private static final GenericCalls genericCalls = new RetrofitBuilder().getRetrofit().create(GenericCalls.class);

    public static Response<ResponseBody> getGenericCall() throws IOException {
        return genericCalls.getGenericCall().execute();
    }

    public static Response<ResponseBody> createGenericCallNoAuth(CreateGenericRequest createGenericRequest) throws IOException {
        return genericCalls.createGenericCall(null, ACCEPT_HEADER, createGenericRequest).execute();
    }

    public static Response<ResponseBody> createGenericCall(CreateGenericRequest createGenericRequest) throws IOException {
        return genericCalls.createGenericCall(TOKEN, ACCEPT_HEADER, createGenericRequest).execute();
    }
}

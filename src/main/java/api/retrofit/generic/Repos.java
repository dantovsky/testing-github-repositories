package api.retrofit.generic;

import api.calls.RepoCalls;
import api.mappings.generic.Repo;
import api.retrofit.RetrofitBuilder;
import lombok.NoArgsConstructor;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;

import static api.data.Common.ACCEPT_HEADER;
import static api.data.Common.TOKEN;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Repos {

    private static final RepoCalls repoCalls = new RetrofitBuilder().getRetrofit().create(RepoCalls.class);

    public static Response<Repo> getRepoByRepoName(String name) throws IOException {
        return repoCalls.getRepoByRepoName(name).execute();
    }

//    public static Response<ResponseBody> createGenericCallNoAuth(Repo repo) throws IOException {
//        return repoCalls.createGenericCall(null, ACCEPT_HEADER, repo).execute();
//    }
//
//    public static Response<ResponseBody> createGenericCall(Repo repo) throws IOException {
//        return repoCalls.createGenericCall(TOKEN, ACCEPT_HEADER, repo).execute();
//    }
}

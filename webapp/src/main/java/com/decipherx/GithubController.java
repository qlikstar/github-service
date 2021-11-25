package com.decipherx;

import org.reactivestreams.Publisher;

import java.util.List;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Mono;

@Controller("/github") // <1>
public class GithubController {

    private final GithubLowLevelClient githubLowLevelClient;
    private final GithubApiClient githubApiClient;

    public GithubController(final GithubLowLevelClient githubLowLevelClient,
                            final GithubApiClient githubApiClient) { // <2>
        this.githubLowLevelClient = githubLowLevelClient;
        this.githubApiClient = githubApiClient;
    }

    @Get("/releases-lowlevel")
        // <3>
    Mono<List<GithubRelease>> releasesWithLowLevelClient() { // <4>
        return githubLowLevelClient.fetchReleases();
    }

    @Get(uri = "/releases", produces = MediaType.APPLICATION_JSON_STREAM)
        // <5>
    Publisher<GithubRelease> fetchReleases() { // <6>
        return githubApiClient.fetchReleases();
    }
}

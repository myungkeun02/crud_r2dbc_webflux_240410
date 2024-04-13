package org.myungkeun.crud_r2dbc_webflux_240410.services;

import org.myungkeun.crud_r2dbc_webflux_240410.entities.Blog;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.blog.BlogRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BlogService {
    Mono<Blog> createBlog(Blog request);
    Flux<Blog> getAllBlogs();

    Mono<Blog> getBlogById(Long id);
}

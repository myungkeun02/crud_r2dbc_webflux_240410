package org.myungkeun.crud_r2dbc_webflux_240410.controllers;

import lombok.AllArgsConstructor;
import org.myungkeun.crud_r2dbc_webflux_240410.entities.Blog;
import org.myungkeun.crud_r2dbc_webflux_240410.services.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/blog")
@AllArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping
    public Mono<ResponseEntity<Blog>> createBlog(
            @RequestBody Blog request
    ) {
        return blogService.createBlog(request)
                .map(saveBlog -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(saveBlog));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Blog> getAllBlog() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Blog> getBlogById(
            @PathVariable(name = "id") Long id
    ) {
        return blogService.getBlogById(id);
    }
}

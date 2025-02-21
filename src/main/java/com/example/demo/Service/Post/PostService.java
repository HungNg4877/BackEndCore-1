package com.example.demo.Service.Post;

import com.example.demo.Dto.PostDto;
import com.example.demo.Dto.Request.CreatePostRequest;
import com.example.demo.Dto.Request.PagingRequest;
import com.example.demo.Dto.Request.UpdatePostRequest;
import com.example.demo.Dto.Response.UserResponse;
import com.example.demo.Entity.Post;
import com.example.demo.Service.Filter.IdFilter;
import org.springframework.data.domain.Page;

public interface PostService {
    PostDto createPost(CreatePostRequest createPostRequest);

    PostDto updatePost(UpdatePostRequest updatePostRequest);

    void deletePost(String postId);

    Page<PostDto> getNews(PagingRequest pagingRequest);

    Post getPost(String postId);

    PostDto getPostDto(String postId);

    Page<UserResponse> getSharedList(PagingRequest<IdFilter> pagingRequest);
}

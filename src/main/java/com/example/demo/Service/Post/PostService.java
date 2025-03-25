package com.example.demo.Service.Post;

import com.example.demo.DTO.PostDTO;
import com.example.demo.DTO.Request.PagingRequest;
import com.example.demo.DTO.Request.PostRequest;
import com.example.demo.DTO.Response.UserResponse;
import com.example.demo.Entity.Post;
import com.example.demo.Service.Filter.IdFilter;
import org.springframework.data.domain.Page;

public interface PostService {
    PostDTO createPost(PostRequest postRequest);

    PostDTO updatePost(PostRequest postRequest);

    void deletePost(String postId);

    Page<PostDTO> getNews(PagingRequest pagingRequest);

    Post getPost(String postId);

    PostDTO getPostDto(String postId);

    Page<UserResponse> getSharedList(PagingRequest<IdFilter> pagingRequest);
}

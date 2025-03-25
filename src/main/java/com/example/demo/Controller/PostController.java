package com.example.demo.Controller;

import com.example.demo.Common.Success.SuccessMessage;
import com.example.demo.DTO.PostDTO;
import com.example.demo.DTO.Request.PagingRequest;
import com.example.demo.DTO.Request.ReportPostRequest;
import com.example.demo.DTO.Request.PostRequest;
import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.DTO.Response.UserResponse;
import com.example.demo.Service.Filter.IdFilter;
import com.example.demo.Service.Post.PostService;
import com.example.demo.Service.Post.ReportPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.Common.EndPoint.EndPointConstant.*;

@RestController
@RequestMapping(value = POSTS)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final ReportPostService reportPostService;
    @PostMapping
    public ResponseEntity<ApiResponse<PostDTO>> createPost(@RequestBody PostRequest postRequest) {
        PostDTO response = postService.createPost(postRequest);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED.value(), SuccessMessage.SUCCESS.getMessage(), response));
    }
    @PutMapping
    public ResponseEntity<ApiResponse<PostDTO>> updatePost(@RequestBody PostRequest postRequest) {
        PostDTO postDto = postService.updatePost(postRequest);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), postDto));
    }
    @DeleteMapping(value = POST_ID)
    public ResponseEntity<ApiResponse<String>> deletePost(@PathVariable("postId") String postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), null));
    }
    @GetMapping(value = POST_ID)
    public ResponseEntity<ApiResponse<PostDTO>> getPostByID(@PathVariable("postId") String postId) {
        return ResponseEntity.ok(ApiResponse.<PostDTO>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.SUCCESS.getMessage())
                .data(postService.getPostDto(postId))
                .build());
    }
    @PostMapping(value = GET_POST_NEWS)
    public ResponseEntity<ApiResponse<Page<PostDTO>>> getNews(@RequestBody PagingRequest pagingRequest) {
        Page<PostDTO> postsDto = postService.getNews(pagingRequest);
        return ResponseEntity.ok(
                ApiResponse.<Page<PostDTO>>builder()
                        .code(HttpStatus.OK.value())
                        .message(SuccessMessage.SUCCESS.getMessage())
                        .data(postsDto)
                        .build()
        );

    }
    @PostMapping(value = REPORT_POST)
    public ResponseEntity<ApiResponse<Object>> report(@RequestBody ReportPostRequest reportPostRequest) {
        reportPostService.report(reportPostRequest);
        return ResponseEntity.ok(ApiResponse.<Object>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.SUCCESS.getMessage())
                .data(null)
                .build());
    }

    @PostMapping(value = SHARE)
    public ResponseEntity<ApiResponse<Page<UserResponse>>> getShare(@RequestBody PagingRequest<IdFilter> pagingRequest) {

        return ResponseEntity.ok(ApiResponse.<Page<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.SUCCESS.getMessage())
                .data(postService.getSharedList(pagingRequest))
                .build());
    }
}

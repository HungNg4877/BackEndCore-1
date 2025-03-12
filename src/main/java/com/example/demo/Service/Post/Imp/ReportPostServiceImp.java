//package com.example.demo.Service.Post.Imp;
//
//import com.example.demo.Dto.Request.ReportPostRequest;
//import com.example.demo.Entity.Post;
//import com.example.demo.Entity.PostReport;
//import com.example.demo.Entity.User;
//import com.example.demo.Repository.PostReportRepository;
//import com.example.demo.Service.Post.PostService;
//import com.example.demo.Service.Post.ReportPostService;
//import com.example.demo.Service.Setting.SettingService;
//import com.example.demo.Service.User.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class ReportPostServiceImp implements ReportPostService {
//    private final UserService userService;
//    private final PostService postService;
//    private final PostReportRepository postReportRepository;
//    private final SettingService settingService;
//
//    @Override
//    public void report(ReportPostRequest reportPostRequest) {
//        User user = userService.getCurrentUser();
//        Optional<PostReport> postReport = postReportRepository.findByUser(user);
//        if (postReport.isPresent()) {
//            postReport.get().setReason(reportPostRequest.getReason());
//            postReportRepository.save(postReport.get());
//        } else {
//            Post post = postService.getPost(reportPostRequest.getPostId());
//            saveReport(reportPostRequest, post, user);
//            handleMaxReport(post);
//        }
//    }
//
//    private void handleMaxReport(Post post) {
//        int totalReport = postReportRepository.findTotalReport(post.getId());
//        int maxReport = settingService.getMaxReport();
//        if (totalReport >= maxReport) {
//            postService.deletePost(post.getId().toString());
//        }
//    }
//
//    private void saveReport(ReportPostRequest reportPostRequest, Post post, User user) {
//        PostReport report = PostReport.builder()
//                .post(post)
//                .user(user)
//                .reason(reportPostRequest.getReason())
//                .build();
//        postReportRepository.save(report);
//    }
//}

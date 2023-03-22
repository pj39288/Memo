package com.doongie.memo.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doongie.memo.post.bo.PostBO;
import com.doongie.memo.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/list/view")
	public String memoList(
			Model model
			, HttpSession session) {
		
		// object를 다운캐스팅한것
		int userId = (Integer) session.getAttribute("userId");
		
		// view 화면에서 표시할 것이기 때문에 view api에서 get처리함
		List<Post> postList = postBO.getPostList(userId);
		
		model.addAttribute("postList", postList);
		
		return "/post/list";
	}
	
	@GetMapping("/detail/view")
	public String memoDetail(
			@RequestParam("id") int postId
			, Model model) {
		
		Post post = postBO.getPost(postId);
		
		model.addAttribute("post", post);
		
		return "post/detail";
	}
}

package com.untact.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.dao.ReplyDao;
import com.untact.dto.Member;
import com.untact.dto.Reply;
import com.untact.dto.ResultData;

@Service
public class ReplyService {
	@Autowired
	private ReplyDao replyDao;
	
	@Autowired
	private MemberService memberService;

	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {
		return replyDao.getForPrintReplies(relTypeCode, relId);
	}

	public Reply getReply(int id) {
		return replyDao.getReply(id);
	}

	public ResultData getActorCanDeleteRd(Reply reply, Member actor) {
		if (reply.getMemberId() == actor.getId()) {
			return new ResultData("S-1", "가능합니다.");
		}

		if (memberService.isAdmin(actor)) {
			return new ResultData("S-2", "가능합니다.");
		}

		return new ResultData("F-1", "권한이 없습니다.");
	}

	public ResultData deleteReply(int id) {
		replyDao.deleteReply(id);

		return new ResultData("S-1", "삭제하였습니다.", "id", id);
	}

	public ResultData getActorCanModifyRd(Reply reply, Member actor) {
		return getActorCanDeleteRd(reply, actor);
	}

	public ResultData modifyReply(int id, String body) {
		replyDao.modifyReply(id, body);

		return new ResultData("S-1", "댓글을 수정하였습니다.", "id", id);
	}

}

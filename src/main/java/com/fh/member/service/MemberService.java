package com.fh.member.service;

import com.fh.commons.ServerResponse;
import com.fh.member.model.Member;

public interface MemberService {
    ServerResponse checkMemberName(String name);

    ServerResponse getUserByphone(String phone);

    ServerResponse register(Member member);

    ServerResponse login(Member member);
}

package org.jinju.admin.global.advices;

import lombok.RequiredArgsConstructor;
import org.jinju.member.entities.Member;
import org.jinju.member.libs.MemberUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@ControllerAdvice("org.jinju.admin")
public class AdminControllerAdvice {
    private final MemberUtil memberUtil;

    @ModelAttribute("profile")
    public Member profile() {
        return memberUtil.getMember();
    }
}

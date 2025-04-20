package org.jinju.mypage.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jinju.global.annotations.ApplyErrorPage;
import org.jinju.global.libs.Utils;
import org.jinju.member.MemberInfo;
import org.jinju.member.entities.Member;
import org.jinju.member.libs.MemberUtil;
import org.jinju.member.services.MemberInfoService;
import org.jinju.member.services.MemberUpdateService;
import org.jinju.member.social.services.KakaoLoginService;
import org.jinju.mypage.validators.ProfileValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@ApplyErrorPage
@RequestMapping("/mypage")
@RequiredArgsConstructor
@SessionAttributes("profile")
public class MypageController {
    private final Utils utils;
    private final MemberUtil memberUtil;
    private final ModelMapper modelMapper;
    private final MemberUpdateService updateService;
    private final ProfileValidator profileValidator;
    private final MemberInfoService infoService;
    private final KakaoLoginService kakaoLoginService;

    @ModelAttribute("profile")
    public Member getMember() {
        return memberUtil.getMember();
    }

    @GetMapping
    public String index(Model model) {
        commonProcess("main", model);

        return utils.tpl("mypage/index");
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        commonProcess("profile", model);

        Member member = memberUtil.getMember();
        RequestProfile form = modelMapper.map(member, RequestProfile.class);
        String optionalTerms = member.getOptionalTerms();
        if (StringUtils.hasText(optionalTerms)) {
            form.setOptionalTerms(Arrays.stream(optionalTerms.split("\\|\\|")).toList());
        }

        form.setKakaoLoginConnectUrl(kakaoLoginService.getLoginUrl("connect"));
        form.setKakaoLoginDisconnectUrl(kakaoLoginService.getLoginUrl("disconnect"));

        model.addAttribute("requestProfile", form);

        return utils.tpl("mypage/profile");
    }

    @PatchMapping("/profile")
    public String updateProfile(@Valid RequestProfile form, Errors errors, Model model) {
        commonProcess("profile", model);

        profileValidator.validate(form, errors);

        if (errors.hasErrors()) {
            return utils.tpl("mypage/profile");
        }

        updateService.process(form);

        // 프로필 속성 변경
        model.addAttribute("profile", memberUtil.getMember());

        return "redirect:/mypage"; // 회원 정보 수정 완료 후 마이페이지 메인 이동
    }

    @ResponseBody
    @GetMapping("/refresh")
    public void refresh(Principal principal, Model model, HttpSession session) {

        MemberInfo memberInfo = (MemberInfo) infoService.loadUserByUsername(principal.getName());
        session.setAttribute("member", memberInfo.getMember());

        model.addAttribute("profile", memberInfo.getMember());
    }
    

    /**
     * 컨트롤러 공통 처리 영역
     *
     * @param mode
     * @param model
     */
    private void commonProcess(String mode, Model model) {
        mode = StringUtils.hasText(mode) ? mode : "main";
        String pageTitle = utils.getMessage("마이페이지");

        List<String> addCommonScript = new ArrayList<>();
        List<String> addScript = new ArrayList<>();

        if (mode.equals("profile")) { // 회원정보 수정
            addCommonScript.add("fileManager");
            addCommonScript.add("address");
            addScript.add("mypage/profile");
            pageTitle = utils.getMessage("회원정보_수정");
        } else if (mode.equals("wishlist")) { // 찜하기 목록
            addCommonScript.add("wish");
            pageTitle = utils.getMessage("나의_WISH");
        }

        model.addAttribute("addCommonScript", addCommonScript);
        model.addAttribute("addScript", addScript);
        model.addAttribute("pageTitle", pageTitle);
    }
}

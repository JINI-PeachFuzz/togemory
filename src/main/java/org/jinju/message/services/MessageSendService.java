package org.jinju.message.services;

import lombok.RequiredArgsConstructor;
import org.jinju.file.services.FileDoneService;
import org.jinju.member.entities.Member;
import org.jinju.member.exceptions.MemberNotFoundException;
import org.jinju.member.libs.MemberUtil;
import org.jinju.member.repositories.MemberRepository;
import org.jinju.message.constants.MessageStatus;
import org.jinju.message.controllers.RequestMessage;
import org.jinju.message.entities.Message;
import org.jinju.message.repositories.MessageRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
@RequiredArgsConstructor
public class MessageSendService {
    private final MemberUtil memberUtil;
    private final MemberRepository memberRepository;
    private final MessageRepository repository;
    private final FileDoneService fileDoneService;

    public Message process(RequestMessage form) {

        String email = form.getEmail();
        Member receiver = !form.isNotice() ? memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new) : null;

        Message message = Message.builder()
                .gid(form.getGid())
                .notice(form.isNotice())
                .subject(form.getSubject())
                .content(form.getContent())
                .sender(memberUtil.getMember())
                .receiver(receiver)
                .status(MessageStatus.UNREAD)
                .build();

        repository.saveAndFlush(message);
        fileDoneService.process(form.getGid()); // 파일 업로드 완료 처리

        return message;
    }
}

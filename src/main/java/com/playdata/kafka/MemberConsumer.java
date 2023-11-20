package com.playdata.kafka;

import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.kafka.Action;
import com.playdata.domain.member.kafka.MemberKafka;
import com.playdata.domain.member.repository.MemberRepository;
import com.playdata.exception.NoMemberByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberConsumer {
    private final MemberRepository memberRepository;

    // TODO memberListen을 할 때 각각의 action의 따라서 다른 결과가 되어야 함
    // TODO 현재는 받아서 로직으로 구현 insert, update, delete를 action에서 확인해서 처리
    @KafkaListener(topics = TopicConfig.MEMBER)
    public void listen(MemberKafka data) {
        if(data.action()==Action.CREATE) {
            memberRepository.save(data.ToEntity());
        }
        else if(data.action()==Action.UPDATE) {
            memberRepository.save(data.ToEntity());
        }
        else {
            Optional<Member> getMemberByKafkaData = memberRepository.findById(UUID.fromString(data.id()));
            Member member = getMemberByKafkaData.orElseThrow(()->
                    new NoMemberByIdException("No Member . id = {%s}".formatted(data.id())));
            member.delete();
        }
    }
    @KafkaListener(topics = TopicConfig.memberDLT)
    public void dltListen(byte[] dlt){
        System.out.println("dltLinsten : " + new String(dlt));
    }
}

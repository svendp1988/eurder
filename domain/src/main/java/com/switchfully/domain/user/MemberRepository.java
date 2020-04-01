package com.switchfully.domain.user;

//import com.switchfully.domain.DummyData;
//import com.switchfully.domain.exceptions.EmailAlreadyRegisteredException;
//import com.switchfully.domain.exceptions.InssAlreadyRegisteredException;
//import com.switchfully.domain.exceptions.MemberNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    private final ConcurrentHashMap<String, Member> memberRepository;
//    private final DummyData dummyData;

    public MemberRepository() {
        this.memberRepository = new ConcurrentHashMap<>();
//        this.dummyData = dummyData;
//        addDefaultData();
    }

    public Collection<Member> getAllMembers() {
        return memberRepository.values();
    }

//    public Member getMemberByEmail(String email) {
//        return memberRepository.values().stream()
//                .filter(member -> (member.getEmail().equals(email)))
//                .findFirst()
//                .orElseThrow(MemberNotFoundException::new);
//    }
//
//    public boolean isEmailAvailable(String email) {
//        if (memberRepository.values().stream()
//                .anyMatch(member -> member.getEmail().equals(email))) {
//            throw new EmailAlreadyRegisteredException(email);
//        }
//        return true;
//    }
//
//    public boolean isInssAvailable(String inss) {
//        if (memberRepository.values().stream()
//        .anyMatch(member -> member.getINSS().equals(inss))) {
//            throw new InssAlreadyRegisteredException(inss);
//        }
//        return true;
//    }
//
//    private void addDefaultData() {
//        for(Member member : dummyData.getDefaultMembers()) {
//            this.registerNewMember(member);
//        }
//    }

    public Member registerNewMember(Member newMember) {
        memberRepository.put(newMember.getId(), newMember);
        return newMember;
    }

    public Member getMemberById(String id) {
        return memberRepository.get(id);
    }
}

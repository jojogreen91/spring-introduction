package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // HashMap 을 이용해서 부여된 아이디와 Member 객체를 매핑하여 저장한다. 실질적 저장소 역할. static 인것에 유의.
    private static Map<Long, Member> store = new HashMap<>();
    // 아이디에 부여될 sequence 숫자 설정
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // sequence 값을 설정해놓고 순차적으로 아이디 부여
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // store.values() 는 Collection(List) 을 반환하기 때문에 바로 넣어서 ArrayList 를 생성해준다.
        return new ArrayList<>(store.values());
    }

    // store Hashmap 에 저장되어 있던 것들을 전부 삭제 시켜주는 메서드, 초기화
    public void clearStore () {
        store.clear();
    }
}

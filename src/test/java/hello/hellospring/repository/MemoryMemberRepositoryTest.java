package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;

// 해당 요소를 static import 시켜서 .assertThat() 과 같은 메서드를 바로 사용 할 수 있다.
import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    // 사용 할 repository 를 하나 선언해준다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각각 테스트 메서드가 종료 될 때 자동적으로 실행하도록 하는 메서드
    @AfterEach
    public void afterEach() {
        // repository 초기화를 통해 각각의 테스트간의 간섭을 없애기 위한 방법
        repository.clearStore();
    }

    @Test // 해당 메서드를 실행해서 테스트를 하기 위해서 붙여야 하는 Annotation
    public void save () {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // assert 라이브러리의 메서드를 활용해서 테스트 검증
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName () {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll () {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

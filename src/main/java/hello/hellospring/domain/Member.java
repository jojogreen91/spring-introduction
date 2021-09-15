package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // jpa 관리하는 entity
public class Member {

    @Id // 이건 id 라는걸 표시하는 jpa Annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db 에서 id의 값을 자동으로 부여한다는 설정
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

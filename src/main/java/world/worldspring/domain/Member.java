package world.worldspring.domain;

// jpa는 인터페이스만 제공이 된다.
// jpa는 객체랑 ORM이라는 기술이다.
// ORM(Object Relational Mapping): 객체(Object)와 데이터베이스 테이블(Relational)을 연결시킨다.(Mapping)

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 회원도메인
@Entity // 이 어노테이션을 설정해놓으면 이건 jpa가 관리하는 Entity다 라는 뜻
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 식별자(데이터를 구분하기 위한 시스템이 저장한 id)
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

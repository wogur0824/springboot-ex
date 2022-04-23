package world.worldspring.domain;

// 회원도메인
public class Member {
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

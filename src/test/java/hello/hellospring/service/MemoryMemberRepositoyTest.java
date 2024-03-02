package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoyTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach// 메소드가 끝날때마다 실행되는 코드 => 여기서는 clean 메소드 역할한다
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");
        repository.save(member);
        Member result=repository.findById(member.getId()).get();
        System.out.print("result = "+ (result == member));
        Assertions.assertEquals(member, result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);

    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
    }

}

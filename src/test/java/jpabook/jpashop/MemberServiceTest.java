package jpabook.jpashop;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Rollback(false)
	public void signup() throws Exception {
		//given
		Member member = new Member();
		member.setName("kim");
		
		//when
		Long savedId = memberService.join(member);
		
		//then
		em.flush();
		assertEquals(member, memberRepository.findOne(savedId));
	}
	
	@Test
	public void duplicatedException() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");
		
		//when
		memberService.join(member1);
		try {
			memberService.join(member2);						
		}catch(IllegalStateException e) {
			return;
		}
	
		//then
		fail("예외가 발생해야 한다.");
	}
}
